package alg.testing;
import java.util.*;

public class Placemark {

	double lat;
	double lon;
	
	public Placemark(double la, double lo){
		
		this.lat = la;
		this.lon = lo;
		
	}
	
	public String toString(){
		
		return"("+lat+","+lon+")";
	}
	public static void loadData(String line, List<Placemark>p_arr){
		
		String []s = line.split(" ");
		double lat = 0;
		double lon = 0;
		String [] st = null;
		
		for(String str : s ){
		
	    st = str.split(",");
		lat = Double.parseDouble(st[0]);
		lon = Double.parseDouble(st[1]);
		p_arr.add(new Placemark(lat, lon));
		}
		
	}
	
}
