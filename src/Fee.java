
public class Fee extends Transaction {
	protected FeeType feeType;
	
	public Fee(Date d, FeeType type, double amount) {
		super(d,TransactionType.FEE,amount);
		this.feeType = type;
	}
}
