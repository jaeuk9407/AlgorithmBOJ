package DAY03.P2042;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, S;
	static long[] nums;
	static long[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		S = 1;
		while(S < N) {
			S *= 2;
		}
		
		tree = new long[2 * S];
		nums = new long[N + 1];
		
		for(int i = 1; i <= N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		makeTree(1, 1, S);
		
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if(a == 1) {
				// b��° ���� c�� update
				update(1, 1, S, b, c - nums[b]);
				
				// ����! �ٲ� ���� �ٽ� �Է������� ������, �� ���� �ι� �̻� ������ �� ������ �߻���ų �� �ִ�. 
				nums[b] = c;
				
				
			}else if(a == 2) {
				// b��° ���� c��° �������� ��
				System.out.println(query(1, 1, S, b, c));
			}
		}
	}
	
	static long makeTree(int node, int left, int right){
		if(left == right) { // leaf Node
			if(left <= N) {
				return tree[node] = nums[left];
			}else {
				return tree[node] = 0;
			}
		}
		int mid = (left + right) / 2;
		tree[node] = makeTree(node * 2, left, mid);
		tree[node] += makeTree(node * 2 + 1, mid + 1, right);
		return tree[node];
	}
	
	static long query(int node, int left, int right, long qLeft, long qRight) {
		if(qRight < left || right < qLeft) { // query ���� ��
			return 0;
		}else if(qLeft <= left && right <= qRight) { // query�� ����
			return tree[node];
		}else { // ������ ��ħ 
			int mid = (left + right) / 2;
			return query(node * 2, left, mid, qLeft, qRight)
					+ query(node * 2 + 1, mid + 1, right, qLeft, qRight);
		}
	}
	
	static void update(int node, int left, int right, int index, long diff) {
		// �ش� Node�� Ŀ�� ������ ���� ��� index�� ������ ��쿡�� ����
		if(left <= index && index <= right) {
			tree[node] += diff;
			if(left != right) {
				// internal node
				int mid =(left + right) / 2;
				update(node * 2, left, mid, index, diff);
				update(node * 2 + 1, mid + 1, right, index, diff);
			}
		}
	}
}
