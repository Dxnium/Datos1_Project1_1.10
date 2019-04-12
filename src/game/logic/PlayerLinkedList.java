package game.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerLinkedList.
 */
public class PlayerLinkedList {/** The head. */
// A circular  linked list that will store nodes that use player objects as data, will be used for a player queue
	PlayerLinkedListNode head;
	
	/** The tail. */
	PlayerLinkedListNode tail;
	
	/** The length. */
	int length;
	
	
	/**
	 * Gets the tail.
	 *
	 * @return the tail
	 */
	public PlayerLinkedListNode getTail() {
		return tail;
	}




	/**
	 * Sets the tail.
	 *
	 * @param tail the new tail
	 */
	public void setTail(PlayerLinkedListNode tail) {
		this.tail = tail;
	}




	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public int getLength() {
		return length;
	}




	/**
	 * Sets the length.
	 *
	 * @param length the new length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	
	
	
	/**
	 * Append.
	 *
	 * @param data the data
	 */
	public void append (Player data) {//adds a node at the end of the list
		if (head == null) {
			this.head = new PlayerLinkedListNode (data);
			this.tail=this.head;
			this.length++;
			return;
			
		}
		PlayerLinkedListNode newNode= new PlayerLinkedListNode(data);
		this.tail.setNext(newNode);
		this.tail=newNode;
		this.tail.setNext(head);
		this.length++;

	}
	
	
	

	/**
	 * Erase data.
	 *
	 * @param data the data
	 */
	public void eraseData(Player data) {//looks for a node via search criteria and breaks the link between the node and the rest of the list
		if (head == null) return;
		if (head.getData()==data) {
			this.setHead(head.getNext());
		}

		PlayerLinkedListNode current = head;
		while (current.getNext() != head) {
			if(current.getNext().getData()==data) {
				current.setNext(current.getNext().getNext());
				return;
			}
			current = current.getNext();

		}
	}


	/**
	 * Sets the head.
	 *
	 * @param head the new head
	 */
	public void setHead(PlayerLinkedListNode head) {
		this.head = head;
	}

	/**
	 * Gets the head.
	 *
	 * @return the head
	 */
	public PlayerLinkedListNode getHead() {
		return head;
	}

}
