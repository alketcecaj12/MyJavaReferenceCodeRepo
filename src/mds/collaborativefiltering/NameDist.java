package mds.collaborativefiltering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NameDist implements Comparable <NameDist>{

	String name; 
	double dist; 
	public NameDist(String n, double d){
		this.name  = n;
		this.dist = d; 
		
	}
	@Override
	public int compareTo(NameDist o) {
		if (dist < o.dist)
			return 1; 
	    if (dist > o.dist) 
	    	return -1;
	    return 0;
	}
	
	
	public String toString(){
		
		return ""+name+","+dist;
		
	}
	public static void main (String [] args){
	
		List<NameDist> nd = new ArrayList<NameDist>();
		
		nd.add(new NameDist("Marco", 2));
		nd.add(new NameDist("Matteo", 1));
		nd.add(new NameDist("Lucca", 5));
		nd.add(new NameDist("Alket", 4));
		Collections.sort(nd);
	
	
		
	for (int i = 0; i <nd.size(); i++) {
		
		System.out.println(nd.get(i).toString());
		
	}
	}
	
	
	
}
