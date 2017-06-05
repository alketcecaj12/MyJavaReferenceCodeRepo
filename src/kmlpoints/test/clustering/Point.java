package kmlpoints.test.clustering;

import java.util.ArrayList;

public class Point {

public double x = 0; 
public double y = 0;


public Point(double asc, double ord){
	this.x = asc;
	this.y = ord;
}



public String toString(){
	return "("+x+", "+y+")";
}

}
