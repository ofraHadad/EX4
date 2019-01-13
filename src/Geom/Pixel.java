package Geom;

/**
 * this class represent a Pixel point in an image.
 *  
 * @author ofra&&shira
 *
 */

public class Pixel implements Geom_element{

	private int x;
	private int y;


/**
 * a constructor, gets  
 * @param x
 * @param y
 */
	public Pixel(int x, int y) {
		setX(x);
		setY(y);
	}


	public Pixel(Pixel p) {

		setX(p.getX());
		setY(p.getY());
	}


	public String toString() {
		return "("+getX()+","+getY()+")";
	}

	public double distance(Pixel p) {
		double x= Math.pow(getX()-p.getX(), 2);
		double y= Math.pow(getY()-p.getY(), 2);
		return Math.sqrt(x+y);
	}
///////////////////////Geom_element//////////////////////////////////
	@Override
	public double distance3D(Point3D p) {
		if(p.z()==0) {
			double x= Math.pow(getX()-p.x(), 2);
			double y= Math.pow(getY()-p.y(), 2);
			return Math.sqrt(x+y);
		}
		throw new RuntimeException("Pixel has only 2D ");
	}

	@Override
	public double distance2D(Point3D p) {
		double x= Math.pow(getX()-p.x(), 2);
		double y= Math.pow(getY()-p.y(), 2);
		return Math.sqrt(x+y);
	}
	
/////////////////////////////////////////Getters and Setters////////////////////////////////////
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}


}
