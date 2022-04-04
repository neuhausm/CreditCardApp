

import java.util.ArrayList;

public class CreditCard  {
	private int creditCardID=101;
	private Date issueDate;
	private Date expirationDate;
	private BankName issueCompany;
	private CCType creditCardType;
	private double creditCardLimit;
	private double currentBalance;
	private String cardNum;
	private ArrayList<Transaction> transactions;
	protected boolean hasPurchase;
	private double totalPay;
	private double totalFee;
	protected boolean hasPayment;
	protected Payment mostRecentPayment;

	public CreditCard(Date issueDate, Date expirationDate, BankName issueCompany,
			CCType type, double creditCardLimit, double currentBalance,String cardNum) {
		this.creditCardID ++;
		this.issueDate = issueDate;
		this.expirationDate = expirationDate;
		this.issueCompany = issueCompany;
		this.creditCardType = type;
		if(creditCardLimit <0) {
			throw new LowerThanZeroException();
		}
		this.creditCardLimit = creditCardLimit;
		this.currentBalance = currentBalance; 
		if (creditCardLimit- currentBalance <0) { //cannot overspend :) 
			throw new LowerThanZeroException();
		}

		this.transactions = new ArrayList <Transaction>();
		this.cardNum=cardNum;
		this.hasPurchase=false; //add new card cant have purchase
		this.hasPayment=false;
	}

	public void addPurchase(Purchase purchase) {
		if (purchase.getAmount()>0 && (purchase.getAmount() <=  getAvailCredit())) { //cannot over spend the amount available
			
			transactions.add(purchase);
			currentBalance += purchase.getAmount(); //owe more because of purchase
		}
		System.out.println(purchase);
		hasPurchase =true;		
	}

	public void addPayment(Payment payment) {
		if (payment.getAmount()>0) {
			transactions.add(payment);
			currentBalance -= payment.getAmount(); //owe less 
		}
		totalPay += payment.getAmount();
		hasPayment=true;
		mostRecentPayment= payment;
	}

	public void addFee(Fee fee) {
		if (fee.getAmount() >0) {
			transactions.add(fee);
			currentBalance += fee.getAmount(); //owe more
		}
		totalFee += fee.getAmount();
	}


	public double getCurrentBalance() {
		return currentBalance;
	}


	public double getAvailCredit() {
		return creditCardLimit- currentBalance;
	}

	public Purchase getLargestPuchase() {
		if(!hasPurchase) {
			return null;
		}
		int largestPurchaseIndex=0;

		for (int i=0; i< transactions.size(); i++) {
			if (transactions.get(i) instanceof Purchase) {
				if(transactions.get(i).getAmount() > transactions.get(largestPurchaseIndex).getAmount()) {
					largestPurchaseIndex=i;
				}
			}
		}
		
		return (Purchase)  (transactions.get(largestPurchaseIndex));
			
	}

	public Purchase getMostRecentPurchase(){
		if(hasPurchase) {
			int lastIndexPurchase=0;
			for (int i=transactions.size(); i>0; i--){ //only need the last one 
				if (transactions.get(i) instanceof Purchase) {
					lastIndexPurchase=i;
					return (Purchase) transactions.get(lastIndexPurchase);
				}
			}
		}
		return null;
	}

	public int getCreditCardID() {
		return creditCardID;
	}
	public String getCardNum() {
		return cardNum;
	}

	public String getLastDigitsOfCC() {
		return	cardNum.substring(cardNum.length() - 4);
	}

	public double getCreditCardLimit () {
		return creditCardLimit;
	}

	public boolean hasPurchase() {
		return hasPurchase;
	}
	public double getTotalFee() {
		return totalFee;
	}

	public double getTotalPay() {
		return totalPay;
	}
	public boolean hasPayment() {
		return hasPayment;
	}
	//payment is immutable
	public Payment getMostRecentPayment() {
		return mostRecentPayment;
	}
}
