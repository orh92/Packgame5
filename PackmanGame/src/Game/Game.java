package Game;
import java.util.ArrayList;
import java.util.Iterator;
import GameObjects.Box;
import GameObjects.Fruit;
import GameObjects.GameObjects;
import GameObjects.Ghost;
import GameObjects.Packman;
import GameObjects.Player;
import Geom.Point3D;
import Map.Map;
/**
 * game has arraylist that contains packmans or fruits
 * this array has iterator getter
 * @author orh92
 *
 */
public class Game {
	private Player player;
	private ArrayList<GameObjects> gameArray=new ArrayList<GameObjects>();
	private Iterator<String> stIter;
	private String st[];
	Map map=new Map();
	Point3D point2;
	/**
	 * contructor to the game
	 * getting array list and creating the game
	 * @param stArray
	 */
	public Game(ArrayList<String> stArray) {
		stIter=stArray.iterator();
		while(stIter.hasNext()) {
			st=stIter.next().split(",");
			Point3D point =new Point3D(Double.parseDouble(st[3]),Double.parseDouble(st[2]),Double.parseDouble(st[4]));
			point=	map.Polar2Pixels(point);
			if(st[0].equals("M")) { 
				point=map.Pixels2Polar(point);
				player=new Player(Double.parseDouble(st[1]),point.x(),point.y(),point.z(),Double.parseDouble(st[5]),Double.parseDouble(st[6]));
				gameArray.add(player);
				point=map.Polar2Pixels(point);
			//	System.out.println("Player"+st[1]+","+point.x()+","+point.y()+","+point.z()+","+Double.parseDouble(st[5])+","+Double.parseDouble(st[6]));
			}
			if(st[0].equals("P")) {  
				gameArray.add( new Packman(Double.parseDouble(st[1]),point.x(),point.y(),point.z(),Double.parseDouble(st[5]),Double.parseDouble(st[6])));								
			//	System.out.println("packman"+st[1]+","+point.x()+","+point.y()+","+point.z()+","+Double.parseDouble(st[5])+","+Double.parseDouble(st[6]));
			}
			if(st[0].equals("G")) {
				gameArray.add( new Ghost(Double.parseDouble(st[1]),point.x(),point.y(),point.z(),Double.parseDouble(st[5]),Double.parseDouble(st[6])));	
				//System.out.println("ghost"+st[1]+","+point.x()+","+point.y()+","+point.z()+","+Double.parseDouble(st[5])+","+Double.parseDouble(st[6]));
			}
			if(st[0].equals("F")) {
				gameArray.add( new Fruit(Double.parseDouble(st[1]),point.x(),point.y(),point.z(),Double.parseDouble(st[5])));	
				//	System.out.println("fruit"+st[1]+","+point.x()+","+point.y()+","+point.z()+","+Double.parseDouble(st[5]));
			}
			if(st[0].equals("B")) {
				point2 =new Point3D(Double.parseDouble(st[6]),Double.parseDouble(st[5]),Double.parseDouble(st[7]));
				point2=	 map.Polar2Pixels(point2);
				gameArray.add( new Box(Double.parseDouble(st[1]),point.x(),point.y(),point.z(),point2.x(),point2.y(),point2.z(),Double.parseDouble(st[8])));	
				//System.out.println("box"+Double.parseDouble(st[1])+","+point.x()+","+point.y()+","+point.z()+","+point2.x()+","+point2.y()+","+point2.z()+","+Double.parseDouble(st[8]));
			}
		}
	}
	public Game() {
	}
	public Player getPlayer() {
		return player;
	}
	public   ArrayList<GameObjects> getGameArray() {
		return gameArray;
	}
	public  Iterator<GameObjects> getGameIterator() {	
		Iterator<GameObjects> itemIterator=gameArray.iterator();
		return itemIterator;
	}
}