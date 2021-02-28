package sds.graph1.P3176;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int max_road[][];
	static int min_road[][];
	static int parents[][];
	static int depths[];
	static ArrayList<Node> edges[];
	static int root = 1;
	static int LOG = 17; // 2^17 = 13xxxx...
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		
		// Initializing
		depths = new int[N + 1];
		edges = new ArrayList[N + 1];
		max_road = new int[N + 1][LOG + 1];
		min_road = new int[N + 1][LOG + 1];
		parents = new int[N + 1][LOG + 1];
		
		for(int i = 0; i <=N; i++) {
			edges[i] = new ArrayList<>();
			depths[i] = 100001;
		}
		depths[root] = 0;
		
		
		// Input Processing
		for(int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edges[a].add(new Node(b, c));
			edges[b].add(new Node(a, c));
		}
		
		setParents();
		
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			lca(d, e);
		}
		System.out.println(sb.toString());
		br.close();

		
	} // end of main
	
	// dfs�� ���� ����
	private static void findDepth(int index, int depth) {
		depths[index] = depth;
		
		for(int i = 0; i < edges[index].size(); i++) {
			Node next = edges[index].get(i);
			// �θ𿡼� ������ edges�� �����ϰ� ������ edges�� ó��
			if(depths[index] < depths[next.dest]) {
				parents[next.dest][0] = index;
				min_road[next.dest][0] = next.length;
				max_road[next.dest][0] = next.length;
				findDepth(next.dest, depth + 1);
			}
			
		}
	}
	
	// parents setting
	private static void setParents() {
		findDepth(root, 0);
		
		for(int i = 1; i <= LOG; i++) {
			for(int j = 1; j <= N; j++) {
				parents[j][i] = parents[parents[j][i-1]][i-1];
				// m**_road[i][j - 1] : i�� ������ 2 ^(j - 1)�� ��������� �ִ�/ ���� �Ÿ� 
				// m**_road[parents[i][j - 1]][j - 1] : i�� ������ 2 ^ (j - 1)�� ���� ��� ����, 2 ^ (j - 1)�� ���� �������� �Ÿ� 
				min_road[j][i] = Math.min(min_road[j][i - 1], min_road[parents[j][i - 1]][i - 1]);
				max_road[j][i] = Math.max(max_road[j][i - 1], max_road[parents[j][i - 1]][i - 1]);
			}
		}
	}
	
	private static void lca(int a, int b) {
		
		if(depths[a] > depths[b]) {
			// a, b swap
			int c = a;
			a = b;
			b = c;
		}
		
		int result_min = Integer.MAX_VALUE;
		int result_max = Integer.MIN_VALUE;
		
		// ���̰� ������ ������ �ö� 
		if(depths[a] != depths[b]) {
			for(int i = LOG; 0 <= i; i--) {
				// ���������� �������µ� ������ ū �������� ���� ������, ���� ����� �ŵ����� �������� �ö�
				if(depths[b] - depths[a] >= (1 << i)) {
					result_min = Math.min(result_min, min_road[b][i]);
					result_max = Math.max(result_max, max_road[b][i]);
					b = parents[b][i];
				}
			}
		}
		
		// �� ���� ������ �� ���� �ּ� ���� �����̾��ų�, �� ���� ���� ����� return
		if(a == b) {
			sb.append(result_min+" "+result_max+"\n");
			return;
		}
		
		// ������ ���� �Ž��� �ö󰡱�
		for(int i = LOG; 0 <= i; i--) {
			if(parents[a][i] != parents[b][i]) {
				result_max = Math.max(result_max, Math.max(max_road[a][i], max_road[b][i]));
				result_min = Math.min(result_min, Math.min(min_road[a][i] , min_road[b][i]));
				a = parents[a][i];
				b = parents[b][i];
			}
		}
		
		// parents[a][0] : lca
		result_max = Math.max(result_max, Math.max(max_road[a][0], max_road[b][0]));
		result_min = Math.min(result_min, Math.min(min_road[a][0] , min_road[b][0]));
		sb.append(result_min+" "+result_max+"\n");
	}
}
class Node{
	int dest, length;

	public Node(int dest, int length) {
		this.dest = dest;
		this.length = length;
	}
}
