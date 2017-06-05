package MatchingPack;

import org.gps.utils.LatLonPoint;

public interface DataI {
	public LatLonPoint getLatLonPoint();
	public long getTimestamp();
	public boolean match(DataI other, double ds, long dt);
	public boolean exclude(DataI other, double ds, long dt);
	public String getUser();
}
