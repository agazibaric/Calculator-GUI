package hr.fer.zemris.java.gui.prim;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class PrimListModelTest {
	
	PrimListModel model;
	
	@Before
	public void setup() {
		model = new PrimListModel();
	}
	
	@Test
	public void initialValueTest() {
		int actual = model.getElementAt(0);
		int expected = 1;
		assertEquals(expected, actual);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void initialValueGetAtIndex1Test() {
		model.getElementAt(1);
	}
	
	@Test
	public void firstPrimeTest() {
		model.next();
		int actual = model.getElementAt(1);
		int expected = 2;
		assertEquals(expected, actual);
	}
	
	@Test
	public void fifthPrimeTest() {
		model.next();
		model.next();
		model.next();
		model.next();
		model.next();
		int actual = model.getElementAt(5);
		int expected = 11;
		assertEquals(expected, actual);
	}
	
	@Test
	public void initialSizeTest() {
		int actual = model.getSize();
		int expected = 1;
		assertEquals(expected, actual);
	}
	
	@Test
	public void sizeTest() {
		model.next();
		model.next();
		model.next();
		model.next();
		model.next();
		int actual = model.getSize();
		int expected = 6;
		assertEquals(expected, actual);
	}
	
	@Test
	public void getAtIndexTest() {
		model.next();
		model.next();
		model.next();
		model.next();
		model.next();
		int actual = model.getElementAt(3);
		int expected = 5;
		assertEquals(expected, actual);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void getAtInvalidIndexTest() {
		model.next();
		model.next();
		model.next();
		model.next();
		model.next();
		model.getElementAt(6);
	}

}
