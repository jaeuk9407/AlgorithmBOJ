package study.dp.P1463;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IncorrectAnswer {
	static int N;
	static int dp[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N + 1];
		
		int ans = solve(N, 0);
		
		System.out.println(ans);
	}
	private static int solve(int num, int cnt) {
		
		// ������ -> ��������� count ��ȯ 
		if(num == 1) {
			return cnt;
		}
		
		// ���� ��ġ�� �湮�� ���� �ִٸ� �ּ��� ���� ����Ǿ� �����Ƿ� �� Ž������ ����
		if(dp[num] != 0) {
			return dp[num];
		}
		
		// ���� ���� Ž���ص� N�� �Ѿ�� ����
		int result1 = N+1;
		int result2 = N+1;
		int result3 = N+1;
		
		// 1, 2, 3�� ������ ����� �ּ� ����� ����
		if(num % 3 == 0) {
			result1 = solve(num/3, cnt+1);
		}
		if(num % 2 == 0) {
			result2 = solve(num/2, cnt+1);
		}
		
		result3 = solve(num-1, cnt+1);
		
		
		// �� ������� ���� �� �߿����� �ּڰ� �����ϰ� ����
		dp[num] = Math.min(result1, Math.min(result2, result3));
		
		//	���� ��ġ������ �ּڰ��� ��ȯ
		return dp[num];
	}
}
