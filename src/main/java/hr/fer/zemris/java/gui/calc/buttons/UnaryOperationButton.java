package hr.fer.zemris.java.gui.calc.buttons;

import java.util.Objects;
import java.util.function.DoubleUnaryOperator;

import hr.fer.zemris.java.gui.calc.CalcModel;

/**
 * Class represents button that has assigned two unary operation to it.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class UnaryOperationButton extends CalcButton {

	/**
	 * First unary operation
	 */
	private DoubleUnaryOperator first;
	/**
	 * Second unary operation
	 */
	private DoubleUnaryOperator second;
	/**
	 * Calculator model
	 */
	private CalcModel model;
	/**
	 * Flag that shows which operation will be used
	 */
	private boolean isFirstOperator = true;
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that creates new {@link UnaryOperationButton} object.
	 * 
	 * @param model  calculator model
	 * @param name   name associated to the button
	 * @param first  first unary operation
	 * @param second second unary operation
	 */
	public UnaryOperationButton(CalcModel model, String name, DoubleUnaryOperator first, DoubleUnaryOperator second) {
		super(name);
		this.model = model;
		this.first = Objects.requireNonNull(first, "Given unary operator must not be null");
		this.second = Objects.requireNonNull(second, "Given unary operator must not be null");
		initUnaryOperationButton();
	}
	
	/**
	 * Method initializes button.
	 */
	private void initUnaryOperationButton() {
		addActionListener(l -> {
			model.setValue(this.performOperation(model.getValue()));
		});
	}
	
	/**
	 * Method performs selected unary operation that is associated to the button.
	 * 
	 * @param value value on which operation is performed
	 * @return      result of unary operation
	 */
	public double performOperation(Double value) {
		return getOperator().applyAsDouble(value);
	}
	
	/**
	 * Method switches operations
	 */
	public void switchOperations() {
		isFirstOperator = !isFirstOperator;
	}
	
	/**
	 * Method returns selected unary operation.
	 * 
	 * @return selected unary operation
	 */
	private DoubleUnaryOperator getOperator() {
		if (isFirstOperator)
			return first;
		return second;
	}

}
