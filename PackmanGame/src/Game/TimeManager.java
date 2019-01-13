package Game;
import java.util.ArrayList;
/**
 * manage the function Move that is in MyFame(doing step in the game by sending rotation to the server)
 */
import GUI.MyFrame;
public class TimeManager extends Thread{
	MyFrame myframe;
	Game game;
	ArrayList<String> board_data;
	/**
	 * this thread manage the move function from myframe and do step at the game
	 * @param myframe
	 */
	public TimeManager(MyFrame myframe) {
		this.myframe=myframe;
		this.game=myframe.getGame();
	}
	public void run() {
		synchronized(myframe) {
			String time[]=myframe.getPlay1().getStatistics().split(",");
			double timeLeft=Double.parseDouble(time[3].substring(11));//now time is 100sec
			while(myframe.getPlay1().isRuning()) {	
				myframe.Move();
				if((timeLeft%5000)==0) {
					System.out.println(myframe.getPlay1().getStatistics());
				}
				time=myframe.getPlay1().getStatistics().split(",");
				timeLeft=Double.parseDouble(time[3].substring(11));//the current time left
				if(timeLeft%1000==0) {
					System.out.println("time left: "+(int)(timeLeft/1000)+" Seconds");
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(myframe.getPlay1().getStatistics());

		}

	}

}
