import java.io.*;
import java.util.*;

public class BracketSequenceDiv2 {
	public int count(String s) {
		int n = s.length();
		long[][] dp = new long[n+1][n+1];

		for (int i=0; i<n; i++) {
			if (s.charAt(i) == '(') {
				dp[i][1] = 1;
				break;
			}
		}

		for (int i=0; i<n; i++) {
			for (int j=0; j<=n; j++) {
				int open = n;
				int close = n;
				for (int k=i+1; k<n; k++) {
					if (s.charAt(k) == '(') {
						open = k;
						break;
					}
				}
				for (int k=i+1; k<n; k++) {
					if (s.charAt(k) == ')') {
						close = k;
						break;
					}
				}
				if (open != n && j < n) {
					dp[open][j+1] += dp[i][j];
				}
				
				if (close != n && j > 0) {
					dp[close][j-1] += dp[i][j];
				}
			}
		}

		long ans = 0;

		for (int i=0; i<n; i++) {
			ans = (ans + dp[i][0])%1_000_000_007;
		}

		return (int)ans;
	}
}
