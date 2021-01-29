package exhaustiveSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1759 {
	static char[] arr;	// ��ȣ�� ������� ���� C���� ���� 
	static int[] result;
	static int L;
	static int C;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[C];
		result = new int[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
//		System.out.println(Arrays.toString(arr));
		Arrays.sort(arr);
		dfs(0, 0, 0, 0);
		
	}
	
	// ������, ���õ� ���� ����, ���� ����, ���� ����
	private static void dfs(int start, int depth, int ja, int mo) {
//		System.out.println("dfs ����! "+start+ ", "+depth+", "+ja+", "+mo);
		for(int i = start; i < C; i++) {
			result[i] = 1;
			
			// ������ ���� ������ �ľ��ؼ� �������� �Ѱ��ش�. 
//			System.out.println(start+ ", "+depth+", "+ja+", "+mo+" �� dfs ȣ��! "+(i+1)+", "+(depth+1)+", "+(ja + (!check(arr[i]) ? 1 : 0)) +", "+ (mo + (!check(arr[i])? 0 : 1)));
			dfs(i + 1, depth + 1, ja + (!check(arr[i]) ? 1 : 0), mo + (!check(arr[i])? 0 : 1));
//			System.out.println("i: "+i);
			result[i] = 0;
		}
		// ���� ������ L���̰�, ���� 2��, ���� 1�� �̻� ����ִ� ��� ���
		if(depth == L && ja >= 2 && mo >= 1) {
//			System.out.println("dfs ���! "+start+ ", "+depth+", "+ja+", "+mo);
			print();
		}
//		System.out.println("dfs �Ҹ�! "+start+ ", "+depth+", "+ja+", "+mo);
	}
	
	public static void print() {
		for(int i = 0; i < C; i++) {
			if(result[i] == 1) {
				System.out.print(arr[i]);
			}
		}
		System.out.println();
	}
	
	// ����, ���� �˻�
	private static boolean check(char a) {
		if(a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u') {
			return true;
		}else {
			return false;
		}
	}

}
