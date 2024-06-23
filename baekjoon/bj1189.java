import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int count = 0;
	static int[] dX = {0, 1, 0, -1};
	static int[] dY = {1, 0, -1, 0};
	static char[][] map;
	static int R, C, K;
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		boolean[][] isVisitied = new boolean[R][C];
		isVisitied[R-1][0] = true;
		dfs(R-1, 0, isVisitied, 1);
		System.out.println(count);
	}

	private static void dfs(int x, int y, boolean[][] isVisitied, int cost) {
		if (x == 0 && y == C-1 && cost == K) {
			count++;
			return;
		} else if (cost >= K || (x == 0 && y == C - 1)) {
			return;
		}

		for (int i = 0; i < dX.length; i++) {
			int nextX = x + dX[i], nextY = y + dY[i];
			if (isAvailable(nextX, nextY) && !isVisitied[nextX][nextY]) {
				isVisitied[nextX][nextY] = true;
				dfs(nextX, nextY, isVisitied, cost+1);
				isVisitied[nextX][nextY] = false;
			}
		}
	}

	private static boolean isAvailable(int x, int y) {
		return x>=0 && x<R && y>=0 && y<C && map[x][y] != 'T';
	}
}
