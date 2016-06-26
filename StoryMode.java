import java.util.ArrayList;

public class StoryMode {
	private Prompter mPrompt;
	private int days;

	public StoryMode(Prompter prompt) {
		mPrompt = prompt;
	}

	public void play() {
		String menuTitle = "Choose a difficulty:";
		String[] menuOptions = { "Easy", "Medium", "Hard" };
		int difficulty = mPrompt.presentMenu(menuTitle, menuOptions);
		difficulty += 3; // Easy = 3, Medium = 4, Hard = 5, there are only 5 stories available
		ArrayList<StoryPart> stories = StoryPart.getStories(difficulty);
		days = 0;

		backgroundStory();

		String name;
		int max;
		int actual;

		for (StoryPart story : stories) {
			presentStory(story.introText);

			name = story.jar.getItemName();
			max = story.jar.getMaxCapacity();
			story.jar.fill();
			actual = story.jar.getQuantity();

			mPrompt.newScreen();
			mPrompt.print(String
					.format("I have a jar here full of %s.\n", name));
			mPrompt.print(String.format("This jar can hold up to %d %s.\n",
					max, name));
			mPrompt.print("Would you like to try and guess how many it actually has?\n");
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
					userWins(story);
					showProgress();
					break;
				}
				if (score >= story.chances) {
					userLoses(story);
					showProgress();
					break;
				}
				mPrompt.print("I'm sorry but that guess was incorrect.\n");
				if (guess < actual)
					mPrompt.print("Maybe try a bit higher next time?\n");
				else
					// i.e. (guess > actual)
					mPrompt.print("Maybe try a bit lower next time?\n");
			} // loop guessing
		} // loop stories
		
		gameOver();
	}

	private void presentStory(ArrayList<String> story) {
		for (String text : story) {
			mPrompt.print(text);
			mPrompt.waitForUser(false);
		}
		mPrompt.print("\n");
	}

	private void backgroundStory() {
		ArrayList<String> story = new ArrayList<String>();
		story.add("You are a traveler in a distant land. ");
		story.add("You are trying to get to the castle. ");
		story.add("You have no money. ");
		story.add("Rumor has it that the princess of this land is a pop idol and that her ");
		story.add("autograph sells for hundreds of thousands at a popular online auction site. ");
		story.add("Rumor also has it that she will be leaving on an extended world tour in ");
		story.add("20 days, so you will need to get to the castle before then if you are to ");
		story.add("have any chance of making hundreds of thousands of.. er.. I mean if you are ");
		story.add("to have any chance of meeting her in person. ");
		story.add("You set off on your journey . . . ");

		mPrompt.newScreen();
		mPrompt.print("Press enter after each line to continue...");
		mPrompt.waitForUser(false);
		presentStory(story);
	}

	private void userForfeits() {
		ArrayList<String> story = new ArrayList<String>();
		story.add("You have chosen to give up. ");
		story.add("Naturally, that means you lose. ");
		story.add(String.format("You have spent %d days traveling. ", days));
		story.add("Without the autograph, you can't really say it was worth it. ");
		story.add("Come play again some time. ");

		mPrompt.newScreen();
		presentStory(story);
	}

	private void userWins(StoryPart story) {
		mPrompt.newScreen();
		presentStory(story.winText);
		days += story.penaltyWin;
	}

	private void userLoses(StoryPart story) {
		mPrompt.newScreen();
		presentStory(story.loseText);
		days += story.penaltyLose;
	}

	private void showProgress() {
		int daysLeft = 20 - days;

		ArrayList<String> story = new ArrayList<String>();
		story.add(String.format(
				"You have spent %d days on your journey so far. ", days));
		if (daysLeft < 0){
			if (daysLeft == -1)
				story.add("The princess left yesterday. ");
			else
				story.add(String.format("The princess left %d days ago. ", -1 * daysLeft));
		} else if (daysLeft > 0){
			if (daysLeft == 1)
				story.add("The princess will leave tomorrow. ");
			else
				story.add(String.format("The princess will leave in %d days. ", daysLeft));
		} else
			story.add("The princess is leaving today. ");
		story.add("You trudge on . . . ");

		presentStory(story);
	}
	private void gameOver(){
		ArrayList<String> story = new ArrayList<String>();
		story.add("You finally reach the castle. ");
		story.add(String.format("It took you %d days.",days));
		if (days < 20){
			story.add("Luckily for you, the rumors of the princess leaving in 20 days were true. ");
			story.add("You get her autograph and return home safely (with the same trouble ");
			story.add("it took to get there). ");
			story.add("Unfortunatley, the rumors concerning how much her autograph were worth ");
			story.add("were greatly exaggeated. Her autograph only sold for enough money to buy her ");
			story.add("latest cd. I suppose you really could have looked that up online before ");
			story.add("you left. Aw, well, better luck next time.");
		} else if (days > 20){
			story.add("Unfortunatley for you, the rumors of her leaving in 20 days were true. ");
			if (days == 19){
				story.add("She left yesterday. ");
				story.add("Yes, that's right, yesterday. ");
				story.add("You missed her by one lousy day. ");
			} else
				story.add(String.format("She left %d days ago. ", days - 20));
			story.add("You decide that with all the trouble it took to get here and with nothing ");
			story.add("to show for it when you get home, you may as well just stay here. ");
			story.add("You rumage through the trash heaps for a jar and once found, set off to ");
			story.add("find something to fill it with. ");
			story.add("You know how this land works, and you are determined to make a fortune here.");			
		} else { // days == 20
			story.add("You catch the princess just as she is leaving and get her autograph. ");
			story.add("She even offers to let you join her entourage and will drop you off at home! ");
			story.add("You make it home safely and decide to keep the autograph. ");
			story.add("You live happily ever after, never again having to guess how many items are in a jar. ");
		}
		story.add("\nGame over. ");
		story.add("Come play again sometime.\n");
		
		mPrompt.newScreen();
		presentStory(story);
	}
}
