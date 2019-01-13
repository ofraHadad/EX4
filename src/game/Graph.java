package game;


import java.util.ArrayList;
import java.util.Iterator;
import Coords.MyCoords;
import Geom.Gps_Point;
import Geom.Line;
import Geom.Pixel;

/**
 * this class represent the bored as a graph.
 * the fruits and boxes corners are Kodkods and they all connected only if there is a direct path 
 * @author ofra and shira
 *
 */
public class Graph {
	private ArrayList<Kodkod> graph= new ArrayList<Kodkod>();
	private MyCoords c= new MyCoords();
	private Game game;
	private int id=0;

	/////////////////////////////////////constructors///////////////////////////////////////////////////////////	
	/**
	 * constructor. the graph game points to the game that insert
	 * @param game
	 */
	public Graph(Game game) {
		this.game=game;
		insertGraph();
	}

	//////////////////////////////methods//////////////////////////////////////////////

	/**
	 * updates the graph. used if the has change.
	 */
	public void reinsert() {

		for(Kodkod k: getGraph()) {

			if(k.getWhoAmI()==2) {//update if the fruit still in the game 
				k.setDead(true);
				for(Fruit f: getGame().getFruits()) {
					if(k.getBoazId()==f.getDataF().getId()) {
						k.setDead(false);
						break;
					}
				}	
			}
			if(k.getWhoAmI()==3) {// update the player location
				k.setLocationGps(game.getMe().getLocationGPS());	

			}
			if(k.getWhoAmI()==4) {//update the packman location and if still in the game.

				for(Packman p: game.getPackmans()) {
					if(k.getBoazId()==p.getDataP().getId()) {
						k.setLocationGps(p.getLocationGPS());
						break;
					}
				}
				k.setDead(true);
				for(Packman p: getGame().getPackmans()) {
					if(k.getBoazId()==p.getDataP().getId()) {
						k.setDead(false);
						break;
					}
				}	
			}

		}
		//update the player connections
		Iterator<Kodkod> boxesJ= graph.iterator();
		Kodkod k= graph.get(0);
		k.getConnected().clear();
		boxesJ.next();

		while(boxesJ.hasNext()) {
			Kodkod j= boxesJ.next();

			if(isLegal(k.getLocation(), j.getLocation())&&!j.isDead()) {
				k.getConnected().add(j);
				j.getConnected().add(k);
			}
		}

	}
	/**
	 * the method compute the best path  between two kodkods in the graph.
	 * we used the code from: https://www.geeksforgeeks.org/find-paths-given-source-destination/
	 * @param s- the id of the first kodkod
	 * @param d-the id of the second kodkod
	 * @param minPathList- an empty path
	 * @return
	 */
	public void bestPath(int s, int d, GpsPath minPathList) 
	{ 

		GpsPath pathList= new GpsPath();
		//add source to path[] 
		pathList.getPath().add(s); 

		//Call recursive utility 
		bestPathUtil(s, d,pathList,minPathList,0); 

		for(Kodkod k: graph) {
			k.setVisited(false);
		}
		return;
	} 

	/**
	 * return the kodkod with the insert id.
	 * return null if the kodkod with that id is not in the graph
	 * @param id
	 * @return
	 */
	public Kodkod search(int id) {
		for(Kodkod k: graph) {
			if(k.getId()==id) {
				return k;
			}
		}
		return null;
	}


	////////////////////////////////////////private ////////////////////////////////////////////////////
	/**
	 * Initializing the graph by the game parameters.
	 */
	private void insertGraph() {
		//add all the elements to the graph. the id of each kodkod is the same as his place in the graph
		Iterator<Box> boxes= getGame().getBoxes().iterator();
		graph.add(new Kodkod(game.getMe().getLocationGPS(),3,game.getMap(),id++,0));
		while (boxes.hasNext()) {
			Box b= boxes.next();
			graph.add(new Kodkod(new Gps_Point(b.getLocationGPS()),1,getGame().getMap(),id++,b.getId()));
			graph.add(new Kodkod(new Gps_Point(b.getLocationGPS().get_x(),b.getMaxGPS().get_y(),0),1,getGame().getMap(),id++,b.getId()));
			graph.add(new Kodkod(new Gps_Point(b.getMaxGPS().get_x(),b.getLocationGPS().get_y(),0),1,getGame().getMap(),id++,b.getId()));
			graph.add(new Kodkod(new Gps_Point(b.getMaxGPS()),1,getGame().getMap(),id++,b.getId()));
		}
		for(Fruit i : game.getFruits()) {
			graph.add(new Kodkod(i.getLocationGPS(),2,game.getMap(),id++,i.getDataF().getId()));
		}
		for(Packman i: game.getPackmans()) {
			graph.add(new Kodkod(i.getLocationGPS(),4,game.getMap(),id++,i.getDataP().getId()));
		}

		conected();
	}

	/**
	 * update each kodkod connections in the graph
	 */
	private void conected() {
		Iterator<Kodkod> boxesI=graph.iterator();
		int count=0;
		while(boxesI.hasNext()) {
			Kodkod i= boxesI.next();

			Iterator<Kodkod> boxesJ= graph.iterator();
			for(int k=-1 ; k<count; k++) {
				boxesJ.next();
			}
			while(boxesJ.hasNext()) {
				Kodkod j= boxesJ.next();

				if(isLegal(i.getLocation(), j.getLocation())&&!j.isDead()) {
					i.getConnected().add(j);
					j.getConnected().add(i);

				}

			}

			count++;
		}
	}


	private void bestPathUtil(Integer s, int d,  GpsPath localPathList,GpsPath MinlocalPathList, int count) { 

		// Mark the current node 
		graph.get(s).setVisited(true);

		if (s.equals(d)) 
		{ 

			if(	MinlocalPathList.getPath().isEmpty()) {

				for(Integer i: localPathList.getPath()) {
					MinlocalPathList.getPath().add(i);
				}
			}
			localPathList.setDis(distance(localPathList.getPath()));
			MinlocalPathList.setDis(distance(MinlocalPathList.getPath()));

			if(MinlocalPathList.getDis()>localPathList.getDis()) {

				MinlocalPathList.getPath().clear();
				for(Integer i: localPathList.getPath()) {
					MinlocalPathList.getPath().add(i);
				}
			}

			graph.get(s).setVisited(false);

			return ; 
		} 

		// Recur for all the vertices 
		// adjacent to current vertex 
		for(Kodkod k: graph.get(s).getConnected())
		{ 
			if (!k.isVisited() ) 
			{ 
				// store current node 
				// in path[] 

				localPathList.getPath().add(k.getId()); 
				bestPathUtil(k.getId(), d,localPathList,MinlocalPathList,count++); 

				// remove current node 
				// in path[] 
				localPathList.getPath().remove((Object)k.getId()); 


			} 
		} 

		// Mark the current node 
		graph.get(s).setVisited(true); 
	} 


	/**
	 * check if two points have a direct path.
	 * @param p1
	 * @param p2
	 * @return
	 */
	private boolean isLegal(Pixel p1, Pixel p2) {
		Iterator <Box> boxes= game.getBoxes().iterator();
		Line l= new Line(p1,p2);
		while(boxes.hasNext()) {
			Box b= boxes.next();
			if(	(l.onTheKeta(l.cutX(b.getLocation().getX())) && b.onTheBoxX(l.cutX(b.getLocation().getX())))
					|| (l.onTheKeta(l.cutX(b.getMax().getX())) && b.onTheBoxX(l.cutX(b.getMax().getX())))
					|| (l.onTheKeta(l.cutY(b.getLocation().getY())) && b.onTheBoxY(l.cutY(b.getLocation().getY())))
					||(l.onTheKeta(l.cutY(b.getMax().getY())) && b.onTheBoxY(l.cutY(b.getMax().getY()))))
			{
				return false;
			}

		}
		return true;
	}
	
	/**
	 * Calculate the all path in meters 
	 * @param path
	 * @return
	 */
	private double distance( ArrayList<Integer> path) {
		double dis=0;
		for(int i=0; i<path.size()-1; i++) {

			dis=dis+c.distance3d(search(path.get(i)).getLocationGps(),search(path.get(i+1)).getLocationGps());
		}
		return dis;
	}

	/////////////////////////////////Getters and Setters////////////////////////////////////////////////
	protected ArrayList<Kodkod> getGraph() {
		return graph;
	}
	
	public MyCoords getC() {
		return c;
	}
	
	protected Game getGame() {
		return game;
	}
	
}