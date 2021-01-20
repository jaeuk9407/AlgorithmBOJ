package DAY08.P11049;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Matrix list[];
	static long dp[][];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		dp = new long[N+1][N+1];
		list = new Matrix[501];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			
			Matrix tmp = new Matrix(a, b);
			list[i] = tmp;
		}
		
		
		calc(0, N-1);
		System.out.println(dp[0][N-1]);
		
		
	}// end of main function
	
	// ����� s���� e���� ����� �ؼ� ������ ���� ���� �Լ�
	static long calc(int s, int e) {
		if(e - s == 1) {
			return list[s].a * list[s].b * list[e].b;
		}
		if(e == s) {
			return 0;
		}
		// �κ����� �� ������ ������ ���� ���ϴµ�....
		// ó������ ����� �ϴ� ���� �ϴ� �����
		
		dp[s][e] = 0;
		
		for(int mid = s; mid <= e-1; mid++) {
			long sum_tmp;
			long last_tmp = list[s].a * list[mid].b * list[e].b;	// (s ~ mid), (mid+1 ~ e) �������� ���� ������ ����� �� ��İ��� �ʿ��� ����
			// calc(s, e)�� ����� ���� ������, �׶� ����ߴ� ����� ����� 
			if(dp[s][mid] != 0 && dp[mid+1][e] != 0) {
				// ��� ���� �ִ� ���
//				System.out.println("case 1");
				sum_tmp = dp[s][mid] + dp[mid+1][e] + last_tmp;
			}else if(dp[s][mid] != 0) {
				// s ~ mid�� �ִ� ���
//				System.out.println("case 2");
				sum_tmp = dp[s][mid] + calc(mid + 1, e) + last_tmp;
			}else if(dp[mid+1][e] != 0) {
//				System.out.println("case 3");
				// mid+1 ~ e�� �ִ� ��� 
				sum_tmp = calc(s,mid) + dp[mid+1][e] + last_tmp;
			}else {
//				System.out.println("case 4");
				// ��� ���� ���� ���
				sum_tmp = calc(s,mid) + calc(mid + 1, e) + last_tmp;
			}
//			System.out.println("s, mid, e, dp[s][e], tmp: "+s+", "+mid+", "+e+", "+dp[s][e]+", "+tmp);
			if(dp[s][e] == 0 || sum_tmp < dp[s][e]) dp[s][e] = sum_tmp;	// �ʱⰪ�̰ų� ������ �ʿ��� ���
		}
		return dp[s][e];
	}

}

class Matrix{
	int a, b;

	public Matrix(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
}