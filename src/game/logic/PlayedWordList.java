package game.logic;

// TODO: Auto-generated Javadoc
/**
 * Implementation of a simple linked list that is used to store and select specific words formed by a Player on any given turn for later verification
 */
public class PlayedWordList {
	
	/** The head. */
	WordListNode head;
	
	/** The length. */
	int length;
	
	
	



	/**
	 * Append.
	 * Appends a WordListNode with the data value at the end of the linked list
	 *
	 * @param data  of String array type
	 */
	public void append (String[] data) {
		if (head == null) {
			if(data[0].length()>1) {
				this.head = new WordListNode (data);
				this.length=this.length+1;
				return;
			}
			
		}

		WordListNode current = head;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		if(data[0].length()>1) {
			current.setNext( new WordListNode(data)) ;
			this.length=this.length+1;	
		}
		

	}


	/**
	 * Removes the duplicates nodes present in the linked list
	 */
	void removeDuplicates() { 
        WordListNode ptr1 = null, ptr2 = null, dup = null; 
        ptr1 = head; 
  
        /* Pick elements one by one */
        while (ptr1 != null && ptr1.next != null) { 
            ptr2 = ptr1; 
  
            /* Compare the picked element with rest 
                of the elements */
            while (ptr2.next != null) { 
  
                /* If duplicate values exist then delete it */
                if (ptr1.data[0].equals(ptr2.next.data[0])) { 
  
                    
                    dup = ptr2.next; 
                    ptr2.next = ptr2.next.next; 
                    this.length=length-1;
                    System.gc(); 
                } else { 
                    ptr2 = ptr2.next; 
                } 
            } 
            ptr1 = ptr1.next; 
        } 
    } 



	/**
	 * Sets the head.
	 *
	 * @param head the new head
	 */
	public void setHead(WordListNode head) {
		this.head = head;
	}

	/**
	 * Gets the head.
	 *
	 * @return the head
	 */
	public WordListNode getHead() {
		return head;
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
}
	

