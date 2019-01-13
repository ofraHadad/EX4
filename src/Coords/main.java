package Coords;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import File_format.CsvToKml;
import File_format.DirectoryToKml;
import GIS.ElementMetaData;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.MyGisElement;
import GIS.MyGisLayer;
import GIS.MyGisProject;
import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Pixel;
import Geom.Point3D;
import game.Game;
import game.Map;
import game.ShortestPathAlgo;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

				DirectoryToKml d= new 	DirectoryToKml ();
				CsvToKml r= new CsvToKml();
				
				//r.KmlWriter(r.read("WigleWifi_20171201110209.csv"),"moshe");
			//	r.KmlWriter("WigleWifi_20171203085618.csv");
			MyGisProject p= new MyGisProject();
				d.multyKmlFile( d.read("data", p),"data");
		
		//		/*	System.out.println(l.equals(l2));
		//	System.out.println(l);
		//	String[] h= {"MAC","SSID","AuthMode","FirstSeen","Channel","RSSI","AccuracyMeters","Type","CurrentLatitude","CurrentLongitude","AltitudeMeters"};
		//	String[] s= {"42501_13111_7943940","Partner","UNKNOWN;il","2017-12-01 11:01:23","0","-107","3","WIFI","32.1721826821653","34.8144640170275","13.6504088889507"};
		//
		//	String[] w= {"42501_1311_7943940","Partner","UNKNOWN;il","2017-12-01 11:01:23","0","-107","3","WIFI","32.1721826821653","34.8144640170275","13.6504088889507"};
		//	ElementMetaData a= new ElementMetaData(s,h);
		//	ElementMetaData b= new ElementMetaData(w,h);
		//	System.out.println(a.getUTC());
		//
		//	MyGisElement e= new MyGisElement(s,h);
		//	MyGisElement e1= new MyGisElement(w,h);
		//	System.out.println(e.equals(e1));
		//
		//		 */
		//		
		//
		Map map= new Map();
		Gps_Point gps= new Gps_Point(35.20724539,32.10394266,0);
		//		
		//		Geom_element f= new Gps_Point(35.20724539,32.10394266,0);
		//		Class[] c= {Gps_Point.class};
		//		try {
		//			System.out.println(f.getClass().getDeclaredMethod("equals",c).invoke(f, gps));
		//		} catch (NoSuchMethodException | SecurityException e1) {
		//			// TODO Auto-generated catch block
		//			e1.printStackTrace();
		//		}
//		Pixel p= new Pixel(734,552);
//		//
//		//
//		//			System.out.println(map.converteGpsToPixel(gps));
//		//
//		System.out.println(map.convertePixelToGps(p));
//		//*/
//		//	//	Game g=new Game("game_1543693911932_a.csv",map);
//		//		//System.out.println(g);
		////		ShortestPathAlgo s= new ShortestPathAlgo(g);
		////		System.out.println(s.bestPathCalculation());
		////	d.multyKmlFile(s.bestPathCalculation(), "game");
		// catch (IllegalAccessException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (IllegalArgumentException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (InvocationTargetException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
	}

}