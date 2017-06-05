package alg.test.clustering;

import java.util.ArrayList;

public class Point {

double x = 0; 
double y = 0;


public Point(double asc, double ord){
	this.x = asc;
	this.y = ord;
}



public String toString(){
	return "("+x+", "+y+")";
}

}
