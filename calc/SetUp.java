package calc;

/**
 * SetUp.java
 * 
 * Class to set up and start the calculator, plus
 * facilities for test-driving the calculator.
 *
 * @author Thomas VanDrunen
 * CS 245, Wheaton College
 * June 27, 2014
*/
public class SetUp {

	/**
	 * Method for initializing the calculator internals and
	 * connecting them to the calculator face.
	 * @param face The component representing the user interface of 
	 * the calculator. 
	 */
	public static void setUpCalculator(CalculatorFace face) {

		Brain brain = new Brain(face);
				
		char[] operators = {'+', '-', '*', '/'};
		face.addPlusMinusActionListener((e) -> {brain.operator(CalculatorFace.PLUS_MINUS);});
		face.addActionListener('C', (e) -> {brain.clear();});
		face.addActionListener('.', (e) -> {brain.addDecimal();});
		
		for(int i = 0; i < operators.length; i++) {
			final char a = operators[i];
			face.addActionListener(a, (e) -> {brain.operator(a);});
		}		
		for(int i = 0; i < 10; i++) {
			final int a = i;
			face.addNumberActionListener(a, (e) -> {brain.addOperand(a);});
		}
		
		
		
		
		
		
	}
	
	
	/**
	 * This main method is for your testing of your calculator.
	 * It will *not* be used during grading. Any changes you make
	 * to this method will be ignored at grading.
	 */
	public static void main(String[] args) {
		setUpCalculator(new PlainCalculatorFace());
	}

}
