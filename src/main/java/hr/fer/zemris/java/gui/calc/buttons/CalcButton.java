package hr.fer.zemris.java.gui.calc.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Class represents general form of calculator button.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class CalcButton extends JButton {
	
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Name of button that is displayed
	 */
	private String name;
	/**
	 * Button's background color
	 */
	private static final Color BUTTON_COLOR = new Color(115, 130, 226);
	/**
	 * Preferred button's width
	 */
	private static final int width = 50;
	/**
	 * Preferred button's height
	 */
	private static final int height = 75;
	
	/**
	 * Constructor that creates new {@link CalcButton} object.
	 * 
	 * @param name name of button that is displayed
	 * @throws     NullPointerException if given {@code name} is {@code null}
	 */
	public CalcButton(String name) {
		this.name = Objects.requireNonNull(name);
		initButton();
	}
	
	/**
	 * Method initializes button.
	 */
	private void initButton() {
		setText(name);
		setBackground(BUTTON_COLOR);
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Method returns color of calculator buttons.
	 * 
	 * @return button's color
	 */
	public Color getColor() {
		return BUTTON_COLOR;
	}
	
}
