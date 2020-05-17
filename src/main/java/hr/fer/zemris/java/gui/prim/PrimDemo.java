package hr.fer.zemris.java.gui.prim;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Program allows you to generate prime number on a click of a button
 * and shows generated prime numbers in two separate scrollable lists.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class PrimDemo extends JFrame {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Name of button
	 */
	private static final String NEXT_BTN_NAME = "Sljedeći";
	
	/**
	 * Main method. Accepts no arguments.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		new PrimDemo().setVisible(true);
	}
	
	/**
	 * Constructor that creates new {@link PrimDemo} object.
	 */
	public PrimDemo() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		initGUI();
	}
	
	/**
	 * Method initializes {@code PrimDemo}.
	 */
	private void initGUI() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		PrimListModel primModel = new PrimListModel();
		
		JButton nextBtn = new JButton(NEXT_BTN_NAME);
		nextBtn.addActionListener(l -> {
			primModel.next();
		});
		cp.add(nextBtn, BorderLayout.SOUTH);
		
		JList<Integer> primListLeft = new JList<>(primModel);
		JList<Integer> primListRight = new JList<>(primModel);
		
		JPanel listPanel = new JPanel(new GridLayout(1, 1));
		listPanel.add(new JScrollPane(primListLeft));
		listPanel.add(new JScrollPane(primListRight));
		cp.add(listPanel, BorderLayout.CENTER);
	}
	
}
