import java.io.*;
import java.util.*;

public class BracketSequenceDiv1 {

	long[][] dp;
	char[] t;

	long dfs(int l, int r) {
		if (dp[l][r] >= 0) return dp[l][r];
		if (l == r) return dp[l][r] = 1;
		long res = dfs(l+1, r);
		for (int i=l+1; i<r; i++) {
			if (t[l] == '(' && t[i] == ')') {
				res += dfs(l+1, i) * dfs(i+1, r);
			}

			if (t[l] == '[' && t[i] == ']') {
				res += dfs(l+1, i) * dfs(i+1, r);
			}
		}
		return dp[l][r] = res;
	}

	public long count(String s) {
		int n = s.length();
		t = s.toCharArray();
		dp = new long[n+2][n+2];
		for (int i=0; i<=n; i++) {
			Arrays.fill(dp[i], -1);
		}
		return dfs(0, n) - 1;
	}

	void dump(Object... o) {System.out.println(Arrays.deepToString(o)); }
}
