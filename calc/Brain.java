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

	public interface Operation{ int calc(int n1, int n2); }
	
	private CalculatorFace face;
	
	private int number = 0;

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
		opmap.put('+', (int n1, int n2) -> {return n1 + n2;});
		opmap.put('*', (int n1, int n2) -> {return  n1 * n2;});
		opmap.put('-', (int n1, int n2) -> {return  n1 - n2;}); 
		opmap.put('/', (int n1, int n2) -> {return  n1 / n2;}); 
		opmap.put(CalculatorFace.PLUS_MINUS, (int n1, int n2) -> {return 0-n1;}); 
	}
	
	public void addOperand(int n) { 
		number = (number * 10) + n;
		face.writeToScreen(number + ""); 
	}
	
	public void addDecimal() {
		operands.push(number);
		number = 0;
	}
	
	public void operator(char c) {	
		int answer;
		operands.push(number);
		int size = operands.size();
		if(c == CalculatorFace.PLUS_MINUS){
			if(size > 0){
				int n1 = operands.pop();
				answer = opmap.get(c).calc(n1, 0);
			}else 
			return;
		}else{
			if(size > 1){
				int n2 = operands.pop();
				int n1 = operands.pop();
				answer = opmap.get(c).calc(n1, n2);
			}else
				return;

		}
		operands.push(answer);
		face.writeToScreen(answer + "");
		number = 0;	
	}
	
	
	public void clear() {
		face.writeToScreen("");
		number = 0;
		operands.clear();
	}
	
	
	
	

}
