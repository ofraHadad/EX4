//package game;

package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Gps_Point;
import Geom.Pixel;


public class Map {

	private BufferedImage myImage;
	private String imageName;
	private Gps_Point gpsPoint1;
	private Pixel pixelPoint1;
	private Gps_Point gpsPoint2;
	private Pixel pixelPoint2;
	private Gps_Point start;
	private Gps_Point end;
	private Pixel startP;
	private  Pixel endP;
	private int width;
	private int height;
	private int x;
	private int y;
	
	
//	public Map() {
//		imageName="Ariel1.png";
//		setMyImage(getImageName());
//		this.gpsPoint1= new Gps_Point(35.20691,32.10535,0);
//		this.pixelPoint1= new Pixel(651,61);
//		this.gpsPoint2= new Gps_Point(35.20703,32.10293,0);
//		this.pixelPoint2= new Pixel(668,466);
//		setX(0);
//		setY(0);
//	
//		startP=new Pixel(x,y);
//		setWidth(getMyImage().getWidth());
//		setHeight(getMyImage().getHeight());
//		endP=new Pixel(getWidth(),getHeight());
//		setStart(convertePixelToGps(startP));
//		setEnd(convertePixelToGps(endP));
//		
//
//	}
	///////////////////constructors//////////////////////////////////////////
	/**
	 * the default constructor.
	 * builds the map at Ariel University. 
	 */
	public Map() {
		imageName="Ariel1.png";
		setMyImage(getImageName());
		this.gpsPoint1= new Gps_Point(35.20691,32.10535,0);
		this.pixelPoint1= new Pixel(586,46);
		this.gpsPoint2= new Gps_Point(35.20703,32.10293,0);
		this.pixelPoint2= new Pixel(603,392);

	}


	/**
	 * creates a map with two known points- pixels and GPS- and an Image.
	 * @param imageName- the Image fileName
	 * @param gpsPoint1
	 * @param pixelPoint1
	 * @param gpsPoint2
	 * @param pixelPoint2
	 */
	public Map(String ImageName,  Gps_Point gpsPoint1, Pixel pixelPoint1, Gps_Point gpsPoint2, Pixel pixelPoint2) {
		this.imageName= ImageName;
		setMyImage(ImageName);
		this.gpsPoint1= new Gps_Point(gpsPoint1);
		this.pixelPoint1= new Pixel(pixelPoint1);
		this.gpsPoint2= new Gps_Point(gpsPoint2);
		this.pixelPoint2= new Pixel(pixelPoint2);
		startP=new Pixel(x,y);
		endP=new Pixel(getMyImage().getWidth(),getMyImage().getHeight());
		setStart(convertePixelToGps(startP));
		setEnd(convertePixelToGps(endP));
		setWidth(getMyImage().getWidth());
		setHeight(getMyImage().getHeight());
	}
	
	/////////////////////////////////////methods//////////////////////////////////////////////

	/**
	 * convert a GPS point to a Pixel point
	 * @param gps
	 * @return
	 */
	public Pixel  converteGpsToPixel(Gps_Point gps) {
		//synchronization();
		//Calculates the pixel-to-GPS ratio 
		double gpsDisX= Math.abs(getGpsPoint1().get_x()-gpsPoint2.get_x());
		double pixelDisX= Math.abs(getPixelPoint1().getX()-getPixelPoint2().getX());
		double gpsDisY= Math.abs(getGpsPoint1().get_y()-gpsPoint2.get_y());		
		double pixelDisY= Math.abs(getPixelPoint1().getY()-getPixelPoint2().getY());
		
		//take one of the known points and calculate the jump whit this point 
		double currentJumpX= gps.get_x()-getGpsPoint1().get_x();
		double currentJumpY= gps.get_y()-getGpsPoint1().get_y();


		//take the current jump and multiply it with the ratio. take the pixel of the point and add the result 
		Pixel pixel= new Pixel((int) (getPixelPoint1().getX()+((currentJumpX/gpsDisX)*pixelDisX))
				,(int)(getPixelPoint1().getY()-((currentJumpY/gpsDisY)*pixelDisY)));
//		if(pixel.getX()<0|| pixel.getX()>1300|| pixel.getY()<0 || pixel.getY()>600) {
//			throw new RuntimeException("the point is not in the image");
//		}
		return pixel;
	}
/**
 * convert a pixel point to a gps point.
 * @param pixel
 * @return
 */
	public Gps_Point  convertePixelToGps(Pixel pixel) {
		//same calculation as the privies method
		if(start!=null&&end!=null) {
			//synchronization();
		}
		double gpsDisX= Math.abs(getGpsPoint1().get_x()-gpsPoint2.get_x());
		double pixelDisX= Math.abs(getPixelPoint1().getX()-getPixelPoint2().getX());
		double gpsDisY= Math.abs(getGpsPoint1().get_y()-gpsPoint2.get_y());		
		double pixelDisY= Math.abs(getPixelPoint1().getY()-getPixelPoint2().getY());
		double currentJumpX= pixel.getX()-getPixelPoint1().getX();
		double currentJumpY= pixel.getY()-getPixelPoint1().getY();

		Gps_Point gps= new Gps_Point(((currentJumpX/pixelDisX)*gpsDisX)+getGpsPoint1().get_x(),
				getGpsPoint1().get_y()-((currentJumpY/pixelDisY)*gpsDisY),0);

		return gps;
	}
	
	private void synchronization() {//dosen't work yet
		this.startP= new Pixel(x,y);
		setEndP(new Pixel(getWidth(),getHeight()));
		setGpsPoint1(getStart());
		setPixelPoint1(getStartP());
		setGpsPoint2(getEnd());
		setPixelPoint2(getEndP());

	}
//////////////////////////////////////////////Getters and Setters/////////////////////////////////////////////
	private void setEndP(Pixel endP) {
		this.endP= endP;
		
	}

	public BufferedImage getMyImage() {
		return myImage;
	}

	private void setMyImage(String jpgName) {
		try {
			this.myImage = ImageIO.read(new File(jpgName));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	protected Gps_Point getGpsPoint1() {
		return gpsPoint1;
	}

	protected Pixel getPixelPoint1() {
		return pixelPoint1;
	}

	protected Gps_Point getGpsPoint2() {
		return gpsPoint2;
	}


	protected Pixel getPixelPoint2() {
		return pixelPoint2;
	}

	public String getImageName() {
		return imageName;
	}

	protected Gps_Point getStart() {
		return start;
	}

	protected void setStart(Gps_Point start) {
		this.start = start;
	}

	protected Gps_Point getEnd() {
		return end;
	}

	protected void setEnd(Gps_Point end) {
		this.end = end;
	}

	private Pixel getStartP() {
		return startP;
	}

	private Pixel getEndP() {
		return endP;
	}

	private void setMyImage(BufferedImage myImage) {
		this.myImage = myImage;
	}

	private void setImageName(String imageName) {
		this.imageName = imageName;
	}

	private void setGpsPoint1(Gps_Point gpsPoint1) {
		this.gpsPoint1 =new Gps_Point( gpsPoint1);
	}

	private void setPixelPoint1(Pixel pixelPoint1) {
		this.pixelPoint1 =new Pixel( pixelPoint1);
	}

	private void setGpsPoint2(Gps_Point gpsPoint2) {
		this.gpsPoint2 = new Gps_Point(gpsPoint2);
	}

	private void setPixelPoint2(Pixel pixelPoint2) {
		this.pixelPoint2 = new Pixel(pixelPoint2);
	}

	public int getWidth() {
		return width;
	}

	protected void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	protected void setHeight(int height) {
		this.height = height;
	}
	
	public int getX() {
		return x;
	}

	protected void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	protected void setY(int y) {
		this.y = y;
	}
}
