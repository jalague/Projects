package hmk7;

/** A class that represents a singly linked list of nodes. 
 *  Each LinkedList had the head (a reference to the first node), and
 *  the tail( the reference to the last node in the list).
 *
 */
public class LinkedList {
		private Node head;
		private Node tail;
		
		LinkedList() {
			head = null;
			tail = null;
		}
		
		public Node head() {
			return head;
		}
		
		public Node tail() {
			return tail;
		}
		
		public int sum(){
			int sum=0;
			Node current = head;
			while (current != null) {
				sum=sum+ current.elem();
				current = current.next();
			}
			return sum;
			
		}
		public void set(int i, int elem){
			int counter=0;
			Node current = head;
			while (counter != i) {
				current = current.next();
				counter++;
			}
			current.setElem(elem);
		}
		public boolean find(int elem){
			Node current= head;
			
			while(current.next()!=null){
				if (current.elem()==elem)
					return true;
				current= current.next();
			}
			return false;
		}
		
		public void swap(Node node1, Node node2){
			int elem1 = node1.elem();
			int elem2=node2.elem();
			node1.setElem(elem2);
			node2.setElem(elem1);
		}
		public void concatenate(LinkedList list){
			tail.setNext(list.head());
			tail= list.tail();
		}
		public void printElementsBackwards(){
			 
			if(head.next()==null){
				System.out.print(head.elem());
			}
			else {
				LinkedList l = new LinkedList();
				Node current=head;
				System.out.print(tail.elem()+ " ");
				while(current.next()!=null){
					
					l.insertElemBack(current.elem());
					current=current.next();
				}
				l.printElementsBackwards();
			}
				
		}
		public boolean isSorted(){
			if(head.next()==null){
				System.out.println("yay");
				return true;
			}
			
			else if(head.next().elem()>=head.elem()){
				LinkedList l = new LinkedList();
				Node current=head.next();
				while(current!=null){
					System.out.println(current.elem());
					l.insertElemBack(current.elem());
					current=current.next();
				}
				if(l.isSorted())
					return true;
				
			}
			
			return false;
			
		}
		
		/** Creates a new node with the given element and 
		 *  inserts it at front of the list. **/
		public void insertElemFront(int elem) {
			Node newNode = new Node(elem);
			if (tail == null)
				tail = newNode;
			
			newNode.setNext(head);
			head = newNode;
			
		}
		
		/** Creates a new node with the given element and 
		 *  inserts it at the back of the list. **/
		public void insertElemBack(int elem) {
			
			Node newNode = new Node(elem);
			if (tail == null) { // inserting the first node
				head = newNode;
				tail = newNode;			
			}
			else {
				tail.setNext(newNode);
				tail = newNode;
			}
		}
		
		/** Inserts a given node at front of the list. **/
		public void insertNodeFront(Node newNode) {
			if (tail == null)
				tail = newNode;
			
			newNode.setNext(head);
			head = newNode;
			
		}
		
		/** Inserts a given node at the back of the list. **/
		public void insertNodeBack(Node newNode) {
			
			if (tail == null) { 
				head = newNode;
				tail = newNode;			
			}
			else {
				tail.setNext(newNode);
				tail = newNode;
			}
		}
		
		/** Traverses the list and prints each element **/
		public void printElements() {
			Node current = head;
			while (current != null) {
				System.out.print(current.elem() + " ");
				current = current.next();
			}
			System.out.println();
		}
}
