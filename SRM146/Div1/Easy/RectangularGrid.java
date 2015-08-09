import java.util.*;

public class RectangularGrid {
    public long countRectangles(int width, int height) {
    	long res = (long)(width+1)*width/2 * (long)(height+1)*height/2;
    	int n = Math.min(width,height);
    	for (int i=1; i<=n; i++) {
    		res -= (width-i+1)*(height-i+1);
    	}
    	return res;
    }
}