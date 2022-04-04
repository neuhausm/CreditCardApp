import java.time.LocalDate;
import java.util.Scanner;

public class ManageCreditCards {

	private CreditCards ccs;
	private Scanner input;
	private String regex;

	public ManageCreditCards() {
		ccs = new CreditCards();
		this.input = new Scanner(System.in);
		this.regex = "[0-9]+"; //make sure card number is only positive digits 	
	}

	public void menu(int choice) {
		
		switch (choice) {
		case 1:
			// add new credit card
			Date issueDate, expirationDate;
			CCType ccType;
			String bankString; 
			BankName bank;  
			double ccLimit, ccBalance;
			String cardNum;
			String ccTypeInput;
			
			do {
				System.out.print("Please enter your CC Type (VISA,AMEX,MASTERCARD AND DISCOVER) : ");
				ccTypeInput = input.nextLine().toUpperCase();
			} while (!isInEnum(ccTypeInput,CCType.class));
			ccType = CCType.valueOf(ccTypeInput);

			do {
				System.out.println("Keep in mind that you can only add cards that have not been added! ");
				if (ccType== (CCType.AMEX)) {
					do {
						System.out.print("Card length AMEX is 15 and begins with a 3.\nPlease enter valid amount digits: ");
						cardNum = input.nextLine();
					} while ((cardNum.length() != 15 ) ||(!cardNum.matches(regex)) || cardNum.charAt(0) != '3');

				} else {
					do {
						System.out.print(
								"Card length for Mastercard/Visa/Discover is 16 and begins with a 4.\nPlease enter valid amount digits: ");
						cardNum = input.nextLine();
					}
					while (cardNum.length() != 16 || (!cardNum.matches(regex))|| cardNum.charAt(0) !='4') ;

				}
			}while ( ifCardExist(cardNum)); //cannot add a card twice

			do {
				System.out.print("Please enter the name of the bank issuing your card: ");
				bankString = input.nextLine().toUpperCase();
			} while (!isInEnum(bankString, BankName.class));
			bank = BankName.valueOf(bankString);

			//don't need to get status as cannot add an expired card and if lost how do u have the info?
			//and if it is cancelled than everything here is irrelevant

			expirationDate = getExpDate();

			issueDate = getIssueDate();

			do {
				System.out.print("Please enter your credit card limit: ");
				ccLimit = input.nextInt();
			} while (ccLimit < 0);

			do {
				System.out.print("Please enter your current balance (for brand new cards enter 0) :");
				ccBalance = input.nextInt();
			} while (ccBalance < 0 || ccBalance > ccLimit);
			
			input.nextLine(); //clear buffer
			
			// finally creating new CC
			CreditCard newCard = new
					CreditCard(issueDate,expirationDate,bank,ccType,ccLimit,ccBalance,cardNum);
			
			ccs.addCard(newCard);
			
			System.out.println("\nCard has been added!\n");
		
			try {
				Music.Sound();
			} catch (Exception e) {
				System.out.println("Error" +e);
			}
			break;
		case 2:
			// Remove a CreditCard
			if (ccs.isEmpty()) {
				System.out.println("There are currently no cards to remove. ");
				return;
			}
			String num;
			do {
				System.out.println("Enter a valid credit card number of the card you would you like to remove:");
				num= input.nextLine();
			}while (! ifCardExist (num));
			
			ccs.removeCard(num);
			
			System.out.println("Card has been removed successfully. ");
			break;
			
		case 3:
			// Display total outstanding balances
			System.out.printf("Your total outstanding balances add up to $%,.2f.\n", ccs.totalBalance());
			break;
			
		case 4:
			// Display total available credit
			double totalAvailCredit = ccs.totalAvailCredit();
			System.out.printf("Your total available credit is $%,.2f.\n", totalAvailCredit);
			break;
			
		case 5:
			// Display largest purchase	
			if(ccs.isEmpty()) {
				System.out.println("No purchases have been added. ");
				return;
			}
			
			Purchase largestPurchase = ccs.getLargestPuchaseAllCards(); 
			
			if (largestPurchase ==null) {
				System.out.println("No purchases have been added. ");
				return;
			}
			System.out.println("Your largest purchase is " + largestPurchase + ".");
			break;
			
		case 6:
			// Display most recent payment
			if(ccs.isEmpty()) {
				System.out.println("No payments have been added. ");
				return;
			}
			Payment mostRecentPayment = ccs.getMostRecentPaymentForAll();
			if (mostRecentPayment ==null) {
				System.out.println("No payments have been added. ");
				return;
			}
			System.out.println("Your most recent payment is " + mostRecentPayment + ".");
			break;
			
		case 7:
			// Display total spent on certain category of expense
			if (ccs.isEmpty()) {
				return;
			}
			double total = 0;
			int option;
			
			do {
				System.out.println("OPTIONS:\n1.Payment\n2.Fee");
				System.out.print("Please enter your choice: ");
				option= input.nextInt();
			}while (option <1 || option >2);
			
			input.nextLine(); //clear buffer
			
			switch (option) {
			case 1:
				total= ccs.getTotalPayment();
				break;
				
			case 2:
				total= ccs.getTotalFees();
				break;
				
			}
			System.out.printf("The total expense for the category selected is $%,.2f.\n" ,total);
			break;
			
		case 8:
			if(ccs.isEmpty()) {
				System.out.println("No purchases have been added. ");
				return;
			}
			// For which type of Purchase was the most money spent
			Purchase largest= ccs.getLargestPuchaseAllCards(); 
			
			if (largest==null) {
				System.out.println("No purchases have been added. ");
				return;
			}
			
			System.out.println("The largest purchase for all credit cards was: "+  largest);
			break;
		}

	}

	public void menu2() {
		
		if(ccs.isEmpty()) {
			System.out.println("No cards have been added to edit. Please add a card. ");
			return;
		}
		
		System.out.println("You selected to Manage a specific Credit Card \n"			
				+ "1. Display current balance \n"
				+ "2. Display current credit limit \n"
				+ "3. Add a Purchase \n"
				+ "4. Add a Payment \n"
				+ "5. Add a Fee \n"
				+ "6. Display most recent Purchase \n"
				+ "7. Display most recent Payment \n"
				+ "8. Exit \n Answer:");
		int choice = input.nextInt();

		while (choice <1 || choice > 8) {
			System.out.println("Invalid. Please choose a number between 1 and 8: ");
			choice = input.nextInt();
		}
		
		input.nextLine(); //clear buffer
		
		if (choice ==8) {
			return;
		}
		String cardNum;
		
		do {
			System.out.print("Please enter your CC Number: ");
			cardNum = input.nextLine();
		}while(!ifCardExist(cardNum));

		switch (choice) {
		case 1:
			//Display current balance
			ccs.displayCurrBalanceCC(cardNum);
			break;
			
		case 2:
			//Display current credit limit
			ccs.displayCurrLimit(cardNum);
			break;
			
		case 3:
			//add a Purchase
			System.out.print("Please enter the type of purchase. \nCAR, CLOTHING, FOOD, GROCERIES, LODGING, RESTAURANT, TRAVEL,UTILITIES,SUBSCRIPTION: ");
			String pType= input.nextLine().toUpperCase();

			while(! isInEnum(pType, PurchaseType.class)) {
				System.out.print("Error! Invalid purchase type. \nPlease reenter: ");
				pType= input.nextLine().toUpperCase();
			}
			PurchaseType type= PurchaseType.valueOf(pType);
			Address address;

			String vendorName;
			System.out.print("Please enter the name of vendor: ");
			vendorName= input.nextLine(); 

			System.out.printf("Please enter the street address of %s : ", vendorName);
			String streetName=input.nextLine();

			int answer;
			do {
				System.out.print("Address:\n1: defualt address \n2: Enter values for address\nAnswer:");
				answer=input.nextInt();
			}while(answer<1 || answer >2);
			
			input.nextLine(); //clear buffer
			
			if (answer ==1) {
				address= new Address(streetName);
			}else {
				String city, zip;
				USState state;
				
				input.nextLine(); //clear buffer
				System.out.print("Please enter your city: ");
				city= input.nextLine();

				String stateString;

				System.out.print("Please enter the state in abbreviation: " );
				stateString= input.nextLine().toUpperCase();
				while (! isInEnum(stateString, USState.class)) {
					System.out.println("Error.\nPlease reenter: ");
					stateString= input.nextLine().toUpperCase();	
				}
				state= USState.valueOf(stateString);

				do {
					System.out.print("Please enter your zip:");
					zip= input.nextLine();
				}while (! isValidZip(zip));

				address= new Address(streetName, city,state,zip);
			}

			Vendor vendor = new Vendor (vendorName, address);
			double amntPurchase;
			do {
				System.out.print("Please enter your purchase amount: (cannot be larger than available limit.) ");
				amntPurchase= input.nextDouble();
			}while(amntPurchase <0 ||amntPurchase > ccs.getAvailCreditOnCard(cardNum));
			
			input.nextLine(); //clear buffer

			Purchase newPurchase = new Purchase (type, getDate(), amntPurchase, vendor);
			ccs.addPurchaseOnCard(newPurchase,cardNum);
			
			try {
				Music.Sound();
			} catch (Exception e) {
				System.out.println("Error" +e);
			}
			break;
			
		case 4:
			//add a payment
			PaymentType payType;
			double amnt;
			if (! ccs.getHasPurchaseOnCard(cardNum)) {
				System.out.println("No purchases have been added to pay. ");
				return;
			}

			System.out.print("Please enter the payment type: ");
			String payTypeString= input.nextLine().toUpperCase();
			while ( ! isInEnum (payTypeString, PaymentType.class)) {
				System.out.print("Invalid payment type.\nPlease reenter: ");
				payTypeString= input.nextLine().toUpperCase();
			}
			payType= PaymentType.valueOf(payTypeString);

			do {
				System.out.print("Please enter your payment amount: ");
				amnt= input.nextInt();
			}while(amnt <0 || amnt > ccs.getBalanceOnCard(cardNum));
			
			input.nextLine(); //clear buffer
			
			Payment newPay= new Payment(payType,getDate(),amnt);
			ccs.addPaymentOnCard(cardNum, newPay);
			
			try {
				Music.Sound();
			} catch (Exception e) {
				System.out.println("Error" +e);
			}
			break;

		case 5:
			//add a Fee
			FeeType feeType;
			double amntFee;

			System.out.print("Please enter the fee type: ");
			String feeTypeString= input.nextLine().toUpperCase();
			while (!isInEnum (feeTypeString, FeeType.class)) {
				System.out.print("Invalid fee type.\nPlease reenter: ");
				feeTypeString= input.nextLine().toUpperCase();
			}
			feeType= FeeType.valueOf(feeTypeString);
			do {
				System.out.print("Please enter your fee amount: ");
				amntFee= input.nextInt();
			}while(amntFee <0);
			
			input.nextLine(); //clear buffer
			
			Fee newFee= new Fee(getDate(),feeType,amntFee);
			
			ccs.addFeeOnCard(cardNum,newFee);
			
			try {
				Music.Sound();
			} catch (Exception e) {
				System.out.println("Error" +e);
			}
			break;

		case 6:
			//Display most recent Purchase
			ccs.displayMostRecentPurchase(cardNum);
			break;
			
		case 7:
			//Display most recent Payment
			ccs.displayMostRecentPayment(cardNum);
			break;
			
		}
	}

	private boolean isValidZip(String zip) {
		if (zip.length()!=5 || ! zip.matches(regex)) {
			return false;
		}
		return true;
	}

	public boolean ifCardExist(String num) {
		return ccs.doesCardExist(num);
	}

	//method to check if Enum is in the class based on the class you pass in 
	public boolean isInEnum(String value, Class enumClass) {
		value=value.toUpperCase();
		for (Object e : enumClass.getEnumConstants()) {
			if (((Enum) e).name().equals(value)) {
				return true;
			}
		}
		return false;
	}


	private Date getDate() {
		Date date=null;
		int day, month, year;
		do {
			System.out.print("Please enter the month of transaction: ");
			month = input.nextInt();
		} while (month < 1 || month > 12);

		do {
			System.out.print("Please enter the year of transaction: ");
			year = input.nextInt();
		} while (year > LocalDate.now().getYear() || year <2000);


		do {
			System.out.print("Please enter the day of transaction: ");
			day = input.nextInt();
		} while (day >31 || day< 0); //max cld have 31 and min cld have 1 if error then will throw exception 
		input.nextLine(); //clear buffer

		//making sure all the data entered reconciles together 
		
		try {
			LocalDate.of(year, month, day);
			date = new Date(month,day, year);
		} 
		catch (Exception e) {
			System.out.println("Error adding the date. "+ e+ "\n");
			getDate();
		}

		//at this point know all values are right..... 	

		return date;
	}
	private Date getIssueDate() {
		int month, year;
		Date date=null;
		// getting issue date
		do {
			System.out.print("Please enter the month of issue: ");
			month = input.nextInt();
		} while (month < 1 || month > 12);

		do {
			System.out.print("Please enter the year of issue: ");
			year = input.nextInt();
		} while (year > LocalDate.now().getYear() || year<2000 );
		input.nextLine(); //clear buffer

		try {
			LocalDate.of(year, month, 01);
			date= new Date (month, year);
		}catch (Exception e){
			System.out.println("Invalid date. "+ e);
			getIssueDate();
		}
		return date;
	}

	private Date getExpDate() {
		Date date= null;
		try {
			int month;
			do {
				System.out.print("Please enter the month of expiration: ");
				month = input.nextInt();
			} while (month < 1 || month > 12);

			int year;
			do {
				System.out.print("Please enter the year of expiration: ");
				year = input.nextInt();
			} while (year < LocalDate.now().getYear() || year <2000); // can't be an expired card
			date= new Date(month,year);
			
			input.nextLine(); //clear buffer
		}
		catch (Exception e) {
			System.out.println("Invalid date. "+ e);
			getExpDate();
		}
		return date;

	}
	
}
