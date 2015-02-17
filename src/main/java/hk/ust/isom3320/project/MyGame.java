package hk.ust.isom3320.project;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;

	// Declare Object Container

	class JLabelContainer
	{
		public JLabel normal,left,right,die ;   // left stand for left leg on front etc   die is always facing upward
		private int width,height;
	
		JLabelContainer(ImageIcon normal, ImageIcon left, ImageIcon right ,ImageIcon die)
	{
		this.normal=new JLabel(normal);
		this.left=new JLabel(left);
		this.right=new JLabel(right);
		this.die=new JLabel(die);
		
		width=normal.getIconWidth();
		height=normal.getIconHeight();   // the size of normal,left,right,die are the same
	}
		public void setAllVisible(boolean isVisible)
	{
		if (isVisible)
		{
			this.normal.setVisible(true);
			this.left.setVisible(true);
			this.right.setVisible(true);
			this.die.setVisible(true);
			
		}
		else
		{
			this.normal.setVisible(false);
			this.left.setVisible(false);
			this.right.setVisible(false);
			this.die.setVisible(false);
		}
	}
		public void setAllOpaque(boolean isOpaque)
	{
		if (isOpaque)
		{
			this.normal.setOpaque(true);
			this.left.setOpaque(true);
			this.right.setOpaque(true);
			this.die.setOpaque(true);		
		}
		else
		{
			this.normal.setOpaque(false);
			this.left.setOpaque(false);
			this.right.setOpaque(false);
			this.die.setOpaque(false);
		}
	}
		public void setAllBounds(int x, int y, int width, int height)
	{
		this.normal.setBounds(x, y, width, height);
		this.left.setBounds(x, y, width, height);
		this.right.setBounds(x, y, width, height);
		this.die.setBounds(x, y, width, height);
	}
		public int getWidth()
	{
		return width;
	}
		public int getHeight()
	{
		return height;
	}	
		public void setAllLocation(int x, int y)
		{
		this.normal.setLocation(x, y);
		this.left.setLocation(x, y);
		this.right.setLocation(x, y);	
		this.die.setLocation(x, y);	
		}	
	}
	
	class AttackJLabelContainer
	{
		public JLabel atk1,atk2,atk3,atk4;
		private int width,height;
		
		AttackJLabelContainer(ImageIcon atk1, ImageIcon atk2, ImageIcon atk3 , ImageIcon atk4)
		{
			this.atk1=new JLabel(atk1);
			this.atk2=new JLabel(atk2);
			this.atk3=new JLabel(atk3);		
			this.atk4=new JLabel(atk4);	
		
			width=atk1.getIconWidth();
			height=atk1.getIconHeight();   // the size of normal,left,right,die are the same
		}
		public void setAllVisible(boolean isVisible)
	{
		if (isVisible)
		{
			this.atk1.setVisible(true);
			this.atk2.setVisible(true);
			this.atk3.setVisible(true);
			this.atk4.setVisible(true);
	
		}
		else
		{
			this.atk1.setVisible(false);
			this.atk2.setVisible(false);
			this.atk3.setVisible(false);
			this.atk4.setVisible(false);
		}
	}
		public void setAllOpaque(boolean isOpaque)
	{
		if (isOpaque)
		{
			this.atk1.setOpaque(true);
			this.atk2.setOpaque(true);
			this.atk3.setOpaque(true);	
			this.atk4.setOpaque(true);	
		}
		else
		{
			this.atk1.setOpaque(false);
			this.atk2.setOpaque(false);
			this.atk3.setOpaque(false);
			this.atk4.setOpaque(false);
		}
	}
		public void setAllBounds(int x, int y, int width, int height)
	{
		this.atk1.setBounds(x, y, width, height);
		this.atk2.setBounds(x, y, width, height);
		this.atk3.setBounds(x, y, width, height);
		this.atk4.setBounds(x, y, width, height);
	
	}
		public int getWidth()
	{
		return width;
	}
		public int getHeight()
	{
		return height;
	}	
		public void setAllLocation(int x, int y)
		{
		this.atk1.setLocation(x, y);
		this.atk2.setLocation(x, y);
		this.atk3.setLocation(x, y);	
		this.atk4.setLocation(x, y);
		}	
	
	}

	class CharacterJLabelContainer
	{
		public JLabelContainer up,down,left,right;   // the direction sequence is always up,down,left,right  in this program
			
		 CharacterJLabelContainer(JLabelContainer up,JLabelContainer down, JLabelContainer left,JLabelContainer right)
		{
			this.up=up;
			this.down=down;
			this.left=left;
			this.right=right;
		}
		public void setCharacterBounds(int x, int y, int width, int height)
		{
			up.setAllBounds(x, y,width,height);
			down.setAllBounds(x, y,width,height);
			left.setAllBounds(x, y,width,height);
			right.setAllBounds(x, y,width,height);
		}
		public synchronized void  setCharacterLocation(int x, int y)    
		{
			up.setAllLocation(x-11, y-45);
			down.setAllLocation(x-11, y-45);
			left.setAllLocation(x-11, y-45);
			right.setAllLocation(x-11, y-45);
			
			//  We set the reference point to the character's leg.  It is x : + 11 pixels, y: + 45 pixels to the
			//original left,top corner reference point
		}	
		public synchronized void setCharacterVisible(boolean isVisible)
		{
			up.setAllVisible(isVisible);
			down.setAllVisible(isVisible);
			left.setAllVisible(isVisible);
			right.setAllVisible(isVisible);
		}
		public int getWidth()
		{
			return up.getWidth();
		}
		public int getHeight()
		{
			return up.getHeight();
		}	
	}
	
	class ZombieJLabelContainer extends CharacterJLabelContainer
	{
		public AttackJLabelContainer upAttack,downAttack,leftAttack,rightAttack;
		
		ZombieJLabelContainer(JLabelContainer up,JLabelContainer down, JLabelContainer left,JLabelContainer right, 
				AttackJLabelContainer upAttack,AttackJLabelContainer downAttack,AttackJLabelContainer leftAttack,AttackJLabelContainer rightAttack)
		{
			super(up,down,left,right);
			this.upAttack=upAttack;
			this.downAttack=downAttack;
			this.leftAttack=leftAttack;
			this.rightAttack=rightAttack;
		}
		public void setCharacterBounds(int x, int y, int width, int height)
		{
			super.setCharacterBounds(x, y, width, height);
			upAttack.setAllBounds(x, y,width,height);
			downAttack.setAllBounds(x, y,width,height);
			leftAttack.setAllBounds(x, y,width,height);
			rightAttack.setAllBounds(x, y,width,height);
		}
		public synchronized void  setCharacterLocation(int x, int y)    
		{
			super.setCharacterLocation(x, y);
			upAttack.setAllLocation(x-11, y-45);
			downAttack.setAllLocation(x-11, y-45);
			leftAttack.setAllLocation(x-11, y-45);
			rightAttack.setAllLocation(x-11, y-45);
			
			//  We set the reference point to the character's leg.  It is x : + 11 pixels, y: + 45 pixels to the
			//original left,top corner reference point
		}	
		public synchronized void setCharacterVisible(boolean isVisible)
		{
			super.setCharacterVisible(isVisible);
			upAttack.setAllVisible(isVisible);
			downAttack.setAllVisible(isVisible);
			leftAttack.setAllVisible(isVisible);
			rightAttack.setAllVisible(isVisible);
		}
		public int getWidth()
		{
			return up.getWidth();
		}
		public int getHeight()
		{
			return up.getHeight();
		}	
	}

	class bulletIconContainer
	{
		public Icon up,down,left,right;
		public int verticalWidth,verticalHeight,horizontalWidth,horizontalHeight;	
		bulletIconContainer(ImageIcon up,ImageIcon down, ImageIcon left, ImageIcon right)
		{
			this.up = up;
			this.down = down;
			this.left = left;
			this.right = right;

			verticalWidth = up.getIconWidth();
			verticalHeight = up.getIconHeight();   
		
			horizontalWidth = left.getIconWidth();
			horizontalHeight = left.getIconHeight();   
		}
	
	}

	//End of Declare Object Container

	public class MyGame extends JApplet
    	implements KeyListener, Runnable
	{
		int[][] bg1 =
			{
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0}, 
				{0, 0, 1, 0, 0, 1, 0, 0, 0, 6, 0, 0, 0, 0}, 
				{0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 6, 0, 0}, 
				{0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
				{0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
				{0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 6, 6, 0}, 
				{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 6, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    		};
		int[][] bg2 =
			{
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 3, 0, 6, 0}, 
				{0, 0, 0, 3, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0}, 
				{0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0}, 
				{0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0}, 
				{0, 0, 0, 3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    		};
		int[][] bgDefault =
			{
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    		};
 
		// By Alex 1
		
		static int selectCharacter1s,selectCharacter2s,numOfPlayers,selectMaps;
		static ImageIcon gameoverIcon, rankingIcon;    //@Alex
				
		static ImageIcon playIcon1, playIcon2, playIcon3, playIcon4; //@Alex
		static ImageIcon map1Icon, map2Icon; //@Alex
		static ImageIcon[] character1Icon, character2Icon; //@Alex

		static JLabel playLabel1, playLabel2, playLabel3, playLabel4; //@Alex
		static JLabel map1Label, map2Label; //@Alex
		static JLabel[] character1Label, character2Label; //@Alex
			
		static JLabel gameoverLabel, rankingLabel; //@Alex


		static int choose=1;//@Alex
				
		static 	Ranking ranking; //@Alex
				
		static int user1Up, user1Down, user1Left, user1Right, user1Shoot, user1Leftweapon, user1Rightweapon, user2Up, user2Down, user2Left, user2Right, user2Shoot, user2Leftweapon, user2Rightweapon;
				// End of By Alex 1
		
		/*------------------ Tim ------------------*/
		static AudioClip bgMusic;
		static AudioClip[] weaponAu, hitZombieAu;
		static AudioClip explodeAu;
		/*------------------ Tim ------------------*/

		static Background background;
		
		static Hero user1,user2;
		
		static JLayeredPane lpane;
	
		static CharacterJLabelContainer player1,player2,player3,player4;
		static CharacterJLabelContainer player_1,player_2,player_3,player_4;
		
		static bulletIconContainer sbullet,lbullet;
	
		static ImageIcon  menuIcon,bg1Icon,bg2Icon,ak47Icon,pistolIcon,rpgIcon ;   // menu icon
		static ImageIcon bloodIcon[];
		static ImageIcon woodIcon,whiteBoxIcon, whiteRoundIcon,testIcon,stoneIcon,woodBoxIcon;
		static ImageIcon explosionIcon;
		static ImageIcon boxIcon;
    
		static JLabel menuLabel,bg1Label,bg2Label,ak47Label, pistolLabel, rpgLabel ; // images on menu
		static JLabel bloodLabel0,bloodLabel1,bloodLabel2,woodLabel;
		static JLabel explosionLabel;
		static JLabel boxLabel;
        
		static JLabel startLabel1,startLabel2,startLabel3;   // Words on menu  By alex
    
		static JLabel ammoLabel1,weaponLabel1,hpLabel1,currentScoreLabel1;  // dynamic words for player1;
		static JLabel ammoLabel2,weaponLabel2,hpLabel2;  // dynamic words for player2;

		static int score1 = 0;
		static int healthPoint1=0; 
		static int ammo1=0;
    
		static int healthPoint2=0; 
		static int ammo2=0;
		
		static ZombieJLabelContainer[]  zombieJLabelContainers,bossJLabelContainers;
		static boolean isStart=false;
 
    
		// Some Method
		public ImageIcon getImageIcon (String path)
		{
			Image tempImage = getImage(getCodeBase(), "images/"+path);
			return new ImageIcon(tempImage);    	         	
		} 
		public JLabel getJLabel (String path)
		{
    	
			ImageIcon tempIcon = getImageIcon (path);
			return new JLabel(tempIcon);   	         	
		} 
		public static synchronized void addJLabelToLpane(JLabel jlabel, int indexOfLayer)
		{
			lpane.add(jlabel, new Integer(indexOfLayer));
		}
		public static synchronized void removeJLabelFromLpane(JLabel jlabel)
		{
			lpane.remove(jlabel);
		}  
		public static synchronized void  addCharacterToLpane ( CharacterJLabelContainer character, int indexOfLayer)
		{
			lpane.add(character.up.normal, new Integer(indexOfLayer));
			lpane.add(character.up.left, new Integer(indexOfLayer));
			lpane.add(character.up.right, new Integer(indexOfLayer));
			lpane.add(character.up.die, new Integer(indexOfLayer));
		
			lpane.add(character.down.normal, new Integer(indexOfLayer));
			lpane.add(character.down.left, new Integer(indexOfLayer));
			lpane.add(character.down.right, new Integer(indexOfLayer));
			lpane.add(character.down.die, new Integer(indexOfLayer));
		
			lpane.add(character.left.normal, new Integer(indexOfLayer));
			lpane.add(character.left.left, new Integer(indexOfLayer));
			lpane.add(character.left.right, new Integer(indexOfLayer));
			lpane.add(character.left.die, new Integer(indexOfLayer));
		
			lpane.add(character.right.normal, new Integer(indexOfLayer));
			lpane.add(character.right.left, new Integer(indexOfLayer));
			lpane.add(character.right.right, new Integer(indexOfLayer));		
			lpane.add(character.right.die, new Integer(indexOfLayer));	
		}
		public static synchronized void  addATKToLpane ( ZombieJLabelContainer character, int indexOfLayer)
		{
			lpane.add(character.upAttack.atk1, new Integer(indexOfLayer));
			lpane.add(character.upAttack.atk2, new Integer(indexOfLayer));
			lpane.add(character.upAttack.atk3, new Integer(indexOfLayer));
			lpane.add(character.upAttack.atk4, new Integer(indexOfLayer));
		
			lpane.add(character.downAttack.atk1, new Integer(indexOfLayer));
			lpane.add(character.downAttack.atk2, new Integer(indexOfLayer));
			lpane.add(character.downAttack.atk3, new Integer(indexOfLayer));
			lpane.add(character.downAttack.atk4, new Integer(indexOfLayer));
		
			lpane.add(character.leftAttack.atk1, new Integer(indexOfLayer));
			lpane.add(character.leftAttack.atk2, new Integer(indexOfLayer));
			lpane.add(character.leftAttack.atk3, new Integer(indexOfLayer));
			lpane.add(character.leftAttack.atk4, new Integer(indexOfLayer));
		
			lpane.add(character.rightAttack.atk1, new Integer(indexOfLayer));
			lpane.add(character.rightAttack.atk2, new Integer(indexOfLayer));
			lpane.add(character.rightAttack.atk3, new Integer(indexOfLayer));
			lpane.add(character.rightAttack.atk4, new Integer(indexOfLayer));
		}
		public static synchronized void  removeCharacterFromLpane ( CharacterJLabelContainer character)
		{
			lpane.remove(character.up.normal);
			lpane.remove(character.up.left);
			lpane.remove(character.up.right);
			lpane.remove(character.up.die);
		
			lpane.remove(character.down.normal);
			lpane.remove(character.down.left);
			lpane.remove(character.down.right);
			lpane.remove(character.down.die);
		
			lpane.remove(character.left.normal);
			lpane.remove(character.left.left);
			lpane.remove(character.left.right);
			lpane.remove(character.left.die);
		
			lpane.remove(character.right.normal);
			lpane.remove(character.right.left);
			lpane.remove(character.right.right);	
			lpane.remove(character.right.die);
		}
		
		
		public static synchronized void addJTextFieldToLpane(JTextField jfield, int indexOfLayer)
		{
			lpane.add(jfield, new Integer(indexOfLayer));
		}
		public static synchronized void removeJTextFieldFromLpane(JTextField jfield, int indexOfLayer)
		{
			lpane.remove(jfield);
		}

		
		/*----------------------- Tim -----------------------*/
		public static synchronized void displayBlood(Character victim) {
			int random = (int)(Math.random()*3);	//generate a random number between 0 and 2 (inclusive)
			JLabel blood = new JLabel(bloodIcon[random]);
			blood.setBounds(victim.xcoord - 30 + (int)(Math.random()*9)*5, victim.ycoord - 45 + (int)(Math.random()*9)*5, bloodIcon[random].getIconWidth(), bloodIcon[random].getIconHeight());
			addJLabelToLpane(blood, 2);
		}
		/*----------------------- Tim -----------------------*/
		
		
		// By Alex 2
		
		// Some Method
		public void removeMenu(){ //@Alex
			startLabel1.setVisible(false);
			startLabel2.setVisible(false);
			startLabel3.setVisible(false);
			ak47Label.setVisible(false);
		}
		
		public void addMenu(){ //@Alex
			startLabel1.setVisible(true);
			startLabel2.setVisible(true);
			startLabel3.setVisible(true);
			ak47Label.setVisible(true);
			ak47Label.setLocation(150, 250);
			choose=1;
		}
		
		public void addPlayMenu(int numOfPlayer){
			boolean isExit = false;
			int select = 1;
			int selectCharacter1 = 1;
			int selectCharacter2 = 1;
			int selectMap = 1;
			ak47Label.setLocation(170, 120);
			ak47Label.setVisible(true);
			map1Label.setLocation(300, 170);
			map1Label.setVisible(true);
			character1Label[0].setLocation(300, 120);
			character1Label[0].setVisible(true);
			if(numOfPlayer==2){
				character2Label[0].setLocation(400, 120);
				character2Label[0].setVisible(true);
			}
			playLabel1.setVisible(true);
			playLabel2.setVisible(true);
			playLabel3.setVisible(true);
			playLabel4.setVisible(true);
			
			while(!isExit){
				boolean user1Left = false;
				boolean user1Right = false;
				boolean user2Left = false;
				boolean user2Right = false;
				switch(select){
					case 1:
						if(user1.up){
							select = 4;
							ak47Label.setLocation(170,420);
						}
						if(user1.down){
							select = 2;
							ak47Label.setLocation(170,170);
						}
						if(user1.left)
							user1Left = true;
						if(user1.right)
							user1Right = true;
						if(user1Left||user1Right){
							switch(selectCharacter1){
								case 1:
									if(user1Left){
										selectCharacter1 = 4;
										character1Label[0].setVisible(false);
										character1Label[3].setLocation(300, 120);
										character1Label[3].setVisible(true);
										break;
									}
								case 2:
								case 3:
									if(user1Right){
										character1Label[selectCharacter1-1].setVisible(false);
										selectCharacter1++;
										character1Label[selectCharacter1-1].setLocation(300, 120);
										character1Label[selectCharacter1-1].setVisible(true);
										break;
									}
								case 4:
									if(user1Left){
										character1Label[selectCharacter1-1].setVisible(false);
										selectCharacter1--;
										character1Label[selectCharacter1-1].setLocation(300, 120);
										character1Label[selectCharacter1-1].setVisible(true);
										break;
									}
									if(user1Right){
										selectCharacter1 = 1;
										character1Label[3].setVisible(false);
										character1Label[0].setLocation(300, 120);
										character1Label[0].setVisible(true);
										break;
									}
							}
							user1Left = false;
							user1Right = false;
						}
						if(numOfPlayer==2){
							if(user2.left)
								user2Left = true;
							if(user2.right)
								user2Right = true;
							if(user2Left||user2Right){
								switch(selectCharacter2){
									case 1:
										if(user2Left){
											selectCharacter2 = 4;
											character2Label[0].setVisible(false);
											character2Label[3].setLocation(400, 120);
											character2Label[3].setVisible(true);
											break;
										}
									case 2:
									case 3:
										if(user2Right){
											character2Label[selectCharacter2-1].setVisible(false);
											selectCharacter2++;
											character2Label[selectCharacter2-1].setLocation(400, 120);
											character2Label[selectCharacter2-1].setVisible(true);
											break;
										}
									case 4:
										if(user2Left){
											character2Label[selectCharacter2-1].setVisible(false);
											selectCharacter2--;
											character2Label[selectCharacter2-1].setLocation(400, 120);
											character2Label[selectCharacter2-1].setVisible(true);
											break;
										}
										if(user2Right){
											selectCharacter2 = 1;
											character2Label[3].setVisible(false);
											character2Label[0].setLocation(400, 120);
											break;
										}
									
								}
								user2Left=false;
								user2Right=false;
							}
						}
						break;
					case 2:
						if(user1.up){
							select = 1;
							ak47Label.setLocation(170,120);
						}
						if(user1.down){
							select = 3;
							ak47Label.setLocation(170, 350);
						}
						if(user1.left||user1.right){
							user1.left = false;
							user1.right = false;
							switch(selectMap){
								case 1:
									selectMap = 2;
									map1Label.setVisible(false);
									map2Label.setLocation(300, 170);
									map2Label.setVisible(true);
									break;
								case 2:
									selectMap = 1;
									map2Label.setVisible(false);
									map1Label.setLocation(300, 170);
									map1Label.setVisible(true);
									break;
							}
						}
						break;
					case 3:
						if(user1.up){
							select = 2;
							ak47Label.setLocation(170,170);
						}
						if(user1.down){
							select = 4;
							ak47Label.setLocation(170,420);
						}
						System.out.println(user1.shoot);
						if(user1.shoot){
							user1.shoot = false;
							isExit = true;
							isStart = true;
							ak47Label.setVisible(false);
							map1Label.setVisible(false);
							map2Label.setVisible(false);
							character1Label[selectCharacter1-1].setVisible(false);
							character2Label[selectCharacter2-1].setVisible(false);
							playLabel1.setVisible(false);
							playLabel2.setVisible(false);
							playLabel3.setVisible(false);
							playLabel4.setVisible(false);
							menuLabel.setVisible(false);
						}
						break;
						case 4:
						if(user1.up){
							select = 3;
							ak47Label.setLocation(170, 350);
						}
						if(user1.down){
							select = 1;
							ak47Label.setLocation(170, 120);
						}
						if(user1.shoot){
							isExit = true;
							ak47Label.setVisible(false);
							map1Label.setVisible(false);
							map2Label.setVisible(false);
							character1Label[selectCharacter1-1].setVisible(false);
							character2Label[selectCharacter2-1].setVisible(false);
							playLabel1.setVisible(false);
							playLabel2.setVisible(false);
							playLabel3.setVisible(false);
							playLabel4.setVisible(false);
							addMenu();
						}
						break;
				}
				try{
					Thread.sleep(90L);
				}
				catch(InterruptedException ex) { } // pause a little bit
				
				
			}
			numOfPlayers=numOfPlayer;
			selectCharacter1s=selectCharacter1;
			selectCharacter2s=selectCharacter2;
			selectMaps=selectMap;
			
			
			System.out.println("numOfPlayer "+numOfPlayer);
			System.out.println("selectCharacter1 "+selectCharacter1);
			System.out.println("selectCharacter2 "+selectCharacter2);
			System.out.println("selectMap "+selectMap); // Startgame
		/*	
			if (numOfPlayer==1)
			{
				////////////////////
				if (selectMap==1)
				{
					lpane.add(bg1Label, new Integer(0) );
					background= new Background(bg1);
				}
				else if (selectMap==2)
				{
					lpane.add(bg2Label, new Integer(0) );
					background= new Background(bg2);
				}
				switch (selectCharacter1)
				{
				case 1:
					user1=new Hero(player1,20,20,10,100,background,7);	
					break;
				case 2:
					user1=new Hero(player2,20,20,10,100,background,7);
					break;
				case 3:
					user1=new Hero(player3,20,20,10,100,background,7);
					break;
				case 4:
					user1=new Hero(player4,20,20,10,100,background,7);
					break;
				}
				//////////////////
				
				user1.isAlive = true;
				user1.startAnimation();
				(new BulletGenerator(user1)).startAnimation();
				
				(new ZombieGenerator( zombieJLabelContainers,bossJLabelContainers, background ,user1, user2) ).startAnimation();
			}
			if (numOfPlayer==2)
			{
				/////////////////
				if (selectMap==1)
				{
					lpane.add(bg1Label, new Integer(0) );
					background= new Background(bg1);
				}
				else if (selectMap==2)
				{
					lpane.add(bg2Label, new Integer(0) );
					background= new Background(bg2);
				}
				////////////////////
				
				switch (selectCharacter1)
				{
				case 1:
					user1=new Hero(player1,20,20,10,100,background,7);	
					break;
				case 2:
					user1=new Hero(player2,20,20,10,100,background,7);
					break;
				case 3:
					user1=new Hero(player3,20,20,10,100,background,7);
					break;
				case 4:
					user1=new Hero(player4,20,20,10,100,background,7);
					break;
				}
				
				user1.isAlive = true;
				user1.startAnimation();	
				(new BulletGenerator(user1)).startAnimation();
				
				switch (selectCharacter2)
				{
				case 1:
					user2=new Hero(player_1,100,20,10,100,background,8);	
					break;
				case 2:
					user2=new Hero(player_2,100,20,10,100,background,8);
					break;
				case 3:
					user2=new Hero(player_3,100,20,10,100,background,8);
					break;
				case 4:
					user2=new Hero(player_4,100,20,10,100,background,8);
					break;
				}
				
				
				user2.startAnimation();	
				user2.isAlive = true;
				(new BoxGenerator()).startAnimation();
				(new BulletGenerator(user2)).startAnimation();
				
				(new ZombieGenerator( zombieJLabelContainers,bossJLabelContainers, background ,user1, user2) ).startAnimation();

				
			}
		*/	
			
	
		}
		
		public static void printGameOver(int score){
			if(ranking.isTop10(score))
				ranking.addPlayer(15000);
			ranking.rankingPanel.setBounds(0, 0, 672, 480);
			lpane.add(ranking.rankingPanel,new Integer(12));
			gameoverLabel.setVisible(true);
			ranking.rankingPanel.setVisible(true);
			ranking.rankingPanel.setOpaque(false);
			/*boolean isExit=false;
			while(!isExit){
				try
				{
					Thread.sleep(90L);
				}
				catch(InterruptedException ex) { } // pause a little bit
				if(user1.shoot){
					user1.shoot = false;
					isExit=true;
				}
			}
			rankingLabel.setVisible(false);
			ranking.rankingPanel.setVisible(false);*/
		}		

		
		// End of By Alex 2
		
		// End of Some Method
		
		
		
		
		public MyGame()
		{
		}

		public void init()
		{   
			setSize(672,480);
			
			setFocusable(true);
			requestFocusInWindow();
			lpane = new JLayeredPane();
			add(lpane, "Center");
			
			/*------------------ Tim ------------------*/
			
			bgMusic = Applet.newAudioClip(getClass().getResource("audio/bg.wav"));
			
			weaponAu = new AudioClip[3];
			weaponAu[0] = Applet.newAudioClip(getClass().getResource("audio/pistol.wav"));
			weaponAu[1] = Applet.newAudioClip(getClass().getResource("audio/machineGun.wav"));
			weaponAu[2] = Applet.newAudioClip(getClass().getResource("audio/rocket.wav"));
			
			hitZombieAu = new AudioClip[2];
			hitZombieAu[0] = Applet.newAudioClip(getClass().getResource("audio/monster0.wav"));
			hitZombieAu[1] = Applet.newAudioClip(getClass().getResource("audio/monster1.wav"));
			
			explodeAu = Applet.newAudioClip(getClass().getResource("audio/explode.wav"));
			
			/*------------------ Tim ------------------*/
			
			
			menuIcon = getImageIcon("menu/menu.gif");
			menuLabel = new JLabel(menuIcon);   
			menuLabel.setBounds(0, 0, menuIcon.getIconWidth(), menuIcon.getIconHeight());
			lpane.add(menuLabel, new Integer(0));
			
			bg1Icon = getImageIcon("court/grass.jpg");
			bg1Label = new JLabel(bg1Icon);   
			bg1Label.setBounds(0, 0, bg1Icon.getIconWidth(), bg1Icon.getIconHeight());
	
			
			bg2Icon = getImageIcon("court/atrium.jpg");
			bg2Label = new JLabel(bg2Icon);   
			bg2Label.setBounds(0, 0, bg2Icon.getIconWidth(), bg2Icon.getIconHeight());
		
			
			// backgroundInitial
			woodIcon = getImageIcon("barrier/wood.gif");
			whiteBoxIcon = getImageIcon("barrier/whiteBox.gif");
			whiteRoundIcon = getImageIcon("barrier/whiteRound.gif");
			testIcon = getImageIcon("barrier/test.gif");
			woodBoxIcon = getImageIcon("barrier/woodBox.png");
			stoneIcon = getImageIcon("barrier/stone.png");
 
			
        		       		
			background= new Background(bgDefault);
		
        
    
        
			gameoverIcon = getImageIcon("menu/gameover.gif");//@Alex
			gameoverLabel = new JLabel(gameoverIcon);
			gameoverLabel.setBounds(0, 0, gameoverIcon.getIconWidth(), gameoverIcon.getIconHeight());
			gameoverLabel.setVisible(false);
			rankingIcon = getImageIcon("menu/brokenWall.jpg");
			rankingLabel = new JLabel(rankingIcon);   
			rankingLabel.setBounds(0, 0, rankingIcon.getIconWidth(), rankingIcon.getIconHeight());
			rankingLabel.setVisible(false);
			lpane.add(gameoverLabel,new Integer(11));
			lpane.add(rankingLabel, new Integer(11));
			

        
			ak47Icon = getImageIcon("ak47.gif");    
			ak47Label = new JLabel(ak47Icon);
			ak47Label.setBounds(150, 260, ak47Icon.getIconWidth(), ak47Icon.getIconHeight());   //@Alex
			lpane.add(ak47Label, new Integer(1));
        
      
		
			// Words on menu				
			Font font = new Font("Times New Roman", 1, 35);
			startLabel1 = new JLabel("Single Player");    
			startLabel1.setFont(font);
			startLabel1.setForeground(Color.RED);//@Alex
			startLabel1.setBounds(270, 250, 400, 40);		
			lpane.add(startLabel1, new Integer(1));
			        
			startLabel2 = new JLabel("Cooperative");
			startLabel2.setFont(font);
			startLabel2.setForeground(Color.RED);//@Alex
			startLabel2.setBounds(270, 300, 400, 40);		
			lpane.add(startLabel2, new Integer(1));
					
			startLabel3 = new JLabel("Ranking");
			startLabel3.setFont(font);
			startLabel3.setForeground(Color.RED);//@Alex
			startLabel3.setBounds(270, 350, 400, 40);		
			lpane.add(startLabel3, new Integer(1));
			        

			// End of Words on menu       
        
			
			// Alex 3
        
			// Words on play menu //@Alex
			playLabel1 = new JLabel("Player");    
			playLabel1.setFont(font);
			playLabel1.setForeground(Color.RED);//@Alex
			playLabel1.setBounds(50, 120, 400, 40);		
			lpane.add(playLabel1, new Integer(1));
			playLabel1.setVisible(false);
        
			playLabel2 = new JLabel("Map");
			playLabel2.setFont(font);
			playLabel2.setForeground(Color.RED);//@Alex
			playLabel2.setBounds(50, 170, 400, 40);		
			lpane.add(playLabel2, new Integer(1));
			playLabel2.setVisible(false);
		
			playLabel3 = new JLabel("Start");
			playLabel3.setFont(font);
			playLabel3.setForeground(Color.RED);//@Alex
			playLabel3.setBounds(50, 350, 400, 40);		
			lpane.add(playLabel3, new Integer(1));
			playLabel3.setVisible(false);
        
			playLabel4 = new JLabel("Return");
			playLabel4.setFont(font);
			playLabel4.setForeground(Color.RED);//@Alex
			playLabel4.setBounds(50, 420, 400, 40);		
			lpane.add(playLabel4, new Integer(1));
			playLabel4.setVisible(false);
			
			// Initialize character image //@Alex
			character1Icon = new ImageIcon[4];
			character2Icon = new ImageIcon[4];
			character1Label = new JLabel[4];
			character2Label = new JLabel[4];
			character1Icon[0] = getImageIcon("player1/down1.gif");    
			character1Label[0] = new JLabel(character1Icon[0]);
			character1Label[0].setBounds(150, 260, character1Icon[0].getIconWidth(), character1Icon[0].getIconHeight());   //@Alex
			lpane.add(character1Label[0], new Integer(1));
			character1Label[0].setVisible(false);
			character1Icon[1] = getImageIcon("player2/down1.gif");    
			character1Label[1] = new JLabel(character1Icon[1]);
			character1Label[1].setBounds(150, 260, character1Icon[1].getIconWidth(), character1Icon[1].getIconHeight());   //@Alex
			lpane.add(character1Label[1], new Integer(1));
			character1Label[1].setVisible(false);
			character1Icon[2] = getImageIcon("player3/down1.gif");    
			character1Label[2] = new JLabel(character1Icon[2]);
			character1Label[2].setBounds(150, 260, character1Icon[2].getIconWidth(), character1Icon[2].getIconHeight());   //@Alex
			lpane.add(character1Label[2], new Integer(1));
			character1Label[2].setVisible(false);
			character1Icon[3] = getImageIcon("player4/down1.gif");    
			character1Label[3] = new JLabel(character1Icon[3]);
			character1Label[3].setBounds(150, 260, character1Icon[3].getIconWidth(), character1Icon[3].getIconHeight());   //@Alex
			lpane.add(character1Label[3], new Integer(1));
			character1Label[3].setVisible(false);
			character2Icon[0] = getImageIcon("player1/down1.gif");    
			character2Label[0] = new JLabel(character2Icon[0]);
			character2Label[0].setBounds(150, 260, character2Icon[0].getIconWidth(), character2Icon[0].getIconHeight());   //@Alex
			lpane.add(character2Label[0], new Integer(1));
			character2Label[0].setVisible(false);
			character2Icon[1] = getImageIcon("player2/down1.gif");    
			character2Label[1] = new JLabel(character2Icon[1]);
			character2Label[1].setBounds(150, 260, character2Icon[1].getIconWidth(), character2Icon[1].getIconHeight());   //@Alex
			lpane.add(character2Label[1], new Integer(1));
			character2Label[1].setVisible(false);
			character2Icon[2] = getImageIcon("player3/down1.gif");    
			character2Label[2] = new JLabel(character2Icon[2]);
			character2Label[2].setBounds(150, 260, character2Icon[2].getIconWidth(), character2Icon[2].getIconHeight());   //@Alex
			lpane.add(character2Label[2], new Integer(1));
			character2Label[2].setVisible(false);
			character2Icon[3] = getImageIcon("player4/down1.gif");    
			character2Label[3] = new JLabel(character2Icon[3]);
			character2Label[3].setBounds(150, 260, character2Icon[3].getIconWidth(), character2Icon[3].getIconHeight());   //@Alex
			lpane.add(character2Label[3], new Integer(1));
			character2Label[3].setVisible(false);
			
			// Initialize 2 maps //@Alex
			map1Icon = getImageIcon("court/smallBG1.gif");
			map1Label = new JLabel(map1Icon);
			map1Label.setBounds(150, 260, map1Icon.getIconWidth(), map1Icon.getIconHeight());
			lpane.add(map1Label, new Integer(1));
			map1Label.setVisible(false);
			map2Icon = getImageIcon("court/smallBG2.gif");
			map2Label = new JLabel(map2Icon);
			map2Label.setBounds(150, 260, map2Icon.getIconWidth(), map2Icon.getIconHeight());
			lpane.add(map2Label, new Integer(1));
			map2Label.setVisible(false);
			// End of By Alex  
		
        // Initialize 4 direction movement picture of characters	
        
			player1 = new CharacterJLabelContainer ( new JLabelContainer(getImageIcon("player1/up1.gif"),getImageIcon("player1/up2.gif"),getImageIcon("player1/up3.gif"),getImageIcon("player1/up4.gif")),
					new JLabelContainer(getImageIcon("player1/down1.gif"),getImageIcon("player1/down2.gif"),getImageIcon("player1/down3.gif"),getImageIcon("player1/down4.gif")),
					new JLabelContainer(getImageIcon("player1/left1.gif"),getImageIcon("player1/left2.gif"),getImageIcon("player1/left3.gif"),getImageIcon("player1/left4.gif")),
					new JLabelContainer(getImageIcon("player1/right1.gif"),getImageIcon("player1/right2.gif"),getImageIcon("player1/right3.gif"),getImageIcon("player1/right4.gif"))	
					);
			player2 = new CharacterJLabelContainer ( new JLabelContainer(getImageIcon("player2/up1.gif"),getImageIcon("player2/up2.gif"),getImageIcon("player2/up3.gif"),getImageIcon("player2/up4.gif")),
					new JLabelContainer(getImageIcon("player2/down1.gif"),getImageIcon("player2/down2.gif"),getImageIcon("player2/down3.gif"),getImageIcon("player2/down4.gif")),
					new JLabelContainer(getImageIcon("player2/left1.gif"),getImageIcon("player2/left2.gif"),getImageIcon("player2/left3.gif"),getImageIcon("player2/left4.gif")),
					new JLabelContainer(getImageIcon("player2/right1.gif"),getImageIcon("player2/right2.gif"),getImageIcon("player2/right3.gif"),getImageIcon("player2/right4.gif"))	
					);
			player3 = new CharacterJLabelContainer ( new JLabelContainer(getImageIcon("player3/up1.gif"),getImageIcon("player3/up2.gif"),getImageIcon("player3/up3.gif"),getImageIcon("player3/up4.gif")),
					new JLabelContainer(getImageIcon("player3/down1.gif"),getImageIcon("player3/down2.gif"),getImageIcon("player3/down3.gif"),getImageIcon("player3/down4.gif")),
					new JLabelContainer(getImageIcon("player3/left1.gif"),getImageIcon("player3/left2.gif"),getImageIcon("player3/left3.gif"),getImageIcon("player3/left4.gif")),
					new JLabelContainer(getImageIcon("player3/right1.gif"),getImageIcon("player3/right2.gif"),getImageIcon("player3/right3.gif"),getImageIcon("player3/right4.gif"))	
					);
			player4 = new CharacterJLabelContainer ( new JLabelContainer(getImageIcon("player4/up1.gif"),getImageIcon("player4/up2.gif"),getImageIcon("player4/up3.gif"),getImageIcon("player4/up4.gif")),
					new JLabelContainer(getImageIcon("player4/down1.gif"),getImageIcon("player4/down2.gif"),getImageIcon("player4/down3.gif"),getImageIcon("player4/down4.gif")),
					new JLabelContainer(getImageIcon("player4/left1.gif"),getImageIcon("player4/left2.gif"),getImageIcon("player4/left3.gif"),getImageIcon("player4/left4.gif")),
					new JLabelContainer(getImageIcon("player4/right1.gif"),getImageIcon("player4/right2.gif"),getImageIcon("player4/right3.gif"),getImageIcon("player4/right4.gif"))	
					);
			
			player_1 = new CharacterJLabelContainer ( new JLabelContainer(getImageIcon("player1/up1.gif"),getImageIcon("player1/up2.gif"),getImageIcon("player1/up3.gif"),getImageIcon("player1/up4.gif")),
					new JLabelContainer(getImageIcon("player1/down1.gif"),getImageIcon("player1/down2.gif"),getImageIcon("player1/down3.gif"),getImageIcon("player1/down4.gif")),
					new JLabelContainer(getImageIcon("player1/left1.gif"),getImageIcon("player1/left2.gif"),getImageIcon("player1/left3.gif"),getImageIcon("player1/left4.gif")),
					new JLabelContainer(getImageIcon("player1/right1.gif"),getImageIcon("player1/right2.gif"),getImageIcon("player1/right3.gif"),getImageIcon("player1/right4.gif"))	
					);
			player_2 = new CharacterJLabelContainer ( new JLabelContainer(getImageIcon("player2/up1.gif"),getImageIcon("player2/up2.gif"),getImageIcon("player2/up3.gif"),getImageIcon("player2/up4.gif")),
					new JLabelContainer(getImageIcon("player2/down1.gif"),getImageIcon("player2/down2.gif"),getImageIcon("player2/down3.gif"),getImageIcon("player2/down4.gif")),
					new JLabelContainer(getImageIcon("player2/left1.gif"),getImageIcon("player2/left2.gif"),getImageIcon("player2/left3.gif"),getImageIcon("player2/left4.gif")),
					new JLabelContainer(getImageIcon("player2/right1.gif"),getImageIcon("player2/right2.gif"),getImageIcon("player2/right3.gif"),getImageIcon("player2/right4.gif"))	
					);
			player_3 = new CharacterJLabelContainer ( new JLabelContainer(getImageIcon("player3/up1.gif"),getImageIcon("player3/up2.gif"),getImageIcon("player3/up3.gif"),getImageIcon("player3/up4.gif")),
					new JLabelContainer(getImageIcon("player3/down1.gif"),getImageIcon("player3/down2.gif"),getImageIcon("player3/down3.gif"),getImageIcon("player3/down4.gif")),
					new JLabelContainer(getImageIcon("player3/left1.gif"),getImageIcon("player3/left2.gif"),getImageIcon("player3/left3.gif"),getImageIcon("player3/left4.gif")),
					new JLabelContainer(getImageIcon("player3/right1.gif"),getImageIcon("player3/right2.gif"),getImageIcon("player3/right3.gif"),getImageIcon("player3/right4.gif"))	
					);
			player_4 = new CharacterJLabelContainer ( new JLabelContainer(getImageIcon("player4/up1.gif"),getImageIcon("player4/up2.gif"),getImageIcon("player4/up3.gif"),getImageIcon("player4/up4.gif")),
					new JLabelContainer(getImageIcon("player4/down1.gif"),getImageIcon("player4/down2.gif"),getImageIcon("player4/down3.gif"),getImageIcon("player4/down4.gif")),
					new JLabelContainer(getImageIcon("player4/left1.gif"),getImageIcon("player4/left2.gif"),getImageIcon("player4/left3.gif"),getImageIcon("player4/left4.gif")),
					new JLabelContainer(getImageIcon("player4/right1.gif"),getImageIcon("player4/right2.gif"),getImageIcon("player4/right3.gif"),getImageIcon("player4/right4.gif"))	
					);

			
			zombieJLabelContainers=new ZombieJLabelContainer[20];
			for (int i=0; i<20; i++)
			{
				zombieJLabelContainers[i]=new ZombieJLabelContainer ( new JLabelContainer(getImageIcon("zombie/up1.gif"),getImageIcon("zombie/up2.gif"),getImageIcon("zombie/up3.gif"),getImageIcon("zombie/up4.gif")),
						new JLabelContainer(getImageIcon("zombie/down1.gif"),getImageIcon("zombie/down2.gif"),getImageIcon("zombie/down3.gif"),getImageIcon("zombie/down4.gif")),
						new JLabelContainer(getImageIcon("zombie/left1.gif"),getImageIcon("zombie/left2.gif"),getImageIcon("zombie/left3.gif"),getImageIcon("zombie/left4.gif")),
						new JLabelContainer(getImageIcon("zombie/right1.gif"),getImageIcon("zombie/right2.gif"),getImageIcon("zombie/right3.gif"),getImageIcon("zombie/right4.gif")),				
						new AttackJLabelContainer(getImageIcon("zombie/upATK1.gif"),getImageIcon("zombie/upATK2.gif"),getImageIcon("zombie/upATK3.gif"),getImageIcon("zombie/upATK4.gif")),
						new AttackJLabelContainer(getImageIcon("zombie/downATK1.gif"),getImageIcon("zombie/downATK2.gif"),getImageIcon("zombie/downATK3.gif"),getImageIcon("zombie/downATK4.gif")),
						new AttackJLabelContainer(getImageIcon("zombie/leftATK1.gif"),getImageIcon("zombie/leftATK2.gif"),getImageIcon("zombie/leftATK3.gif"),getImageIcon("zombie/leftATK4.gif")),
						new AttackJLabelContainer(getImageIcon("zombie/rightATK1.gif"),getImageIcon("zombie/rightATK2.gif"),getImageIcon("zombie/rightATK3.gif"),getImageIcon("zombie/rightATK4.gif"))											
						);
			}
			bossJLabelContainers=new ZombieJLabelContainer[15];
			for (int i=0; i<15; i++)
			{
				bossJLabelContainers[i]=new ZombieJLabelContainer ( new JLabelContainer(getImageIcon("boss/up1.gif"),getImageIcon("boss/up2.gif"),getImageIcon("boss/up3.gif"),getImageIcon("boss/up4.gif")),
						new JLabelContainer(getImageIcon("boss/down1.gif"),getImageIcon("boss/down2.gif"),getImageIcon("boss/down3.gif"),getImageIcon("boss/down4.gif")),
						new JLabelContainer(getImageIcon("boss/left1.gif"),getImageIcon("boss/left2.gif"),getImageIcon("boss/left3.gif"),getImageIcon("boss/left4.gif")),
						new JLabelContainer(getImageIcon("boss/right1.gif"),getImageIcon("boss/right2.gif"),getImageIcon("boss/right3.gif"),getImageIcon("boss/right4.gif")),				
						new AttackJLabelContainer(getImageIcon("boss/upATK1.gif"),getImageIcon("boss/upATK2.gif"),getImageIcon("boss/upATK1.gif"),getImageIcon("boss/upATK2.gif")),
						new AttackJLabelContainer(getImageIcon("boss/downATK1.gif"),getImageIcon("boss/downATK2.gif"),getImageIcon("boss/downATK1.gif"),getImageIcon("boss/downATK2.gif")),
						new AttackJLabelContainer(getImageIcon("boss/leftATK1.gif"),getImageIcon("boss/leftATK2.gif"),getImageIcon("boss/leftATK1.gif"),getImageIcon("boss/leftATK2.gif")),
						new AttackJLabelContainer(getImageIcon("boss/rightATK1.gif"),getImageIcon("boss/rightATK2.gif"),getImageIcon("boss/rightATK1.gif"),getImageIcon("boss/rightATK2.gif"))											
						);
			}

			// End of Initialize 4 direction movement picture of characters	
        
			// Initialize blood JLabel
			bloodIcon = new ImageIcon[3];
			
			bloodIcon[0] =getImageIcon("blood/0.png");
			bloodLabel0=new JLabel(bloodIcon[0]);
			bloodLabel0.setBounds(0, 0,  bloodIcon[0].getIconWidth(),  bloodIcon[0].getIconHeight());
			
			bloodIcon[1] =getImageIcon("blood/1.png");
			bloodLabel1=new JLabel(bloodIcon[1]);
			bloodLabel1.setBounds(0, 0,  bloodIcon[1].getIconWidth(),  bloodIcon[1].getIconHeight());
			
			bloodIcon[2] =getImageIcon("blood/2.png");
			bloodLabel2=new JLabel(bloodIcon[2]);
			bloodLabel2.setBounds(0, 0,  bloodIcon[2].getIconWidth(),  bloodIcon[2].getIconHeight());     
			// End of Initialize blood JLabel
			
			//Initialize explosion JLabel
			explosionIcon = getImageIcon("explosion.gif");
			explosionLabel = new JLabel(explosionIcon);
			explosionLabel.setBounds(0, 0, explosionIcon.getIconWidth(), explosionIcon.getIconHeight());
			//End of Initialize explosion JLabel
			
			//Initialize box JLabel
			boxIcon = getImageIcon("box.gif");
			boxLabel = new JLabel(boxIcon);
			boxLabel.setBounds(0, 0, boxIcon.getIconWidth(), boxIcon.getIconHeight());
			//End of Initialize box JLabel

			// Initialize 4 direction movement picture of bullets	
        
			sbullet = new bulletIconContainer(getImageIcon("sbullet/up.gif"),getImageIcon("sbullet/down.gif"),getImageIcon("sbullet/left.gif"),getImageIcon("sbullet/right.gif"));
			lbullet = new bulletIconContainer(getImageIcon("lbullet/up.gif"),getImageIcon("lbullet/down.gif"),getImageIcon("lbullet/left.gif"),getImageIcon("lbullet/right.gif"));
    
			//End of Initialize 4 direction movement picture of bullets	
        
        
			// Initialize dynamic Words
			Font font0 = new Font("Candara", 3, 30);
			Font font1 = new Font("Times New Roman", 1, 20);
			Font font2 = new Font("Times New Roman", 1, 14);
        
		
			currentScoreLabel1 = new JLabel((new StringBuilder()).append("Score: ").append(Integer.toString(score1)).toString());
			currentScoreLabel1.setFont(font0);
			currentScoreLabel1.setForeground(Color.YELLOW);
			currentScoreLabel1.setBounds(261, 0, 150, 50);
			currentScoreLabel1.setVisible(false);
			lpane.add(currentScoreLabel1, new Integer(10));
		
			//user 1
			hpLabel1 = new JLabel((new StringBuilder()).append("HP: ").append(Integer.toString(healthPoint1)).toString());
			hpLabel1.setFont(font1);
			hpLabel1.setForeground(Color.BLACK);
			hpLabel1.setBackground(new Color(255,255,255,80));
			hpLabel1.setOpaque(true);
			hpLabel1.setBounds(10, 30, 150, 25);
			hpLabel1.setVisible(false);
			lpane.add(hpLabel1, new Integer(10));

			weaponLabel1 = new JLabel("Weapon: Pistol");
			weaponLabel1.setFont(font2);
			weaponLabel1.setForeground(Color.BLACK);
			weaponLabel1.setBackground(new Color(255,255,255,80));
			weaponLabel1.setOpaque(true);
			weaponLabel1.setBounds(10, 55, 150, 25);
			weaponLabel1.setVisible(false);
			lpane.add(weaponLabel1, new Integer(10));

			ammoLabel1 = new JLabel((new StringBuilder()).append("Ammo: ").append(Integer.toString(ammo1)).toString());
			ammoLabel1.setFont(font2);
			ammoLabel1.setForeground(Color.BLACK);
			ammoLabel1.setBackground(new Color(255,255,255,80));
			ammoLabel1.setOpaque(true);
			ammoLabel1.setBounds(10, 80, 150, 25);
			ammoLabel1.setVisible(false);
			lpane.add(ammoLabel1, new Integer(10));
			
			//user 2
			hpLabel2 = new JLabel((new StringBuilder()).append("HP: ").append(Integer.toString(healthPoint2)).toString());
			hpLabel2.setFont(font1);
			hpLabel2.setForeground(Color.BLACK);
			hpLabel2.setBackground(new Color(255,255,255,80));
			hpLabel2.setOpaque(true);
			hpLabel2.setBounds(512, 30, 150, 25);
			hpLabel2.setVisible(false);
			lpane.add(hpLabel2, new Integer(10));

			weaponLabel2 = new JLabel("Weapon: Pistol");
			weaponLabel2.setFont(font2);
			weaponLabel2.setForeground(Color.BLACK);
			weaponLabel2.setBackground(new Color(255,255,255,80));
			weaponLabel2.setOpaque(true);
			weaponLabel2.setBounds(512, 55, 150, 25);
			weaponLabel2.setVisible(false);
			lpane.add(weaponLabel2, new Integer(10));

			ammoLabel2 = new JLabel((new StringBuilder()).append("Ammo: ").append(Integer.toString(ammo2)).toString());
			ammoLabel2.setFont(font2);
			ammoLabel2.setForeground(Color.BLACK);
			ammoLabel2.setBackground(new Color(255,255,255,80));
			ammoLabel2.setOpaque(true);
			ammoLabel2.setBounds(512, 80, 150, 25);
			ammoLabel2.setVisible(false);
			lpane.add(ammoLabel2, new Integer(10));
			// End of Initialize dynamic Words
			

		
			
			// Initialize player's key control@Alex
			
			user1Up = 87;
			user1Down = 88;
			user1Left = 65;
			user1Right = 68;
			user1Shoot = 83;
			user1Leftweapon = 81;
			user1Rightweapon = 69;
			user2Up = 38;
			user2Down = 40;
			user2Left = 37;
			user2Right = 39;
			user2Shoot = 98;
			user2Leftweapon = 97;
			user2Rightweapon = 99;
			
			// Initialize Ranking
			ranking = new Ranking();
			
			//End of By Alex

			addKeyListener(this);
			Thread thread = new Thread(this);
			thread.start();     
            
    }

		public void run()
		{
			
			user1=new Hero(player1,20,20,10,100,background,7,hpLabel1,weaponLabel1, ammoLabel1);	// HIHI
			
			user2=new Hero(player_1,400,400,10,100,background,8,hpLabel2,weaponLabel2, ammoLabel2);
    	
		ak47Label.setLocation(150, 260);//@Alex
		
    	//@Alex
		while ( !isStart)
		{			
			// Set the AK47 icon align the menu 
			switch(choose){
				case 1:
					if(user1.up) {
						ak47Label.setLocation(150, 350);
						choose=3;
					}
					else if (user1.down) {
						ak47Label.setLocation(150, 300);
						choose=2;
					}
					break;
				case 2:
					if(user1.up) {
						ak47Label.setLocation(150, 250);
						choose=1;
					}
					else if (user1.down) {
						ak47Label.setLocation(150, 350);
						choose=3;
					}
					break;
				case 3:
					if(user1.up) {
						ak47Label.setLocation(150, 300);
						choose=2;
					}
					else if (user1.down) {
						ak47Label.setLocation(150, 250);
						choose=1;
					}
					break;
			}
			//Single Player
			if (choose ==1 && user1.shoot)
			{
				removeMenu();
				addPlayMenu(1);
			}
			else if (choose ==2 && user1.shoot)
			{
				removeMenu();
				addPlayMenu(2);
			}
			else if (choose ==3 && user1.shoot)
			{
				ranking.rankingPanel.setBounds(0, 0, 672, 480);
				lpane.add(ranking.rankingPanel,new Integer(12));
				rankingLabel.setVisible(true);
				ranking.rankingPanel.setVisible(true);
				ranking.rankingPanel.setOpaque(false);
				boolean isExit=false;
				while(!isExit){
					try
					{
						Thread.sleep(90L);
					}
					catch(InterruptedException ex) { } // pause a little bit
					if(user1.shoot){
						user1.shoot = false;
						isExit=true;
					}
				}
				rankingLabel.setVisible(false);
				ranking.rankingPanel.setVisible(false);
			}
			
			else if (choose ==4 && user1.shoot)
			{
			}
			
			try
            {
                Thread.sleep(90L);
            }
            catch(InterruptedException ex) { } // pause a little bit
		}

            try
            {
                Thread.sleep(5L);
            }
            catch(InterruptedException ex) { } // pause a little bit


       
    		if (numOfPlayers==1)
			{
				////////////////////
				if (selectMaps==1)
				{
					lpane.add(bg1Label, new Integer(0) );
					background= new Background(bg1);
				}
				else if (selectMaps==2)
				{
					lpane.add(bg2Label, new Integer(0) );
					background= new Background(bg2);
				}
				switch (selectCharacter1s)
				{
				case 1:
					user1=new Hero(player1,20,20,10,100,background,7,hpLabel1,weaponLabel1, ammoLabel1);	
					break;
				case 2:
					user1=new Hero(player2,20,20,10,100,background,7,hpLabel1,weaponLabel1, ammoLabel1);
					break;
				case 3:
					user1=new Hero(player3,20,20,10,100,background,7,hpLabel1,weaponLabel1, ammoLabel1);
					break;
				case 4:
					user1=new Hero(player4,20,20,10,100,background,7,hpLabel1,weaponLabel1, ammoLabel1);
					break;
				}
				//////////////////
				
				user1.isAlive = true;
				user1.startAnimation();
				(new BulletGenerator(user1)).startAnimation();
				
				(new ZombieGenerator( zombieJLabelContainers,bossJLabelContainers, background ,user1, user2) ).startAnimation();
			}
			if (numOfPlayers==2)
			{
				/////////////////
				if (selectMaps==1)
				{
					lpane.add(bg1Label, new Integer(0) );
					background= new Background(bg1);
				}
				else if (selectMaps==2)
				{
					lpane.add(bg2Label, new Integer(0) );
					background= new Background(bg2);
				}
				////////////////////
				
				switch (selectCharacter1s)
				{
				case 1:
					user1=new Hero(player1,20,20,10,100,background,7,hpLabel1,weaponLabel1, ammoLabel1);	
					break;
				case 2:
					user1=new Hero(player2,20,20,10,100,background,7,hpLabel1,weaponLabel1, ammoLabel1);
					break;
				case 3:
					user1=new Hero(player3,20,20,10,100,background,7,hpLabel1,weaponLabel1, ammoLabel1);
					break;
				case 4:
					user1=new Hero(player4,20,20,10,100,background,7,hpLabel1,weaponLabel1, ammoLabel1);
					break;
				}
				
				user1.isAlive = true;
				user1.startAnimation();	
				(new BulletGenerator(user1)).startAnimation();
				
				switch (selectCharacter2s)
				{
				case 1:
					user2=new Hero(player_1,100,20,10,100,background,8,hpLabel2,weaponLabel2, ammoLabel2);	
					break;
				case 2:
					user2=new Hero(player_2,100,20,10,100,background,8,hpLabel2,weaponLabel2, ammoLabel2);
					break;
				case 3:
					user2=new Hero(player_3,100,20,10,100,background,8,hpLabel2,weaponLabel2, ammoLabel2);
					break;
				case 4:
					user2=new Hero(player_4,100,20,10,100,background,8,hpLabel2,weaponLabel2, ammoLabel2);
					break;
				}
				
				user2.isAlive = true;
				user2.startAnimation();	
				
				(new BoxGenerator()).startAnimation();
				(new BulletGenerator(user2)).startAnimation();
				
				(new ZombieGenerator( zombieJLabelContainers,bossJLabelContainers, background ,user1, user2) ).startAnimation();

				
			}
			
			currentScoreLabel1.setVisible(true);
			hpLabel1.setVisible(true);
			weaponLabel1.setVisible(true);
			ammoLabel1.setVisible(true);
			
			if (numOfPlayers == 2) {
				hpLabel2.setVisible(true);
				weaponLabel2.setVisible(true);
				ammoLabel2.setVisible(true);
			}
            
            bgMusic.loop();
			(new BoxGenerator()).startAnimation();
        
		}

		


		public static void newThread()
		{
			
			
			
		
			
			user1.startAnimation();	
			(new BulletGenerator(user1)).startAnimation();
		
			
			
		
			
			user2.startAnimation();	
			(new BulletGenerator(user2)).startAnimation();
    
        	(new ZombieGenerator( zombieJLabelContainers,bossJLabelContainers, background ,user1, user2) ).startAnimation();
    
      
		}

    


	// Set up centralized Key Control 
		public void keyPressed(KeyEvent keyevent)
		{
	
			//key control for player1
			if(keyevent.getKeyCode() == user1Up)
			{
				user1.up = true;
			}
			if(keyevent.getKeyCode() == user1Down)
			{
				user1.down = true;
         
			}
			if(keyevent.getKeyCode() == user1Left)
			{
				user1.left = true;
			}
			if(keyevent.getKeyCode() == user1Right)
			{
				user1.right = true;
			}
			if(keyevent.getKeyCode() == user1Shoot)
			{
				user1.shoot = true;
			}
			if(keyevent.getKeyCode() == user1Leftweapon)
			{
				user1.leftweapon = true;
				//Tim
				if (user1.currentWeapon != 0 && !user1.pauseSwitchWeapon)
					user1.currentWeapon--;
				user1.pauseSwitchWeapon = true;
			}
			if(keyevent.getKeyCode() == user1Rightweapon)
			{
				user1.rightweapon = true;
				//Tim
				if (user1.currentWeapon != 2 && !user1.pauseSwitchWeapon)
					user1.currentWeapon++;
				user1.pauseSwitchWeapon = true;
			}
			
			
			
			
			
			//key control for user2
			if(keyevent.getKeyCode() == user2Up)
			{
				user2.up = true;
			}
			if(keyevent.getKeyCode() == user2Down)
			{
				user2.down = true;
         
			}
			if(keyevent.getKeyCode() == user2Left)
			{
				user2.left = true;
			}
			if(keyevent.getKeyCode() == user2Right)
			{
				user2.right = true;
			}
			if(keyevent.getKeyCode() == user2Shoot)
			{
				user2.shoot = true;
			}
			if(keyevent.getKeyCode() == user2Leftweapon)
			{
				user2.leftweapon = true;
				//Tim
				if (user2.currentWeapon != 0 && !user1.pauseSwitchWeapon)
					user2.currentWeapon--;
				user2.pauseSwitchWeapon = true;
			}
			if(keyevent.getKeyCode() == user2Rightweapon)
			{
				user2.rightweapon = true;
				//Tim
				if (user2.currentWeapon != 2 && !user1.pauseSwitchWeapon)
					user2.currentWeapon++;
				user2.pauseSwitchWeapon = true;
			}
					
		}

		public void keyTyped(KeyEvent keyevent)
		{
		}

		public void keyReleased(KeyEvent keyevent)
		{	
			//key control for player1
			if(keyevent.getKeyCode() == 87)
			{
				user1.up = false;
			}
			if(keyevent.getKeyCode() == 88)
			{
				user1.down = false;
         
			}
			if(keyevent.getKeyCode() == 65)
			{
				user1.left = false;
			}
			if(keyevent.getKeyCode() == 68)
			{
				user1.right = false;
			}
			if(keyevent.getKeyCode() == 83)
			{
				user1.shoot = false;
				user1.pauseShoot = false;	//Tim
			}
			if(keyevent.getKeyCode() == 81)
			{
				user1.leftweapon = false;
				user1.pauseShoot = false;	//Tim
				user1.pauseSwitchWeapon = false;	//Tim
			}
			if(keyevent.getKeyCode() == 69)
			{
				user1.rightweapon = false;
				user1.pauseShoot = false;	//Tim
				user1.pauseSwitchWeapon = false;	//Tim
			}
			
			
			
			
			
			//key control for user2
			if(keyevent.getKeyCode() == 38)
			{
				user2.up = false;
			}
			if(keyevent.getKeyCode() == 40)
			{
				user2.down = false;
         
			}
			if(keyevent.getKeyCode() == 37)
			{
				user2.left = false;
			}
			if(keyevent.getKeyCode() == 39)
			{
				user2.right = false;
			}
			if(keyevent.getKeyCode() == 98)
			{
				user2.shoot = false;
				user2.pauseShoot = false;	//Tim
			}
			if(keyevent.getKeyCode() == 97)
			{
				user2.leftweapon = false;
				user2.pauseShoot = false;	//Tim
				user2.pauseSwitchWeapon = false;	//Tim
			}
			if(keyevent.getKeyCode() == 99)
			{
				user2.rightweapon = false;
				user2.pauseShoot = false;	//Tim
				user2.pauseSwitchWeapon = false;	//Tim
			}
		}

		// End of Set up Key Control      
    
    

}


