package hr.fer.zemris.java.gui.calc;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Main calculator's frame.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class Calculator extends JFrame {
	
	/**
	 * Frame's width
	 */
	private static final int WIDTH = 580;
	/**
	 * Frame's height
	 */
	private static final int HEIGHT = 350;
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Frame's title
	 */
	private static final String TITLE = "Calculator";
	
	/**
	 * Constructor that creates new {@link Calculator} object.
	 */
	public Calculator() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(10, 10);
		setSize(WIDTH, HEIGHT);
		initGUI();
	}
	
	/**
	 * Method initializes {@code Calculator} object.
	 */
	private void initGUI() {
		Container cp = getContentPane();
		cp.add(new CalcPanel());
		setTitle(TITLE);
	}

	/**
	 * Main method. Accepts no arguments.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new Calculator().setVisible(true);
		});
	}
	
}
