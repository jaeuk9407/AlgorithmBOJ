package exhaustiveSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1963 {
	static int N, CNT[];
	static boolean prime[];
	static Queue<Integer> q;
	
	// �����佺�׳׽��� ü -> ���� �� �Ҽ� ã��  
	static void eratos() {
		for(int i = 2; i * i < 10000; i++) {
			for(int j = i * i; j < 10000; j+=i) {
				prime[j] = false;
			}
		}
	}
	
	// BFS �������� ��� �Ҽ����� �ּ� ���� Ƚ�� ���
	static void bfs(int s) {
		q.add(s);
		CNT[s] = 0;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int i = 0; i <4; i++) {
				// 1 ~ 1000�� �ڸ� ���� 0 ~ 9 ����
				// �ڸ����� �ű� ������ ���� �ڸ��� reset ����� ��!
				// 9033 -> 9133(X), 9033 -> 1133(O) <== case(1033)
				char[] nowCharArray = String.valueOf(now).toCharArray();
				for(int j = 0; j < 10; j++) {
					nowCharArray[i] = (char)(j +'0');
//					System.out.println("["+i+"]: "+(char)(j +'0'));
					StringBuilder sb = new StringBuilder();
					for(char x : nowCharArray) {
						sb.append(x);
					}
					int next = Integer.parseInt(sb.toString());
					if(next > 1000 && prime[next] == true && CNT[next] == -1) {
//						System.out.println("next: "+next+", now: "+now);
						q.add(next);
						CNT[next] = CNT[now] + 1;
//						System.out.println(CNT[next]);
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		q = new LinkedList<>();
		prime = new boolean[10000];
		for(int i = 1; i<10000; i++) {
			prime[i] = true;
		}
		
		eratos();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			CNT = new int[10000];
			for(int j = 0; j < 10000; j++) {
				CNT[j] = -1;
			}
			
			bfs(start);
			
			if(CNT[end] != -1) {
				System.out.println(CNT[end]);
			}else {
				System.out.println("Impossible");
			}
		}
	}
}
