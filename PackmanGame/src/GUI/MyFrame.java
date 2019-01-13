package GUI;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Game.Game;
import Game.TimeManager;
import GameObjects.Box;
import GameObjects.Fruit;
import GameObjects.GameObjects;
import GameObjects.Ghost;
import GameObjects.Packman;
import Geom.MyCoords;
import Geom.Point3D;
import Map.Map;
import Path.Algo;
import Path.AlgoThread;
import Robot.Play;
public class MyFrame extends JFrame implements MouseListener,ComponentListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BufferedImage myImage;
	public BufferedImage theImage;
	public BufferedImage playerImage;
	String item="";
	String autoAlgo="";
	String run="";
	private double widthAttitude=1;
	private double heightAttitude=1;
	private Game game =new Game();
	Iterator<GameObjects> gameIterator =game.getGameIterator();
	String mode="";

	Play play1;
	private double azimuth[]= {0,0,0};	
	public  int Width;
	public  int Height;
	ArrayList<String> board_data;
	String changePlace="";
	Map map=new Map();
	MyCoords m=new MyCoords();
	/**
	 * constructor for MyFrame
	 * initialize the gui
	 */
	public MyFrame() {
		initializeGui();
		addMouseListener(this);
	}
	/**
	 * initialize the gui
	 */
	private  void initializeGui(){
		try {
			myImage = ImageIO.read(new File("Ariel1.png"));
			Width=myImage.getWidth();
			Height=myImage.getHeight();
		}
		catch (IOException e) {
			System.out.println("cant find the image");
			e.printStackTrace();
		}
		try {
			playerImage = ImageIO.read(new File("Player.png"));
			item="";
			mode="notFile";
		}				
		catch (IOException f) {
			System.out.println("cant find the image");
			f.printStackTrace();
		}
		MenuBar menuBar=new MenuBar();
		Menu menu = new Menu("Options");
		/**
		 * item1 is listener to the klicking by mouse to start new game and clear the screen
		 */
		MenuItem item1=new MenuItem("start new Game");
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play1.stop();
				run="";
				autoAlgo="";
				item="";	
				mode="";
				game.getGameArray().clear();
				gameIterator =game.getGameIterator();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				repaint();	
			}		
		});
		menu.add(item1);
		/**
		 * mouse listener to start game from a file
		 * loading data/sending id's and creating data to game and initialize the player location
		 */
		Menu menu2=new Menu("Start game from a File");
		MenuItem item2=new MenuItem("Load file");
		item2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//file_name=the name of the pressed item(need to do that)
				JFileChooser fc=new JFileChooser();
				JButton open=new  JButton();
				fc.setCurrentDirectory(new java.io.File("C:\\Or\\Moodle\\שנה ב\\תיכנות מונחה עצמים\\מטלות\\מטלה 5\\מטלה 5 להגשה\\Ex4_OOP\\data"));
				if(fc.showOpenDialog(open)==JFileChooser.APPROVE_OPTION) {
					String gamePath=fc.getSelectedFile().getAbsolutePath();
					play1=new Play(gamePath);
					board_data = play1.getBoard();
					game=new Game(board_data);
					Point3D thePlayer=new Point3D(330,191,0);
					thePlayer=map.Pixels2Polar(thePlayer);	
					//y=x x=y
					play1.setInitLocation(thePlayer.y(), thePlayer.x());
					game.getPlayer().setLatitude(thePlayer.x());
					game.getPlayer().setLontitude(thePlayer.y());
				}
				//				Csvload cs=new Csvload();
				//				game = cs.load();
				mode="load";
				repaint();
			}
		});
		menu2.add(item2);
		MenuItem item3=new MenuItem("Show Statistics");
		item3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Statistics.showStatistics();				
			}
		});
		menu.add(item3);
		/**
		 * mouse listener to klick to start game to play mouse
		 */
		MenuItem item4=new MenuItem("Start Game");
		item4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Game Started");
				run="yes";
				synchronized(play1) {
					play1.setIDs(-305771420,-203958939);
					play1.start();
					play1.rotate(0);
				}
				TimeManager timeManager=new TimeManager(MyFrame.this);
				timeManager.start();
				System.out.println("TimeManager started");
				//algo=new ShortestPathAlgo(game);
			}	
		});
		menu.add(item4);
		/**
		 * stop game is to freez the situation and can continue
		 */
		MenuItem item5=new MenuItem("stop game");
		item5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				play1.stop();
				autoAlgo="";
			}
		});
		menu.add(item5);
		//saving the path of the packmans to kml file 
		//have time stamps of the packman moves
		MenuItem item6=new MenuItem("Change Place of the Player");
		item6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePlace="yes";
				System.out.println("choose the place for the player");
			}});
		menu.add(item6);	
		/**
		 * this mouse listener is to play the game by automatic algorithem
		 * calculating the path to the targets
		 */
		MenuItem item7=new MenuItem("Auto Play with Algorithem");
		item7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				board_data = play1.getBoard();
				game=new Game(board_data);
				String s=board_data.get(0);
				String st[];
				st=s.split(",");
				game.getPlayer().setLatitude(Double.parseDouble(st[3]));
				game.getPlayer().setLontitude(Double.parseDouble(st[2]));
				play1.setIDs(305771420,203958939);
				play1.start();
				play1.rotate(1);
				repaint();
				autoAlgo="yes";
				AlgoThread algt=new AlgoThread(MyFrame.this);
				algt.start();
			}
		});
		menu.add(item7);
		menuBar.add(menu);
		menuBar.add(menu2);
		this.setMenuBar(menuBar);
	}
	/**
	 * this fuiction (Move) is managed by the Thread "TimeManager"
	 * sending rotation to the server(imported jar)
	 */
	public void Move() {
		board_data = play1.getBoard();
		game=new Game(board_data);
		String s=board_data.get(0);
		String st[];
		st=s.split(",");
		game.getPlayer().setLatitude(Double.parseDouble(st[3]));
		game.getPlayer().setLontitude(Double.parseDouble(st[2]));
		play1.rotate(azimuth[0]);
		repaint();
	}
	/**
	 * this function (algoMove) managed by the Thread "AlgoThread"
	 * this func calculating the path of the targets
	 * calculation the azimuth to the target
	 * sending rotation to the server(imported jar)
	 */
	public void algoMove() {
		synchronized(MyFrame.this) {
			ArrayList<Point3D> path=new ArrayList<Point3D>();
			board_data = play1.getBoard();
			game=new Game(board_data);	
			String s=board_data.get(0);
			String st[];
			st=s.split(",");
			game.getPlayer().setLatitude(Double.parseDouble(st[3]));
			game.getPlayer().setLontitude(Double.parseDouble(st[2]));
			Algo alg=new Algo(game);
			path=alg.shortestPath();
			if(path.size()>1) {
				Point3D p1=new Point3D(game.getPlayer().getLatitude(),game.getPlayer().getLontitude(),game.getPlayer().getAltitude());
				Point3D p2=new Point3D(path.get(1));//target
				azimuth=m.azimuth_elevation_dist(p1, p2);
				azimuth[0]=360-(azimuth[0])+90;
				while(azimuth[0]<0) {
					azimuth[0]=(azimuth[0]+360);
				}
				azimuth[0]=(azimuth[0]%360);
				p1=map.Polar2Pixels(p1);
				p2=map.Polar2Pixels(p2);
				if(((p1.distance2D(p2))>(game.getPlayer().getRadius()))&&play1.isRuning()) {
					String time[]=play1.getStatistics().split(",");
					double timeLeft=Double.parseDouble(time[3].substring(11));//now time is 100sec
					System.out.println("time left: "+timeLeft/1000);
					play1.rotate(azimuth[0]);
					repaint();
				}
			}
		}
	}
	/**
	 * painting the map,packmans,fruits,paths that calculated by algorithem 
	 */
	int itemWidth=45;
	int  itemHeight=35;
	int x=-1;
	int y=-1;
	public void paint(Graphics g) {
		java.awt.Image image = createImage(5000,5000);
		Graphics g1 = image.getGraphics();
		g1.drawImage(myImage, 0, 0,this.getWidth(),this.getHeight(), this);
		if(!(game.getPlayer()==null)) {
			Point3D pl=new Point3D(game.getPlayer().getLatitude(),game.getPlayer().getLontitude(),game.getPlayer().getAltitude());
			pl=map.Polar2Pixels(pl);
			g1.drawImage(playerImage,(int)(pl.x()*(widthAttitude))-11, (int)(pl.y()*heightAttitude)-11,(int)(itemWidth*widthAttitude),(int)(itemHeight*heightAttitude), this);
			pl=map.Pixels2Polar(pl);
		}
		Iterator<GameObjects> current=game.getGameIterator();
		GameObjects currGameOb;
		while(current.hasNext()) {
			currGameOb=current.next();
			if(currGameOb instanceof Box) {
				Box b=(Box)currGameOb;
				double x1,x2,y1,y2;
				double boxwidth,boxheight;
				x1=b.getLatitude();
				x2=b.getLatitude2();
				y1=b.getLontitude();
				y2=b.getLontitude2();
				boxwidth=Math.abs(x1-x2);		
				boxheight=Math.abs(y1-y2);
				if(x2<x1) {x1=x2;}
				if(y2<y1) {y1=y2;}
				g1.fillRect((int)(x1*(widthAttitude)),(int)(y1*(heightAttitude)),(int)(boxwidth*widthAttitude),(int)(boxheight*heightAttitude));
			}
		}
		current=game.getGameIterator();
		while(current.hasNext()) {
			currGameOb=current.next();
			if( currGameOb instanceof Packman) {
				try {
					theImage = ImageIO.read(new File("Packman.png"));	
					g1.drawImage(theImage,(int)(currGameOb.getLatitude()*(widthAttitude))-13, (int)(currGameOb.getLontitude()*heightAttitude)-13,(int)(itemWidth*widthAttitude),(int)(itemHeight*heightAttitude), this);
				}
				catch (IOException f) {
					System.out.println("cant find the image");
					f.printStackTrace();
				}
			}
			if(currGameOb instanceof Fruit) {
				try {
					theImage = ImageIO.read(new File("Fruit.png"));
					g1.drawImage(theImage,(int)(currGameOb.getLatitude()*(widthAttitude))-13, (int)(currGameOb.getLontitude()*heightAttitude)-13,(int)(itemWidth*widthAttitude),(int)(itemHeight*heightAttitude), this);
				}
				catch (IOException f) {
					System.out.println("cant find the image");
					f.printStackTrace();
				}	
			}
			if(currGameOb instanceof Ghost) {
				try {
					theImage = ImageIO.read(new File("Ghost.png"));
					g1.drawImage(theImage,(int)(currGameOb.getLatitude()*(widthAttitude)), (int)(currGameOb.getLontitude()*heightAttitude),(int)(itemWidth*widthAttitude),(int)(itemHeight*heightAttitude), this);
				}
				catch (IOException f) {
					System.out.println("cant find the image");
					f.printStackTrace();
				}	
			}


		}
		g.drawImage(image,0,0,this);
	}
	/**
	 * using mouse listener to see when the user klicking on the frame and want to creat packman or fruit
	 * we use component listener to see if the user creating game by loading file or by create each packman and each fruit by mouse klicking
	 * if clicked on create fruit so string will be F
	 * if clicked on create packman string will be P
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!autoAlgo.equals("yes")&&play1.isRuning()) {
			x=(int)(e.getX()*(1/widthAttitude));
			y=(int)(e.getY()*(1/heightAttitude));
			System.out.println(x+","+y);
			if(changePlace.equals("yes")&&(!run.equals("yes"))) {
				Point3D p=new Point3D(x,y,0);
				p=map.Pixels2Polar(p);
				play1.setInitLocation(p.y(),p.x());
				game.getPlayer().setLatitude(p.x());
				game.getPlayer().setLontitude(p.y());
				changePlace="";
				System.out.println("changed place");	
				repaint();
			}	
			Point3D point=new Point3D(x,y,0);//klicked x,y
			point=map.Pixels2Polar(point);
			Point3D point2=new Point3D(game.getPlayer().getLatitude(),game.getPlayer().getLontitude(),0);//player x,y
			azimuth=m.azimuth_elevation_dist(point2, point);
			azimuth[0]=360-(azimuth[0])+90;
			while(azimuth[0]<0) {
				azimuth[0]=(azimuth[0]+360);
			}
			azimuth[0]=(azimuth[0]%360);
			System.out.println("the azimuth in click"+azimuth[0]);
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void componentHidden(ComponentEvent e) {
	}
	@Override
	public void componentMoved(ComponentEvent e) {	
	}
	/**
	 * widthAttitude is the % of the change in the frame width
	 * heightAttitude is the % of the change in the frame height
	 * we use them to multiple in the x,y and size of packman and fruit 
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		widthAttitude=e.getComponent().getSize().getWidth()/Width;
		heightAttitude=e.getComponent().getSize().getHeight()/Height;
	}
	@Override
	public void componentShown(ComponentEvent e) {
	}
	/**
	 * upload data to sql
	 * @param infoMessage
	 * @param titleBar
	 */
	public void up(String infoMessage, String titleBar)
	{
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	public double getPlayerX() {
		return game.getPlayer().getLatitude();
	}
	public double getPlayerY() {
		return game.getPlayer().getLontitude();
	}
	public void setGame(ArrayList<String> s) {
		this.game=new Game(s);
	}
	public  double[] getAzimuth() {
		return azimuth;
	}
	public Play getPlay1() {
		return play1;
	}

	public  Game getGame() {
		return game;
	}
}