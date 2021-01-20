package DAY08.P1932;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// DP

	static int N, tri[][], max_val[][], ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		tri = new int[510][510];
		max_val = new int[510][510];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= i; j++) {
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		br.close();
		
		max_val[1][1] = tri[1][1];
		for(int i = 2; i <= N ; i++) {
			for(int j = 1; j <= i; j++) {
				// max_val[i][j] == max_val[i-1][j], max_val[i-1][j-1] �� �� �ϳ��� + tri[i][j]
				// j ������� ó���� ���ָ�....
				// �ص� ���� �ʴ� ������ ������ 0�̴ϱ�...
				max_val[i][j] = Math.max(max_val[i-1][j-1], max_val[i-1][j])+tri[i][j];
			}
		}
		
		for(int i = 1; i <= N; i++) {
			ans = Math.max(max_val[N][i], ans);
		}
		
		
		
		System.out.println(ans);
	}
}
