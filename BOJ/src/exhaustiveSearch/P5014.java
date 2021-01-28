package exhaustiveSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5014 {
	static int F, S, G, U, D;
	static boolean[] visited;
	static int[] cnt;
	static Queue<Integer> q = new LinkedList<>();
	static int[] action = new int[2];
	
	private static void bfs(int start) {
		visited[start] = true;
		q.add(start);
		cnt[start] = 0;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(now == G) {
				break;
			}
			
			// ���� �� �ִ� Action(Up or Down)
			for(int i = 0; i<=1; i++) {
				int next = now + action[i];
				// �湮���� �ʾҰ�, �� �� �ִ� ���̸� ����
				if(1 <= next && next <= F) {
					// �湮�� ���� ���� ���� �湮
					if(!visited[next]) {
						q.add(next);
						visited[next] = true;
						cnt[next] = cnt[now] + 1;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		visited = new boolean[F + 1];
		cnt = new int[F + 1];
		
		for(int i = 0; i <= F; i++) {
			cnt[i] = -1;
		}
		
		action[0] = U;
		action[1] = -D;
		
		bfs(S);
		
		if(cnt[G] == -1) {
			System.out.println("use the stairs");
		}else {
			System.out.println(cnt[G]);
		}
		
	}
}
