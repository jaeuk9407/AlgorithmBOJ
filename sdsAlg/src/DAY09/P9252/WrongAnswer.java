package DAY09.P9252;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class WrongAnswer {
	/*
	 * ����: 3, CAP
	 * ==> �ܼ��� �ϳ��� STR�� ���� ������Ű�� Ž���ϸ� �ȵȴ�!
	 * ==> ���� ������ų��, �Ʒ��� ������ų�� �˱� ���ؼ��� �ݵ�� �� �� ������ ������ �� �� �ִ�.
	 * ==> ������ �Ųٷ� ���� Ž���ϴ� ��� ���·� ���� �����ϴ�.
	 * ==> ������ ���̱� ���ؼ��� �޸������̼��� Ȱ���� DP�� ����ؾ� �Ѵ�. 
	 */
	static String str1, str2;
	
    public static void main(String[] args) throws IOException {
    	System.setIn(new FileInputStream("src/DAY09/P9252/input_forWrong.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
		str2 = br.readLine();
		
		int point1 = 0, point2 = 0;
		int last_point1 = 0, last_point2 = 0;
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			// str1, str2 ������ Ž�� �Ϸ� ==> Ż��
			if(str1.length() <= point1) {
				break;
			}else if(str2.length() <= point2) {
				//str2�� ������ Ž���ߴٸ� ���������� ���� ���ڿ��� ã�Ҵ� �ε����� ���ư� �ٽ� Ž���Ѵ�. 
				point2 = last_point2;
				point1++;
				if(str2.length() == 1) break;
			}else {
				// str1, str2 ��� Ž���� �κ��� �����ִ� ���
//				System.out.println(point1+", "+point2);
				if(str1.charAt(point1) == str2.charAt(point2)) {
					sb.append(str1.charAt(point1));
					cnt++;
					last_point1 = point1;
					last_point2 = point2;
					point1++;
					point2++;
				}else {
					point2++;
				}
			}
		} // end of While
		
		if(cnt == 0) {
			System.out.println(0);
		}else {
			System.out.println(cnt);
			System.out.println(sb.toString());
		}
    }
}