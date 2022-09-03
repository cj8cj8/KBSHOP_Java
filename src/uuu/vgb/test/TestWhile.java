package uuu.vgb.test;

public class TestWhile {

	public static void main(String[] args) {
		int i=0;
		while( i<10)
		{
			//i++;
			System.out.println(i++);
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
