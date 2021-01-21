package DAY09.P7579;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M,cost[], mem[], ans;
	static int dp[][]; // dp: cost i �� �Ҹ��ؼ� Ȯ���� �� �ִ� �ִ� �޸� ũ��
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[101][10001];
		cost = new int[10001];
		mem = new int[101];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<= N; i++) {
			mem[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		for(int j = 1; j<=N; j++) {
			for(int i = 0; i<= 10000; i++) {
				// [j][i] : j�۱��� ������� ��(������� ���� ��) cost i �� Ȯ���� �� �ִ� �ִ� �޸�
				// j��° ���� ������� �ʰų� / ����ϰų�
				// ���߿� ū ��
				dp[j][i] = dp[j-1][i];	// j��° ���� ������� ���� �� 
				if(i - cost[j] >= 0) {
					dp[j][i] = Math.max(dp[j][i], dp[j-1][i-cost[j]] +mem[j]);	// j��° ���� ������� �� 
				}
			}
		}
		
		// ����� ã�ƺ��鼭, m�̻��� �޸𸮸� Ȯ���ߴ��� Ȯ���غ�
		for(int i = 0; i <=10000; i++) {
			if(dp[N][i] >= M) {
				ans = i;
				break;
			}
		}
		
		System.out.println(ans);
	}
}
