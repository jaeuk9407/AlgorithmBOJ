package DAY01.P1713;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static int N;
	static int R;
	

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		HashMap<Integer, Integer> counts = new HashMap<>();
		N = sc.nextInt();
		R = sc.nextInt();
		
		// ���� �� �ʱ�ȭ
		List<Person> frames = new ArrayList<Person>(); 
		
		// i: ��ǥ index, t: ��õ ����� ��ȣ
		for(int i =0; i<R; i++) {
			int t = Integer.parseInt(sc.next());
			
			// �ش� �ĺ����� ���� �Է�
			Person person = new Person();
			person.setNum(t);
			person.setTimeStamp(i);
			
			// ���� �ȿ� �ش� �ĺ��ڰ� �ִ� ���
			// �ش� �ĺ��ڸ� ��õ ���� ���� �ִ� ��� -> counts�� �ش� �ĺ� count+1, person�� counts�� ����
			if(counts.get(t) != null) {
				int count = counts.get(t);
				
				// �ش� �ĺ��ڸ� frames���� ã�� count update
				for(int j = 0; j < frames.size(); j++) {
					if(frames.get(j).num == t) {
						Person updatePerson = frames.get(j);
						updatePerson.setCount(count+1);
					}
				}
				
				counts.replace(t, count+1);
			}else {
				// ���� �ȿ� �ش� �ĺ��ڰ� ���� ��� 
				// �ش� �ĺ��ڸ� ��õ ���� ���� ���� ��� -> count = 1�� person�� counts�� ����  
				person.setCount(1);
				counts.put(t, 1);
				
				// ���ڰ� �� �� ���
				if(frames.size() == N) {
					// count, timeStamp ���� �������� ����
					Collections.sort(frames, new Comparator<Person>() {
						@Override
						public int compare(Person o1, Person o2) {
							if(o1.count < o2.count) {
								return -1;
							}else if(o1.count == o2.count) {
								if(o1.timeStamp < o2.timeStamp) {
									return -1;
								}else {
									return 1;
								}
							}else {
								return 1;
							}
						}
					});
					
					// ���� �տ� �ִ� �ĺ� ���� -> frames, counts & ���ο� �ĺ� ����
					Person removePerson = (Person) frames.get(0);

					counts.remove(removePerson.num);
					frames.remove(0);
					frames.add(person);					
					
				}else {
					// ���ڰ� ������ ���� ���
					frames.add(person);
				}
			}
			
			
		} // End of All votes
		sc.close();
		Collections.sort(frames, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				if(o1.num < o2.num) {
					return -1;
				}else if(o1.num == o2.num) {
					return -1;
				}else {
					return 1;
				}
			}
		});
		
		for(int i = 0; i < frames.size(); i++) {
			System.out.print(frames.get(i).num);
			if(i != frames.size()-1) {
				System.out.print(" ");
			}
		}

	}

}

class Person {
	int num;
	int count;
	int timeStamp;
	
	
	
	public Person() {
		super();
	}



	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public int getTimeStamp() {
		return timeStamp;
	}



	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}



	public Person(int num, int count, int timeStamp) {
		super();
		this.num = num;
		this.count = count;
		this.timeStamp = timeStamp;
	}
	
	
}
