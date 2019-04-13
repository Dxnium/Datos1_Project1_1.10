package game.logic;

// TODO: Auto-generated Javadoc
/**
 * A simple node for the dictionary linked list that stores a string data, serves as node for the DictionaryLinkedList Class.
 */
public class DictionaryNode {/** The data. */

	private String data;
	
	/** The next. */
	private DictionaryNode next;
	
	
	/**
	 * Instantiates a new dictionary node.
	 *
	 * @param data String value 
	 */
	public DictionaryNode(String data) {
		this.data=data;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public DictionaryNode getNext() {
		return next;
	}

	/**
	 * Sets the next.
	 *
	 * @param next the new next
	 */
	public void setNext(DictionaryNode next) {
		this.next = next;
	}

}
