import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

	static int[] dX = {0, 1, 0, -1};
	static int[] dY = {1, 0, -1, 0};

	static final int MAX_COST = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = 1;
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			System.out.printf("Problem %d: %d%n", count++, findMinRoutes(N, map));
		}
	}

	private static int findMinRoutes(int size, int[][] map) {
		int[][] minCosts = new int[size][size];
		for (int i = 0; i < size; i++) {
			Arrays.fill(minCosts[i], MAX_COST);
		}
		minCosts[0][0] = map[0][0];
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		queue.add(new Vertex(0, 0, minCosts[0][0]));
		while (!queue.isEmpty()) {
			Vertex currVertex = queue.poll();
			if (currVertex.x == size - 1 && currVertex.y == size - 1) {
				break;
			}

			for (int i = 0; i < dX.length; i++) {
				int nextX = currVertex.x + dX[i], nextY = currVertex.y + dY[i];
				if (isAvailable(nextX, nextY, minCosts)) {
					int nextCost = currVertex.cost + map[nextX][nextY];
					queue.add(new Vertex(nextX, nextY, nextCost));
					minCosts[nextX][nextY] = nextCost;
				}
			}
		}
		return minCosts[size - 1][size - 1];
	}

	private static boolean isAvailable(int x, int y, int[][] isVisited) {
		int size = isVisited.length;
		return x >= 0 && x < size && y >= 0 && y < size && isVisited[x][y] == MAX_COST;
	}
}

class Vertex implements Comparable<Vertex> {
	int x;
	int y;
	int cost;

	public Vertex(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	//cost ASC
	@Override
	public int compareTo(Vertex o) {
		return this.cost - o.cost;
	}
}
