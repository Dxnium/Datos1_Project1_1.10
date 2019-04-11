package game.logic;

public class PlayedWordList {
	WordListNode head;
	int length;
	
	
	



	public void append (String[] data) {//adds a node at the end of the list
		if (head == null) {
			this.head = new WordListNode (data);
			this.length=this.length+1;
			return;
		}

		WordListNode current = head;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext( new WordListNode(data)) ;
		this.length=this.length+1;

	}


	public void eraseData(String[] data) {//erases a node using a search criteria
		if (head == null) return;
		if (head.getData()==data) {
			this.setHead(head.getNext());
		}

		WordListNode current = head;
		while (current.getNext() != null) {
			if(current.getNext().getData()==data) {
				current.setNext(current.getNext().getNext());
				return;
			}
			current = current.getNext();

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
	

