package Pset3;
import java.util.*;
public class CLL {
	private Node rear; //pointer to the last node of CLL
	
	//PROBLEM 1
	public boolean delete (String target) throws NoSuchElementException {
		if (rear == null) throw new NoSuchElementException();
		//rear is the only element in the list and it is the target
		if (rear.next == rear && rear.data.equals(target)) {
			rear = null;
			return true;
		}
		//target is front
		if (rear.next.data.equals(target)){
			rear.next = rear.next.next;
			return true;
		}
		//target is anywhere other than front, including rear
		for(Node n = rear.next; n != rear; n = n.next){
			if (n.next.data.equals(target)) {
				n.next = n.next.next;
				return true;
			}
		}
		
		//target not in list
		return false;
	}
	//PROBLEM 2
	public boolean addAfter(String newItem, String afterItem) {
		if (rear == null) return false;
		//rear is the only element in the list and it is the target
		if (rear.next == rear && rear.data.equals(afterItem)) {
			rear.next = new Node(newItem, rear);
			return true;
		}
		
		//target is front
		if (rear.next.data.equals(afterItem)){
			rear.next = new Node(newItem, rear.next.next);
			return true;
		}
		
		//target is anywhere other than front
		for(Node n = rear.next; n != rear; n = n.next){
			if (n.next.data.equals(afterItem)) {
				n.next = new Node(newItem, n.next.next);
				return true;
			}
		}
		
		//target not in list
		return false;
	}
	
	
}
