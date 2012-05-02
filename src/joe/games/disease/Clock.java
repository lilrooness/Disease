package joe.games.disease;

public class Clock implements Runnable{
	
	private int time;
	private int downTo;
	private int fromTime;
	
	public Clock() {
		
	}

	/**
	 * @return the time
	 */
	public synchronized int getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @return the downTo
	 */
	public int getDownTo() {
		return downTo;
	}

	/**
	 * @param downTo the downTo to set
	 */
	public void setDownTo(int downTo) {
		this.downTo = downTo;
	}
	
	/**
	 * @return the fromTime
	 */
	public int getFromTime() {
		return fromTime;
	}

	/**
	 * @param fromTime the fromTime to set
	 */
	public void setFromTime(int fromTime) {
		this.fromTime = fromTime;
	}

	/**
	 * checks if the Clock has timed out
	 * @return
	 */
	public boolean isRunning(){
		if(getDownTo() < getTime()){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Adds a value to time
	 * @param tickBy
	 */
	public void tick(int tickBy){
		setTime(getTime()+tickBy);
	}
	
	@Override
	public void run() {
		try{
			while(isRunning()){
				Thread.sleep(1000);
				tick(-1);
			}
		}catch(Exception e){
			System.out.println("Clock threading problem");
			e.printStackTrace();
		}
		setTime(0);
	}
	
}
