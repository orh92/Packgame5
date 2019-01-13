package Packman;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import Geom.Point3D;
import Map.Map;
class PackmanGameJunitTest {
	//testing the function that changing the point3d with polar values to pixels values
	@Test
	void testPolar2Pixels() {
		System.out.println("@testing Polar2Pixels@");
		double x,y,z;
		Map map=new Map();
		x=35.21015066294487;
		y=32.10348629283489;
		z=0.0;
	Point3D point=new Point3D(x,y,z);
	System.out.println("the point on polar values "+point);
	point=map.Polar2Pixels(point);
	System.out.println("(Polar2Pixels): point after the changeto pixels"+point);
	assertTrue("the point after changing to polar is (1125,374,0.0)",point.x()==1125&&point.y()==374&&point.z()==0);
	System.out.println();
	}
	//testing the function that changing point with pixels values to polar values
	@Test
	void testPixels2Polar() {
		System.out.println("@testing Pixels2Polar@");
		double x,y,z;
		Map map=new Map();
		x=1350;
		y=250;
		z=0.0;
	Point3D point=new Point3D(x,y,z);
	System.out.println("the point on pixels values"+point);
	point=map.Pixels2Polar(point);
	System.out.println("(Pixels2Polar): the point after changing to polar values"+point);
	assertTrue("the point after changing to polar is (35.21172079553384,32.104220249221186,0.0)",point.x()==35.21172079553384&&point.y()==32.104220249221186&&point.z()==0);
	System.out.println();
	}
}
