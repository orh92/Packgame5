package GameObjects;
/**
 * packman is an object that extends GameObjects class
 * @author orh92
 *
 */
public class Packman extends GameObjects{
	private double SpeedWeight;//speed of the packman
	private double latitude;//x of the packman
	private double lontitude;//y of the packman
	private double altitude;//z of the packman
	private double radius;//radius that the packman can eat from
	private double id;		//if of the packman
	public Packman(double id,double latitude,double lontitude,double altitude,double SpeedWeight,double radius) {
		super(id,latitude,lontitude,altitude,SpeedWeight);
		this.latitude=latitude;
		this.lontitude=lontitude;
		this.altitude=altitude;
		this.SpeedWeight=SpeedWeight;
		this.id=id;
		this.radius=radius;	
	}
	public void setSpeedWeight(double SpeedWeight) {
		this.SpeedWeight = SpeedWeight;
	}
	public double getSpeedWeight() {
		return SpeedWeight;
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

}