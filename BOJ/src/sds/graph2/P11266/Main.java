package sds.graph2.P11266;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int V, E;
	static int visit_order[];
	static ArrayList<Integer> adj[];
	static boolean ans[];	// ������ ���� ���
	static int ansCnt;		// ������ ����
	static int num;			// �湮 ����
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[V + 1];
		visit_order = new int[V + 1];
		ans = new boolean[V + 1];
		
		for(int i = 0; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a].add(b);
			adj[b].add(a);
		}
		
		// DFS�� ������ ã��
		for(int i = 1; i <= V; i++) {
			// �׷����� ��� ����Ǿ� �ִ� ���°� �ƴϱ� ������...
			if(visit_order[i] == 0) {
				// �����ϴ� ���� root
				dfs(0, i, true);
			}
		}
		
		// ������ ���� count 
		for(int i = 1; i <= V; i++) {
			if(ans[i]) ansCnt++; 
		}
		System.out.println(ansCnt);
		
		for(int i = 1; i <= V; i++) {
			if(ans[i]) {
				System.out.print(i + " ");
			}
		}
		
	} // end of main
	
	private static int dfs(int parent, int cur, boolean isRoot) {
		// 1. check in
		
		// ���� ��� �湮�� �� ���� ��쿡�� ����
		if(visit_order[cur] != 0) return 0;
		int min_visit_order = 10010;	// �ڽ� ���� �� ���� ���� �湮 ����
		int chlcnt = 0;		// �ڽ� ��� ����
		
		// �湮 ���� �Է�
		num++;
		visit_order[cur] = num;
		
		// 2. (�������� �����ߴ°�?)
		
		
		// 3. ����� �� ��ȸ
		for(int i = 0; i < adj[cur].size(); i++) {
			int nxt = adj[cur].get(i);
			
			// 4. �� �� �ִ°�
			// ������ �θ� ������ �����̸� �ǳʶ�
			if(nxt == parent) continue;		
			
			// ����� ��尡 �湮�� ���� �ְ�,
			if(visit_order[nxt] != 0) {
				// ���� ��庸�� ���� �湮�ߴٸ� �ڽ� �� �ּ� �湮 ������ update
				if(visit_order[nxt] < min_visit_order) {
					min_visit_order = visit_order[nxt];
				}
			}else {	// ����� ��尡 �湮�� �� ���� ��쿡�� �湮
				 
				// 5. ����
				int tmp = dfs(cur, nxt, false);
				min_visit_order = Math.min(min_visit_order, tmp);
				
				if(isRoot != true && tmp >= visit_order[cur]) {
					// ��Ʈ��尡 �ƴϰ�,
					// ����� ���� �ֺ� �ּ� �湮 ������ ���� ������ ���ų� ũ�� ���� ���� ������!
					ans[cur] = true;
				}
				chlcnt++;
			}
		}
		
		// �������� ������ ó�� ������ ����
		if(adj[cur].size() == 1) {
			return visit_order[cur];
		}
		
		// root ��� ���� ó��
		if(isRoot) {
			if(chlcnt >= 2) {
				ans[cur] = true;
			}
		}
		
		// üũ�ƿ�
		return Math.min(min_visit_order, visit_order[cur]);
	}

}
