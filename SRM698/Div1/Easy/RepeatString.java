import java.io.*;
import java.util.*;

public class RepeatString {

	static int levenshteinDistance(String s, String t) {
		int n = s.length();
		int m = t.length();
		int[][] dp = new int[n+1][m+1];
		for (int i=0; i<=n; i++) {
			for (int j=0; j<=m; j++) {
				if (i == 0 && j == 0) continue;
				dp[i][j] = Integer.MAX_VALUE/2;
				if (i > 0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + 1);
				if (j > 0) dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + 1);
				if (i > 0 && j > 0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1] + (s.charAt(i-1) == t.charAt(j-1) ? 0 : 1));
			}
		}
		return dp[n][m];
	}

	public int minimalModify(String s) {

		int n = s.length();
		int ans = n;
		for (int i=0; i<n; i++) {
			StringBuilder sb = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			sb.append(s.substring(0, i)).append(s.substring(0, i));
			sb2.append(s.substring(i, n)).append(s.substring(i, n));
			ans = Math.min(ans, Math.min(levenshteinDistance(s, sb.toString()), levenshteinDistance(s, sb2.toString())));
		}

		return ans;
	}
}
