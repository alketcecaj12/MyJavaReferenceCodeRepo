package visualize;

import java.util.ArrayList;
import java.util.List;

public class PLSValues {

	public String user;
	List<Double>val;
	
	public PLSValues(String u){
		val = new ArrayList<Double>();
	}
	
	public void print(){
		String us = user;
		StringBuffer sb = new StringBuffer();
		sb.append(us);
		for (int i = 0; i < val.size(); i++) {
			
			sb.append(","+val.get(i).toString());
		}
		System.out.println(sb);
	}
}
