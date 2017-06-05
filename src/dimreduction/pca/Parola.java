package dimreduction.pca;

public class Parola {

	String parola;
	double freq;
	public Parola(String p , double f){
		this.parola = p; 
		this.freq = f; 
	}
	
	
	public String toString(){
		return"parola: "+parola+" freq: "+freq;
	}
}
