import java.util.*;

public class BinarySum {

	static final int INF = Integer.MAX_VALUE;

	public int rearrange(int a, int b, int c) {

		int n = Math.max(Integer.toBinaryString(a).length(),Math.max(Integer.toBinaryString(b).length(),Integer.toBinaryString(c).length()));

		int x = Integer.bitCount(a);
		int y = Integer.bitCount(b);
		int z = Integer.bitCount(c);

		int[][][][][] dp = new int[n+1][x+2][y+2][z+2][2];
		for (int i=0; i<=n; i++)
			for (int j=0; j<=x; j++)
				for (int k=0; k<=y; k++)
					for (int l=0; l<=z; l++)
						Arrays.fill(dp[i][j][k][l], INF);
		dp[0][0][0][0][0] = 0;

		for (int i=0; i<n; i++) {
			for (int j=0; j<=x; j++) {
				for (int k=0; k<=y; k++) {
					for (int l=0; l<=z; l++) {
						for (int ub=0; ub<2; ub++) {

							if (dp[i][j][k][l][ub] == INF) continue;

							for (int aBit=0; aBit<2; aBit++) {
								for (int bBit=0; bBit<2; bBit++) {
									int cBit = aBit + bBit + ub;

									dp[i+1][j+aBit][k+bBit][l+(cBit&1)][cBit>>1] = Math.min(dp[i+1][j+aBit][k+bBit][l+(cBit&1)][cBit>>1], dp[i][j][k][l][ub] + ((cBit&1)<<i));

								}
							}
						}
					}
				}
			}
		}

		return dp[n][x][y][z][0] == INF ? -1 : dp[n][x][y][z][0];
	}
}