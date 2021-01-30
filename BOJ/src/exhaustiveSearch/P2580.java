package exhaustiveSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2580 {
	static int[][] totalMap = new int[9][9];
	static int[][][] smallMaps = new int[9][3][3];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				totalMap[i][j] = Integer.parseInt(st.nextToken());
			}
//			System.out.println(Arrays.toString(totalMap[i]));
		}
		solve(0, 0);
		
	}
	private static void solve(int row, int col) {
		
		// ���� ��� ä������ ��� ���� ���� ù ��° ������ ����
		if(col == 9) {
			solve(row+1, 0);
			return;
		}
		
		// ��� ���� ��� ä������ ��� ��� �� ����
		if(row == 9) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					sb.append(totalMap[i][j]).append(' ');
				}
				sb.append('\n');
			}
			System.out.println(sb);
			
			// ��� �� �ý��� ����
			System.exit(0);
		}
		
		// ���� ��ġ�� 0�̶�� 1���� 9���� �� ������ �� Ž��
		if(totalMap[row][col] == 0) {
			for(int i = 1; i<= 9; i++) {
				// i ���� �ߺ����� �ʴ��� �˻�
				if(possibility(row, col, i)) {
					totalMap[row][col] = i;
					solve(row, col + 1);
				}
			}
			
			// 1 ~ 9 ���� ���� ���� ���� �������� ������ �ٽ� 0���� ������ �� ������ ��ٸ���.
			// �˻��� ������, ����, ���� ���簢�� �� 0�� ������ �����ϴ� ��� => �� ���� 0�� ���� �ڹٲ� �� ��� ��..
			totalMap[row][col] = 0;
			return;
		}
		solve(row, col + 1);
		
	}
	
	private static boolean possibility(int row, int col, int value) {
		
		// ���� ��, ���� �ִ� ���ҵ� �� ��ġ�� �� ���Ұ� �ִ��� �˻�. 
		for(int i = 0; i < 9; i++) {
			if(totalMap[row][i] == value || totalMap[i][col] == value) {
				return false;
			}
		}
		
		// 3*3 ĭ�� �ߺ��Ǵ� ���Ұ� �ִ��� �˻�
		int set_row = (row / 3) * 3;	// value�� ���� 3 * 3�� ���� ù ��ġ
		int set_col = (col / 3) * 3;	// value�� ���� 3 * 3�� ���� ù ��ġ 
		
		for(int i = set_row; i < set_row + 3; i++) {
			for(int j = set_col ; j < set_col + 3; j++) {
				if(totalMap[i][j] == value) {
					return false;
				}
			}
		}
		
		return true;	// �ߺ��Ǵ� ���� ���� ��� true ��ȯ
	}

}
