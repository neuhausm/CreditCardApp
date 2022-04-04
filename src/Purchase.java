public class Purchase extends Transaction {
	
	protected PurchaseType purchaseType;
    protected Vendor vendor;
    
    public Purchase(PurchaseType pType,Date d,double amnt,Vendor v ) {
		super(d,TransactionType.PURCHASE,amnt);
		this.vendor=v; 
		purchaseType=pType;
	}

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }
 
    @Override
    public String toString() {
    	return ("Purchase type: "+ this.purchaseType + this.vendor.toString()+ " $"+ String.format("%.2f",this.getAmount()));
    }
    
}
