import java.util.*;

public class DevuAndGame {
	public String canWin(int[] nextLevel) {
		int n = nextLevel.length;
		boolean[] f = new boolean[n];
		int cur = 0;
		while (true) {
			if (nextLevel[cur] == -1) return "Win";
			if (f[cur]) return "Lose";
			f[cur] = true;
			cur = nextLevel[cur];
		}
	}
}