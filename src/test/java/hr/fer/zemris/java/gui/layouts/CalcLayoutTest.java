package hr.fer.zemris.java.gui.layouts;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalcLayoutTest {
	
	private JPanel p;
	
	@Before
	public void setup() {
		p = new JPanel(new CalcLayout(2));
	}

	@Test
	public void preferredSizeTest() {
		JLabel l1 = new JLabel("");
		l1.setPreferredSize(new Dimension(10, 30));
		JLabel l2 = new JLabel("");
		l2.setPreferredSize(new Dimension(20, 15));
		p.add(l1, new RCPosition(2, 2));
		p.add(l2, new RCPosition(3, 3));
		Dimension actualDim = p.getPreferredSize();
		Dimension expectedDim = new Dimension(152, 158);
		
		assertEquals(expectedDim, actualDim);
	}
	
	@Test
	public void preferredSizeFirstPositionTest() {
		JLabel l1 = new JLabel("");
		l1.setPreferredSize(new Dimension(108, 15));
		JLabel l2 = new JLabel("");
		l2.setPreferredSize(new Dimension(20, 30));
		p.add(l1, new RCPosition(1, 1));
		p.add(l2, new RCPosition(3, 3));
		Dimension actualDim = p.getPreferredSize();
		Dimension expectedDim = new Dimension(152, 158);
		
		assertEquals(expectedDim, actualDim);
	}
	
	@Test
	public void minimumSizeTest() {
		JLabel l1 = new JLabel("");
		l1.setMinimumSize(new Dimension(10, 30));
		JLabel l2 = new JLabel("");
		l2.setMinimumSize(new Dimension(20, 15));
		p.add(l1, new RCPosition(2, 2));
		p.add(l2, new RCPosition(3, 3));
		Dimension actualDim = p.getMinimumSize();
		Dimension expectedDim = new Dimension(152, 158);
		
		assertEquals(expectedDim, actualDim);
	}
	
	@Test
	public void maximumSizeTest() {
		JLabel l1 = new JLabel("");
		l1.setMaximumSize(new Dimension(10, 30));
		JLabel l2 = new JLabel("");
		l2.setMaximumSize(new Dimension(20, 15));
		p.add(l1, new RCPosition(2, 2));
		p.add(l2, new RCPosition(3, 3));
		Dimension actualDim = p.getMaximumSize();
		Dimension expectedDim = new Dimension(152, 158);
		
		assertEquals(expectedDim, actualDim);
	}
	
	@Test (expected = CalcLayoutException.class)
	public void invalidPositionTest1() {
		JLabel l1 = new JLabel("");
		p.add(l1, new RCPosition(1, 2));
	}
	
	@Test (expected = CalcLayoutException.class)
	public void invalidPositionTest2() {
		JLabel l1 = new JLabel("");
		p.add(l1, new RCPosition(1, 3));
	}
	
	@Test (expected = CalcLayoutException.class)
	public void invalidPositionTest3() {
		JLabel l1 = new JLabel("");
		p.add(l1, new RCPosition(1, 4));
	}
	
	@Test (expected = CalcLayoutException.class)
	public void invalidPositionTest4() {
		JLabel l1 = new JLabel("");
		p.add(l1, new RCPosition(1, 5));
	}
	
	@Test (expected = CalcLayoutException.class)
	public void invalidPositionTest5() {
		JLabel l1 = new JLabel("");
		p.add(l1, new RCPosition(0, 6));
	}
	
	@Test (expected = CalcLayoutException.class)
	public void invalidPositionTest6() {
		JLabel l1 = new JLabel("");
		p.add(l1, new RCPosition(3, 8));
	}
	
	@Test (expected = CalcLayoutException.class)
	public void invalidPositionTest7() {
		JLabel l1 = new JLabel("");
		p.add(l1, new RCPosition(2, -1));
	}
	
	
	
}
