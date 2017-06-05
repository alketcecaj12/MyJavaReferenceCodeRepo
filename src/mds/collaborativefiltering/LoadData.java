package mds.collaborativefiltering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class LoadData {

	public static void loadRData(String file, Map<String, List<Recensione>> map) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));	
		List<Recensione>p_arr = null;
		String line = null;
		
		while((line=br.readLine())!= null){
		 String [] s = line.split("   ");
		
		 String name = s[0];
		
		 p_arr = new ArrayList<Recensione>();
		
		 String [] s2 = s[1].split(",");
		
          for (int i = 0; i < s2.length; i++) {
			String [] s3 = s2[i].split(":");
			String p = s3[0];
			double d = Double.parseDouble(s3[1]);
			p_arr.add(new Recensione(p, d));
		  }
		  map.put(name, p_arr);
	   }br.close();
	}
	/*
    public static void loadPData(String file, Map<String, List<Person>> map) throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));	
		//List<Person>p_arr = null;
		String line = null;
		
		 while((line=br.readLine())!= null){
		   String [] s = line.split("   ");
		
		   String name = s[0];
		
		   p_arr = new ArrayList<Person>();
		
		   String [] s2 = s[1].split(",");
		
		
           for (int i = 0; i < s2.length; i++) {
			
			 String [] s3 = s2[i].split(":");
			 String p = s3[0];
			 double d = Double.parseDouble(s3[1]);
			 p_arr.add(new Person(p, d));
		   }
		   map.put(name, p_arr);
	     
		 }br.close();
	}
	*/
    public static List<String> loadMovies(String id){
	 
		List<String> movie = new ArrayList<String>();
	    try
		    {
		      String myDriver = "org.gjt.mm.mysql.Driver";
		      String myUrl = "jdbc:mysql://localhost/recensioni";
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, "root", "SS7IAZ_*");
		      
		      Statement st = conn.createStatement();
		      
			  ResultSet rs = st.executeQuery("SELECT* FROM movierate WHERE userid = "+id+"");
		      
		      System.out.println("got connection for userid "+id);
		    
		         String movieid = "";
		         while (rs.next()) {
		    	   
		    	    movieid = rs.getString("movieid");
		           movie.add(movieid);
		    	 }   
		}
	    catch(Exception e){
			 System.out.println("errore"+e);
	    }
	    return movie;
    }
    
    public static List<String> mustSee(String c, Map<String, List<Recensione>>map)throws Exception{
	 
	 List<String> mustsee = new ArrayList<String>();
	 List<String> allmovies = new ArrayList<String>();
	 loadData(allmovies);
	 List<String> critic = loadMovies(c);
	 int k = 0;
	 for(int i = 0; i < critic.size(); i++){
	     for(int j = 0; j < allmovies.size(); j++){
			 if(allmovies.get(j).equals(critic.get(i))){			  
				 System.out.println(k+" Tolgo "+allmovies.get(j));
				 k++;
				 allmovies.remove(allmovies.get(j));	 
		     } 
		 }
	 }
	 mustsee = allmovies;
	 return mustsee;
    }

    public static void loadData(List<String>movieid) throws Exception{
	 
	  BufferedReader br = new BufferedReader(new FileReader(new File("data/moviefile.txt")));
	  String line;
	  while((line = br.readLine())!=null){
		 
		 String s = line;
		 movieid.add(s);
	  }
	  br.close(); 
}

     public static void loadFile(String file, Map<String, List<Recensione>>map)throws Exception{
	   List<Recensione>rec ;
	   BufferedReader br = new BufferedReader(new FileReader(new File(file)));
	   String userid = "";
	   String movieid = "";
	   double rate = 0;
	   String line;
	   while((line = br.readLine())!=null){
		 
		  rec = new ArrayList<Recensione>();
		  String [] s = line.split(" = ");
		  userid = s[0];
		 
		  String[] s2 = s[1].split(" ");
		 
		 for(String str: s2){
		  String [] s3 = str.split(",");
			 movieid = s3[0];
			 rate = Double.parseDouble(s3[1]);
			 rec.add(new Recensione(movieid, rate));
		 }
		 map.put(userid, rec);
	   }
	   br.close();	
     }  
}
