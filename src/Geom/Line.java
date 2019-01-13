package Geom;

public class Line {

	private double m,n;
	private Pixel p1,p2;
	boolean flag=true;
	
	
	public Line(Pixel p3, Pixel p4) {
		setP1(p3);
		setP2(p4);
		if(p1.getX()-p2.getX()==0) {
				flag=false;
		}
		else {
		setM((double)(p1.getY()-p2.getY())/(p1.getX()-p2.getX()));
		setN((double)(p1.getY()-(getM()*p1.getX())));
		}
		
	}
	
	public boolean onTheKeta(Pixel p) {
		int maxX=Math.max(getP1().getX(),getP2().getX());
		int minX=Math.min(getP1().getX(),getP2().getX());
		int maxY=Math.max(getP1().getY(),getP2().getY());
		int minY=Math.min(getP1().getY(),getP2().getY());
		if(p.getX()>=minX && p.getX()<=maxX 
				&& p.getY()>=minY && p.getY()<=maxY ) {
			return true;
		}
		return false;	
	}
	
	public Pixel cutX(int x) {
		if(!flag) {
			return(new Pixel(-1,-1));
		}
		return new Pixel(x ,(int) (getM()*x+getN()));
		
	}

	public Pixel cutY(int y) {
		if(!flag) {
			return(new Pixel (p1.getX(),y));
		}
		return new Pixel((int)((y-getN())/getM()),(int) (y));
		
	}
	
	public double dis(double x1, double y1, int x2, int y2) {
		return Math.sqrt(Math.pow(y1-y2, 2)+Math.pow(x1-x2, 2));
	}

	public double getM() {
		return m;
	}

	public void setM(double m) {
		this.m = m;
	}

	public double getN() {
		return n;
	}

	public void setN(double n) {
		this.n = n;
	}

	public Pixel getP1() {
		return p1;
	}

	public void setP1(Pixel p1) {
		this.p1 = p1;
	}

	public Pixel getP2() {
		return p2;
	}

	public void setP2(Pixel p2) {
		this.p2 = p2;
	}
	
	
}
