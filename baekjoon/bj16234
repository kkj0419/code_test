import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static class Vertex{
		int x;
		int y;

		Vertex(int x, int y) {
			this.x= x;
			this.y = y;
		}
	}
	static int[] dX = {0, 1, 0, -1};
	static int[] dY = {1, 0, -1, 0};

	static int N, L ,R;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		int count = 0;
		while(true) {
			boolean[][] isVisited = new boolean[N][N];
			boolean flag = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!isVisited[i][j] && bfs(isVisited, i, j)) {
						flag = true;
					}
				}
			}
			count = flag ? count + 1 : count;
			if (!flag) {
				System.out.println(count);
				return;
			}

		}
	}

	private static boolean bfs(boolean[][] isVisited, int x, int y) {
		LinkedList<Vertex> queue = new LinkedList<>();
		ArrayList<Vertex> united = new ArrayList<>();
		Vertex curr = new Vertex(x, y);
		int sum = map[x][y];
		queue.addLast(curr);
		united.add(curr);
		isVisited[x][y] = true;
		while (!queue.isEmpty()) {
			curr = queue.removeFirst();
			for (int i = 0; i < dX.length; i++) {
				int nextX = curr.x + dX[i], nextY = curr.y +dY[i];
				if (isAvailable(nextX, nextY) && !isVisited[nextX][nextY]) {
					int diff = Math.abs(map[curr.x][curr.y] - map[nextX][nextY]);
					if (diff >= L && diff <= R) {
						Vertex next = new Vertex(nextX, nextY);
						sum += map[nextX][nextY];
						isVisited[nextX][nextY] = true;
						united.add(next);
						queue.addLast(next);
					}
				}
			}
		}

		if (united.size() != 1) {
			int renew = sum / united.size();
			for (int i = 0; i < united.size(); i++) {
				curr = united.get(i);
				map[curr.x][curr.y] = renew;
			}
			return true;
		}
		return false;
	}

	private static boolean isAvailable(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
