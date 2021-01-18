package DAY06.P1922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, Answer;
	private static Info[] edge;
	private static int[] pr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		edge = new Info[M];
		Answer = 0;
		pr = new int[N+1];
		
		for(int i = 0; i <= N; i++) {
			pr[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a, b, c;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			edge[i] = new Info(a, b, c);
		}
		
		
		
		// ������ ũ�� ������ ����
		Arrays.sort(edge, new Comparator<Info>(){

			@Override
			public int compare(Info o1, Info o2) {
				if(o1.c < o2.c) return -1;
				else if(o1.c > o2.c) return 1;
				return 0;
			}
		});
		
//		System.out.println(Arrays.toString(edge));
		
		
		int cnt = 0;
		// ũ�罺Į �˰��� ��� = ���� ����� �����ͺ��� Ʈ�� ����
		// union-find �̿��ؼ� Ʈ������
		for(int i = 0; i < M; i++) {
			// ���� ������ �о��
			int a, b, c;
			a = edge[i].a; // ���� �� ��� 
			b = edge[i].b; // �ٸ��� �� ���
			c = edge[i].c; // ������ ���
			
			// ���̿���Ǿ� �ִ��� = ���� �׷����� Ȯ��
			// ���� �ٸ� �׷��̸� ��������, ��뵵 �߰��� 
			// ����� �Ի�
			if(find(a) != find(b)) {
//				System.out.println("union a, b"+a+","+b);
				union(a, b);
				Answer += c;
				cnt++;
			}
		}
		
		// ���� Ƚ�� = MST �������� != N-1 ==> ��Ȳ�� ���� MST ������ �ȵ� ��Ȳ ����ó�� �ʿ�!!
//		System.out.println("pr : "+Arrays.toString(pr));
		System.out.println(Answer);
		
	}
	
	public static int find(int a) {
		if(a == pr[a]) return a;
		pr[a] = find(pr[a]);
		return pr[a];
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		pr[a] = b;
	}
	
}

class Info{
	public int a, b, c;

	public Info(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Info [a=" + a + ", b=" + b + ", c=" + c + "]";
	}
}