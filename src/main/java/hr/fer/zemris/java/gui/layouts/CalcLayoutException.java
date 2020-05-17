package hr.fer.zemris.java.gui.layouts;

/**
 * Class represents exception used by {@link CalcLayout} when invalid situation happens.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class CalcLayoutException extends RuntimeException {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that can accept message about invalid situation that happened.
	 * 
	 * @param message message about invalid situation that happened
	 */
	public CalcLayoutException(String message) {
		super(message);
	}

}
