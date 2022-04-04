
public class OutOfBalanceException extends RuntimeException{
	public OutOfBalanceException() {
		super("Can't pay more than on account! Please recheck your amount! ");
	}

}
