package game.logic;

public class PlayerLinkedList {// A circular  linked list that will store nodes that use player objects as data, will be used for a player queue
	PlayerLinkedListNode head;
	PlayerLinkedListNode tail;
	int length;
	
	
	public PlayerLinkedListNode getTail() {
		return tail;
	}




	public void setTail(PlayerLinkedListNode tail) {
		this.tail = tail;
	}




	public int getLength() {
		return length;
	}




	public void setLength(int length) {
		this.length = length;
	}

	
	
	
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


	public void setHead(PlayerLinkedListNode head) {
		this.head = head;
	}

	public PlayerLinkedListNode getHead() {
		return head;
	}

}

