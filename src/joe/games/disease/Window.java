package joe.games.disease;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Window extends JFrame {
	public Window(int width, int height){
		super("Simple Point Click Game");
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new Panel(width, height));
		this.setVisible(true);
	}
}
