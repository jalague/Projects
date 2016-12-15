package hmk7;

/** A class that describes a node in a doubly linked list of integers. 
 * Each Node stores an integer elem, a reference to the next node, 
 * and a reference to the previous node.
 */
public class DLLNode {

		private int elem;
		private DLLNode next;
		private DLLNode prev;

		
		DLLNode(int elem) {
			this.elem = elem;
			this.next = null;
			this.prev = null;
		}
		
		public int elem() {
			return elem;
		}
		public DLLNode next() {
			return next;
		}
		public DLLNode prev() {
			return prev;
		}
		
		public void setNext(DLLNode node) {
			next = node;
		}
		
		public void setPrev(DLLNode node) {
			prev = node;
		}
}

