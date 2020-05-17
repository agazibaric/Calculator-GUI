package hr.fer.zemris.java.gui.calc.buttons;

import java.util.function.DoubleBinaryOperator;

import hr.fer.zemris.java.gui.calc.CalcModel;

public class BinarySwitchableOperationButton extends CalcButton {
	
	/**
	 * Primary binary operator that is associated to the button
	 */
	private DoubleBinaryOperator primaryOperator;
	/**
	 * Secondary binary operator that is associated to the button
	 */
	private DoubleBinaryOperator secondaryOperator;
	/**
	 * Calculator model
	 */
	private CalcModel model;
	/**
	 * Flag that shows which operation will be used
	 */
	private boolean isPrimaryOperator = true;
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that creates new {@link BinarySwitchableOperationButton} object.
	 * 
	 * @param model				calculator model
	 * @param name				name associated to the button
	 * @param primaryOperator   primary double binary operator
	 * @param secondaryOperator secondary double binary operator
	 */
	public BinarySwitchableOperationButton(CalcModel model, String name, DoubleBinaryOperator primaryOperator, DoubleBinaryOperator secondaryOperator) {
		super(name);
		this.model = model;
		this.primaryOperator = primaryOperator;
		this.secondaryOperator = secondaryOperator;
		initButton();
	}
	
	/**
	 * Method initializes button.
	 */
	private void initButton() {
		addActionListener(l -> {
			DoubleBinaryOperator operator = getOperator();
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
	
	/**
	 * Method switches operations
	 */
	public void switchOperations() {
		isPrimaryOperator = !isPrimaryOperator;
	}
	
	/**
	 * Method returns selected binary operation.
	 * 
	 * @return selected unary operation
	 */
	private DoubleBinaryOperator getOperator() {
		if (isPrimaryOperator)
			return primaryOperator;
		return secondaryOperator;
	}
	

}
