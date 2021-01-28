package exhaustiveSearch;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ������ �ִ� �簢�������� ���� ���� �ʰ� �� ���� �׸� �� �����Ƿ�, ������ ���� �簢�� ������ ������ ����.
public class P3108 {
	static int N;
	static Rec[] map;
	static boolean[] visited;
	static Queue<Integer> q = new LinkedList<>();
	static int cnt;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new Rec[N+1];
		visited = new boolean[N+1];
		
		// ������
		map[0] = new Rec(0, 0, 0, 0);
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			map[i] = new Rec(x1, y1, x2, y2);
		}
		
		for(int i = 0; i<= N; i++) {
			// �̹� �׸����� �ִ� ���
			if(visited[i]) continue;
			
			visited[i] = true;
			q.add(i);
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				
				for(int j = 0; j <= N; j++) {
					// ���� ���簢���̰ų�, �����ϴ� �κ��� ���� ����ų�, �̹� �湮�� ���� ������ �ǳʶ�
					if(cur == j || !check(cur, j) || visited[j]) {
						continue;
					}
					visited[j] = true;
					q.add(j);
				}
			}
			cnt++;
		}
		System.out.println(cnt - 1);
	}
	
	static boolean check(int cur, int next) {
		Rec c = map[cur];
		Rec n = map[next];
		if((c.x1 < n.x1 && n.x2 < c.x2 && c.y1 < n.y1 && n.y2 < c.y2)	// C�� N�� �����ϴ� ��� 
				|| (c.x1 > n.x1 && n.x2 > c.x2 && c.y1 > n.y1 && n.y2 > c.y2) // N�� C�� �����ϴ� ���
				|| c.x2 < n.x1 || c.x1 > n.x2 || c.y2 < n.y1 || c.y1 > n.y2)	// �ƿ� ������ ���� ���
		{
			return false;}
		
		
		// C�� N�� �����ϴ� �κ��� �ִ� ���
			
		return true;
	}

}


class Rec {
	int x1, x2, y1, y2;

	public Rec(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
	}

	@Override
	public String toString() {
		return "Rec [x1=" + x1 + ", x2=" + x2 + ", y1=" + y1 + ", y2=" + y2 + "]";
	}
}