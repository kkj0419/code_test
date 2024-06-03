import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int[][] map = new int[10][10];
	static int minCount = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 10; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		dfs(map, 0, new int[] {5, 5, 5, 5, 5});
		if (minCount == Integer.MAX_VALUE)
			minCount = -1;
		System.out.println(minCount);
	}

	private static void dfs(int[][] currmap, int currCount, int[] cnt) {
		int nextX = -1, nextY = -1;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (currmap[i][j] == 1) {
					nextX = i;
					nextY = j;
					break;
				}
			}
			if (nextX != -1)
				break;
		}

		if (nextX == -1 && nextY == -1) {
			minCount = Math.min(minCount, currCount);
			return;
		}

		for (int i = 0; i < cnt.length; i++) {
			if (cnt[i] > 0 && isAvailable(currmap, nextX, nextY, i + 1)) {
				int[][] nextMap = addRect(currmap, nextX, nextY, i + 1);
				int[] nextCnt = cnt.clone();
				nextCnt[i]--;
				dfs(nextMap, currCount + 1, nextCnt);
			}
		}

	}

	private static boolean isAvailable(int[][] map, int x, int y, int size) {
		if (x + size > 10 || y + size > 10)
			return false;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[x + i][y + j] == 0)
					return false;
			}
		}
		return true;
	}

	private static int[][] addRect(int[][] currMap, int x, int y, int size) {
		int[][] newMap = new int[10][10];
		for (int i = 0; i < 10; i++) {
			newMap[i] = currMap[i].clone();
		}

		for (int i = 0; i < size; i++) {
			Arrays.fill(newMap[x + i], y, y + size, 0);
		}
		return newMap;
	}
}
