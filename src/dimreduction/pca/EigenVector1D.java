package dimreduction.pca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mds.collaborativefiltering.Recensione;
import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class EigenVector1D {

public static void main (String[] args)throws Exception{
		
		Map<String, List<Recensione>> map2 = new HashMap<String, List<Recensione>>();
		mds.collaborativefiltering.LoadData.loadRData("data/eigendata.txt", map2);
        double[][] orig_data = new double[map2.size()][4];
        
        int index = 0;
        for(String k:map2.keySet()) {
        	//System.out.println(k);
        	List<Recensione> lr = map2.get(k);
        	for(int j=0;j<lr.size();j++)
        		orig_data[index][j] = lr.get(j).voto;
        	index++;
        }
        
        System.out.println("original data");
        Matrix od = new Matrix(orig_data);
        od.print(od.getColumnDimension(),2);
     
		
		Matrix mm = od.transpose().times(od);
		EigenvalueDecomposition e = new EigenvalueDecomposition(mm);
		Matrix avet = e.getV();
		Matrix aval = e.getD();
		System.out.println("matrice autovettori : ");
		avet.print(avet.getColumnDimension(), 2);
		System.out.println("matrice autovalori : ");
		aval.print(aval.getColumnDimension(), 2);
		
		//int how_many_autovettori = 2;
		Matrix max = avet.getMatrix(0, avet.getRowDimension()-1, 
				avet.getColumnDimension()-1, avet.getColumnDimension()-1);
		System.out.println("max autovettori : ");
		max.print(max.getColumnDimension(), 2);
		Matrix proj = od.times(max);
		System.out.println("proiezioni  : ");
		proj.print(proj.getColumnDimension(), 2);	
	}
}
