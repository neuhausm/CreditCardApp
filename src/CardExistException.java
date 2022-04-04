
public class CardExistException extends RuntimeException {
	public CardExistException(int cardId) {
		super("Card # "+ cardId +" already exists.");
	}
}
