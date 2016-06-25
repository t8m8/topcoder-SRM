import java.io.*;
import java.util.*;

public class BiconnectedDiv1 {
	public int minimize(int[] w1, int[] w2) {
		int n = w1.length + 1;

		int sum = 0;
		for (int i=0; i<w1.length; i++) {
			sum += w1[i];
		}
		for (int i=0; i<w2.length; i++) {
			sum += w2[i];
		}

		int[][] dp = new int[n][2];
		int max = 0;
		for (int i=2; i<n-1; i++) {
			dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
			dp[i][1] = Math.max(dp[i-1][0] + Math.max(w1[i-1], w2[i-2]), dp[i-1][1] + w1[i-1]);
			if (i == 2) {
				dp[i][1] = w1[i-1];
			}
			max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
		}

		return sum - max;
	}
}
