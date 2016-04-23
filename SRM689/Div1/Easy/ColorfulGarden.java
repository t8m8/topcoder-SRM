import java.io.*;
import java.util.*;

public class ColorfulGarden {

	public String[] rearrange(String[] garden) {
		
		int n = garden[0].length();
		char[][] s = new char[2][n];
		s[0] = garden[0].toCharArray();
		s[1] = garden[1].toCharArray();

		int[][] cnt = new int[26][2];
		for (int i=0; i<n; i++) {
			for (int j=0; j<2; j++) {
				cnt[s[j][i] - 'a'][0]++;
				cnt[s[j][i] - 'a'][1] = s[j][i];
			}
		}

		for (int i=0; i<26; i++) {
			if (cnt[i][0] > n) return new String[0];
		}

		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();

		for (int i=0; i<n; i++) {
			Arrays.sort(cnt, new Comparator<int[]>(){
				public int compare(int[] a, int[] b) {
					return b[0] - a[0];
				}
			});

			cnt[0][0]--;
			cnt[1][0]--;

			char c1 = (char)cnt[0][1];
			char c2 = (char)cnt[1][1];
			if (i == 0 || a.charAt(i-1) == c1 || b.charAt(i-1) == c2) {
				a.append(c2);
				b.append(c1);
			} else {
				a.append(c1);
				b.append(c2);
			}
		}

		return new String[]{a.toString(), b.toString()};
	}
}
