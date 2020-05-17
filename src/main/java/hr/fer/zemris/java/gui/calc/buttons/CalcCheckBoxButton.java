package hr.fer.zemris.java.gui.calc.buttons;

import javax.swing.JCheckBox;

/**
 * Class represents calculator button that is used as check box.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class CalcCheckBoxButton extends CalcButton {

	/**
	 * Check box button
	 */
	private JCheckBox checkBox;
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that creates new {@link CalcCheckBoxButton} object.
	 * 
	 * @param name name associated to the button
	 */
	public CalcCheckBoxButton(String name) {
		super(name);
		initButton();
	}
	
	/**
	 * Method initializes button
	 */
	private void initButton() {
		this.checkBox = new JCheckBox();
		checkBox.setOpaque(false);
		checkBox.setEnabled(true);
		checkBox.addActionListener(l -> {
			this.toggleCheckBox();
		});
		add(checkBox);
	}
	
	/**
	 * Method toggles check box
	 */
	public void toggleCheckBox() {
		checkBox.setSelected(!checkBox.isSelected());
	}

}
