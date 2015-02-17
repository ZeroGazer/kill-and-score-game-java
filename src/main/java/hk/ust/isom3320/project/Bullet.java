package hk.ust.isom3320.project;

import javax.swing.JLabel;

public class Bullet
	implements Runnable
{
	/* ================================ Variables ================================ */
	
	private int initXcoord, initYcoord;
	private int xcoord, ycoord;
	private char direction;
	private JLabel sbulletLabelUp,sbulletLabelDown,sbulletLabelLeft,sbulletLabelRight;
	private JLabel lbulletLabelUp,lbulletLabelDown,lbulletLabelLeft,lbulletLabelRight;
	private JLabel explosionLabel;
	
	private int[] weaponRange, weaponSpeed;
	
	private Character shooter;

	boolean isFlyable;
	boolean outOfRange, hitObstacle, hitZombie, hitHero;
	
	
	/* ================================ Constructor ================================ */

	public Bullet()
	{		
		/*--- Start: set default range for each weapon ---*/
		weaponRange = new int[3];
		weaponSpeed = new int[3];
		
		//Pistol
		weaponRange[0] = 200;
		weaponSpeed[0] = 5;
		
		//Machine gun
		weaponRange[1] = 270;
		weaponSpeed[1] = 6;
		
		//Rocket
		weaponRange[2] = 999;
		weaponSpeed[2] = 8;
		/*--- End: set default range for each weapon ---*/
		
		/*--- Start: instantiate the JLabel for the 4 directions of each type of bullet ---*/
		//sbullet
		sbulletLabelUp = new JLabel(MyGame.sbullet.up);
		sbulletLabelUp.setBounds(0, 0, MyGame.sbullet.verticalWidth, MyGame.sbullet.verticalHeight);
		sbulletLabelUp.setOpaque(false);
		sbulletLabelUp.setVisible(false);
		MyGame.addJLabelToLpane(sbulletLabelUp, 8);
		
		sbulletLabelDown = new JLabel(MyGame.sbullet.down);
		sbulletLabelDown.setBounds(0, 0, MyGame.sbullet.verticalWidth, MyGame.sbullet.verticalHeight);
		sbulletLabelDown.setOpaque(false);
		sbulletLabelDown.setVisible(false);
		MyGame.addJLabelToLpane(sbulletLabelDown, 8);
		
		sbulletLabelLeft = new JLabel(MyGame.sbullet.left);
		sbulletLabelLeft.setBounds(0, 0, MyGame.sbullet.horizontalWidth, MyGame.sbullet.horizontalHeight);
		sbulletLabelLeft.setOpaque(false);
		sbulletLabelLeft.setVisible(false);
		MyGame.addJLabelToLpane(sbulletLabelLeft, 8);

		sbulletLabelRight = new JLabel(MyGame.sbullet.right);
		sbulletLabelRight.setBounds(0, 0, MyGame.sbullet.horizontalWidth, MyGame.sbullet.horizontalHeight);
		sbulletLabelRight.setOpaque(false);
		sbulletLabelRight.setVisible(false);
		MyGame.addJLabelToLpane(sbulletLabelRight, 8);
		
		//--lbullet
		lbulletLabelUp = new JLabel(MyGame.lbullet.up);
		lbulletLabelUp.setBounds(0, 0, MyGame.lbullet.verticalWidth, MyGame.lbullet.verticalHeight);
		lbulletLabelUp.setOpaque(false);
		lbulletLabelUp.setVisible(false);
		MyGame.addJLabelToLpane(lbulletLabelUp, 8);
		
		lbulletLabelDown = new JLabel(MyGame.lbullet.down);
		lbulletLabelDown.setBounds(0, 0, MyGame.lbullet.verticalWidth, MyGame.lbullet.verticalHeight);
		lbulletLabelDown.setOpaque(false);
		lbulletLabelDown.setVisible(false);
		MyGame.addJLabelToLpane(lbulletLabelDown, 8);
		
		lbulletLabelLeft = new JLabel(MyGame.lbullet.left);
		lbulletLabelLeft.setBounds(0, 0, MyGame.lbullet.horizontalWidth, MyGame.lbullet.horizontalHeight);
		lbulletLabelLeft.setOpaque(false);
		lbulletLabelLeft.setVisible(false);
		MyGame.addJLabelToLpane(lbulletLabelLeft, 8);
		
		lbulletLabelRight = new JLabel(MyGame.lbullet.right);
		lbulletLabelRight.setBounds(0, 0, MyGame.lbullet.horizontalWidth, MyGame.lbullet.horizontalHeight);
		lbulletLabelRight.setOpaque(false);
		lbulletLabelRight.setVisible(false);
		MyGame.addJLabelToLpane(lbulletLabelRight, 8);
		/*--- End: instantiate the JLabel for the 4 directions of each type of bullet ---*/
		
		/*--- Start: instantiate the JLabel for explosion of bullet ---*/
		explosionLabel = new JLabel(MyGame.explosionIcon);
		explosionLabel.setBounds(0, 0, MyGame.explosionIcon.getIconWidth(), MyGame.explosionIcon.getIconHeight());
		explosionLabel.setOpaque(false);
		explosionLabel.setVisible(false);
		MyGame.addJLabelToLpane(explosionLabel, 9);
		/*--- End: instantiate the JLabel for explosion of bullet ---*/
	}
	
	
	/* ================================ Run ================================ */

	public void run()
	{
		MyGame.weaponAu[shooter.currentWeapon].play();
		
		isFlyable = true;
		
		//initialize the x- and y-coordinate of the bullet
		initBulletPosition();
		
		//determine whether the bullet should fly onward
		while (isFlyable) {
			outOfRange = checkOutOfRange();
			hitObstacle = checkHitObstacle();
			hitZombie = checkHitZombie();
			hitHero = checkHitHero();
			System.out.println("---------------------------------");
			System.out.println("outOfRange: " + outOfRange);
			System.out.println("hitObstacle: " + hitObstacle);
			System.out.println("hitZombie: " + hitZombie);
			System.out.println("hitHero: " + hitHero);
			
			if (outOfRange || hitObstacle || hitZombie || hitHero) {
				isFlyable = false;
				setBulletInvisible();
				try
		        {
		            Thread.sleep(280);
		        }
		        catch(InterruptedException interruptedexception) {}
				explosionLabel.setVisible(false);
			}
			else
				flyOnward();
			
			try
            {
                Thread.sleep(25);
            }
            catch(InterruptedException interruptedexception) {}
		}
	}
	
	
	/* ================================ Methods ================================ */
	
	public void setShooter(Character shooter) {
		this.shooter = shooter;
	}
	
	private void setBulletInvisible() {
		switch (direction) {
		case 'u':
			sbulletLabelUp.setVisible(false);
			lbulletLabelUp.setVisible(false);
			break;
		case 'd':
			sbulletLabelDown.setVisible(false);
			lbulletLabelDown.setVisible(false);
			break;
		case 'l':
			sbulletLabelLeft.setVisible(false);
			lbulletLabelLeft.setVisible(false);
			break;
		case 'r':
			sbulletLabelRight.setVisible(false);
			lbulletLabelRight.setVisible(false);
			break;
		}
	}
	
	private void initBulletPosition() {
		//initialize the position of the bullet
		switch (shooter.lastmove) {
		case 'u':
			if (shooter.currentWeapon == 0 || shooter.currentWeapon == 1) {
				initXcoord = xcoord = shooter.xcoord + 22;
				initYcoord = ycoord = shooter.ycoord - 47;
				sbulletLabelUp.setLocation(xcoord, ycoord);
				sbulletLabelUp.setVisible(true);
			}
			else if (shooter.currentWeapon == 2) {
				initXcoord = xcoord = shooter.xcoord;
				initYcoord = ycoord = shooter.ycoord - 82;
				lbulletLabelUp.setLocation(xcoord, ycoord);
				lbulletLabelUp.setVisible(true);
			}
			direction = 'u';
			break;
		case 'd':
			if (shooter.currentWeapon == 0 || shooter.currentWeapon == 1) {
				initXcoord = xcoord = shooter.xcoord;
				initYcoord = ycoord = shooter.ycoord - 14;
				sbulletLabelDown.setLocation(xcoord, ycoord);
				sbulletLabelDown.setVisible(true);
			}
			else if (shooter.currentWeapon == 2) {
				initXcoord = xcoord = shooter.xcoord - 21;
				initYcoord = ycoord = shooter.ycoord - 12;
				lbulletLabelDown.setLocation(xcoord, ycoord);
				lbulletLabelDown.setVisible(true);
			}
			direction = 'd';
			break;
		case 'l':
			if (shooter.currentWeapon == 0 || shooter.currentWeapon == 1) {
				initXcoord = xcoord = shooter.xcoord - 11;
				initYcoord = ycoord = shooter.ycoord - 36;
				sbulletLabelLeft.setLocation(xcoord, ycoord);
				sbulletLabelLeft.setVisible(true);
			}
			else if (shooter.currentWeapon == 2) {
				initXcoord = xcoord = shooter.xcoord - 45;
				initYcoord = ycoord = shooter.ycoord - 57;
				lbulletLabelLeft.setLocation(xcoord, ycoord);
				lbulletLabelLeft.setVisible(true);
			}
			direction = 'l';
			break;
		case 'r':
			if (shooter.currentWeapon == 0 || shooter.currentWeapon == 1) {
				initXcoord = xcoord = shooter.xcoord + 28;
				initYcoord = ycoord = shooter.ycoord - 22;
				sbulletLabelRight.setLocation(xcoord, ycoord);
				sbulletLabelRight.setVisible(true);
			}
			else if (shooter.currentWeapon == 2) {
				initXcoord = xcoord = shooter.xcoord + 25;
				initYcoord = ycoord = shooter.ycoord - 45;
				lbulletLabelRight.setLocation(xcoord, ycoord);
				lbulletLabelRight.setVisible(true);
			}
			direction = 'r';
			break;
		}
	}
	
	private void flyOnward() {
		JLabel up = (shooter.currentWeapon == 2) ? lbulletLabelUp : sbulletLabelUp;
		JLabel down = (shooter.currentWeapon == 2) ? lbulletLabelDown : sbulletLabelDown;
		JLabel left = (shooter.currentWeapon == 2) ? lbulletLabelLeft : sbulletLabelLeft;
		JLabel right = (shooter.currentWeapon == 2) ? lbulletLabelRight : sbulletLabelRight;
		
		switch (direction) {
		case 'u':
			ycoord -= weaponSpeed[shooter.currentWeapon];
			up.setLocation(xcoord, ycoord);
			break;
		case 'd':
			ycoord += weaponSpeed[shooter.currentWeapon];
			down.setLocation(xcoord, ycoord);
			break;
		case 'l':
			xcoord -= weaponSpeed[shooter.currentWeapon];
			left.setLocation(xcoord, ycoord);
			break;
		case 'r':
			xcoord += weaponSpeed[shooter.currentWeapon];
			right.setLocation(xcoord, ycoord);
			break;
		}
	}
	
	private boolean checkOutOfRange() {
		if ((Math.abs(xcoord - initXcoord) > weaponRange[shooter.currentWeapon])
				|| (Math.abs(ycoord - initYcoord) > weaponRange[shooter.currentWeapon])) {
			if (shooter.currentWeapon == 2)
				explode();
			return true;
		}
		else
			return false;
	}
	
	private boolean checkHitObstacle() {
		int[][] grid = MyGame.background.grid;
		int i = 0, j = 0;
		
		switch (direction) {
		case 'u':
			if (shooter.currentWeapon == 0 || shooter.currentWeapon == 1) {
				i = ycoord/48;
				j = (xcoord + MyGame.sbullet.verticalWidth/2)/48;
			}
			else if (shooter.currentWeapon == 2) {
				i = ycoord/48;
				j = (xcoord + MyGame.lbullet.verticalWidth/2)/48;
			}
			break;
		case 'd':
			if (shooter.currentWeapon == 0 || shooter.currentWeapon == 1) {
				i = (ycoord + MyGame.sbullet.verticalHeight)/48;
				j = (xcoord + MyGame.sbullet.verticalWidth/2)/48;
			}
			else if (shooter.currentWeapon == 2) {
				i = (ycoord + MyGame.lbullet.verticalHeight)/48;
				j = (xcoord + MyGame.lbullet.verticalWidth/2)/48;
			}
			break;
		case 'l':
			if (shooter.currentWeapon == 0 || shooter.currentWeapon == 1) {
				i = (ycoord + MyGame.sbullet.horizontalHeight/2)/48;
				j = xcoord/48;
			}
			else if (shooter.currentWeapon == 2) {
				i = (ycoord + MyGame.lbullet.horizontalHeight/2)/48;
				j = xcoord/48;
			}
			break;
		case 'r':
			if (shooter.currentWeapon == 0 || shooter.currentWeapon == 1) {
				i = (ycoord + MyGame.sbullet.horizontalHeight/2)/48;
				j = (xcoord + MyGame.sbullet.horizontalWidth)/48;
			}
			else if (shooter.currentWeapon == 2) {
				i = (ycoord + MyGame.lbullet.horizontalHeight/2)/48;
				j = (xcoord + MyGame.lbullet.horizontalWidth)/48;
			}
			break;
		}
		if ((xcoord < 0 || ycoord < 0) || (i >= 10 || j >= 14)) {
			if (shooter.currentWeapon == 2)
				explode();
			return true;
		}
		else if (grid[i][j] == 1 || grid[i][j] == 2 || grid[i][j] == 3
				|| grid[i][j] == 4 || grid[i][j] == 5 || grid[i][j] == 6) {
			if (shooter.currentWeapon == 2)
				explode();
			return true;
		}
		else
			return false;
	}
	
	private boolean checkHitZombie() {
		if (shooter instanceof Hero) {
			int bulletHorizontalWidth = 0, bulletHorizontalHeight = 0;
			int bulletVerticalWidth = 0, bulletVerticalHeight = 0;
			
			if (shooter.currentWeapon == 0 || shooter.currentWeapon == 1) {
				bulletHorizontalWidth = MyGame.sbullet.horizontalWidth;
				bulletHorizontalHeight = MyGame.sbullet.horizontalHeight;
				bulletVerticalWidth = MyGame.sbullet.verticalWidth;
				bulletVerticalHeight = MyGame.sbullet.verticalHeight;
			}
			else if (shooter.currentWeapon == 2) {
				bulletHorizontalWidth = MyGame.lbullet.horizontalWidth;
				bulletHorizontalHeight = MyGame.lbullet.horizontalHeight;
				bulletVerticalWidth = MyGame.lbullet.verticalWidth;
				bulletVerticalHeight = MyGame.lbullet.verticalHeight;
			}
			
			switch (direction) {
			case 'u':
				for (int i = 0; i < ZombieGenerator.zombies.length; i++)
					if (ZombieGenerator.zombies[i].isAlive)
						if (xcoord + bulletVerticalWidth/2 > ZombieGenerator.zombies[i].xcoord
								&& xcoord + bulletVerticalWidth/2 < ZombieGenerator.zombies[i].xcoord + 48
								&& ycoord < ZombieGenerator.zombies[i].ycoord
								&& ycoord > ZombieGenerator.zombies[i].ycoord - 48) {
							hurtCharacter(ZombieGenerator.zombies[i]);
							return true;
						}
				for (int i = 0; i < ZombieGenerator.bosses.length; i++)
					if (ZombieGenerator.bosses[i].isAlive)
						if (xcoord + bulletVerticalWidth/2 > ZombieGenerator.bosses[i].xcoord
								&& xcoord + bulletVerticalWidth/2 < ZombieGenerator.bosses[i].xcoord + 48
								&& ycoord < ZombieGenerator.bosses[i].ycoord
								&& ycoord > ZombieGenerator.bosses[i].ycoord - 48) {
							hurtCharacter(ZombieGenerator.bosses[i]);
							return true;
						}
				return false;
				
			case 'd':
				for (int i = 0; i < ZombieGenerator.zombies.length; i++)
					if (ZombieGenerator.zombies[i].isAlive)
						if (xcoord + bulletVerticalWidth/2 > ZombieGenerator.zombies[i].xcoord
								&& xcoord + bulletVerticalWidth/2 < ZombieGenerator.zombies[i].xcoord + 48
								&& ycoord + bulletVerticalHeight > ZombieGenerator.zombies[i].ycoord - 48
								&& ycoord + bulletVerticalHeight < ZombieGenerator.zombies[i].ycoord) {
							hurtCharacter(ZombieGenerator.zombies[i]);
							return true;
						}
				for (int i = 0; i < ZombieGenerator.bosses.length; i++)
					if (ZombieGenerator.bosses[i].isAlive)
						if (xcoord + bulletVerticalWidth/2 > ZombieGenerator.bosses[i].xcoord
								&& xcoord + bulletVerticalWidth/2 < ZombieGenerator.bosses[i].xcoord + 48
								&& ycoord + bulletVerticalHeight > ZombieGenerator.bosses[i].ycoord - 48
								&& ycoord + bulletVerticalHeight < ZombieGenerator.bosses[i].ycoord) {
							hurtCharacter(ZombieGenerator.bosses[i]);
							return true;
						}
				return false;

			case 'l':
				for (int i = 0; i < ZombieGenerator.zombies.length; i++)
					if (ZombieGenerator.zombies[i].isAlive)
						if (xcoord < ZombieGenerator.zombies[i].xcoord + 48
								&& xcoord > ZombieGenerator.zombies[i].xcoord
								&& ycoord + bulletHorizontalHeight/2 < ZombieGenerator.zombies[i].ycoord
								&& ycoord + bulletHorizontalHeight/2 > ZombieGenerator.zombies[i].ycoord - 48) {
							hurtCharacter(ZombieGenerator.zombies[i]);
							return true;
						}
				for (int i = 0; i < ZombieGenerator.bosses.length; i++)
					if (ZombieGenerator.bosses[i].isAlive)
						if (xcoord < ZombieGenerator.bosses[i].xcoord + 48
								&& xcoord > ZombieGenerator.bosses[i].xcoord
								&& ycoord + bulletHorizontalHeight/2 < ZombieGenerator.bosses[i].ycoord
								&& ycoord + bulletHorizontalHeight/2 > ZombieGenerator.bosses[i].ycoord - 48) {
							hurtCharacter(ZombieGenerator.bosses[i]);
							return true;
						}
				return false;

			case 'r':
				for (int i = 0; i < ZombieGenerator.zombies.length; i++)
					if (ZombieGenerator.zombies[i].isAlive)
						if (xcoord + bulletHorizontalWidth > ZombieGenerator.zombies[i].xcoord
								&& xcoord + bulletHorizontalWidth < ZombieGenerator.zombies[i].xcoord + 48
								&& ycoord + bulletHorizontalHeight/2 < ZombieGenerator.zombies[i].ycoord
								&& ycoord + bulletHorizontalHeight/2 > ZombieGenerator.zombies[i].ycoord - 48) {
							hurtCharacter(ZombieGenerator.zombies[i]);
							return true;
						}
				for (int i = 0; i < ZombieGenerator.bosses.length; i++)
					if (ZombieGenerator.bosses[i].isAlive)
						if (xcoord + bulletHorizontalWidth > ZombieGenerator.bosses[i].xcoord
								&& xcoord + bulletHorizontalWidth < ZombieGenerator.bosses[i].xcoord + 48
								&& ycoord + bulletHorizontalHeight/2 < ZombieGenerator.bosses[i].ycoord
								&& ycoord + bulletHorizontalHeight/2 > ZombieGenerator.bosses[i].ycoord - 48) {
							hurtCharacter(ZombieGenerator.bosses[i]);
							return true;
						}
				return false;
			}
		}
		return false;
	}
	
	private boolean checkHitHero() {
		if (shooter instanceof Zombie) {			
			int bulletHorizontalWidth = MyGame.sbullet.horizontalWidth;
			int bulletHorizontalHeight = MyGame.sbullet.horizontalHeight;
			int bulletVerticalWidth = MyGame.sbullet.verticalWidth;
			int bulletVerticalHeight = MyGame.sbullet.verticalHeight;
			
			switch (direction) {
			case 'u':
				if (MyGame.user1.isAlive)
					if (xcoord + bulletVerticalWidth/2 > MyGame.user1.xcoord
							&& xcoord + bulletVerticalWidth/2 < MyGame.user1.xcoord + 48
							&& ycoord < MyGame.user1.ycoord
							&& ycoord > MyGame.user1.ycoord - 48) {
						hurtCharacter(MyGame.user1);
						return true;
					}
				if (MyGame.user2.isAlive)
					if (xcoord + bulletVerticalWidth/2 > MyGame.user2.xcoord
							&& xcoord + bulletVerticalWidth/2 < MyGame.user2.xcoord + 48
							&& ycoord < MyGame.user2.ycoord
							&& ycoord > MyGame.user2.ycoord - 48) {
						hurtCharacter(MyGame.user2);
						return true;
					}
				return false;
				
			case 'd':
				if (MyGame.user1.isAlive)
					if (xcoord + bulletVerticalWidth/2 > MyGame.user1.xcoord
							&& xcoord + bulletVerticalWidth/2 < MyGame.user1.xcoord + 48
							&& ycoord + bulletVerticalHeight > MyGame.user1.ycoord - 48
							&& ycoord + bulletVerticalHeight < MyGame.user1.ycoord) {
						hurtCharacter(MyGame.user1);
						return true;
					}
				if (MyGame.user2.isAlive)
					if (xcoord + bulletVerticalWidth/2 > MyGame.user2.xcoord
							&& xcoord + bulletVerticalWidth/2 < MyGame.user2.xcoord + 48
							&& ycoord + bulletVerticalHeight > MyGame.user2.ycoord - 48
							&& ycoord + bulletVerticalHeight < MyGame.user2.ycoord) {
						hurtCharacter(MyGame.user2);
						return true;
					}
				return false;

			case 'l':
				if (MyGame.user1.isAlive)
					if (xcoord < MyGame.user1.xcoord + 48
							&& xcoord > MyGame.user1.xcoord
							&& ycoord + bulletHorizontalHeight/2 < MyGame.user1.ycoord
							&& ycoord + bulletHorizontalHeight/2 > MyGame.user1.ycoord - 48) {
						hurtCharacter(MyGame.user1);
						return true;
					}
				if (MyGame.user2.isAlive)
					if (xcoord < MyGame.user2.xcoord + 48
							&& xcoord > MyGame.user2.xcoord
							&& ycoord + bulletHorizontalHeight/2 < MyGame.user2.ycoord
							&& ycoord + bulletHorizontalHeight/2 > MyGame.user2.ycoord - 48) {
						hurtCharacter(MyGame.user2);
						return true;
					}
				return false;

			case 'r':
				if (MyGame.user1.isAlive)
					if (xcoord + bulletHorizontalWidth > MyGame.user1.xcoord
							&& xcoord + bulletHorizontalWidth < MyGame.user1.xcoord + 48
							&& ycoord + bulletHorizontalHeight/2 < MyGame.user1.ycoord
							&& ycoord + bulletHorizontalHeight/2 > MyGame.user1.ycoord - 48) {
						hurtCharacter(MyGame.user1);
						return true;
					}
				if (MyGame.user2.isAlive)
					if (xcoord + bulletHorizontalWidth > MyGame.user2.xcoord
							&& xcoord + bulletHorizontalWidth < MyGame.user2.xcoord + 48
							&& ycoord + bulletHorizontalHeight/2 < MyGame.user2.ycoord
							&& ycoord + bulletHorizontalHeight/2 > MyGame.user2.ycoord - 48) {
						hurtCharacter(MyGame.user2);
						return true;
					}
				return false;
			}
		}
		return false;
	}
	
	private void hurtCharacter(Character victim) {
		switch (shooter.currentWeapon) {
		case 0:
			victim.healthPoint -= 25;
			break;
		case 1:
			victim.healthPoint -= 25;
			break;
		case 2:
			victim.healthPoint -= 75;
			explode();
			break;
		}

		MyGame.hitZombieAu[(int)(Math.random()*2)].play();
		
		//reward points for shooting zombie
		if (victim instanceof Zombie && victim.healthPoint < 0) {
			if (victim.speed > ZombieGenerator.zombies[0].speed)
				MyGame.score1 += 30;
			else
				MyGame.score1 += 10;
			
			victim.isAlive = false;
			
			MyGame.currentScoreLabel1.setText((new StringBuilder()).append("Score: ").append(Integer.toString(MyGame.score1)).toString());
			
			Hero user = (Hero)shooter;
			user.numOfKills++;
			System.out.println("Number of Kills ------ " + user.numOfKills);
		}
		
		//display blood
		MyGame.displayBlood(victim);
	}
	
	private void explode() {
		MyGame.explodeAu.play();
		switch (direction) {
		case 'u':
			explosionLabel.setLocation(xcoord - 8, ycoord - 52);
			break;
		case 'd':
			explosionLabel.setLocation(xcoord - 8, ycoord);
			break;
		case 'l':
			explosionLabel.setLocation(xcoord - 35, ycoord - 30);
			break;
		case 'r':
			explosionLabel.setLocation(xcoord + 15, ycoord - 30);
			break;
		}
		explosionLabel.setVisible(true);
	}

	public void startAnimation()
	{
		new Thread(this).start();
	}
}
