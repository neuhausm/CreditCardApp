
public class CardNotFoundException extends RuntimeException {
	public CardNotFoundException(String cardNum) {
		super("Card # " +cardNum+ " not found! ");
	}
	public CardNotFoundException() {
		super("The Card # you entered was not found! ");
	}
}

