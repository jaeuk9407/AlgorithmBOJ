package DAY01.P1062;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	static int N, K;
	static boolean[] visited;
	static String[] words;
	static int selectedCount = 0;
	static int max = 0;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/DAY01/P1062/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		N = Integer.parseInt(sc.next());
		K = Integer.parseInt(sc.next());
		
		// words �Է�
		words = new String[N];
		for(int i=0; i<N; i++) {
			words[i] = sc.next();
		}
		
		visited = new boolean[26];
		
		// a, n, t, i, c
		visited['a' - 'a'] = true;
		visited['n' - 'a'] = true;
		visited['t' - 'a'] = true;
		visited['i' - 'a'] = true;
		visited['c' - 'a'] = true;
		
		if(K < 5) {
			System.out.println(0);
			return;
		}
		
		for(int i = 0; i < 26; i++) {
			if(visited[i] == false) {
				dfs(i);
			}
		}
		
		//max ���
		System.out.println(max);
	}
	
	
	static void dfs(int index) {
		int sentenceCount = 0;
		// 1. üũ�� => visitied[index] = true, selectedCount
		visited[index] = true;
		selectedCount++;
		// 2. �������ΰ�? => selectedCount �� K�� �����ߴ� -> max ����
		if(selectedCount == K-5) {
			WordInspection: for(String str: words) {
				for(int i = 0; i<str.length(); i++) {
					if(visited[str.charAt(i)-'a'] == false) {
						// word�ȿ� �������� ���� ���ڰ� �����Ƿ� ���� word�� �Ѿ��
						continue WordInspection;
					}
				}
				// ��� �ִ� ���ڶ� break���� ���� ���� ���� counting
				sentenceCount++;
			}
			// max ����
			if(sentenceCount >= max) {
				max = sentenceCount;
			}
			// ������ �� �湮 ��� �ʱ�ȭ
			visited[index] = false;
			selectedCount--;
			return;
		}
		// 3. ����� ���� ��ȸ -> index +1 ~ 26
		for(int i = index; i < 26; i++) {
			// 4. �� �� �ִ°�? => visited[next] == false
			if(visited[i] == false) {
				// 5. ���� dfs(next)
				dfs(i);
			}
		}
		// 6. üũ�ƿ� => visited[index] = false, selectedCount
		visited[index] = false;
		selectedCount--;
	}

}
