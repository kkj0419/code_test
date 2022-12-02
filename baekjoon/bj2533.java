import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Main {

	static ArrayList<Integer>[] linkedList;
	static int[] inbound;
	static boolean[] isVisited;
	static boolean[] isEarly;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		//init
		linkedList = new ArrayList[N + 1];
		inbound = new int[N + 1];
		isVisited = new boolean[N + 1];
		isEarly = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			linkedList[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			String line = br.readLine();
			int u1 = Integer.parseInt(line.split(" ")[0]);
			int u2 = Integer.parseInt(line.split(" ")[1]);

			inbound[u1]++;
			inbound[u2]++;
			linkedList[u1].add(u2);
			linkedList[u2].add(u1);
		}

		topologySort(N);
		System.out.println(countOfBooleanArr());
	}

	private static int countOfBooleanArr() {
		int count = 0;
		for (int i = 0; i < isEarly.length; i++) {
			if (isEarly[i]) {
				count++;
			}
		}
		return count;
	}

	private static void topologySort(int vertex) {

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= vertex; i++) {
			//leaf
			if (inbound[i] == 1) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int currVertex = queue.poll();
			isVisited[currVertex] =true;
			int parentVertex = findParent(currVertex);
			if (parentVertex != 0) {
				if (!isEarly[currVertex]) {
					isEarly[parentVertex] = true;
				}

				if (--inbound[parentVertex] == 1) {
					queue.add(parentVertex);
				}
			}
		}

	}

	private static int findParent(int leaf) {
		for (int i = 0; i < linkedList[leaf].size(); i++) {
			int fri = linkedList[leaf].get(i);
			if (!isVisited[fri]) {
				return fri;
			}
		}
		return 0;
	}
}
