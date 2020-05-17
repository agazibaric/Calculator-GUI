package hr.fer.zemris.java.gui.calc.buttons;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import hr.fer.zemris.java.gui.calc.CalcModel;
import hr.fer.zemris.java.gui.calc.CalcValueListener;

/**
 * Class represents unmodifiable label used for displaying result of calculations. </br>
 * It also used as {@link CalcValueListener} that is notified whenever calculator changes value.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class Display extends JLabel implements CalcValueListener {
	
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that creates new {@link Display} object.
	 */
	public Display() {
		initDisplay();
	}
	
	/**
	 * Method initializes display label.
	 */
	private void initDisplay() {
		setText("0");
		setHorizontalAlignment(SwingConstants.RIGHT);
		setBackground(Color.ORANGE);
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}
	
	@Override
	public void valueChanged(CalcModel model) {
		setText(model.toString());
	}
	
}
