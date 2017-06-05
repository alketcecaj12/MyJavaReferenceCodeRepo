package kmlpoints.test.clustering;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main2Cluster {

	public static void main (String [] args) throws Exception {
		
	  ArrayList<Point> arr = new ArrayList<Point>();
	  Cluster c = null;
	  HashMap<Integer, ArrayList<Point>>clustermap = new HashMap<Integer, ArrayList<Point>>();
	  Map<Integer, Cluster> map = new HashMap<Integer,Cluster>();
	  //Map<Integer, ArrayList<Cluster>> map1 = new HashMap<Integer,ArrayList<Cluster>>();
	  
	  BufferedReader br = new BufferedReader(new FileReader(new File("data/datiAlg.txt")));
	 
	  String line = "";
	  
	    while((line = br.readLine())!=null){
		  
    	 loadData(arr, line);
	    
	    }br.close();
           
	    // mi assicuro che i dati vengono caricati 
	       for (int i = 0; i < arr.size(); i++) {
			System.out.println(""+arr.get(i));
	       }
	        System.out.println(arr.size());
	      
	    //provo l'algoritmo di clustering: metodo cluster
	       for (int i = 0; i < 3; i++) {
	    	System.out.println("--------------------------------"+arr.size());
	    	if(arr.size() == 0)
	    		break;
	    	Object[] c_rem = cluster(i,arr);
			map.put(i, (Cluster)c_rem[0]);
			arr = (ArrayList<Point>)c_rem[1];
	       }
	    
	       for(int id : map.keySet()) {
	    	System.out.println("********* "+id+" = "+map.get(id).cluster.size());
	    	System.out.println(map.get(id));
	       }
	       // provo l'alg di clustering clusteringSet();
		System.out.println(""+clusteringSet(2,arr));
	}
	
	public static void loadData(ArrayList<Point> arr, String line){
		double x = 0;
		double y = 0;
		String[] s = line.split(",");
		x = Double.parseDouble(s[0]);
		y = Double.parseDouble(s[1]);
		
		arr.add(new Point(x, y));
		
	}
	
	public static double distEuclid(Point a, Point b){
		double metric_de = 0;
		double dx = 0;
	    double dy = 0;
	//System.out.println(""+a.x+","+a.y+","+b.x+","+b.y);
			
	        dx = b.x - a.x;
			//System.out.println(""+dx);
			dy = b.y - a.y;
			//System.out.println(","+dy);
		 metric_de = Math.sqrt(((dx*dx)+(dy*dy)));	
		
		return metric_de;
	}
	
	
	public static Object[] cluster(int id, ArrayList<Point>arr){
		Cluster c1 = new Cluster(id);
		
		ArrayList<Point> rem_points =  new ArrayList<Point>();
		double d = 8.0;
		
		//c1.cluster.add(arr.get(0));
		
		for(int i = 1; i < arr.size(); i++){
			if (distEuclid(arr.get(0), arr.get(i)) < d) {
			//	c1.cluster.add(arr.get(i));
			}
			else rem_points.add(arr.get(i));
		}
		return new Object[]{c1,rem_points};
	}
	
	public static ArrayList<Cluster> clusteringSet(int k, ArrayList<Point> arr){
		
		ArrayList <Cluster>clusterset = new ArrayList<Cluster>();
		
        double d = 8.0;
		
		for(int j = 0; j <=k; j++){
			
			Cluster c = new Cluster(k);
			
		  for(int i = 0; i < arr.size(); i++){
			
			  if(distEuclid(arr.get(0), arr.get(i)) < d){
			 //c.cluster.add(arr.get(i));
			  arr.remove(i);
			}
		  }	
		clusterset.add(c);
		}
		
		return clusterset;	
	}

	
	
}
