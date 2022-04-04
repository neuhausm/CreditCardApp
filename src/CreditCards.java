import java.util.ArrayList;

public class CreditCards {

	private ArrayList<CreditCard> allCards;
	private double totalBalance;
	private double totalCredit;
	
	public CreditCards() {
		this.allCards = new ArrayList<CreditCard>();
		
	}

	public double totalBalance() {
		//need to reset to 0 every time
		totalBalance=0;
		for (int i=0; i< allCards.size();i++) {
			totalBalance += allCards.get(i).getCurrentBalance();
		}
		return totalBalance;
	}
	public double totalAvailCredit() {
		//need to reset to 0 every time
		totalCredit=0;
		for (int i=0; i< allCards.size();i++) {
			totalCredit+= allCards.get(i).getAvailCredit();
		}
		return totalCredit;
	}

	public void addCard(CreditCard newCard) {
		if( allCards.contains(newCard))
			throw new CardExistException(newCard.getCreditCardID());
		else {
			allCards.add(newCard);
		}
	}
	public void removeCard(String num) {
		for (int i=0; i< allCards.size();i++) {
			if (allCards.get(i).getCardNum().equals(num)) {
				allCards.remove(i);
				return;//found card and end method
			}
		}
		throw new CardNotFoundException(num);
	}

	public boolean doesCardExist(String cardNum) {
		for (int x=0; x< allCards.size(); x++){
			if (allCards.get(x).getCardNum().equals(cardNum)) {
				return true;
			}
		}
		return false;
	}
	//purchase is immutable
	public Purchase getLargestPuchaseAllCards() {
		double largest= 0;
		Purchase largestPurchase= null;
		if (! allCards.isEmpty()) {
			for (int i=0; i< allCards.size(); i++)
			{
				if(allCards.get(i).hasPurchase()) {
					if(allCards.get(i).getLargestPuchase().getAmount() > largest)
					{
						largest= allCards.get(i).getLargestPuchase().getAmount();
						largestPurchase= allCards.get(i).getLargestPuchase();
					}
				}
			}

		}
		if (largestPurchase ==null) {
			return largestPurchase;
		}

		return largestPurchase;


	}

	public Payment getMostRecentPaymentForAll() {
		Payment mostRecent=null;
		for (int i =allCards.size()-1;i>=0; i--) {
			if (allCards.get(i).hasPayment()) {
					mostRecent= allCards.get(i).getMostRecentPayment();
					return mostRecent; //found last one added 
			}
		}
		return mostRecent;
	}

	public boolean isEmpty() {
		return allCards.isEmpty();
	}

	private CreditCard getCard(String cardNum) {
			for (int i=0; i< allCards.size();i ++) {
				if (allCards.get(i).getCardNum().equals(cardNum)) {
					return allCards.get(i);
				}
			}
			return null;
		}
	

	public double getTotalPayment() {
		double total = 0;
		for (int i=0; i< allCards.size();i++) {
			total += allCards.get(i).getTotalPay();
		}
		return total;
	}

	public double getTotalFees() {

		double total = 0;
		for (int i=0; i< allCards.size();i++) {
			total += allCards.get(i).getTotalFee();
		}
		return total;
	}
	public int size() {
		return allCards.size();
	}
	public void displayCurrBalanceCC(String cardNum) {
		CreditCard cc= getCard(cardNum);
		System.out.println("The current balance of card ending in "+ cc.getLastDigitsOfCC() + " is "+ cc.getCurrentBalance());
	}
	public void displayCurrLimit(String cardNum) {
		CreditCard cc= getCard(cardNum);
		System.out.println("The current balance of card ending in "+ cc.getLastDigitsOfCC() + " is "+ cc.getCreditCardLimit());
	}


	public double getAvailCreditOnCard(String cardNum) {
		CreditCard cc= getCard(cardNum);
		return cc.getAvailCredit();
	}


	public void addPurchaseOnCard(Purchase newPurchase, String cardNum) {
		CreditCard cc= getCard(cardNum);
		cc.addPurchase(newPurchase);
	}
	public double getBalanceOnCard(String cardNum) {
		CreditCard cc= getCard(cardNum);
		return cc.getCurrentBalance();
	}


	public boolean getHasPurchaseOnCard(String cardNum) {
		CreditCard cc= getCard(cardNum);
		return cc.hasPurchase;
	}


	public void addPaymentOnCard(String cardNum, Payment newPay) {
		CreditCard cc= getCard(cardNum);
		cc.addPayment(newPay);
		
	}

	public void addFeeOnCard(String cardNum, Fee newFee) {
		CreditCard cc= getCard(cardNum);
		cc.addFee(newFee);
		
	}

	public void displayMostRecentPurchase(String cardNum) {
		CreditCard cc= getCard(cardNum);
		Purchase value= cc.getMostRecentPurchase();
		if (value== null) {
			System.out.println("No purchase have been added. ");
			return;
		}
		System.out.println("The most recent purchase for the card ending in # "+ cc.getLastDigitsOfCC() +" is "+value.toString());
	}


	public void displayMostRecentPayment(String cardNum) {
		CreditCard cc= getCard(cardNum);
		Payment val=cc.getMostRecentPayment();
		if (val==null) {
			System.out.println("No payments have been added. ");
			return;
		}
		System.out.println("The most recent payment for the card ending in # "+ cc.getLastDigitsOfCC()+" is "+ val.toString());
		
	}
}
