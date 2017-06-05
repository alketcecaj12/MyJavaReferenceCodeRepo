package mds.collaborativefiltering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Recensione {

	public static void main (String [] args)throws Exception{
		
		Map<String, List<Recensione>> map2 = new HashMap<String, List<Recensione>>();
		//LoadData.loadFile("data/datamovielens.txt", map2);
        LoadData.loadRData("data/recensioni.txt", map2);
		for (String s: map2.keySet()) {
			System.out.print("NOME "+s);
			System.out.println(" REC "+map2.get(s));
		}
		
        List<double[]>matrix = new ArrayList<double[]>();
        matrix = Recensione.coeffpearsUvT(map2);
         for (int j = 0; j < matrix.size(); j++){ 
           for (int i = 0; i < matrix.size(); i++) {
			 System.out.print(","+matrix.get(i)[j]);
		   }
          System.out.println("");
         }
         
         double[][] points = new double[4][2];
         System.out.println("punti A,B,C,D random ");
         TestMds.getRandPts( points);
        
         for(int i = 0; i < points.length; i ++){
        	 for (int j = 0; j < points[0].length; j++) {
				System.out.print(points[i][j]+", ");
			}
        	 System.out.println();
         }
        	 System.out.println("");
        	 for(int t=0; t<1000000;t++)
	             for(int i=0; i<points.length;i++) {
	                    points[i] = TestMds.shiftpoints(i,points,matrix);
	                    //System.out.println("----------------"+i);
	             }
        	 System.out.println("Punti A,B,C,D dopo lo scaling");
        	 for(int i = 0; i < points.length; i ++){
            	 for (int j = 0; j < points[0].length; j++) {
    				System.out.print(points[i][j]+" ");
    			}
            	 System.out.println();
             }
	}
}
