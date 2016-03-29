import java.io.*;
import java.util.*;

public class SegmentsAndPoints {
	public String isPossible(int[] p, int[] l, int[] r) {
		int n = p.length;
		ArrayList<int[]> a = new ArrayList<>();
		for (int i=0; i<n; i++) {
			p[i] += 500;
			l[i] += 500;
			r[i] += 500;
			a.add(new int[]{l[i], r[i]});
		}

		Collections.sort(a,new Comparator<int[]>(){
			public int compare(int[] a, int[] b) {
				if (a[1] != b[1]) return a[1] - b[1];
				return b[0] - a[0];
			}
		});
		Arrays.sort(p);
		BitSet used = new BitSet();
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				int[] q = a.get(j);
				if (!used.get(j) && q[0] <= p[i] && p[i] <= q[1]) {
					used.set(j, true);
					break;
				}
			}
		}

		return used.cardinality() == n ? "Possible" : "Impossible";
	}

	void dump(Object... o) {System.err.println(Arrays.deepToString(o)); }

}
