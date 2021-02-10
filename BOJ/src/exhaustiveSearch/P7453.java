package exhaustiveSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1. �迭 �� ���� ���� ���� �����ϴ� ArrayList ��� => Collections.sort�� ���� ����
// https://www.acmicpc.net/board/view/50851

// 2. N = 1�� ��� ���� ó��
// 3. ans Integer => Long �ڷ��� ��ȯ

public class P7453 {
	
	static int[] A, B, C, D;
	static int[] leftArr, rightArr; // �迭 �� ���� ���� ���� Array
	static int N;
	static long ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		B = new int[N];
		C = new int[N];
		D = new int[N];
		
		leftArr = new int[N*N];
		rightArr = new int[N*N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		makeSum(A, B, leftArr);
		makeSum(C, D, rightArr);
		
		Arrays.sort(leftArr);
		Arrays.sort(rightArr);
		
		ans = 0;
		calcAns();
		
		System.out.println(ans);
		
	}
	// �� �迭���� ���Ҹ� �ϳ��� �̾� ���� �� �ִ� ���� ��� ��츦 list�� �����
	private static void makeSum(int[] arr1, int[] arr2, int[] whereArr) {
		int index = 0;
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr2.length; j++) {
				whereArr[index] = arr1[i] + arr2[j];
				index++;
			}
		}
	}
	
	private static void calcAns() {
		int left = 0;
		int right = rightArr.length - 1;
		while(left < leftArr.length && right >= 0) {
			int lv = leftArr[left];
			int rv = rightArr[right];
			
			if(lv + rv == 0) {
				int lc = 0;
				while(true) {
					// N = 1�� ��� outOfBounds�� �����ֱ� ���� ����ó��
					if(left == leftArr.length) break;
					if(leftArr[left] == lv) {
						lc++;
						left++;
					}else {
						break;
					}
				}
				
				int rc = 0;
				while(true) {
					// N = 1�� ��� outOfBounds�� �����ֱ� ���� ����ó�� 
					if(right < 0) break;
					if(rightArr[right] == rv) {
						rc++;
						right--;
					}else {
						break;
					}
				}
				ans += (long)lc * (long)rc;
			}
			else if(lv + rv < 0) left++;
			else if(lv + rv > 0) right--;
		}
	}
}
