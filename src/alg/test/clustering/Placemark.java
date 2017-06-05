package alg.test.clustering;

import java.util.ArrayList;

public class Placemark {

	double lon ;
	double lat ;
	
	public Placemark(double la, double lo){
		this.lon = lo;
		this.lat = la;
		
	}
	
	public String toString(){
		return lat+","+lon;
	}
public static void loadData(String data, ArrayList<Placemark> p_arr){
		
		String[] s = data.split(" ");
		double lat = 0;
		double lon = 0;
		String [] str = null;
		
		  for(String st: s){
			
		   str = st.split(",");	
		   lat = Double.parseDouble(str[0]);	
		   lon = Double.parseDouble(str[1]);	
		   p_arr.add(new Placemark(lat, lon));
		  }
		
	}
	public static double distEuclid(Placemark a, Placemark b){
		double metric_de = 0;
		double dx = 0;
	    double dy = 0;
	//System.out.println(""+a.x+","+a.y+","+b.x+","+b.y);
			
	        dx = b.lat - a.lat;
			//System.out.println(""+dx);
			dy = b.lon - a.lon;
			//System.out.println(","+dy);
		 metric_de = Math.sqrt(((dx*dx)+(dy*dy)));	
		
		return metric_de;
	}
	public static Object[] cluster(int id, ArrayList<Placemark>arr){
		
		Cluster c1 = new Cluster(id);
		
		ArrayList<Placemark> rem_points =  new ArrayList<Placemark>();
		double d = 15.0;
		
		c1.cluster.add(arr.get(0));
		
		for(int i = 1; i < arr.size(); i++){
			if (distEuclid(arr.get(0), arr.get(i)) < d) {
				c1.cluster.add(arr.get(i));
				
			}
			else rem_points.add(arr.get(i));
		}
		return new Object[]{c1,rem_points};
	}
	
}
