import java.util.*;

public class ReversalChain {

	final int INF = Integer.MAX_VALUE/2;

	public int minReversal(String init, String goal) {
		int n = init.length();

		int[][][][] dp = new int[n+1][n][n][2];

		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				for (int k=0; k<2; k++) {
					if (init.charAt(i) != goal.charAt(j))
						dp[1][i][j][k] = INF;
				}
			}
		}

		for (int len=2; len<=n; len++) {
			for (int i=0; i<=n-len; i++) {
				for (int j=0; j<=n-len; j++) {
					for (int k=0; k<2; k++) {
						int a = init.charAt(i) != goal.charAt(j) ? INF : dp[len-1][i+1][j+1][0] + k;
						int b = init.charAt(i+len-1) != goal.charAt(j+len-1) ? INF : dp[len-1][i][j][0] + k;
						int c = init.charAt(i) != goal.charAt(j+len-1) ? INF : dp[len-1][i+1][j][1] + (k+1)%2;
						int d = init.charAt(i+len-1) != goal.charAt(j) ? INF : dp[len-1][i][j+1][1] + (k+1)%2;

						dp[len][i][j][k] = Math.min(Math.min(a,b),Math.min(c,d));
					}
				}
			}
		}

		return dp[n][0][0][0] == INF ? -1 : dp[n][0][0][0];
	}
}