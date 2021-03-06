package queue;

import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Class myQueue is queue uss for cientes connects .
 *
 * @param <E> the element type
 */
public class myQueue<E> {
	
	/** The list. */
	private LinkedList<E> list = new LinkedList<E>();

	  /**
  	 * Enqueue.
  	 *
  	 * @param item the item
  	 */
  	public void enqueue(E item) {
	    list.addLast(item);
	  }

	  /**
  	 * Dequeue.
  	 *
  	 * @return the e
  	 */
  	public E dequeue() {
	    return list.poll();
	  }

	  /**
  	 * Checks for items.
  	 *
  	 * @return true, if successful
  	 */
  	public boolean hasItems() {
	    return !list.isEmpty();
	  }

	  /**
  	 * Size.
  	 *
  	 * @return the int of the size 
  	 */
  	public int size() {
	    return list.size();
	  }

	  /**
  	 * Adds the items.
  	 *
  	 *]
  	 */
  	public void addItems(myQueue<? extends E> q) {
	    while (q.hasItems())
	      list.addLast(q.dequeue());
	  }
	

}
