package visualize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main2Vis {

	public static void main (String[]args) throws Exception{
        
		Map<Double, Map<String, List<Double>>>mk = new HashMap<Double, Map<String, List<Double>>>();
	
	    String path2files2 = "C:\\Users\\alketcecaj\\eclipsework_space\\D4DMatching\\test set8_300\\matching time";
		String path2files = "C:\\Users\\alketcecaj\\eclipse_workspace" +
				"\\DataTel\\MatchingTestDataset40gg\\54_utenti-60eventi_Soglia BadUser40";
		String path3 = "G:\\twittermatching\\flickr";
		String path4 = "C:\\Users\\alketcecaj\\eclipse_workspace\\DataTel\\testdatad4dmatching";
		String path2files3 = "G:\\dati matching pls-pls distanza variabile";
		Map<String, Double>m = new HashMap<String, Double>();
		mk = loadNrPoints(path4);
		//System.out.println(mk);
		
		Map<Double, Double>media = new TreeMap<Double, Double>();
		Map<Double, Double>devstd = new TreeMap<Double, Double>();
		Map<Double, Double>mediana = new TreeMap<Double, Double>();
		Map<Double, Double>varianza_mediana = new TreeMap<Double, Double>();
		
		for (double s: mk.keySet()) {
			DescriptiveStatistics stats = new DescriptiveStatistics();
			for (String s1: mk.get(s).keySet()) {
				
					stats.addValue(mk.get(s).get(s1).size());	
			}
			media.put(s, stats.getMean());
		}
		//System.out.println("media "+media.size());
		for (double s: mk.keySet()) {
			DescriptiveStatistics stats = new DescriptiveStatistics();
			for (String s1: mk.get(s).keySet()) {
				
					stats.addValue(mk.get(s).get(s1).size());
				
			}
			devstd.put(s, stats.getStandardDeviation());
			
		}
		//System.out.println("dev std "+devstd.size());
		
		for (double s: mk.keySet()) {
			DescriptiveStatistics stats = new DescriptiveStatistics();
			for (String s1: mk.get(s).keySet()) {
				
					stats.addValue(mk.get(s).get(s1).size());
				
			}
			mediana.put(s, stats.getPercentile(50));
		}
		for (double s: mk.keySet()) {
			DescriptiveStatistics stats = new DescriptiveStatistics();
			for (String s1: mk.get(s).keySet()) {
				
					stats.addValue(mk.get(s).get(s1).size());
				
			}
			varianza_mediana.put(s, stats.getPercentile(75)-stats.getPercentile(25));
		}
		
//		for (double s: media.keySet()) {
//			System.out.println(s+", "+media.get(s));
//		}
//		for (double s: devstd.keySet()) {
//			System.out.println(s+", "+devstd.get(s));
//		}
		vizData(media, "media");
		vizData(devstd, "devstd");
		vizData(mediana, "mediana");
		vizData(varianza_mediana, "interquartile");

	}
	
	
	 public static void vizData( Map<Double, Double>map, String media){
	    	
	           DefaultCategoryDataset ds = new DefaultCategoryDataset();
	           String[] COLORS = {"A", "B", "C", "D", "E"};
	           int i = 0;
	           for(Double d: map.keySet()){
	        	  System.out.println( "**************************"+d );
	        	
		        ds.addValue(map.get(d),COLORS[i] , d);
		       
	           }

//		        JFreeChart bc = ChartFactory.createBarChart("My Bar Chart", "Things",
//	           "Corrispondences",  ds, PlotOrientation.VERTICAL, true, false, false);
		        //JFreeChart bcTop = ChartFactory.createBarChart("My Bar Chart", "Things", "Counts",  ds, PlotOrientation.VERTICAL, true, false, false);

//		        CombinedDomainCategoryPlot combinedPlot = new CombinedDomainCategoryPlot();
//		    
//		        CategoryPlot mainPlot = bc.getCategoryPlot();
//		        combinedPlot.add(mainPlot, 250);
//
//		        NumberAxis mainAxis = (NumberAxis) mainPlot.getRangeAxis();;
//		        mainAxis.setLowerBound(0);
//		        mainAxis.setUpperBound(420);
//
//		        JFreeChart combinedChart = new JFreeChart("PLS-PLS Corrisp "+media , combinedPlot);
//
//		        ChartFrame cf = new ChartFrame("PLS-PLS Corrisp "+media, combinedChart);
//		        cf.setSize(800, 180);
//		        cf.setVisible(true);
	           
		
	    }
	 
	 public static Map<Double, Map<String, List<Double>>> load3(String inflow){
	    	
		 Map<Double, Map<String, List<Double>>> m = new HashMap<Double, Map<String, List<Double>>>();
    	 String filename3 = "";
		 File Folder = new File(inflow);
		 File files[];
		 files = Folder.listFiles();
		
		try{
	      
           if(files.length>1)
           {
            for(int i = 0;i<files.length; i++){
              
            	System.out.print("reading...");
                System.out.println(files[i].getName()+", number: "+i);
                String filename = files[i].getName();
                String cat = filename.substring(0, filename.indexOf("test.txt"));
                System.out.println(cat);
            double cat1 = Double.parseDouble(cat);
              BufferedReader br = new BufferedReader(new FileReader(files[i]));
      		  String line ; 
      		  String user;
      		  Map<String, List<Double>>mi = new HashMap<String, List<Double>>();
      		
      		  while((line = br.readLine())!= null){
      			  if(line.endsWith("1")){
      				  String [] r = line.split(",");
      				  user = r[0];

      				  List<Double>l = new ArrayList<Double>();

      				  for (int j = 1; j < r.length; j++) {

      					  String []k = r[j].split("=");
      					  Double npls = Double.parseDouble(k[1]);
      					  l.add(npls);	
      				  }
      				  mi.put(user, l);
      			  }
      		  }br.close();
              
              m.put(cat1, mi);
             }
          } 
		 }catch( Exception ex){
		    	ex.printStackTrace(); 
	  }
		return m;
    }
	 public static Map<Double, Map<String, List<Double>>> loadNrPoints(String inflow){
	    	
		 Map<Double, Map<String, List<Double>>> m = new HashMap<Double, Map<String, List<Double>>>();
    	 String filename3 = "";
		 File Folder = new File(inflow);
		 File files[];
		 files = Folder.listFiles();
		
		try{
	      
           if(files.length>1)
           {
            for(int i = 0;i<files.length; i++){
              
            	System.out.print("reading...");
                System.out.println(files[i].getName()+", number: "+i);
                String filename = files[i].getName();
                String cat = filename.substring(0, filename.indexOf("."));
                System.out.println(cat);
            double cat1 = Double.parseDouble(cat);
              BufferedReader br = new BufferedReader(new FileReader(files[i]));
      		  String line ; 
      		  String user;
      		  Map<String, List<Double>>mi = new HashMap<String, List<Double>>();
      		
      		  while((line = br.readLine())!= null){
      			  if(line.endsWith("1")){
      				  String [] r = line.split(",");
      				  user = r[0];
                      System.out.print(user+"  ");
      				  List<Double>l = new ArrayList<Double>();

      				  for (int j = 1; j < r.length; j++) {

      					  String []k = r[j].split("=");
      					  System.out.print(k[0]+"="+k[1]+", ");
      					 
      	
      					  Double npls = Double.parseDouble(k[0]);
      					  l.add(npls);	
      				  }
      				  System.out.println();
      				  mi.put(user, l);
      			  }
      		  }br.close();
              
              m.put(cat1, mi);
             }
          } 
		 }catch( Exception ex){
		    	ex.printStackTrace(); 
	  }
		return m;
    }
	 
	 public static Map<Double, Map<String, List<Double>>> load4(String inflow){
	    	
		 Map<Double, Map<String, List<Double>>> m = new HashMap<Double, Map<String, List<Double>>>();
    	 String filename3 = "";
		 File Folder = new File(inflow);
		 File files[];
		 files = Folder.listFiles();
		
		try{
	      
           if(files.length>1)
           {
            for(int i = 0;i<files.length; i++){
              
            	System.out.print("reading...");
                System.out.println(files[i].getName()+", number: "+i);
                String filename = files[i].getName();
                String cat = filename.substring(0, filename.indexOf("_min.txt"));
                System.out.println(cat);
            double cat1 = Double.parseDouble(cat);
              BufferedReader br = new BufferedReader(new FileReader(files[i]));
      		  String line ; 
      		  String user;
      		  Map<String, List<Double>>mi = new HashMap<String, List<Double>>();
      		
      		  while((line = br.readLine())!= null){
      			
      			String [] r = line.split(",");
      		    user = r[0];
      		   
      		    List<Double>l = new ArrayList<Double>();
      		    
      		    for (int j = 1; j < r.length; j++) {
					
      		    	String []k = r[j].split("=");
                    	Double npls = Double.parseDouble(k[1]);
    					l.add(npls);	
      		    }
      		    mi.put(user, l);
      		    
      		  }br.close();
              
              m.put(cat1, mi);
             }
            
          }
//           DefaultCategoryDataset ds = new DefaultCategoryDataset();
//           String[] COLORS = {"A", "B", "C", "D", "E", "F", "G", "H", "K", "L", "M", "N", "O", "P", "Q"};
//           int i = 0;
//           for(double d: map.keySet()){
//        	  System.out.println( "**************************"+d );
//	        ds.addValue(d,COLORS[i] , map.get(d));
//	        i++;
//	        if(i == 14){
//	        	i = 0;
//	        }
//           }
//
//	        JFreeChart bc = ChartFactory.createBarChart("My Bar Chart", "Things",
//           "Corrispondences",  ds, PlotOrientation.VERTICAL, true, false, false);
//	        //JFreeChart bcTop = ChartFactory.createBarChart("My Bar Chart", "Things", "Counts",  ds, PlotOrientation.VERTICAL, true, false, false);
//
//	        CombinedDomainCategoryPlot combinedPlot = new CombinedDomainCategoryPlot();
//	    
//	        CategoryPlot mainPlot = bc.getCategoryPlot();
//	        combinedPlot.add(mainPlot, 5);
//
//	        NumberAxis mainAxis = (NumberAxis) mainPlot.getRangeAxis();;
//	        mainAxis.setLowerBound(0);
//	        mainAxis.setUpperBound(4750);
//
//	        JFreeChart combinedChart = new JFreeChart("FlickrUsers-PLS Corrisp "+filename3 , combinedPlot);
//
//	        ChartFrame cf = new ChartFrame("FlickrUsers-PLS Corrisp "+filename3, combinedChart);
//	        cf.setSize(1340, 500);
//	        cf.setVisible(true);
           
		 }catch( Exception ex){
		    	ex.printStackTrace(); 
	  }
		return m;
    }

	 
	 public static  Map<String, Map<String, Map<Integer, Integer>>>load2(String inflow){
	    	
		 Map<String, Map<String,Map<Integer, Integer>>>bigm = new HashMap<String, Map<String, Map<Integer, Integer>>>();
    	 String filename3 = "";
		 File Folder = new File(inflow);
		 File files[];
		 files = Folder.listFiles();
		
		try{
	      
           if(files.length>1)
           {
            for(int i = 0;i<files.length; i++){
              
              System.out.print("reading...");
              System.out.println(files[i].getName()+", number: "+i);
              String filename = files[i].getName();
              String cat = filename.substring(filename.indexOf("_")+1, filename.indexOf(".txt"));
              
              Map<String, Map<Integer, Integer>>mj = bigm.get(cat);
              if(mj== null){
            	  mj = new HashMap<String, Map<Integer, Integer>>();
            	  bigm.put(cat,mj);
              }
              
              BufferedReader br = new BufferedReader(new FileReader(files[i]));
      		  String line ; 
      		  String user;
      		  while((line = br.readLine())!= null){
      			
      			String [] r = line.split(",");
      		    user = r[0];
      		    
      		    Map<Integer, Integer>mi = mj.get(user);
      		    if(mi== null){
      		    	mi = new TreeMap<Integer, Integer>();
      		    	mj.put(user, mi);
      		    }
      		    
      		    String [] val = r[1].split(",");
      		    
      		    for (int j = 0; j < val.length; j++) {
					String []k = val[j].split("=");
					int pc = Integer.parseInt(k[0]);
					int npls = Integer.parseInt(k[1]);
					mi.put(pc, npls);
				}
      		   //mj.put(user, mi);
      		  }br.close();
              
      		  
              
             }
            
          }

		 }catch( Exception ex){
		    	ex.printStackTrace(); 
	  }
		return bigm;
    }
}
