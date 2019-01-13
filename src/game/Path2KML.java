package game;

import File_format.DirectoryToKml;

/**
 * take a path and represent it with a KML file.
 * @author ofra and shira
 *
 */
public class Path2KML {
	
	public  Path2KML(Path path, String file) {
		DirectoryToKml d= new DirectoryToKml();
		d.multyKmlFile(path, file);

	}
}
