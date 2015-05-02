package se.sds.crazyquotes.main;

import java.util.Scanner;

import se.sds.crazyquotes.api.EService;
import se.sds.crazyquotes.model.User;

public class UserOptions {

	Scanner input = new Scanner(System.in);
	
	EService eService = new EService();

	public void menu() {
		System.out.println("USER MENU\n");
		System.out.println("1. Create user account");
		System.out.println("2. Login");
		System.out.println("0. Quit\n");
		System.out.println("Option: \n");
	}

	public void selectOption(int option) {
		switch (option) {
		case 1: // Create account
			for (;;) {
				System.out.println("Username (email): ");
				String email = input.nextLine();
				System.out.println("Password: ");
				String password = input.nextLine();
				eService.createUser(new User(-1, email, password));
				break;
			}
			break;
		case 2: // Login
			String email, password;
			boolean result;				
			do {
				System.out.println("Username:");
				email = input.nextLine();
				System.out.println("Password:");
				password = input.nextLine();
				result = eService.validateUser(new User(-1, email, password));
				if (result == false) {
					System.out.println("\nWrong username or/and password\n");
				} else {
					System.out.println("\nLogin success...\n");
				}
			} while (result != true);	
			break;
		case 0: // Quit
			break;
		}	

	}
	
}
