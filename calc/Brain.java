package calc;

import java.util.*;

/**
 * Brain.java
 * 
 * This class is the brain behind the calculator
 * 
 * @author stevenbarker
 *
 */
public class Brain {

	private CalculatorFace face;
	
	private int operator = 0;

	private Stack<Integer> operands;
	
	private Stack<Character> operators;
	
	private String toprint;
	
	private HashMap<Character, Operation> opmap;
	
	private int answer = 0;
	
	/**
	 * Constructor
	 */
	public Brain(CalculatorFace f) { 
		face = f;
		operands = new Stack<>();
		operators = new Stack<>();
		opmap = new HashMap<>();
		opmap.put('+', this.new Plus());
		opmap.put('*', this.new Mul());
		opmap.put('-', this.new Minus());
		opmap.put('/', this.new Div());
		opmap.put(CalculatorFace.PLUS_MINUS, new PM());
		}
	
	public void addOperand(int n) { 
		operator = (operator * 10) + n;
		toprint += "" + operator;
		face.writeToScreen("" + operator); 
		System.out.println(toprint);
	}
	
	public void addDecimal() {
		toprint += ".";
		face.writeToScreen(toprint);
		operands.push(operator);
		operator = 0;
		System.out.println(toprint);

	}
	
	public void addOperator(char c) {
		toprint += c;
		operators.push(c);
		face.writeToScreen(toprint);
		System.out.println(toprint);

	}
	
	public void addPlusMinus() {
		toprint += CalculatorFace.PLUS_MINUS;
		operators.push(CalculatorFace.PLUS_MINUS);
		face.writeToScreen(toprint);
		System.out.println(toprint);

	}
	
	public void calc() {
		while (!operators.empty()) 
			answer += opmap.get(operators.pop()).solve();
		clear();
		operands.push(answer);
		face.writeToScreen("" + answer);
		answer = 0;
	}
	
	public void clear() {
		toprint = "";
		face.writeToScreen(toprint);
		operator = 0;
		operands.clear();
		operators.clear();
	}
	
	public interface Operation{
		int solve();
	}
	
	public class Plus implements Operation {
		
		public Plus() {}
		
		public int solve() { return operands.pop() + operands.pop(); }
	}
	
	public class Minus implements Operation{ 
		
		public Minus() {}
		
		public int solve() { return operands.pop() - operands.pop(); } 
		
	}
	
	public class Mul implements Operation{

		public Mul() {}
		
		public int solve() {return operands.pop() * operands.pop();}
		
	}

	public class Div implements Operation{

		public Div() {}
		
		public int solve() {return operands.pop() * operands.pop(); }
		
	}

	public class PM implements Operation{

		public PM() {}
		
		public int solve() { return (0-operands.pop());}
		
	}
}
