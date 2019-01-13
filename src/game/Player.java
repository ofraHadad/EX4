package game;

import Geom.Gps_Point;
import Geom.Pixel;
/**
 * This class represent a player in the game.
 * @author ofra&shira
 */
public class Player {

	private Gps_Point LocationGPS;
	private int speed;
	private Map map;
	private int radius;
///////////// Contractors\\\\\\\\\\\\\
	/**
	 * Creates player from pixel coordinates,and a map. 
	 * @param x
	 * @param y
	 * @param map
	 */
	public Player(int x,int y, Map map) {
		this.map=map;
		setLocationGPS(getMap().convertePixelToGps(new Pixel(x,y)));
		setSpeed(20);
		setRadius(1);
	}

/**
 * Creates player from player. 
 * deep copy of player.
 * @param me
 */

	public Player(Player me) {
		setMap(me.getMap());
		setLocationGPS(me.getLocationGPS());
		setRadius(me.getRadius());
		setSpeed(me.getSpeed());
	}
/**
 * the function get a kodkod and check if they have the same location
 * @param k
 * @return true if the location is equal else false
 */
	public boolean equal(Kodkod k) {
		
		if(getLocation().getX()<=k.getLocation().getX()+1 &&getLocation().getX()>=k.getLocation().getX()-1) {
			if(getLocation().getY()<=k.getLocation().getY()+1&&getLocation().getY()>=k.getLocation().getY()-1) {
				return true;
			}
		}
	
	
	return false;
}

/////////////Getters and Setters\\\\\\\\\\\

	public Pixel getLocation() {
		return getMap().converteGpsToPixel(getLocationGPS());
	}
	public Gps_Point getLocationGPS() {
		return LocationGPS;
	}

	public void setLocationGPS(Gps_Point locationGPS) {
		LocationGPS = locationGPS;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}



	
}
