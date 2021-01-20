package DAY06.Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BellmanFord {
	// P11657
	static ArrayList<InfoB> adj[];
	static int n, m;
	static int INF = 100000000;
	static long dist[];
	static boolean has_minus_cycle;
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// �Է��� �ޱ� ���� �ʱ�ȭ
		adj = new ArrayList[n+1];
		dist = new long[n+1];
		for(int i = 0; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		// �Է� ����
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a =Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c =Integer.parseInt(st.nextToken());
			
			adj[a].add(new InfoB(b, c));
		}
		
		// �ʱ� ����
		for(int i = 1; i<= n; i++) {
			dist[i] = INF;
		}
		
		
		bell();
		// ���ܸ� ���� ó����
		// �ð��� ������ �ǵ��ư��� -1
		// � ���ñ��� ���� ��ΰ� ������ -1 
		// �� �������� ��� ���
		if(has_minus_cycle) {
			System.out.println(-1);
		}else {
			for(int i =2; i<=n; i++) {
				if(dist[i]==INF)  System.out.println(-1);
				else System.out.println(dist[i]);
			}
		}
	}
	
	static void bell() {
		// �������� �ʱ�ȭ 
		dist[1] = 0;
		for(int i = 0; i < n-1; i++) {	// �ִ� �湮�� �� �ִ� ������ ������ŭ ����
			for(int j =1; j <= n; j++) {	// j�� �ֺ��� �ִ� ������ ������Ʈ �� �� �ִ��� Ȯ���� 
				for(int k =0; k < adj[j].size(); k++) {	
					InfoB nxt = adj[j].get(k);	//nxt.b: j�� �ֺ��� ��, nxt.c: �� ������ �̵��ϴµ� �ɸ��� ���
					if(dist[j] + nxt.c < dist[nxt.b] && dist[j] != INF) {	// dist[j] == INF, nxt.c ==���� ��� ó�� �ʼ�!
						dist[nxt.b] = dist[j] + nxt.c;	// j----->nxt �̵��ϴµ� �� ���� ������� �̵������ϸ� ������Ʈ 
					}
				}
			}
		}
		
		/*
		 * ����: n - 1�� �����߱� ������, ���� �� ��δ��� ����� �������� ���� 
		 * �׸��� �� ��ΰ� �ִܰ�ο��� ���̴�.
		 * �ֳĸ� �� �߰��ؼ� �湮�� ������ ����� ���� Ŀ���״ϱ�....
		 * ������! �߰��� �����ϴ� ������ �־��ٸ�????
		 * ���� �ؾ� ��.
		 * �ѹ� �� �غ�.
		 */
		
		for(int j =1; j <= n; j++) {	// j�� �ֺ��� �ִ� ������ ������Ʈ �� �� �ִ��� Ȯ���� 
			for(int k =0; k < adj[j].size(); k++) {	//
				InfoB nxt = adj[j].get(k);	//nxt.b: j�� �ֺ��� ��, nxt.c: �� ������ �̵��ϴµ� �ɸ��� ���
				if(dist[j] + nxt.c < dist[nxt.b] && dist[j] != INF) {
					// �̷� ���� �Ͼ �� ������??
					has_minus_cycle = true;
					return;
				}
			}
		}
	}
}



class InfoB{
	int b, c;

	public InfoB(int b, int c) {
		this.b = b;
		this.c = c;
	}
	
}