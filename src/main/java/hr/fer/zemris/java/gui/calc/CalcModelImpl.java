package hr.fer.zemris.java.gui.calc;

import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class represents implementation of {@link CalcModel}.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class CalcModelImpl implements CalcModel {
	
	/**
	 * Current user input
	 */
	private String userInput;
	/**
	 * Operand waiting on operation
	 */
	private double activeOperand;
	/**
	 * Flag shows if it's active operator set
	 */
	private boolean isActiveOperandSet;
	/**
	 * Operation to be performed when second operand is given
	 */
	private DoubleBinaryOperator pendingOperation;
	/**
	 * Flag shows if it's pending operation set
	 */
	private boolean isPendingOperationSet;
	/**
	 * List of listeners waiting on change of state
	 */
	private List<CalcValueListener> listeners = new LinkedList<>();
	
	private JPanel parentPanel;
	
	/**
	 * Constructor that creates new {@link CalcModelImpl} object.
	 */
	public CalcModelImpl(JPanel parentPanel) {
		this.parentPanel = parentPanel;
	}

	@Override
	public void addCalcValueListener(CalcValueListener l) {
		listeners.add(l);
	}

	@Override
	public void removeCalcValueListener(CalcValueListener l) {
		listeners.remove(l);
	}

	@Override
	public double getValue() {
		return userInput == null ? 0.0 : Double.parseDouble(userInput);
	}

	@Override
	public void setValue(double value) {
		if (!Double.isNaN(value) && !Double.isInfinite(value)) {
			userInput = String.valueOf(value);
			notifyListeners();
		} else {
			JOptionPane.showMessageDialog(parentPanel, "Performed operation is invalid.", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void clear() {
		userInput = null;
	}

	@Override
	public void clearAll() {
		userInput = null;
		isActiveOperandSet = false;
		isPendingOperationSet = false;
		activeOperand = 0.0;
		pendingOperation = null;
		notifyListeners();
	}

	@Override
	public void swapSign() {
		if (userInput == null)
			return;

		if (userInput.charAt(0) == '-') {
			userInput = userInput.substring(1);
		} else if (userInput.length() > 0) {
			userInput = "-".concat(userInput);
		}
		notifyListeners();
	}

	@Override
	public void insertDecimalPoint() {
		if (userInput == null) {
			userInput = "0.";
		} else if(!userInput.contains(".")) {
			userInput = userInput.concat(".");
		}
		notifyListeners();
	}

	@Override
	public void insertDigit(int digit) {
		if (userInput == null) {
			userInput = String.valueOf(digit);
			notifyListeners();
			return;
		}
		
		if (userInput.equals("0")) {
			userInput = String.valueOf(digit);
			notifyListeners();
			return;
		}
		
		String value = userInput.concat(String.valueOf(digit));
		if (isValidValue(value)) {		
			userInput = value;
			notifyListeners();
		}
	}
	
	private boolean isValidValue(String value) {
		return Double.isFinite(Double.parseDouble(value));
	}

	@Override
	public boolean isActiveOperandSet() {
		return isActiveOperandSet;
	}

	@Override
	public double getActiveOperand() {
		if (!isActiveOperandSet)
			throw new IllegalStateException("Active operand is not set.");
		return activeOperand;
	}

	@Override
	public void setActiveOperand(double activeOperand) {
		this.activeOperand = activeOperand;
		isActiveOperandSet = true;
	}

	@Override
	public void clearActiveOperand() {
		isActiveOperandSet = false;
		activeOperand = 0.0;
	}

	@Override
	public DoubleBinaryOperator getPendingBinaryOperation() {
		if (!isPendingOperationSet)
			throw new IllegalStateException("Pending operation is not set.");
		return pendingOperation;
	}

	@Override
	public void setPendingBinaryOperation(DoubleBinaryOperator op) {
		pendingOperation = op;
		isPendingOperationSet = true;
	}
	
	@Override
	public String toString() {
		return userInput == null ? "0" : userInput;
	}
	
	/**
	 * Method notifies calculator listeners when value is changed.
	 */
	private void notifyListeners() {
		listeners.forEach(l -> l.valueChanged(this));
	}

}
