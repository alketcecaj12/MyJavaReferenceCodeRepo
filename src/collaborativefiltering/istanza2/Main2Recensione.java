package collaborativefiltering.istanza2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Recensione {

	public static void main (String [] args)throws Exception{
		
		Map<String, List<Recensione>> map2 = new HashMap<String, List<Recensione>>();
		//LoadSimpleData.loadDataDB("data/ratings_istanza2.txt");
		
		LoadSimpleData.loadData("data/ratings_istanza2.txt", map2);
		
		for (String s: map2.keySet()) {
			System.out.print("NOME "+s);
			System.out.println(" REC "+map2.get(s));
		}
        Recensione.pScore("Toby", map2);
        List<String> films = new ArrayList<String>();
        
        films = LoadSimpleData.mustSee("Toby", map2);
        for (int i = 0; i < films.size(); i++) {
	   	    System.out.println(" film da vedere per il critico : "+films.get(i));
	     }
        
        Recensione.recItems("Toby",films, map2);
        
        
        
	}
}
