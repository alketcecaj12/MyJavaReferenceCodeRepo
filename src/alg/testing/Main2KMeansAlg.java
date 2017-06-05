package alg.testing;
import java.math.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main2KMeansAlg {

	public static void main (String [] args) throws Exception{
		String data = "-4.817068715124663,42.04373364036778,0 -5.188634873501846,41.77228385578868,0 -5.527446828798449,41.39769154453843,0 -5.125658685958719,41.03710214783911,0 -4.531054966183042,41.02558547995614,0 -3.937247205382887,41.01107959804275,0 -3.423636876803208,41.2994411664865,0 -3.387850699314499,41.72787042256399,0 -3.494963037783178,42.13564621513375,0 -4.009635568874904,42.37840556605759,0 -4.789430531722172,42.47314504989882,0 -5.337503947616779,42.28086793536046,0 -5.391391964571656,41.95271302939901,0 -4.873308712912243,41.71593023877571,0 -4.244976365569309,41.60076557038481,0 17.72103525777121,65.36193304969252,0 16.69088497262257,65.3765453949072,0 15.64837917279008,65.26861412493911,0 14.84932594924475,64.90004754365556,0 14.79890140306629,64.27320527821505,0 15.50901460598143,64.0149868873404,0 16.51744887136849,64.24781856156163,0 17.07207919163282,64.61431009555813,0 17.43914163162769,65.2396449911361,0 17.56260865568196,65.870839517462,0 16.51104458116913,65.88629056225318,0 15.67801026551381,65.52066439810632,0 15.63379252442191,65.1427452343147,0 36.40901825708348,39.86232429144697,0 35.89323935790167,39.75515150086656,0 36.03862076255167,39.28667799640385,0 36.56162643757573,38.9510791132122,0 37.00140404818852,38.85114351957888,0 37.76786457827879,38.78313059851812,0 38.01750465317829,39.05628403381503,0 38.16078242544225,39.46503546231231,0 38.22481837867438,40.00256353546017,0 37.90955583533145,40.40665448538601,0 37.31849272298688,40.5398634499629,0 36.57847062986468,40.70166612864595,0 35.79860594531877,40.75716946881675,0 35.38824173685323,40.51506801539137,0 35.23774387678426,40.1108492112518,0 35.3803339827541,39.64597140523873,0 35.56614334735701,39.27953789104662,0 36.0580410538953,38.84363981367253,0 36.43241049706459,38.53986636018523,0 37.01576918785034,38.40611742490331,0 37.30731672035043,38.33800278435214,0 ";
		List<double[]>p_arr = new ArrayList<double[]>();
		
		int n = 3;

		double minx = 0; // queste valori mi servono per delimitare l'area  dove ci sono i miei punti-
		double miny = 0; 
		double maxx = 0; // infatti minx mi da il punto pi� in basso a/pi� sinistra e maxy il punto piu in alto a destra o pi� a destra
		double maxy = 0; // e inoltre mi servono per trovare il minimo/massimo tramite il metodo sottostante 
		
		loadData(data, p_arr);	
		//setMinMaxXY(p_arr, minx, miny, maxx, maxy); 
		 minx = p_arr.get(0)[0]; // queste valori mi servono per delimitare l'area  dove ci sono i miei punti-
		 miny = p_arr.get(0)[1]; 
         maxx = p_arr.get(0)[0];// infatti minx mi da il punto pi� in basso a/pi� sinistra e maxy il punto piu in alto a destra o pi� a destra
		 maxy = p_arr.get(0)[1];// 

		for (int i = 0; i < p_arr.size(); i++) {
			minx = Math.min(minx, p_arr.get(i)[0]);
			miny = Math.min(miny, p_arr.get(i)[1]);
			maxx = Math.max(maxx, p_arr.get(i)[0]);
			maxy = Math.max(maxy, p_arr.get(i)[1]);
		}
		
		System.out.println("**************************************************ll "+minx+", "+miny);
		System.out.println("**************************************************tr "+maxx+", "+maxy);
		List <double[]>c = new ArrayList<double[]>(); // creo una lista di centroidi
        for (int i = 0; i < n; i++) {
        	double r1 = 10;
        	double r2 = 10;
        	r1 = (int)r1*Math.random();
        	r2 = (int)r2*Math.random();
    	c.add(new double[]{ r1*maxx, r2*maxy});	
        }
        Map<Integer, ArrayList <double []>> map  = null;
        
        for (int step = 0; step < 100; step++) {
        	 map = Kmeans.assignToCentroid(p_arr, c);
        	 c = Kmeans.spostaCent(map);
        }
        
        for (int k : map.keySet()) {
			System.out.println("Cluster******************************************** " +k);
			
			for (int j = 0; j < map.get(k).size(); j++) {
				
				System.out.println(""+map.get(k).get(j)[0]+", "+map.get(k).get(j)[1]);
			}
		}
	//	PrintKmeansClusters.print(map, "printK-meansResult.kml");
		
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
