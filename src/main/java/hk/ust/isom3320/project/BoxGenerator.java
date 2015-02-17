package hk.ust.isom3320.project;

public class BoxGenerator
	implements Runnable
{
	/* ================================ Variables ================================ */
	
	private final int numOfBoxes = 5;
	private Box boxes[];
	private int boxIndex;
	
	
	/* ================================ Constructor ================================ */
	
	public BoxGenerator()
	{
		//instantiate the boxes 
		this.boxes = new Box[numOfBoxes];
		for (int i = 0; i < numOfBoxes; i++) {
			boxes[i] = new Box();
		}
	}

	
	/* ================================ Run ================================ */
	
	public synchronized void run()
	{		
		boxIndex = 0;
		while(true)
		{
			if ((Box.numOfDisplayedBoxes < 4 && (MyGame.user1.numOfKills + MyGame.user2.numOfKills) % 10 == 0)
					|| (int)(Math.random()*90) == 0)
			{
				boxes[boxIndex].startAnimation();
				
				//return to use the 1st box if all boxes have been consumed
				if(++boxIndex >= numOfBoxes)
					boxIndex = 0;
			}
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException interruptedexception) {}
		}
	}
	
	
	/* ================================ Methods ================================ */
	
	public void startAnimation()
	{
		(new Thread(this)).start();
	}
}