import java.util.*;

public class Bonuses {
    public int[] getDivision(int[] points) {
    	int n = points.length;
   		int pool = 0;
   		for (int i=0; i<n; i++) {
   			pool += points[i];
   		} 

   		int[] res = new int[n];
   		int sum = 0;
   		for (int i=0; i<n; i++) {
   			res[i] = (int)((double)points[i]/pool*100);
   			sum += res[i];
   		}

   		int extra = 100 - sum;
   		boolean[] f = new boolean[n];
   		while (extra > 0) {
   			int idx = -1, max = -1;
   			for (int i=0; i<n; i++) {
   				if (max < points[i] && !f[i]) {
   					idx = i;
   					max = points[i];
   				}
   			}

   			f[idx] = true;
   			res[idx]++;
   			extra--;
   		}

   		return res;
    }
}