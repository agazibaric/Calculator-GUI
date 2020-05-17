package hr.fer.zemris.java.gui.charts;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Program that shows bar chart diagram 
 * that contains information given in file through arguments.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class BarChartDemo extends JFrame {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default frame's width
	 */
	private static final int DEFAULT_WIDTH = 800;
	/**
	 * Default frame's height
	 */
	private static final int DEFAULT_HEIGHT = 600;
	/**
	 * Default file path 
	 */
	private static final Path DEFAULT_PATH = Paths.get("./src/main/resources/BarChartInfo.txt");
	
	/**
	 * Main method.
	 * Accepts one argument that represents path of the file that contains bar chart informations.
	 * If no arguments are given program uses default bar chart file.
	 * If there's more then one arguments the program stops.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		int argsLength = args.length;
		Path path;
		BarChart barChart;
		if (argsLength == 1) {
			path = Paths.get(args[0]);
			try {
				barChart = getBarChartFromPath(path);
			} catch (IllegalArgumentException ex) {
				System.err.println(ex.getMessage());
				return;
			}
		} else if (argsLength == 0){
			System.out.println("Default barChart will be used.");
			path = DEFAULT_PATH;
			barChart = getBarChartFromPath(path);
		} else {
			System.err.println("Invalid number of arguments. Was: " + argsLength);
			return;
		}
		new BarChartDemo(barChart, path).setVisible(true);
	}
	
	/**
	 * Method returns {@link BarChart} object that contains information given in file
	 * which is represented by given {@code path}.
	 * 
	 * @param path path of the file that contains bar chart informations
	 * @return     {@link BarChart} object with information given in file
	 * @throws     IllegalArgumentException if it fails to open the file,
	 * 			   or if given bar chart informations in file are to valid
	 */
	private static BarChart getBarChartFromPath(Path path) {
		try (BufferedReader br = Files.newBufferedReader(path)) {
			String xDescription = br.readLine();
			String yDescription = br.readLine();
			List<XYValue> values = getXYValues(br.readLine());
			Integer yMin = Integer.parseInt(br.readLine());
			Integer yMax = Integer.parseInt(br.readLine());
			Integer space = Integer.parseInt(br.readLine());
			return new BarChart(values, xDescription, yDescription, yMin, yMax, space);
		} catch (IOException ex) {
			throw new IllegalArgumentException("Unable to open the given file.\nWas: " + path);
		} catch (NullPointerException ex) {
			throw new IllegalArgumentException("Missing information for bar chart in given file.");
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Given values must be integer values.");
		}
	}
	
	/**
	 * Method parser given {@code line} in list of {@link XYValue} objects.
	 * 
	 * @param line line that is parsed
	 * @return     list of {@link XYValue} objects
	 * @throws     IllegalArgumentException if given informations are not valid
	 */
	private static List<XYValue> getXYValues(String line) {
		Objects.requireNonNull(line, "Given values must not be null");
		String[] pairs = line.split("\\s+");
		List<XYValue> values = new LinkedList<>();
		try {
		for (String pair : pairs) {
			String[] pairParts = pair.split(",");
			values.add(new XYValue(Integer.parseInt(pairParts[0]), Integer.parseInt(pairParts[1])));
		}
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new IllegalArgumentException("Given values are invalid.\n"
					+ "X and Y values must be separated by ',' and value pairs must be separated by white space.");
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Given values must be integer values.");
		}
		return values;
	}
	
	/**
	 * Constructor that creates new {@link BarChartDemo} object.
	 * 
	 * @param barChart bar chart object
	 * @param path     path of the file that contains bar chart informations
	 */
	public BarChartDemo(BarChart barChart, Path path) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(10, 10);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		initGUI(barChart, path);
	}
	
	/**
	 * Method initializes {@code BarChartDemo} object.
	 * 
	 * @param barChart bar chart object
	 * @param path     path of the file that contains bar chart informations
	 */
	private void initGUI(BarChart barChart, Path path) {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		// Add barChart
		BarChart model = barChart;
		BarChartComponent barChartComponent = new BarChartComponent(model);
		barChartComponent.setBounds(getBounds());
		barChartComponent.setVisible(true);
		cp.add(barChartComponent, BorderLayout.CENTER);
		// Add path label
		JLabel pathLabel = new JLabel(path.toAbsolutePath().normalize().toString());
		cp.add(pathLabel, BorderLayout.NORTH);
	}

}
