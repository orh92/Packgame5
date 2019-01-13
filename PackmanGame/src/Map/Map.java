package Map;
import Game.Game;
import Geom.Point3D;
public class Map {
	double PixelsWidthX=1433;
	 double rangeYpixels=-642;
	 double rangeXpolar=0.0100;
	 double rangeYpolar=0.0038;
	 double PolarX0=35.2023;
	 double PolarY0=32.1057;
	 /**
	  * the width of the pixels
	  * @return
	  */
	 public double getPixelsWidthX() {
		return PixelsWidthX;
	}
		public Game getGame() {
			return getGame();
		}
		/**
		 * set width of the pixels
		 * @param PixelsWidthX
		 */
	public void setPixelsWidthX(double PixelsWidthX) {
		this.PixelsWidthX = PixelsWidthX;
	}
	/**
	 * get the range of the Y in pixels
	 * @return
	 */
	public double getRangeYpixels() {
		return rangeYpixels;
	}
	/**
	 * set the range of the Y in pixels
	 * @param rangeYpixels
	 */
	public void setRangeYpixels(double rangeYpixels) {
		this.rangeYpixels = rangeYpixels;
	}
	/**
	 * get the range of X in polar value
	 * @return
	 */
	public double getRangeXpolar() {
		return rangeXpolar;
	}
	/**
	 * set the range of X in polar value
	 * @param rangeXpolar
	 */
	public void setRangeXpolar(double rangeXpolar) {
		this.rangeXpolar = rangeXpolar;
	}
	/**
	 * get the range of Y polar values
	 * @return
	 */
	public double getRangeYpolar() {
		return rangeYpolar;
	}
	/**
	 * set the range of Y in polar values
	 * @param rangeYpolar
	 */
	public void setRangeYpolar(double rangeYpolar) {
		this.rangeYpolar = rangeYpolar;
	}
	/**
	 * change the point3d from pixels values to polar values
	 * @param point
	 * @return
	 */
	public  Point3D Pixels2Polar(Point3D point) {
		double newX = rangeXpolar*(point.x()/PixelsWidthX)+PolarX0;
		double newY = rangeYpolar*(point.y()/rangeYpixels)+PolarY0;
		return new Point3D(newX,newY,0);
	}
	/**
	 * change the point3d from polar values to pixels values
	 * @param point
	 * @return
	 */
	public  Point3D Polar2Pixels(Point3D point) {
		double newX = Math.round(((point.x()-PolarX0)/rangeXpolar)*PixelsWidthX);
		double newY = Math.round(((point.y()-PolarY0)/rangeYpolar)*rangeYpixels);
		return new Point3D(newX,newY,0);
	}
}
