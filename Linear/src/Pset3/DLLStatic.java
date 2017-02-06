package Pset3;

public class DLLStatic {
	// PROBLEM 3
	public static DLLNode moveToFront(DLLNode front, DLLNode target) {
		while (target.prev != null) {
			DLLNode swapNode = target.prev;
			target.prev = swapNode.prev;
			target.prev.next = target;
			swapNode.prev = target;
			swapNode.next = target.next;
			if (target.next != null) {
				target.next.prev = swapNode;

			}
			target.next = swapNode;
		}
		return target;
	}
	//PROBLEM 4
	public static DLLNode reverse(DLLNode front) {
		if (front == null) 
			return null;
		
		for (DLLNode n = front; n != null; n = n.prev) {
			DLLNode tmp = n.prev;
			n.prev = n.next;
			n.next = tmp;
			if (n.prev == null) {
				return n;
			}
		}
		return null;
	}
	//PROBLEM 5
	public static Node deleteAll(Node front, String target) {
		if (front == null)
			return null;
		if (front.data.equals(target)) {
			return deleteAll(front.next, target);
		}
		else {
			front.next = deleteAll(front.next, target);
			return front;
		}
	}
	//PROBLEM 6
	public static IntNode merge(IntNode frontL1, IntNode frontL2) {
		
		if (frontL1 == null && frontL2 == null) {
			return null;
		}
		if (frontL1 == null || frontL1.data >= frontL2.data){
			frontL2.next = merge(frontL1, frontL2.next);
			return frontL2;
		}
		if (frontL2 == null || frontL2.data > frontL1.data){
			frontL1.next = merge(frontL1.next, frontL2);
			return frontL1;
		}
		return null;
	}
	//PROBLEM 7
	public static Node reverse(Node front){
		
		
		if (front.next != null) {
			Node rev = reverse(front.next);
			
			rev.next = front;
			return null;
		}
		else {
			return front;
		}
	}
	
	
	public static void main(String[] args){
		DLLNode n1 = new DLLNode("Hi", null, null);
		DLLNode n2 = new DLLNode("My", null, null);
		DLLNode n3 = new DLLNode("Name", null, null);
		DLLNode n4 = new DLLNode("Is", null, null);
		DLLNode n5 = new DLLNode("Java", null, null);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n2.prev = n1;
		n3.prev = n2;
		n4.prev = n3;
		n5.prev = n4;
		printList(n1);
		System.out.println();
		printList(moveToFront(n1, new DLLNode("Hi", null, null)));
		//printList(reverse(n1));
		
	}

	public static void printList(DLLNode front){
		for(DLLNode tmp = front; tmp!=null; tmp = tmp.next){
			System.out.print(tmp.data + " ");
		}
	}
}