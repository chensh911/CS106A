// Author: Shangheng Chen

import java.io.*;
import java.util.*;

public class NameSurferDatabase implements NameSurferConstants {

	/**
	 * Initialize the state of a new database and
	 * read in the data from the given data filename.
	 * @param filename The filename from the source
	 */
	private HashMap <String, String> DBMS;

	public NameSurferDatabase(String filename) {
		try {
			Scanner line = new Scanner(new File(filename));
			DBMS = new HashMap<>();
			while (line.hasNext()) {
				String entry = line.nextLine();
				Scanner token = new Scanner(entry);
				String name = token.next();
				DBMS.put(name, entry);
			}
		} catch (FileNotFoundException o) {
			System.out.println("File NOT Found!!!");
		}

	}

	/**
	 * Takes a name, looks it up in the database.
	 * @param name name of the entry
	 * @return
	 */
	public NameSurferEntry findEntry(String name) {
		if (DBMS.get(name) == null) {
			return null;
		}
		return new NameSurferEntry(DBMS.get(name));
	}
}

