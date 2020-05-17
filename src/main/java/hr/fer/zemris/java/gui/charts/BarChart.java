package hr.fer.zemris.java.gui.charts;

import java.util.List;
import java.util.Objects;

/**
 * Class represents information about bar chart.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class BarChart {
	
	/**
	 * list of pairs of values
	 */
	private List<XYValue> values;
	/**
	 * Description of x axis
	 */
	private String xDescription;
	/**
	 * Description of y axis
	 */
	private String yDescription;
	/**
	 * Minimum value for y
	 */
	private int yMin;
	/**
	 * Maximum value for y
	 */
	private int yMax;
	/**
	 * Value difference between two closest y values
	 */
	private int space;

	/**
	 * Constructor that creates new {@link BarChart} object.
	 * 
	 * @param values       list of x and y values
	 * @param xDescription description of x axis
	 * @param yDescription description of y axis
	 * @param yMin	 	   minimum value for y
	 * @param yMax		   maximum value for y
	 * @param space		   value difference between two closest y values
	 */
	public BarChart(List<XYValue> values, String xDescription, String yDescription, int yMin, int yMax, int space) {
		this.values = values;
		this.xDescription = Objects.requireNonNull(xDescription, "Given x axis description must not be null");
		this.yDescription = Objects.requireNonNull(yDescription, "Given y axis description must not be null");
		this.yMin = yMin;
		this.yMax = yMax;
		this.space = space;
	}

	/**
	 * Method returns list of x and y values.
	 * 
	 * @return list of x and y values
	 */
	public List<XYValue> getValues() {
		return values;
	}

	/**
	 * Method returns x axis description.
	 * 
	 * @return x axis description
	 */
	public String getxDescription() {
		return xDescription;
	}

	/**
	 * Method returns y axis description.
	 * 
	 * @return y axis description
	 */
	public String getyDescription() {
		return yDescription;
	}

	/**
	 * Method returns minimum value for y.
	 * 
	 * @return minimum value for y
	 */
	public int getyMin() {
		return yMin;
	}

	/**
	 * Method returns maximum value for y.
	 * 
	 * @return maximum value for y
	 */
	public int getyMax() {
		return yMax;
	}

	/**
	 * Method returns value difference between two closes y values.
	 * 
	 * @return difference between two closes y values
	 */
	public int getSpace() {
		return space;
	}

}
