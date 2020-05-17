package hr.fer.zemris.java.gui.calc.buttons;

import java.util.function.DoubleBinaryOperator;

import hr.fer.zemris.java.gui.calc.CalcModel;

/**
 * Class represents button that has binary operator associated to it.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class BinaryOperationButton extends CalcButton {
	
	/**
	 * Binary operator that is associated to the button
	 */
	private DoubleBinaryOperator operator;
	/**
	 * Calculator model
	 */
	private CalcModel model;

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that creates new {@link BinaryOperationButton} object.
	 * 
	 * @param model    calculator model
	 * @param name     name that is associated to the button
	 * @param operator binary operator that is associated to the button
	 */
	public BinaryOperationButton(CalcModel model, String name, DoubleBinaryOperator operator) {
		super(name);
		this.model = model;
		this.operator = operator;
		initBinaryOperationButton();
	}
	
	/**
	 * Method initializes button.
	 */
	private void initBinaryOperationButton() {
		addActionListener(l -> {
			if (model.isActiveOperandSet()) {
				Double newActiveOperand =  model.getPendingBinaryOperation()
												.applyAsDouble(model.getActiveOperand(), model.getValue());
				model.setValue(newActiveOperand);
				model.setActiveOperand(newActiveOperand);
				model.setPendingBinaryOperation(operator);
			} else {
				model.setActiveOperand(model.getValue());
				model.setPendingBinaryOperation(operator);
			}
			model.clear();
		});
	}
	
}
