package project.utilityclasses;

import java.util.ArrayList;
import java.util.List;

import org.gps.utils.LatLonPoint;
import org.gps.utils.LatLonUtils;





public class CalculateDistance{ 
	
	public static List<double[]> generateGrid(double lat1, double lon1){
		
		List<double[]> grid = new ArrayList<double[]>();
		
		double c1 = 0.0062;
		double c2 = 0.005;
		double d1 = 0;
		double d2 = 0;
		double temp = lat1;
		
		grid.add(new double[]{lat1, lon1});
	
		for (int i = 0; i < 100; i++) {
			lat1 = temp;
			
			for (int j = 0; j < 100; j++) {
				
				 lat1 =  lat1+c1;
				 
				grid.add(new double[]{lat1, lon1});
			
			}
			lon1 =lon1 +0.0037;
		}
		
		return grid;
		
	}
	
	
	
	
   public static void main (String [] args) throws Exception{
	
	double lat1 = 45.068893;
	double lat2 = 45.03078400;
	
	double lon1 = 7.694667;
	double lon2 = 	7.65651400;
	
	LatLonPoint gp1 = new LatLonPoint(lat1, lon1);
	
	LatLonPoint gp2 = new LatLonPoint(lat2, lon2);
	
    double d = LatLonUtils.getHaversineDistance(gp1, gp2);	
	System.out.println( d);

           }
     public static double distanceCalculator(LatLonPoint gp1,LatLonPoint gp2 ){
    	 double d = LatLonUtils.getHaversineDistance(gp1, gp2);	
    		//System.out.println( d);
    		return d;
     }
	} 

