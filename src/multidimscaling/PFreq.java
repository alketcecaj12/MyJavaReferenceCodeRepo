package multidimscaling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PFreq {
	
		public String parola;
		public double f = 0; 
		public PFreq(String p, double freq){
			this.parola = p;
			this.f = freq;
		}
		
		
		public String toString (){
			return ""+parola+", "+f;
		}
		
		
		public static void load(String file, Map<String, List<PFreq>>map) throws Exception{
			
			BufferedReader br = new BufferedReader(new FileReader(new File(file)));
			String titolo;
			String parola; 
			double f = 0;
			String line; 
			
			List<PFreq> pf = null;
			
			while((line = br.readLine())!= null) {
				pf = new ArrayList<PFreq>();
				String [] s = line.split("  ");
				
				titolo = s[0];
				
				String []s1 = s[1].split(" ");
				
				for(String str : s1){
					String [] s2 = str.split(":");
					parola = s2[0];
					f = Double.parseDouble(s2[1]);
					pf.add(new PFreq(parola, f));
				}
				map.put(titolo, pf);
			}
			
		}
		
		
		
	}
	

