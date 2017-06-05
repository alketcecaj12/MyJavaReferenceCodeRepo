package MatchingPack;

import java.io.Serializable;

import org.gps.utils.LatLonPoint;
import org.gps.utils.LatLonUtils;

public class PLS implements Comparable<PLS>,Serializable,DataI {
	
	public String h;
	public int imsi;
	public long cellac;
	public long time;
	public double lat;
	public double lon;
	public double radius;
	public transient LatLonPoint p;
	
	public PLS(String hash, int im, long cell, long t, double la, double lo, double r){
		this.h = hash;
		this.imsi = im;
		this.cellac = cell;
		this.time = t;
		this.lat = la;
		this.lon = lo;
		this.radius = r;
	}
	
	
	public int compareTo(PLS pls){
		return h.compareTo(pls.h);
	}
	public String toString(){
		return h+","+imsi+","+cellac+","+time+","+lat+","+lon+","+radius;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof PLS)) return false;
		return ((PLS)o).time == time && ((PLS)o).h.equals(h);
	}
	
	@Override
	public int hashCode() {
		return h.hashCode();
	}


	@Override
	public LatLonPoint getLatLonPoint() {
		if(p==null) p = new LatLonPoint(lat,lon);
		return p;
	}


	@Override
	public long getTimestamp() {
		return time;
	}


	@Override
	public boolean match(DataI other, double ds, long dt) {
		if(other instanceof PLS) 
			//
			return Math.abs(time-((PLS)other).time) < dt && cellac == ((PLS)other).cellac; //per valutare i PLS
		
		// per valutare 
		return Math.abs(time-other.getTimestamp()) < dt && LatLonUtils.getHaversineDistance(this.getLatLonPoint(),
				other.getLatLonPoint()) < ds;
	}
	
	public boolean exclude(DataI other, double ds, long dt) {
		
		
		// per valutare 
		return Math.abs(time-other.getTimestamp()) < dt && LatLonUtils.getHaversineDistance(this.getLatLonPoint(),
				other.getLatLonPoint()) > 100+this.radius;
	}
    

	@Override
	public String getUser() {
		// TODO Auto-generated method stub
		return h;
	}
	
	
}
