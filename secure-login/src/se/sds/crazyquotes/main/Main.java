package se.sds.crazyquotes.main;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		UserOptions userOptions = new UserOptions();
		
		Scanner input = new Scanner(System.in);
		int option;

		for (;;) {
			userOptions.menu();
			option = input.nextInt();
			input.nextLine();

			if (option == 0) break;

			System.out.println();
			userOptions.selectOption(option);
		}
	}
}

