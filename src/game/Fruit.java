package game;

import GIS.FruitMetaData;
import GIS.GIS_element;
import GIS.Meta_data;
import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Pixel;
import Geom.Point3D;
/**
 * This class represent a fruit in the game.
 * it's has a location in the game and in the game
 * @author ofra&shira
 *
 */

public class Fruit implements GIS_element{

	private Gps_Point locationGPS;
	private Map map;
	private FruitMetaData dataF;
	
	//////////////////constructors////////////////////////////////////////////
/**
 * Creates fruit from pixel coordinates,a map, and an id.  
 * @param x
 * @param y
 * @param map
 * @param id
 */
	public Fruit(int x, int y,Map map,int id) {
		this.map= map;

		this.dataF= new FruitMetaData(id);
		setLocationGPS(new Gps_Point(map.convertePixelToGps((new Pixel(x, y)))));

	}
/**
 * creates a fruit from arrays of String's.
 * need's in to create a game from a CSV file.
 * @param line
 * @param head
 * @param map
 */
	public Fruit(String[] line, String[] head,Map map) {

		setLocationGPS(new Gps_Point(head,line));
		this.map= map;
	

		this.dataF= new FruitMetaData(head,line);
	}

	/**
	 * deep copy of fruit.
	 * @param f
	 */
	public Fruit(Fruit f) {
		setLocationGPS(new Gps_Point(f.getLocationGPS()));
		this.map= f.map;
		this.dataF= new FruitMetaData(f.dataF);
	}


/////////////////////////////method's/////////////////////////////////////
	/**
	 * Returns the representing String of the fruit
	 */
	public String toString() {
		return "Location: " + getLocationGPS() +", "+dataF;
	}

/**
 * check if the GIS_element equals the fruit
 * @param e
 * @return
 */
	public boolean equals(GIS_element e) {
		
		return getGeom().equals(e.getGeom()) && getData().equals(e.getData())&&(whatAmI()==e.whatAmI());
	}



//////////////////////////////////GIS_element/////////////////////////////////

	@Override
	public Geom_element getGeom() {
		return locationGPS;
	}

	@Override
	public Meta_data getData() {
		return dataF;
	}

	@Override
	public void translate(Point3D vec) {

		

	}
	@Override
	public int whatAmI() {
		return 1;
	}
	
	
	////////////////////////////////////////Getters and Setters//////////////////////////////////
	protected Gps_Point getLocationGPS() {
		return locationGPS;
	}

	protected void setLocationGPS(Gps_Point locationGPS) {
		this.locationGPS = locationGPS;
	}



	protected Pixel getLocation() {
		return getMap().converteGpsToPixel(getLocationGPS());
	}

	protected Map getMap() {
		return map;
	}
	protected FruitMetaData getDataF() {
		return this.dataF;
	}


}