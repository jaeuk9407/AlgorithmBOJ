package DAY02.P2143;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class MainSolution {
	static long T;
	static int N, M;
	static long[] A, B;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Long.parseLong(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		A = new long[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Long.parseLong(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		
		B = new long[M + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			B[i] = Long.parseLong(st.nextToken());
		}
		
		// �κ� �迭 ���� ���ҷ� ���� subA, subB ����
		List<Long> subA = new ArrayList<>();
		List<Long> subB = new ArrayList<>();
		
		// subA ����
		for(int i = 0; i < N; i++) {
			long sum = A[i];
			subA.add(sum);
			for(int j = i+1 ; j < N; j++) {
				sum += A[j];
				subA.add(sum);
			}
		}
		
		// subB ����
		for(int i = 0; i < M; i++) {
			long sum = B[i];
			subB.add(sum);
			for(int j = i+1 ; j < M; j++) {
				sum += B[j];
				subB.add(sum);
			}
		}
		
//		System.out.println(subA);
//		System.out.println(subB);
		
		// subA, subB Sorting
		Collections.sort(subA);
		Collections.sort(subB, Comparator.reverseOrder()); // �������� ����
		
//		System.out.println(subA);
//		System.out.println(subB);
		
		long result = 0;
		int ptA = 0;
		int ptB = 0;
		
		while(ptA < subA.size() && ptB < subB.size()) {
			long currentA = subA.get(ptA);
			long target = T - currentA;
			
			if(subB.get(ptB) > target) {
				// Target�� ���� ã�� ���� ���
				ptB++;
			}else if(subB.get(ptB) == target) {
				// Target�� ã�� ���
				long countA = 0;
				long countB = 0;
				
				while(ptA < subA.size() && subA.get(ptA) == currentA) {
					ptA++;
					countA++;
				}
				while(ptB < subB.size() && subB.get(ptB) == target) {
					ptB++;
					countB++;
				}
//				System.out.println("result ����, ptA:"+ptA +" ptB:"+ ptB);
//				System.out.println("result ����, countA:"+countA +" countB:"+ countB);
				result += countA * countB;
				
			}else {
				// subB�� Target�� ���� ���
				ptA++;
			}
		}
		
		System.out.println(result);
		
	}

}
