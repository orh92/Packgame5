package GameObjects;
public class Box extends GameObjects {
	private double id;//the id of the box
	private double latitude; //the x of the box Point1
	private double lontitude;//the z of the box Point1
	private double altitude;//the z of the box Point1
	private double radius;//the radius that score will get down
	private double latitude2;//the x of the box Point2
	private double lontitude2;//the y of the box Point2
	private double altitude2;	//the z of the box Point2

	public Box(double id,double latitude,double lontitude,double altitude,double latitude2,double lontitude2,double altitude2,double radius) {
		super(id,latitude,lontitude,altitude,latitude2);
		this.id=id;
		this.latitude=latitude;
		this.lontitude=lontitude;
		this.altitude=altitude;
		this.latitude2=latitude2;
		this.lontitude2=lontitude2;
		this.altitude2=altitude2;
		this.radius=radius;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLontitude() {
		return lontitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void seAltitude(double altitude) {
		this.latitude=altitude;
	}
	public void setLatitude(double latitude) {
		this.latitude=latitude;
	}
	public void setLontitude(double lontitude) {
		this.lontitude=lontitude;
	}
	public void setLatitude2(double latitude2) {
		this.latitude2=latitude2;
	}
	public void setLontitude2(double lontitude2) {
		this.lontitude2=lontitude2;
	}
	public double getLatitude2() {
		return latitude2;
	}
	public double getLontitude2() {
		return lontitude2;
	}
	public void setRadius(double radius) {
		this.radius=radius;
	}
	public double getRadius() {
		return radius;
	}
	public void setId(double id) {
		this.id=id;
	}
	public double  getId() {
		return id;
	}
	public double getAltitude2() {
		return altitude2;
	}
	public void setAltitude2(double altitude2) {
		this.altitude2=altitude2;
	}
}