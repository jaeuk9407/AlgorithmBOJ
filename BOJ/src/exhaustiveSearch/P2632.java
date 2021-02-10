package exhaustiveSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// ���� : https://kohen.tistory.com/20
public class P2632 {
	static int M, N, S;
	static int[] A, B;
	static int ans;
	static boolean check[];
	static ArrayList<Integer> AList = new ArrayList<>();
	static ArrayList<Integer> BList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		A = new int[M];
		B = new int[N];
		
		for(int i = 0; i<M; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(br.readLine());
		}

		for(int i = 0; i < M; i++) {
			// check�迭 �ʱ�ȭ
			check = new boolean[M];
			// ù ��° ���� ���  
			check[i] = true;
			getSum(A[i], i, i+1, check, A, AList);
			
		}
		
		for(int i = 0; i < N; i++) {
			// check�迭 �ʱ�ȭ
			check = new boolean[N];
			// ù ��° ���� ���  
			check[i] = true;
			getSum(B[i], i, i+1, check, B, BList);
		}
		
		// ���� ���� ������ �ʴ� ��� ó�� 
		AList.add(0);
		BList.add(0);
		
		
		Collections.sort(AList);
		Collections.sort(BList);
		
		getAns();
		
		System.out.println(ans);
		
	
		
		
	} // end of main
	

	// �κ��� ���ϱ�
	// ��ȯ ť �����ϵ���
	private static void getSum(int sum, int startIdx, int idx, boolean[] check, int[] arr, List list) {
		// ���� �ε����� ���̸� �����̱� ������ �ٽ� 0���� ó��
		if(idx == arr.length) idx = 0;
		
		list.add(sum);
		
		// ���� �� ���� ���������� ���ؼ��� �Ǹ��� && ������ �ε��� ���� ��� �������� ���� && ������ ���� �̹� Ÿ���� �Ѿ�� ������� ����
		if(check[idx] == false && idx != startIdx -1 && sum <= S) {
			check[idx] = true;
			getSum(sum +arr[idx], startIdx, idx +1, check, arr, list);
		}else {
			return;
		}
	}
	
	// �� �����ͷ� �κ��յ��� �ϳ��� ���� ������, �ϳ��� ū ������ ���غ��� ��ǥ ���� ���ϸ� ans ����ϱ�
	private static void getAns() {
		int leftIdx = 0;
		int rightIdx = BList.size() - 1;
		
		while(leftIdx < AList.size() && 0 <= rightIdx) {
			int lv = AList.get(leftIdx);
			int rv = BList.get(rightIdx);
			
			// �� �κ����� ���� ���ϴ� �հ� ������ ���
			if(lv + rv == S) {
				int lc = 0;
				while(leftIdx < AList.size() && AList.get(leftIdx) == lv) {
					lc++;
					leftIdx++;
				}
				
				int rc = 0;
				while(rightIdx >= 0 && BList.get(rightIdx) == rv) {
					rc++;
					rightIdx++;
				}
				
				ans += lc * rc;
			}else if(lv + rv < S) {
				// �� �κ����� ���� ���ϴ� �պ��� ���� ���
				leftIdx++;
			}else {
				// �� �κ����� ���� ���ϴ� �պ��� ū ���
				rightIdx--;
			}
		}
	}
}
