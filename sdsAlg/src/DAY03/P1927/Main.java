package DAY03.P1927;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		MinHeap mh = new MinHeap();
		
		for (int i = 0; i < N; i++) {
			int request  = sc.nextInt();
			if(request != 0) {
				mh.insert(request);
			}else {
				if(mh.list.size() <= 1) {
					System.out.println(0);
					continue;
				}else {
					System.out.println(mh.delete());
				}
			}
		}
		
		sc.close();

	}

}

class MinHeap{
	List<Integer> list;
	
	public MinHeap() {
		list = new ArrayList<>();
		list.add(0);
	}
	
	public void insert(int val) {
		// ���� ���� �׸� �߰� 
		list.add(val);
		
		int current = list.size() - 1;
		int parent = current / 2;
		
		
		while(true) {
			// root�� ������ ��� 
			if(current == 1) {
				break;
			}
			// �θ� ������ ���� ��� -> ������ ����
			if (list.get(current) > list.get(parent)) {
				break;
			}else {
				// �θ� ������ ū ��� -> ������ �Ҹ��� -> �ڸ� �ٲ��ֱ�
				int temp = 0;
				temp = list.get(parent);
				list.set(parent, list.get(current));
				list.set(current, temp);
				
				current = parent;
				parent = current / 2;
				
			}
			
		}
		
	}
	public int delete() {
		// ��Ʈ ���� ���� �� ���� ������ ���� ��Ʈ�� ������
		int top = list.get(1);
		list.set(1, list.get(list.size() - 1));
		list.remove(list.size() - 1);
		
		int currentPos = 1;
		
		while(true) {
			// �ڽ� ���� ���� Ȯ��
			int leftPos = currentPos * 2;
			int rightPos = currentPos * 2 + 1;
			
			// �ڽ� ���� X
			if(leftPos >= list.size()) {
				break;
			}
			// ����, ���� �� ���� �� ����
			int minValue = list.get(leftPos);
			int minPos = leftPos;
			
			// ������ ���� �����ϰ�, ���������� ������ minValue, minPos Update
			if(rightPos < list.size() && list.get(rightPos) < minValue) {
				minValue = list.get(rightPos);
				minPos = rightPos;
			}
			
			// ������ �ڽ��� �ڽź��� ���� ���
			if(minValue < list.get(currentPos)) {
				int temp = list.get(currentPos);
				list.set(currentPos, list.get(minPos));
				list.set(minPos, temp);
				currentPos = minPos;
			}else {
				// ������ �ڽ��� �ڽź��� ū ���
				break;
			}
		}
		return top;
	}
}