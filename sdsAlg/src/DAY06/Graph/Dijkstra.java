package DAY06.Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
	// ��������Ʈ, �Ÿ����̺�, Priority Queue, Class
	// P1753
	static int v, e, start_point;
	static ArrayList<Node> adj[];	// ��������Ʈ
	static PriorityQueue<Node> pq;	// �켱���� ť
	static int dist[];
	static int INF = 300000;	// �������� �־��� �ִ� ���� 20��
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[v+1];
		for(int i = 0; i<=v; i++) {
			adj[i] = new ArrayList<>();
		}
		pq = new PriorityQueue<>();
		dist = new int[v+1];
		
		st = new StringTokenizer(br.readLine());
		
		start_point = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a].add(new Node(b, c));
		}
		
		for(int i = 0; i <= v; i++) {
			dist[i] = INF;
		}
		
		dijk();
		
		for(int i = 1; i <= v; i++) {
			if(dist[i] != INF) {
				System.out.println(dist[i]);
			}else {
				System.out.println("INF");
			}
		}
		
	}
	
	static void dijk() {
		// 0. ��� ��带 ����(�Ÿ����̺� �ʱ�ȭ, �켱���� ť ����)
		dist[start_point] = 0;
		pq.add(new Node(start_point, 0));
		
		// 1. ť���� ������
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
//			System.out.println("node num: "+cur.node_num+" dist: "+cur.node_dist +"dist[]: "+dist[cur.node_num]);
			
			// 2. �湮�� ���� �ִ� ����� �ǳʶڴ�
			if(dist[cur.node_num] < cur.node_dist) continue;
			
			// 3. ����� ���� ��ȸ
			for(int i = 0; i < adj[cur.node_num].size(); i++) {
				Node nxt = adj[cur.node_num].get(i);
				int tmp = cur.node_dist + nxt.node_dist; // ������(cur) -> ������(nxt)�� �̵��� �� ��� ���
				
				// 4. ������ �� �ִ°� (�Ÿ����̺� ��ġ���� ���� �Ÿ��� ������)
				if(tmp < dist[nxt.node_num]) {
					// 5. ����
					dist[nxt.node_num] = tmp;
					
					// 6. ť�� �ִ´�
					pq.add(new Node(nxt.node_num, tmp));
				}
			}
		}
	}

}

class Node implements Comparable<Node>{
	int node_num, node_dist;

	public Node(int node_num, int node_dist) {
		this.node_num = node_num;
		this.node_dist = node_dist;
	}

	@Override
	public int compareTo(Node o) {
		if(this.node_dist < o.node_dist) {
			return -1;
		}else if(this.node_dist >o.node_dist){
			return 1;
		}else {
			return 0;
		}
	}
}
