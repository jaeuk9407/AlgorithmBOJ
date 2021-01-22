package DAY10.P10714;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int A[];
	static long dp[][];	// [i][j] ������ i��° cake,�������� j��° cake�϶� �ִ�� ���� �� �ִ� ��
	
	// J�� ���� ���ʰ� �Ǿ��µ� �ٶ󺼶� ������ l����ũ, �������� r����ũ�� ���� �� ���� �Դ� ��
	static long J_eat(int l, int r) {
		if(l==r) return A[l];
		// ���� �� �ִ� ����� ��
		if(dp[l][r] != -1) return dp[l][r];
		int nxtl, nxtr;

		// l�� �԰ų�...A[l]
		nxtl = l+1;
		if(nxtl == N) nxtl = 0;
		// r�� �԰ų�...A[r]
		nxtr = r-1;
		if(nxtr<0) nxtr = N-1;
		
		return dp[l][r] = Math.max(A[l] + I_eat(nxtl, r), A[r] + I_eat(l, nxtr));
	}
	// L�� ���� ���ʰ� �Ǿ��µ� �ٶ󺼶� ������ l����ũ, �������� r����ũ�� ���� �� ���� �Դ� ��
	// L�� ���� ���ʰ� �Ǿ��µ�... J�� ���� ���� �� �ִ� �� ==> ����! 
	static long I_eat(int l, int r) {
		if(l==r) return 0;
		// ���� �� �ִ� ����� ��....�� ���� ū �͸� �Ծ�� ��
		if(dp[l][r] != -1) return dp[l][r];
		int nxtl, nxtr;
		if(A[l] > A[r]) {
			// A[l]�� �԰�
			nxtl = l+1;
			if(nxtl == N) nxtl = 0;
			return J_eat(nxtl, r);
		}else {
			// A[r]�� ����
			nxtr = r-1;
			if(nxtr < 0) nxtr = N-1;
			return J_eat(l, nxtr);
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N+1];
		dp = new long[N+1][N+1];
		
		for(int i= 0; i<N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		if(N == 1) {
			System.out.println();
			return;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j<N; j++) {
				dp[i][j] = -1;
			}
		}
		
		long ans = 0;
		for(int i =0; i<N; i++) {
			int nxtl, nxtr;
			nxtl = i+1;
			if(nxtl ==N) nxtl = 0;
			nxtr = i-1;
			if(nxtr < 0) nxtr = N-1;
			ans = Math.max(ans, A[i]+I_eat(nxtl, nxtr));
		}
		
		System.out.println(ans);
		
		
	}
}
