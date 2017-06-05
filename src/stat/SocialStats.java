package stat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SocialStats {
	
	public static Map<String, Integer>map = new HashMap<String, Integer>();
	public static Map<String, Integer>map2 = new HashMap<String, Integer>();
	public static Map<String, String>map3 = new HashMap<String, String>();
	
	public static void main (String [] args) throws Exception{
		
		String t2 = "C:\\Users\\alketcecaj\\eclipsework_space\\D4DMatching\\AllGeoTwitterUsers.csv";
		String t = "AllTwitterUsers";
		String file2 = "C:\\Users\\alketcecaj\\eclipse_workspace\\" +
				"DataTel\\socialusers\\social_users_by_username-events.csv";
		String f = "TotalFlickrUsers4MatchingNoDuplicates";
		String file ="C:\\Users\\alketcecaj\\eclipse_workspace\\" +
				"DataTel\\socialusers\\"+f+".csv";
		System.out.println(file);
		BufferedReader br = new BufferedReader(new FileReader(new File(t2)));
		String line;
		while((line = br.readLine())!= null){
			
			String [] r = line.split(",");
			
			String user = r[1];
			String text= r[6];
			
			String v = map3.get(user);
			if(v==null){
			map3.put(user, text);
			}
			else map3.put(user, v+". "+text);
			
		}
		br.close();
		
		for (String b: map3.keySet()) {
			System.out.println(b+"-->"+map3.get(b));
		
		}
		
		
	}
	private static HashMap sortByValues(HashMap map) { 
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
}
