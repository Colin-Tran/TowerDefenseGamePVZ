import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;

public class Level {
	
	private int mode; //the game mode: easy, med, hard
	private int level; //1,2,3,etc. 
	private int maxNumberOfEnemies; //each level has a number of enemies that get created
	private int enemyCounter; //number of enemies created
	private long lastSpawnTime; //time when the enemy was last spawned


	public Level(int mode, int level) {
		this.mode = mode;
		this.level = level;
		maxNumberOfEnemies = 5 + 5 * level;	
		enemyCounter = 0;
		lastSpawnTime = 0;
	}
	
	public int getMaxNumEnemies() {
		return maxNumberOfEnemies;
	}
	
	private double secondsSinceLastSpawn() {
		return (System.currentTimeMillis()-lastSpawnTime)/1000;
	}
	
	public SlimeEnemy spawnEnemy(int x, int y, Object enemyPath)  {
		if (enemyCounter < maxNumberOfEnemies &&
				secondsSinceLastSpawn() > 1.5) {//wait 2 seconds between spawning a new enemy
			
			enemyCounter ++;
			lastSpawnTime = System.currentTimeMillis();
			System.out.println("spawning" + enemyCounter);
			
			File numberSpawned = new File("numEnemies.txt");
			{
			 try {
				 FileWriter writer = new FileWriter(numberSpawned);
				 writer.write(enemyCounter);
				 writer.close();
			    } catch(Exception e) {
			        System.out.println("something is wrong with file!");
			    }
			}
			if(level == 1) {
				return new SlimeEnemy(0, 330);
			}else if(level == 2) {
				if(enemyCounter%2 == 0)	{
					return new SuperSlimeEnemy(0, 330);
				}else {
					return new SlimeEnemy(0, 330);
				}
			}else {
				return new SuperSlimeEnemy(0, 330);
			}
		
		}
		//System.out.println("not spawning");
	/*	int counter = 0;
		Scanner obj = new Scanner(System.in);
		if(obj.hasNext("spawning")) {
			counter++;
		}
		System.out.println(counter);*/
		
		return null; //no more enemies can be spawn at this point
	}

	public int getLevelCounter() {
		return level;
	}
	
	
	
}

