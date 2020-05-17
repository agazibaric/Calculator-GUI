package hr.fer.zemris.java.gui.calc;

/**
 * Class represents general form of listeners
 * that record calculator value changes.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public interface CalcValueListener {
	
	/**
	 * Method that is called on listener whenever value is changed.
	 * 
	 * @param model calculator model
	 */
	void valueChanged(CalcModel model);
	
}