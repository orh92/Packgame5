package Packman;
import javax.swing.JFrame;
import GUI.MyFrame;
public class Main extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Main() {	
		MyFrame window = new MyFrame();
		window.setVisible(true);
		setTitle("W"+this.getWidth()+"H"+this.getHeight());
		window.setLocation(200,200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight()); 
	}
	/**
	 * running the packman game
	 * @param args
	 */
	public static void main(String[] args){	
		new Main();					
	}
}
