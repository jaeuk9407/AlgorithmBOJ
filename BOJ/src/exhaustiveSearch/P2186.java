package exhaustiveSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2186 {
	static int N, M, K;
	static char[][] map;
	static char[] str;
	static int[][][] dp;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static int dfs(int x, int y, int index) {
		// �ܾ ��� ������ ���
		if(index == str.length -1) return dp[x][y][index] = 1;
		// �̹� ���� �ܰ踦 ���� ���� �ִ� ���
		if(dp[x][y][index]!= -1) return dp[x][y][index];
		
		// check in
		dp[x][y][index] = 0;
		for(int i = 0; i<4; i ++) {
			for(int c = 1; c <= K; c++) {	// K ���� ���� �̵� ���� ó��
				int nx = x + (dx[i] * c);
				int ny = y + (dy[i] * c);
				
				if(0 <= nx && nx < N && 0 <= ny && ny < M) {
					if(map[nx][ny] == str[index+1]) {
						dp[x][y][index] += dfs(nx, ny, index+1);
					}
				}
			}
		}
		
		return dp[x][y][index];
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i = 0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		str = br.readLine().toCharArray();
		
		dp = new int[N][M][str.length];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		int ans = 0;
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < M; j++) {
				// ù ���ڰ� ��ġ�ϴ� ��ġ������ dfs ���� 
				if(map[i][j] == str[0]) {
					ans += dfs(i, j, 0);
				}
			}
		}
		System.out.println(ans);
	}
}
