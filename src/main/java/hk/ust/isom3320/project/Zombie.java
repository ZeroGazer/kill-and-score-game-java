package hk.ust.isom3320.project;

import java.util.LinkedList;

class Node
{
	public int row,column;
	public Node ()
	{	
	}
	public Node(int row, int column)
	{
		this.row=row;
		this.column=column;
	}	
	public boolean isEqual(Node input)
	{
		if (this.row == input.row)
		{
			if (this.column == input.column)
				return true;
		}
		return false;
	}
}

public class Zombie extends Character
{
	ZombieJLabelContainer actor;
	
	Node myLocation,user1Location,user2Location,targetLocation;
	int xInitial, yInitial;

	Character targetUser; // It want to trace and attack one user only at a instant

	Character user1,user2;
	
	int[][] map1,map2;  
	int[][] map; // 10 x 14 array contains 0 and 2 only , the composition of 2 forms the shortest path
	int[][] visit;
	int[][] parent;
	
	int attack;
	int count;
	
	char initialDirection;
	
	public Zombie(ZombieJLabelContainer actor,int xInitial, int yInitial,int speed, int healthPoint, int attack,Background background, int symbol,
			Character user1, Character user2 , char initialDirection)
	{
			
		super(actor, xInitial, yInitial, speed, healthPoint, background,symbol);
		this.actor=(ZombieJLabelContainer) super.actor;
		this.attack=attack;
		this.currentWeapon = -1; // Tim   // indicate no weapon
		this.initialDirection=initialDirection;
		//
        this.actor.upAttack.setAllOpaque(false);
        this.actor.upAttack.setAllVisible(false);
        this.actor.upAttack.setAllBounds(xcoord, ycoord,this.actor.up.getWidth(), this.actor.up.getHeight() );    
       
            
        this.actor.downAttack.setAllOpaque(false);
        this.actor.downAttack.setAllVisible(false);
        this.actor.downAttack.setAllBounds(xcoord, ycoord,this.actor.down.getWidth(), this.actor.down.getHeight() );    
      
        this.actor.leftAttack.setAllOpaque(false);
        this.actor.leftAttack.setAllVisible(false);
        this.actor.leftAttack.setAllBounds(xcoord, ycoord,this.actor.left.getWidth(), this.actor.left.getHeight() );    
   
        
        this.actor.rightAttack.setAllOpaque(false);
        this.actor.rightAttack.setAllVisible(false);
        this.actor.rightAttack.setAllBounds(xcoord, ycoord,this.actor.right.getWidth(), this.actor.right.getHeight() );  
        //
        MyGame.addATKToLpane(actor,8);
     
		up=false;
		down=false;
		left=false;
		right=false;
		shoot=false;
		
		myLocation = new Node(xInitial,yInitial);
		user1Location = new Node();	
		user2Location = new Node();
		
		this.xInitial = xInitial;
		this.yInitial = yInitial;
		
		map1=new int [10][14];	
		map2=new int [10][14];
		
		this.user1=user1;
		this.user2=user2;
	}

	
	public synchronized int updateMap1()
	{
		count=0;
		visit= Background.copy2DIntArray(background.grid); 
		
		for(int i = 0; i < 10; i++) 
		{ 
			for(int j = 0; j < 14; j++)  
				if ( (visit[i][j] == 7) || (visit[i][j] == 8) )
					visit[i][j]=0;     	// original , there is a 7 ,which is represent the location of the user, in the grid, in need to change back to 0 
										//that represent visit able
		}
		parent=new int[background.grid.length][background.grid[0].length]; // all the entries are zero
		
		updateUser1Location();
		updateMyLocation();
		
		search(myLocation,user1Location,visit,parent);

		int temp,tempRow,tempColumn;
		
		int currentRow=user1Location.row;
		int currentColumn=user1Location.column;
		
		//System.out.println("User: " + currentRow+","+currentColumn);
		
		try
		{
		while (parent[currentRow][currentColumn] != -1)  // search from end to start   
		{												//-1 represent the start node has no "parent"
			temp=parent[currentRow][currentColumn];
			tempRow=currentRow;	
			tempColumn=currentColumn;
		
			currentRow=temp/parent[0].length; // parent[0].length is number of column
			currentColumn = temp-(currentRow*parent[0].length);
			
			parent[tempRow][tempColumn]=-2;         // -2 represent the path 
	
		}
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			//System.out.println("User: " + currentRow+","+currentColumn);
		}
		
		for(int i = 0; i < parent.length; i++) 
		{ 
			for(int j = 0; j < parent[0].length; j++) 
					if (parent[i][j] !=-2 && parent[i][j] !=-1)
						parent[i][j]=0;
					else
					{
						parent[i][j]=2;    // I like 2 more than-2 
						count++;
					}
			
		}
		map1=parent;
		/*
    	for(int i = 0; i < parent.length; i++) 
		{ 
			for(int j = 0; j < parent[0].length; j++) 
				System.out.print(parent[i][j]+"   ");
			System.out.println(); 
		}
    	System.out.println(); 
    	*/
		
		return count;
	
	}
	
	public synchronized int updateMap2()
	{
		count=0;
		visit= Background.copy2DIntArray(background.grid); 
		
		for(int i = 0; i < 10; i++) 
		{ 
			for(int j = 0; j < 14; j++)  
				if ( (visit[i][j] == 7) || (visit[i][j] == 8) )
					visit[i][j]=0;     	// original , there is a 8 ,which is represent the location of the user, in the grid, in need to change back to 0 
										//that represent visit able
		}
		parent=new int[background.grid.length][background.grid[0].length]; // all the entries are zero
		
		updateUser2Location();
		updateMyLocation();
	
		search(myLocation,user2Location,visit,parent);
		
		int temp,tempRow,tempColumn;
		
		int currentRow=user2Location.row;
		int currentColumn=user2Location.column;
		
		try
		{
		while (parent[currentRow][currentColumn] != -1)  // search from end to start   
		{												//-1 represent the start node has no "parent"
			temp=parent[currentRow][currentColumn];
			tempRow=currentRow;	
			tempColumn=currentColumn;
			
			
			currentRow=temp/parent[0].length; // parent[0].length is number of column
			currentColumn = temp-(currentRow*parent[0].length);
			
			parent[tempRow][tempColumn]=-2;         // -2 represent the path 
	
		}
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
		}
	
		for(int i = 0; i < parent.length; i++) 
		{ 
			for(int j = 0; j < parent[0].length; j++) 
					if (parent[i][j] !=-2 && parent[i][j] !=-1)
						parent[i][j]=0;
					else
					{
						parent[i][j]=2;
						count++;
					}			
		}
		map2=parent;
		return count;
	
	}
	
	public void chooseTarget()
	{
		int lengthOfPathOfMap1 = updateMap1();
		int lengthOfPathOfMap2 = updateMap2();
	
		if ( user1.isAlive && user2.isAlive )
		{
			if (lengthOfPathOfMap1 == lengthOfPathOfMap2 )
			{
				targetLocation=user1Location;
		    	targetUser=user1;
		    	map=map1;
			}
			else if (lengthOfPathOfMap1 < lengthOfPathOfMap2)
			{
				targetLocation=user1Location;
		    	targetUser=user1;
		    	map=map1;
			}
			else if (lengthOfPathOfMap1 > lengthOfPathOfMap2)
			{
				targetLocation=user2Location;
		    	targetUser=user2;
		    	map=map2;
			}
		}
		else if  (( user1.isAlive) && (!user2.isAlive) )
		{
			targetLocation=user1Location;
	    	targetUser=user1;
	    	map=map1;
		}
		else if  ( (!user1.isAlive) && user2.isAlive )
		{
			targetLocation=user2Location;
	    	targetUser=user2;
	    	map=map2;
		}
		else if  ( (!user1.isAlive) && (!user2.isAlive) )
		{
			targetLocation=user1Location;
	    	targetUser=user1;
	    	map=map1;
	    	
	    	//System.out.println("shit");
		}
			

	}
	
	public synchronized void updateUser1Location()
	{
		for (int i=0; i< 10; i++)
		{
			for (int j=0; j < 14; j++)
			{		
				if ( background.getEntry(i, j) == 7) // 7 represent the user 1
				{
					user1Location.row=i;
					user1Location.column=j;
					break;
				}
					
			}
			
		}
	}
	public synchronized void updateUser2Location()
	{
		for (int i=0; i< 10; i++)
		{
			for (int j=0; j < 14; j++)
			{		
				if ( background.getEntry(i, j) == 8) // 8 represent the user 2
				{
					user2Location.row=i;
					user2Location.column=j;
					break;
				}
					
			}
			
		}
	}
	
	public synchronized void updateMyLocation()
	{
		myLocation.row=background.getWhichRow(ycoord);
		myLocation.column=background.getWhichColumn(xcoord);
	}
	

	public void walkInShortestPath()  // it will "press" one and only one direction button 
	{
	
			
			try 
			{
				if (map[myLocation.row-1][myLocation.column] == 2   )   // looking up to find the next step
					up=true;	
			}
			catch (ArrayIndexOutOfBoundsException ex) 
			{
		
			}
		
			try 
			{		
				if (map[myLocation.row+1][myLocation.column] == 2   )	// looking down to find the next step
					down=true;		
			}
			catch (ArrayIndexOutOfBoundsException ex) 
			{
				
			}
			try 
			{			
				if (map[myLocation.row][myLocation.column-1] == 2   )	// looking left to find the next step
					left=true;
			}
			catch (ArrayIndexOutOfBoundsException ex) 
			{
			
			}
			try 
			{				
				if (map[myLocation.row][myLocation.column+1] == 2   )	// looking right to find the next step
					right=true;
			}
			catch (ArrayIndexOutOfBoundsException ex) 
			{		
			}

	}
	
	
	public void initialMovement(char direction)
	{
		  
		for(int i = 0; i < 10; i++)
	        {	
			    switch(direction) 
			    { 
	  
	            case 'u': 
	            	ycoord=ycoord-5;
					startWalking('u');
	                break; 
	            case 'd': 
	            	ycoord=ycoord+5;
					startWalking('d'); 
	                break; 
	            case 'l': 
	            	xcoord=xcoord-5;
					startWalking('l');
	                break; 
	            case 'r': 
	            	xcoord=xcoord+5;
					startWalking('r');
	                break; 
	     
			    }
				
				
				actor.setCharacterLocation(xcoord, ycoord);  	
	            try
	            {
	                Thread.sleep(10L);
	            }
	            catch(InterruptedException ex) { }
	        }

	}
	
	public void attackMotion (char direction)
	{
	
	   	actor.setCharacterVisible(false);
	   	
    	if (direction =='u')
    	{
    		
    		actor.upAttack.atk1.setVisible(true);
    	
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    		actor.upAttack.atk1.setVisible(false);
    		actor.upAttack.atk2.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    		actor.upAttack.atk2.setVisible(false);
    		actor.upAttack.atk3.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    		actor.upAttack.atk3.setVisible(false);
    		actor.upAttack.atk4.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    			
    		
    	}
    	else if (direction =='d')
    	{
    		actor.downAttack.atk1.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    		actor.downAttack.atk1.setVisible(false);
    		actor.downAttack.atk2.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    		actor.downAttack.atk2.setVisible(false);
    		actor.downAttack.atk3.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    		actor.downAttack.atk3.setVisible(false);
    		actor.downAttack.atk4.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    	}
    	
    	else if (direction =='l')
    	{
    		actor.leftAttack.atk1.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    		actor.leftAttack.atk1.setVisible(false);
    		actor.leftAttack.atk2.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    		actor.leftAttack.atk2.setVisible(false);
    		actor.leftAttack.atk3.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    		actor.leftAttack.atk3.setVisible(false);
    		actor.leftAttack.atk4.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    	}
    	else if (direction =='r')
    	{
    		actor.rightAttack.atk1.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    		actor.rightAttack.atk1.setVisible(false);
    		actor.rightAttack.atk2.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    		actor.rightAttack.atk2.setVisible(false);
    		actor.rightAttack.atk3.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
    		actor.rightAttack.atk3.setVisible(false);
    		actor.rightAttack.atk4.setVisible(true);
    		try
    		{
    			Thread.sleep(60L);
    		}
    		catch(InterruptedException ex) { }
       
    	}
    	
    	
	}
	
    public synchronized void run()
    {    	
    	lastmove =initialDirection;
    	initialMovement(initialDirection);
    	
    	
    	int remainPixel;
    	int movePixel;
	
        while ( isAlive )
        {
        
        	up=false;   // release the botton
    		down=false;
    		left=false;
    		right=false;
    		shoot=false;
    		
    		movePixel=speed;
    		addLocationToMap(xcoord,ycoord);
    		
    		chooseTarget();  	// include update map1 and map2
        	walkInShortestPath();
        
        	if ( targetLocation.isEqual(myLocation) ) // user and the zombie are in the same grid
        	{
        		
        	    try
                {
                    Thread.sleep(100L);
                }
                catch(InterruptedException ex) { }
        		
        	    if ( targetLocation.isEqual(myLocation) ) // the user stay too long near the zombie
        	    {
        	    	attackMotion (lastmove);
        	    	MyGame.displayBlood(targetUser);
        	    	targetUser.healthPoint=targetUser.healthPoint-attack;
        	    	
        	    }
        		
        	}
        		
 	
            if(up)
            {
            	remainPixel=ycoord-0;
            	if ( speed > remainPixel)
            		movePixel=remainPixel;
            	
            	ycoord -= movePixel;
            	
            	  if ( !background.isMoveAvailable(xcoord,ycoord) )  // to ensure it does not pass through barrier
                  {	
   	
                  	while (!background.isMoveAvailable(xcoord,ycoord) )
                  	{
                  		xcoord = xcoord-speed;
                  		startWalking('l');
                  		lastmove='l';
                  		
                  		actor.setCharacterLocation(xcoord, ycoord+movePixel);
                  	}	
                  	ycoord += movePixel;
                  	
                  }
     
            }
            else if(down )
            {
            	remainPixel=480-ycoord;
            	if ( speed > remainPixel)
            		movePixel=remainPixel;
            	
                ycoord += movePixel;
                
                if ( !background.isMoveAvailable(xcoord,ycoord) )  // to ensure it does not pass through barrier
                {	
 	
                	while (!background.isMoveAvailable(xcoord,ycoord) )
                	{
                		xcoord = xcoord-speed;
                		startWalking('l');
                		lastmove='l';
                		
                		actor.setCharacterLocation(xcoord, ycoord-movePixel);
                	}	
                	ycoord -= movePixel;
                }
                
      
            }
            else if(left  )
            {
            	remainPixel=xcoord-0;
            	if ( speed > remainPixel)
            		movePixel=remainPixel;

                xcoord -= movePixel;

            }
            else if(right )
            {
            	remainPixel=672-xcoord;
            	if ( speed > remainPixel)
            		movePixel=remainPixel;
            	
                xcoord += movePixel;  
                if ( !background.isMoveAvailable(xcoord,ycoord) )  // to ensure it does not pass through barrier
                {	
 	
                	while (!background.isMoveAvailable(xcoord,ycoord) )
                	{
                		ycoord = ycoord+speed;
                		startWalking('d');
                		lastmove='d';
                		
                		actor.setCharacterLocation(xcoord-movePixel, ycoord);
                	}	
                	xcoord -= movePixel;
                	
                }
         
            }
       
            actor.setCharacterLocation(xcoord, ycoord);
           
          if(up)
            {
            	startWalking('u'); // u stand for up, d stand for down, etc
            	lastmove='u';
            	
            } 	
            else if(down)
            {
            	startWalking('d'); 
            	lastmove='d';
            }	
            else if(left)
            {
            	startWalking('l'); 
            	lastmove='l';
            } 	
            else if(right)
            {
            	startWalking('r'); 
            	lastmove='r';
            }
            
            if (!up && !down && !left && !right )
            {
            	if (lastmove=='u')
            		showRestState('u');
            	else if (lastmove=='d')
            		showRestState('d');
            	else if (lastmove=='l')
            		showRestState('l');
            	else if (lastmove=='r')
            		showRestState('r');
            }         
           // checkAlive();
            try
            {
                Thread.sleep(10L);
            }
            catch(InterruptedException ex) { }
            
   
        }
        showDie(lastmove);
    	xcoord = 50;
    	ycoord = 50;
    	initialDirection = 'd';
        try {
        	Thread.sleep(3000);
        }
        catch (InterruptedException ex) {}
        actor.setCharacterVisible(false);
    
        //isAlive=false;
    }
       
       
    


 // 0 stand for it can be visit  1 stand for visited and 2 is the wall
 	public synchronized void enqueneUp(Node currentNode, int[][] visit, int[][] parent, LinkedList<Node> list)
 	{
 		if ( visit[(currentNode.row)-1][currentNode.column] == 0 ) // up
 		{
 			parent[(currentNode.row)-1][currentNode.column]=  (visit[0].length * currentNode.row) + (currentNode.column);
 			visit[currentNode.row-1][currentNode.column] = 1;
 			list.add( new Node(currentNode.row-1,currentNode.column) ) ;
 		}
 	}
 	public synchronized void enqueneDown(Node currentNode, int[][] visit, int[][] parent, LinkedList<Node> list)
 	{
 		if ( visit[(currentNode.row)+1][currentNode.column] == 0 ) 
 		{
 			parent[(currentNode.row)+1][currentNode.column]=  (visit[0].length * currentNode.row) + (currentNode.column);
 			visit[currentNode.row+1][currentNode.column] = 1;
 			list.add( new Node(currentNode.row+1,currentNode.column) ) ;
 		}
 	}
 	public synchronized void enqueneLeft(Node currentNode, int[][] visit, int[][] parent, LinkedList<Node> list)
 	{
 		if ( visit[currentNode.row][(currentNode.column)-1] == 0 )
 		{
 			parent[currentNode.row][(currentNode.column)-1]=  (visit[0].length * currentNode.row) + (currentNode.column);
 			visit[currentNode.row][(currentNode.column)-1] = 1;
 			list.add(new Node(currentNode.row,currentNode.column-1) ) ;
 		}
 	}
 	public synchronized void enqueneRight(Node currentNode, int[][] visit, int[][] parent, LinkedList<Node> list)
 	{
 		if ( visit[currentNode.row][(currentNode.column)+1] == 0 ) 
 		{
 			parent[currentNode.row][(currentNode.column)+1]=  (visit[0].length * currentNode.row) + (currentNode.column);
 			visit[currentNode.row][(currentNode.column)+1] = 1;
 			list.add( new Node(currentNode.row,currentNode.column+1) ) ;
 		}
 	}

 	
 	// This method use breath first search to find the shortest path 
 	public synchronized boolean search(Node startNode, Node endNode, int[][] visit ,int[][] parent) // return true if there exist a path
 	{
 		LinkedList<Node> openList = new LinkedList<Node>();   // I use it as a queue
 		
 		openList.add(startNode);
 		parent[startNode.row][startNode.column]= -1; // the start node has no parent
 		visit[startNode.row][startNode.column]= 1;   // the start point has then be visited
 	
 		while (!openList.isEmpty())
 		{
 			
 			Node node = (Node) openList.removeFirst(); // dequene
 		
  
 			if ( node.isEqual(endNode) )
 			{
 				return true; // path found
 			}
 			else 
 			{		
 			    //
 			
 				try 
 				{
 					enqueneUp(node, visit, parent, openList);		
 				}
 				catch (ArrayIndexOutOfBoundsException ex) {
 					//System.out.println("up");
 				}
 			
 				try 
 				{		
 					enqueneRight(node, visit, parent, openList);			
 				}
 				catch (ArrayIndexOutOfBoundsException ex) {
 					//System.out.println("right");
 				}
 				try 
 				{			
 					enqueneDown(node, visit, parent, openList);		
 				}
 				catch (ArrayIndexOutOfBoundsException ex) {
 					//System.out.println("dwon");
 				}
 				try 
 				{				
 					enqueneLeft(node, visit, parent, openList);	
 				}
 				catch (ArrayIndexOutOfBoundsException ex) {
 					//System.out.println("left");
 				}
 								
 			} // end of else
 		} // end of while loop
 		
 	
 		return false;
 		
 	} // end of method
}
