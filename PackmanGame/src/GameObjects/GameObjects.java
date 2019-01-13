package GameObjects;
/**
 * GameObjects is class that packman,fruit extends him 
 * GameObjects has common values that packman and fruit needs
 * x,y,z speed/weight ,id
 * @author orh92
 *
 */
public class GameObjects {
	private	double id; //if of the object
	private	double latitude; //x of the object
	private	double lontitude;//y of the object
	private	double altitude;//z of the object
	private	double SpeedWeight;//speed or weight of the object
	
		public GameObjects(double id,double latitude,double lontitude,double altitude,double SpeedWeight) {
			this.id=id;
			this.latitude=latitude;
			this.lontitude=lontitude;
			this.altitude=altitude;
			this.SpeedWeight=SpeedWeight;
		}
		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}
		public void setLontitude(double lontitude) {
			this.lontitude = lontitude;
		}
		public void setAltitude(double altitude) {
			this.altitude = altitude;
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
		public void setId(double id) {
			this.id = id;
		}
		public double getId() {
			return id;
		}
		public void setSpeedWeight(double SpeedWeight) {
			this.SpeedWeight = SpeedWeight;
		}
		public double getSpeedWeight() {
			return SpeedWeight;
		}
}
