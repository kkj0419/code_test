import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int count = 0;
	static char[][] input = new char[5][5];

	static boolean[] isVisited = new boolean[25];
	static int[][] dMove = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //RIGHT, DOWN, LEFT, UP
	static int[] dIdx = {1, 5, -1, -5};
	static int[] indexY = {0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4};
	static int[] indexX = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			input[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < 25; i++) {
			int x = indexX[i], y = indexY[i];
			combination(1, (input[x][y] == 'S' ? 1 : 0), i);
		}
		System.out.println(count);
	}

	private static void combination(int depth, int sCount, int currIdx) {
		isVisited[currIdx] = true;
		if (depth == 7) {
			if (sCount >= 4 && isConnected(currIdx)) {
				count++;
			}
			isVisited[currIdx] = false;
			return;
		}

		for (int i = currIdx + 1; i < 25; i++) {
			int dX = indexX[i], dY = indexY[i];
			combination(depth + 1, sCount + (input[dX][dY] == 'S' ? 1 : 0), i);
		}
		isVisited[currIdx] = false;
	}

	private static boolean isConnected(int startIdx) {
		int connectedCount = 1;

		boolean[] isSearched = new boolean[25];
		Queue<Integer> queue = new LinkedList<>();
		isSearched[startIdx] = true;
		queue.add(startIdx);
		while (!queue.isEmpty()) {
			int currIdx = queue.poll();
			int currX = indexX[currIdx], currY = indexY[currIdx];
			for (int i = 0; i < 4; i++) {
				int nextX = currX + dMove[i][0], nextY = currY + dMove[i][1];
				int nextIdx = currIdx + dIdx[i];
				if (!isOutOfRange(nextX, nextY) && isVisited[nextIdx] && !isSearched[nextIdx]) {
					connectedCount++;
					isSearched[nextIdx] = true;
					queue.add(nextIdx);
				}
			}
		}

		return connectedCount == 7;
	}

	private static boolean isOutOfRange(int x, int y ) {
		return x >= 5 || x < 0 || y >= 5 || y < 0;
	}
}
