package timeTracker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SessionKeeper {
	public static void saveSession(String filename, Work root) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(filename);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(root);
		out.close();
		fileOut.close();
	}
	public static Work loadSession(String filename) throws IOException {
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Work root = (Work)in.readObject();
			in.close();
			fileIn.close();
			return root;
		} catch (ClassNotFoundException e) {
			Logging.getLogger().error("Couldn't load saved data (unexisting class)");
			return null;
		}
	}
}
