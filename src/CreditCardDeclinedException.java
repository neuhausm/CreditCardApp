
public class CreditCardDeclinedException extends RuntimeException{
	public CreditCardDeclinedException()
	{
		super("Card is declined. You don't have enough credit to make this purchase.");
	}
}
