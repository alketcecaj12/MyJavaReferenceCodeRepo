package visualize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

public class Main2RBoxplot {
	
	public static void main (String [] args) throws Exception{
		
		String file = "C:\\Users\\alketcecaj\\eclipse_workspace\\DataTel\\data4R\\t7\\1800000,7 test.txt";
		//String file = "test.csv";
		String line;
		Map<Integer, Integer>map = new TreeMap<Integer, Integer>();
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		
		while((line = br.readLine())!= null){
	
			
//          for calculation of boxplot values
			
//			String [] r = line.split(",");
//			if(line.endsWith("1")){
//				for (int i = 1; i < r.length; i++) {
//					if(r.length>20) continue;
//					System.out.println(r[i]);
//				}
//			}
//			else{
//				for (int i = 1; i < r.length-1; i++) {
//					if(r.length>20) continue;
//					System.out.println(r[i]);
//				}
//			}
			
//          for calculation of percentages 
			
			String [] r = line.split(",");
			if(line.endsWith("1")){
				
					System.out.println(r.length);
				
						String [] rr = r[r.length-1].split("="); 
						//System.out.println(rr[0]+"     "+r[1]);
						int key = Integer.parseInt(rr[1]);
						Integer v = map.get(key);
						//System.out.println(key +"-->>>"+v);
						if(v == null)
						map.put(key,1 );
						else map.put(key, v+1);
					
				}
			
			else{
			
					if(r.length>20) continue;
					else{
						String []rr = r[r.length-2].split("="); 
						//System.out.println(rr[0]+", "+rr[1]);
						int key = Integer.parseInt(rr[1]);
						Integer v = map.get(key);
						//System.out.println(key +"--> "+v);
						if(v == null)
						map.put(key,1 );
						else map.put(key, v+1);
					}
					
				
			}
		}br.close();
		
		
		for (int i : map.keySet()) {
			System.out.println(i+","+map.get(i));
		}
		
	}

}
