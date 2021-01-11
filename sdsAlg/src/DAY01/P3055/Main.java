package DAY01.P3055;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int[] mx = {-1, 1, 0, 0};
	static int[] my = {0, 0, 1, -1};
	
	static int R, C;
	static char[][] map;
	static int[][] dp;
	static Queue<Point> queue;
	static boolean foundAnswer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		
		map = new char[R][C];
		dp = new int[R][C];
		queue = new LinkedList<>();
		
		Point st = null;
		// map �Է�
		for(int r = 0; r < R; r++) {
			String line = sc.next();
			for(int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
				if(map[r][c] == '*') {
					queue.add(new Point(r, c, '*'));
				}
				if(map[r][c] == 'S') {
					st = new Point(r, c, 'S');
				}
			}
		}
		
		sc.close();
		
		// ť�� ���� water ���� ���� ���� ��, start ��ġ ����
		// [*, *, *, S]
		queue.add(st);
		
		
		while(!queue.isEmpty()) {
			// 1. ť���� ������
			Point now = queue.poll();
			// 2. �������ΰ�? if(p ==D)
			if(now.type == 'D') {
				System.out.println(dp[now.x][now.y]);
				foundAnswer = true;
				break;
				
			}
			// 3. ���� �ִ� ���� ��ȸ for(��, ��, ��, ��)
			for(int i = 0; i<4; i++) {
				int nx = now.x + mx[i];
				int ny = now.y + my[i];
				// 4. �� �� �ִ°�? if(���� ����� �ʴ°�)
				if(0 <= nx && nx < R && 0 <= ny && ny < C ) {
					// queue���� ������ type�� ����ġ or *
					if(now.type == 'S' || now.type == '.') { // ����ġ
						// next�� ���� �湮���� �ʾҰ�, �̵� �����Ѱ�?
						if(dp[nx][ny] == 0 && (map[nx][ny] == '.' || map[nx][ny] == 'D')) {
							// 5. üũ�� dp[r][c] = time
							dp[nx][ny] = dp[now.x][now.y] + 1;
							// 6. ť�� ���� queue.add(next)
							queue.add(new Point(nx, ny, map[nx][ny]));
						}
					
					}else { // ��
						if(map[nx][ny] == '.') {
							map[nx][ny] = '*';
							queue.add(new Point(nx, ny, '*'));
						}
					}
				}
			}
		}
		if(foundAnswer == false) {
			System.out.println("KAKTUS");
		}

	}

}
class Point{
	int x;
	int y;
	char type;
	public Point(int x, int y, char type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
	}
}