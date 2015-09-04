import java.util.*;

public class CountPaths {

	static final int MOD = 1000007;

	public int[] difPaths(int r, int c, int[] fieldrow, int[] fieldcol) {
		int n = fieldrow.length;
		int[][] sf = new int[r][c];

		for (int i=0; i<n; i++) {
			sf[fieldrow[i]-1][fieldcol[i]-1] = i+1;
		}

		int[][][][] dp = new int[r][c][n+1][n+1];
		if (sf[0][0] == 0) dp[0][0][0][0] = 1;
		else dp[0][0][sf[0][0]][1] = 1;

		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				for (int k=0; k<=n; k++) {
					for (int l=0; l<=n; l++) {
						if (i+1 < r && sf[i+1][j] > k && l+1 <= n) {
							dp[i+1][j][sf[i+1][j]][l+1] = (dp[i+1][j][sf[i+1][j]][l+1] + dp[i][j][k][l])%MOD;
						} else if (i+1 < r && sf[i+1][j] == 0) {
							dp[i+1][j][k][l] = (dp[i+1][j][k][l] + dp[i][j][k][l])%MOD;
						}

						if (j+1 < c && sf[i][j+1] > k && l+1 <= n) {
							dp[i][j+1][sf[i][j+1]][l+1] = (dp[i][j+1][sf[i][j+1]][l+1] + dp[i][j][k][l])%MOD;
						} else if (j+1 < c && sf[i][j+1] == 0) {
							dp[i][j+1][k][l] = (dp[i][j+1][k][l] + dp[i][j][k][l])%MOD;
						}
					}
				}
			}
		}

		int[] res = new int[n+1];

		for (int i=0; i<=n; i++) {
			for (int j=0; j<=n; j++) {
				res[i] = (res[i] + dp[r-1][c-1][j][i])%MOD;
			}
		}

		return res;
	}
}