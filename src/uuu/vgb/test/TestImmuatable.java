package uuu.vgb.test;

public class TestImmuatable {

	public static void main(String[] args) {
		Integer i=1;
		Integer j=i;
		System.out.println(i==j);
		i=i++;
		System.out.println(i);
		System.out.println(j);
		
		String s="Hello";
		System.out.println(s);
		s+="World";
		System.out.println(s);
		
		int x=1;
		int y=x;
		x++;
		System.out.println(x);
		System.out.println(y);
		
		
	}

}
