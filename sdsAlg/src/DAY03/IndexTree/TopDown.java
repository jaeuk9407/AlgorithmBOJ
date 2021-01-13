package DAY03.IndexTree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TopDown {
	static int N, M, K;
	static long[] nums; // �Է°� �迭
	static long[] tree; // �κ����� ��� Ʈ��
	static int S; // leaf ����� ���� : 2^D
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/DAY03/IndexTree/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		nums = new long[N + 1];
		for(int i = 1; i <= N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		System.out.println(Arrays.toString(nums));
		
		S = 1;
		while(S < N) {
			S *= 2;
		}
		tree = new long[2 * S];
		
		// N �ƴ� ����!
		makeTree(1, 1, S);
		System.out.println(Arrays.toString(tree));
		System.out.println(query(1, 1, S, 3, 7));
		update(1, 1, S, 3, -1);
		System.out.println(Arrays.toString(tree));
		

	}
	
	static long makeTree(int node, int left, int right) {
		if(left == right) { // leaf ���
			if(left <= N) {
				return tree[node] = nums[left];
			}else {
				return tree[node] = 0;
			}
		}
		int mid = (left + right) / 2; 
		tree[node] = makeTree(node * 2, left, mid);
		tree[node] += makeTree(node * 2 + 1, mid+1, right);
		
		return tree[node];
	}
	
	static long query(int node, int left, int right, int qLeft, int qRight) {
		if(qRight < left || right < qLeft) { // ���� ���� ��
			return 0;
		}else if(qLeft <= left && right <= qRight) { // ������ ����
			return tree[node];
		}else { // ������ ��ħ
			int mid = (left + right) / 2; // �θ� ���
			return query(node * 2, left, mid, qLeft, qRight)
					+ query(node * 2 + 1, mid + 1, right, qLeft, qRight);
		}
	}
	
	static void update(int node, int left, int right, int index, long diff) {
		// �ش� Node�� Ŀ�� ������ ���� ��� index�� ������ ��쿡�� ����
		if (left <= index && index <= right) {
			tree[node] += diff;
			if(left != right) {
				// internal node�� ���
				int mid = (left + right) / 2;
				update(node * 2, left, mid, index, diff);
				update(node * 2 + 1, mid+1, right, index, diff);
			}
		}
	}

}
