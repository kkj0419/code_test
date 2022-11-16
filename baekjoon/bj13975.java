import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		PriorityQueue<Long> queue;
		for (int i = 0; i < testcase; i++) {
			queue = new PriorityQueue<>();
			br.readLine();
			queue.addAll(
				Arrays.stream(br.readLine().split(" ")).mapToLong(Long::valueOf).boxed().collect(Collectors.toList()));
			long cost = 0;
			while (queue.size() > 1) {
				long sum = 0;
				sum += (queue.poll() + queue.poll());
				queue.add(sum);
				cost += sum;
			}
			System.out.println(cost);
		}
	}
}
