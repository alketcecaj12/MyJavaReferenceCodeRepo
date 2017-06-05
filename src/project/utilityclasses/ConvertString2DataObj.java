package project.utilityclasses;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.crypto.Data;

public class ConvertString2DataObj {

	public static void main (String [] args) throws Exception{
		
		String datastring = "08/03/12 11.01.36";
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH.mm.ss"); 
		Date date;
		date = df.parse(datastring);
		System.out.println("la data come oggetto "+date); 
		Timestamp ts = new Timestamp(date.getTime());
		long tsp = ts.getTime();
		System.out.println("la data come timestamp: "+tsp);
		
		Date date2 = new Date();
		long tsp2 = 1330945415501L;
		date2.setTime(tsp);
		//date2.setTime(tsp2);
		System.out.println("la date2 dopo conversione da tsp è "+date2);	
		
	}
	public static long convertDate2Tsp(String datastring) throws Exception{
		long tsp = 0; 
		DateFormat df = new SimpleDateFormat("dd/MM/yy hh.mm.ss"); 
		Date date;
		date = df.parse(datastring);
		//System.out.println("la data come oggetto "+date); 
		Timestamp ts = new Timestamp(date.getTime());
	    tsp = ts.getTime();
		System.out.println("la data come timestamp: "+tsp);
		return tsp;
	}
	public static String convertTsp2Date(long timestamp){
		
		Date data ;
		
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(timestamp);
		data = c.getTime();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yy hh.mm.ss");
		String datainstringa = new String(dateformat.format(data));
		return datainstringa;
		
	}
}
