package hr.fer.zemris.java.gui.calc;

import java.util.function.DoubleBinaryOperator;

/**
 * Class represents general form of calculator model.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public interface CalcModel {

	/**
	 * Method adds listener.
	 * 
	 * @param l listener that is added
	 */
	void addCalcValueListener(CalcValueListener l);
	/**
	 * Method removes given listener.
	 * 
	 * @param l listener that is removed
	 */
	void removeCalcValueListener(CalcValueListener l);
	/**
	 * Method returns current calculator state in string form.
	 * 
	 * @return String representation of calculator model
	 */
	String toString();
	/**
	 * Method returns current calculator's value.
	 * 
	 * @return current value
	 */
	double getValue();
	/**
	 * Method sets current calculator's value.
	 * 
	 * @param value new value
	 */
	void setValue(double value);
	/**
	 * Method clears last value entered.
	 */
	void clear();
	/**
	 * Method clears all values that calculator stored.
	 */
	void clearAll();
	/**
	 * Method swaps sign of current value.
	 */
	void swapSign();
	/**
	 * Method inserts decimal point.
	 */
	void insertDecimalPoint();
	/**
	 * Method concatenates given digit to the current value.
	 * 
	 * @param digit that is inserted
	 */
	void insertDigit(int digit);
	/**
	 * Method checks if active operand is set.
	 * 
	 * @return {@code true} if active operand is set,
	 * 		   {@code false} otherwise
	 */
	boolean isActiveOperandSet();
	/**
	 * Method returns active operand.
	 * 
	 * @return active operand
	 */
	double getActiveOperand();
	/**
	 * Method sets active operator.
	 * 
	 * @param activeOperand new active operator
	 */
	void setActiveOperand(double activeOperand);
	/**
	 * Method clears active operand.
	 */
	void clearActiveOperand();
	/**
	 * Method returns pending binary operation.
	 * 
	 * @return pending double binary operator
	 */
	DoubleBinaryOperator getPendingBinaryOperation();
	/**
	 * Method sets pending binary operation.
	 * 
	 * @param op new double binary operator
	 */
	void setPendingBinaryOperation(DoubleBinaryOperator op);

}