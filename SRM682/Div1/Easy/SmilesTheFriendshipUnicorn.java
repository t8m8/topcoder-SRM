import java.io.*;
import java.util.*;

public class SmilesTheFriendshipUnicorn {

	BitSet visited = new BitSet();
	// BitSet start = new BitSet();
	int[][] g;

	int[][] undirectedGraph(int n, int[] v1, int[] v2) {
		int[] cnt = new int[n];
		for (int i : v1) cnt[i]++;
		for (int i : v2) cnt[i]++;

		int[][] g = new int[n][];
		for (int i=0; i<n; i++) g[i] = new int[cnt[i]];
		for (int i=0; i<v1.length; i++) {
			int from = v1[i];
			int to = v2[i];
			g[from][--cnt[from]] = to;
			g[to][--cnt[to]] = from;
		}

		return g;
	}

	boolean rec(int cur, int prev, int cnt) {
		// dump(visited);
		visited.set(cur);
		if (cnt == 5) return true;

		for (int to : g[cur]) {
			if (to == prev) continue;
			if (!visited.get(to) && rec(to, cur, cnt+1)) return true;
		}
		visited.set(cur, false);

		return false;
	}

	public String hasFriendshipChain(int N, int[] A, int[] B) {
		g = undirectedGraph(N, A, B);
		// dump(g);
		for (int i=0; i<N; i++) {
			if (rec(i, -1, 1)) return "Yay!";
			visited.clear();
		}
		return ":(";
	}

	static void dump(Object... o) { System.err.println(Arrays.deepToString(o)); }
}
