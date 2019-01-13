package game;

import GIS.FruitMetaData;
import GIS.GIS_element;
import GIS.Meta_data;
import GIS.PackmanMetaData;
import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Pixel;
import Geom.Point3D;

public class Packman implements GIS_element, Runnable{

	private Pixel location;	
	private Gps_Point locationGPS;
	private Map map;
	private PackmanMetaData dataP;


	public Packman(int x,int y, Map map,int id) {
		this.map= map;
		setLocation(new Pixel(x, y));
		this.dataP= new PackmanMetaData(id);
		setLocationGPS(new Gps_Point(map.convertePixelToGps(new Pixel(x, y))));
		

	}

	public Packman(String[] line, String[] head,Map map) {
		Gps_Point gps= new Gps_Point(head,line);
		setLocationGPS(gps);
		this.map=map;
		location=getMap().converteGpsToPixel(gps);

		this.dataP= new PackmanMetaData(head, line);
		
	}
	
	public Packman(Packman p) {
		this.map= p.map;
		this.location= new Pixel (p.getLocation());
		this.dataP= new PackmanMetaData(p.getDataP());
		setLocationGPS(p.getLocationGPS());
		
	}
	
	
	
	
	public String toString() {
		
		return "Location: " + locationGPS + ", "+ this.dataP;
	}



	public Map getMap() {
		return map;
	}

	protected PackmanMetaData getDataP() {
		return dataP;
	}

	protected Pixel getLocation() {
		 return getMap().converteGpsToPixel(getLocationGPS());
	}
	



	@Override
	public Geom_element getGeom() {
		return getLocationGPS();
	}

	@Override
	public Meta_data getData() {
		return this.dataP;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub

	}
	@Override
	public int whatAmI() {
		return 2;
	}

	
	
	public boolean equals(GIS_element e) {
		return getGeom().equals(e.getGeom())&&getData().equals(e.getData())&&(whatAmI()==e.whatAmI());
		
	}

	protected Gps_Point getLocationGPS() {
		return locationGPS;
	}

	protected void setLocationGPS(Gps_Point locationGPS) {
		this.locationGPS = new Gps_Point(locationGPS);
		//setLocation(getMap().converteGpsToPixel(getLocationGPS()));
	}
	
	private void setLocation(Pixel location) {
		this.location = location;
	}

	private void setMap(Map map) {
		this.map = map;
	}

	private void setDataP(PackmanMetaData dataP) {
		this.dataP = dataP;
	}

	@Override
	public void run() {
	
		
		
	}


}
