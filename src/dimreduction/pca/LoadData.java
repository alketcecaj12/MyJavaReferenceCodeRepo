package dimreduction.pca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LoadData {

	public static void loadData(String file, double[][]d)throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line = "";
		while((line = br.readLine())!=null){
			
			
		}
		
	}
	
	
	public static double[][] loadData(int row, int col, String file) throws Exception{
	BufferedReader br = new BufferedReader(new FileReader(new File(file)));
	double[][] d = new double[row][col];
	String line  ="";
	
	 int k = 0; 
	  while((line = br.readLine())!= null){
		int j =0;
		String [] s = line.split(",");
		
		  for (int i = 0; i < s.length; i++) {
		  	   d[k][j] = Double.parseDouble(s[i]);
		       j++;
		  }
		  k++;
	
	  }br.close();
	
	  return d ;
	}
	
	public static void main (String [] args)throws Exception{
		
		double[][] d = new double[3][3];
		
		d=loadData(3,3,"matrixdata.txt");
		
		for (int i = 0; i < d.length; i++) {
			for (int j = 0; j < d.length; j++) {
				System.out.print(d[i][j]+","); 
			}
			System.out.println("");
		}
		
		
	}
}
