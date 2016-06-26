import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Encapsulates i/o for the Game. Does not do the prompting itself. Would be
 * better named "Presenter" or "Screen"
 * 
 * @author David
 *
 */
public class Prompter {
	private Console mConsole;

	public Prompter() {
		mConsole = System.console();
	}

	public boolean hasConsole() {
		return (mConsole != null);
	}

	public void newScreen() {
		// Console cannot clear the screen, so print a separator
		// to show start of new section.
		mConsole.printf("\n========================================\n\n");
	}

	public void printDashes(int count) {
		char[] result = new char[count];
		Arrays.fill(result, '-');
		mConsole.printf("%s\n", new String(result));
	}

	public int presentMenu(String menuTitle, ArrayList<String> menuOptions) {
		String[] options = new String[menuOptions.size()];
		menuOptions.toArray(options);
		return presentMenu(menuTitle, options);
	}

	public int presentMenu(String menuTitle, String[] menuOptions) {
		// returns the index of the option chosen by user from the array of
		// options passed in.
		int optionChosen;
		int numOptions = menuOptions.length;

		newScreen();
		mConsole.printf("%s\n", menuTitle);
		printDashes(menuTitle.length());
		for (int i = 0; i < numOptions; i++) {
			mConsole.printf("%2s) %s\n", i + 1, menuOptions[i]);
		}
		mConsole.printf("\n");

		String msg = String.format("Please select an option(1-%d): ",
				numOptions);

		while (true) {
			optionChosen = readInt(msg);
			if (optionChosen < 1)
				continue;
			if (optionChosen > numOptions)
				continue;
			return optionChosen - 1;
		} // loop
	}

	public void print(String msg) {
		mConsole.printf(msg);
	}

	public void waitForUser() {
		waitForUser(true);
	}

	public void waitForUser(boolean showPrompt) {
		if (showPrompt)
			mConsole.printf("\n(press [Enter] to continue)");
		mConsole.readLine();
	}

	public String readString(String msg) {
		String result;
		do {
			result = mConsole.readLine(msg);
		} while (result.length() < 1);
		return result;
	}

	public int readInt(String msg) {
		String result;
		while (true) {
			result = mConsole.readLine(msg);
			try {
				return Integer.parseInt(result);
			} catch (Throwable ex) {

			}
		}
	}
}
