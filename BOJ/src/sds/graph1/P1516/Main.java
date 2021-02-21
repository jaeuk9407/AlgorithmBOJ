package sds.graph1.P1516;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int minTime[];
	static int timeCost[];
	static boolean[] visited;
	static ArrayList<Integer> pre[];
	static ArrayList<Integer> outs[];
	static Queue<Integer> q = new LinkedList<>();
 	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		pre = new ArrayList[N + 1];
		outs = new ArrayList[N + 1]; 
		visited = new boolean[N + 1];
		minTime = new int[N + 1];
		timeCost = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			pre[i] = new ArrayList<>();
			outs[i] = new ArrayList<>();
//			minTime[i] = 60000000;	// ��� �ִ� �����ð� 50000000 
		}
		
		StringTokenizer st;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			timeCost[i] = Integer.parseInt(st.nextToken());
			
			// �Է� ���� �� ������ 
			while(st.hasMoreTokens()){
				// �޾Ƽ� -1�� �ƴϸ� ���� ��带 �����ϱ� ���� �ʿ��� �����湮��带 ���� 
				int num = Integer.parseInt(st.nextToken());
				if(num != -1) {
					pre[i].add(num);
					outs[num].add(i);
				}
			}
		} // end of input processing
		
		topologySort();
		
		for(int i = 1; i <= N; i++) {
			System.out.println(minTime[i]);
		}
		
	} // end of main
	
	private static void topologySort() {
		// �ʿ��� �����湮��尡 ���ٸ� ť�� �־���
		for(int i = 1; i <= N; i++) {
			if(pre[i].isEmpty()) {
				q.add(i);
				minTime[i] = timeCost[i];
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			// ���� ��带 ���Գ��� ���� ������ ���԰����� �����ش�
			if(!outs[now].isEmpty()) {
				// ���Ⱓ���� �ϳ��� ������
				for(int i = 0; i < outs[now].size(); i++) {
					int next = outs[now].get(i);
					// ���Ⱓ���� ����� ���� ����� ���԰����� �ϳ��� Ȯ���غ���
					for(int j = 0; j < pre[next].size(); j++) {
						int preNode = pre[next].get(j);
						// �����忡�� ���Ⱓ���� �ش��ϴ� ���԰����� ã���� �����ش�.
						if(preNode == now) {
							pre[next].remove(j);
							// ������ ������ �ּ� �ð��� �����Ѵ�. (����)
							int tmp = minTime[now] + timeCost[next];
							if(minTime[next] < tmp) {
								minTime[next] = tmp;
							}
							// ���԰����� ������ ���� ��尡 ���̻� ���԰����� ������
							if(pre[next].isEmpty()) {
								// ť�� �߰��ϰ� �ּҽð��� �����Ѵ�. 
								q.add(next);
							}
						}
					}
				}
			}
		}
	}
}
