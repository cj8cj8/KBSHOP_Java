package uuu.vgb.service;

import uuu.vgb.entity.Customer;
import uuu.vgb.exception.LoginFailedException;
import uuu.vgb.exception.VGBException;

public class CustomerService {
	private CustomersDAO dao = new CustomersDAO();

	public Customer login(String idorEmail, String password) throws VGBException {
		if (idorEmail == null || idorEmail.length() == 0 || password == null) {
			throw new IllegalArgumentException("未輸入帳號密碼");
		}
		Customer c = null;
		c = dao.selectCustomerById(idorEmail);
		if (c != null) {

			if (!c.getPassword().equals(password)) {
				c = null;

			}

		}
		if (c != null) {
			return c;
		} else {
			throw new LoginFailedException("登入失敗");
		}

	}

	public void register(Customer c) throws VGBException {
		if (c == null) {

			throw new IllegalArgumentException("註冊會員:會員註冊客戶物件不得為null");
		}

		dao.insert(c);
	}
	public void update(Customer c) throws VGBException {
		if (c == null) {

			throw new IllegalArgumentException("修改會員:客戶物件不得為null");
		}

		dao.update(c);
	}
}
