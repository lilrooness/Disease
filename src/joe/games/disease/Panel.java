package joe.games.disease;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Panel extends JPanel implements Runnable{
	
	BufferedImage image;
	Graphics buffer;
	
	private boolean isMouseDown;
	private int mousex;
	private int mousey;
	private long framerate;
	
	Game game;
	Thread loop;
	
	public Panel(int width, int height, long framerate){
		this.framerate = framerate;
		isMouseDown = false;
		image  = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		buffer = image.getGraphics();
		game = new Game(width, height, 5);
		loop = new Thread(this);
		
		this.addMouseListener(new MouseClickHandler());
		this.addMouseMotionListener(new MouseMotionHandler());
		this.setFocusable(true);
		
		loop.start();
	}
	
	@Override
	public void paint(Graphics g){
		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void run() {
		while((!game.isGameOver())&&(!game.isGameWon())){
			game.tick(buffer, isMouseDown, mousex, mousey);
			repaint();
			sleepThread(framerate);
		}
	}
	
	/**
	 * sets new mouse x and y positions
	 * @param mousex
	 * @param mousey
	 */
	public void updateMousePosition(int mousex, int mousey){
		this.mousex = mousex;
		this.mousey = mousey;
	}
	
	/**
	 * sleeps the thread by a parsed long value
	 * @param millis
	 */
	public void sleepThread(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private class MouseMotionHandler implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			updateMousePosition(e.getX(), e.getY());
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			updateMousePosition(e.getX(), e.getY());
			
		}
		
	}
	
	private class MouseClickHandler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			isMouseDown = true;
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			isMouseDown = false;
			
		}
		
	}
}
