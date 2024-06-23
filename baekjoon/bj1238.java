import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static class Edge{
		int dest;
		int cost;

		Edge(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
	static ArrayList<Edge>[] linkedlist;
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken())
			, X = Integer.parseInt(st.nextToken());

		//init
		linkedlist = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			linkedlist[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int curr = Integer.parseInt(st.nextToken());
			linkedlist[curr].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		int maxCost = 0;
		for (int i = 0; i < N; i++) {
			maxCost = Math.max(bfs(i + 1, X, N), maxCost);
		}
		System.out.println(maxCost);
	}

	private static int bfs(int start, int dest, int N) {

		int[] isGoVisited = new int[N + 1];
		int[] isComeVisited = new int[N + 1];
		bfs(isGoVisited, start);
		bfs(isComeVisited, dest);

		return isGoVisited[dest] + isComeVisited[start];
	}

	private static void bfs(int[] isVisited, int start) {
		Arrays.fill(isVisited, Integer.MAX_VALUE);
		isVisited[start] = 0;
		LinkedList<Integer> queue = new LinkedList<>();
		queue.addLast(start);
		while (!queue.isEmpty()) {
			int currIdx = queue.removeFirst();
			ArrayList<Edge> currList = linkedlist[currIdx];
			for (int i = 0; i < currList.size(); i++) {
				Edge next = currList.get(i);
				int nextCost = next.cost + isVisited[currIdx], nextIdx = next.dest;
				if (isVisited[nextIdx] > nextCost) {
					isVisited[nextIdx] = nextCost;
					queue.addLast(nextIdx);
				}
			}
		}
	}
}
