package hmk7;

/** A class that describes a single node in a singly linked list of integers. 
 * Each Node stores an integer elem, and a reference to the next node, next
 */
public class Node {
	private int elem;
	private Node next;
	
	Node(int elem) {
		this.elem = elem;
		next = null;
	}
	
	public void setElem(int elem) {
		this.elem = elem;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
	public int elem() {
		return elem;
	}
	public Node next() {
		return next;
	}
}
