package multidimscaling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogCluster{

	public static void main (String [] args)throws Exception{
		
	List<Blog> data = new ArrayList<Blog>();
	int n = 4;
	loadBdata("data/datiBlog.txt", data);
	for (int h = 0; h < data.size(); h++) {
		
		System.out.print("-------------"+data.get(h).title);
		for(int g = 0; g < data.get(h).values.length; g++){
		 // System.out.println(data.get(h).toString());
		  
		  System.out.print("***"+data.get(h).values[g]);
	}
	}
	
    int dim = data.get(0).values.length;
	
	double[] min = data.get(0).values.clone();
	double[] max = data.get(0).values.clone();

	
	for (int i = 0; i < data.size(); i++) 
	for (int j = 0; j < dim; j++) {
		min[j] = Math.min(min[j], data.get(i).values[j]);
		max[j] = Math.max(max[j], data.get(i).values[j]);	
	}

	System.out.println("ll "+punto2String(min));
	System.out.println("tr "+punto2String(max));
  
	List <double[]>centroids = new ArrayList<double[]>(); // creo una lista di centroidi
	
	// in base al numero dei centroidi che mi servono creo i cetroidi random tramite il ciclo sottostante
	for(int i = 0; i < n; i++) {
		double[] coord = new double[dim];
		for(int j = 0; j < dim; j++) 
			coord[j] = Math.random() * max[j];
		centroids.add(coord);	
		// dato che i centroidi sono ormai con più di due dimensioni
		// allora mi serve il metodo punto2String() per visualizzarli tutti 
		System.out.println("Centroide creato in ("+punto2String(coord)+")");
    }
    
    Map<Integer, ArrayList <Blog>> map  = null;
    for (int step = 0; step < 3; step++) {
   	 map = assignToCentroid(data, centroids);
   	 centroids = spostaCent(map);
    }
    
    for(int k : map.keySet()) {
		System.out.println("Cluster************ " +k);
		
		for(int j = 0; j < map.get(k).size(); j++) {
			System.out.print(map.get(k).get(j).title+" = ");
			for(int i = 0; i < map.get(k).get(j).values.length; i++){
			System.out.print(" "+map.get(k).get(j).values[i]+", ");
			}
			System.out.println("");
		}		
     }  
   
	}
	
	


	public static String punto2String(double[] x) {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<x.length;i++)
			sb.append(","+x[i]);
		return sb.toString().substring(1);
	}
	
	
public static Map<Integer, ArrayList<Blog>> assignToCentroid(List<Blog> arr, List<double[]>centroids){
		
		double [] dim = new double[arr.get(0).values.length];
		Map<Integer, ArrayList<Blog>>a = new HashMap<Integer, ArrayList<Blog>>();
		for (int i = 0; i < centroids.size(); i++) {
			a.put(i, new ArrayList<Blog>());
		}
		for (int i = 0; i < arr.size(); i++) {
			int c = getClosestCen(arr.get(i).values, centroids);
			a.get(c).add(arr.get(i));
			
			for(int j = 0; j < dim.length; j++){
			System.out.print(arr.get(i).values[j]+",");
					
			}	
			System.out.println(" è stato aggiunto al cluster" +
					"/centroide" );
		}
		return a;
	}

     public static List<double[]> spostaCent(Map<Integer, ArrayList<Blog>>a){
		
		List<double[]>c = new ArrayList<double[]>();
		
	   for (int k : a.keySet()) { //per ogni centroide calcolo il suo nuovo baricentro
		if(a.get(k).size()>0) {
			c.add(getBari(a.get(k)));
			System.out.println("centroide del cluster "+k+" spostato");
		}
		else {
			System.out.println("centriode eliimnato per mancanza di punti");
		}
		
	   }	
		return c; 	
	 }
	
	public static double distEuclid(double[]a, double[]b){
		
		for(int i = 0; i < a.length; i++){
		 System.out.print(" "+a[i]);
		}
		System.out.println();
		for(int i = 0; i < b.length; i++){
		 System.out.print(" "+b[i]);
		}
		System.out.println("\nTestMls.distEuclid()");
		double dist = 0; 
		int dim = a.length;
		double [] delta = new double[dim];
		
		for(int i = 0; i < delta.length; i++){
			delta[i] = b[i]-a[i];
			System.out.println("valore delta "+delta[i]);
		}
	
		double d = 0;
		for(int i = 0; i < delta.length; i++){
			
			d += Math.pow(delta[i], 2);
		}
		dist = Math.sqrt(d);
		return dist;
	}
	
	
	public static double[] getBari(List<Blog>points){
		//System.err.println("------------------- "+points.size());
		int dim = points.get(0).values.length;
		double [] bari = new double[dim]; 
		 
			for(int j = 0; j < bari.length; j++){
				//System.out.println("iterazione "+t);
				double sommassei = 0;
				for(int i = 0; i < points.size(); i++){
			    sommassei +=  points.get(i).values[j];
			}
			System.out.println("sommassei "+sommassei);
			
			bari[j]= sommassei/points.size();
			System.out.println("bari_i"+bari[j]);
			
			
			System.out.println("problema ? ");
		    }
		return  bari;
	}
	
       public static int getClosestCen(double [] a, List<double[]> c){
		
		int closest = 0; // inizializzo l'indice del centroide più vicino a zero
		
		 for (int i = 1; i < c.size(); i++) { // per tutti i centroidi
			// se la distanza del punto a rispetto al primo centroide e maggiore della 
			// distanza del punto a rispetto al secondo centroide allora quest'ultimo diventa 
			// il centroide più vicino o closest
			if (distEuclid(a, c.get(closest)) > distEuclid(a, c.get(i))){
			 closest = i;	
			}	
		 }
		 for(int i = 0; i < a.length; i++){
				System.out.println("il centroide più vicino per il punto di coordinate "+a[i]+" è "+closest);
				}
		return closest;
	   }
       public static void loadBdata(String file, List<Blog>data) throws Exception{
    	   
    	   BufferedReader br = new BufferedReader(new FileReader(new File(file)));
    	  // List<Blog>b = new ArrayList<Blog>();
    	   String blog = "";
    	   
    	   double [] p = null;
    	   String line = "";
    	   while((line = br.readLine())!= null){
    		   int k = 0;
    		System.out.println(line);
    		   String [] s = line.split("  ");
    		   blog = s[0];
    		  // System.out.println(blog);
    		  String[] s1 = s[1].split(" ");
    		   p = new double[s1.length];
    		   
    		   for(String str : s1 ){
    		   String []str1 = str.split(":"); 
    			   p[k] = Double.parseDouble(str1[1]);
    			  //System.out.println(p[k]);
    			   k++;
    		   }
    		   data.add(new Blog(blog, p));
    		  // System.out.println("BlogCluster.loadBdata()");
    	   }  
    	   
       }
}


class Blog {
	public String title;
	public double[] values;
	public Blog(String t, double[] v) {
		this.title = t;
		this.values = v;
	}
	public String toString(){
		
		return title+", "+values;
	}
}
