package hr.fer.zemris.java.gui.layouts;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class represents constraint used in {@link CalcLayout}.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class RCPosition {
	
	/**
	 * Number of row
	 */
	private int row;
	/**
	 * Number of column
	 */
	private int column;
	
	
	/**
	 * Constructor that creates new {@link RCPosition} object.
	 * 
	 * @param row    number of row
	 * @param column number of column
	 */
	public RCPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Method returns row.
	 * 
	 * @return row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Method returns column.
	 * 
	 * @return column
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Method parses given String {@code s} into {@link RCPosition} object if so is possible.
	 * If not, it throws exception.
	 * 
	 * @param s string that is parsed
	 * @return  {@link RCPosition} that represents given {@code s}.
	 */
	public static RCPosition parse(String s) {
		Objects.requireNonNull(s, "Given string must not be null");
		
		s = s.replaceAll("\\s+", "");
		Pattern p = Pattern.compile("^(\\d+),(\\d+)$");
		Matcher m = p.matcher(s);
		if (m.find()) {
			try {
				Integer row = Integer.parseInt(m.group(1));
				Integer column = Integer.parseInt(m.group(2));
				return new RCPosition(row, column);
			} catch (NumberFormatException ex) {
				throw new IllegalArgumentException("Given string can not be parsed into RCPosition. Was: " + s);
			}
		}
		throw new IllegalArgumentException("Given string can not be parseds into RCPosition. Was: " + s);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RCPosition))
			return false;
		RCPosition other = (RCPosition) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("(%d, %d)", row, column);
	}

}
