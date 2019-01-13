package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MyGisProject implements GIS_project{

	private ArrayList<GIS_layer> project;
	private Meta_data metaData;

	/**
	 * Default constructor
	 */
	public MyGisProject() {
		project = new ArrayList<GIS_layer>();
		
	}

//////////////////////////////GIS_project////////////////////////////////
	@Override
	public boolean add(GIS_layer e) {
		if(getProject().contains(e)) {
			return false;
		}
		return project.add(e);
	}



	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		Iterator collection=c.iterator();
		boolean add= true;
		boolean out=true;
		while(collection.hasNext()) {
			GIS_layer layer=  (GIS_layer) collection.next();
			add= add(layer);
			if(!add) {
				out=add;
			}
		}
		return out;
	}

	@Override
	public void clear() {

		project.clear();

	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return project.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return project.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return getProject().isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {

		return getProject().iterator();
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return getProject().remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return getProject().removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return getProject().retainAll(c);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return getProject().size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return getProject().toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return getProject().toArray(a);
	}

	@Override
	public Meta_data get_Meta_data() {
		// TODO Auto-generated method stub
		return getMetaData();
	}
	
////////////////////////////////////methods////////////////////////////////////
	
	
	/**
	 * return a String that represent the class
	 */
	public String toString() {
		Iterator<GIS_layer> p= iterator();
		String ans= "";
		while(p.hasNext()) {
			ans= ans+ p.next();
		}
		return ans;
	}

///////////////////////////////////////getters and setters//////////////////////////	
	private ArrayList<GIS_layer> getProject() {
		return project;
	}

	private Meta_data getMetaData() {
		return metaData;
	}

	public void setMetaData(Meta_data metaData) {
		this.metaData = metaData;
	}


}
