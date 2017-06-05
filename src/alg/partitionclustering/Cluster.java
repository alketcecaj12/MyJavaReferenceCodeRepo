package alg.partitionclustering;

import java.util.ArrayList;
import java.util.List;

import alg.testing.Placemark;


public class Cluster {

	
	int id = 0; 
	List<double[]> cluster = null;
	
	public Cluster(int i ){
		
		cluster = new ArrayList<double[]>();
		this.id = i;
	}

	public String toString(){

		for(int i = 0; i < cluster.size(); i++) {
			System.out.println(cluster.get(i)[0]+", "+cluster.get(i)[1] );
		}
		return "Cluster id:"+id+",of size: "+cluster.size();
	}
	
	
	public static double distEuclid( double []a, double [] b){
		double metric_de = 0;
		double dx = 0;
	    double dy = 0;
	//System.out.println(""+a.x+","+a.y+","+b.x+","+b.y);
			
	        dx = b[0] - a[0];
			//System.out.println(""+dx);
			dy = b[1] - a[1];
			//System.out.println(","+dy);
		 metric_de = Math.sqrt(((dx*dx)+(dy*dy)));	
		
		return metric_de;
	}
	
     public static Object[] part_cluster(int id, ArrayList<double[]>arr){
		
		Cluster c1 = new Cluster(id);
		
		ArrayList<double[]> rem_points =  new ArrayList<double[]>();
		double d = 2.2;
		
		c1.cluster.add(arr.get(0));
		
		for(int i = 1; i < arr.size(); i++){
			if (distEuclid(arr.get(0), arr.get(i)) < d) {
				c1.cluster.add(arr.get(i));
				
			}
			else rem_points.add(arr.get(i));
		}
		return new Object[]{c1,rem_points};
	}
	
     
     public static void loadData(String data, ArrayList<double[]> p_arr){
 		
 		String[] s = data.split(" ");
 		double lat = 0;
 		double lon = 0;
 		String [] str = null;
 		
 		  for(String st: s){
 			
 		   str = st.split(",");	
 		   lat = Double.parseDouble(str[0]);	
 		   lon = Double.parseDouble(str[1]);	
 		   p_arr.add(new double []{lat, lon});
 		  }
     }
}
