import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Info {
		int cost;
		int days;

		Info(int days, int cost){
			this.cost = cost;
			this.days = days;
		}
	}

	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<Info> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			list.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		int day = n, sum = 0, index = 0;
		list.sort(((o1, o2) -> o2.days - o1.days));
		PriorityQueue<Info> priorityQueue = new PriorityQueue<>((o1, o2) -> o2.cost - o1.cost);
		while(day >0){
			for (; index < n; index++) {
				Info curr = list.get(index);
				if(day <= curr.days)
					priorityQueue.add(curr);
				else break;
			}

			if (!priorityQueue.isEmpty()) {
				Info curr = priorityQueue.poll();
				sum+= curr.cost;
			}
			day--;
		}
		System.out.println(sum);
	}
}
