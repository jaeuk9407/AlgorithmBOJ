package DAY01.P1062;

import java.io.FileInputStream;
import java.util.Scanner;

public class MainSolve {
	static int N, K;
	static boolean[] visited;
	static String[] words;
	static int selectedCount = 0;
	static int max = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/DAY01/P1062/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		N = Integer.parseInt(sc.next());
		K = Integer.parseInt(sc.next());
		
		words = new String[N];
		visited = new boolean[26];
		
		// words �Է�
		for(int i =0; i < N; i++) {
			words[i] = sc.next().replaceAll("[antic]", "");
		}
		
		
		// a, n, t, i, c
		visited['a' - 'a'] = true;
		visited['n' - 'a'] = true;
		visited['t' - 'a'] = true;
		visited['i' - 'a'] = true;
		visited['c' - 'a'] = true;
		selectedCount = 5;
		
		// K<5�� ��� ���� �� �ִ� �ܾ� ����
		if(K < 5) {
			System.out.println(0);
			sc.close();
			return;
		}
		
		// K = 5�� ��� ���� ó��
		max = countWords();
		
		
		for(int i = 0; i < 26; i++) {
			if(visited[i] == false) {
				dfs(i);
			}
		}
		
		//max ���
		System.out.println(max);
		sc.close();
	}
	
	
	static void dfs(int index) {
		// 1. üũ�� => visitied[index] = true, selectedCount
		visited[index] = true;
		selectedCount++;
		// 2. �������ΰ�? => selectedCount �� K�� �����ߴ� -> max ����
		if(selectedCount == K) {
			// max ����
			max = Math.max(countWords(),max);
		}else {
			// 3. ����� ���� ��ȸ -> index +1 ~ 26
			for(int i = index+1; i < 26; i++) {
				// 4. �� �� �ִ°�? => visited[next] == false
				if(visited[i] == false) {
					// 5. ���� dfs(next)
					dfs(i);
				}
			}
		}
		// 6. üũ�ƿ� => visited[index] = false, selectedCount
		visited[index] = false;
		selectedCount--;
	}
	// ���� �� �ִ� �ܾ� ����
	static int countWords() {
		int count = 0;
		for(int i = 0; i < N; i++) {
			String word = words[i];
			boolean isPossible = true;
			for(int j = 0; j < word.length(); j++) {
				if(visited[word.charAt(j)-'a']==false) {
					isPossible = false;
					break;
				}
			}
			if(isPossible == true) {
				count++;
			}
		}
		return count;
	}
}

