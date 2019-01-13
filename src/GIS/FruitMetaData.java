package GIS;

import Geom.Point3D;
/**
 * represent the meta data of a fruit- alt, weight, id, time; implement Meta_data
 * @author ofra and shira
 *
 */
public class FruitMetaData implements Meta_data{

	private int alt;
	private int weight;
	private int id;
	private double whenEaten;
	
////////////////////////////Constructors/////////////////////////////////////////
/**
 * get the fruit ID, and create the fruit meta data. 
 * @param id
 */
	public FruitMetaData(int id) {
		setAlt(0);
		setWeight(1);
		setId(id);
	}
/**
 * create a fruit meta data from two String arrays.
 * (used for read a CSV file)
 * @param head
 * @param line
 */
	public FruitMetaData(String[] head, String [] line) {

		int hasWeight= serch(head,"Weight");
		int hasAlt= serch(head,"Alt");
		int hasId= serch(head,"id");

		if(hasId==head.length) {
			throw new RuntimeException("EROR: the fruit has no id");
		}
		else {
			setId(Integer.parseInt(line[hasId]));
			if(hasWeight== head.length) {
				setWeight(0);
			}
			else {
				double speed=Double.parseDouble(line[hasWeight]);
				setWeight((int)speed);
			}
			if(hasAlt == head.length) {
				setAlt(0);
			}
			else {
				double alt=Double.parseDouble(line[hasAlt]);
				setAlt((int) alt);
			} 
		}

	}
/**
 * the copy constructor
 * @param data
 */
	public FruitMetaData(FruitMetaData data) {
		setAlt(data.getAlt());
		setId(data.getId());
		setWeight(data.getWeight());
		setWhenEaten(data.getWhenEaten());
	}

	
	///////////////////////////////Meta_data////////////////////////////////////
	@Override
	public long getUTC() {
			
		
		return (long) (getWhenEaten()*1000);
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}

////////////////////////////////methods//////////////////////////////////////////////////////////////
	public String toString() {
		return "Alt: "+getAlt()+ ", Weight/Grade: "+getWeight()+ ", Id: "+ getId()+", Time: "+getWhenEaten()+", Start: "+0.0+";";

	}
	
	public boolean eqauls(Meta_data e) {
		return (getUTC()==e.getUTC())&&(toString().substring(toString().indexOf("Id: ")+4, toString().indexOf(", Time:")))
				.equals(e.toString().substring(e.toString().indexOf("Id: ")+4, e.toString().indexOf(", Time:")));
	}


	private int serch(String[] head,String s) {
		int index=head.length;
		for (int i=0; i<head.length; i++) {
			if(s.equalsIgnoreCase(head[i])||head[i].contains(s)) {
				index= i;
				return index;
			}
		}


		return index;
	}

	///////////////////////////////////////Geterrs and Setters//////////////////////////////////
	private void setAlt(int alt) {
		this.alt = alt;
	}

	private void setWeight(int weight) {
		this.weight = weight;
	}

	private void setId(int id) {
		this.id = id;
	}

	public int getAlt() {
		return alt;
	}

	public int getWeight() {
		return weight;
	}

	public int getId() {
		return id;
	}
	public double getWhenEaten() {
		return whenEaten;
	}

	public void setWhenEaten(double whenEaten) {
		this.whenEaten = whenEaten;
	}


}
