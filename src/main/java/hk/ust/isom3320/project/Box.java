package hk.ust.isom3320.project;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Box
	implements Runnable
{
	/* ================================ Variables ================================ */
	
	public static int numOfDisplayedBoxes;
	
	private int xcoord, ycoord;
	private JLabel boxLabel;
	private int boxWidth, boxHeight;				
	Font giftFont[];
	JLabel giftLabel[];	//0 - add machine gun; 1 - add rocket; 2 - add healthPoint
	
	
	/* ================================ Constructor ================================ */

	public Box()
	{
		numOfDisplayedBoxes = 0;
		
		/*--- Start: instantiate the JLabel for the box ---*/
		boxWidth = MyGame.boxIcon.getIconWidth();
		boxHeight = MyGame.boxIcon.getIconHeight();
		
		boxLabel = new JLabel(MyGame.boxIcon);
		boxLabel.setBounds(0, 0, boxWidth, boxHeight);
		boxLabel.setOpaque(false);
		boxLabel.setVisible(false);
		MyGame.addJLabelToLpane(boxLabel, 3);
		/*--- End: instantiate the JLabel for the box ---*/
		
		/*--- Start: instantiate the JLabel for gift ---*/
		giftFont = new Font[3];
		giftLabel = new JLabel[3];
		
		setGiftLabel(0, "+80 bullets (machine gun)", 170, 20);
		setGiftLabel(1, "+10 rockets (cannon)", 135, 20);
		setGiftLabel(2, "+30 HP", 50, 20);
		/*--- End: instantiate the JLabel for gift ---*/
	}
	
	
	/* ================================ Run ================================ */

	public void run()
	{
		numOfDisplayedBoxes++;
		
		//load the box and display it
		displayBox();
		
		//determine whether the box is unloaded
		while (!isUnloaded()) {			
			try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException interruptedexception) {}
		}
	}
	
	
	/* ================================ Methods ================================ */

	private void displayBox()
	{
		//pick a grid which is empty
		int i = 0, j = 0;
		do {
			i = (int)(Math.random()*10);
			j = (int)(Math.random()*14);
		} while (MyGame.background.grid[i][j] != 0);
		
		//set x- and y-coordinate
		xcoord = j*48 + 8;
		ycoord = i*48 + 8;
		
		//display the box
		boxLabel.setLocation(xcoord, ycoord);
		boxLabel.setVisible(true);
	}
	
	private boolean isUnloaded() {
		Hero user1 = MyGame.user1, user2 = MyGame.user2;
		
		if (user1.xcoord + 24 > xcoord - 5
				&& user1.xcoord + 24 < xcoord + boxWidth + 5
				&& user1.ycoord - 10 > ycoord - 5
				&& user1.ycoord - 10 < ycoord + boxHeight + 5)
		{
			boxLabel.setVisible(false);
			deliverGift(user1);
			return true;
		}
		if (user2.xcoord + 24 > xcoord
				&& user2.xcoord + 24 < xcoord + boxWidth
				&& user2.ycoord - 10 > ycoord
				&& user2.ycoord - 10 < ycoord + boxHeight)
		{
			boxLabel.setVisible(false);
			deliverGift(user2);
			return true;
		}
		return false;
	}
	
	private void deliverGift(Hero user) {
		int coupon = (int)(Math.random()*4);
		
		if (coupon < 1) {
			addBullet(user, 1, 80);
			printGift(user, 0, 65, 55);
		}
		else if (coupon < 2) {
			addBullet(user, 2, 10);
			printGift(user, 1, 52, 55);
		}
		else if (coupon < 4) {
			addHealthPoint(user, 30);
			printGift(user, 2, 10, 55);
		}
	}
	
	private void addBullet(Hero user, int weapon, int recoverBullets) {
		if (user.ammo[weapon] + recoverBullets > user.ammoMax[weapon])
			user.ammo[weapon] = user.ammoMax[weapon];
		else
			user.ammo[weapon] += recoverBullets;
	}
	
	private void addHealthPoint(Hero user, int recoverHP) {
		if (user.healthPoint + recoverHP > user.defaultHealthPoint)
			user.healthPoint = user.defaultHealthPoint;
		else
			user.healthPoint += recoverHP;
	}
	
	private void setGiftLabel(int i, String text, int width, int height) {
		giftFont[i] = new Font("Candara", 3, 15);
		giftLabel[i] = new JLabel(text);
		giftLabel[i].setFont(giftFont[i]);
		giftLabel[i].setForeground(new Color(255, 255, 255, 255));
		giftLabel[i].setBounds(0, 0, width, height);
		giftLabel[i].setVisible(false);
		MyGame.addJLabelToLpane(giftLabel[i], 9);
	}
	
	private void printGift(Hero user, int i, int xShift, int yShift) {
		int x = user.xcoord - xShift, y = user.ycoord - yShift;
		for (int j = 0; j < 60; j++) {
			giftLabel[i].setLocation(x, y);
			giftLabel[i].setVisible(true);
			giftLabel[i].setForeground(new Color(255, 255, 255, 255-j*4));
			y -= 1;
			try
			{
				Thread.sleep(35);
			}
			catch (InterruptedException ex) {}
		}
		giftLabel[i].setVisible(false);
	}
	
	public void startAnimation()
	{
		new Thread(this).start();
	}
}
