package game;

/**
 * This class represent a kodkod in the graph.
 * @author ofra&shira
 */

import java.util.ArrayList;

import Geom.Gps_Point;
import Geom.Pixel;


public class Kodkod {
	private Gps_Point locationGps;
	private ArrayList<Kodkod> connected=new ArrayList();

	private int whoAmI;//1-box, 2- fruit, 3- me, 4- packman
	private Map map;
	private int id;
	private int boazId;
	private boolean isVisited;
	private boolean dead;

///////////// constractor\\\\\\\\\\\\\
	/**
	 * Creates kodkod from GPS coordinates,a map,int whoAmI , int boazIdand an id.
	 * @param p GPS coordinate
	 * @param whoAmI 1-box, 2- fruit, 3- me, 4- packman
	 * @param map
	 * @param id
	 * @param boazId
	 */
	public Kodkod( Gps_Point p,int whoAmI,Map map,int id,int boazId) {
		setLocationGps(p);
		setWhoAmI(whoAmI);
		this.map= map;
		setId(id);
		setBoazId(boazId);
	}

///////////// Getters and Setters \\\\\\\\\\\\\
	public Gps_Point getLocationGps() {
		return locationGps;
	}

	public void setLocationGps(Gps_Point locationGps) {
		this.locationGps = locationGps;
	}

	public ArrayList<Kodkod> getConnected() {
		return connected;
	}

	public void setConnected(ArrayList<Kodkod> connected) {
		this.connected = connected;
	}

	public int getWhoAmI() {
		return whoAmI;
	}


	public void setWhoAmI(int whoAmI) {
		this.whoAmI = whoAmI;
	}


	public Pixel getLocation() {
		return map.converteGpsToPixel(getLocationGps());
	}
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	public boolean isVisited() {
		return isVisited;
	}


	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}


	public int getBoazId() {
		return boazId;
	}


	public void setBoazId(int boazId) {
		this.boazId = boazId;
	}


	public boolean isDead() {
		return dead;
	}


	public void setDead(boolean dead) {
		this.dead = dead;
	}
	

}


	





