package alg.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Kmeans {

	public static void main (String[] args) throws Exception{
		int n = 3;
		//String data = "-4.817068715124663,42.04373364036778,0 -5.188634873501846,41.77228385578868,0 -5.527446828798449,41.39769154453843,0 -5.125658685958719,41.03710214783911,0 -4.531054966183042,41.02558547995614,0 -3.937247205382887,41.01107959804275,0 -3.423636876803208,41.2994411664865,0 -3.387850699314499,41.72787042256399,0 -3.494963037783178,42.13564621513375,0 -4.009635568874904,42.37840556605759,0 -4.789430531722172,42.47314504989882,0 -5.337503947616779,42.28086793536046,0 -5.391391964571656,41.95271302939901,0 -4.873308712912243,41.71593023877571,0 -4.244976365569309,41.60076557038481,0 17.72103525777121,65.36193304969252,0 16.69088497262257,65.3765453949072,0 15.64837917279008,65.26861412493911,0 14.84932594924475,64.90004754365556,0 14.79890140306629,64.27320527821505,0 15.50901460598143,64.0149868873404,0 16.51744887136849,64.24781856156163,0 17.07207919163282,64.61431009555813,0 17.43914163162769,65.2396449911361,0 17.56260865568196,65.870839517462,0 16.51104458116913,65.88629056225318,0 15.67801026551381,65.52066439810632,0 15.63379252442191,65.1427452343147,0 36.40901825708348,39.86232429144697,0 35.89323935790167,39.75515150086656,0 36.03862076255167,39.28667799640385,0 36.56162643757573,38.9510791132122,0 37.00140404818852,38.85114351957888,0 37.76786457827879,38.78313059851812,0 38.01750465317829,39.05628403381503,0 38.16078242544225,39.46503546231231,0 38.22481837867438,40.00256353546017,0 37.90955583533145,40.40665448538601,0 37.31849272298688,40.5398634499629,0 36.57847062986468,40.70166612864595,0 35.79860594531877,40.75716946881675,0 35.38824173685323,40.51506801539137,0 35.23774387678426,40.1108492112518,0 35.3803339827541,39.64597140523873,0 35.56614334735701,39.27953789104662,0 36.0580410538953,38.84363981367253,0 36.43241049706459,38.53986636018523,0 37.01576918785034,38.40611742490331,0 37.30731672035043,38.33800278435214,0 ";
		List<double[]> arr = new ArrayList<double[]>();
		//List<double[]> p_arr = new ArrayList<double[]>();
		//loadData(data, p_arr);
		
		arr.add(new double[]{1,1,3,4,5,6});
		arr.add(new double[]{1,2,4,4,5,6});
		arr.add(new double[]{2,1,3,4,5,6});
		
		
		arr.add(new double[]{7,8,3,4,5,6});
		arr.add(new double[]{7,7,3,4,5,6});
		arr.add(new double[]{8,7,2,4,5,6});
		
		arr.add(new double[]{15,15,3,4,5,6});
		arr.add(new double[]{14,14,1,4,5,6});
		arr.add(new double[]{15,14,3,4,5,6});	
		
		int dim = arr.get(0).length;
		
		double[] min = arr.get(0).clone();
		double[] max = arr.get(0).clone();
	
		
		for (int i = 0; i < arr.size(); i++) 
		for (int j=0; j<dim; j++) {
			min[j] = Math.min(min[j], arr.get(i)[j]);
			max[j] = Math.max(max[j], arr.get(i)[j]);	
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
			System.out.println("Centroide creato in ("+punto2String(coord)+")");
        }
        
        Map<Integer, ArrayList <double []>> map  = null;
        
        for (int step = 0; step < 3; step++) {
        	 map = assignToCentroid(arr, centroids);
        	 centroids = spostaCent(map);
        }
         
        for (int k : map.keySet()) {
			System.out.println("Cluster************ " +k);
			
			for (int j = 0; j < map.get(k).size(); j++) {
				
				System.out.println(""+map.get(k).get(j)[0]+", "+map.get(k).get(j)[1]);
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
		
		Map<Integer, ArrayList<double[]>>a = new HashMap<Integer, ArrayList<double[]>>();
		for (int i = 0; i < centroids.size(); i++) {
			a.put(i, new ArrayList<double[]>());
		}
		for (int i = 0; i < arr.size(); i++) {
			int c = getClosestCen(arr.get(i), centroids);
			a.get(c).add(arr.get(i));
			System.out.println(" il punto ("+arr.get(i)[0]+","+arr.get(i)[1]+") è stato aggiunto al cluster" +
					"/centroide" );
		}
		return a;
	}
	
	public static List<double[]> spostaCent(Map<Integer, ArrayList<double[]>>a){
		
		List<double[]>c = new ArrayList<double[]>();
		
	   for (int k : a.keySet()) { //per ogni centroide calcolo il suo nuovo baricentro
		
		 c.add(getBari(a.get(k)));
		 System.out.println("centroide del cluster "+k+" spostato");
		
	   }	
		return c; 	
	}
	public static double[] getBari(List<double[]>points){
		
	double x = 0;
	double y = 0; 
	for (int i = 0; i < points.size(); i++){
		x = (int) x + points.get(i)[0];
		y = (int) y + points.get(i)[1];
	}
	x = x/points.size();
	y = y/ points.size();
	System.out.println("il nuovo baricentro è : "+x+","+y);
	return  new double []{x, y};
	}
	
	public static double disEu( double [] a, double [] b){
		double d = 0;
		
		double b0 = (int)b[0];
		double b1 = (int)b[1];
		
		double a0 = (int)a[0];
		double a1 = (int)a[1];
		
		System.out.println("i punti in considerazione per il calcolo della d.e. sono:"+a0+", "+a1+" e "+b0+","+b1);
		double dx = b0 - a0;
		System.out.println("dx è "+dx);
		double dy = b1 - a1;
		System.out.println("dy è "+dy);
		
		d = Math.sqrt(Math.pow(dx, 2)+ Math.pow(dy, 2));
		System.out.println("--la distanza euclidea è : "+d);
		return d;
	}
	
	public static int getClosestCen(double [] a, List<double[]> c){
		
		int closest = 0; // inizializzo l'indice del centroide più vicino a zero
		
		for (int i = 1; i < c.size(); i++) { // per tutti i centroidi
			// se la distanza del punto a rispetto al primo centroide e maggiore della 
			// distanza del punto a rispetto al secondo centroide allora quest'ultimo diventa 
			// il centroide più vicino o closest
			if (disEu(a, c.get(closest)) > disEu(a, c.get(i))){
			 closest = i;	
			}	
		}
		for(int i = 0; i < a.length; i++){
		System.out.println("il centroide più vicino per il punto "+a[i]+" è "+closest);
		}
		return closest;
	}
     public static void loadData(String line, List<double[]>p_arr){
		
		String []s = line.split(" ");
		double lat = 0;
		double lon = 0;
		String [] st = null;
		
		for(String str : s ){
		
	    st = str.split(",");
		lat = Double.parseDouble(st[0]);
		lon = Double.parseDouble(st[1]);
		p_arr.add(new double[]{lat, lon});
		}
	  }
	
}
