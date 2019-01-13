package GameObjects;
/**
 * ghost extends gameobjects class x,y,z,id,speed,radius
 * @author orh92
 *
 */
public class Ghost extends GameObjects {
		private double id;//
		private double latitude;//x of the ghost
		private double lontitude;// y of the ghost
		private double altitude;//z of the ghost
		private double radius;// radius of the ghost 
		private double SpeedWeight;	// speed of the ghost	
		public Ghost(double id,double latitude,double lontitude,double altitude,double SpeedWeight,double radius) {
			super(id,latitude,lontitude,altitude,SpeedWeight);
			this.id=id;
			this.latitude=latitude;
			this.lontitude=lontitude;
			this.SpeedWeight=SpeedWeight;
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
