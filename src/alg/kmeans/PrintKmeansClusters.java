package alg.kmeans;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

//import alg.clustering.Cluster;
//import alg.clustering.Placemark;

public class PrintKmeansClusters {

static String[] COLORS = {"ff0000ff", "ff00ff00", "ffff0000"};	
	
	public static void print(Map<Integer, ArrayList <double []>> map,String data)throws Exception{
		
		    ArrayList<double[]> p_arr = new ArrayList<double[]>();	
		
	    	PrintWriter out = new PrintWriter(new FileWriter(data));	
		
		    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.println("<kml>");
			out.println("<Document>");
		 for(int i = 0; i < COLORS.length; i++){
			out.println("<Style id=\""+COLORS[i]+"\">");
			out.println("<IconStyle>");
			out.println("<color>"+COLORS[i]+"</color>");
			out.println("<scale>1.2</scale>");
			out.println("<Icon>");
			out.println("<href>http://maps.google.com/mapfiles/kml/shapes/shaded_dot.png</href>");
			out.println("</Icon>");
			out.println("</IconStyle>");
			out.println("<ListStyle>");
			out.println("</ListStyle>");
			out.println("</Style>");
		}	
			int k = 0;
		  for(ArrayList<double[]> p: map.values()){
	    	  k++;
				if (k == 3){
					k=0;     // cosi non mi da ArrayIndexOutOfBounds
				}
			  for (int i = 0; i < p.size(); i++) {
					out.println("<Placemark>");
					out.println("<styleUrl>#"+COLORS[k]+"</styleUrl>");
					out.println("<Point>");
			        out.print(" <coordinates>"+p.get(i)[0]+","+p.get(i)[1]+",0 </coordinates>");
			        out.println("</Point>");
					out.println("</Placemark>");
				}
		  }
		         out.println("</Document>");
			     out.println("</kml>");
			     out.close();
    }	
}
