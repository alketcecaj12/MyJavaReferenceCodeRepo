package collaborativefiltering.istanza2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoadSimpleData {

	public static void loadData(String file, Map<String, List<Recensione>>map) throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		List<Recensione> lr = null;
		String line = "";
		double score = 0; 
		String film= "";
		
		while((line = br.readLine())!= null){
			
			lr = new ArrayList<Recensione>();
			
			String [] riga = line.split(";");
			String name = riga[0];
			System.out.println(name);
			String [] substr1 = riga[1].split(" ");
			
			for (int i = 0; i < substr1.length; i++) {
				String [] substr2 = substr1[i].split(",");
				film = substr2[0];
				score = Double.parseDouble(substr2[1]);
				lr.add(new Recensione(film, score));
			}
			map.put(name, lr);
		}
		br.close();
		
	}
	

	 public static void loadDataDB(String file) throws Exception{
		    
	    	BufferedReader br = new BufferedReader(new FileReader(new File(file)));
	    	
	         String line = null;
			double score = 0; 
	         try
			    {
			      String myDriver = "org.gjt.mm.mysql.Driver";
			      String myUrl = "jdbc:mysql://localhost/recensioni";
			      Class.forName(myDriver);
			      Connection conn = DriverManager.getConnection(myUrl, "root", "SS7IAZ_*");
			      
			      Statement st = conn.createStatement();
			
			   while((line=br.readLine())!= null){
		          
				 String [] riga = line.split(";");
					String name = riga[0];
					System.out.println(name);
					String [] substr1 = riga[1].split(" ");
					
					for (int i = 0; i < substr1.length; i++) {
						String [] substr2 = substr1[i].split(",");
						String film = substr2[0];
						score = Double.parseDouble(substr2[1]);
						System.out.println(film);
						System.out.println(score);
						
						st.executeUpdate("INSERT INTO movierate2 (nome, movie, score) "
						          +"VALUES ('"+name+"', '"+film+"', '"+score+"')");
			         }
			    }
			   br.close();
			
			 } 
	         catch (Exception e) {
				System.out.println(" error "+e.getMessage());
			 }  
	    	
	    }
	 
	 public static List<String> mustSee(String c, Map<String, List<Recensione>>map)throws Exception{
		 
		 List<String> mustsee = new ArrayList<String>();
		 List<String> allmovies = new ArrayList<String>();
		 loadAllData(allmovies);
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

	    public static void loadAllData(List<String>movieid) throws Exception{
		 
		  BufferedReader br = new BufferedReader(new FileReader(new File("data/movieList2.txt")));
		  String line;
		  while((line = br.readLine())!=null){
			 
			 String s = line;
			 movieid.add(s);
		  }
		  br.close(); 
	}
	
	 
	    public static List<String> loadMovies(String id){
	   	 
			List<String> movie = new ArrayList<String>();
		    try
			    {
			      String myDriver = "org.gjt.mm.mysql.Driver";
			      String myUrl = "jdbc:mysql://localhost/recensioni";
			      Class.forName(myDriver);
			      Connection conn = DriverManager.getConnection(myUrl, "root", "SS7IAZ_*");
			      
			      Statement st = conn.createStatement();
			      
				  ResultSet rs = st.executeQuery("SELECT* FROM movierate2 WHERE nome = '"+id+"'");
			      
			      System.out.println("got connection for userid "+id);
			    
			         String movieid = "";
			         while (rs.next()) {
			    	   
			    	    movieid = rs.getString("movie");
			           movie.add(movieid);
			    	 }   
			}
		    catch(Exception e){
				 System.out.println("errore"+e);
		    }
		    return movie;
	    }
	    
	
	    public static void main (String [] args){
	    List<String>movies = new ArrayList<String>();
	    
	    movies = loadMovies("Lisa");
	    
	    for (int i = 0; i < movies.size(); i++) {
			System.out.println(movies.get(i));
		}
	    	
		}

}
