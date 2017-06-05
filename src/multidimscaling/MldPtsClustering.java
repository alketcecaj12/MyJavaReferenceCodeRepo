package multidimscaling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MldPtsClustering{

	public static void main (String [] args)throws Exception{
		
	List<double[]> data = new ArrayList<double[]>();
	int n = 2;
	
	LoadData.loadData(data, "data/mldclusterdata.txt");
	
	/*data.add(new double[]{1,2,1,0,1});
	data.add(new double[]{1,0,1,1,1});
	data.add(new double[]{1,1,1,1,1});
	data.add(new double[]{1,1,0,0,1});
	
	data.add(new double[]{4,5,5,6,7});
    data.add(new double[]{5,5,5,6,8});
    data.add(new double[]{6,3,5,6,7});
	
    data.add(new double[]{14,15,15,16,17});
    data.add(new double[]{15,15,15,16,15});
    data.add(new double[]{16,13,15,16,18});
    
    data.add(new double[]{30,31,40,50,52});
    data.add(new double[]{35,32,42,47,55});
    data.add(new double[]{30,38,45,46,58});
    */
    
    int dim = data.get(0).length;
	
	double[] min = data.get(0).clone();
	double[] max = data.get(0).clone();

	
	for (int i = 0; i < data.size(); i++) 
	for (int j = 0; j < dim; j++) {
		min[j] = Math.min(min[j], data.get(i)[j]);
		max[j] = Math.max(max[j], data.get(i)[j]);	
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
    
    Map<Integer, ArrayList <double []>> map  = null;
    for (int step = 0; step < 3; step++) {
   	 map = assignToCentroid(data, centroids);
   	 centroids = spostaCent(map);
   }
    
   for (int k : map.keySet()) {
		System.out.println("Cluster************ " +k);
		
		for (int j = 0; j < map.get(k).size(); j++) {
			for(int i = 0; i < map.get(k).get(j).length; i++){
			System.out.print(" "+map.get(k).get(j)[i]+", ");
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
	
	
public static Map<Integer, ArrayList<double[]>> assignToCentroid(List<double[]> arr, List<double[]>centroids){
		
		double [] dim = new double[arr.get(0).length];
		Map<Integer, ArrayList<double[]>>a = new HashMap<Integer, ArrayList<double[]>>();
		for (int i = 0; i < centroids.size(); i++) {
			a.put(i, new ArrayList<double[]>());
		}
		for (int i = 0; i < arr.size(); i++) {
			int c = getClosestCen(arr.get(i), centroids);
			a.get(c).add(arr.get(i));
			
			for(int j = 0; j < dim.length; j++){
			System.out.print(arr.get(i)[j]+",");
					
			}	
			System.out.println(" è stato aggiunto al cluster" +
					"/centroide" );
		}
		return a;
	}

     public static List<double[]> spostaCent(Map<Integer, ArrayList<double[]>>a){
		
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
	
	
	public static double[] getBari(List<double[]>points){
		//System.err.println("------------------- "+points.size());
		int dim = points.get(0).length;
		double [] bari = new double[dim]; 
		 
			for(int j = 0; j < bari.length; j++){
				//System.out.println("iterazione "+t);
				double sommassei = 0;
				for(int i = 0; i < points.size(); i++){
			    sommassei +=  points.get(i)[j];
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
}
