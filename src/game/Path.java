package game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.Meta_data;
import GIS.PathMetaData;

/**
 * represent the best path of all th packmans. implements GIS_project.
 * @author ofra and shira
 *
 */
public class Path implements  GIS_project{

	ArrayList<GIS_layer> path= new ArrayList<GIS_layer>();
	PathMetaData data=new PathMetaData();
	/////////////////////////////GIS_project//////////////////////////////////
	@Override
	public boolean add(GIS_layer e) {
		if(contains(e)) {
			return false;
		}
		return getPath().add(e);
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
		getPath().clear();
		
	}

	@Override
	public boolean contains(Object o) {
		
		return getPath().contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		
		return getPath().containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return getPath().isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		// TODO Auto-generated method stub
		return path.iterator();
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return getPath().remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return getPath().removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return getPath().retainAll(c);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return getPath().size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return getPath().toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return getPath().toArray(a);
	}

	@Override
	public Meta_data get_Meta_data() {

		return data;
	}
//////////////////////////////methods//////////////////////////////
	/**
	 * a string represents the path
	 */
	public String toString() {
		Iterator<GIS_layer> it= path.iterator();
		String ans="";
		while(it.hasNext()) {
			ans=ans+it.next()+"\n";
		}
		return ans;
	}
	
	///////////////////////////Getters and Setters/////////////////////////////////////////////
	private ArrayList<GIS_layer> getPath() {
		return path;
	}



	protected PathMetaData getData() {
		return data;
	}
	
	

}
