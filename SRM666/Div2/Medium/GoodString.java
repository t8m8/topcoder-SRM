import java.util.*;

public class GoodString {
    public String isGood(String s) {
    	int n = s.length();
    	int cnt = 0;
    	for (int i=0; i<n; i++) {
    		if (s.charAt(i) == 'a') cnt++;
    		else cnt--;
    		if (cnt < 0) return "Bad";
    	}
    	return cnt == 0 ? "Good" : "Bad";
    }
}