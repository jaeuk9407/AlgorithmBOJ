package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P11437 {
	
	static ArrayList<Integer> edges[];
	static int N, M;
	static int depths[], parents[];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		edges = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		depths = new int[N+1];
		for(int i = 0 ; i <= N; i++) {
			depths[i] = 50001;
		}
		parents = new int[N+1];
		
		
		StringTokenizer st;
		// �Է¹��� ���� ���� ���� 
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edges[a].add(b);
			edges[b].add(a);
		}
		
		findDepth(1, 0);
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(lca(a, b));
		}
		

	}
	
	// DFS�� ��� ����� depth Ž�� �� ����  
	private static void findDepth(int index, int depth) {
		depths[index] = depth;
		
		// leaf Node�̸� Ž�� ����
		if(edges[index].size() == 0) {
			return;
		}
		
		for(int i = 0; i < edges[index].size(); i++) {
			int next = edges[index].get(i);
			// �θ���� ����� edge�� �ٽ� ó������ ����
			if(depths[index] < depths[next]) {
				parents[next] = index;
				findDepth(next, depth+1);
			}
		}
	}
	
	
	
	private static int lca(int a, int b) {
		
		// �� �ڵ� 2616ms -> �Ʒ� �ڵ� 1664ms
		
//		// ���������̸� return�Ѵ�.
//		if(a == b) {
//			return a;
//		}
//		
//		// �� ����� depth�� �ٸ��� �����ش�
//		if(depths[a] < depths[b]) {
//			return lca(a, parents[b]);
//		}else if(depths[b] < depths[a]){
//			return lca(parents[a], b);
//		}else {
//			// �� ����� depth�� ������ ���� ������ ã�� �Ž��� �ö󰣴�.
//			return lca(parents[a], parents[b]);
//		}
		
		
		// �� ����� depth�� �ٸ��� �����ش� 
		while(depths[a] != depths[b]) {
			if(depths[a] > depths[b]) a = parents[a];
			else b = parents[b];
		}
		
		// �� ����� depth�� ������ ���� ������ ã�� �Ž��� �ö󰣴�
		while(a != b) {
			a = parents[a];
			b = parents[b];
		}
		return a;
	}
}
