package PSet2;

import llstatic.Node;

public class StaticLL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntNode head = new IntNode(1, null);
		head = add(head, 3);
		head = add(head, 4);
		head = add(head, 5);
		head = add(head, 6);
		printLL(head);
		
		IntNode head2 = new IntNode(2, null);
		head2 = add(head2, 7);
		head2 = add(head2, 8);
		head2 = add(head2, 9);
		head2 = add(head2, 601);
		printLL(head2);
		
		printLL(commonElements(head, head2));
		
	}
	
	public static void printLL (IntNode head) {
		for (IntNode n = head; n != null; n = n.next){
			System.out.print(n.data + " -> ");
		}
		
		
		System.out.println("\n----------");
		
	}
	
	public static void printLL (StringNode head) {
		for (StringNode n = head; n != null; n = n.next){
			System.out.print(n.data + " -> ");
		}
		System.out.println("\n----------");
		
	}
	//PROBLEM 1
	public static IntNode addBefore(IntNode front, int target, int newItem) {
		if (front == null) return null;
		if (front.data == target) {
			return new IntNode(newItem, front);
		}
		IntNode prev = front;
		for (IntNode n = front.next; n != null; n = n.next){
			if (n.data == target) {
				prev.next = new IntNode(newItem, n);
				return front;
			}
			prev = prev.next;
		}
		return front;
	}
	
	//PROBLEM 2
	public static IntNode addBeforeLast(IntNode front, int item) {
		if (front == null) return null;
		if (front.next == null)
			return new IntNode(item, front);
		IntNode prev = front;
		for (IntNode n = front.next; n != null; n = n.next) {
			if (n.next == null) {
				prev.next = new IntNode(item, n);
				return front;
			}
			prev = prev.next;
		}
		return front;
	}
	
	//PROBLEM 3
	public static int numberOfOccurences (StringNode front, String target) {
		if (front == null) return 0;
		int count = 0;
		for (StringNode n = front; n != null; n = n.next) {
			if (n.data.equals(target)){
				count++;
			}
		}
		return count;
	}
	
	
	//PROBLEM 4
	public static void deleteEveryOther(IntNode front) {
		//This line is redundant, but is good practice.
		//if (front == null) return;
		
		for (IntNode n = front; n != null; n = n.next) {
			if (n.next != null) {
				n.next = n.next.next;
			}
		}
	}
	
	//PROBLEM 5
	public static StringNode deleteAllOccurences(StringNode front, String target) {
		while (true) {
			if (front == null) return null;
			if (front.data.equals(target)) {
				front = front.next;
				continue;
			}
			break;
		}
		
		StringNode prev = front;
		for (StringNode n = front.next; n != null; n = n.next) {
			if (n.data.equals(target)) {
				prev.next = n.next;
				continue;
			}
			prev = prev.next;
		}
		return front;
	}
	
	//PROBLEM 6
	public static IntNode commonElements(IntNode frontL1, IntNode frontL2) {
		if (frontL1 == null || frontL2 == null) return null;
		
		IntNode commoners = null;
		
		while (frontL1 != null && frontL2 != null) {
			if (frontL1.data == frontL2.data) {
				if (commoners == null) {
					commoners = new IntNode(frontL1.data, null);
					frontL1 = frontL1.next;
					frontL2 = frontL2.next;
					continue;
				}
				for (IntNode n = commoners; n != null; n = n.next){
					if (n.next == null) {
						n.next = new IntNode(frontL1.data, null);
						break;
					}
				}
				frontL1 = frontL1.next;
				frontL2 = frontL2.next;
			}
			else {
				if (frontL1.data > frontL2.data) {
					frontL2 = frontL2.next;
				}
				else
					frontL1 = frontL1.next;
			}
		}
		
		return commoners;
	}
	
	
	
	public static StringNode add(StringNode front, String item) {
		if (front == null) return new StringNode(item, null);
		
		for (StringNode n = front; n != null; n = n.next) {
			if (n.next == null) {
				n.next = new StringNode(item, null);
				return front;
			}
		}
		return front;
	}
	
	public static IntNode add(IntNode front, int item) {
		if (front == null) return new IntNode(item, null);
		
		for (IntNode n = front; n != null; n = n.next) {
			if (n.next == null) {
				n.next = new IntNode(item, null);
				return front;
			}
		}
		return front;
	}

}
