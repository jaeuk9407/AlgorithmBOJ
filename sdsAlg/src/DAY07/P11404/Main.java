package DAY07.P11404;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	// P11404
	static int n, m;
	static int INF = Integer.MAX_VALUE;
	static int graph[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n+1][n+1];
		
		// �ʱ�ȭ
		for(int i =1 ; i <=n; i++) {
			for(int j =1; j<=n; j++) {
				if(i!=j) {	//i == j�϶��� 0���� ����Ϸ���....
					graph[i][j] = INF;	
				}
			}
		}
		
		// �Է� 
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// �뼱�� ������ �ִ��� �ּҰ��� �����ϱ� ����
			if(c < graph[a][b]) {
				graph[a][b] = c; 
			}
		}
		
		floyd();
		
		// ���
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++){
				if(graph[i][j] == INF) {
					System.out.print(0+" ");
				}
				else System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}
		
		
	}
	
	
	static void floyd() {
		for(int k = 1; k<=n; k++) { 	// �߰���...
			for(int a = 1; a <=n; a++) {	// ������...
				for(int b =1; b <= n; b++) {	// ����...
					if(graph[a][k] == INF || graph[k][b] == INF) continue;
					
					// k�� ������ ���� ��ΰ� weight�� �� �۴ٸ� ����
					if(graph[a][k]+graph[k][b] < graph[a][b]) {
						graph[a][b] = graph[a][k] + graph[k][b];
					}
				}
			}
		}
	}
}
