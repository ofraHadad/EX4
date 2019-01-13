package GIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Geom.Point3D;

/**
 * this class represent the meta data of layers of gps points in google earth
 * @author ofra&shira
 *
 */
public class LayerMetaData implements Meta_data {

	private String createdTime;
	
/**
 * constructor that get an element and take is time
 * @param e
 */
	public LayerMetaData(MyGisElement e) {
		this.createdTime= e.getMetaData().getFirstSeen();	
	}




	public String getCreatedTime() {
		return createdTime;
	}

	@Override
	public long getUTC() {
		// https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds
		SimpleDateFormat sdf = new SimpleDateFormat(createdTime);
		Date date;
		try {
			date = sdf.parse(createdTime);
			long timeInMillis = date.getTime();
			return timeInMillis;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		return "Time: " +getCreatedTime();
	}

}
