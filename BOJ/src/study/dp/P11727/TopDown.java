package study.dp.P11727;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TopDown {
	static int N;
	static int dp[];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = 1;
		
		solve(N);
		
		System.out.println(dp[N]);
		
	}
	static int solve(int num) {
		// ������ �� �ִ� ���̸�, �޸�Ǿ� �ִ� ���� �̿�!
		if(dp[num] != 0) {
			return dp[num];
		}else {
			// ó�� �����ϴ� ���̸� ���� ���ϰ� ����
			dp[num] = 2 * solve(num - 2) + solve(num - 1);
			dp[num] %= 10007;
			
			return dp[num];
		}
	}
}
