package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Pixel;
import Geom.Point3D;
import game.Map;

public class MyGisElement implements GIS_element{
	private Gps_Point gps;
	private ElementMetaData metaData;


	/**
	 * Constructor that get two arrays of String
	 * used in the read method , to read csv files 
	 * @param line
	 * @param head
	 */
	public MyGisElement(String[] line, String[] head) {

		gps= new Gps_Point(head, line);
		metaData= new ElementMetaData(line, head);
	}
	
	

	/////////////////////////////////GIS_element/////////////////////////////////////
	@Override
	public Geom_element getGeom() {
		return gps;
	}
	@Override
	public Meta_data getData() {

		return metaData;
	}

	@Override
	public void translate(Point3D vec) {
		MyCoords c= new MyCoords();
		Gps_Point newGps=	c.add((Gps_Point) getGeom(), vec);
		setGps(newGps);
	}
	@Override
	public int whatAmI() {
		return 0;
	}

	/////////////////////////////////methods////////////////////////////
	/**
	 * check if two elements are equals
	 * @param e
	 * @return
	 */
	public boolean equals(GIS_element e) {
		if(getGeom().equals(e.getGeom()) && getData().equals(e.getData()) &&( whatAmI()==e.whatAmI())){
			return true;
		}
		return false;
	}

	/**
	 * return a String that represent the class
	 */
	public String toString() {
		return "GPS Point: "+getGeom().toString()+ "\n"+ getData().toString();
	}


	//////////////////////////////getters and setters//////////////////////////////////////////
	private void setMetaData(ElementMetaData metaData) {
		this.metaData = metaData;
	}

	public ElementMetaData getMetaData() {
		return  metaData;
	}

	private void setGps(Gps_Point geom) {
		this.gps = geom;


	}
	public Gps_Point getGps(){
		return gps;
	}
	



	///////////////////////////////private///////////////////////////	





}
