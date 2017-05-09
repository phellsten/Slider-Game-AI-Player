package aima.core.util.datastructure;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): pg 80.<br>
 * 
 * First-in, first-out or FIFO queue, which pops the oldest element of the queue;
 */

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public class FIFOQueue<E> extends LinkedList<E> implements Queue<E> {
	private static final long serialVersionUID = 1;

	public FIFOQueue() {
		super();
	}

	public FIFOQueue(Collection<? extends E> c) {
		super(c);
	}

	//
	// START-Queue
	@Override
	public boolean isEmpty() {
		return 0 == size();
	}

	@Override
	public E pop() {
		return poll();
	}

	@Override
	public Queue<E> insert(E element) {
		if (offer(element)) {
			return this;
		}
		return null;
	}
	// END-Queue
	//
}