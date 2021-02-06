package exhaustiveSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// ERROR

public class P2186_DP {
	private static char[][] P;
	private static char[] word;
	private static int N, M, K, cnt;
	private static int[] dx;
	private static int[] dy;
	private static int[][][] DP;	// x, y, wordIndex ==> �������, value ==> �޼��� �� �ִ� ��� ��
	private static ArrayList<Info2186> list;
	
	
	private static void dfs(int x, int y, int wordIndex) {
		// �������� word�� ù ���ڿ� �ٸ��� �������� ����
		if(P[x][y] != word[wordIndex]) {
			return;
		}
		
		// ���� ������°� �̹� �湮�� �� �ִ� �����̸� ����� �ݿ��ϰ� �� �������� ����
		if(DP[x][y][wordIndex] != 0) {
			System.out.println("���� ���� ���¸� �湮�� �� ���� ==> out");
			System.out.println("DP: "+ DP[x][y][wordIndex]);
			cnt += DP[x][y][wordIndex];
			return;
		}
		
		// üũ��
		list.add(new Info2186(x, y));
		System.out.println("checkIN ====> "+list.toString());
		
		// ����� ���� ��ȸ
		for(int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// �� �� �ִ°�
			if(1 <= nx && nx <= N && 1 <= ny && ny <= M) {
				int nextWordIndex = wordIndex+1;
				// �������� �����ϴ°�
				if(nextWordIndex == word.length) {
					System.out.println("============= ������ ���� ==============");
					System.out.println("nextWordIndex: "+nextWordIndex);
					cnt++;
//					list.add(new Info2186(nx, ny));
					// ������ ����� DP + 1
					int path = 0;
					while(!list.isEmpty()) {
						System.out.println("������ ���� list ���� ���: "+list.toString());
						Info2186 backPoint = list.get(0);
						DP[backPoint.x][backPoint.y][path]++;
						list.remove(0);
						System.out.println("list ��� ����: "+backPoint.toString()+", index: "+path);
						path++;
					}
					return;
				}else if(P[nx][ny] == word[nextWordIndex]) {
					// �������� ����X, �� �� �ִ� ����̹Ƿ� ��� ����
					System.out.println("���� ��� ��� ����");
					System.out.println("nextWordIndex: "+nextWordIndex);
					dfs(nx, ny, nextWordIndex);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// K���� ���� �̵� ���� ����
		dx = new int[K * 4];
		dy = new int[K * 4];
		
		DP = new int[101][101][81];
		
		int index = 0;
		// �����¿�(1, 2, 3 ,4)
		for(int dir = 1;  dir <= 4; dir++) {
			for(int k = 1; k <=K; k++) {
				if(dir == 1) {
					// �� ���� 
					dx[index] = k;
					dy[index] = 0;
					index++;
				}else if(dir == 2) {
					// �� ����
					dx[index] = -k;
					dy[index] = 0;
					index++;
				}else if(dir == 3) {
					// �� ����
					dx[index] = 0;
					dy[index] = -k;
					index++;
				}else {
					// �� ����
					dx[index] = 0;
					dy[index] = k;
					index++;
				}
			}
		}
		
		P = new char[N+1][M+1];
		
		for(int i = 1; i <= N; i++) {
			String line = br.readLine();
			for(int j = 1; j<=M; j++) {
				P[i][j] = line.charAt(j-1);
			}
		}
		String lastLine = br.readLine();
		word = lastLine.toCharArray();
		cnt = 0;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				list = new ArrayList<>();
				dfs(i, j, 0);
			}
		}
		
		System.out.println(cnt);
	}

}

class Info2186{
	int x, y;

	public Info2186(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Info2186 [x=" + x + ", y=" + y + "]";
	}
}
