package hr.fer.zemris.java.gui.charts;

/**
 * Class represents pair of integer values.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class XYValue {
	
	/**
	 * X component 
	 */
	private int x;
	/**
	 * Y component
	 */
	private int y;

	/**
	 * Constructor that creates new {@link XYValue} object.
	 * 
	 * @param x X component
	 * @param y Y component
	 */
	public XYValue(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Method returns X value.
	 * 
	 * @return X value
	 */
	public int getX() {
		return x;
	}

	/**
	 * Method returns Y value
	 * 
	 * @return Y value
	 */
	public int getY() {
		return y;
	}

}
