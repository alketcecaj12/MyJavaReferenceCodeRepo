package alg.test.clustering;

import java.util.ArrayList;

public class ClusterPoint {

	int id = 0;
    ArrayList<Point> cluster = null;
	
	public ClusterPoint(int i){
		this.id = i;
		//cluster = new ArrayList<Point>();
		cluster = new ArrayList<Point>();
	}
		
	
	public String toString(){
		ArrayList<Point> point = new ArrayList<Point>();

		for(int i = 0; i < cluster.size(); i++) {
			System.out.println(cluster.get(i) );
		}
		return "Cluster id:"+id+",of size: "+cluster.size();
	}
	
	
public static Object[] cluster(int id, ArrayList<Point>arr){
		
		ClusterPoint c1 = new ClusterPoint(id);
		
		ArrayList<Point> rem_points =  new ArrayList<Point>();
		double d = 15.0;
		
		c1.cluster.add(arr.get(0));
		
		for(int i = 1; i < arr.size(); i++){
			if (Main2Point.distEuclid(arr.get(0), arr.get(i)) < d) {
				c1.cluster.add(arr.get(i));
				
			}
			else rem_points.add(arr.get(i));
		}
		return new Object[]{c1,rem_points};
	}
}
