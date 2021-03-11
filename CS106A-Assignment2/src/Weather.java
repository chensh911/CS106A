// Problem 2

import acm.program.*;

public class Weather extends ConsoleProgram {
	public static final int sentinel = -1;
	public void run() {
		println("CS 106A \"Weather Master 4000\"!");
		int coldDays = 0;
		int totalDays = 0;
		int highest = -9999;
		int lowest = 9999;
		int total = 0;
		int nextDay = readInt("Next temperature (or -1 to quit)? ");
		while (nextDay != sentinel) {
			if (highest < nextDay) {
				highest = nextDay;
			}
			if (lowest > nextDay) {
				lowest = nextDay;
			}
			if (nextDay <= 50) {
				coldDays += 1;
			}
			totalDays += 1;
			total += nextDay;

			nextDay = readInt("Next temperature (or -1 to quit)? ");
		}
		if (totalDays == 0) {
			println("No temperatures were entered.");
		} else {
			println("Highest temperature = " + highest);
			println("Lowest temperature = " + lowest);
			println("Average = " + (double) total / (double) totalDays);
			println(coldDays + " cold day(s).");
		}
	}
}
