package game.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayedWordList.
 */
public class PlayedWordList {
	
	/** The head. */
	WordListNode head;
	
	/** The length. */
	int length;
	
	
	



	/**
	 * Append.
	 *
	 * @param data the data
	 */
	public void append (String[] data) {//adds a node at the end of the list
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
	 * Removes the duplicates.
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
  
                /* If duplicate then delete it */
                if (ptr1.data[0].equals(ptr2.next.data[0])) { 
  
                    /* sequence of steps is important here */
                    dup = ptr2.next; 
                    ptr2.next = ptr2.next.next; 
                    this.length=length-1;
                    System.gc(); 
                } else /* This is tricky */ { 
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
	

