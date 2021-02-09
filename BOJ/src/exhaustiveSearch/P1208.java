package exhaustiveSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
����
N���� ������ �̷���� ������ ���� ��, ũ�Ⱑ ����� �κм��� �߿��� �� ������ ���Ҹ� �� ���� ���� S�� �Ǵ� ����� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
�Է�
ù° �ٿ� ������ ������ ��Ÿ���� N�� ���� S�� �־�����. 
(1 �� N �� 40, |S| �� 1,000,000) ��° �ٿ� N���� ������ �� ĭ�� ���̿� �ΰ� �־�����. �־����� ������ ������ 100,000�� ���� �ʴ´�.
���
ù° �ٿ� ���� S�� �Ǵ� �κм����� ������ ����Ѵ�.
 */
public class P1208 {
	static ArrayList<Integer> A,B;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int [] arr = new int [N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		A = new ArrayList<Integer>();
		B = new ArrayList<Integer>();
		
		// 40������ ���� ���ϴ� ���� ������ Ŀ�� �ð��ʰ� �߻�
		// �������� ������ A(0~N/2), B(N/2~N)�� �κм����� ���� ����.
		dfs(arr, 0, N/2, 0, A);
		dfs(arr, N/2,N,  0, B);
		
		// ����
		Collections.sort(A);
		Collections.sort(B);
		
		// left, right �� lower_bound, upper_bound�� ��Ƽ� �Ѵ�.
		// left, right �ݴ�� �ص� ���������. 
		
		int left = 0;
		int right = B.size()-1;
		long ans=0;
		while(left<A.size() && right>=0){
			int lv = A.get(left);
			int rv = B.get(right);
			if(lv+rv==S){
				// lv + rv �� ���� S�� �� ó��
				long lc = 0;
				long rc = 0;
				// ���ʿ��� �ߺ��Ǵ� �� ������ ī�����Ѵ�(�ڱ�����).
				while(left<A.size() && A.get(left)==lv){
					lc++;
					left++;
				}
				// �����ʿ��� �ߺ��Ǵ� �� ������ ī�����Ѵ�(�ڱ�����).
				while(right>=0 && B.get(right)==rv){
					rc++;
					right--;
				}
				// ������ �����ش�. �ش�Ǵ� ����� ���� ���ϴ� �����̱� ������
				ans += lc*rc;
			}
			// ���ϰ��� �պ��� ū ��� right�� ����.
			if(lv+rv>S){
				right--;
			}
			// ���ϰ��� �ϴ� �պ��� ���� ��� low�� ����.
			if(lv+rv <S) {
				left++;
			}
		}
		// ���ϰ��� �ϴ� ���� 0�� ��, ������(�ƹ��͵� �������� ���� ��=0)���� �Ǿ� �־ �信�� -1�� ���ش�.
		if(S==0){
			System.out.println(ans-1);
		}else {
			System.out.println(ans);
		}
	}
	// �κм����� ���� ���ϴ� ����Լ�
	public static void dfs(int arr[], int idx, int n, int sum, ArrayList<Integer> list){
		
		if(idx==n){
			list.add(sum);
			return ;
		}
		
		dfs(arr, idx+1, n, sum+arr[idx], list);
		dfs(arr, idx+1, n, sum, list);
		
	}
}