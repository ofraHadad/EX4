package File_format;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;


import GIS.GIS_layer;
import GIS.GIS_project;


import GIS.MyGisLayer;
import GIS.MyGisProject;
import GIS.ProjectMetaData;

/**
 * this class read a folder and take the csv file of gps point 
 * and generate it to one kml file
 * @author ofra&shira
 *
 */
public class DirectoryToKml {

	/**
	 * we took the code from this link:
	 * https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
	 * @param parentDirectory
	 * @param p
	 * @return
	 */
	public  MyGisProject read(String parentDirectory,MyGisProject p){
		File[] filesInDirectory = new File(parentDirectory).listFiles();
		for(File f : filesInDirectory){
			if(f.isDirectory()){
				read(f.getAbsolutePath(),p);
			}
			String filePath = f.getAbsolutePath();
			String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());
			if("csv".equals(fileExtenstion)&&filePath.contains("WigleWifi")){
				//add to a list or array
				CsvToKml r= new CsvToKml();

				p.add(r.read(filePath));
			}

		} 
		Iterator<GIS_layer> it= p.iterator();
		p.setMetaData(new ProjectMetaData((MyGisLayer)it.next()));     
		return p;
	}

	/**
	 * make a kml file from a project- an arrayList of layers 
	 * @param csvfile
	 */
	public void multyKmlFile(GIS_project project, String name) {
		try 
		{

			FileWriter writer = new FileWriter(name+".kml");
			BufferedWriter bw = new BufferedWriter(writer);
			StringBuilder sb = new StringBuilder();
		//	MyGisProject project=new MyGisProject();
			//project=read(csvfile, project);
			//Iterator<GIS_layer> itData= project.iterator();
			//project.setMetaData(new ProjectMetaData((MyGisLayer)itData.next()));

			Iterator<GIS_layer> itP= project.iterator();
			//create kml file	



			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"blue\"><IconStyle><Icon><href>"
					+ "http://maps.google.com/mapfiles/ms/icons/blue-dot.png</href>"
					+ "</Icon></IconStyle></Style><Style id=\"blue\"><IconStyle><Icon><href>"
					+ "http://maps.google.com/mapfiles/ms/icons/blue-dot.png</href>"
					+"</Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>"
					+"http://maps.google.com/mapfiles/kml/paddle/ylw-stars.png</href>"
					+"</Icon></IconStyle></Style><Style id=\"red\"><IconStyle><Icon><href>"
					+"http://maps.google.com/mapfiles/kml/paddle/red-stars.png</href>"
					+ "</Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>"
					+ "http://maps.google.com/mapfiles/ms/icons/green-dot.png</href>"
					+ "</Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\r\n" + "");

	
			while(itP.hasNext()) {
				MyGisLayer layer= (MyGisLayer) itP.next();
				CsvToKml s= new CsvToKml();
				if(layer.get(0).whatAmI()==2) {
				sb.append(s.contentPath(layer,project.get_Meta_data().getUTC()));
				}
				else {
					sb.append(s.content(layer));
				}
			}
			sb.append("\n</Folder>\n");
			sb.append("</Document></kml>");

			bw.write(sb.toString());
			bw.close();



		} 
		catch (IOException e) {
			e.printStackTrace();
		}	

	}

}
