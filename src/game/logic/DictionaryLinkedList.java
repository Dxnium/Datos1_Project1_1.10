package game.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class DictionaryLinkedList.
 */
public class DictionaryLinkedList {// a simple linked list that takes dictionary nodes
	
	/** The head. */
DictionaryNode head;

		/**
		 * Append.
		 *
		 * @param data the data
		 */
		public void append (String data) {//adds a node at the end of the list
			if (head == null) {
				this.head = new DictionaryNode (data);
				return;
			}

			DictionaryNode current = head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext( new DictionaryNode(data));

		}


		/**
		 * Erase data.
		 *
		 * @param data the data
		 */
		public void eraseData(String data) {//erases a node using a search criteria
			if (head == null) return;
			if (head.getData()==data) {
				this.setHead(head.getNext());
			}

			DictionaryNode current = head;
			while (current.getNext() != null) {
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
		public void setHead(DictionaryNode head) {
			this.head = head;
		}

		/**
		 * Gets the head.
		 *
		 * @return the head
		 */
		public DictionaryNode getHead() {
			return head;
		}
		
}
