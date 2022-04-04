
public class CCNotRightAmntDigitsException extends RuntimeException{
	public CCNotRightAmntDigitsException () {
		super("Error adding new card. Be sure to have the right amount of digits! "
				+ "\nCard number: 15 for AMEX and 16 for VISA, MASTERCARD, and DISCOVER. "
				+ "\nCVV: 3 digits for VISA, MASTER, and DISCOVER; 4 digits for AMEX. ");
	}
}
