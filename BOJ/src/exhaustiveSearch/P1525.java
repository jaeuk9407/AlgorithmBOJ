package exhaustiveSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1525 {
	private static Queue<Integer> q = new LinkedList<>();
	private static Map<Integer, Integer> m = new HashMap<>();
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static int start;
	
	private static void bfs() {
		m.put(start, 0);	// key: �迭 ����, value: �̵� Ƚ��
		q.add(start);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			String nowString = String.valueOf(now);
			int nine = nowString.indexOf("9");	// 1���� �迭���� 9�� index
			int x = nine / 3;	// 2���� �迭���� 9�� row index 
			int y = nine % 3;	// 2���� �迭���� 9�� col index
			
			for(int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				int move = nextX * 3 + nextY; // 1���� �迭������ �̵��� ��ġ �ε���
				if(0 <= nextX && nextX <= 2 && 0 <= nextY && nextY <= 2) {
					StringBuilder sb = new StringBuilder(nowString);
					char temp = sb.charAt(move);
					sb.setCharAt(move, '9');
					sb.setCharAt(nine, temp);
					int nextNum = Integer.parseInt(sb.toString());
					
					// ���� �̵��� ���°� �湮�� �� ���� ��� �湮 ó��
					if(!m.containsKey(nextNum)) {
						m.put(nextNum, m.get(now)+1);
						q.add(nextNum);
					}
				}
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		start = 0;
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				int k = Integer.parseInt(st.nextToken());
				if(k == 0) {
					k = 9;
				}
				// (3*3)2���� �迭�� �þ�߷� �ϳ��� 9�ڸ� ������ ��ȯ 
				start = (start * 10) + k;
			}
		}
		
//		System.out.println(start);
		bfs();
		
		if(m.containsKey(123456789)) {
			System.out.println(m.get(123456789));
		}else {
			System.out.println(-1);
		}
		
	}

}
