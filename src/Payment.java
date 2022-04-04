public class Payment extends Transaction{
	protected PaymentType paymentType;

	public Payment(PaymentType paymentType,Date d,double amnt) {
		super(d,TransactionType.PAYMENT,amnt);
		this.paymentType = paymentType;
	}
	
	@Override
	public String toString() {
		return (super.toString() + "\nPayment type: " +this.paymentType );
	}

}

