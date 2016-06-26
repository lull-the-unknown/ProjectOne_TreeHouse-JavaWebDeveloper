import java.util.ArrayList;

public class Game {
	// Note: Despite being called "Game", this is the App class.
	// for actual gameplay, see the ArcadeMode class

	private Prompter mPrompt;
	private Jar mJar;
	private ArrayList<HighScore> mHighScores;
	public static final int NUM_HIGH_SCORES = 10;

	public static void main(String[] args) {
		// Note: game could be expanded to accept a filepath as an argument to save/load highscores (with the jar specs)
		
		Prompter prompt = new Prompter();
		if (!prompt.hasConsole()) {
			// Console obj not available, cannot play without Console.
			System.out.printf("No Console available!\n");
			System.exit(0);
		}
		Game app = new Game(prompt);
		app.mainMenu();
	}

	public Game(Prompter prompt) {
		mPrompt = prompt;
		mJar = null;
		mHighScores = new ArrayList<HighScore>();

		// for testing purposes only
		/*
		mJar = new Jar("eels", 20);
		/*
		mHighScores.add(new HighScore("first", 1));
		mHighScores.add(new HighScore("second", 7));
		mHighScores.add(new HighScore("third", 10));
		/**/
	}

	public void mainMenu() {
		ArrayList<String> menuOptions = new ArrayList<String>();
		int selected;

		while (true) {
			menuOptions.clear();
			if (mJar != null){
				// Bug: user will not be able to play story mode if no item entered for arcade mode
				menuOptions.add("Play Game");
			}
			if (mHighScores.size() > 0)
				menuOptions.add("High Scores");
			menuOptions.add("Admin Setup");
			menuOptions.add("Exit");

			selected = mPrompt.presentMenu("Main Menu", menuOptions);
			switch (menuOptions.get(selected)) {
			case "Play Game":
				playNewGame();
				break;
			case "High Scores":
				showHighScores();
				break;
			case "Admin Setup":
				adminSetup();
				break;
			default: // i.e. "Exit"
				return;
			}
		} // loop
	}

	private void playNewGame() {
		String[] menuOptions = { 
				"Arcade Mode: Play to beat the high score!",
				"Story Mode : Can you catch the princess before it's too late?",
				"Main Menu" };
		int gameMode = mPrompt.presentMenu("Start New Game", menuOptions);

		switch (gameMode) {
		case 0: // Arcade Mode
			ArcadeMode game1 = new ArcadeMode(mPrompt);
			game1.play(mJar, mHighScores);
			showHighScores();
			break;
		case 1: // Story Mode
			StoryMode game2 = new StoryMode(mPrompt);
			game2.play();
			break;

		default: // i.e. "Main Menu"
			break;
		}
	}

	private void showHighScores() {
		mPrompt.newScreen();
		mPrompt.print("High Scores\n");
		mPrompt.printDashes("High Scores".length());

		int numExisting = mHighScores.size();
		HighScore current;
		for (int i = 0; i < NUM_HIGH_SCORES; i++) {
			if (i < numExisting) {
				current = mHighScores.get(i);
				mPrompt.print(String.format("%2d) %s (%d)\n", i + 1,
						current.name, current.score));
			} else
				mPrompt.print(String.format("%2d)\n", i + 1));
		}
		mPrompt.waitForUser();
	}

	private void adminSetup() {
		String itemDesc;
		String title;
		int selected;
		ArrayList<String> options = new ArrayList<String>();
		options.add("");
		options.add("Clear High Scores");
		options.add("Main Menu");

		while (true) {
			if (mJar == null) {
				itemDesc = "none";
				options.set(0, "Add Item");
			} else {
				itemDesc = String.format("%s (%d)", mJar.getItemName(),
						mJar.getMaxCapacity());
				options.set(0, "Change Item");
			}
			title = String.format("Admin Setup\ncurrent item: %s", itemDesc);

			selected = mPrompt.presentMenu(title, options);

			switch (selected) {
			case 0: // Add/Change Item
				changeItem();
				break;
			case 1: // Clear High Scores
				mHighScores.clear();
				return;
			default: // i.e. "Main Menu"
				return;
			}
		}
	}

	private void changeItem() {
		mPrompt.newScreen();
		if (mJar != null)
			mPrompt.print(String.format("Last Item: %s (%d)\n\n",
					mJar.getItemName(), mJar.getMaxCapacity()));
		else
			mPrompt.print("Last Item: none\n\n");

		mPrompt.print("Please enter a new item to fill the jar with:\n");
		String name = mPrompt.readString("name(plural): ");

		mPrompt.print(String.format("\nAnd how many %s will fit in the jar?\n",
				name));
		int count = mPrompt.readInt("max(number): ");

		mJar = new Jar(name, count);
	}

} // end class