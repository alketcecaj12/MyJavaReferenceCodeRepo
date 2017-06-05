package mds.collaborativefiltering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Recensione {

	public String titolo;
	public double voto;
	
	public Recensione(String t, double v){
		this.titolo = t;
		this.voto = v;
	}
	public String toString(){
		return " titolo : "+titolo+", voto: "+voto;
	}
	
    // metodi distanza euclidea
	public static  double dist (Map<String, List<Recensione>>map, String c1, String c2){
		List<Recensione> l1 = map.get(c1);
		List<Recensione> l2 = map.get(c2);
		double dist = 0;
		double quad = 0;
		for (int i = 0; i < l1.size(); i++) {
			Recensione r1 = l1.get(i);
			double dx = 0;
			for (int j = 0; j < l2.size(); j++) {
				
				Recensione r2 = l2.get(j);	
				if(r1.titolo.equals(r2.titolo)){
				    dx = r2.voto-r1.voto;
				    quad = Math.pow(dx, 2);
				}	
			}
			dist+= quad;
		    dist = Math.sqrt(dist);
		}
		return 1/(1+dist);
	}
	public static List<Double>edUvT(String c, Map<String, List<Recensione>>map){
		
		List<Double> darr = new ArrayList<Double>();
		
		for(String s : map.keySet()){
			double d = dist(map,s, c);
			if (s.equals(c)) 
				continue;
			     darr.add(d);
		}
		return darr;
	}
	
	// metodi pearson
    public static double pearson(String c1, String c2, Map<String, List<Recensione>>map){
		
		double cp = 0;
		double cov = covarianza(c1, c2, map);
		
		List<Recensione>mr1 = map.get(c1);
		List<Recensione>mr2 = map.get(c2);
		
		List<Double>d1 = new ArrayList<Double>();
		List<Double>d2 = new ArrayList<Double>();
		Map<String, List<Double>>mappa = new HashMap<String, List<Double>>();
		int den = 0;
		for (int i = 0; i < mr1.size(); i++) {
			double m2 = -1;
			for(int j = 0; j <mr2.size(); j++){
				if (mr2.get(j).titolo.equals(mr1.get(i).titolo)) {
					m2 = mr2.get(j).voto;
					d1.add(mr1.get(i).voto);
					d2.add(m2);
					den++;
				}
			}
		}
		mappa.put(c1, d1);
		mappa.put(c2, d2);
		
		double v1 = varianza(c1, mappa);
		double v2 = varianza(c2, mappa);
		
		cp = cov/(v1*v2);
		if(Double.isNaN(cp))  return 0;
		return cp;
		//return (1- Math.abs(cp));	
	}
	
	public static double covarianza(String c1,String c2, Map<String, List<Recensione>>m){
		double cov = 0; 
		List<Double> m1 = new ArrayList<Double>();
		List<Double> m2 = new ArrayList<Double>();
		
		List<Recensione>mr1 = m.get(c1);
		List<Recensione>mr2 = m.get(c2);
		Map<String, List<Double>>mappa = new HashMap<String, List<Double>>();
		
		double medc1 = 0;
		double medc2 = 0; 
		
		double sommatoria = 0;
		
		double den = 0; 
		double prodotto_i = 0; 
		
		for (int i = 0; i < mr1.size(); i++) {
			double recc2 = -1;
			
		  for(int j = 0; j < mr2.size(); j++){
			 
			  if(mr1.get(i).titolo.equals(mr2.get(j).titolo)){
				  recc2 = mr2.get(j).voto;
				  m1.add(mr1.get(i).voto);
				  m2.add(recc2);
				  den++;
			  }  
		   }
		}
		mappa.put(c1, m1);
		mappa.put(c2, m2);
		
		medc1 = media(c1, mappa); 
		medc2 = media(c2, mappa);
	  
		for (int i = 0; i < m1.size(); i++) {
			prodotto_i =( m1.get(i)-medc1)*(m2.get(i)-medc2);
			 sommatoria += prodotto_i;
			 }
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
	 public static List<Double> cpUvT( String c1, Map <String, List<Recensione>> critics){
        List<Double>cpdist = new ArrayList<Double>();
		
		for (String criticsi: critics.keySet()){
			//if(criticsi.equals(c1))
				//continue;
			cpdist.add(pearson( c1, criticsi, critics));	
		}
		return cpdist;
	}
	 public static double[] cp_UvT( String c1, Map <String, List<Recensione>> critics){
	     int dim = critics.size()*critics.size();   
		 double [] cpdist = new double[dim];
			int i = 0; 
			for (String criticsi: critics.keySet()){
				//if(criticsi.equals(c1))
					//continue;
				cpdist[i] = pearson(c1, criticsi, critics);
				i++;
			}
			return cpdist;
		}
	 
	 public static List<double[]> coeffpearsUvT( Map <String, List<Recensione>> critics){
	        List<double[]>cpdist = new ArrayList<double[]>();
			
			for (String critics_i: critics.keySet()){
				//if(criticsi.equals(c1))
					//continue;
				System.out.println(critics_i);
				cpdist.add(cp_UvT(critics_i, critics));	
			}
			return cpdist;
		}        
	 
	 
	 
	public static double bestMatch(List<Double>dist){
		double bmatch = 0;
		for (int i = 0; i < dist.size(); i++) {
		if(dist.get(i) > bmatch)
			bmatch = dist.get(i);
		}
		return bmatch;	
	}

	public static void pScore(String c, Map<String , List<Recensione>> map){
		
		 List<Double> dis = new ArrayList<Double>();
			
		    dis = Recensione.cpUvT(c, map);
		    String [] d= new String[map.size()-1];
		    int l=0;
		    for(String u: map.keySet()){
			  d[l]=u;
			   if(u.equals(c))
				continue;
			    l++;
		    }
		    for (int i = 0; i < dis.size(); i++) {
			  System.out.println( i+" coefficenti di Pearson di "+c+" verso critico " +d[i]+ " con valore "+dis.get(i));
		    }
		    List<NameDist> nd = new ArrayList<NameDist>();
	        for(String name : map.keySet()) {
	           //if(name.equals(c))
	        	 //  continue;
				nd.add(new NameDist(name,Recensione.pearson(c, name, map) ));
	        }
		    Collections.sort(nd);
		    for (int i = 0; i < nd.size(); i++) {
			    System.out.println(nd.get(i));
		    }
		    for(int i = 0; i < dis.size(); i++){	
			  if(dis.get(i)==Recensione.bestMatch(dis)){
				System.out.println("Il matching migliore per "+c+" è : "+d[i]);
			  }
		    }
	}
	
	public static double getVoto(List<Recensione> rec , String film){
		for (int i = 0; i < rec.size(); i++) {
			if (rec.get(i).titolo.equals(film)){ 
				return rec.get(i).voto;
			}	
		}
		return -1;
	}
	
	public static void recItems(String c, List<String>film, Map<String, List<Recensione>>map)throws Exception{
		List<RecOrd>score = new ArrayList<RecOrd>();
        double sc_pes = 0;
        for (int j = 0; j < film.size(); j++) {
			double sum = 0;
		    double sumsim = 0;
	        for (String name : map.keySet()) {
	    	  if (name.equals(c)) 
			  continue;
			
		      List<Recensione> r = map.get(name);
		
		      double v = getVoto(r,film.get(j));
              if(v > 0){
			   double sumprov = 0; 
			   sumprov = Recensione.pearson(c, name, map)*v;
			   sum += sumprov;
			   sumsim += Recensione.pearson(c, name, map);
		      }
            }
	       sc_pes = sum/sumsim;
	       if(Double.isNaN(sc_pes))
	       sc_pes = 0;
	       System.out.println("Score pesato del critico "+c+" per" +
	    	                        	" il film "+film.get(j)+" è "+sc_pes+"************************");
           String f = film.get(j);
           score.add(new RecOrd(c, f, sc_pes));
        }
        Collections.sort(score);
        for (int i = 0; i < score.size(); i++) {
			System.out.println(score.get(i));
		}
        for(int i = 0; i < 3; i++){
        RecOrd rc = null;
        rc = Collections.max(score);
        System.out.println("massimo "+i+" è  "+rc);
        score.remove(rc);
       } 
	}	
	

	
	public static List<Double> distPearson(String c1 , Map<String, List<Recensione>>map){
		
		    List<Double>cpdist = new ArrayList<Double>();
			
			for (String criticsi: map.keySet()){
				if(criticsi.equals(c1))
					continue;
				cpdist.add(1 - Math.abs(pearson( c1, criticsi, map)));	
			}
			return cpdist;
	}
}