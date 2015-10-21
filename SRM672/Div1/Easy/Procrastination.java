import java.util.*;

public class Procrastination {
    public long findFinalAssignee(long n) {

    	long lb = n-1, ub = n;

    	while (!isPrime(lb)) lb--;
    	while (!isPrime(ub)) ub++;

    	TreeSet<Long> set = new TreeSet<Long>();

    	for (long i=lb+1; i<=ub; i++) {
    		for (long j=2; j*j<=i; j++) {
    			if (i%j == 0) {
    				set.add(j);
    				set.add(i/j);
    			}
    		}
    	}

    	for (long div : set) {
    		if (n%div == 0) {
    			n++;
    		} else if (n%div == 1) {
    			n--;
    		}
    	}

    	return n;
    }

    boolean isPrime(long n) {
    	for (long i=2; i*i<=n; i++) {
    		if (n%i == 0) return false;
    	}
    	return true;
    }
}