package Path;

import java.util.ArrayList;
import java.util.Iterator;

import GameObjects.Box;
import Geom.Point3D;
import Map.Map;

public class AlgoIntersection {
	//check if is a box on the path between the player and the fruit(target)
	public static boolean Intersection(ArrayList<Box> boxArray,Point3D p1,Point3D p2) {
		Map map=new Map();
Point3D p3=p1;
Point3D p4=p2;
p3=map.Polar2Pixels(p3);
p4=map.Polar2Pixels(p4);
		Box box;
		Iterator<Box> boxIter=boxArray.iterator();
		while(boxIter.hasNext()) {
			box=boxIter.next();
			double x1,y1,x2,y2;

			//point of p1
			x1=p1.x();
			y1=p1.y();

			//point of p2
			x2=p2.x();
			y2=p2.y();
			double m;//shipua
			m=((y2-y1)/(x2-x1));

			double n=((-m)*x1+y1);

			double boxX1=box.getLatitude();
			double boxX2=box.getLatitude2();
			double boxY1=box.getLontitude();
			double boxY2=box.getLontitude2();
			//the suspicios points of the player 
			//each play(1/2/3/4) point is point that mabey inside the box
			Point3D player1=new Point3D(boxX1,m*boxX1+n);
			Point3D player2=new Point3D(boxX2,m*boxX2+n);
			Point3D player3=new Point3D(((boxY1-n)/m),boxY1);
			Point3D player4=new Point3D(((boxY2-n)/m),boxY2);
			if(((boxX1<=player1.x()&&player1.x()<=boxX2)&&((boxY1<=player1.y())&&player1.y()<=boxY2))
					||((boxX1>=player1.x()&&player1.x()>=boxX2)&&((boxY1>=player1.y())&&player1.y()>=boxY2))) {
				return true;
			}
			if(((boxX1<=player2.x()&&player2.x()<=boxX2)&&((boxY1<=player2.y())&&player2.y()<=boxY2))
					||((boxX1>=player2.x()&&player2.x()>=boxX2)&&((boxY1>=player2.y())&&player2.y()>=boxY2))) {
				return true;
			}
			if(((boxX1<=player3.x()&&player3.x()<=boxX2)&&((boxY1<=player3.y())&&player3.y()<=boxY2))
					||((boxX1>=player3.x()&&player3.x()>=boxX2)&&((boxY1>=player3.y())&&player3.y()>=boxY2))) {
				return true;
			}
			if(((boxX1<=player4.x()&&player4.x()<=boxX2)&&((boxY1<=player4.y())&&player4.y()<=boxY2))
					||((boxX1>=player4.x()&&player4.x()>=boxX2)&&((boxY1>=player4.y())&&player4.y()>=boxY2))) {
				return true;
			}
		}

		return false;
	}
}
