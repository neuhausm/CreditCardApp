import java.util.Scanner;
public class ManageCCWithUser {
	public static void main(String [] args) {
		try {
			Scanner input = new Scanner (System.in);
			int select=0;
			ManageCreditCards manage= new ManageCreditCards();
			do {
				select = mainMenu(input);
				if (select == 9) {
					manage.menu2();
				}
				manage.menu(select);
			} while ( select != 10);
			System.out.println("Thanks for using our cc app! ");
		}
		catch (Exception e) {
			System.out.println("Seems like an error occurred! Please try again later.\n " + e);
		}
	}

	public static int mainMenu(Scanner input) {
		System.out.println("\nMain Menu:");
		System.out.println("---------------------------------------------");
		System.out.println("1. Add a new CreditCard\n"
				+ "2. Remove a CreditCard\n"
				+ "3. Display total outstanding balances \n"
				+ "4. Display total available credit \n"
				+ "5. Display largest purchase  \n"
				+ "6. Display most recent payment \n"
				+ "7. Display total spent on certain category of expense \n"
				+ "8. For which type of Purchase was the most money spent \n"
				+ "9. Manage a specific Credit Card \n"
				+ "10.Exit \nAnswer: ");
		int choice = input.nextInt();
		while (choice < 1 || choice > 10) {
			System.out.println("Invalid. Please choose a number between 1 and 10: ");
			choice = input.nextInt(); 
		}
		return choice;
	}
}