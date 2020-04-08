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
	
	
	private HashMap<Character, Operation> opmap;
	
	//private int answer = 0;
	
	/**
	 * Constructor
	 */
	public Brain(CalculatorFace f) { 
		face = f;
		operands = new Stack<>();
		opmap = new HashMap<>();
		opmap.put('+', this.new Plus());
		opmap.put('*', this.new Mul());
		opmap.put('-', this.new Minus());
		opmap.put('/', this.new Div());
		opmap.put(CalculatorFace.PLUS_MINUS, new PM());
	}
	
	public void addOperand(int n) { 
		operator = (operator * 10) + n;
		face.writeToScreen(operator + ""); 
	}
	
	public void addDecimal() {
		operands.push(operator);
		operator = 0;
	}
	
	public void operator(char c) {
		int answer = opmap.get(c).calc();
		operands.push(answer);
		face.writeToScreen(answer + "");
	}
	
	
	
	public void clear() {
		face.writeToScreen("");
		operator = 0;
		operands.clear();

	}
	
	public interface Operation{
		int calc();
	}
	
	public class Plus implements Operation {
		
		public Plus() {}
		
		public int calc() { return operands.pop() + operands.pop(); }
	}
	
	public class Minus implements Operation{ 
		
		public Minus() {}
		
		public int calc() { return ((0-operands.pop()) + operands.pop()); } 
		
	}
	
	public class Mul implements Operation{

		public Mul() {}
		
		public int calc() {return operands.pop() * operands.pop();}
		
	}

	public class Div implements Operation{
		
		public Div() {}
		
		public int calc() {return ((1/operands.pop()) * operands.pop()); }
		
	}

	public class PM implements Operation{

		public PM() {}
		
		public int calc() { return (0-operands.pop());}
		
	}
}
