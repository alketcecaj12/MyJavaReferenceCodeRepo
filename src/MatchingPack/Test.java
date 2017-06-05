package MatchingPack;

import java.util.HashSet;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		PLS p1 = new PLS("marco", 3, 3, 3, 3, 3, 3);
		PLS p2 = new PLS("marco", 3, 3, 4, 3, 3, 3);
		Set<PLS> s = new HashSet<PLS>();
		s.add(p1);
		s.add(p2);
		System.out.println(s.size());
	}
}
