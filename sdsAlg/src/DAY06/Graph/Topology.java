package DAY06.Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Topology {
	static int N, M;
	static int indegree[]; // node�� ����Ű�� ȭ��ǥ�� ���� 
	static ArrayList<Integer>[] pr;
	static Queue<Integer> q;
	
	public static void main(String[] args) throws Exception{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		pr = new ArrayList[N+1];
		indegree = new int[N+1];
		q = new LinkedList<>();
		
		// 1<= N <=32000
		for(int i = 0; i < N+1; i++) {
			pr[i] = new ArrayList<>();
		}
		
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			pr[a].add(b);
			indegree[b]++;
		}
		
		// indegree�� 0�� ���� ã�´�.
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				q.add(i);
			}
		}

		// ��Ƴ��� ���� �ϳ��� �����鼭 ����ϰ� 
		while(!q.isEmpty()) {
			int current = q.poll();
			System.out.print(current+" ");
			
			for(int i =0; i<pr[current].size(); i++) {
				int next = pr[current].get(i);
				indegree[next]--;
				// ����� �͵��� ������ �ϳ��� �ٿ��ش�.
				if(indegree[next]==0) {
					q.add(next);
				}
			}
		}

	}
}

