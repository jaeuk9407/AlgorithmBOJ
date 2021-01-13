package DAY03.IndexTree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BottomUp {
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
		makeTree();
		System.out.println(Arrays.toString(tree));
		System.out.println(query(3, 7));
		update(3, tree[3+S-1]-1);
		System.out.println(Arrays.toString(tree));
	
	}
	
	static void makeTree() {
		for (int i = 0; i < N; i++) {
			tree[S + i] = nums[i + 1];
		}
		for(int i = S - 1; i > 0; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static long query(int left, int right) {
		long result = 0;
		left += S - 1;
		right += S - 1;
		
		// left, right index�� ������ ������ �ݺ� 
		while(left <= right) {
			if(left % 2 == 1) { // ������ ���� ������ �θ� ��带 ������ �ִ� ���
				result += tree[left++];
			}
			if(right % 2 == 0) { // ������ ���� ������ �θ� ��带 ������ �ִ� ���
				result += tree[right--];
			}
			left /= 2;
			right /= 2;
		}
		
		return result;
	}
	
	static void update(int index, long value) {
		// ��û ���� index -> Ʈ�� �� �ε����� ��ȯ
		index += S -1;
		tree[index] = value;
		
		// �θ� ��� �ݺ������� root���� update
		index /= 2;
		while(index >= 1) {
			tree[index] = tree[index * 2] + tree[index * 2 + 1];
			index /= 2;
		}
	}

}
