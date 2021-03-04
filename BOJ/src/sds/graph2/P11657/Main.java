package sds.graph2.P11657;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Node>[] adj;
	static long dist[];
	static boolean has_minus_cycle;
	static int INF = 6000000;
	static int Start = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// initialize
		adj = new ArrayList[N + 1];
		dist = new long[N + 1];
		for(int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
			dist[i] = INF;
		}
		
		// input processing
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Node(b, c));
		}
		
		bell(Start);
		
		// output processing
		if(has_minus_cycle) {
			System.out.println(-1);
		}else {
			for(int i = 1; i <= N; i++) {
				if(i != Start) {
					if(dist[i] == INF) System.out.println(-1);
					else System.out.println(dist[i]);
				}
			}
		}
	} // end of main
	
	private static void bell(int start) {
		dist[start] = 0;
		
		for(int i = 0; i < N - 1; i++) {	// �ִ� �湮�� �� �ִ� ����� ������ŭ 
			for(int j = 1; j <= N; j++) {	// j�� �ֺ��� �ִ� ������ ������Ʈ �� �� �ִ��� Ȯ��
				for(int k =0; k < adj[j].size(); k++) {		// j������ ����� �� �ִ� ���� �뼱
					Node next = adj[j].get(k);
					
					// ���� ���� �뼱�� ���� �ִ� ��θ� ������ �� �ִٸ�
					if(dist[j] + next.weight < dist[next.dest] && dist[j] != INF) {
						// ����
						dist[next.dest] = dist[j] + next.weight;
					}
				}
			}
		}
		
		// �ִ� ��θ� ���ߴµ�, �� �� �� ������ �Ͼ�ٸ� minus_cycle ����
		for(int j = 1; j <= N; j++) {	// j�� �ֺ��� �ִ� ������ ������Ʈ �� �� �ִ��� Ȯ��
			for(int k =0; k < adj[j].size(); k++) {		// j������ ����� �� �ִ� ���� �뼱
				Node next = adj[j].get(k);
				
				// ���� ���� �뼱�� ���� �ִ� ��θ� ������ �� �ִٸ�
				if(dist[j] + next.weight < dist[next.dest] && dist[j] != INF) {
					// ����
					has_minus_cycle = true;
					return;
				}
			}
		}
		
		
	}
}
class Node{
	int dest, weight;

	public Node(int dest, int weight) {
		this.dest = dest;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Node [dest=" + dest + ", weight=" + weight + "]";
	}
}