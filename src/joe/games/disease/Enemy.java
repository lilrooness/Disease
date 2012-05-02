package joe.games.disease;

public class Enemy extends GameObject {
	
	private int xVell;
	private int yVell;
	
	private boolean up = true;
	private boolean right = true;
	private boolean smile = true;
	
	private int downBounds;
	private int rightBounds;
	
	private int speed;
	
	/**
	 * 
	 * @param xPos
	 * @param yPos
	 * @param width
	 * @param height
	 * @param downBounds
	 * @param rightBounds
	 * @param speed
	 */
	public Enemy(int xPos, int yPos, int width, int height, int downBounds, int rightBounds, int speed){
		super(xPos, yPos, width, height);
		xVell = 0;
		yVell = 0;
		
		this.downBounds = downBounds;
		this.rightBounds = rightBounds;
		this.speed = speed;
	}

	/**
	 * Updates the players position variables
	 */
	public void update(){
		checkCollisions();
		move();
		xPos += xVell;
		yPos +=yVell;
	}
	
	/**
	 * Checks for collision with bounderies
	 */
	public void checkCollisions(){
		if(yPos > downBounds ){
			up = true;
		}else if(yPos < 0){
			up = false;
		}
		
		if(xPos > rightBounds){
			right = false;
		}else if(xPos < 0){
			right = true;
		}
	}
	
	/**
	 * Controls the players movement
	 */
	public void move(){
		if(xPos > rightBounds){
			right = false;
		}
		if(xPos < 0){
			right = true;
		}
		
		if(yPos > downBounds){
			up = true;
		}
		if(yPos < 0){
			up = false;
		}
		
		if(up){
			yVell = -speed;
		}else{
			yVell = speed;
		}
		
		if(right){
			xVell = speed;
		}else{
			xVell = -speed;
		}
	}
	
	/**
	 * @return the smile
	 */
	public boolean isSmile() {
		return smile;
	}

	/**
	 * @param smile the smile to set
	 */
	public void setSmile(boolean smile) {
		this.smile = smile;
	}
}
