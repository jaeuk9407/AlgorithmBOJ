package exhaustiveSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// Wrong Answer

public class P1208_2 {
	static int N, S, cnt;
	static ArrayList<Integer> leftList = new ArrayList<>();
	static ArrayList<Integer> rightList = new ArrayList<>();
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// �Է¹��� �迭�� �� �κ����� ���� �� �κп��� ��� �κ� ������ �� case���� list�� ����  
		makeSum(0, 0, N/2, leftList);
		makeSum(0, N/2, N, rightList);
		
		// ��� �κ� ������ �� case�� ��� list�� ������������ ����
		Collections.sort(leftList);
		Collections.sort(rightList);
//		System.out.println(leftList.toString());
//		System.out.println(rightList.toString());
//		System.out.println("----------------------------------------------");
		
		cnt = 0;
		calcC();
		
		// ���� 0�� ���� ã�� ���, �κ� ������ ���� ã�� �� �ϳ��� �������� ���� ��찡 ���ԵǱ� ������ �� �� ���־�� ��
		if(S == 0) {
			System.out.println(cnt - 1);
		}else {
			System.out.println(cnt);
		}
		
		
	}// end of main
	
	private static void calcC() {
		int pointerL = 0;
		int pointerR = rightList.size()-1;
		
		while(true) {
			if(pointerL == leftList.size() || pointerR < 0) {
				break;
			}
			int lv = leftList.get(pointerL);
			int rv = rightList.get(pointerR);
			
			// ���� ���� ���� ������ ���� �̷�� �ִ� �� ���� list ���� � �ִ��� ����. 
			if(lv + rv == S) {
				int lc = 0;
				while(pointerL < leftList.size() && leftList.get(pointerL) == lv) {
					lc++;
					pointerL++;
				}
				
				int rc = 0;
				while(0 <= pointerR && rightList.get(pointerR) == rv) {
					rc++;
					pointerR--;
				}
				cnt += lc * rc;
			}
			
			// �����ϴ� ������ �� ū ���
			if(lv + rv > S) {
				pointerR--;
			}
			
			// �����ϴ� ������ �� ���� ��� 
			if(lv + rv < S) {
				pointerL++;
			}
			
			
		}
		
	}
	
	private static void makeSum(int sum, int start, int end, ArrayList<Integer> list) {
		// ������ �������� ��� ȣ���� ������ ������ list�� �־� ��� �κм��� ���� ��츦 list�� ����  
		if(start == end) {
			list.add(sum);
			return;
		}
		makeSum(sum, start+1, end, list);
		makeSum(sum+arr[start], start+1, end, list);
	}

}
