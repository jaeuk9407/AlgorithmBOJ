package DAY09.P2098;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N; 
	private static int W[][] = new int[16][16]; // i -> j���� �̵��ϴ� �Ÿ�
	private static int dp[][] =new int[(1<<16)][16]; 	//dp[������ ���õ�][�������� ������ ����]
	private static final int INF = 20000000;
	// ���������� �ٽ� �;���
	// 0������, 1������, 2������......
	// 0 0 0 0 0 1 1 0 0 0 0 : 6, 7�� ���ø� ������
	
	// x \= 1 << 10 -> masking
	// x&(1<<10); -> check
	// 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N;j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
				
		// dp �迭 �ʱ�ȭ 
		for(int i = 0; i <(1<<16); i++) {
			for(int j = 0; j <16; j++) {
				dp[i][j] = INF;
			}
		}
		
		// ������ ���� : 0 ��° ���ÿ��� ������ .... [0��° ���ø� ���Ŀ԰�][���� 0��°�� �����ϱ�] 
		dp[1][0] = 0;
		for(int i = 0; i <(1<<N); i++) {
			// ���� dp[i][j]
			for(int j = 0; j < N ; j ++) {
				for(int k = 0; k < N; k++) {
					// k�� ���� �ִ���
					if(bit_check(i, k) == true) continue;
					if(W[j][k] == 0) continue;
					// ���� 
					// ���� �ּҸ� ������Ʈ
					int nxt = bit_set(i, k);
					dp[nxt][k] = Math.min(dp[nxt][k], dp[i][j]+W[j][k]);
				}
			}
		}
		
		// ��� ���ø� ��ȸ�߰�, �������� ���ư����� 
		int answer = INF;
		int last_state = (1<<N) -1;	// ��� ���ø� �湮�� �����̰�
		for(int i = 0; i < N; i++) {
			if(W[i][0] == 0) continue; // ���� ����
			int tmp = dp[last_state][i] + W[i][0];
			if(tmp < answer) answer = tmp;
		}
		
		
		// Test
//		for(int i = 0; i< N; i++) {
//			for(int j = 0; j<N;j++) {
//				System.out.print(dp[i][j]+" ");
//			}
//			System.out.println("");
//		}
		
		System.out.println(answer);
	} // end of main
	
	
	// pos ��°�� ��Ʈ�� 1�� ������ : ���� ���� �ڸ����� 0
	public static int bit_set(int orgbit, int pos) {
		return orgbit | (1<<pos);
	}
	
	// Ư�� �ڸ��� ��Ʈ�� 0���� ����
	public static int bit_unset(int orgbit, int pos) {
		return orgbit & (~(1<<pos));
	}
	
	// Ư�� �ڸ��� ��Ʈ�� 0���� 1���� Ȯ�� 
	public static boolean bit_check(int orgbit, int pos) {
		if((orgbit & (1<<pos))==0) return false;
		return true;
	}

}
