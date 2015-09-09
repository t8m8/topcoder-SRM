import java.util.*;

public class FixImage {

	DisjointSet ds;
	char[][] pixel;
	int h, w;
	int[] dy = {0,1,0,-1};
	int[] dx = {1,0,-1,0};


	public String[] originalImage(String[] alteredImage) {
		h = alteredImage.length;
		w = alteredImage[0].length();

		pixel = new char[h][];
		for (int i=0; i<h; i++) pixel[i] = alteredImage[i].toCharArray();

		ds = new DisjointSet(h*w);

		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {

				if (pixel[i][j] == '.') continue;

				for (int k=0; k<2; k++) {
					int ni = i + dy[k];
					int nj = j + dx[k];
					if (ni < 0 || nj < 0 || h <= ni || w <= nj || pixel[ni][nj] == '.') continue;

					ds.unite(i*w+j, ni*w+nj);
				}
			}
		}

		boolean flag = true;

		while (flag) {
			flag = false;
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					if (pixel[i][j] == '.') continue;

					for (int k=0; k<4; k++) {
						int ni = i + dy[k];
						int nj = j + dx[k];
						if (ni < 0 || nj < 0 || h <= ni || w <= nj) continue;
						if (pixel[ni][nj] == '#') continue;
						if (check(ni, nj, dy[k], dx[k], i*w+j)) {
							flag = true;
						}
					}
				}
			}
		}

		String[] res = new String[h];
		for (int i=0; i<h; i++) {
			res[i] = new String(pixel[i]);
		}
		return res;
	}


	boolean check(int i, int j, int di, int dj, int s) {
		if (i < 0 || j < 0 || h <= i || w <= j) return false;
		if (pixel[i][j] == '#') {
			if (ds.same(s, i*w+j)) return true;
			else return false;
		}

		if (check(i+di, j+dj, di, dj, s)) {
			pixel[i][j] = '#';
			for (int k=0; k<4; k++) {
				int ni = i + dy[k];
				int nj = j + dx[k];
				if (ni < 0 || nj < 0 || h <= ni || w <= nj || pixel[ni][nj] == '.') continue;
				ds.unite(i*w+j, ni*w+nj);
			}
			return true;
		}
		return false;
	}

	class DisjointSet {
		int[] data;

		public DisjointSet(int n){
			data = new int[n];
			for (int i=0; i<n; i++) data[i] = i;
		}

		public int find(int x){
			if(data[x] == x) return x;
			return data[x] = find(data[x]);
		}

		public boolean same(int x,int y){
			return find(x) == find(y);
		}

		public void unite(int x,int y){
			if (find(x) == find(y)) return;
			data[find(x)] = find(y);
		}

		public String toString() {
			return Arrays.toString(data);
		}
	}
}