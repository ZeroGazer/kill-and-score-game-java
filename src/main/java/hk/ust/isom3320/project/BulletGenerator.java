package hk.ust.isom3320.project;

public class BulletGenerator
	implements Runnable
{
	/* ================================ Variables ================================ */
	
	private final int numOfBullets = 30;
	private Bullet bullets[];
	private int bulletIndex;
	
	private Character shooter;
	
	
	/* ================================ Constructor ================================ */
	
	public BulletGenerator(Character shooter)
	{
		//set a shooter
		shooter.pauseShoot = false;
		this.shooter = shooter;
		
		//instantiate the bullets 
		this.bullets = new Bullet[numOfBullets];
		for (int i = 0; i < numOfBullets; i++) {
			bullets[i] = new Bullet();
		}
	}

	
	/* ================================ Run ================================ */
	
	public synchronized void run()
	{		
		bulletIndex = 0;
		while(true) //isAlive
		{
			
			if (!shooter.pauseShoot && shooter.shoot && shooter.ammo[shooter.currentWeapon] > 0)
			{
				if (shooter.currentWeapon != 0)
					shooter.ammo[shooter.currentWeapon]--;
				bullets[bulletIndex].setShooter(shooter);
				bullets[bulletIndex].startAnimation();

				//return to use the 1st bullet if all bullets have been consumed
				if(bulletIndex >= numOfBullets)
					bulletIndex = 0;

				//set the shooting frequency for each weapon
				switch (shooter.currentWeapon) {
				case 0:
					shooter.pauseShoot = true;
					try
					{
						Thread.sleep(300);
					}
					catch(InterruptedException interruptedexception) {}
					break;
				case 1:
					try
					{
						Thread.sleep(150);
					}
					catch(InterruptedException interruptedexception) {}
					break;
				case 2:
					shooter.pauseShoot = true;
					try
					{
						Thread.sleep(1200);
					}
					catch(InterruptedException interruptedexception) {}
					break;
				}
			}
			try
			{
				Thread.sleep(60);
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