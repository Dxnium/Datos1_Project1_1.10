package game.logic;

public class PlayedWordList {
	WordListNode head;
	int length;
	
	
	



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



	public void setHead(WordListNode head) {
		this.head = head;
	}

	public WordListNode getHead() {
		return head;
	}
	
	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}
}
	

