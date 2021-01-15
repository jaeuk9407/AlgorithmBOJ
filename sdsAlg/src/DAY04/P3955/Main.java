package DAY04.P3955;

import java.util.Scanner;

public class Main {
	static int N, A, B;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		// X : �δ� ������ ������ ��
		// Y : ���� ������ ��
		// A * x + 1 = B * y
		// Ax + By = C ���·� ��ȯ
		// -Ax + By = 1
		// A(-x) + By = 1
		
		// A, B �Է�
		for(int i = 0; i < N; i++) {
			A = sc.nextInt();
			B = sc.nextInt();
		// Ȯ�� ��Ŭ���� ȣ������ �̿��Ͽ� s, t, r �� ã�Ƴ�
		// D = gcd(A,B)
		// D * K = C ==> C % D == 0 �̾�߸� �ظ� ���� �� �ֽ��ϴ� : ���� �׵��
			
			int[] result = eGcd(A, B);
			if(result[2] != 1) {
				System.out.println("IMPOSSIBLE");
			}else {
				int x0 = result[0]; // C = 1, D = 1
				int y0 = result[1];
		
				// x0 = s * C/D
				// y0 = t * C/D
				
				// �Ϲ� �� ���� 
				// x = x0 + B/D * k
				// y = y0 - A/D * k
				
				// 0 < x -> ���� ���ǿ� ���� ��ȣ �ٲ� -> x < 0
				// x0 + B/D * k < 0
				// k < -x0 / B * D
				
				// 0 < y <= 1e9
				// 0 < y0 - A/D * k <= 1e9
				// -y0 < - A/D * k <= 1e9 -y0
				// (y0-1e9) / A * D <= k < y0 / A * D
				
				// (y0-1e9) / A * D <= k < y0 / A * D
				//						< -x0 / B * D
		
				long kFromY = (long)Math.ceil((double)y0 / (double)A) - 1;
				long kFromX = (long)Math.ceil((double)-x0 /(double)B) -1;
				// k�� max�� ���� �� �� k�� �̿��ؼ� y���� ���س��ϴ�. => k�� Ŀ���� y�� �۾����Ƿ�
				long k = Math.min(kFromX,  kFromY);
				// �׷��� ���� y�� ���� ���� y�� �Դϴ�.
				long y = y0 - A * k;
				
				// �� ���� y ���� 1e9���� �۰ų� ������ ������ ��.
				if(y <= 1e9) {
					System.out.println(y);
				}else {
					// �ƴϸ� �Ұ����� ��.
					System.out.println("IMPOSSIBLE");
				}
			}
		}
		sc.close();
	}
	
	//ax + by = c => as + bt = r �� �����ϴ� s,t,r ������ ã��(r�� gcd(a,b)�� ��)
		static int[] eGcd(int a, int b) {
			int s0 = 1, t0 = 0, r0 = a;
			int s1 = 0, t1 = 1, r1 = b;
			
			int temp;
			while(r1 != 0) {
				int q = r0 / r1;
				
				temp = r0 - q * r1; // ���ο� r��
				r0 = r1;
				r1 = temp;
				
				temp = s0 -q * s1;
				s0 = s1;
				s1= temp;
				
				temp = t0 - q * t1;
				t0 = t1;
				t1 = temp;
			}
			return new int[] {s0, t0, r0};
		}
}
