package hr.fer.zemris.java.gui.layouts;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Class represents calculator layout manager.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class CalcLayout implements LayoutManager2 {
	
	/** 
	 * Gap between rows and columns 
	 */
	private int gap;
	/**
	 *  Minimum width
	 */
	private int minWidth;
	/**
	 *  Minimum height
	 */
	private int minHeight;
	/** 
	 * Preferred width 
	 */
	private int preferredWidth;
	/** 
	 * Preferred height 
	 */
	private int preferredHeight;
	/**
	 * Maximum width
	 */
	private int maxWidth;
	/** 
	 * Maximum height
	 */
	private int maxHeight;
	/**
	 * Map maps components to the given positions
	 */
	private Map<RCPosition, Component> componentsMap = new HashMap<>();
	/**
	 * Flag that shows if sizes have been set 
	 */
	private boolean sizesUnknown = true;
	/**
	 * Number of rows
	 */
	private static final int ROWS = 5;
	/**
	 * Number of columns
	 */
	private static final int COLUMNS = 7;
	/**
	 * Position array
	 */
	private static final RCPosition[][] positions = new RCPosition[ROWS][COLUMNS];
	/**
	 *  Default gap value 
	 */
	private static final int DEFAULT_GAP = 0;
	
	/**
	 * Constructor that creates new {@link CalcLayout} object.
	 * 
	 * @param gap gap between rows and columns
	 */
	public CalcLayout(int gap) {
		if (gap < 0) 
			throw new CalcLayoutException("Gap must not be negative");
		this.gap = gap;
		initLayout();
	}
	
	/**
	 * Method initializes {@code CalcLayout}.
	 */
	private void initLayout() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				positions[i][j] = new RCPosition(i + 1, j + 1);
			}
		}
	}
	
	/**
	 * Constructor that creates new {@link CalcLayout} object
	 * with gap between rows and columns set to zero.
	 */
	public CalcLayout() {
		this(DEFAULT_GAP);
	}
	
	/**
	 * Method sets preferred, maximum and minimum width and height.
	 */
	private void setSizes() {
		RCPosition firstPosition = positions[0][0];
		preferredWidth = preferredHeight = minWidth = minHeight = 0;
		Set<RCPosition> positions = componentsMap.keySet();
		for (RCPosition position : positions) {
			if (position.equals(firstPosition))
					continue;
			Component component = componentsMap.get(position);
			if (component.isVisible()) {
				Dimension preferredDim = component.getPreferredSize();
				Dimension minDim = component.getMinimumSize();
				Dimension maxDim = component.getMaximumSize();
				preferredWidth = Math.max(preferredDim.width, preferredWidth);
				preferredHeight = Math.max(preferredDim.height, preferredHeight);
				minWidth = Math.max(minDim.width, minWidth);
				minHeight = Math.max(minDim.height, minHeight);
				maxWidth = Math.max(maxDim.width, maxWidth);
				maxHeight = Math.max(maxDim.height, maxHeight);
			}
		}
		preferredWidth = preferredWidth * COLUMNS + gap * (COLUMNS - 1);
		preferredHeight = preferredHeight * ROWS + gap * (ROWS - 1);
		minWidth = minWidth * COLUMNS + gap * (COLUMNS - 1);
		minHeight = minHeight * ROWS + gap * (ROWS - 1);
		maxWidth = maxWidth * COLUMNS + gap * (COLUMNS - 1);
		maxHeight = maxHeight * ROWS + gap * (ROWS - 1);
		sizesUnknown = false;
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		if (sizesUnknown) {
			setSizes();
		}
		
		Insets insets = parent.getInsets();
		int width = preferredWidth + insets.right + insets.left;
		int height = preferredHeight + insets.top + insets.bottom;
		return new Dimension(width, height);
	}
	
	@Override
	public Dimension minimumLayoutSize(Container parent) {
		if (sizesUnknown) {
			setSizes();
		}
		
		Insets insets = parent.getInsets();
		int width = minWidth + insets.right + insets.left;
		int height = minHeight + insets.top + insets.bottom;
		return new Dimension(width, height);
	}
	
	@Override
	public Dimension maximumLayoutSize(Container parent) {
		if (sizesUnknown) {
			setSizes();
		}
		
		Insets insets = parent.getInsets();
		int width = maxWidth + insets.right + insets.left;
		int height = maxHeight + insets.top + insets.bottom;
		return new Dimension(width, height);
	}
	

	@Override
	public void layoutContainer(Container parent) {
		Insets insets = parent.getInsets();
		if (sizesUnknown) {
			setSizes();
		}
		int startX = insets.left;
		int startY = insets.top;
		int componentWidth = (preferredWidth - gap * (ROWS - 1)) / ROWS;
		int componentHeight = (preferredHeight - gap * (COLUMNS - 1)) / COLUMNS;
		for (int row = 1; row <= ROWS; row++) {
			for (int column = 1; column <= COLUMNS; column++) {
				RCPosition position = getPositionAt(row, column);
				Component component = componentsMap.get(position);
				if (component != null && component.isVisible()) {
					if (row == 1 && column == 1) {
						component.setBounds(startX, startY, componentWidth * 5 + gap * 4, componentHeight);
					} else {
						int componentY = startY + (row - 1) * (componentHeight + gap);
						int componentX = startX + (column - 1) * (componentWidth + gap);
						component.setBounds(componentX, componentY, componentWidth, componentHeight);
					}
				}
				
			}
		}
		
	}
	
	/**
	 * Method returns {@link RCPosition} that represents given {@code row} and {@code column}.
	 * 
	 * @param row    position's row
	 * @param column position's column
	 * @return       {@link RCPosition} that represents given {@code row} and {@code column}
	 */
	private RCPosition getPositionAt(int row, int column) {
		return positions[row - 1][column - 1];
	}

	@Override
	public void removeLayoutComponent(Component parent) {
		for (Map.Entry<RCPosition, Component> entry : componentsMap.entrySet()) {
			if (entry.getValue() == parent) {
				componentsMap.remove(entry.getKey());
				return;
			}
		}
	}

	@Override
	public void addLayoutComponent(Component component, Object obj) {
		RCPosition position = checkAndGetPosition(obj);
		componentsMap.put(position, component);
	}
	
	/**
	 * Method checks validity of given position
	 * and returns given position casted to {@link RCPosition} if that is possible.
	 * If not, method throws exception.
	 * 
	 * @param position object that is checked
	 * @return         {@link RCPosition} object
	 * @throws         NullPointerException if given {@code position} is {@code null}
	 * @throws 	       CalcLayoutException if given {@code position} is not of a type {@link RCPosition}
	 * 				   or given position is not valid position or is already used
	 */
	private RCPosition checkAndGetPosition(Object obj) {
		Objects.requireNonNull(obj, "Given position must not be null");
		RCPosition position;
		if (obj instanceof String) {
			position = RCPosition.parse((String) obj);
		} else if (!(obj instanceof RCPosition))
			throw new CalcLayoutException("Given position is not of a type RCPosition.");
		else {
			position = (RCPosition) obj;
		}
		
		if (componentsMap.get(position) != null)
			throw new CalcLayoutException("Invalid position.\n"
					+ "There's already component that is assigned to the position: " + position);
		
		int posRow = position.getRow();
		if (posRow < 1 || posRow > 5)
			throw new CalcLayoutException("Invalid position. Was: " + position);
		
		int posColumn = position.getColumn();
		if (posColumn < 1 || posColumn > 7)
			throw new CalcLayoutException("Invalid position. Was: " + position);
		
		if (posRow == 1 && (posColumn >= 2 && posColumn <= 5 ))
			throw new CalcLayoutException("Invalid position. Was: " + position);
		
		return position;
	}
	
	@Override
	public void addLayoutComponent(String arg0, Component arg1) {
	}

	@Override
	public float getLayoutAlignmentX(Container parent) {
		return 0f;
	}

	@Override
	public float getLayoutAlignmentY(Container parent) {
		return 0f;
	}

	@Override
	public void invalidateLayout(Container parent) {
	}
	
}
