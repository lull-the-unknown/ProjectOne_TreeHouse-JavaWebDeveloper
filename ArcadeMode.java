import java.util.ArrayList;

public class ArcadeMode {
	private Prompter mPrompt;
	private Jar mJar;
	private ArrayList<HighScore> mHighScores;

	public ArcadeMode(Prompter prompt) {
		mPrompt = prompt;
	}

	public void play(Jar jar, ArrayList<HighScore> highScores) {
		mJar = jar;
		mHighScores = highScores;
	
		playNewGame();

		mJar = null;
		mHighScores = null;
	}

	private void playNewGame() {
		String menuTitle = "Would you like for me to give you hints along the way?";
		String[] menuOptions = { "Yes, tell me if I am too high or too low.",
				"No, I'll just flail about wildly in the dark this time around." };
		boolean giveHints = (0 == mPrompt.presentMenu(menuTitle, menuOptions));

		String name = mJar.getItemName();
		int max = mJar.getMaxCapacity();
		mJar.fill();
		int actual = mJar.getQuantity();

		mPrompt.newScreen();
		mPrompt.print(String.format("I have a jar here full of %s.\n", name));
		mPrompt.print(String.format("This jar can hold up to %d %s.\n", max,
				name));
		mPrompt.print("Would you like to try and guess how many it actually has?\n");
		mPrompt.print("Will you get the high score?\n");
		mPrompt.print("(enter -1 at any time to quit)\n\n");

		int guess;
		int score = 0;
		String msg = String.format("Enter a guess (1-%d):", max);

		while (true) {
			while (true) {
				guess = mPrompt.readInt(msg);
				if (guess == 0) //Note: negative numbers are allowed for quit, but not zero
					continue;
				if (guess > max)
					continue;
				break;
			}
			score++;
			mPrompt.print("\n");
			
			if (guess < 0) {
				userForfeits();
				return;
			}
			if (guess == actual) {
				userWins(score);
				return;
			}
			if (guess < actual) {
				// too low
				boolean withinTwentyPercent = guess >= (int) (actual * 0.8);
				if (withinTwentyPercent)
					mPrompt.print("Aw, so close. ");

				mPrompt.print("I'm sorry but that guess was incorrect.\n");
				if (giveHints)
					mPrompt.print("Maybe try a bit higher next time?\n");
				
			} else { // i.e. (guess > actual)
				// too high
				boolean withinTwentyPercent = guess <= (int) (actual * 1.2);
				if (withinTwentyPercent)
					mPrompt.print("Aw, so close. ");

				mPrompt.print("I'm sorry but that guess was incorrect.\n");
				if (giveHints)
					mPrompt.print("Maybe try a bit lower next time?\n");
			}
		} // loop
	}

	private void userWins(int score) {
		mPrompt.print("Yes! That's it! You got it!\n");
		mPrompt.print(String.format("There are %d %s in this jar.\n",
				mJar.getQuantity(), mJar.getItemName()));
		mPrompt.print(String.format("You guessed it in %d tries.\n", score));
		mPrompt.waitForUser();
		mPrompt.print("\n");

		int rank = rankScore(score);
		if (rank >= Game.NUM_HIGH_SCORES) {
			// not good enough to make list
			mPrompt.print("I'm sorry but you didn't score low enough to make the list of \"High Scores\".\n");
			mPrompt.print(String.format(
					"You'll need to guess it in less than %d tries for that.\n",
					mHighScores.get(Game.NUM_HIGH_SCORES - 1).score));

			mPrompt.waitForUser();
		} else {
			// made high scores list
			mPrompt.print("Congratulations, you scored well enough to get your name on the list of High Scores!\n");
			String userName = mPrompt.readString("Please enter your name: ");
			mPrompt.print(String.format(
					"\nThank you, %s. You are now ranked number %d on the leaderboard.\n",
					userName, rank + 1));
			mHighScores.add(rank, new HighScore(userName, score));
			if (mHighScores.size() > Game.NUM_HIGH_SCORES)
				mHighScores.remove(mHighScores.size() - 1);

			mPrompt.waitForUser();
		}
	}

	private void userForfeits() {
		mPrompt.print("Are you leaving so soon?\n");
		mPrompt.print("Oh, too bad.\n");
		mPrompt.print(String.format("There were %d %s in the jar.\n",
				mJar.getQuantity(), mJar.getItemName()));
		mPrompt.print("Come play again sometime.\n");
		mPrompt.waitForUser();
	}

	private int rankScore(int score) {
		int numScores = mHighScores.size();
		for (int i = 0; i < numScores; i++) {
			if (score < mHighScores.get(i).score)
				return i;
		}
		return numScores;
	}
}
