package DAY07.P11266;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	// ������ ã��
	static int v, e, count, num;
	static ArrayList<Integer> adj[];
	static boolean ans[];	// �������̸� true�� üũ
	static int visit_order[];
	
	static int getmin(int x, int y) {
		if(x < y) return x; 
		else return y;
	}
	
	// ���� : ��Ʈ�� ���
	// ���� �湮������ �ڽĵ��� ������ �湮���� �߰��� ���� ���� ��
	static int dfs(int parent, int cur, boolean isRoot) {
		int min_visit_order = 20000;	// �� �ڽĵ��� ���� �� �ִ� ���߿��� ���� ���� ��
		int chlcnt = 0;	// �ڽĵ��� ��
		
		// ���� ��尡 �湮������ �ԷµǾ� ���� ���� ���(�湮���� ���� ���)���� �Լ� ����
		if(visit_order[cur] != 0) {	 
			return 0;
		}
		
		// �湮 ���� �Է�
		num++;
		visit_order[cur] = num;
		
		// ����� ��� ��ȸ 
		for(int i = 0; i < adj[cur].size(); i++) {
			int nxt = adj[cur].get(i);
			if(nxt == parent) continue;	// ����� ������ �θ���� �ǳ� �ڴ�.
			
			if(visit_order[nxt] != 0) { //	���� ����� �湮������ ��ϵǾ� �ִ� ��� 
				if(visit_order[nxt] < min_visit_order) {
					min_visit_order = visit_order[nxt];
				}
			}else {	//���Ӱ� �湮�ϴ� ���
				int tmp;
				tmp = dfs(cur, nxt, false);		// ����� �ּ� �湮 ����
				min_visit_order = getmin(tmp, min_visit_order);
				if(isRoot != true && tmp >= visit_order[cur]) {
					// ��Ʈ��尡 �ƴϰ�, (��Ʈ���� ���� ���� ó��)
					// ����� ���� �ֺ� �ּ� �湮 ������ ���� ������ ���ų� ũ�� ���� ���� ������! 
					ans[cur] = true;
				}
				chlcnt ++;
			}
		}
		
		// �������� ������ ó���� ������ ����.
		if(adj[cur].size() == 1) {
			return visit_order[cur];
		}
		
		// ��Ʈ��� ���� ó�� 
		if(isRoot) {
			// �ڽ��� �ΰ� �̻��̸� ������
			if(chlcnt >= 2) {
				ans[cur] = true;
			}
		}
		
		return getmin(min_visit_order, visit_order[cur]);	// ���� ���� ���߿��� �湮 ������ ���� ���� ���� ��ȯ...
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[v+1];
		ans = new boolean[v+1];
		visit_order = new int[v+1];
		
		// �ʱ�ȭ 
		for(int i = 1; i <= v; i++) {
			adj[i] = new ArrayList<>();
		}
		
		// �Է�
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		
		
		// �������� ã��
		for(int i = 1; i <= v; i++) {
			if(visit_order[i] == 0) { // �׷����� ��� ����Ǿ� �ִ� ���°� �ƴϱ� ������...
				dfs(0, i, true);	// �����ϴ� ���� root
			}
		}
		
		// ��� 
		// ����
		for(int i = 1; i <= v; i++) {
			if(ans[i]) count++;
		}
		System.out.println(count);
		for(int i = 1; i <= v; i++) {
			if(ans[i]) {
				System.out.print(i+" ");
			}
		}
	}
}
