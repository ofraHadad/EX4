package game;

import java.util.Iterator;

import GIS.MyGisLayer;
import Geom.Gps_Point;


/**
 *  @author Shira & ofra
 * class that get a game and calculates the optimal path so that all the fruits are eaten in the shortest time possible
 */
public class ShortestPathAlgo {
	private Game game;

	////////////////////Constructor\\\\\\\\\\\\\\\\\\
	/**
	 * Constructor that get a game
	 * @param game
	 */
	public ShortestPathAlgo(Game game) {	
		setGame(game);
	}


	////////////////////Methods\\\\\\\\\\\\\\\\\\


/**
 * update the path with the best path found to the game.
 * @return
 */
	public Path forGPS() {
		Gps_Point first[]=pathalgo();
		Iterator<Packman> it1=getGame().getPackmans().iterator();
		Path path= new Path();
		int i=0;
		while(it1.hasNext()) {
			Packman pack=it1.next();
			Iterator<Fruit> it= pack.getDataP().getEat().iterator();
			Iterator<Fruit> it2=pack.getDataP().getEat().iterator();

			double t=0;
			MyGisLayer layer= new MyGisLayer();
			pack.setLocationGPS(first[i]);
			i++;
			layer.add(pack);

			if(it2.hasNext()) {
				pack.getDataP().setTimeNext(it2.next().getDataF().getWhenEaten());
			}
			int grade=0;
			while(it.hasNext()) {

				Fruit f= new Fruit(it.next());


				Packman pNext= new Packman(pack);

				double time=f.getDataF().getWhenEaten();
				pNext.getDataP().setTime(time);
				layer.add(f);
				layer.add(pNext);

				grade=grade+f.getDataF().getWeight();
				pNext.getDataP().setGrade(grade);
				pNext.setLocationGPS(f.getLocationGPS());
				if(it2.hasNext()) {
					pNext.getDataP().setTimeNext(it2.next().getDataF().getWhenEaten());


				}
				else {

					t=pNext.getDataP().getTime()+10;
					if(t>path.getData().getTime()) {
						path.getData().setTime(t);
					}
					pNext.getDataP().setTimeNext(path.getData().getTime());
				}

			}


			path.add(layer);
		}
		return path;
	}



	private Gps_Point[] pathalgo() {
		/*the main ïidea:
		 *check for each packman which fruit us the closest to him.
		 *then check with which packman best to go, considering the time its take him to it the fruit plus the time he already spend.
		 */
		Iterator<Fruit> it= getGame().getFruits().iterator();
		int size=getGame().getPackmans().size();
		double close[][]= new double[4][size];//0-min time, 1- fruit, 2- time, 3- grade
		Gps_Point first[]= new Gps_Point[size];//
		for(int i=0; i<size; i++) {//run over the packmans of the game and save the original location of each packman.
			first[i]= getGame().getPackmans().get(i).getLocationGPS();
		}

		for (int i=0; i<size; i++) {
			close[0][i]=-1;
		}
		boolean in=true;

		while(it.hasNext()) {//if there are still fruits to eat
			for(int i=0; i<size; i++) {//run over the packmans

				Packman p= getGame().getPackmans().get(i);


				if(in||close[0][i]==-2) {//if it's the first round or the closest fruit was eaten
					for(int j=0; j<getGame().getFruits().size();j++) {//run over the fruits
						Fruit f= getGame().getFruits().get(j);
						double dis=(p.getLocationGPS().distance3d(f.getLocationGPS())-p.getDataP().getRadius());

						double time= (dis)/p.getDataP().getSpeed();
						if(time<0) {//when in the same place (distance3d=0 - raduis) 
							time=0;
						}
						if(close[0][i]<0) {//the first round
							close[0][i]=time;
							close[1][i]=j;
						}
						if(time<close[0][i]) {//check if there is a new minimum
							close[0][i]=time;//update the min fruit an time
							close[1][i]=j;
						}
					}
					if(i==size-1) {//the and of the round
						in=false;
					}
				}
			}
			double min= close[0][0]+close[2][0];//the time the packman past if he eat the fruit
			int f=(int) close[1][0];
			int p=0;
			for(int i=1; i<size; i++) {//run over the packmans

				if(min>close[0][i]+close[2][i] ){//check if min
					min=close[0][i]+close[2][i];//updates the minimum time, packman and fruit 
					f=(int) close[1][i];
					p=i;
				}

			}
			for(int i=0; i<size; i++) {//run over the packmans and update the fruit list

				if((int)(close[1][i])>f) {
					close[1][i]--;
				}
				if(f==close[1][i]) {//if fruit been eaten update a new fruit
					close[0][i]=-2;
				}
			}
			close[2][p]=min;//update the past time
			Fruit now=getGame().getFruits().get(f);
			close[3][p]=close[3][p]+now.getDataF().getWeight();//update grade
			now.getDataF().setWhenEaten(close[2][p]);//update in the fruit when it was eaten
			getGame().getPackmans().get(p).getDataP().getEat().add(now);//add the fruit to the list in the packman
			getGame().getPackmans().get(p).setLocationGPS(now.getLocationGPS());//update the new location of the packman
			getGame().getFruits().remove(f);//remove the eaten fruit from the game

		}
		return first;

	}
	
	////////////////////////Getters and Setters////////////////////////////////
	public Game getGame() {
		return game;
	}

	private void setGame(Game game) {
		this.game = game;
	}

}
