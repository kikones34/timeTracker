package timetracker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import utilities.Logging;

// Provides the functionality to save/load a session to/from a file.
public final class SessionKeeper {
	
	private SessionKeeper() {
	}
	
	public static void saveSession(
			final String filename, final Work root) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(filename);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(root);
		out.close();
		fileOut.close();
	}
	
	// Loads the session saved to a file.
	// Returns null if the file can be opened and read,
	// but the data can't be loaded.
	public static Work loadSession(final String filename) throws IOException {
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Work root = (Work) in.readObject();
			in.close();
			fileIn.close();
			return root;
		} catch (ClassNotFoundException e) {
			Logging.getLogger().error(
					"Couldn't load saved data (unexisting class)");
			return null;
		}
	}
}
