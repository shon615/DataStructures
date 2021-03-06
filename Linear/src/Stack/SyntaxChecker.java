package Stack;

public class SyntaxChecker {
	public static void main(String[] args) {
		System.out.println(parenMatch("{[]()"));
		System.out.println(parenMatch("{}[]()"));
		System.out.println(parenMatch("{}[){)"));
	}
	
	public static boolean parenMatch(String str) {
		Stack<Character> s = new Stack<Character>();
		
		for (int i = 0; i < str.length(); i++) {
			char x = str.charAt(i);
			if (x == '(' || x == '[' || x == '{') {
				s.push(x);
				continue;
			}
			else {
				if (s.isEmpty()) return false;
				
				char c = s.pop();
				
				
				if (x == ')' && c == '(' 
						|| x == ']' && c == '['
						|| x == '}' && c == '{')
					continue;
				else
					return false;
				
			}
		}
		return s.isEmpty();
	}
}
