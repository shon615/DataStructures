package LinkedList;
public class CircularLinkedList<T extends Comparable<T>> {
	Node<T> tail;
	
	public void addToFront (T data) {
		Node<T> n = new Node<T>(data);
		if (tail == null) {
			
			n.next = tail;
			tail = n;
		}
		else {
			n.next = tail.next;
			tail.next = n;
		}
	}
	
	public void addToRear (T data) {
		Node<T> n = new Node<T>(data);
		if (tail == null) {
			
			n.next = tail;
			tail = n;
		}
		else {
			n.next = tail.next;
			tail.next = n;
			tail = n;
		}
	}
	
	public boolean search (T target) {
		if (tail == null) return false;
		for (Node<T> n = tail.next; n != tail; n = n.next){
			if (n.data.equals(target)) return true;
		}
		if (tail.data.equals(target)) return true;
		return false;
	}
	
	
}
