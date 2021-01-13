package DAY01.P1713;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainSolution {
	
	static int N, K;
	static int[] inputs;
	static Student[] students;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		
		// ����
		List<Student> list = new ArrayList<>();
		// �л��� ������ 1������ 100������
		students = new Student[101];
		
		for(int i= 0; i < K; i++) {
			int num = sc.nextInt();
			if(students[num] == null) {
				students[num] = new Student(num, 0, 0, false);
			}
			if(students[num].inIt == true) {
				students[num].count++;
			}else {
				// ������ ����� �� �� ��� 
				if(list.size() == N) {
					Collections.sort(list);
					Student p = list.remove(0);
					p.count = 0;
					p.inIt = false;
				}
				students[num].count = 1;
				students[num].inIt = true;
				students[num].timeStamp = i;
				list.add(students[num]);
			}
		}
	
		sc.close();
		
		// ��� ��, ��ȣ������ ����
		Collections.sort(list, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if(o1.num < o2.num) {
					return -1;
				}else if (o1.num == o2.num) {
					return 0;
				}else {
					return 1;
				}
			}
		});
		
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).num+" ");
		}
	}
}

class Student implements Comparable<Student>{
	int num;
	int count;
	int timeStamp;
	boolean inIt; // ���� ���ڿ� �ִ��� ����
	



	public Student(int num, int count, int timeStamp, boolean inIt) {
		super();
		this.num = num;
		this.count = count;
		this.timeStamp = timeStamp;
		this.inIt = inIt;
	}


	@Override
	public int compareTo(Student o) {
		int r1 = Integer.compare(count,  o.count);
		if(r1 == 0) {
			return Integer.compare(timeStamp, o.timeStamp);
		}else {
			return r1;
		}
	}


	@Override
	public String toString() {
		return "Student [num=" + num + ", count=" + count + ", timeStamp=" + timeStamp + ", inIt=" + inIt + "]";
	}
}