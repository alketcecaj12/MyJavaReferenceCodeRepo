package matchingD4D;

public class FO {
	
	public String h1;
	public String h2;
	public int freq;
	
	public FO(String h1, String h2, int f){
		this.h1 = h1;
		this.h2 = h2;
		this.freq = f;
	}
    
	
	public String toString(){
		return h1+","+h2+","+freq;
	}
}
