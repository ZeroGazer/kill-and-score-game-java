package hk.ust.isom3320.project;

public class ZombieGenerator
    implements Runnable
{
	ZombieJLabelContainer[] zombieJLabelContainers,bossJLabelContainers;
    Background background;
    
    static Zombie[] zombies;
    static Zombie[] bosses; 
    
    Hero user1, user2;
    
    int xcoord;
    int ycoord;

    int index;
    
    char direction;
    
    public ZombieGenerator( ZombieJLabelContainer[] zombieJLabelContainers,ZombieJLabelContainer[] bossJLabelContainers,
    		Background background, Hero user1, Hero user2)
    {
    	this.user1 = user1;
    	this.user2 = user2;
    	this.zombieJLabelContainers=zombieJLabelContainers;
    	this.bossJLabelContainers=bossJLabelContainers;
    	this.background=background;
    	
    	zombies= new Zombie[20]; // max 20 zombies
    	bosses= new Zombie[15]; // max 15 zombies
    	
    	for (int i = 0; i < zombies.length; i++) {
    		determineDirection();
    		zombies[i] = new Zombie(zombieJLabelContainers[i],xcoord,ycoord,5,100,5,background,9 , user1, user2,direction);
    	}
    	
    	for (int i = 0; i < bosses.length; i++) {
    		determineDirection();
    		bosses[i] = new Zombie(bossJLabelContainers[i],xcoord,ycoord,5,200,7,background,9 , user1, user2,direction);
    	}
    }

    public synchronized void run()
    {
    	int level=0;
    	while (user1.isAlive || user2.isAlive) 
    	{
    		if ( user1.numOfKills + user2.numOfKills >= (2*level) )
    		{
    			for (int i = 0, numOfNewZombie = 0; numOfNewZombie < 3; i++) {
    				if (!zombies[i].isAlive) {
    					zombies[i].isAlive = true;
    					zombies[i].startAnimation();
    					numOfNewZombie++;
    				}
    			}
    			
    			for (int i = 0, numOfNewBoss = 0; numOfNewBoss < 2; i++) {
    				if (!bosses[i].isAlive) {
    					bosses[i].isAlive = true;
    					bosses[i].startAnimation();
    					numOfNewBoss++;
    				}
    			}
    				
    			level++;
    		}
    		try
    		{
    			Thread.sleep(6000);
    		}
    		catch (InterruptedException ex) {}
    	}
    	MyGame.printGameOver(MyGame.score1);
    }
    
    private void determineDirection() {
    	int choice = (int)(Math.random()*4);
    	int xTemp = (int)(Math.random()*500);
    	int yTemp = (int)(Math.random()*400);
    	
    	switch (choice) {
    	case 0:
    		this.direction = 'u';
    		this.xcoord = xTemp;
    		this.ycoord = 500;
    		break;
    	case 1:
    		this.direction = 'd';
    		this.xcoord = xTemp;
    		this.ycoord = -20;
    		break;
    	case 2:
    		this.direction = 'l';
    		this.xcoord = 670;
    		this.ycoord = yTemp;
    		break;
    	case 3:
    		this.direction = 'r';
    		this.xcoord = -10;
    		this.ycoord = yTemp;
    		break;
    	}
    }

    public void generateZombies(int x,int y)
    {
        zombies[index] = new Zombie(zombieJLabelContainers[index],x,y,5,100,5,background,9 , user1, user2,direction); 
        zombies[index].isAlive=true;
        zombies[index].startAnimation();
        
        index++;
        if(index >= 8)
        {
        	index= 0;
        }
    }
    public void generateBosses(int x,int y)
    {
        zombies[index] = new Zombie(bossJLabelContainers[index],x,y,10,200,7,background,9 , user1, user2,direction); 
        zombies[index].isAlive=true;
        zombies[index].startAnimation();
        
        index++;
        if(index >= 2)
        {
        	index= 0;
        }
    }
    
    public void startAnimation()
    {
        (new Thread(this) ).start();
	}

}
