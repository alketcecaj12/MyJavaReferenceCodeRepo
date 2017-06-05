package alg.kmeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestReadingFileFromMethod {

	public static void main (String[]args) throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader(new File("data/largeClusterData.txt")));
		List<Placemark> plc1 = new ArrayList<Placemark>();
		List<Placemark> plc2 = new ArrayList<Placemark>();
		String linea = null;
		
		
		
		// con questo metodo posso leggere da file dove le righe da leggere sono una dopo l'altra
		//Il metodo esegue anche la connessione con il file 
		List<Placemark> plc = new ArrayList<Placemark>();
		System.out.println("Dati letti dal file filedati.txt");
		loadDataFF("data/lineaDatiRipeti.txt", plc);
		
		for (int i = 0; i < plc.size(); i++) {
			System.out.println("l'elemnto i in lettura da lineaDatiRipeti.txt: "+plc.get(i));
		}
	}
	
	public static void loadDataFF(String file, List<Placemark> p_arr) throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		
		String line = null;
		
		while((line = br.readLine())!= null){
		
			String[] s = line.split(" ");
			double lat = 0;
			double lon = 0;
			String [] str = null;
			
			  for(String st: s){
				
			   str = st.split(",");	
			   lat = Double.parseDouble(str[0]);	
			   lon = Double.parseDouble(str[1]);	
			   p_arr.add(new Placemark(lat, lon));
			  }
		}br.close();
	}
       public static void loadData(String line, List<Placemark>p_arr){
		
		String []s = line.split(" ");
		double lat = 0;
		double lon = 0;
		String [] st = null;
		
		for(String str : s ){
		
	    st = str.split(",");
		lat = Double.parseDouble(st[0]);
		lon = Double.parseDouble(st[1]);
		p_arr.add(new Placemark(lat, lon));
		}
	  }
}
