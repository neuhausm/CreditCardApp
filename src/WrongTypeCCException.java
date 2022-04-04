
public class WrongTypeCCException extends RuntimeException {
	public WrongTypeCCException () {
		super("Error adding new card. Be sure to have the right amount of digits! "
				+ "15 for AMEX and 16 for VISA, MASTERCARD, and DISCOVER ");
	}
}
