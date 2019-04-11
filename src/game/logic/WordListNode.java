package game.logic;

public class WordListNode {
	
	String[] data;
	WordListNode next;
	
	public String[] getData() {
		return data;
	}


	public void setData(String[] data) {
		this.data = data;
	}


	public WordListNode getNext() {
		return next;
	}


	public void setNext(WordListNode next) {
		this.next = next;
	}
	

	public WordListNode(String[] data) {
		this.data=data;
	}

}
