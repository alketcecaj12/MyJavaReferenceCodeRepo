package alg.clustering;

import java.util.ArrayList;

public class Cluster {

	int id = 0;
	//ArrayList<Point> cluster = null;
	ArrayList<Placemark> cluster = null;
	
	public Cluster(int i){
		this.id = i;
		
		cluster = new ArrayList<Placemark>();
	}
		
	
	public String toString(){
		ArrayList<Placemark> point = new ArrayList<Placemark>();
//		ArrayList<Point> point = new ArrayList<Point>();

		for(int i = 0; i < cluster.size(); i++) {
			System.out.println(cluster.get(i) );
		}
		return "Cluster id:"+id+",of size: "+cluster.size();
	}
	
	
public static Object[] cluster(int id, ArrayList<Placemark>arr){
		
		Cluster c1 = new Cluster(id);
		
		ArrayList<Placemark> rem_points =  new ArrayList<Placemark>();
		double d = 0.02;
		
		c1.cluster.add(arr.get(0));
		
		for(int i = 1; i < arr.size(); i++){
			if (Placemark.distEuclid(arr.get(0), arr.get(i)) < d) {
				c1.cluster.add(arr.get(i));
				
			}
			else rem_points.add(arr.get(i));
		}
		return new Object[]{c1,rem_points};
	}
	
}
