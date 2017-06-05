package multidimscaling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class LoadData {

	
	public static void loadData(List<double[]> d, String file) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line = "";
		double [] d1 = null;
		while((line = br.readLine())!= null){
			
			String [] s = line.split(" ");
			d1 = new double[s.length];
			
			for (int i = 0; i < s.length; i++) {
				
				d1[i] = Double.parseDouble(s[i]);
				System.out.println(d1[i]);
			}
			d.add(d1);
			
		}
		br.close();
	}
	
	
}
