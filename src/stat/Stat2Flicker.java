package stat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Stat2Flicker {
public static void main (String [] args)throws Exception{
		
		String file = "C:/Users/alketcecaj/eclipsework_space/FlickrSource/ATALANTA2_bbox";
		String file2 = "C:/Users/alketcecaj/eclipsework_space/FlickrSource/ATALANTA_bbox";
		String file3 = "C:\\Users\\alketcecaj\\Desktop\\F_Data_FIle";
	
		File f = new File(file);
		File []folder = f.listFiles();
		
		Set<String>set = new HashSet<String>();
		Set<String>list = new HashSet<String>();
		
		load(set, file);
		
		Map<String, List<String>>map = new TreeMap<String, List<String>>();
			
			BufferedReader br = new BufferedReader(new FileReader(file2));

			String line;
			
			while((line = br.readLine())!= null){
					
			    String [] l = line.split("\t");
			    if(set.contains(l[0])){
			    	list.add(l[0]);
			    }
               

			}br.close();

		System.out.println(map.size());

		//System.out.println(set.size());
		System.out.println("nuovi utenti = "+ list.size());
		//print(list, "AllFlickrData.tsv");
	}

public static void print(Set<String>s, String file) throws Exception{
	
	PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
	
	for(String st : s){
		out.println(st);
	}
	out.close();
}

public static void load(Set<String>s, String file) throws Exception{
	System.out.println("dentro metodo ");
	BufferedReader br = new BufferedReader(new FileReader(new File(file)));
	System.out.println("dopo br");
	String line;
	
	while((line = br.readLine())!= null){

			String [] l = line.split("\t");
	        s.add(l[0]);
	
	}br.close();
}

}
