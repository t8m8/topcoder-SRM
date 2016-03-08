import java.io.*;
import java.util.*;

public class CliqueParty {

	public int maxsize(int[] a, int k) {
		int n = a.length;

		Arrays.sort(a);
		int ans = 0;

		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				long b = a[j] - a[i];
				int cnt = 1;
				int cur = a[i];
				for (int x=i; x<=j; x++) {
					if (b <= (long)k*(a[x] - cur)) {
						cnt++;
						cur = a[x];
					}
				}

				ans = Math.max(ans, cnt);
			}
		}

		return ans;
	}
}
