/**
  * Problem 1
  * @author Shangheng Chen
  */

import acm.program.*;

public class QuadraticEquation extends ConsoleProgram {
	public void run() {
		println("CS 106A Quadratic Solver!");
		int a = readInt("Enter a: ");
		int b = readInt("Enter b: ");
		int c = readInt("Enter c: ");
		int delta = b * b - 4 * a * c;
		if (delta < 0) {
			println("No real roots");
		} else if (delta == 0) {
			double root = (-(double)b/(2.0*(double)a));
			println("One root: " + root);
		} else {
			double root1 = ((-(double)b+Math.sqrt((double)delta))/(2.0*(double)a));
			double root2 = ((-(double)b-Math.sqrt((double)delta))/(2.0*(double)a));
			println("Two root: " + root1+ " and " + root2);
		}
	}
}
