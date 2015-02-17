package hk.ust.isom3320.project;

import javax.swing.*;

class Background    
{

	public int noOfColumn, noOfRow, noOfBarrier;
	public int grid[][];
	JLabel barrier[];
	
	public Background(int[][] allocation )
	{

		this.noOfColumn =14;
		this.noOfRow =10;		
		this.noOfBarrier=0;
			
		grid= copy2DIntArray(allocation); // row : 0-9           column :0-13
		barrier = new JLabel[140]; // 14 x10 = 140
		createBarrier();
		
	}
	public void modifyGrid(int i, int j,int entry)
	{
		grid[i][j]=entry;
	}
	public int getEntry(int i, int j)
	{
		return grid[i][j];
	}	
	


	public boolean isMoveAvailable(int x, int y)
	{
		
		for (int i=0; i< noOfRow; i++)
		{
			for (int j=0; j < noOfColumn; j++)
			{		
				if ( (grid[i][j]==1) || (grid[i][j]==2) || (grid[i][j]==3) || (grid[i][j]==4) || (grid[i][j]==5) || (grid[i][j]==6))
				{
					if (		
							x > (j*48) - 25 &&
							x < (j*48) + 48 &&
							y > ( (i+1)*48) - 35 &&
							y < ( (i+1)*48) + 38
							)
						return false;
						/*
				       return target != null && 
        		xcoord > target.xcoord - normalWidth &&
        		xcoord < target.xcoord + target.width &&
        		ycoord > target.ycoord - normalHeight &&
        		ycoord < target.ycoord + target.height;
				        
				        */
				    
				}
			}
		}
		
		return true;
	}
	
	static public int[][] copy2DIntArray(int[][] allocation)
	{
		int[][] temp = new int[10][14];
		for (int i=0; i< 10; i++)
		{
			for (int j=0; j < 14; j++)
			{
				temp[i][j]=allocation[i][j];
			}
		}	
		return temp;
	}
	public void createBarrier()
	{
		for (int i=0; i< noOfRow; i++)
		{
			for (int j=0; j < noOfColumn; j++)
			{
				if ( grid[i][j]==1)
				{			
					barrier[noOfBarrier] = new JLabel(MyGame.woodIcon);
					barrier[noOfBarrier].setBounds( j*48, i*48, MyGame.woodIcon.getIconWidth(), MyGame.woodIcon.getIconHeight());   
					//System.out.println("Create barrier " + i+","+j);
			        MyGame.lpane.add(barrier[noOfBarrier], new Integer(3));	        
			        noOfBarrier++;
				}
				else if ( grid[i][j]==2)
				{			
					barrier[noOfBarrier] = new JLabel(MyGame.whiteBoxIcon);
					barrier[noOfBarrier].setBounds( j*48, i*48, MyGame.whiteBoxIcon.getIconWidth(), MyGame.whiteBoxIcon.getIconHeight());   
			        MyGame.lpane.add(barrier[noOfBarrier], new Integer(3));			        
			        noOfBarrier++;
				}
				else if ( grid[i][j]==3)
				{			
					barrier[noOfBarrier] = new JLabel(MyGame.whiteRoundIcon);
					barrier[noOfBarrier].setBounds( j*48, i*48, MyGame.whiteRoundIcon.getIconWidth(), MyGame.whiteRoundIcon.getIconHeight());   
			        MyGame.lpane.add(barrier[noOfBarrier], new Integer(3));		        
			        noOfBarrier++;
				}
				else if ( grid[i][j]==4)
				{			
					barrier[noOfBarrier] = new JLabel(MyGame.testIcon);
					barrier[noOfBarrier].setBounds( j*48, i*48, MyGame.testIcon.getIconWidth(), MyGame.testIcon.getIconHeight());   
			        MyGame.lpane.add(barrier[noOfBarrier], new Integer(3));		        
			        noOfBarrier++;
				}
				else if ( grid[i][j]==5)
				{			
					barrier[noOfBarrier] = new JLabel(MyGame.woodBoxIcon);
					barrier[noOfBarrier].setBounds( j*48, i*48, MyGame.testIcon.getIconWidth(), MyGame.testIcon.getIconHeight());   
			        MyGame.lpane.add(barrier[noOfBarrier], new Integer(3));		        
			        noOfBarrier++;
				}
				else if ( grid[i][j]==6)
				{			
					barrier[noOfBarrier] = new JLabel(MyGame.stoneIcon);
					barrier[noOfBarrier].setBounds( j*48, i*48, MyGame.testIcon.getIconWidth(), MyGame.testIcon.getIconHeight());   
			        MyGame.lpane.add(barrier[noOfBarrier], new Integer(3));		        
			        noOfBarrier++;
				}
			}
			
		}
	}
	
	public int getWhichRow(int y)          // divide the row from 0-47 (48 pixel ) 48-95 (47 pixel ) ..... 432 -480 (48 pixel )
	{
		int measurePoint=0;
		for ( int i=0; i <10; i++ )
		{
			if ( (measurePoint <= y  ) && ( y <= measurePoint+48 ) )
			{
				return i;
			}
			measurePoint = measurePoint+48;
		}
		return 100; // indicate false
	}
	public int getWhichColumn(int x)
	{
		int measurePoint=0;
		for ( int i=0; i <14; i++ )
		{
			if ( (measurePoint <= x  ) && ( x <= measurePoint+48 ) )
			{
				return i;
			}
			measurePoint = measurePoint+48;
		}
		return 100; // indicate false
	}
	public void printGrid() // test only
	{
		for (int i=0; i< noOfRow; i++)
		{
			for (int j=0; j < noOfColumn; j++)
			{		
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println();
	}
}



