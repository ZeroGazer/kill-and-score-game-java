package hk.ust.isom3320.project;

import javax.swing.JLabel;

public class Hero extends Character 
{
	int defaultHealthPoint;
	int numOfKills;	//Tim
	private JLabel hpLabel, weaponLabel, ammoLabel;
	
	public Hero(CharacterJLabelContainer actor,int xInitial, int yInitial,int speed, int healthPoint,Background background,int symbol,
			JLabel hpLabel, JLabel weaponLabel, JLabel ammoLabel)
	{
		
		super(actor, xInitial, yInitial, speed, healthPoint, background ,symbol);
		this.defaultHealthPoint = healthPoint;

		/*-------- Tim --------*/
		this.numOfKills = 0;
		this.currentWeapon = 0;
		
		this.ammo[0] = 999;
		this.ammo[1] = this.ammo[2] = 0;
		
		this.ammoMax[0] = 0;
		this.ammoMax[1] = 150;
		this.ammoMax[2] = 20;
		/*-------- Tim --------*/
		
		this.hpLabel = hpLabel;		
		this.weaponLabel = weaponLabel;
		this.ammoLabel = ammoLabel;
	}
	
	public synchronized void run()
    {		
    	lastmove ='d';
    	int remainPixel;
    	int movePixel;
    	
        while ( healthPoint >0 )
        {
        	hpLabel.setText((new StringBuilder()).append("HP: ").append(Integer.toString(healthPoint)).toString());
        	ammoLabel.setText((new StringBuilder()).append("Ammo: ").append(Integer.toString(ammo[currentWeapon])).toString());
        	switch (currentWeapon) {
        	case 0:
        		weaponLabel.setText("Weapon: Pistol");
        		break;
        	case 1:
        		weaponLabel.setText("Weapon: Machine Gun");
        		break;
        	case 2:
        		weaponLabel.setText("Weapon: Cannon");
        		break;
        	}
        
        	movePixel=speed;
        	addLocationToMap(xcoord,ycoord);
        	
            if(up && ycoord >= 35 )
            {	
            	remainPixel=ycoord-0;
            	if ( speed > remainPixel)
            		movePixel=remainPixel;
            	
            	ycoord -= movePixel;
 
                if ( !background.isMoveAvailable(xcoord,ycoord) )
                	ycoord += movePixel;               
                //background.printGrid();	
             
            }
            else if(down && ycoord <= 480 )
            {
            	remainPixel=480-ycoord;
            	if ( speed > remainPixel)
            		movePixel=remainPixel;
            	
                ycoord += movePixel;
             
                if ( !background.isMoveAvailable(xcoord,ycoord) )
                	ycoord -= movePixel;
               //background.printGrid();	
            
            }
            else if(left  && xcoord >= 0)
            {
            	remainPixel=xcoord-0;
            	if ( speed > remainPixel)
            		movePixel=remainPixel;

                xcoord -= movePixel;
  
                if ( !background.isMoveAvailable(xcoord,ycoord) )
                	xcoord += movePixel;           
                //background.printGrid();	   
            }
            else if(right && xcoord <= 652)
            {
            	remainPixel=672-xcoord;
            	if ( speed > remainPixel)
            		movePixel=remainPixel;
            	
                xcoord += movePixel;
                //
                //System.out.println(xcoord); 
                
                if ( !background.isMoveAvailable(xcoord,ycoord) )
                	 xcoord -= speed;               
                //System.out.println(background.getWhichColumn(xcoord)+"," +background.getWhichRow(ycoord));
                //System.out.println(xcoord+"," +ycoord);
                //background.printGrid();	
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
        isAlive=false;
       
    }
	

	
	
}
