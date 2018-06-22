package main;

import java.util.Scanner;

import listener.DicListenner;

public class RUN {
	public static void main(String[] args) {
		
		DicListenner.start();
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			if(in.nextLine().equals("exit")){
				System.exit(0);
			}
		}
	}

}
