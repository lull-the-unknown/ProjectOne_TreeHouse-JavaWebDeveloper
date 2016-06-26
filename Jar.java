import java.util.Random;

public class Jar {
	private String mItemName;
	private int mMaxCapacity;
	private int mQuantity;
	private Random mRand;
	
	public Jar( String itemName, int jarCapacity){
		mItemName = itemName;
		mMaxCapacity = jarCapacity;
		mQuantity = 0;
		mRand = new Random();
	}

	public String getItemName(){ return mItemName; }
	public int getMaxCapacity(){ return mMaxCapacity; }
	public int getQuantity(){ return mQuantity; }
	
	public void fill(){
		// Note: this does not allow zero items to be in the jar.
		//		 to get a jar with zero items, a new jar will need to be created.
		//		 (not that anybody would ever want an empty jar)
		mQuantity = mRand.nextInt(mMaxCapacity) + 1;
	}
	
	public int makeGuess(int guess){
		if (guess < mQuantity)
			return -1;
		if (guess > mQuantity)
			return 1;
		return 0;
	}
}
