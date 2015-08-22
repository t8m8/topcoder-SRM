import java.util.*;

public class InterestingDigits {
    public int[] digits(int base) {
		int[] ret = new int[base];
		int ptr = 0;

		int stop = (int)Math.pow(base,3);

		for (int i=2; i<base; i++) {
			boolean flag = true;
			for (int j=i; j<stop; j+=i) {
				if ((j/(base*base)+j%(base*base)/base+j%base)%i != 0) {
					flag = false;
					break;
				}
			}
			if (flag) ret[ptr++] = i;
		}

    	return Arrays.copyOfRange(ret,0,ptr);
    }
}
