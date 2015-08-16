import java.util.*;

public class BigBurger {
    public int maxWait(int[] arrival, int[] service) {
    	int n = arrival.length;
    	int t = arrival[0];
    	int max = -1;
    	for (int i=0; i<n; i++) {
    		max = Math.max(max,t-arrival[i]);
    		if (t < arrival[i]) t = arrival[i];
    		t += service[i];
    	}
    	return max;
    }
}