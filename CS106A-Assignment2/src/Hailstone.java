// Problem 3

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		println("This program computes Hailstone sequences.");
		boolean yes = true;
		do {
			println();
			int number = readInt("Enter a number: ");
			process(number, 0);
			yes = readBoolean("Run again? ", "y", "n");
		} while (yes);
		println("Thanks for using Hailstone.");
	}

	public void process(int x, int cnt) {
		if (x == 1) {
			println("It took " + cnt +" steps to reach 1.");
		} else if (x % 2 == 0){
			println(x + " is even, so I take half: " + x/2);
			process(x/2, cnt+1);
		} else {
			println(x + " is odd, so I make 3n + 1: " + (3*x+1));
			process(3*x+1, cnt+1);
		}
	}
}
