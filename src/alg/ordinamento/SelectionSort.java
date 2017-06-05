package alg.ordinamento;

public class SelectionSort {

	public static int[] selectionSort(int[] data){
		
		  int lenD = data.length;
		  int j = 0;
		  int tmp = 0;
		  for(int i=0;i<lenD;i++){
		    j = i;
		     for(int k = i;k<lenD;k++){
		
		      if(data[j]>data[k]){
		
		        j = k;
		       }
		     }
		    tmp = data[i];
		    data[i] = data[j];
	        data[j] = tmp;
		  }
		  return data;
		}

	public static void print(int [] d){
for (int i = 0; i < d.length; i++){
	
	System.out.print(d[i]+",");
}		
		
	}
	
	public static void main (String[] args){
		
		int []d = {2, 4, 5, 6, 1, 0, 9, 11, 3, 14};
		
		selectionSort(d);
		print(d);
	}
	
	
}
