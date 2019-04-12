package game.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerLinkedListNode.
 */
public class PlayerLinkedListNode {/** The data. */
//node that takes a player object as data
	private Player data;
	
	/** The next. */
	private PlayerLinkedListNode next;
	
	
	/**
	 * Instantiates a new player linked list node.
	 *
	 * @param data the data
	 */
	public PlayerLinkedListNode(Player data) {
		this.data=data;
	}
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Player getData() {
		return data;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Player data) {
		this.data = data;
	}
	
	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public PlayerLinkedListNode getNext() {
		return next;
	}
	
	/**
	 * Sets the next.
	 *
	 * @param next the new next
	 */
	public void setNext(PlayerLinkedListNode next) {
		this.next = next;
	}
	
	
	
}
