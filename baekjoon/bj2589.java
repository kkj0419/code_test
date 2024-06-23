import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static class Vertex{
		int x;
		int y;
		int cost;

		Vertex(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
	static int[] dX = {0, 1, 0, -1};
	static int[] dY = {1, 0, -1, 0};
	static int maxCost = 0;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken()), width = Integer.parseInt(st.nextToken());
		map = new char[height][width];
		for (int i = 0; i < height; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (map[i][j] == 'L') {
					bfs(height, width, i, j);
				}
			}
		}
		System.out.println(maxCost);
	}

	private static void bfs(int height, int width, int x, int y) {
		boolean[][] isVisited = new boolean[height][width];
		isVisited[x][y] = true;
		LinkedList<Vertex> queue = new LinkedList<>();
		queue.addLast(new Vertex(x, y, 0));
		while (!queue.isEmpty()) {
			Vertex curr = queue.removeFirst();
			maxCost = Math.max(maxCost, curr.cost);
			for (int i = 0; i < dX.length; i++) {
				int nextX = curr.x + dX[i], nextY = curr.y + dY[i];
				if (isAvailable(height, width, nextX, nextY) && !isVisited[nextX][nextY]) {
					isVisited[nextX][nextY] = true;
					queue.addLast(new Vertex(nextX, nextY, curr.cost + 1));
				}
			}
		}
	}

	private static boolean isAvailable(int height, int width, int x, int y) {
		return x>=0 && x<height && y>=0 && y<width && map[x][y] == 'L';
	}
}
