package GIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Geom.Point3D;

public class ProjectMetaData implements Meta_data{
	
	private String time;
	
	/**
	 * constructor that get a layer and is time
	 * @param l
	 */
	public  ProjectMetaData(MyGisLayer l) {
		time= l.getMetaData().getCreatedTime();
	}
	
	//////////////////////////////////GIS_project/////////////////////////////////

	@Override
	public long getUTC() {
		// https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds
				SimpleDateFormat sdf = new SimpleDateFormat(time);
				Date date;
				try {
					date = sdf.parse(time);
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

}
