package visualize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Main2RDataLineDiagram {
	
	public static void main(String [] args) throws Exception {
		int ver = 0;
		Map<Long, Map<Double,Double>>map = new TreeMap<Long, Map<Double, Double>>();
		String outfile ="C:\\Users\\alketcecaj\\eclipse_workspace\\DataTel\\data4R";
		//File folder = new File("G:\\dati matching pls-pls distanza variabile");
		File folder = new File("data4R\\t7");
		File[] files = folder.listFiles();
		
		for (int i = 0; i < files.length; i++) {
            String nomefile = files[i].getName().substring(0,files[i].getName().indexOf(","));
            System.out.println(nomefile);
            long namefile = Long.parseLong(nomefile); 
            namefile = namefile/1000/60;
			String line;
			BufferedReader br = new BufferedReader(new FileReader(files[i]));
			DescriptiveStatistics stats = new DescriptiveStatistics();
			double count_tot = 0;
			double count_id = 0;
			
			Map<Double, Double>map_i = new HashMap<Double, Double>();
			
			
			while((line = br.readLine())!= null){
             
				if(line.endsWith("1")){
					System.out.println(line);
					
    				  String [] r = line.split(",");
    					  String []k = r[1].split("=");
    					   stats.addValue(r.length-1);
    					   System.out.println("r[i].length = "+r[1].length());
    					   System.out.println("r.length = "+r.length);
    					  count_id++;
    					  System.out.println("°° "+count_id);
    		    }
				
				count_tot++;
				System.out.println(count_tot);
				
			}br.close();
			
		   double percent =(count_id/(count_tot-1))*100; 
		   System.out.println(percent);
           map_i.put(percent, stats.getMean());
           map.put(namefile, map_i);
      
		}
		
		PrintWriter out2 = new PrintWriter(new FileWriter(new File(outfile+"\\diagrammiR_socialUsers7.csv")));
        out2.println("time,average,points");
        for (Long s : map.keySet( )) {
        	out2.print(s+",");
        }
        out2.println();
        for(Long s : map.keySet( )){
        	for (Double d : map.get(s).keySet()) {
        		out2.print(round(d,2)+",");
        		
        	}
        }
        
        	out2.println();
        	for(Long s : map.keySet( )){
        	for (Double d : map.get(s).keySet()) {
        		out2.print(round(map.get(s).get(d),2)+",");	
			}
        	
        }
        out2.close();
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}