package stat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import matchingD4D.DataI;

public class Main2Stats {

	
	public static void main(String[] args) throws Exception{
		
		Map<String, Integer>map = new HashMap<String, Integer>();
		String path2files = "C:\\DATA\\pls\\CoordPLS";
		load(path2files, map);
		
		Set set = map.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	           Map.Entry me = (Map.Entry)iterator.next();
//	           System.out.print(me.getKey() + ": ");
//	           System.out.println(me.getValue());
	      }
	      Map<String, Integer> map2 = sortByValues(map); 
//	      System.out.println("After Sorting:");
//	      Set set2 = map.entrySet();
//	      Iterator iterator2 = set2.iterator();
//	      while(iterator2.hasNext()) {
//	           Map.Entry me2 = (Map.Entry)iterator2.next();
//	           System.out.print(me2.getKey() + ": ");
//	           System.out.println(me2.getValue());
//	      }
		  
	      print(map2, "EventsPerUSer7GiorniNight.csv");	
	}
	
	 private static Map sortByValues(Map map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
	public static void load(String file, Map<String, Integer>map) throws Exception{
		List<DataI>l = new ArrayList<DataI>();
		Set<String>set = new HashSet<String>();

		File Folder = new File(file);
		File files[];
		files = Folder.listFiles();
       // map = new TreeMap<String, Integer>();
        try{
		if(files.length>1)
		{
			Calendar c = Calendar.getInstance();
			for(int i = 0;i<files.length; i++){
				//if(i==10) break;
				BufferedReader br = new BufferedReader(new FileReader(files[i]));
				String line;
                System.out.println(files[i].getName()+"  "+i);
				while((line = br.readLine())!= null){
				
					String[] r = line.split(",");
					String user = r[0];
					long ts = Long.parseLong(r[3]);
					c.setTimeInMillis(ts);
					if(c.get(Calendar.HOUR_OF_DAY) > 0 && c.get(Calendar.HOUR_OF_DAY) < 5){
					
					Integer count = map.get(user);
					if(count == null){
						map.put(user, 1);
					}
					else map.put(user, count+1);
					}
				}br.close();
				}
		}
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	public static void print(Map<String, Integer>map, String file)throws Exception{
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));

		for (String s: map.keySet()) {
			
				out.println(s+"="+map.get(s));
				
				//System.out.println(map.get(s).get(s2).toString());

		}
		out.close();
	}
}
