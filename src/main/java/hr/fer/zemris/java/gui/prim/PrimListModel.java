package hr.fer.zemris.java.gui.prim;

import java.util.LinkedList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Class represents generator of prime numbers that is used as list model.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class PrimListModel implements ListModel<Integer> {
	
	/**
	 * Next number to check if it's prime number
	 */
	private int nextNumber;
	/**
	 * List of prime numbers
	 */
	private List<Integer> primeNumbers;
	/**
	 * List of data listeners
	 */
	private List<ListDataListener> listeners;
	
	/**
	 * Constructor that creates new {@link PrimListModel} object.
	 */
	public PrimListModel() {
		initPrimListModel();
	}

	/**
	 * Method initializes {@code PrimListModel}.
	 */
	private void initPrimListModel() {
		primeNumbers = new LinkedList<>();
		listeners = new LinkedList<>();
		primeNumbers.add(1);
		nextNumber = 2;
	}
	/**
	 * Method returns next prime number.
	 * 
	 * @return next prime number
	 */
	public void next() {
		if (nextNumber == 2) {
			nextNumber = 3;
			primeNumbers.add(2);
		} else {
			int primeNumber = nextNumber;
			while (true) {
				if (isPrime(primeNumber)) {
					nextNumber = primeNumber + 2;
					primeNumbers.add(primeNumber);
					break;
				}
				primeNumber += 2;
			}
		}
		notifyAboutAddedInterval();
	}
	
	/**
	 * Method notifies listeners about added prime number.
	 */
	private void notifyAboutAddedInterval() {
		int lastIndex = primeNumbers.size();
		ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, lastIndex, lastIndex);
		for (ListDataListener listener : listeners) {
			listener.intervalAdded(event);
		}
	}
	
	/**
	 * Method checks if given {@code number} is prime number.
	 * 
	 * @param number number that is checked if it's prime
	 * @return       {@code true} if given {@code number} is prime number,
	 * 				 {@code false} otherwise
	 */
	private boolean isPrime(int number) {
		if (number == 2)
			return true;
		
		if (number % 2 == 0)
			return false;

		int limit = (int) Math.sqrt(number) + 1;
		for (int k = 3; k < limit; k += 2) {
			if (number % k == 0)
				return false;
		}
		return true;
	}

	@Override
	public Integer getElementAt(int index) {
		if (index >= getSize())
			throw new IndexOutOfBoundsException("Was: " + index);
		return primeNumbers.get(index);
	}

	@Override
	public int getSize() {
		return primeNumbers.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
	}

}
