package llstatic;
public class LLUtil {
	
	public static Node addToFront(Node head, int data){
		Node n = new Node(data);
		n.next = head;
		return n;
	}
	
	public static boolean search(Node head, int data) {
		for (Node n = head; n != null; n = n.next)
			if (n.data == data)
				return true;
		return false;
	}
	
	public static Node deleteFromFront(Node head) {
		if (head == null)
			return null;
		
		return head.next;
	}
	
	public static Node deleteFirstTarget(Node head, int target) {
		// 4 cases:
		
		// 1. Empty list
		if (head == null)
			return null;
		
		// 2. if target is at the beginning (prev = null, n.data == data)
		if (head.data == target)
			return deleteFromFront(head);
			
	
		// 3. if target is in the middle or at the end
		Node prev = head;
		for (Node n = head.next; n != null; n = n.next){
			if (n.data == target) {
				prev.next = n.next;
				return head;
			}
			prev = prev.next;
		}
		
		// 4. if target doesn't exist, return the same list
		return head;
		
		
	}
	
	public static Node deleteAll(Node head, int target){
		if (head == null)
			return null;
		
		if (head.data == target) {
			
		}
		return head;
	}
	
	
	public static void printLL (Node head) {
		for (Node n = head; n != null; n = n.next){
			System.out.println(n.data);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = new Node(1);
		head = addToFront(head, 3);
		head = addToFront(head, 0);
		head = addToFront(head, 8);
		
		// 8-0-3-1
		head = deleteFirstTarget(head, 1);
		//printLL(head);
		//8-3-1
		head = deleteFirstTarget(head, 8);
		//3-1
		head = deleteFirstTarget(head, 1);
		head = deleteFirstTarget(head, 3);
		head = deleteFirstTarget(head, 8);
		printLL(head);
	}
	
	

}
