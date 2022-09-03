package uuu.vgb.test;

public class Test99Table {

	public static void main(String[] args) {
		final int start = 1;
		final int end = 9;
		
outer: 		for(int i=start;i<=end;i++) {
			int innerStart = 1;
			int innerEnd = 9;
inner:		for(int j=innerStart;j<=innerEnd;j++) {
				System.out.print(i + "*" + j + "=" + i*j + (j<innerEnd?"\t":"\n"));
				if(i==j) {
					System.out.println();
					continue outer;
				}
			}			
		}
		System.out.println("The End");
//		System.out.print("1 * 1 = " + 1*1 + "\t");
//		System.out.print("1 * 2 = " + 1*2 + "\t");
//		System.out.println("1 * 3 =" + 1*3);
		
//		System.out.print("2 * 1 = " + 2*1 + "\t");
//		System.out.print("2 * 2 = " + 2*2 + "\t");
//		System.out.println("2 * 3 =" + 2*3);
//		
//		System.out.print("3 * 1 =" + 3*1 + "\t");
//		System.out.print("3 * 2 =" + 3*2 + "\t");
//		System.out.println("3 * 3 =" + 3*3);

	}

}
