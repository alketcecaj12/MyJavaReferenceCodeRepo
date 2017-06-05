
package project.utilityclasses;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;



public class ReadThroughFiles {

	public static void main (String [] args)throws Exception{
			
			 String inflow = "C:/DATA/data/PLS";
			 File Folder = new File(inflow);
			 File files[];
			 files = Folder.listFiles();
			
			try{
		        String line = "";

	            if(files.length>1)
	            {
	             for(int i = 0;i<files.length; i++){
	               
	               BufferedReader br = new BufferedReader(new FileReader(files[i]));
	               System.out.print("reading...");
	               System.out.println(files[i].getName()+", number: "+i);
	               String filefullname = files[i].getName();
	               String filetimestamp = filefullname.substring(filefullname.indexOf("_")+1,
	            		   filefullname.indexOf("."));
	               
	               System.out.println("la parte che mi serve è : "+filetimestamp);
	               long ts = Long.parseLong(filetimestamp);
	               
	              
	                while((line = br.readLine())!=null){
			      
					  String [] riga = line.split("\t");
					  
					  }
			          br.close();
	            }
	           }
			 }catch( Exception ex){
			    	ex.printStackTrace(); 
		  }
		}
		
	/*public static List<PLSUser> findPLSUser(FlickrUser f){
		List<PLSUser> plsuser = new ArrayList<PLSUser>();

		 String inflow = "C:/DATA/data/PLS";
		 File Folder = new File(inflow);
		 File files[];
		 files = Folder.listFiles();
		
		try{
	        String line = "";

           if(files.length>1)
           {
            for(int i = 0;i<files.length; i++){
             
               System.out.print("reading...");
               System.out.println(files[i].getName()+", number: "+i);
              String filefullname = files[i].getName();
              String [] filename = filefullname.split("_");
              System.out.println("la parte che mi serve è : "+filename[1]);
              long ts = Long.parseLong(filename[1]);
              long c = -600000L;
              long ftsp = f.getFUTimestamp(f);
              if(ftsp < ts){
            	  
               BufferedReader br = new BufferedReader(new FileReader(files[i]));
              
               while((line = br.readLine())!=null){
		      
				  String [] riga = line.split("\t");
				  
				  String username = riga[0];
				  int imsi = Integer.parseInt(riga[1]);
				  int celllac = Integer.parseInt(riga[2]);
				  long time = Long.parseLong(riga[3]);
				  if(time == ts){
					  plsuser.add(new PLSUser(username, imsi, celllac,time));
				  }
				  
				  
				  }
		          br.close();
		       
              }
              
           }
          }
		 }catch( Exception ex){
		    	ex.printStackTrace(); 
	  }
		return plsuser;
	}
	
	*/
	
	}
	
	
