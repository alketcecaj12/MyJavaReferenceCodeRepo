package dimreduction.pca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadBlogdata {

	public static void main (String [] args) throws Exception{
		
		Map<String, List<Parola>>map = new HashMap<String, List<Parola>>();
		
		loadData("data/datiBlog.txt", map);
		
		for(String s : map.keySet()){
			System.out.print(s);
			System.out.println(map.get(s));
		}
		System.out.println(map);
	
	System.out.println("BEPPEGRILLO-ECONOMIST "+pearson("beppegrillo", "economist", map));
	System.out.println("BEPPEGRILLO-HITECH "+pearson("beppegrillo", "hitech", map));
	System.out.println("BEPPEGRILLO-BASKET "+pearson("beppegrillo", "basket", map));
	System.out.println("BEPPEGRILLO-GADLERNER "+pearson("beppegrillo", "gadlerner", map));
	System.out.println("BEPPEGRILLO-BALLARò "+pearson("beppegrillo", "ballarò", map));
	System.out.println("BEPPEGRILLO-REPUBBLICA "+pearson("beppegrillo", "repubblica", map));
	System.out.println("BEPPEGRILLO-TIMES "+pearson("beppegrillo", "times", map));
	System.out.println("BEPPEGRILLO-BUSSINESSINS "+pearson("beppegrillo", "businessinsider", map));
	System.out.println("ECONOMIST-BUSSINESSINS "+pearson("economist", "businessinsider", map));
	System.out.println("BASKET-CALCIO "+pearson("basket", "calcio", map));
	}
	
	
	
	
	public static void loadData(String file, Map<String, List<Parola>> map) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));	
		List<Parola>p_arr = null;
		String line = null;
		
		while((line=br.readLine())!= null){
		 String [] s = line.split("  ");
		
		 String name = s[0];
		
		 p_arr = new ArrayList<Parola>();
		
		 String [] s2 = s[1].split(" ");
		
          for (int i = 0; i < s2.length; i++) {
			String [] s3 = s2[i].split(":");
			String p = s3[0];
			double d = Double.parseDouble(s3[1]);
			p_arr.add(new Parola(p, d));
		  }
		  map.put(name, p_arr);
	   }br.close();
	}
public static double pearson(String c1, String c2, Map<String, List<Parola>>map){
		
		double cp = 0;
		double cov = covarianza(c1, c2, map);
		System.out.println("la covarianza è "+cov);
		List<Parola>mr1 = map.get(c1);
		List<Parola>mr2 = map.get(c2);
		
		List<Double>d1 = new ArrayList<Double>();
		List<Double>d2 = new ArrayList<Double>();
		Map<String, List<Double>>mappa = new HashMap<String, List<Double>>();
		int den = 0;
		for (int i = 0; i < mr1.size(); i++) {
			double m2 = -1;
			for(int j = 0; j <mr2.size(); j++){
				if (mr2.get(j).parola.equals(mr1.get(i).parola)) {
					m2 = mr2.get(j).freq;
					d1.add(mr1.get(i).freq);
					d2.add(m2);
					den++;
				}
			}
		}
		mappa.put(c1, d1);
		
		mappa.put(c2, d2);
		System.out.println("la mappa è "+mappa);
		double v1 = varianza(c1, mappa);
		
		double v2 = varianza(c2, mappa);
		System.out.println(""+v1+", "+v2);
		cp = cov/(v1*v2);
		return cp;	
	}
	
	public static double covarianza(String c1,String c2, Map<String, List<Parola>>m){
		double cov = 0; 
		List<Double> m1 = new ArrayList<Double>();
		List<Double> m2 = new ArrayList<Double>();
		
		List<Parola>mr1 = m.get(c1);
		List<Parola>mr2 = m.get(c2);
		Map<String, List<Double>>mappa = new HashMap<String, List<Double>>();
		
		double medc1 = 0;
		double medc2 = 0; 
		
		double sommatoria = 0;
		
		double den = 0; 
		double prodotto_i = 0; 
		
		for (int i = 0; i < mr1.size(); i++) {
			double recc2 = -1;
			
		  for(int j = 0; j < mr2.size(); j++){
			 
			  if(mr1.get(i).parola.equals(mr2.get(j).parola)){
				  recc2 = mr2.get(j).freq;
				  m1.add(mr1.get(i).freq);
				  m2.add(recc2);
				  den++;
				  System.out.println(den);
			  }  
		   }
		}
		 mappa.put(c1, m1);
		 mappa.put(c2, m2); 
	     System.out.println(mappa);
		
		medc1 = media(c1, mappa); 
		System.out.println(medc1);
		
		medc2 = media(c2, mappa);
	    System.out.println(medc2);
	    
		for (int i = 0; i < m1.size(); i++) {
			 prodotto_i =( m1.get(i)-medc1)*(m2.get(i)-medc2);
			 System.out.println("prodotto i "+prodotto_i);
			 sommatoria += prodotto_i;
			 }
			System.out.println("sommatoria "+sommatoria);
		  cov = sommatoria/den;
	  return cov;
	}
	
	public static double varianza(String c, Map<String, List<Double>> m){
		double var = 0;
		double somma = 0;
		List<Double>d = m.get(c);
		for (int i = 0; i < d.size(); i++) {
			somma += Math.pow((d.get(i)-media(c, m)), 2);
			
		}
	
		var= Math.sqrt(somma/(d.size()));
		return var;
	}
	 public static double media(String c, Map<String, List<Double>> m){
			
			double media = 0; 
			
			List<Double>mr = m.get(c);
	          for(int i = 0; i < mr.size(); i++){
				media += mr.get(i);
			  }
				media = media/mr.size();
			return media;
		}
}
