package GIS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Geom.Point3D;

/**
 * this class represent the meta data of gps point in google earth
 * @author ofra&shira
 *
 */
public class ElementMetaData implements Meta_data{

	private String MAC;
	private String SSID;
	private String AuthMode;
	private String FirstSeen;
	private int Channel;
	private int RSSI;
	private int AccuracyMeters;
	private String Type;
	private String date;
	private String time;



	/**
	 * Constructor that get two arrays of String
	 * used in the read method , to read csv files 
	 * @param line
	 * @param head
	 */
	public ElementMetaData(String[] line,String[] head) {

		setMAC(line[serch(head, "MAC")]);
		setSSID(line[serch(head, "SSID")]);
		setAuthMode(line[serch(head, "AuthMode")]);
		setFirstSeen(line[serch(head, "FirstSeen")]);
		setChannel(Integer.parseInt(line[serch(head, "Channel")]));
		setRSSI(Integer.parseInt(line[serch(head, "RSSI")]));
		setAccuracyMeters(Integer.parseInt(line[serch(head, "AccuracyMeters")]));
		setType(line[serch(head, "Type")]);
		int i= getFirstSeen().indexOf(' ');
		setDate(getFirstSeen().substring(0, i));
		setTime(getFirstSeen().substring(i+1));

	}
	public ElementMetaData() {
		
	}
	/**
	 * check if two ElementMetaData are equals
	 * @param m
	 * @return
	 */
	public boolean equals(ElementMetaData m) {
		if(getMAC().equals(m.getMAC()) && getSSID().equals(m.getSSID()) && getAuthMode().equals(m.getAuthMode()) &&
				getFirstSeen().equals(m.getFirstSeen()) && getChannel()==m.getChannel() && getRSSI()==m.getRSSI() &&
				getAccuracyMeters()==m.getAccuracyMeters() && getType().equals(m.getType())) {return true;}
		return false;
	}

	///////////////////////////Meta_data/////////////////////////////////////////////////
	@Override
	public long getUTC() {
		// https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds
		SimpleDateFormat sdf = new SimpleDateFormat(FirstSeen);
		Date date;
		try {
			date = sdf.parse(FirstSeen);
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

	@Override
	public String toString() {
		return "MAC: "+getMAC()+", SSID: "+ getSSID()+ ", AuthMode: "+ getAuthMode()+", FirstSeen: "+getFirstSeen()+
				", Channel: "+ getChannel()+ ", RSSI: "+ getRSSI()+", AccuracyMeters: "+ getAccuracyMeters()+", Type: "+getType();
	}

	///////////////////////////////getters and setters///////////////////////////////////
	public String getDate() {
		return date;
	}

	private void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	private void setTime(String time) {
		this.time = time;
	}


	private void setMAC(String mAC) {
		MAC = mAC;
	}

	private void setSSID(String sSID) {
		SSID = sSID;
	}

	private void setAuthMode(String authMode) {
		AuthMode = authMode;
	}

	private void setFirstSeen(String firstSeen) {
		FirstSeen = firstSeen;
	}

	private void setChannel(int channel) {
		Channel = channel;
	}

	private void setRSSI(int rSSI) {
		RSSI = rSSI;
	}

	private void setAccuracyMeters(int accuracyMeters) {
		AccuracyMeters = accuracyMeters;
	}

	private void setType(String type) {
		Type = type;
	}

	public String getMAC() {
		return MAC;
	}

	public String getSSID() {
		return SSID;
	}

	public String getAuthMode() {
		return AuthMode;
	}

	public String getFirstSeen() {
		return FirstSeen;
	}

	public int getChannel() {
		return Channel;
	}

	public int getRSSI() {
		return RSSI;
	}

	public int getAccuracyMeters() {
		return AccuracyMeters;
	}

	public String getType() {
		return Type;
	}

	///////////////////////////////////////private/////////////////////////////////
	private int serch(String[] head,String s) {
		int index=head.length;
		for (int i=0; i<head.length; i++) {
			if(s.equals(head[i])) {
				index= i;
				return index;
			}
		}

		if(index>=head.length) {
			throw new RuntimeException("ivalid input");	
		}
		return index;
	}

}
