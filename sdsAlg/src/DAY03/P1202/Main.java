package DAY03.P1202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] bags;
	static Jewelry[] jewelries;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		jewelries = new Jewelry[N];
		bags = new int[K];
		
		// jewelries ���� �Է�
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int m = Integer.parseInt(st.nextToken()); 
			int v = Integer.parseInt(st.nextToken());
			
			Jewelry temp = new Jewelry(m, v);
			jewelries[i] = temp;
		}
		
		// bags ���� �Է�
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken()); 
			bags[i] = c;
		}
		
		// ���� ���� 
		Arrays.sort(bags);
		
		// ���� ���Լ� ����
		Arrays.sort(jewelries, Comparator.comparingInt(Jewelry::getWeight));
		
		// ���� ������ ���� �� 
		PriorityQueue<Jewelry> pq = new PriorityQueue<Jewelry>(Comparator.comparing(Jewelry::getValue).reversed());
		
		int jIndex = 0;
		long result = 0;
		// 1. ���� ���� �� ���� ���� ������ ���� <- ���� 
		for(int i = 0; i < bags.length; i++) {
			// 2. ���õ� ���濡 ���� �� �ִ� ���� ���� �� ���� ��� ������ ���� <- ���� ���
			while(jIndex < N && jewelries[jIndex].weight <= bags[i]) {
				pq.add(jewelries[jIndex++]);
			}
			// �ռ� ���濡 ������ �־��ٸ� ���� ��� ������ ���� result�� �־���
			if(!pq.isEmpty()) {
				result += pq.poll().value;
			}
		}
		System.out.println(result);
	}

}

class Jewelry {
	int weight;
	int value;
	
	public Jewelry(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}
	public int getWeight() {
		return weight;
	}
	public int getValue() {
		return value;
	}
}
