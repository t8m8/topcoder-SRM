import java.util.*;

public class PeopleYouMayKnow {

    public int maximalScore(String[] friends, int person1, int person2) {
    	int n = friends.length;

    	char[][] link = new char[n][];
    	for (int i=0; i<n; i++) link[i] = friends[i].toCharArray();

    	int cnt = 0;
    	for (int i=0; i<n; i++) {
    		if (link[i][person1] == 'Y' && link[i][person2] == 'Y') {
    			cnt++;
    			for (int j=0; j<n; j++) {
    				link[i][j] = link[j][i] = 'N';
    			}
    		}
    	}

    	DirectedGraph g = new DirectedGraph(n);

    	int[] level = new int[n];
    	for (int i=0; i<n; i++) {
    		if (i == person1) level[i] = 1;
    		else if (link[person1][i] == 'Y') level[i] = 2;
    		else if (link[person2][i] == 'Y') level[i] = 3;
    		else if (i == person2) level[i] = 4;
    	}

    	for (int i=0; i<n; i++) {
    		for (int j=0; j<n; j++) {
    			if (link[i][j] == 'Y' && level[i] != 0 && level[j] != 0 && level[i] < level[j]) {
    				g.addLink(i,j,1);
    			}
    		}
    	}

    	return requireMaxFlow(g,person1,person2) + cnt;
    }

	class DirectedGraph {

		public static final int INF = Integer.MAX_VALUE/2;

		public final int n;
		private ArrayList<ArrayList<int[]>> adjlist;

		public DirectedGraph(int n) {
			this.n = n;
			this.adjlist = new ArrayList<ArrayList<int[]>>();
			for (int i=0; i<n; i++) adjlist.add(new ArrayList<int[]>());
		}

		public void addLink(int from, int to) {
			addLink(from,to,0);
		}

		public void addLink(int from, int to, int c) {
			adjlist.get(from).add(new int[]{to,c});
		}

		public ArrayList<int[]> get(int v) {
			return adjlist.get(v);
		}

		public String toString() {
			StringBuilder res = new StringBuilder();
			for (int i=0; i<n; i++) {
				res.append(i).append(" ").append(Arrays.deepToString(adjlist.get(i).toArray())).append("\n");
			}
			return res.substring(0,res.length()-1);
		}
	}


    static int requireMaxFlow(DirectedGraph g, int source, int sink) {
		int n = g.n;
		int[] cnt = new int[n];

		for (int i=0; i<n; i++)
			for (int[] link : g.get(i))
				cnt[link[0]]++;

		int[][] rev = new int[n][];
		for (int i=0; i<n; i++) rev[i] = new int[cnt[i]];
		for (int i=n-1; i>=0; i--)
			for (int[] link : g.get(i))
				rev[link[0]][--cnt[link[0]]] = i;

		int[][] flow = new int[n][n];
		int[] level = new int[n];
		int[] path = new int[n];
		int res = 0;

		while (true) {
			Arrays.fill(level,-1);
			path[0] = source;
			int ptr = 1;
			level[source] = 0;

			for (int i=0; i<ptr; i++) {
				int cur = path[i];

				for (int[] link : g.get(cur)) {
					int next = link[0], cap = link[1];
					if (level[next] == -1 && cap - flow[cur][next] > 0) {
						path[ptr++] = next;
						level[next] = level[cur] + 1;
					}
				}

				for (int next : rev[cur]) {
					if (level[next] == -1 && -flow[cur][next] > 0) {
						path[ptr++] = next;
						level[next] = level[cur] + 1;
					}
				}
			}

			if (level[sink] == -1) break;
			int f = 0;
			while ((f = dfsMaxFlow(g,level,rev,flow,source,sink,Integer.MAX_VALUE/2)) > 0)
				res += f;
		}

		return res;
	}

	static int dfsMaxFlow(DirectedGraph g, int[] level, int[][] rev, int[][] flow, int cur, int sink, int min){
		if (cur == sink) return min;

		int left = min;

		for (int[] link : g.get(cur)) {
			int next = link[0], cap = link[1];
			if (level[next] == level[cur] + 1 && cap-flow[cur][next] > 0) {
				int f = dfsMaxFlow(g,level,rev,flow,next,sink,Math.min(left, cap-flow[cur][next]));
				if (f > 0) {
					flow[cur][next] += f;
					flow[next][cur] -= f;
					left -= f;
					if (left == 0) return min;
				}
			}
		}

		for (int next : rev[cur]) {
			if (level[next] == level[cur] + 1 && -flow[cur][next] > 0) {
				int f = dfsMaxFlow(g,level,rev,flow,next,sink,Math.min(left, -flow[cur][next]));
				if (f > 0) {
					flow[cur][next] += f;
					flow[next][cur] -= f;
					left -= f;
					if (left == 0) return min;
				}
			}
		}

		return min - left;
	}
}