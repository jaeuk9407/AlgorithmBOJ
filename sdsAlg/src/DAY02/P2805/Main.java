package DAY02.P2805;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] Trees;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Trees = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			Trees[i] = Integer.parseInt(st.nextToken());
		}
		
		int maxHeight = Trees[0];
		for(int i = 0; i < N-1; i++) {
			maxHeight = Math.max(maxHeight, Trees[i+1]);
		}
		
		// M�� ������ �ִ� 20��, ���� ������ ���� 10��, ������ ���� 100������ ���� �ſ� ū ��Ȳ���� int�� ����ϸ� ���� ����� ������ ����
		long woods = 0, mid = maxHeight / 2, low = 0, high = maxHeight, result = 0;
		int pt = 0;
		
		while(true) {
			pt = 0;
			woods = 0;
			// �ش� ���� ���� ��� ���緮 ��� 
			while(pt < N) {
				if(Trees[pt] > mid) {
					woods += Trees[pt] - mid;
				}
				pt++;
			}
			
			if(woods == M) {
				//���緮�� �˸��� ���
				result = mid;
				break;
			}else if(woods > M) {
				// ���緮�� ��ǥ�� �ʰ��� ���
				low = mid;
				mid = (low + high) / 2;
			}else {
				// ���緮�� ������ ���
				high = mid;
				mid = (low + high) / 2;
			}
			
			// ���緮�� ��Ȯ�� ���� �� ���� ���̽� ó��
			if(low == mid) {
				result = mid;
				break;
			}
		}
		
		System.out.println(result);
		
	}
}
