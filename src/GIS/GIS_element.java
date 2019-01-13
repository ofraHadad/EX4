package GIS;

import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Point3D;

/**
 * This interface represents a GIS element with geometric representation and meta data such as:
 * Orientation, color, string, timing...
 * @author Boaz Ben-Moshe
 *
 */
public interface GIS_element {
	public Geom_element getGeom();
	public Meta_data getData();
	public void translate(Point3D vec);

	/**
	 * check which type of gis element 
	 * @return 0- MyGisElement, 1- Fruit, 2-packman
	 */
	public int whatAmI();
}
