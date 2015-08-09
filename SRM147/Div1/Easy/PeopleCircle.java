import java.util.*;

public class PeopleCircle {
    public String order(int numMales, int numFemales, int k) {
    	int n = numMales + numFemales;
    	char[] c = new char[n];
    	Arrays.fill(c,'M');

    	int idx = 0;
    	while (numFemales-- > 0) {
    		int cnt = 0;
    		while (cnt < k-1 || c[idx] == 'F') {
    			if (c[idx] == 'M') cnt++;
    			idx = (idx+1)%n;
    		}
    		c[idx] = 'F';
    	}

    	return new String(c);
    }
}
