package Path;
import java.util.ArrayList;
import java.util.Iterator;
import Game.Game;
import GameObjects.Box;
import GameObjects.Fruit;
import GameObjects.GameObjects;
import Geom.Point3D;
import Map.Map;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;
public class Algo {
	Map map=new Map(); 
	//boxArray contains all the box that are in the game
	private ArrayList<Box> boxArray=new ArrayList<Box>();
	//fruit array contains all the fruits that are in the game
	private ArrayList<Fruit> fruitArray=new ArrayList<Fruit>();
	//iter is pointer to the GameObjects Array that contains all the objects: packmans,fruits,boxes,ghosts..
	private Iterator<GameObjects> iter;
	//Box box;
	int size=0;
	//pointArrayList contains the points3d of the player,boxes,fruit target
//	ArrayList<Point3D> pointArrayList=new ArrayList<Point3D>();
	 ArrayList<Point3D> shortestPath=new ArrayList<Point3D>() ;
	//pp is the array of point3d  that we create graph by his info
	Point3D[] graphPoints;
	//xx is the x of the points that will be in pp array, we need this array to count how maney slots will be in pp
	double[] xx; 

	//b is the point3d of the target
	Point3D b;

	//game will hold the data of the game objects (fruits and boxes and packmans and ghosts),and hold data of the Player
	Game game;

	//in the constructor create array of boxes, and create another array of fruits
	/**
	 * init the algo by getting game data
	 * @param game
	 */
	public Algo(Game game) {
		this.game=game;
		Point3D playerPoint=new Point3D(game.getPlayer().getLatitude(),game.getPlayer().getLontitude(),game.getPlayer().getAltitude());
		shortestPath.add(playerPoint);
		iter=game.getGameIterator();
		GameObjects gameobjects;
		while(iter.hasNext()) {
			gameobjects=iter.next();
			if(gameobjects instanceof Box) {
				//	box=new Box(gameobjects.getId(),gameobjects.getLatitude(),gameobjects.getLontitude(),gameobjects.getAltitude(),((Box) gameobjects).getLatitude2(),((Box) gameobjects).getLontitude2(),((Box) gameobjects).getAltitude2(),((Box) gameobjects).getRadius());
				boxArray.add((Box)gameobjects);
			}
			if (gameobjects instanceof Fruit) {
				fruitArray.add((Fruit)gameobjects);
			}
		}

		InitPoint3DArray(boxArray);	
	
	}
	//now we have boxses array and fruits array in 2 diffrent arrays


	//creating array of Point3D
	//first point is of the Player
	//last Point is of the target
	private void InitPoint3DArray(ArrayList<Box> boxArray) {

		//boxIter is pointer number1 to the array of boxes
		Iterator<Box> boxIter=boxArray.iterator();

		Box box1;

		boxIter=boxArray.iterator();
		while(boxIter.hasNext()) {
			box1=boxIter.next();
			Point3D p1=new Point3D(box1.getLatitude()+5,box1.getLontitude()-5,box1.getAltitude());//right up
			p1=map.Pixels2Polar(p1);
			Point3D p2=new Point3D(box1.getLatitude2()-5,box1.getLontitude2()+5,box1.getAltitude2());//left down
			p2=map.Pixels2Polar(p2);
			Point3D p3=new Point3D(box1.getLatitude2()+5,box1.getLontitude()+5,box1.getAltitude());//right down
			p3=map.Pixels2Polar(p3);
			Point3D p4=new Point3D(box1.getLatitude()-5,box1.getLontitude2()-5,box1.getAltitude());//left up
			p4=map.Pixels2Polar(p4);
			shortestPath.add(p1);
			shortestPath.add(p2);
			shortestPath.add(p3);
			shortestPath.add(p4);
		}	
		//creating point array of the graph 
		graphPoints=new Point3D [shortestPath.size()+1];
		for(int i=0;i<shortestPath.size();i++) {
			graphPoints[i]=shortestPath.get(i);
		}
	}//givePoint3DArray ends
	//now we have Point3D array that hold the point of the player,point of the relevant boxes(not target yet)




	//find the shortest Path from the player to the taget(fruit)
	/**
	 * calculating the path to the target
	 * @return
	 */
	public ArrayList<Point3D> shortestPath() {	
		Double dist=Double.MAX_VALUE;
		//find if have better path and distance
		Iterator<Fruit> fruitIter=fruitArray.iterator();
		while(fruitIter.hasNext()) {
			Fruit fruit=fruitIter.next();
			Point3D point=new Point3D(fruit.getLatitude(),fruit.getLontitude(),fruit.getAltitude());
			point=map.Pixels2Polar(point);
			graphPoints[graphPoints.length-1]=point;
     		String source="a";
    		String target="b";
    		Graph G = new Graph();
    		G.add(new Node(source));//in place index0 the item is a(player)
    		if(graphPoints.length==2) {
    			G.add(new Node(""+1)); 
    		}
    		else if(graphPoints.length>2) {
    		for(int j=1;j<graphPoints.length-1;j++) {
    			G.add(new Node(""+j));
    		}
    		Point3D p=new Point3D(fruit.getLatitude(),fruit.getLontitude(),fruit.getAltitude());
    		p=map.Pixels2Polar(p);
    		graphPoints[graphPoints.length-1]=p;
    		G.add(new Node(target));
    		}
    		//now i have Graph with all the nodes and my array contains all the points
    		for(int h=1;h<graphPoints.length;h++) {
    			for(int j=0;j<h;j++) {
    				Node node1=G.getNodeByIndex(h);
    				Node node2=G.getNodeByIndex(j);
    				//if can create edge so create
    				if(!AlgoIntersection.Intersection(boxArray,graphPoints[h],graphPoints[j])) {
    					Point3D p1=new Point3D(graphPoints[h].x(),graphPoints[h].y()); //need 2d? or 3d
   					p1=map.Polar2Pixels(p1);
    					Point3D p2=new Point3D(graphPoints[j].x(),graphPoints[j].y());
   					p2=map.Polar2Pixels(p2);
   					//send dist on pixels and back to polar
    					G.addEdge(node1.get_name(), node2.get_name(), p1.distance2D(p2));
    					p1=map.Pixels2Polar(p1);
    					p2=map.Pixels2Polar(p2);
    				}
    			}
    		}//now i have graph with edges
    		
			Graph_Algo.dijkstra(G,G.getNodeByIndex(0).get_name());
			Node b=G.getNodeByIndex(G.size()-1); //b is the target node
			if(dist>b.getDist()) {
				dist=b.getDist();
				System.out.println("dist changed: "+dist);
				shortestPath = new ArrayList<Point3D>();
//				Point3D playerPoint=new Point3D(game.getPlayer().getLatitude(),game.getPlayer().getLontitude(),game.getPlayer().getAltitude());
//				shortestPath.add(playerPoint);
//				InitPoint3DArray(boxArray);	
				for(int j=0;j<b.getPath().size();j++) {
					Point3D pointOnPath=new Point3D(graphPoints[j].x(),graphPoints[j].y(),graphPoints[j].z());
					shortestPath.add(pointOnPath);
				}
				Point3D targetPoint=new Point3D(graphPoints[graphPoints.length-1].x(),graphPoints[graphPoints.length-1].y(),graphPoints[graphPoints.length-1].z());
				shortestPath.add( targetPoint);
			}
		}
		//now i have the best dist+best path to the target
		return shortestPath;
	}
}