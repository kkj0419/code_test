import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static class Point {
		int r;
		int c;
		int cost;

		public Point(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
	}

	static int[][] isVisited;
	static boolean[][] isFlamed;

	static final int[] dX = {0, 1, 0, -1};
	static final int[] dY = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//init
		int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
		isVisited = new int[R][C];
		isFlamed = new boolean[R][C];
		LinkedList<Point> moves = new LinkedList<>();
		LinkedList<Point> flames = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (line[j] == '#') {
					isVisited[i][j] = -1;
				} else if (line[j] == 'J') {
					isVisited[i][j] = 1;
					moves.add(new Point(i, j, 0));
				} else if (line[j] == 'F') {
					isFlamed[i][j] = true;
					flames.add(new Point(i, j, 0));
				}
			}
		}

		//cal
		int cost = 0;
		while (!moves.isEmpty()) {
			flaming(flames, cost);
			if (moving(moves, cost)) {
				System.out.println(cost + 1);
				return;
			}
			cost++;
		}
		System.out.println("IMPOSSIBLE");
	}

	private static void flaming(LinkedList<Point> flames, int currCost) {
		while (!flames.isEmpty() && flames.getFirst().cost == currCost) {
			Point curr = flames.removeFirst();
			for (int i = 0; i < dX.length; i++) {
				int nextR = curr.r + dX[i], nextC = curr.c + dY[i];
				int nextCost = curr.cost + 1;
				if (!isOutOfBound(nextR, nextC) && !isFlamed[nextR][nextC] && isVisited[nextR][nextC] != -1) {
					isFlamed[nextR][nextC] = true;
					flames.addLast(new Point(nextR, nextC, nextCost));
				}
			}
		}
	}

	private static boolean isOutOfBound(int r, int c) {
		int R = isVisited.length, C = isVisited[0].length;
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

	private static boolean moving(LinkedList<Point> moves, int currCost) {
		while (!moves.isEmpty() && moves.getFirst().cost == currCost) {
			Point curr = moves.removeFirst();

			for (int i = 0; i < dX.length; i++) {
				int nextR = curr.r + dX[i], nextC = curr.c + dY[i];
				int nextCost = curr.cost + 1;
				if (isOutOfBound(nextR, nextC)) {
					return true;
				}

				if (isVisited[nextR][nextC] == 0 && !isFlamed[nextR][nextC]) {
					isVisited[nextR][nextC] = 1;
					moves.addLast(new Point(nextR, nextC, nextCost));
				}
			}
		}

		return false;
	}
}
