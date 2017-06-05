package stat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Main2Stats2 {

	public static void main (String [] args) throws Exception{
		
		String file = "1476.txt";
		String line;
		Map<Integer, DescriptiveStatistics>map = new TreeMap<Integer, DescriptiveStatistics>();
		List<String>coppie = new ArrayList<String>();
		
		Set<String>set = new HashSet<String>();
		List<String>list = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		while((line = br.readLine())!= null){
			
			String [] user = line.split(",");
			String username = user[0];
			set.add(username);
			
			if(line.endsWith("=1")){
				list.add(line);
			}
			//if(line.endsWith("=1")){
			for (int i = 1; i < user.length; i++) {
				String []keyval = user[i].split("=");
				
				int key = Integer.parseInt(keyval[0]);
				double val = Double.parseDouble(keyval[1]);
				
				DescriptiveStatistics li = map.get(key);
				if(li == null){
					li = new DescriptiveStatistics();
					map.put(key, li);
				}
				else{
				li.addValue(val);	
				}
				
				coppie.add(user[i]);
			}
			//}
		}br.close();
		System.out.println(list.size());
		System.out.println(set.size());
		PrintWriter out = new PrintWriter(new FileWriter(new File("statsd4d.txt")));
		
		for(int i : map.keySet()){
			//System.out.println(i+"--> "+map.get(i).getN());
		}
		
		for (int i :map.keySet()) {
		
				out.println(i+","+map.get(i).getMean()+","+map.get(i).getStandardDeviation()+","
			+map.get(i).getPercentile(25)+","+map.get(i).getPercentile(50)+","+map.get(i).getPercentile(75));
			
		}
		out.close();
		Map<Integer, Map<Double,Double>> statmap = new TreeMap<Integer, Map<Double, Double>>();
		
		for(int i : map.keySet()){
			Map<Double, Double>m1 = statmap.get(i);
			if(m1== null){
				m1 = new HashMap<Double, Double>();
				m1.put(map.get(i).getMean(), map.get(i).getStandardDeviation());
			}
			else{
				m1.put(map.get(i).getMean(), map.get(i).getStandardDeviation());
			}
			
		}
		}
		
//		Map<Integer, Map<Double, Map<Double, Map<Double, Double>>>> m = 
//				     new HashMap<Integer, Map<Double, Map<Double,Map<Double, Double >>>>();
//	
//		for (int i : map.keySet()) {
//			Map<Double, Map<Double, Map<Double, Double>>>m1 = m.get(i);
//			if(m1 == null){
//				m1 = new HashMap<Double, Map<Double, Map<Double, Double>>>();
//				m.put(i, m1);
//				System.out.println(m1.size());
//			}
//			else m.put(i, m1);
//			double mean = map.get(i).getMean();
//			 Map<Double, Map<Double, Double>>m2 = m1.get(mean);
//			 if(m2== null){
//				 m2 = new HashMap<Double, Map<Double, Double>>();
//				 m1.put(mean, m2);
//				 System.out.println(m2.size());
//			 }
//			 else m1.put(mean, m2);
//			 System.out.println(m.size());
//			 double devstd = map.get(i).getStandardDeviation();
//			 Map<Double, Double>m3 = m2.get(devstd);
//			 if(m3 == null){
//				 m3 = new HashMap<Double, Double>();
//				 m3.put(map.get(i).getPercentile(25), map.get(i).getPercentile(75));
//			 }
//			 else m3.put(map.get(i).getPercentile(25), map.get(i).getPercentile(75));
//			//System.out.println(i+" --> "+map.get(i).getN());
//		}
//		System.out.println("--> "+m.size());
//		for (int i : m.keySet()) {
//			for (double med: m.get(i).keySet()) {
//				for(double dev : m.get(i).get(med).keySet()){
//					for(double per : m.get(i).get(med).get(dev).keySet()){
//					  System.out.println(i+","+med+","+dev+","+per+","+m.get(i).get(med).get(dev).get(per));
//					}
//				}
//			}
//		}
//		Set<String>slist = new HashSet<String>();
//		BufferedReader br2 = new BufferedReader(new FileReader(new File(file)));
//		String line2;
//		
//        while((line2 = br2.readLine())!= null){
//			
//			String [] user = line2.split(",");
//			String username = user[0];
//			if(set.contains(username)){
//				slist.add(line2);
//			}
//		}br.close();
//		
//		System.out.println(slist.size());
//		print("1476.txt", slist);
//		Collections.sort(coppie);
//		printList("coppie.txt", coppie);
	
	
	public static void print(String file, Set<String>set) throws Exception {
		
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
	    for (String s: set) {
			out.println(s);
		}
	    out.close();
	}
    public static void printList(String file, List<String>set) throws Exception {
		
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
	    for (int i = 0; i < set.size(); i++) {
			out.println(set.get(i));
		}
	    out.close();
	}
}
