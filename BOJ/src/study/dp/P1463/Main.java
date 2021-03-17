package study.dp.P1463;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int dp[];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N + 1];
		
		dp[0] = 0;
		dp[1] = 0;
		
		// bottom-up���� �� ���� ����� ���ϸ鼭 �ּڰ��� ����
		for(int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + 1;
			if(i % 2 == 0) dp[i] = Math.min(dp[i/2] + 1, dp[i]);
			if(i % 3 == 0) dp[i] = Math.min(dp[i/3] + 1, dp[i]);
		}
		
		System.out.println(dp[N]);
	}

}