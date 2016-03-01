import java.io.*;
import java.util.*;

public class MoveStones {
	public long get(int[] a, int[] b) {
		int n = a.length;
		long ans = Long.MAX_VALUE;
		for (int i=0; i<n; i++) {
			long sum = 0;
			long tmp = 0;
			for (int j=0; j<n; j++) {
				int k = (i+j)%n;
				sum += a[k] - b[k];
				tmp += Math.abs(sum);
			}
			if (sum != 0) return -1;
			ans = Math.min(ans, tmp);
		}
		return ans;
	}
}
