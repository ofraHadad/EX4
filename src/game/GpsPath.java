package game;

import java.util.ArrayList;

import Geom.Gps_Point;
/**
 * this class represent a path that the player must go in order to reach the fruit closest to him
 * The path is represented by the id of the kodkod to which the player should arrive.
 * @author ofra and shira
 */
public class GpsPath {
	private ArrayList<Integer> path=new ArrayList();
	private double dis;
/////////////Getters and Setters\\\\\\\\\\\\\
	public ArrayList<Integer> getPath() {
		return path;
	}

	public double getDis() {
		return dis;
	}

	public void setDis(double dis) {
		this.dis = dis;
	}

}
