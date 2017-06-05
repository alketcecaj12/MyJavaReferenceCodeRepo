package kmlpoints.test.clustering;

import java.util.ArrayList;

public class Cluster {

	int id = 0;
	public ArrayList<Point> cluster = null;
	public Cluster(int i){
		this.id = i;
		cluster = new ArrayList<Point>();
	}
		
	
	public String toString(){
		ArrayList<Point> point = new ArrayList<Point>();
		for(int i = 0; i < cluster.size(); i++) {
			System.out.println(cluster.get(i) );
		}
		return "Cluster id:"+id+",of size: "+cluster.size();
	}
	
}
