package alg.ordinamento;

public class BubbleSort {

	
	public static int[] bubbleSort(int[] data){
		int lenD = data.length;
		int tmp = 0;
		  for(int i = 0;i<lenD;i++){
		    for(int j = (lenD-1);j>=(i+1);j--){
		      if(data[j]<data[j-1]){
		        tmp = data[j];
		        data[j]=data[j-1];
		        data[j-1]=tmp;
	           }   
		    }
          }
		
		  return data;
		
		}

	public static void print(int [] d){
		for (int i = 0; i < d.length; i++){
			System.out.print(d[i]+",");
		}		
    }
			
	public static void main (String[] args){
		
		int[] d = {1, 4, 2, 6, 12, 3, 7, 0, 9, 13};
		bubbleSort(d);
		print(d);
	}
}
