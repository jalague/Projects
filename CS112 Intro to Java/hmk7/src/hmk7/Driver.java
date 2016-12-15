package hmk7;

/** Tests classes Node and LinkedList **/
public class Driver {
	public static void main(String[] args) {
		LinkedList myList = new LinkedList();
		myList.insertElemFront(95);
		myList.insertElemFront(15);
		myList.insertElemFront(5);
		//myList.printElements();
		//myList.printElementsBackwards();
		//DLLNode v = new DLLNode(15);
		//myList.remove(v);
		//myList.printElements();
		myList.insertElemBack(101);
		//myList.insertElemBack(100);
		//myList.insertElemBack(103);
		myList.printElements();
		//System.out.println(myList.isSorted());
		myList.printElementsBackwards();
		//myList.reverse();
		//myList.printElements();
	}
	
}
