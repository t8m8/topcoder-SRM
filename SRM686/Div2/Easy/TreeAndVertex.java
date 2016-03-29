import java.io.*;
import java.util.*;

public class TreeAndVertex {
	public int get(int[] tree) {
		int n = tree.length;
		int[] cnt = new int[n+1];
		for (int i=0; i<n; i++) {
			cnt[i+1]++;
			cnt[tree[i]]++;
		}

		int max = 0;
		for (int i=0; i<=n; i++) {
			max = Math.max(max, cnt[i]);
		}

		return max;
	}

	void dump(Object... o) {System.err.println(Arrays.deepToString(o)); }
}
