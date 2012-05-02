package joe.games.disease;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	private int width;
	private int height;
	private int mousex;
	private int mousey;
	private Random rand;
	private ImageBank images;
	private boolean isMouseDown;
	private Thread clockSpace;
	private Clock clock;
	private boolean gameOver;
	
	private ArrayList<Enemy> enemies;
	
	/**
	 * Creates a Game with the graphics width and height values equal to the parsed values
	 * Also times the game from the time specified, game ends when the time in seconds reaches zero
	 * @param width
	 * @param height
	 * @param time
	 */
	public Game(int width, int height, int time){
		gameOver = false;
		this.width = width;
		this.height = height;
		rand = new Random();
		images = new ImageBank();
		enemies = genEnemies(5);
		clock = new Clock();
		clock.setFromTime(time);
		clock.setDownTo(0);
		clock.setTime(time);
		clockSpace = new Thread(clock);
		clockSpace.start();
	}
	
	/**
	 * Moves the game on one step and renders it to the parsed graphics object
	 * @param g
	 * @param isMouseDown
	 * @param mousex
	 * @param mousey
	 */
	public void tick(Graphics g, boolean isMouseDown, int mousex, int mousey){
		this.mousex = mousex;
		this.mousey = mousey;
		this.isMouseDown = isMouseDown;
		updateEnemies();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.drawString(""+clock.getTime(), 10, 10);
		for(int i=0; i<enemies.size(); i++){
			if(enemies.get(i).isSmile()){
				g.drawImage(images.getImage("smile"), enemies.get(i).getxPos(), enemies.get(i).getyPos(), null);
			}else{
				g.drawImage(images.getImage("skull"), enemies.get(i).getxPos(), enemies.get(i).getyPos(), null);
			}
			
		}
		
		paintCrossHairs(g, isMouseDown, mousex, mousey);
		if(clock.getTime() == 0){
			g.drawString("GAME OVER", width/2, height/2);
			gameOver = true;
		}
	}
	
	/**
	 * Generates an ArrayList of Enemy objects of an amount equal to the parsed value
	 * @param ammount
	 * @return
	 */
	public ArrayList<Enemy> genEnemies(int amount){
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		for(int i=0; i<amount; i++){
			enemies.add(new Enemy(rand.nextInt(width), rand.nextInt(height), 25, 25, height, width, 5));
		}
		return enemies;
	}
	
	/**
	 * calls the update function on all enemies
	 */
	public void updateEnemies(){
		for(int i=0; i<enemies.size(); i++){
			if(enemies.get(i).isInside(mousex, mousey) && isMouseDown){
				enemies.get(i).setSmile(false);
			}
			enemies.get(i).update();
		}
	}
	
	/**
	 * paints cross hairs to the screen when the left mouse button is down
	 * @param g
	 * @param isMouseDown
	 * @param mousex
	 * @param mousey
	 */
	public void paintCrossHairs(Graphics g, boolean isMouseDown, int mousex, int mousey){
		if(isMouseDown){
			Color before = g.getColor();
			Color transRed = new Color(1,0,0,0.5f);
			g.setColor(transRed);
			g.fillRoundRect(mousex-25, mousey-25, 50, 50, 50, 50);
			g.setColor(before);
		}
	}

	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
}
