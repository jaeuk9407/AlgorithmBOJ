package exhaustiveSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1759_2 {
	private static int cryptographyLen;
	private static int numOfAlpha;
	private static String[] alphas;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st= new StringTokenizer(br.readLine());
    	
    	cryptographyLen = Integer.parseInt(st.nextToken());
    	numOfAlpha = Integer.parseInt(st.nextToken());
    	
    	alphas = br.readLine().split(" ");
    	Arrays.sort(alphas);
    	
    	System.out.println(Arrays.toString(alphas));
    	solve(0, "");
    }
    
    // ������ ��ȣ���� Ž�� �� ����ϴ� �Լ�
    private static void solve(int index, String ret) {
    	if(ret.length() == cryptographyLen) {
    		if(isPossible(ret)) {
    			System.out.println(ret);
    		}
    		return;
    	}
    	// ������ ���ڱ��� Ž���ߴٸ� ����
    	if(index >= numOfAlpha) return; 
    	
    	solve(index + 1, ret + alphas[index]);
    	solve(index + 1, ret);
    }
    
    // �ڸ��� ������ ����, �־��� ���ǿ� �����ϴ� ��ȣ���� Ȯ���ϴ� �Լ�
    private static boolean isPossible(String ret) {
    	int vowel = 0, consonant = 0;
    	for(int i = 0; i < ret.length(); i++) {
    		if(isVowel(ret.charAt(i))) vowel++;
    		else consonant++;
    	}
    	return vowel >= 1 && consonant >= 2;
    }
    
    // �־��� ���ڰ� �������� Ȯ���ϴ� �Լ�
    private static boolean isVowel(char ch) {
    	if(ch == 'a' || ch == 'e' || ch == 'i'|| ch == 'o' || ch == 'u') return true;
    	return false;
    }
}