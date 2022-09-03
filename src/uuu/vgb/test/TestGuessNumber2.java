package uuu.vgb.test;

import java.util.Random;
import java.util.Scanner;

public class TestGuessNumber2 {

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
		System.out.println(answer);
		
		int target;
		do {
			target=scanner.nextInt();
			guessNum++;
			
			if(target<1||target>bound) 
			{
				System.out.println("輸入數字超出範圍，遊戲強制結束");
				break;
			}else if(guessNum<guessMax&&answer==target) {
				System.out.println("恭喜猜對了");
				return;
			}else if(guessNum<guessMax&&answer!=target) {
				System.out.println("猜錯了重新輸入，目前剩餘"+(guessMax-guessNum)+"次");
				
			}else break;
			
			
		}while(guessNum<guessMax&&answer!=target);
		System.out.println("GG!"+"答案:"+answer);
	}
}


