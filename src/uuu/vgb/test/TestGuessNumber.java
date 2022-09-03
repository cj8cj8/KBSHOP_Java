package uuu.vgb.test;

import java.util.Random;
import java.util.Scanner;

public class TestGuessNumber {

	public static void main(String[] args) {
		int bound;
		int guessNum=0;
		Random random=new Random();
		
		
		Scanner scanner=new Scanner(System.in);
		System.out.println("請設定範圍0~?");
		bound=scanner.nextInt();
		int answer=random.nextInt(bound)+1;
		System.out.println("請設定次數上限");
		int guessMax=scanner.nextInt();
		System.out.println("可猜"+guessMax+"次");
		System.out.println("請輸入1~"+bound+"之間的整數");
		System.out.println(answer);
		String input=scanner.next();
		//int target=0;
		int target=scanner.nextInt();
//		if(input!=null&&input.matches("\\d")) {
//			target=Integer.parseInt(input);
//		}
		while(guessNum<guessMax&&answer!=target) {			
			if(target<1||target>bound) {
				System.out.println("輸入數字超出範圍，遊戲強制結束");
				break;
			}
			guessNum++;
				System.out.println("猜錯了重新輸入，目前已猜"+guessNum+"次");
				target=scanner.nextInt();	
		}if(guessNum<guessMax&&answer==target) {
			
				System.out.println("恭喜猜對了");
			
		}else {
			System.out.println("GG!"+"答案:"+answer);
		}
			
	}

}

