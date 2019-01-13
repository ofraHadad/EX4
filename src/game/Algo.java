
package game;


import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import GIS.GIS_element;
import Geom.Gps_Point;
import Geom.Line;
import Geom.Pixel;
import Robot.Play;


/**
 * this class give an algorithm for the player  
 * @author ofra and shira
 *
 */
public class Algo {

	private Play play;
	private Game game;
	private Graph graph;
	MyCoords c= new MyCoords();
	private ArrayList<Double> p= new ArrayList<Double>();
	private MyFrame mf;

	////////////////////////////////constructors///////////////////////////////////////
	/**
	 * point to the insert elements
	 * @param p
	 * @param game
	 */
	public Algo(Play p,Game game,MyFrame mf) {
		this.play= p;		
		this.game= game;		
		graph= new Graph(this.game);
		
		play.start();
		this.mf=mf;
	}


	//////////////////////////////////Methods///////////////////////////////////////////////
	/**
	 * the algorithm- the player go to the closest fruit.
	 * we decided to not disturb the packmans, they help eat the fruit faster.   
	 */
	public void algo() {
		if(game.getFruits().size()==0) {//to stop the game.
			play.rotate(0);
		}
		if(!play.isRuning()) {// print the statistics to the console after the game is over.
			System.out.println(play.getStatistics());
			return ;

		}
		ArrayList<Integer> path= computPath().getPath();

		for(int i=1; i<path.size(); i++) {//run over the path
			Kodkod kodkod=getGraph().search(path.get(i));

			//while the path is still relevant.
			while(!getGraph().search(path.get(path.size()-1)).isDead() &&
					!game.getMe().equal(kodkod)&&play.isRuning() ){
				double a= (360-c.azimuth( kodkod.getLocationGps(),game.getMe().getLocationGPS()
						)-90);
				while(a<0) {
					a=a+360;
				}
				p.add(a);
				play.rotate(a);


				graph.getGame().readArrayList(play.getBoard());

				mf.paintElement();

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				graph.reinsert();

			}


			if( getGraph().search(path.get(path.size()-1)).isDead()||!play.isRuning()) {
				break;
			}


		}
		algo();//calculate a new path till all the fruits been eaten or the time up
	}


	//////////////////////////////////private//////////////////////////////////////////////////////////////////////

	private GpsPath computPath() {
		double min=-1;

		GpsPath minPath= new GpsPath();
		for( Kodkod k :getGraph().getGraph() ) {//check  the paths from the player to the fruits and choose the shortest
			if(k.getWhoAmI()==2 && !k.isDead()) {

				GpsPath path= new GpsPath();
				graph.bestPath(0,k.getId(), path);
				if(path.getPath().size()>0) {
					if(path.getDis()<min || min==-1) {
						min=path.getDis();
						minPath.getPath().clear();
						for(Integer i : path.getPath()) {
							minPath.getPath().add(i);
						}
						minPath.setDis(path.getDis());
					}
				}
			}
		}

		return minPath;
	}



	///////////////////////////////////////Getters and Setters/////////////////////////////////
	public Game getGame() {
		return game;
	}


	protected Graph getGraph() {
		return graph;
	}



	protected ArrayList<Double> getP() {
		return p;
	}

}