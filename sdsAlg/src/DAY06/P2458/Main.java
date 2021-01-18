package DAY06.P2458;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, ANS, visited_cnt, visited_rev_cnt;
	static ArrayList<Integer>[] adj, rev;
	static boolean[] visited, visited_rev;
	
	public static void main(String[] args) throws Exception{
		// �Է����� �׷����� ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		rev = new ArrayList[N+1];
		visited = new boolean[N+1];
		visited_rev = new boolean[N+1];
		
		
		for(int i=0; i <= N; i++) {
			adj[i] = new ArrayList<>();
			rev[i] = new ArrayList<>();
		}
		
		// for int i=0 i<m i++
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			//a�� b���� ����
			adj[a].add(b); // ------- a ----> b
			rev[b].add(a); // ------- b ----> a
		}
		
//		System.out.println("adj: "+Arrays.toString(adj));
//		System.out.println("rev: "+Arrays.toString(rev));
		
		// ������ ū �л����� ��
		for(int i = 1; i<=N ; i++) {
			visited_cnt =0; // �� + ������ ū �л�
			dfs(i);
			
			
			// ������ ���� ���� ��
			visited_rev_cnt = 0; // �� + ������ ���� �л�
			dfs_rev(i);
			
//			System.out.println(i+"�� �л����� ū �л� ��: "+visited_cnt+", ���� �л� ��: "+visited_rev_cnt+" ��: "+(visited_cnt+visited_rev_cnt)+" (������ 2�� ����)");
			
			// i��° �л��� ������ �� �� �ִ��� ������ �Ǵ�
			// ������ ū �л��� + ������ ���� �л����� + 1 == N
			// ��δ� ������ �Ǿ����� Ȯ���غ�
			if(visited_cnt + visited_rev_cnt == N + 1) {
				ANS++;
			}
			
			// ���ο� �л��� DFS�� ���� �ʱ�ȭ 
			for(int j = 1; j <= N; j++) {
				visited[j] = false;
				visited_rev[j] = false;
			}
			
		}
		
		System.out.println(ANS);
		
	}
	static void dfs(int cur) {
		// 1. üũ��
//		System.out.println("dfs cur: "+cur);
		visited[cur] = true;
		visited_cnt++;
		// (2. �������� �����ߴ°�?)
		// 3. ����� ���� ��ȸ
		if(!adj[cur].isEmpty()) {
			// 4. �� �� �ִ°�?
			for(int i = 0; i<adj[cur].size(); i++) {
				int next = adj[cur].get(i);
				if(!visited[next]) {
					// 5. ����.
					dfs(next);
				}
			}
		}
		// (6. üũ�ƿ�)
	}
	
	static void dfs_rev(int cur) {
		// 1. üũ��
//		System.out.println("dfs_rev cur: "+cur);
		visited_rev[cur] = true;
		visited_rev_cnt++;
		// (2. �������� �����ߴ°�?)
		// 3. ����� ���� ��ȸ
		if(!rev[cur].isEmpty()) {
			// 4. �� �� �ִ°�?
			for(int i = 0; i<rev[cur].size(); i++) {
				int next = rev[cur].get(i);
				if(!visited_rev[next]) {
					// 5. ����.
					dfs_rev(next);
				}
			}
		}
		// (6. üũ�ƿ�)
	}

}
