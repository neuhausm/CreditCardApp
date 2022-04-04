public class Transaction {

	private long transactionID;
	private long lastTransactionID;
	private Date transactionDate;
	private TransactionType transactionType;
	private double amount;
	
	public Transaction(Date date,TransactionType type, double a) {
		this.lastTransactionID=transactionID;
		this.transactionID ++;
		this.transactionDate= date;
		this.transactionType= type;
		this.amount=a;
	}
	public Transaction(Transaction t) {
		this.lastTransactionID=t.transactionID;
		this.transactionID= t.transactionID;
		this.transactionDate= t.transactionDate;
		this.transactionType= t.transactionType;
		this.amount=t.amount;
	}
	public long getTransactionID() {
		return transactionID;
	}
	public long getLastTransactionID() {
		return lastTransactionID;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public double getAmount () {
		return amount;
	}
	public Date getDate() {
		return transactionDate;
	}

	@Override
	public String toString() {
		return ("ID #: " + this.transactionID +" Date: " + this.transactionDate +" Type: " +this.transactionType +" Amount: $" + String.format("%.2f",amount));
	}
}
