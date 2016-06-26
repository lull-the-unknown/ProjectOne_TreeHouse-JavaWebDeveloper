import java.util.ArrayList;
import java.util.Random;

public class StoryPart {
	public Jar jar;
	public int chances;
	public int penaltyWin;
	public int penaltyLose;
	public ArrayList<String> introText;
	public ArrayList<String> winText;
	public ArrayList<String> loseText;

	private static Random rand = new Random();
	private static final int NUM_STORIES = 5;

	public StoryPart() {
		introText = new ArrayList<String>();
		winText = new ArrayList<String>();
		loseText = new ArrayList<String>();
	}

	public static ArrayList<StoryPart> getStories(int num) {
		if (num > NUM_STORIES)
			num = NUM_STORIES;
		if (num < 1)
			num = 1;

		int randomId;
		ArrayList<Integer> selectedIds = new ArrayList<Integer>();

		while (selectedIds.size() < num) {
			randomId = rand.nextInt(NUM_STORIES);
			if (selectedIds.contains(randomId))
				continue;
			selectedIds.add(randomId);
		}

		ArrayList<StoryPart> results = new ArrayList<StoryPart>();
		for (int id : selectedIds) {
			results.add(getOne(id));
		}
		return results;
	}

	public static StoryPart getOne(int id) {
		StoryPart result = new StoryPart();
		switch (id) {
		case 0:
			result.jar = new Jar("pickled eggs", 20);
			result.chances = 5;
			result.penaltyWin = 4;
			result.penaltyLose = 7;
			result.introText.add("You arrive at an inn. You still have no money. ");
			result.introText.add("The innkeeper, feeling sorry for you, offers to play a game with you. ");
			result.introText.add("\"Tell you what, sonny,\" he offers. \"If you can guess how many ");
			result.introText.add("pickled eggs I have in this here jar, I'll give you a bit of food ");
			result.introText.add("to carry you over. What do you say?\"");

			result.winText.add("\"Well I'll be!\" The innkeeper says. \"That was pretty good. Here's some ");
			result.winText.add("food for you.\" ");
			result.winText.add("The innkeeper hands you some food and you are rejuvenated. You feel that you ");
			result.winText.add("can carry on with your journey now and set off at once.");

			result.loseText.add("\"Yeah, yeah, that's enough. I don't think your heart was really into ");
			result.loseText.add("the game anyways. Off with you, get! Get!\" ");
			result.loseText.add("You failed to guess the number of pickled eggs before the innkeeper got bored. ");
			result.loseText.add("You must carry on your journey now, but you are hungry and tired and are ");
			result.loseText.add("moving rather sluggishly.");
			break;

		case 1:
			result.jar = new Jar("coins", 20);
			result.chances = 5;
			result.penaltyWin = 3;
			result.penaltyLose = 7;
			result.introText.add("You arrive at an inn. You still have no money, but you are so hungry that you ");
			result.introText.add("order something to eat and hope for the best. ");
			result.introText.add("When it comes time to pay the bill you explain your situation to the waitress and, ");
			result.introText.add("with a twinkle of mischief in her eye, she says,\"Tell you what. Guess how many coins ");
			result.introText.add("I have in my tip jar and I'll let you run off without paying. What do you say?\"");

			result.winText.add("\"All right! You're pretty good.\" She exclaims with delight. \"Run off now, while the ");
			result.winText.add("innkeeper is busy. Anyone asks, I'll just tell them I never heard of you.\" ");

			result.loseText.add("\"Uh.. yeah, you can stop.\" She blurts out in disgust. \"Off to the kitchen with you, ");
			result.loseText.add("you can pay off your bill scrubbing pots.\" ");
			result.loseText.add("She puts you right to work and after she gets through with you, you are several days later ");
			result.loseText.add("than when you started. Will you ever reach the castle!?");
			break;

		case 2:
			result.jar = new Jar("lentils", 20);
			result.chances = 5;
			result.penaltyWin = 3;
			result.penaltyLose = 7;
			result.introText.add("You come across a traveling merchant. Seeing the poor state of your clothes and your ");
			result.introText.add("person in general, he offers to sell you a map that will allow you to cut out a large ");
			result.introText.add("portion of your journey. You tell him you have no money and he decides that your need ");
			result.introText.add("is so dire he would be willing to part with it for a bit of amusement. ");
			result.introText.add("\"Traveling in these parts can get mighty dull at times\", he explains. \"Try and guess ");
			result.introText.add("how many lentils I have in this jar, and I'll see what I can do. What do you say?\"");

			result.winText.add("\"Yep, yep, pretty good. All right, that was fun enough (as things go around here). Here's ");
			result.winText.add("the map. Look me up sometime if you ever get some money and we'll do some real business.\" ");
			result.winText.add("He gives you the map. It's easy enough to follow and, as advertised, you are able to cut out ");
			result.winText.add("a large portion of your journey, saving you several days of walking.");

			result.loseText.add("\"Well.. yeah.. you see.. no. That's not even close. Look, I was more entertained watching my ");
			result.loseText.add("donkey's head bob up and down on the road than I am playing this game. You're not a very fun ");
			result.loseText.add("person to be around. Look, I'll give you the map, just never come near me again. Deal?\" ");
			result.loseText.add("He pulls out from behind him a map and hands it to you. It doesn't look like the same map he ");
			result.loseText.add("held up earlier; the paper is yellower and a bit more tattered at the edges. But then, a free ");
			result.loseText.add("map is a free map, and a free map of a shortcut is even better. You set off at once. ");
			result.loseText.add("After a few days, you are completely lost and decide that the traveling merchant and his ");
			result.loseText.add("\"...never come near me again. Deal?\" attitude intentionally gave you a bad map to get you lost. ");
			result.loseText.add("It takes days to get back on track, and then days more to cover the ground you were supposed to ");
			result.loseText.add("have \"cut out\" with his stupid map.");
			break;

		case 3:
			result.jar = new Jar("fireflies", 20);
			result.chances = 5;
			result.penaltyWin = 2;
			result.penaltyLose = 7;
			result.introText.add("You come to a fork in the road. Being a self-proclaimed master of divination, you go left. ");
			result.introText.add("After half a day the road ends abruptly before a small lake. When you approach, the lake starts ");
			result.introText.add("to glow softly and a fairy appears hovering above it. \"Welcome traveler! You have reached ");
			result.introText.add("The Lake of the Fairy. Not many people come here, actually nobody ever comes here. You must be lost.\" ");
			result.introText.add("You admit that you may have taken a wrong turn at a fork earlier and you ask her for directions, ");
			result.introText.add("she seems nice enough. \"What!?\" she exclaims, \"You're leaving already? But you just got here. ");
			result.introText.add("At least play one little game with me before you go. If you win, I'll.. I'll.. I know, I'll help you ");
			result.introText.add("get back to your path. And even save you some time at that! What do you say?\"");

			result.winText.add("\"Yay!!! You got it!\" she giggles, as fairies are known to do, \"Okay, off we go!\" ");
			result.winText.add("You are instantly transported back to your road and soon come to a village by which you learn that ");
			result.winText.add("The Lake of the Fairy is several days away and she really did save you quite a bit of walking.");

			result.loseText.add("\"Boo! Boo! Are you even trying? Go away now, you're no fun.\" ");
			result.loseText.add("The fairy disappears and the lake is no longer glowing. The only thing for you to do is spend the ");
			result.loseText.add("rest of the day backtracking to the fork where you dropped your \"self-proclaimed master of divination\" badge.");
			break;

		case 4:
			result.jar = new Jar("what you can only assume are peeled grapes",
					20);
			result.chances = 5;
			result.penaltyWin = 7;
			result.penaltyLose = 4;
			result.introText.add("You come to a bridge and a troll pops out. \"Halt!\", he shouts, \"If you want to pass, you must ");
			result.introText.add("beat my game.\" He is holding a jar of what you can only assume to be peeled grapes. Being that ");
			result.introText.add("guessing how many items a person has in a jar is kind of a big deal in this land, you start guessing.");

			result.winText.add("\"All right. Fine. You can pass.\" He tucks the jar away under his cloak and steps aside. You pass the troll ");
			result.winText.add("and as you are crossing the bridge with your back to him, he attacks you. You manage to struggle free ");
			result.winText.add("but it takes days to lose him in the forest on the other side. When it's over, you've lost more time than ");
			result.winText.add("it would have taken to go around. Such is the joys of winning a game against a troll.");

			result.loseText.add("\"Ugh!\" The troll rolls his eyes for the last time and draws a knife. \"You lose.\" he snarls as he tucks ");
			result.loseText.add("the jar away under his coat. Being a quick thinker, you point off to the side with an expression of surprise ");
			result.loseText.add("and yell, \"What's that! Quick! Look over there!\" The troll turns to look where you are pointing and as ");
			result.loseText.add("he is distracted you sneak past him, running across the bridge and far beyond.");
			break;

		default:
			result.jar = new Jar("", 20);
			result.chances = 5;
			result.penaltyWin = 3;
			result.penaltyLose = 5;
			result.introText.add("");
			result.winText.add("");
			result.loseText.add("");
			break;
		}
		return result;
	}
}
