package sds.graph1.P11438;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Integer> edges[];
	static int LOG = 17; // 2^17 == 131072 > 100000
	static int parents[][], depths[];
	static int root;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		edges = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			edges[i] = new ArrayList<>();
		}
		parents = new int[N+1][LOG+1];
		depths = new int[N+1];
		for(int i = 1; i <= N; i++) {
			depths[i] = 100001;
		}
		
		StringTokenizer st;
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edges[a].add(b);
			edges[b].add(a);
		}
		root = 1;
		setParents();
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(lca(a, b));
		}
		
	}
	
	private static void findDepth(int index, int depth) {
		depths[index] = depth;
		
		// index�� leaf node�̸� ���� 
		if(edges[index].size() == 1 && index != root) {
			return;
		}
		
		for(int i = 0; i < edges[index].size(); i++) {
			int next = edges[index].get(i);
			// �θ𿡼� ������ edge�� ó������ �ʴ´�.
			if(depths[index] < depths[next]) {
				parents[next][0] = index;
				findDepth(next, depth+1);
			}
		}
	}
	
	// 2^i��° �θ���� Ž���ϰ� ���� 
	private static void setParents() {
		findDepth(root,0);
		
		for(int i = 1; i <= LOG; i++) {
			for(int j = 1; j <= N; j++) {
				parents[j][i] = parents[parents[j][i-1]][i-1];
			}
		}
	}
	
	private static int lca(int a, int b) {
		// depth�� �ٸ��� �ݵ�� b�� ������ �����ϱ� ���� swap
		
		
		if(depths[a] > depths[b]) {
			int c = a;
			a = b;
			b = c;
			
		}
		// ���̰� ������ ������ �ö�
		if(depths[a] != depths[b]) {
			for(int i = LOG; 0 <= i; i--) {
				// ���������� �������µ� ������ ū�������� ���� ������
				if(depths[b] - depths[a] >= (1 << i)) {
					b = parents[b][i];
				}
			}
		}
		// �� ���� ������ �� ���� �ּҰ��������̾��ų�, �� ���� ���� ����� return 
		if(a == b) {
			return a;
		}
		
		// ������ ���� �Ž��� �ö󰡱�
		for(int i = LOG; 0 <= i; i--) {
			if(parents[a][i] != parents[b][i]) {
				a = parents[a][i];
				b = parents[b][i];
			}
		}
		
		return parents[a][0];
	}

}
