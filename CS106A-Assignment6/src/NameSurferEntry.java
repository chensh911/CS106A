// Author: Shangheng Chen

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {
	private String name;
	private int[] ranks;
	/**
	 * Initialize the state of a new entry from the given line of data
	 * @param dataLine The line of data is from the names-data.txt
	 * Such as: Sam 58 69 99 131 168 236 278 380 467 408 466
	 */
	public NameSurferEntry(String dataLine) {
		Scanner token = new Scanner(dataLine);
		name = token.next();
		ranks = new int[11];
		for (int i = 0; i < 11; i += 1) {
			ranks[i] = token.nextInt();
		}
	}

	/**
	 * Return the entry’s name as it was read from the input data
	 * @return Name of the entry
	 */
	public String getName() {
		return name;
	}

	/**
	 * return the entry’s ranking for the given number
	 * @param decadesSince1900 year we want 0-10
	 * @return rank of that year
	 */
	public int getRank(int decadesSince1900) {
		if (decadesSince1900 < 0 || decadesSince1900 > 10) {
			return -1;
		}
		return ranks[decadesSince1900];
	}

	/**
	 * Return a human-readable string representation of that entry’s data
	 * @return String of the entry
	 */
	public String toString() {
		//Sam [58, 69, 99, 131, 168, 236, 278, 380, 467, 408, 466]
		String s = name + " [" + ranks[0];
		for (int i = 1; i < 11; i += 1) {
			s += (", " + ranks[i]);
		}

		return s + "]";
	}
}

