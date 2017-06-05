package alg.clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


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
public static void loadDataFF(String file, List<Placemark> p_arr) throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		
		String line = null;
		
		while((line = br.readLine())!= null){
		
			String[] s = line.split(" ");
			double lat = 0;
			double lon = 0;
			String [] str = null;
			
			  for(String st: s){
				
			   str = st.split(",");	
			   lat = Double.parseDouble(str[0]);	
			   lon = Double.parseDouble(str[1]);	
			   p_arr.add(new Placemark(lat, lon));
			  }
		}br.close();
	}
	
}
