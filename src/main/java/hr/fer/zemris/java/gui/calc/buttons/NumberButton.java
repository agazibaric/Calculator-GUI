package hr.fer.zemris.java.gui.calc.buttons;

import hr.fer.zemris.java.gui.calc.CalcModel;

/**
 * Class represents buttons that have numbers associated to them.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class NumberButton extends CalcButton {

	/**
	 * Number associated to the button
	 */
	private int number;
	/**
	 * Calculator model
	 */
	private CalcModel model;
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that creates new {@link NumberButton} object.
	 * 
	 * @param number number that is associated to the button
	 * @param model  calculator model
	 */
	public NumberButton(int number, CalcModel model) {
		super(String.valueOf(number));
		this.number = number;
		this.model = model;
		initButton();
	}
	
	/**
	 * Method initializes button.
	 */
	private void initButton() {
		addActionListener(l -> {
			
			model.insertDigit(number);
		});
	}
	
}
