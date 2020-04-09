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

	/**
	 *This is defines the supertype for mapping operators to their operations
	 */
	public interface Operation{ int calc(int n2, int n1); }

	/**
	 * The reference to the calculator face
	 */
	private CalculatorFace face;

	/**
	 * Keeps track of the number currently being worked on
	 */
	private int current = 0;

	/**
	 * The stack to store each operand
	 */
	private Stack<Integer> operands;

	/**
	 * Boolean flag to determine when to push current onto the stack
	 */ 
	private boolean needspush = false;
	
	/**
	 * The HashMap that maps the operators to thier operation
	 */
	private HashMap<Character, Operation> opmap;
	
	/**
	 * Constructor
	 *
	 * Initializes all necessary variables and maps each operator to an anonymous class
	 * that does its operation.
	 * @param f The reference to the calculator face
	 */
	public Brain(CalculatorFace f) { 
		face = f;
		operands = new Stack<>();
		opmap = new HashMap<>();
		opmap.put('+', (int n2, int n1) -> {return n1 + n2;});
		opmap.put('*', (int n2, int n1) -> {return  n1 * n2;});
		opmap.put('-', (int n2, int n1) -> {return  n1 - n2;}); 
		opmap.put('/', (int n2, int n1) -> {return  n1 / n2;}); 
		opmap.put(CalculatorFace.PLUS_MINUS, (int n2, int n1) -> {return 0-n2;}); 
	}

	/**
	 * When a number is pressed it will be added onto the operand and signal that
	 * it still needs to be pushed.
	 */ 
	public void addOperand(int n) { 
		current = (current * 10) + n;
		face.writeToScreen(current + "");
		needspush = true;
	}
	
	/**
	 * When the decimal is pushed, this will push the current onto the stack,
	 * and signal that it does not need to be pushed.
	 */ 
	public void addDecimal() {
		operands.push(current);
		current = 0;
		needspush = false;
	}
	
	/**
	 * When and operator is pressed, it will either push the current or not, 
	 * then depending on the size of the stack, it will do the necessary operation
	 * and store it in answer, answer is then pushed onto the top of the stack
	 * and things are reset for the next calculation
	 */
	public void operator(char c) {	
		int answer;
		if(needspush)
			operands.push(current);
		int size = operands.size();
		if(c == CalculatorFace.PLUS_MINUS){
			if(size > 0)
				answer = opmap.get(c).calc(operands.pop(),1);
			else 
			return;
		}else{
			if(size > 1){
				try{ answer = opmap.get(c).calc(operands.pop(), operands.pop()); }
				catch(ArithmeticException e){ answer = 0; }
			}else
				return;
		}
		operands.push(answer);
		face.writeToScreen(answer + "");
		current = 0;
		needspush = false;
	}
	
	/**
	 * Clears the calculator
	 */ 
	public void clear() {
		face.writeToScreen("");
		current = 0;
		operands.clear();
	}
	
	
	

}
