package GameObjects;
import Geom.Point3D;
/**
 * fruit is an object that extends GameObjects class
 * @author orh92
 *
 */
public class Fruit extends GameObjects{
	private double latitude; //x of the fruit
	private double lontitude;//y of the fruit
	private double altitude;//z of the fruit
	private double SpeedWeight;//weight of the fruit
	private double id;//if of the fruit
	public Fruit(double id,double latitude,double lontitude,double altitude,double SpeedWeight) {
		super(id,latitude,lontitude,altitude,SpeedWeight);
		this.latitude=latitude;
		this.lontitude=lontitude;
		this.altitude=altitude;
		this.SpeedWeight=SpeedWeight;
		this.id=id;
	}
	Point3D Fruit=new Point3D(latitude,lontitude,altitude);
	public double getLatitude() {
		return latitude;
	}
	public double getLontitude() {
		return lontitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setLatitude(double latitude) {
		this.latitude=latitude;
	}
	public void setLontitude(double lontitude) {
		this.lontitude=lontitude;
	}
	public void setAltitude(double altitude) {
		this.altitude=altitude;
	}
	public void setSpeedWeight(double SpeedWeight) {
		this.SpeedWeight = SpeedWeight;
	}
	public double getSpeedWeight() {
		return SpeedWeight;
	}
	public void setId(double id) {
		this.id = id;
	}
	public double getId() {
		return id;
	}
}