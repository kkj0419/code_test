import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int[] start;
	static int[] end;
	static List<int[]> stops;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 0; t < testcase; t++) {
			int passby = Integer.parseInt(br.readLine());
			//start
			start = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			stops = new ArrayList<>();
			for (int i = 0; i < passby; i++) {
				stops.add(
					Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
			}
			Collections.sort(stops, (o1, o2) -> {
				int value1 = Math.abs(start[1] - o1[1]) + Math.abs(start[0] - o1[0]);
				int value2 = Math.abs(start[1] - o2[1]) + Math.abs(start[0] - o2[0]);
				return value1 - value2;
			});

			//destination
			end = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			if (travel()) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
		}
	}

	private static boolean travel() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[] isVisited = new boolean[stops.size()];
		queue.add(start);
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			if (canMove(curr, end)) {
				return true;
			}

			for (int i = 0; i < stops.size(); i++) {
				if (!canMove(curr, stops.get(i)) || isVisited[i]) {
					continue;
				}
				queue.add(stops.get(i));
				isVisited[i] = true;
			}
		}
		return false;
	}

	private static boolean canMove(int[] depart, int[] arrival) {
		return 1000 >= Math.abs(arrival[1] - depart[1]) + Math.abs(arrival[0] - depart[0]);
	}
}
