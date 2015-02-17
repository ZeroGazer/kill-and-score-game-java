package hk.ust.isom3320.project;

public  abstract class Character
    implements Runnable
{
	CharacterJLabelContainer actor; 	
	int speed,healthPoint;
    int xcoord,ycoord;
    int row =0;
    int column =0;
    int lastRow, lastColumn;
    char lastmove;  
    int symbol;
    Background background;
    
    boolean up,down,left,right,shoot,leftweapon,rightweapon;

    //------------Tim
    
    int ammo[];
    int ammoMax[];
    boolean pauseShoot;
    boolean pauseSwitchWeapon;
    
    int currentWeapon;	//-1: No weapon; 0: Pistol; 1: Machine gun; 2: Rocket
    
    boolean isAlive;
    
    //------------Tim
   
 
    public Character(CharacterJLabelContainer actor,int xInitial, int yInitial,int speed, int healthPoint, Background background,int symbol)
    {
    	//------------Tim
    	
    	this.isAlive = false;    	
    	this.ammo = new int[3];
    	this.ammoMax = new int[3];
    	this.pauseShoot = false;
    	this.pauseSwitchWeapon = false;
    	
    	//------------Tim
    	
    	this.actor=actor;
    	
        this.xcoord = xInitial;   
        this.ycoord = yInitial;
        
        this.healthPoint =healthPoint;
        
        this.speed=speed;
        
        this.background= background;
        
        this.symbol=symbol;
      
        
  
        this.actor.up.setAllOpaque(false);
        this.actor.up.setAllVisible(false);
        this.actor.up.setAllBounds(xcoord, ycoord,this.actor.up.getWidth(), this.actor.up.getHeight() );    
       
            
        this.actor.down.setAllOpaque(false);
        this.actor.down.setAllVisible(false);
        this.actor.down.setAllBounds(xcoord, ycoord,this.actor.down.getWidth(), this.actor.down.getHeight() );    
      
        this.actor.left.setAllOpaque(false);
        this.actor.left.setAllVisible(false);
        this.actor.left.setAllBounds(xcoord, ycoord,this.actor.left.getWidth(), this.actor.left.getHeight() );    
   
        
        this.actor.right.setAllOpaque(false);
        this.actor.right.setAllVisible(false);
        this.actor.right.setAllBounds(xcoord, ycoord,this.actor.right.getWidth(), this.actor.right.getHeight() );    
   
        MyGame.addCharacterToLpane(actor,8);
     
    }
    
 

    public abstract void run();
    
    public synchronized void addLocationToMap(int x, int y)
	{
    	lastRow=row;
    	lastColumn=column;
    	row=background.getWhichRow(y);
    	column=background.getWhichColumn(x);
    	
    	
    	if (background.getEntry(lastRow, lastColumn) == symbol)
			background.modifyGrid(lastRow,lastColumn,0);
    	
    	if (background.getEntry(row, column) == 0 )    // because the actor may enter the blocked region, we can only modify the 0 of the grid[][] 
    	{

    		background.modifyGrid(row,column,symbol);   
    	}  		 	 		
     		
	}
    
    public void startWalking(char direction)
    {
    	
    	actor.setCharacterVisible(false);
    	if (direction =='u')
    	{
    	
    		actor.up.left.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    		actor.up.left.setVisible(false);
    		actor.up.right.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    			
    		
    	}
    	else if (direction =='d')
    	{
    	
    		actor.down.left.setVisible(true);
			try
            {
                Thread.sleep(60L);
            }
            catch(InterruptedException ex) { }
			actor.down.left.setVisible(false);
			actor.down.right.setVisible(true);
			try
            {
                Thread.sleep(60L);
            }
            catch(InterruptedException ex) { }
    		}
    	
    	else if (direction =='l')
    	{
    		actor.left.left.setVisible(true);
			try
            {
                Thread.sleep(60L);
            }
            catch(InterruptedException ex) { }
			actor.left.left.setVisible(false);
			actor.left.right.setVisible(true);
			try
            {
                Thread.sleep(60L);
            }
            catch(InterruptedException ex) { }
    	}
    	else if (direction =='r')
    	{
    		actor.right.left.setVisible(true);
    		
			try
            {
                Thread.sleep(60L);
            }
            
            catch(InterruptedException ex) { }
            
			actor.right.left.setVisible(false);
			actor.right.right.setVisible(true);
			
			try
            {
                Thread.sleep(60L);
            }
            catch(InterruptedException ex) { }
            
            
    	}
    }
    public void showRestState(char direction)
    {
    	actor.setCharacterVisible(false);
    	if (direction =='u')
    	{
    		actor.up.normal.setVisible(true);
    	}
    	else if (direction =='d')
    	{
    		actor.down.normal.setVisible(true);
    	}
    	else if (direction =='l')
    	{
    		actor.left.normal.setVisible(true);
    	}
    	else if (direction =='r')
    	{
    		actor.right.normal.setVisible(true);
    	}
    }




    

    public void showDie(char direction)
    {
    	
     	actor.setCharacterVisible(false);
     	
     	
     	switch (direction)
		{
		case 'u':
			
			actor.up.die.setVisible(true);
			break;
		case 'd':
			actor.down.die.setVisible(true);
			break;
		case 'l':
			actor.left.die.setVisible(true);
			break;
		case 'r':
			actor.right.die.setVisible(true);		
			break;	
		}
   }
    public void startAnimation()
    {
    	new Thread(this).start();
    }

}
