package alg.ordinamento;

public class MaxMin {
	
	public static int max(int[] t) {
	    int maximum = t[0];   // start with the first value
	    for (int i=1; i<t.length; i++) {
	        if (t[i] > maximum) {
	            maximum = t[i];   // new maximum
	        }
	    }
	    return maximum;
	}//end method max
	
	public static int min(int[] t) {
	    int minimum = t[0];   // start with the first value
	    for (int i=1; i<t.length; i++) {
	        if (t[i] < minimum) {
	            minimum = t[i];   // new maximum
	        }
	    }
	    return minimum;
	}//end method max

	
	public static void main (String [] args){
		
		int [] d = {20, 1, 0, 13, 2, 9, 11, 112, 34, 56};
		
		System.out.println(max(d));
		System.out.println(min(d));
	}

}
