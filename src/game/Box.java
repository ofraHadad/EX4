package game;

import Geom.Gps_Point;
import Geom.Pixel;
/**
 * This class represent a box in the game.
 * @author ofra&shira
 */
public class Box {

	private int id;
	private Gps_Point LocationGPS;
	private int hetigh;
	private int width;
	private Map map;
	private Gps_Point maxGPS;

	/////////////constructors\\\\\\\\\\\\\
	/**
	 * Creates fruit from arrays of string , and a map.
	 * @param line
	 * @param head
	 * @param map
	 */
	public Box(String[] line, String[] head,Map map) {
		setId(Integer.parseInt(line[1]));
		this.map= map;

		Gps_Point min=new Gps_Point(head,line);

		String[] newHead= {"null","null","null","null","null","Lat","Lon","Alt"};
		Gps_Point max=new Gps_Point(newHead,line);
		setLocationGPS(new Gps_Point(min.get_x(),max.get_y(),0));
		setMaxGPS(new Gps_Point(max.get_x(),min.get_y(),0));
		Pixel minP= getMap().converteGpsToPixel(min);
		Pixel maxP= getMap().converteGpsToPixel(max);
		setHetigh(minP.getY()-maxP.getY());
		setWidth(maxP.getX()-minP.getX());
		Pixel p= new Pixel(getLocation().getX(),getLocation().getY()-1);
		Pixel pmax=new Pixel(getMax().getX(),getMax().getY()+1);
		setLocationGPS(map.convertePixelToGps(p));
		setMaxGPS(map.convertePixelToGps(pmax));

	}
	/**
	 * Creates fruit from 2 pixel point.
	 */
	public Box(Pixel p, Pixel pmax) {
		map=new Map();
		setLocationGPS(map.convertePixelToGps(p));
		setMaxGPS(map.convertePixelToGps(pmax));
	}

	///////////// Method \\\\\\\\\\\\\
	/**
	 * The function takes a point in a pixel and checks whether it is on the box
	 * @param p pixel point
	 * @return true if the point on the box else false
	 */
	public boolean onTheBoxY(Pixel p) {
		return(getLocation().getX()+1<p.getX() && getMax().getX()-1>p.getX());
	}
	/**
	 * The function takes a point in a pixel and checks whether it is on the box
	 * @param p pixel point
	 * @return true if the point on the box else false
	 */
	public boolean onTheBoxX(Pixel p) {
		return(getLocation().getY()+1<p.getY() && getMax().getY()-1>p.getY());
	}
	/**
	 * the function print the class
	 */
	public String toString() {
		String s= "id:"+" "+ getId()+" "+ "LocationGPS:"+ " "+ getLocationGPS()+" "+"maxGPS:"+" "+getMaxGPS();
		return s;
	}

	/////////////Getters and Setters\\\\\\\\\\\
	public Pixel getMax() {
		return map.converteGpsToPixel(getMaxGPS());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Gps_Point getLocationGPS() {
		return LocationGPS;
	}
	
	public void setLocationGPS(Gps_Point locationGPS) {
		LocationGPS = locationGPS;
	}
	
	public int getHetigh() {
		return hetigh;
	}
	
	public void setHetigh(int hetigh) {
		this.hetigh = hetigh;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}

	public Pixel getLocation() {
		return getMap().converteGpsToPixel(getLocationGPS());
	}
	
	public Gps_Point getMaxGPS() {
		return maxGPS;
	}
	
	public void setMaxGPS(Gps_Point maxGPS) {
		this.maxGPS = maxGPS;
	}

}
