package hr.fer.zemris.java.gui.charts;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;

import javax.swing.JComponent;

/**
 * Component that draws bar chart.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class BarChartComponent extends JComponent {
	
	/**
	 * Bar chart informations
	 */
	private BarChart barChart;
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Color of axis
	 */
	private static final Color AXIS_COLOR = Color.GRAY;
	/**
	 * Color of grid
	 */
	private static final Color GRID_COLOR = Color.LIGHT_GRAY;
	/**
	 * Color of chart bars
	 */
	private static final Color BARS_COLOR = new Color(245, 130, 65);
	/**
	 * Color of shadow
	 */
	private static final Color SHADOWN_COLOR = Color.LIGHT_GRAY;
	/**
	 * Color of lines that separate bars
	 */
	private static final Color WHITELINE_COLOR = new Color(230, 230, 230);
	/**
	 * Color of values assigned to x and y axis
	 */
	private static final Color VALUES_COLOR = Color.BLACK;
	/**
	 * Color of axis description
	 */
	private static final Color AXISNAMES_COLOR = Color.BLACK;
	/**
	 * Gap that separates axis from edge of window 
	 * where there's axis description that uses extra space.
	 * LB : gap from Left and Bottom edge of the window
	 */
	private static final int GAP_EDGE_LB = 130;
	/**
	 * Gap that separates axis from edge of window
	 * where there's no axis description
	 * TR: gap from Top and Right edge of the window
	 */
	private static final int GAP_EDGE_TR = 50;
	/**
	 * Thickness of lines
	 */
	private static final int LINE_THICKNESS = 2;
	/**
	 * Number that shows how close is shadow to bars 
	 */
	private static final int SHADOW_DIFF = 5;

	
	/**
	 * Constructor that creates new {@link BarChartComponent} object.
	 * 
	 * @param barChart bar chart that is drawn
	 */
	public BarChartComponent(BarChart barChart) {
		this.barChart = barChart;
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		
		// Draw background
		g2D.setColor(getBackground());
		g2D.fillRect(0, 0, getWidth(), getHeight());
		
		// Draw axis and grid
		drawChartBars(g2D);
		
	}
	
	/**
	 * Method draws component content.
	 * 
	 * @param g2D graphics object used to draw component
	 */
	private void drawChartBars(Graphics2D g2D) {
		// Draw axis
		g2D.setColor(AXIS_COLOR);
		int axisModifier = 5;
		// Y axis
		int yAxisLength = getHeight() - GAP_EDGE_LB - GAP_EDGE_TR + 10;
		g2D.fillRect(GAP_EDGE_LB, GAP_EDGE_TR - axisModifier, LINE_THICKNESS, yAxisLength + axisModifier);
		// X axis
		int xAxisLength = getWidth() - GAP_EDGE_LB - GAP_EDGE_TR;
		g2D.fillRect(GAP_EDGE_LB - 8, getHeight() - GAP_EDGE_LB, xAxisLength + axisModifier, LINE_THICKNESS);
		// Y axis arrow
		g2D.fillPolygon(
				new int[] {GAP_EDGE_LB - 3, GAP_EDGE_LB + 5, GAP_EDGE_LB},
				new int[] {GAP_EDGE_TR - 5, GAP_EDGE_TR - 5, GAP_EDGE_TR - 15},
				3);
		// X axis arrow
		g2D.fillPolygon(
				new int[] {getWidth() - GAP_EDGE_TR - 5, getWidth() - GAP_EDGE_TR - 5, getWidth() - GAP_EDGE_TR + 5},
				new int[] {getHeight() - GAP_EDGE_LB + 4, getHeight() - GAP_EDGE_LB - 4, getHeight() - GAP_EDGE_LB},
				3);
		
		List<XYValue> values = barChart.getValues();
		// Draw horizontal lines and y axis values
		g2D.setColor(GRID_COLOR);
		int startValue = barChart.getyMax();
		int spaceBetweenValues = barChart.getSpace();
		int numOfHorizontalLines = (startValue - barChart.getyMin()) / spaceBetweenValues; 
		int gapBetweenHorizontalLines = yAxisLength / numOfHorizontalLines;
		int startLineAtY = GAP_EDGE_TR;
		final int yValueModifier = 15;
		final int fontSize = g2D.getFont().getSize();
		FontMetrics fontMetrics = g2D.getFontMetrics();
		for (int i = 0; i < numOfHorizontalLines; i++) {
			// Draw horizontal lines
			g2D.setColor(GRID_COLOR);
			g2D.fillRect(GAP_EDGE_LB - 8, startLineAtY, xAxisLength, LINE_THICKNESS);
			// Draw numeric value for y axis
			g2D.setColor(VALUES_COLOR);
			String value = String.valueOf(startValue);
			int valueWidth = fontMetrics.stringWidth(value);
			g2D.drawString(value, GAP_EDGE_LB - valueWidth - yValueModifier, startLineAtY + fontSize / 2);
			startValue -= spaceBetweenValues;
			startLineAtY += gapBetweenHorizontalLines;
		}
		String value = String.valueOf(startValue);
		int valueWidth = fontMetrics.stringWidth(value);
		g2D.drawString(value, GAP_EDGE_LB - valueWidth - yValueModifier, startLineAtY);
		
		
		// Draw vertical lines and x axis values
		int numOfVerticalLines = barChart.getValues().size();
		int gapBetweenVerticalLines = xAxisLength / numOfVerticalLines - LINE_THICKNESS;
		int startLineAtX = GAP_EDGE_LB + gapBetweenVerticalLines;
		int xNumberValue = GAP_EDGE_LB + gapBetweenVerticalLines / 2;
		for (int i = 0; i < numOfVerticalLines; i++) {
			// Draw vertical line
			g2D.setColor(GRID_COLOR);
			g2D.fillRect(startLineAtX, GAP_EDGE_TR - LINE_THICKNESS, LINE_THICKNESS, yAxisLength);
			startLineAtX += gapBetweenVerticalLines;
			// Draw numeric value for x axis
			g2D.setColor(VALUES_COLOR);
			value = String.valueOf(values.get(i).getX());
			g2D.drawString(value, xNumberValue, yAxisLength + GAP_EDGE_TR + 20);
			xNumberValue += gapBetweenVerticalLines;
		}
		
		// Draw chart bars with shadows and white lines between them
		startLineAtX = GAP_EDGE_LB + gapBetweenVerticalLines;
		int numOfBars = numOfVerticalLines;
		for  (int i = 0; i < numOfBars; i++) {
			int barValue = (values.get(i).getY() - barChart.getyMin()) / barChart.getSpace();
			int barHeight = barValue * gapBetweenHorizontalLines - 3 * LINE_THICKNESS;
			// Draw shadow
			g2D.setColor(SHADOWN_COLOR);
			g2D.fillRect(GAP_EDGE_LB + gapBetweenVerticalLines * i + LINE_THICKNESS + 5, 
					yAxisLength + GAP_EDGE_TR - barHeight - SHADOW_DIFF,
					gapBetweenVerticalLines, barHeight - SHADOW_DIFF);
			// Draw bar
			g2D.setColor(BARS_COLOR);
			g2D.fillRect(GAP_EDGE_LB + gapBetweenVerticalLines * i + LINE_THICKNESS, 
					yAxisLength + GAP_EDGE_TR - barHeight - 10,
					gapBetweenVerticalLines, barHeight);
			// Draw white line
			g2D.setColor(WHITELINE_COLOR);
			g2D.fillRect(startLineAtX , yAxisLength + GAP_EDGE_TR - barHeight - 10, LINE_THICKNESS, barHeight);
			startLineAtX += gapBetweenVerticalLines;
		}
		
		// Draw coordinate's names
		g2D.setColor(AXISNAMES_COLOR);
		// Draw x coordinate name
		String xName = barChart.getxDescription();
		int xNameLength = g2D.getFontMetrics().stringWidth(xName);
		g2D.drawString(xName, GAP_EDGE_LB + xAxisLength / 2 - xNameLength / 2, GAP_EDGE_TR + yAxisLength + 50);
		// Draw y coordinate name
		AffineTransform at = AffineTransform.getQuadrantRotateInstance(3);
		g2D.setTransform(at);
		String yName = barChart.getyDescription();
		int yNameLength = g2D.getFontMetrics().stringWidth(yName);
		g2D.drawString(yName, -yAxisLength/2 - GAP_EDGE_TR - yNameLength / 2, GAP_EDGE_LB / 2);
	}
	
}
