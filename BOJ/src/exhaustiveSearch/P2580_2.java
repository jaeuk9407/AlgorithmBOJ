package exhaustiveSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2580_2 {
	private static int[][] map = new int[9][9];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(0, 0);
		
	}
	
	private static void solve(int row, int col) {
//		System.out.println(row+","+ col);
		
		// ��� ���� ó�������� ���� �� ���� 
		if(col == 9) {
			solve(row + 1, 0);
			return;
		}
		// ��� ���� ó�������� ��� �� ����
		if(row == 9) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					sb.append(map[i][j]+" ");
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
			System.exit(0);
		}
		
		// ���� ��ġ�� ���� ä�������� �ϴ� ���̸� 1 ~ 9 ���� �־�� �˻�
		if(map[row][col] == 0) {
			for(int i = 1; i <= 9; i++) {
				if(isPossible(row, col, i)) {
					map[row][col] = i;
					solve(row, col+1);
				}
			}
			
			map[row][col] = 0;
			return;
		}
		solve(row, col + 1);
	}
	
	private static boolean isPossible(int row, int col, int value) {
		for(int i = 0; i < 9; i++) {
			if(map[row][i] == value || map[i][col] == value) {
				return false;
			}
		}
		
		int smallRow = (row / 3) * 3;
		int smallCol = (col / 3) * 3;
		
		for(int i = smallRow; i < smallRow + 3; i++) {
			for(int j = smallCol; j < smallCol + 3; j++) {
				if(map[i][j] == value) return false;
			}
		}
		
		return true;
	}

}
