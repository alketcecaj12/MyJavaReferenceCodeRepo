package visualize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main2Rdata {
	
	public static void main (String [] args) throws Exception{
		
		String path2file = "C:\\Users\\alketcecaj\\eclipsework_space\\D4DMatching\\EventsPerUser\\EventsPerUserSET2_0.csv";
		String outfile ="C:\\Users\\alketcecaj\\eclipse_workspace\\DataTel\\data4R";
		List<Integer>l = new ArrayList<Integer>();
		Map<Integer, Integer>map = new TreeMap<Integer, Integer>();
		
		BufferedReader br = new BufferedReader(new FileReader(new File(path2file)));
		
		String line;
		while((line = br.readLine())!= null){

			String []r = line.split("=");
            
			int nr = Integer.parseInt(r[1]);
			l.add(nr);
			
			Integer val = map.get(nr);
			if(val == null){
				map.put(nr, 1);
			}
			else{
				map.put(nr, val+1);
			}

		}br.close();
        Collections.sort(l);
        
        PrintWriter out = new PrintWriter(new FileWriter(new File(outfile+"\\dataR.csv")));
        for (int i = 0; i < l.size(); i++) {
			out.println(l.get(i));
		}
        out.close();
        System.out.println(map.size());
        
        PrintWriter out2 = new PrintWriter(new FileWriter(new File(outfile+"\\datafreqR.csv")));
        out2.println("valore,freq");
        for (Integer i : map.keySet()) {
        	out2.print(i+",");
			out2.println(map.get(i));
		}
        out2.close();
	}

}
