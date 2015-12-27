import java.io.*;
import java.util.*;

public class DoubleOrOneEasy {
	public int minimalSteps(int a, int b, int newA, int newB) {
		if (a > newA || b > newB) return -1;

		int cnt = 0, ans = Integer.MAX_VALUE;
		while (true) {
			if (newA < a || newB < b) break;
			if (newA - a == newB - b) ans = Math.min(ans, (cnt + newA - a));
			if (newA%2 != newB%2) break;

			if (newA%2 == 0) {
				newA /= 2;
				newB /= 2;
			} else {
				newA--;
				newB--;
			}

			cnt++;
		}

		return ans == Integer.MAX_VALUE ? -1 : ans;
	}
}
