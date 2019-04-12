package game.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class WordListNode.
 */
public class WordListNode {
	
	/** The data. */
	String[] data;
	
	/** The next. */
	WordListNode next;
	


	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String[] getData() {
		return data;
	}


	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(String[] data) {
		this.data = data;
	}


	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public WordListNode getNext() {
		return next;
	}


	/**
	 * Sets the next.
	 *
	 * @param next the new next
	 */
	public void setNext(WordListNode next) {
		this.next = next;
	}
	

	/**
	 * Instantiates a new word list node.
	 *
	 * @param data the data
	 */
	public WordListNode(String[] data) {
		this.data=data;
	}

}
