package MatchingPack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gps.utils.LatLonUtils;



public class PLSMethods {
	
	public static List<DataI> load(String file, int nr) throws Exception{
		List<DataI>l = new ArrayList<DataI>();
		Set<String>set = new HashSet<String>();

		File Folder = new File(file);
		File files[];
		files = Folder.listFiles();

		if(files.length>1)
		{
			for(int i = 14;i<files.length; i++){

				BufferedReader br = new BufferedReader(new FileReader(files[i]));
				String line;

				while((line = br.readLine())!= null){

					set.add(line);

					if(set.size()==nr) break;

				}br.close();
				break;
			}
		}
		for (String s : set){
			
			String[]r = s.split(",");	
			String h = r[0];
			int i = Integer.parseInt(r[1]);
			long c = Long.parseLong(r[2]);
			long t = Long.parseLong(r[3]);
			double lat = Double.parseDouble(r[4]);
			double lon = Double.parseDouble(r[5]);
			double rad = Double.parseDouble(r[6]);
			l.add(new PLS(h, i, c, t, lat, lon, rad));
		}
		//System.out.println(l);
		return l;
	}
	
	
	public static List<DataI> load2(String file) throws Exception{
		List<DataI>l = new ArrayList<DataI>();
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line;

		while((line = br.readLine())!= null){

			String [] r = line.split("=");
            String user = r[0];
//            int im = Integer.parseInt(r[1]);
//            long cell = Long.parseLong(r[2]);
//            long ts = Long.parseLong(r[3]);
//            System.out.println(ts);
//            double lat = Double.parseDouble(r[4]);
//            double lon = Double.parseDouble(r[5]);
//            double rag = Double.parseDouble(r[6]);
//            System.out.println(rag);
            l.add(new PLS(user, 12, 12, 12, 12, 12, 12));
		}br.close();
		
		return l;
	}
	
	
	public static List<PLS> pls_Users(String pls_user, long mintime) throws Exception{
		String inflow = "C:\\DATA\\dataPLS\\mese\\AllData";
		List<PLS>list = new ArrayList<PLS>();
		File Folder = new File(inflow);
		File files[];
		files = Folder.listFiles();

		try{
			String line = "";

			if(files.length>1)
			{
				for(int i = 0;i<files.length; i++){

					BufferedReader br = new BufferedReader(new FileReader(files[i]));
					//System.out.print("reading...");
					//System.out.println(files[i].getName()+", number: "+i);
					
					while((line = br.readLine())!=null){

						String [] riga = line.split("\t");
						String hash = riga[0];
						hash.trim();
						pls_user.trim();
						if(hash.equals(pls_user)){
							//System.out.println("uguali ");
							int imsi = Integer.parseInt(riga[1]);
							long cellac = Long.parseLong(riga[2]);
							
							long t = Long.parseLong(riga[3]);
							
							if( list.isEmpty() || (Math.abs(t - list.get(list.size()-1).time) > mintime) ){
								double lat = Double.parseDouble(riga[4]);
								double lon = Double.parseDouble(riga[5]);
								double rad = Double.parseDouble(riga[6]);
								list.add(new PLS(hash, imsi, cellac, t, lat, lon, rad));
							}
						}

					}
					br.close();
				}
			}
		}catch( Exception ex){
			ex.printStackTrace(); 
		}

		System.out.println(list.size());
           return list;
	}

	
	public static Map<String,List<DataI>> findEvents(List<DataI> pls_users, long mintime, int max) throws Exception{
		String inflow = "C:\\DATA\\AllData";
		Map<String,List<DataI>> map = new HashMap<String,List<DataI>>();
		
		for(DataI u: pls_users) 
			map.put(u.getUser(), new ArrayList<DataI>());
		
		
		File Folder = new File(inflow);
		File files[];
		files = Folder.listFiles();

		try{
			String line = "";

			if(files.length>1)
			{
				for(int i = 0;i<files.length; i++) {

					BufferedReader br = new BufferedReader(new FileReader(files[i]));
					System.out.print("reading...");
					System.out.println(files[i].getName()+", number: "+i);
					
					while((line = br.readLine())!=null){

						String [] riga = line.split("\t");
						String hash = riga[0];
						hash.trim();
						
						List<DataI> list = map.get(hash);
						
						if(list!=null){
							int imsi = Integer.parseInt(riga[1]);
							long cellac = Long.parseLong(riga[2]);
							long t = Long.parseLong(riga[3]);
							
							if( list.isEmpty() ||(t - list.get(list.size()-1).getTimestamp()) > mintime ){
								double lat = Double.parseDouble(riga[4]);
								double lon = Double.parseDouble(riga[5]);
								double rad = Double.parseDouble(riga[6]);
								list.add(new PLS(hash, imsi, cellac, t, lat, lon, rad));
							}
						}
					}
					br.close();
					
					// check if I have enough event
					
					boolean exit = true;
					for(List<DataI> l: map.values())
						if(l.size() < max)
							exit = false;
					if(exit && max > 0) break;
				}
			}
		}catch( Exception ex){
			ex.printStackTrace(); 
		}

		return map;
	}

   
	public static void print(String file, List<DataI>list)throws Exception{
		
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
		
		for (int i = 0; i < list.size(); i++) 
			out.println(list.get(i).toString());
		
		out.close();
	}
    
	public static void print(String file, Map<String, List<DataI>>list)throws Exception{
		
	PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
	for (String s: list.keySet()) {
			
		for (int i = 0; i < list.get(s).size(); i++) 
			out.println(s+","+list.get(s).get(i).toString());
		out.println();
					
	}
	
	out.close();
	}

    public static List<PLS> getPLS1(String file, long cellac, long ts) throws Exception{

	 File Folder = new File(file);
	 File files[];
	 files = Folder.listFiles();
	 List<PLS>list = new ArrayList<PLS>();
	 
	try{
        String line = "";

        if(files.length>1){
        	
         for(int i = 0;i<files.length; i++){
           
            System.out.print("reading...");
            System.out.println(files[i].getName()+", number: "+i);
            String filefullname = files[i].getName();
			String filetimestamp = filefullname.substring(
					filefullname.indexOf("_1") + 1,
					filefullname.indexOf("_.txt"));

			System.out.println("la parte che mi serve è : "+filetimestamp);
			long pls_ts = Long.parseLong(filetimestamp);

			long c = 1797000;
			
			if ((pls_ts >= ts) & (pls_ts - ts) <= c) {
			
			  BufferedReader br = new BufferedReader(new FileReader((files[i])));
              while((line = br.readLine())!=null){
	      
            	  String[] riga = line.split(",");
					String username_i = riga[0];
					int imsi = Integer.parseInt(riga[1]);
					long cell_i = Long.parseLong(riga[2]);
					long tempo = Long.parseLong(riga[3]);
					double pls_lat = Double.parseDouble(riga[4]);
					double pls_lon = Double.parseDouble(riga[5]);
					double r = Integer.parseInt(riga[6]);

					long c1 = 600000;

					if ((ts - tempo <= c1) && (ts - tempo >= -c1)) {
					  if(cellac == cell_i){
						  list.add(new PLS(username_i, imsi, cell_i, tempo, pls_lat, pls_lon, r));
					  }
					}
			  
			  }br.close();
           }
         }
       }
	 }catch( Exception ex){
	    	ex.printStackTrace(); 
      }
	System.out.println("** "+list.size());
	
	return list;
}

    public static void print(List<String>map , String file)throws Exception{
	
	PrintWriter out = new PrintWriter(new FileWriter(file));
	for(int i = 0; i < map.size(); i++){
		
			out.println(map.get(i));
			
		
	}
	out.close();
}
    public static List<DataI> matchPLS(String file, List<DataI>list,Set<String>baduser, double ds, long dt) throws Exception{

    	File Folder = new File(file);
    	File files[];
    	files = Folder.listFiles();
    	Set<String>excluded = new HashSet<String>(); 
    	List<DataI>returnlist = new ArrayList<DataI>();
    	List<DataI>finalreturnlist = new ArrayList<DataI>();
    	for (int k = 0; k < list.size(); k++) {
    		//System.out.println(k+" iterazione");
    		long ts = list.get(k).getTimestamp();
    		try{
    			String line = "";

    			if(files.length>1){

    				for(int i = 0;i<files.length; i++){

    					//System.out.print("reading...");
    					// System.out.println(files[i].getName()+", number: "+i);
    					String filefullname = files[i].getName();
    					//  System.out.println(filefullname+"--------------------------------------"+i);
    					String filetimestamp = filefullname.substring(
    							filefullname.indexOf("_1") +1,
    							filefullname.indexOf("_.txt"));

    					//System.out.println("la parte che mi serve è : "+filetimestamp);
    					long pls_ts = Long.parseLong(filetimestamp);

    					long c = 1797000;

    					if(pls_ts - c > ts) break;

    					if ((pls_ts >= ts) & (pls_ts - ts) <= c) {

    						BufferedReader br = new BufferedReader(new FileReader((files[i])));
    						//System.out.println(files[i].getName()); 
    						while((line = br.readLine())!=null){

    							String[] riga = line.split("\t");
    							String username_i = riga[0];

    							if(baduser.contains(username_i)) continue;
    							int imsi = Integer.parseInt(riga[1]);
    							long cell_i = Long.parseLong(riga[2]);
    							long tempo = Long.parseLong(riga[3]);
    							double pls_lat = Double.parseDouble(riga[4]);
    							double pls_lon = Double.parseDouble(riga[5]);
    							double r = Integer.parseInt(riga[6]);
    							DataI other = new PLS(username_i,imsi,cell_i,tempo,pls_lat,pls_lon,r);

    							if(list.get(k).match(other, ds, dt)){
    								returnlist.add(new PLS(username_i, imsi, cell_i, tempo, pls_lat, pls_lon, r));
    								//System.out.println("matching trovato "+i+ ". --> lunghezza lista matching : "+returnlist.size());
    							}
    							if (list.get(k).exclude(other, ds, dt)) {
    								//   						 excluded2.add(new PLS(username_i,imsi, cell_i,
    								//   							tempo,pls_lat, pls_lon,r ));
    								excluded.add(username_i);
    								// System.out.println("escluso --> "+username_i);
    							}

    						}br.close();
    					}
    				}
    			}
    		}catch( Exception ex){
    			ex.printStackTrace(); 
    		}
    	}
    	System.out.println("prima "+returnlist.size());

    	finalreturnlist = elimina(excluded, returnlist);

    	System.out.println("dopo : "+finalreturnlist.size());
    	return finalreturnlist;
    }
    public static List<DataI> elimina(Set<String>s, List<DataI>t){

    	//List<TestObject>ret = new ArrayList<TestObject>();
       List<DataI> toberemoved = new ArrayList<DataI>(); 
    	for (DataI pls: t) 
    		if(s.contains(pls.getUser()))
    			toberemoved.add(pls);

    	t.removeAll(toberemoved);
    	return t;
    }
    
    public static List<PLS> getPLS(String file, long cellac, long ts) throws Exception{

      	 File Folder = new File(file);
      	 File files[];
      	 files = Folder.listFiles();
      	 List<PLS>list = new ArrayList<PLS>();
      	 
      	try{
              String line = "";

              if(files.length>1){
              	
               for(int i = 0;i<files.length; i++){
                 
                  //System.out.print("reading...");
                  System.out.println(files[i].getName()+", number: "+i);
                  String filefullname = files[i].getName();
      			String filetimestamp = filefullname.substring(
      					filefullname.indexOf("_1") + 1,
      					filefullname.indexOf("_.txt"));

      			System.out.println("la parte che mi serve è : "+filetimestamp);
      			long pls_ts = Long.parseLong(filetimestamp);

      			long c = 1797000;
      			
      			if ((pls_ts >= ts) & (pls_ts - ts) <= c) {
      			
      			  BufferedReader br = new BufferedReader(new FileReader((files[i])));
                    while((line = br.readLine())!=null){
      	      
                  	  String[] riga = line.split(",");
      					String username_i = riga[0];
      					int imsi = Integer.parseInt(riga[1]);
      					long cell_i = Long.parseLong(riga[2]);
      					long tempo = Long.parseLong(riga[3]);
      					double pls_lat = Double.parseDouble(riga[4]);
      					double pls_lon = Double.parseDouble(riga[5]);
      					double r = Integer.parseInt(riga[6]);

      					long c1 = 600000;

      					if ((ts - tempo <= c1) && (ts - tempo >= -c1)) {
      					  if(cellac == cell_i){
      						  list.add(new PLS(username_i, imsi, cell_i, tempo, pls_lat, pls_lon, r));
      					  }
      					}
      			  
      			  }br.close();
                 }
               }
             }
      	 }catch( Exception ex){
      	    	ex.printStackTrace(); 
            }
      	
      	return list;
      }
}