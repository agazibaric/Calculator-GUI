package hr.fer.zemris.java.gui.calc;

import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hr.fer.zemris.java.gui.calc.buttons.BinaryOperationButton;
import hr.fer.zemris.java.gui.calc.buttons.BinarySwitchableOperationButton;
import hr.fer.zemris.java.gui.calc.buttons.CalcButton;
import hr.fer.zemris.java.gui.calc.buttons.CalcCheckBoxButton;
import hr.fer.zemris.java.gui.calc.buttons.Display;
import hr.fer.zemris.java.gui.calc.buttons.NumberButton;
import hr.fer.zemris.java.gui.calc.buttons.UnaryOperationButton;
import hr.fer.zemris.java.gui.layouts.CalcLayout;
import hr.fer.zemris.java.gui.layouts.RCPosition;

/**
 * Class represents panel for calculator.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class CalcPanel extends JPanel {
	
	/**
	 * Calculator model
	 */
	private CalcModel model;
	/**
	 * Gap between buttons
	 */
	private final static int gap = 5;
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Number of switchable operations
	 */
	private static final int NUM_SWITCHABLE = 7;
	/**
	 * Array of swichable operations buttons
	 */
	private UnaryOperationButton[] switchableUnaryOperations = new UnaryOperationButton[NUM_SWITCHABLE];
	/**
	 * Button that performs power operation and root operation
	 */
	private BinarySwitchableOperationButton powerBtn;
	/**
	 * Stack for push and pop calculator's commands
	 */
	private Stack<Double> stack = new Stack<>();
	
	/**
	 * Constructor that creates new {@link CalcPanel} object.
	 */
	public CalcPanel() {
		initPanel();
	}
	
	/**
	 * Method initializes {@link CalcPanel} object.
	 */
	private void initPanel() {
		setLayout(new CalcLayout(gap));
		addButtons();
	}
	
	/**
	 * Method switches operation for buttons that has swichable operations
	 */
	private void switchOperations() {
		powerBtn.switchOperations();
		for (UnaryOperationButton btn : switchableUnaryOperations) {
			btn.switchOperations();
		}
	}
	
	/**
	 * Method adds all calculator's buttons
	 */
	private void addButtons() {
		model = new CalcModelImpl(this);
		Display display = new Display();
		add(display, new RCPosition(1, 1));
		model.addCalcValueListener(display);
		
		int number = 9;
		for (int i = 2; i <= 4; i++) {
			for (int j = 5; j >= 3; j--) {
				NumberButton numBtn = new NumberButton(number--, model);
				add(numBtn, new RCPosition(i, j));
			}
		}
		NumberButton zero = new NumberButton(0, model);
		add(zero, new RCPosition(5, 3));
		
		int index = 0;
		// sin
		switchableUnaryOperations[index] = new UnaryOperationButton(model, "sin", v -> Math.sin(v), v -> Math.asin(v));
		add(switchableUnaryOperations[index++], new RCPosition(2, 2));
		
		// cos
		switchableUnaryOperations[index] = new UnaryOperationButton(model, "cos", v -> Math.cos(v), v -> Math.acos(v));
		add(switchableUnaryOperations[index++], new RCPosition(3, 2));
		
		// tan
		switchableUnaryOperations[index] = new UnaryOperationButton(model, "tan", v -> Math.tan(v), v -> Math.atan(v));
		add(switchableUnaryOperations[index++], new RCPosition(4, 2));
		
		// ctg
		switchableUnaryOperations[index] = new UnaryOperationButton(model, "ctg", v -> 1.0/Math.tan(v), v -> Math.atan(1.0/v));
		add(switchableUnaryOperations[index++], new RCPosition(5, 2));
		
		// 1/x
		switchableUnaryOperations[index] = new UnaryOperationButton(model, "1/x", v -> 1.0/v, v -> 1.0/v);
		add(switchableUnaryOperations[index++], new RCPosition(2, 1));
		
		// log10
		switchableUnaryOperations[index] = new UnaryOperationButton(model, "log", v -> Math.log10(v), v -> Math.pow(10, v));
		add(switchableUnaryOperations[index++], new RCPosition(3, 1));
		
		// ln
		switchableUnaryOperations[index] = new UnaryOperationButton(model, "ln", v -> Math.log(v), v -> Math.pow(Math.E, v));
		add(switchableUnaryOperations[index++], new RCPosition(4, 1));
	
		// x^n
		powerBtn = new BinarySwitchableOperationButton(model, "x^n", (a, b) -> Math.pow(a, b), (a, b) -> Math.pow(a, 1.0/b));
		add(powerBtn, new RCPosition(5, 1));
		
		// a + b
		CalcButton plusBtn = new BinaryOperationButton(model, "+", (a, b) -> a + b);
		add(plusBtn, new RCPosition(5, 6));
		
		// a - b
		CalcButton minusBtn = new BinaryOperationButton(model, "-", (a, b) -> a - b);
		add(minusBtn, new RCPosition(4, 6));
		
		// a * b
		CalcButton multiplyBtn = new BinaryOperationButton(model, "*", (a, b) -> a * b);
		add(multiplyBtn, new RCPosition(3, 6));
		
		// a / b
		BinaryOperationButton divideBtn = new BinaryOperationButton(model, "/", (a, b) -> a / b);
		add(divideBtn, new RCPosition(2, 6));
		
		// +/-
		CalcButton signSwapBtn = new CalcButton("+/-");
		signSwapBtn.addActionListener(l -> {
			model.swapSign();
		});
		add(signSwapBtn, new RCPosition(5, 4));
		
		// insert decimal point '.'
		CalcButton dotButton = new CalcButton(".");
		dotButton.addActionListener(l -> {
			model.insertDecimalPoint();
		});
		add(dotButton, new RCPosition(5, 5));
		
		// equals '='
		CalcButton equalsButton = new CalcButton("=");
		equalsButton.addActionListener(l -> {
			if (model.isActiveOperandSet()) {
				Double newValue = model.getPendingBinaryOperation()
									   .applyAsDouble(model.getActiveOperand(), model.getValue());
				model.clearAll();
				model.setValue(newValue);
			}
		});
		add(equalsButton, new RCPosition(1, 6));
		
		// clear
		CalcButton clrBtn = new CalcButton("clr");
		clrBtn.addActionListener(l -> {
			model.clear();
		});
		add(clrBtn, new RCPosition(1, 7));
		
		// clear all
		CalcButton resBtn = new CalcButton("res");
		resBtn.addActionListener(l -> {
			model.clearAll();
		});
		add(resBtn, new RCPosition(2, 7));
		
		// inverse functions
		CalcCheckBoxButton invBtn = new CalcCheckBoxButton("inv");
		invBtn.addActionListener(l -> {
			invBtn.toggleCheckBox();
			switchOperations();
		});
		add(invBtn, new RCPosition(5, 7));
		
		// push
		CalcButton pushBtn = new CalcButton("push");
		pushBtn.addActionListener(l -> {
			stack.push(model.getValue());
		});
		add(pushBtn, new RCPosition(3, 7));
		
		// pop
		CalcButton popBtn = new CalcButton("pop");
		popBtn.addActionListener(l -> {
			if (!stack.isEmpty()) {
				model.setValue(stack.pop());
			} else {
				JOptionPane.showMessageDialog(this, "Stack is empty.", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		});
		add(popBtn, new RCPosition(4, 7));
		
	}

}
