package GIS;

import Geom.Point3D;
/**
 * Represent the path meta data. implements Meta_data
 * @author ofra&&shira
 *
 */
public class PathMetaData implements Meta_data{

	
	private double time=0;
	/////////////////////////////Meta_data///////////////////////////////////////
	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		return (long) (getTime()*1000);
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}
/////////////////////////////////////////Getters and Settars//////////////////////////////	
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	
	
}
