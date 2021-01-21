package DAY09.P5582;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static String str1, str2;
	static int len1, len2, ans;
	static int dp[][];		// Ȱ���ؼ� �޸������̼� 
	
	
	
	// p1, p2�� ������ ���ڿ��� ���� �κ� ���ڿ�
	// �ݴ�� p1, p2���� �����ϴ� ���� �κ� ���ڿ��� ����������, 
	// �޸������̼� Ȱ�뼺 ���鿡�� �Ųٷ� Ž���ϴ� ���� ����. 
	static int calc(int p1, int p2) {
		if(p1 < 0 || p2 < 0) {
			return 0;
		}
//		if(p1 > len1-1 || p2 > len2-1) {
//			return 0;
//		}
		// �̹� ����� ���� �ִ� ���
		if(dp[p1][p2] != -1) {
			return dp[p1][p2];
		}
		// ���� ���ڿ��� ã�� ��� ==> �� ���ڿ� ��� �� ĭ�� �̵�
		if(str1.charAt(p1) == str2.charAt(p2)) {
			dp[p1][p2] = calc(p1 -1, p2 - 1) + 1;
			return  dp[p1][p2];
//			dp[p1][p2] = calc(p1 +1, p2 + 1) + 1;
//			return  dp[p1][p2];
		}
		else {
			dp[p1][p2] = 0;
			return dp[p1][p2];
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine();
		str2 = br.readLine();
		
		
		len1 = str1.length();
		len2 = str2.length();
		dp = new int[len1][len2];
		
		for(int i = 0; i < len1; i++) {
			for(int j = 0; j < len2; j++) {
				dp[i][j] = -1;
			}
		}
		
		for(int i = 0; i < len1; i++) {
			for(int j = 0; j < len2; j++) {
				int tmp = calc(i, j);
				ans = Math.max(ans, tmp);
			}
		}
		
		System.out.println(ans);
		
	}
}
