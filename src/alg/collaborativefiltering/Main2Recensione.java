package alg.collaborativefiltering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Recensione {

	public static void main (String [] args)throws Exception{
		
		Map<String, List<Recensione>> map2 = new HashMap<String, List<Recensione>>();
		
		LoadData.loadFile("data/datamovielens.txt", map2);
		for (String s: map2.keySet()) {
			System.out.print("NOME "+s);
			System.out.println(" REC "+map2.get(s));
		}
		
        List<String> films = new ArrayList<String>();
       
         films = LoadData.mustSee("1111", map2);
         for (int i = 0; i < films.size(); i++) {
	   	    System.out.println(" film da vedere : "+films.get(i));
	     }
        
         Recensione.recItems("3",films, map2); 
       }
}
