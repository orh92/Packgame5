package Path;
import GUI.MyFrame;
import Game.Game;
public class AlgoThread extends Thread {
	MyFrame myframe;
	Game game;
	public	AlgoThread(MyFrame myframe){
		this.myframe=myframe;
		this.game=myframe.getGame();
	}
	public void run() {
		synchronized (myframe) {
			while(myframe.getPlay1().isRuning()) {
				myframe.algoMove();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}