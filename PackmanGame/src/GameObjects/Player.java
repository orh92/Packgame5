package GameObjects;
public class Player extends GameObjects {
	private double id; // id of the player 
	private double latitude;//x of the player
	private double lontitude;//y of the player
	private double altitude;//z of the player
	private double speed;//speed of the player
	private double radius;		//eating radius of the player
	public Player(double id,double latitude,double lontitude,double altitude,double speed,double radius) {
		super(id,latitude,lontitude,altitude,speed);
		this.radius=radius;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude=latitude;
	}
	public double getLontitude() {
		return lontitude;
	}
	public void setLontitude(double lontitude) {
		this.lontitude=lontitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude=altitude;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed=speed;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius=radius;
	}
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id=id;
	}
}
