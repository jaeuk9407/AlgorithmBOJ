package DAY04.EuclideanAlg;

import java.util.Arrays;

public class ExtendedEuclideanAlg {
	
	public static void main(String[] args) throws Exception{
		System.out.println(Arrays.toString(eGcd(71, 240)));
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
