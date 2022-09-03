package uuu.vgb.test;

import uuu.vgb.entity.PaymentType;
import uuu.vgb.entity.ShippingType;

public class TestEnumPaymentTypeShippingType {

	public static void main(String[] args) {
		System.out.println("*****付款方式*****");
		PaymentType atm=PaymentType.ATM;
		PaymentType[]pArray=PaymentType.values();
		for (PaymentType ptype:pArray) {
			System.out.println(ptype.toString());
			System.out.println(ptype.ordinal());
			System.out.println(ptype.name());
		}
		System.out.println("*****取貨方式*****");
		ShippingType[]sArray=ShippingType.values();
		for (ShippingType stype:sArray) {
			System.out.println(stype.toString());
			System.out.println(stype.ordinal());
			System.out.println(stype.name());
		}
	}

}
