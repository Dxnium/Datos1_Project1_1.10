package game.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class DictionaryLinkedList is an implementation of a simple linked list that takes Dictionary Nodes, it contains the basic methods of a linked list implementation
 */ 
public class DictionaryLinkedList {
	
	/** The head. */
DictionaryNode head;

		/**
		 * Append.
		 * Appends a new Dictionary node at the end of the list
		 *
		 * @param data of DictionaryNode type
		 */
		public void append (String data) {
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
		 * Erases a specific node of the list given a search criteria of String type.
		 *The data parameter is compared to each of the strings contained on the DictionaryNodes of the list
		 *and stops referencing the node that matches the criteria.
		 * @param data of DictionaryNode type
		 */
		public void eraseData(String data) {
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
