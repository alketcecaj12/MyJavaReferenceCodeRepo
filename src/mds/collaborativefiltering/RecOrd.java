package mds.collaborativefiltering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecOrd implements Comparable<RecOrd>{

	String critic;
	String movie;
	double rate;
	public RecOrd(String c, String m, double r){
		
		this.critic = c;
		this.movie = m;
		this.rate = r;
	
	}
	@Override
	public int compareTo(RecOrd o) {
		if (rate > o.rate)
			return 1; 
	    if (rate < o.rate) 
	    	return -1;
	    return 0;
	}
	public String toString(){
		
		return "raccomandazione per il critico "+critic+", il film "+movie+",con rate "+rate;
	}	
}
