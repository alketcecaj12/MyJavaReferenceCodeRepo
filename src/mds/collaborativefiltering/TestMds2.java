package mds.collaborativefiltering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class TestMds2 {

	public static String [] cities = {"Torino", "Milano", "Venezia", "Roma", "Bari", "Palermo", "Napoli"};

	   public static double delta = 0.01;
	   public static void main(String[] args) throws Exception {


		 // for(int cts = 0; cts < cities.length; cts++){


			   String path = "C:/Users/alketcecaj/workspace_new/DataChallengeCPT/KmeansData/"
					   + "AtecoVectors/Venezia_SectionsAtecoVectors.csv";
			   //	             double[][] items = new double[][]{
			   //	                           { 1, 2, 3 ,4},
			   //	                           { 2, 3, 4, 5},
			   //	                           {14,15,16,17},
			   //	                           {13,14,17,16}
			   //	             };
			   int rows = 0; 
			   int col = 0; 
			   int [] rowcol = getMatrixNrOfColRows( path);
			   rows = rowcol[0];
			   col = rowcol[1];
			   double[][] items = loadDataFromMatrix(rows, col, path);
//			   for(int i=0; i<items.length;i++)
//				   for(int j=0; j<items.length;j++) {
//					   double d = d(items[i],items[j]);
//					   // System.out.println("dist item_"+i+", item_"+j+" = "+d);
//				   }

			   System.out.println("Finish calculating distance");
//			   double[][] points = new double[][]{
//				   {10*Math.random(),10*Math.random()},
//				   {10*Math.random(),10*Math.random()},
//				   {10*Math.random(),10*Math.random()},
//				   {10*Math.random(),10*Math.random()}
//			   };
			   double[][] points = new double[rows][col];
			   for(int k = 0; k < rows; k++){
				   for(int h = 0; h < 2; h++){
					   points[k][h] = 10*Math.random();
				   }
			   }
			   System.out.println(" i numeri random sono ");
			   // print(points);

               System.out.print("start shifting for t = ");
			   for(int t=0; t<10;t++){
				   System.out.println("start shifting for t = "+t);
				   for(int i=0; i<points.length;i++) {
					   points[i] = shift(i,points,items);
					    System.out.println(t+"----------------"+i);
				   }
				   
			   }
			   System.out.println("Finish shifting points");
			   // print(points);
			   System.out.println("calcolo la distanza dopo il shift ");
//			   for(int i=0; i<points.length;i++)
//				   for(int j=0; j<points.length;j++) {
//					   double d = d(points[i],points[j]);
//					 //  System.out.println("dist point_"+i+", point_"+j+" = "+d);
//				   }
			   System.out.println("---------------------------------------------");
			   // print(points);
			   printFile(points, "ClustersOf_Venezia_InMDS.csv"); 
		   
	   }
	       public static double dist(double[] x, double[] y) {
	             double s = 0;
	             for(int i=0; i<x.length;i++)
	                    s+= Math.pow(x[i] - y[i],2);
	             return Math.sqrt(s);
	       }
	       
	    
	       public static double[] shiftpoints(int i, double[][] points, List<double[]> items) {
	             double[] p = points[i];
	            
	             for(int j=0; j<points.length;j++) {
	            	 double[] d = items.get(j);
	                    double[] d1 = items.get(i);
	                    
	                    double dist2d = d(points[i],points[j]);
	                    //System.out.println("distanza in 2d = "+dist2d);
	                    
	                    double dist4d = dist(d,d1);
	                    //System.out.println("distanza in 4d = "+dist4d);
	                    double v = 0;
	                    if(dist2d > dist4d) v = 1;
	                    if(dist2d < dist4d) v = -1;
	                   
	                    p[0] = points[i][0] + (points[j][0] - points[i][0])*delta*v;
	                    p[1] = points[i][1] + (points[j][1] - points[i][1])*delta*v;
	                   // System.out.println("p0 = "+p[0]);
	                   // System.out.println("p1 = "+p[1]);
	             }
	             return p;
	       }
	      
	      
	       public static void print(double[][] v) {
	             for(int i=0;i<v.length;i++) {
	                    for(int j=0; j<v[i].length;j++)
	                           System.out.print(v[i][j]+", ");
	                    System.out.println();
	             }
	             System.out.println();
	       }
	       public static void printFile(double[][] v, String file) throws Exception{
	    	   PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
	    	   for(int i=0;i<v.length;i++) {
	    		   for(int j=0; j<v[i].length;j++){
	    			   out.print(v[i][j]+", ");

	    		   }
	    		   out.println();
	    	   }
	    	   out.close();
	       }
	       public static double d(double[] x, double[] y) {
	             double s = 0;
	             for(int i=0; i<x.length;i++)
	                    s+= Math.pow(x[i] - y[i],2);
	             return Math.sqrt(s);
	       }
	       
	       public static void getRandPts( double[][] points){
		    
		    for(int j = 0; j < points[0].length; j++){
		       for(int i = 0; i < points.length; i++){
			      double elem = 10*Math.random();
			      points[i][j] = elem;
			      
		       }
	         }
	       }
	       public static double[] shift(int i, double[][] points, double[][] items) {
	             double[] p = points[i];
	            
	             for(int j=0; j<points.length;j++) {
	                    double dist2d = d(points[i],points[j]);
	                    //System.out.println("distanza in 2d = "+dist2d);
	                    double dist4d = d(items[i],items[j]);
	                    //System.out.println("distanza in 4d = "+dist4d);
	                    double v = 0;
	                    if(dist2d > dist4d) v = 1;
	                    if(dist2d < dist4d) v = -1;
	                   
	                    p[0] = points[i][0] + (points[j][0] - points[i][0])*delta*v;
	                    p[1] = points[i][1] + (points[j][1] - points[i][1])*delta*v;
	                    //System.out.println("p0 = "+p[0]);
	                    //System.out.println("p1 = "+p[1]);
	             }
	             return p;
	       }
	    
	       public static double [][] loadDataFromMatrix(int rows, int cols, String file) throws Exception{
	    	  
	    	   double [][] m = new double[rows][cols];
	    	   
	    	   String line;
	    	   BufferedReader br = new BufferedReader(new FileReader(new File(file)));
	    	   int j = 0;
	    	   while((line = br.readLine())!= null){
	    		   String [] r = line.split("	");
	    		   String sec = r[0];
	    		   String []r2 = r[1].split(",");
	    		   System.out.println("length r2  -- --> "+r2.length);
	    		   for(int i = 0; i < r2.length; i++){
	    			  // System.out.println(r2[i]);
	    			   m[j][i] = round(Double.parseDouble(r2[i]), 2);
	    		   }
	    		   j++;
	    		   
	    	   }br.close();
	    	   return m;
	       }
	       public static int [] getMatrixNrOfColRows( String file) throws Exception{
		    	  
	    	 int [] ret = new int[2];
	    	   
	    	   String line;
	    	   BufferedReader br = new BufferedReader(new FileReader(new File(file)));
	    	   int rows = 0;
	    	   int col = 0;
	    	   while((line = br.readLine())!= null){
	    		   String [] r = line.split("	");
	    		   String sec = r[0];
	    		   String [] r2 = r[1].split(",");
	    		    col = r2.length;
	    		   rows++;
	    		  
	    		   
	    	   }br.close();
	    	   
	    	   ret[0] = rows;
	    	   ret[1]= col;
	    	   return ret;
	       }
	       public static double round(double value, int places) {
	    	    if (places < 0) throw new IllegalArgumentException();

	    	    long factor = (long) Math.pow(10, places);
	    	    value = value * factor;
	    	    long tmp = Math.round(value);
	    	    return (double) tmp / factor;
	    	}
}
