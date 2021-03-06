package Stack;

public class Postfix {
	public static void main (String[] args) {
		System.out.println(postfixEvaluate("2"));
	}
	
	public static float postfixEvaluate(String expr) {
		Stack<Float> operands = new Stack<Float>();
		for (int i = 0 ; i < expr.length(); i+=2) {
			char x = expr.charAt(i);
			if (x == '*' || x == '+' || x == '/' || x == '-') {
				float a = operands.pop();
				float b = operands.pop();
				if (x == '*') operands.push(a*b);
				if (x == '/') operands.push(b/a);
				if (x == '+') operands.push(b+a);
				if (x == '-') operands.push(b-a);
				continue;
			}
			else {
				operands.push(Float.parseFloat("" + x));
			}
		}
		return operands.pop();
	}
}
