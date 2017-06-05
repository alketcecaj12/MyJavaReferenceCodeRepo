package dimreduction.pca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class BlogEigenVector {

	public static void main (String [] args) throws Exception{
	
	Map<String, List<Parola>>map = new HashMap<String, List<Parola>>();
    
    ReadBlogdata.loadData("data/eigenval4clustering.txt", map);
    double[][] data = new double[map.size()][3];
	
    int index = 0;
    for(String k:map.keySet()) {
    	//System.out.println(k);
    	List<Parola> lr = map.get(k);
    	for(int j=0;j<lr.size();j++)
    		data[index][j] = lr.get(j).freq;
    	index++;
    }
	Matrix m = new Matrix(data);
	System.out.println("stampa matrice");
	m.print(0, 2);
	Matrix mt = m.transpose();
	
	Matrix prod = m.times(mt);
	
	EigenvalueDecomposition e = new EigenvalueDecomposition(prod);
	
	Matrix eigenval = e.getD();
	Matrix eigenvect = e.getV();
	
	}
	
	
}
