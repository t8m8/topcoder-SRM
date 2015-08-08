import java.util.*;

public class BinaryCode {
    public String[] decode(String message) {
    	return new String[]{decode(message,"00"), decode(message,"01")};
    }

    String decode(String message, String prefix) {
    	int n = message.length();
    	StringBuilder sb = new StringBuilder(prefix);
    	for (int i=0; i<n; i++) {
    		int x = message.charAt(i) - '0';
    		int y = (sb.charAt(i) - '0') + (sb.charAt(i+1) - '0');
    		if (x - y == 0) {
    			sb.append("0");
    		}else if (x - y == 1) {
    			sb.append("1");
    		}else {
    			return "NONE";
    		}
    	}

    	return sb.charAt(sb.length()-1) == '0' ? sb.toString().substring(1,sb.length()-1) : "NONE";
    }
}