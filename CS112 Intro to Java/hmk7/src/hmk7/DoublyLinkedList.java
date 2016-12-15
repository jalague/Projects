package hmk7;

/** A class that represents a doubly linked list of nodes. 
 *  Each DoublyLinkedList has the head (a reference to the first node), and
 *  the tail( the reference to the last node in the list).
 *
 */
public class DoublyLinkedList {
	private DLLNode head;
	private DLLNode tail;
	
	DoublyLinkedList()  { 
		head = tail = null;
		
	}
	public void insertElemBack(int elem){
		DLLNode n = new DLLNode(elem);
		n.setPrev(tail);
		n.setNext(null);
		tail.setNext(n);
		tail= n;
		
	}
	public void remove(DLLNode n){
		DLLNode current= head;
		while(current.next()!=null){
			if(current.elem()==n.elem()){
				
				DLLNode prevN = current.prev();
				prevN.setNext(current.next());
				current.next().setPrev(prevN);
				
				break;
				
			}
			else{
				current=current.next();
			}
		}
	}
	public void printElementsBackwards(){
		DLLNode current= tail;
		while(current!=null){
			System.out.print(current.elem()+ " ");
			current=current.prev();
		}
		System.out.println();

	}
	public void reverse(){
		
		DLLNode current= head;
		while(current!=null){
			DLLNode next = current.next();
			DLLNode prev = current.prev();
			if (current.equals(head)){
					head.setPrev(head.next());
					head.setNext(null);
			}
			else{
			
				
				current.setPrev(next);
				current.setNext(prev);
			}
			current= next;
		}
		head=tail;
		
	}
	public boolean isEmpty() {
		return (head == null);
	}
	
	/** Creates a new node with the given element and 
	 *  inserts it at front of the list. **/
	public void insertElemFront(int elem) {
		
		DLLNode newNode = new DLLNode(elem);
		if (isEmpty()) {
			head = tail = newNode;
		}
		else {
			newNode.setNext(head);
			head.setPrev(newNode);
			newNode.setPrev(null);
			head = newNode;
		}
	}
	

	/** Traverses the list and prints each element **/
	public void printElements() {
		DLLNode current = head;
		while (current != null) {
			System.out.print(current.elem() + " ");
			current = current.next();
		}
		System.out.println();
	}
	
}
