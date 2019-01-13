package Map;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFrame;

import Game.Game;
import GameObjects.Box;
import GameObjects.Fruit;
import GameObjects.GameObjects;
import GameObjects.Ghost;
import GameObjects.Packman;
import Geom.Point3D;

public class Csvload extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game gameObjects =new Game();
	Iterator<GameObjects> gameIterator =gameObjects.getGameIterator();
/**
 * loading the game by reading the data from csv file
 * @return
 */
	public Game load(){
		FileDialog fd = new FileDialog(Csvload.this, "Open text file", FileDialog.LOAD);
		fd.setFile("*.csv");
		fd.setDirectory("Ex4_OOP_example8.csv");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();                 
		try {
			FileReader fr = new FileReader(folder + fileName);
			BufferedReader br = new BufferedReader(fr);
			String str = new String();
			str = br.readLine();
			str = br.readLine();
			for (int i = 0; str != null; i =i++) {									
				if (str != null) {
					String SplitedFile[]=str.split(",");
					System.out.println(str);
					Point3D point =new Point3D(Double.parseDouble(SplitedFile[3]),Double.parseDouble(SplitedFile[2]),Double.parseDouble(SplitedFile[4]));
					Map map=new Map();
					point=	 map.Polar2Pixels(point);
				//	System.out.println(packfruit.x()+" its the x.loadd. "+packfruit.y()+"  its the y..");
					if(SplitedFile[0].equals("P")) {  
						gameObjects.getGameArray().add( new Packman(Double.parseDouble(SplitedFile[1]),point.x(),point.y(),point.z(),Double.parseDouble(SplitedFile[5]),Double.parseDouble(SplitedFile[6]))) ;								
					}
					if(SplitedFile[0].equals("F")) {
						gameObjects.getGameArray().add( new Fruit(Double.parseDouble(SplitedFile[1]),point.x(),point.y(),point.z(),Double.parseDouble(SplitedFile[5])));	
					}
					if(SplitedFile[0].equals("B")) {
						Point3D point2 =new Point3D(Double.parseDouble(SplitedFile[6]),Double.parseDouble(SplitedFile[5]),0);
						point2=	 map.Polar2Pixels(point2);
						gameObjects.getGameArray().add( new Box(Double.parseDouble(SplitedFile[1]),point.x(),point.y(),point.z(),point2.x(),point2.y(),Double.parseDouble(SplitedFile[7]),Double.parseDouble(SplitedFile[8])));	
						System.out.println(SplitedFile[1]+","+point.x()+","+point.y()+","+point.z()+","+point2.x()+","+point2.y()+","+SplitedFile[7]+","+SplitedFile[8]);
					}
					if(SplitedFile[0].equals("G")) {
						gameObjects.getGameArray().add( new Ghost(Double.parseDouble(SplitedFile[1]),point.x(),point.y(),point.z(),Double.parseDouble(SplitedFile[5]),Double.parseDouble(SplitedFile[6])));	
					}
				}
				str = br.readLine();	
			}		
			br.close();
			fr.close();

		} catch (IOException ex) {
			System.out.print("Error reading file " + ex);
			System.exit(2);
		}

		return gameObjects;
	}
}

