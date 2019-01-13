package Coords;
import java.awt.Point;

import Geom.Gps_Point;
import Geom.Point3D;
/**
 * This interface represents a basic coordinate system converter, including:
 * 1. The 3D vector between two lat,lon, alt points 
 * 2. Adding a 3D vector in meters to a global point.
 * 3. convert a 3D vector from meters to polar coordinates
 * @authors ofra and Shira
 *
 */
public class MyCoords implements coords_converter{
	final long Radius_earth=6371000;
    @Override
	public Gps_Point add(Gps_Point gps, Point3D local_vector_in_meter) {
		double LonNorm= Math.cos(gps.get_x()*(Math.PI/180));
		Point3D meter=ConvertsDegreesToMeters(gps, LonNorm);
		meter.add(local_vector_in_meter);
		Gps_Point gpsDestination= ConvertsMeterToDegree(meter,LonNorm);
		if(gpsDestination.get_z()<-450) {
			throw new RuntimeException ("Destination does not exist. elevation less than -450");
		}
		return gpsDestination;
	}

	@Override
	public double distance3d(Gps_Point gps0, Gps_Point gps1) {
		return gps0.distance3d(gps1);
		
	}


	@Override
	public Point3D vector3D(Gps_Point gps0, Gps_Point gps1) {
		Gps_Point diff=new Gps_Point(gps1.get_x()-gps0.get_x(),gps1.get_y()-gps0.get_y(),gps1.get_z()-gps0.get_z());
		double LonNorm= Math.cos(gps0.get_x()*(Math.PI/180));
		Point3D diffMeter=ConvertsDegreesToMeters(diff,LonNorm);
		return diffMeter;
	}
	@Override
	public double[] azimuth_elevation_dist(Gps_Point gps0, Gps_Point gps1) {
		double[] azimuth_elevation_dist=new double [3];
		azimuth_elevation_dist[0]=azimuth(gps0,gps1);
		azimuth_elevation_dist[1]=elevation(gps0,gps1);
		azimuth_elevation_dist[2]=distance3d(gps0, gps1);
		return azimuth_elevation_dist;
	}


	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if(p.x()>180 || p.x()<-180)
			return false;
		if(p.y()>90 || p.y()<-90)
			return false;
		if(p.z()<-450)
			return false;
		return true;
	}
/**
 * The function calculates the azimuth between two gps points
 * @param p0
 * @param p1
 * @return the azimuth
 */
	public double  azimuth(Gps_Point p0, Gps_Point p1) {
		double longDiff= Point3D.d2r((p1.get_y()-p0.get_y()));
		double p0_x_r=Point3D.d2r(p0.get_x());
		double p1_x_r=Point3D.d2r(p1.get_x());
		double y = Math.sin(longDiff)*Math.cos(p1_x_r);
		double x = Math.cos(p0_x_r)*Math.sin(p1_x_r)-Math.sin(p0_x_r)*Math.cos(p1_x_r)*Math.cos(longDiff);
		return (Point3D.r2d(Math.atan2(y, x))+360)%360;
	}
/**
 * The function calculates the elevation between two gps points
 * @param p0
 * @param p1
 * @return the elevation
 */
	private double  elevation(Gps_Point p0, Gps_Point p1) {
		return Point3D.r2d(Math.asin((p1.get_z()-p0.get_z())/(distance3d(p0, p1))));
	}
	/**
	 * /**
	 * The function converts a gps point to a point3D point
	 * @param gps
	 * @param LonNorm
	 * @return The point received after the conversion
	 */
	
	private Point3D ConvertsDegreesToMeters(Gps_Point gps, double LonNorm) {
		long Radius_earth=6371000;
		Point3D rad=new Point3D(Point3D.d2r(gps.get_x()), Point3D.d2r(gps.get_y()), gps.get_z());
		Point3D meter=new Point3D((Math.sin(rad.x()))*Radius_earth, (Math.sin(rad.y()))*Radius_earth*LonNorm,rad.z());
		return meter;
	}
	/**
	 *  The function converts a point3D point to a gps point
	 * @param meter
	 * @param LonNorm
	 * @return The point received after the conversion
	 */
	private Gps_Point ConvertsMeterToDegree(Point3D meter,double LonNorm) {
		long Radius_earth=6371000;
		Point3D rad=new Point3D((Math.asin((meter.x())/(Radius_earth))),(Math.asin((meter.y())/(Radius_earth*LonNorm))),meter.z());
		Gps_Point dig=new Gps_Point(rad.r2d(rad.x()), rad.r2d(rad.y()),rad.z());
		return dig;
	}


}
