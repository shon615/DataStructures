package LinkedList;

import java.util.*;

public class LinkedList<T extends Comparable<T>> {
	
	private Node<T> head;
	private int length;
	
	public LinkedList () {
		head = null;
		length = 0;
	}
	
	public void deleteFromFront() throws MyCoolException {
		if (head != null) {
			head = head.next;
			return;
		}
		else {
			throw new MyCoolException();
		}
	}
	
	//addToFront
	public void addToFront(T data) {
		Node<T> n = new Node(data);
		n.next = head;
		head = n;
	}
	
	//addAfter
	public void addAfter(T s, T target) {
		if (search(s) == false) return;
		else {
			for (Node<T> n = head; n != null; n = n.next) {
				if (n.data.equals(s)) {
					Node<T> created = new Node<T>(target);
					created.next = n.next;
					n.next = created;
					return;
				}
				else {
					continue;
				}
				
			}
		}
		
		
		
	}
	
	//addToRear
	 
	//get
	public T get(int x) {
		if (x > length - 1 || x < 0) throw new IllegalArgumentException();
		Node<T> tmp = head;
		for (int i = 1; i < x; i++) {
			tmp = tmp.next;
		}
		return tmp.data;
	}
	
	//search
	public boolean search(T target) {
		return search(head, target);
	}
	
	private boolean search (Node<T> newHead, T target) {
		if (newHead == null) return false;
		if (newHead.data.equals(target)) return true;
		else return search(newHead.next, target);
	}
	
	//delete
	
	//toString
	
	//equals
	
	public static void main (String[] args) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		try {
			Integer.parseInt("hi");
			l.deleteFromFront();
		}
		catch (MyCoolException mce) {
			mce.printStackTrace();
		}
		catch (NumberFormatException e) {
			
		}
		catch (Exception e) {
			
		}
	}
	
	
	
	
}
