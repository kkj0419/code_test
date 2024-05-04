import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static class Scv{
		int[] value;
		int count;

		Scv(int[] value, int count) {
			this.value = value;
			this.count = count;
		}
	}
	static int N;
	static int[] scv = new int[3];
	static int[][] attack = {{9, 3, 1}, {3, 1, 9}, {1, 9, 3}, {9, 1, 3}, {3, 9, 1}, {1, 3, 9}};
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());
			int[][][] minCount = init(br.readLine());
			System.out.println(bfs(minCount, new Scv(scv, 0)));

		}

		private static int[][][] init(String line){
			StringTokenizer st = new StringTokenizer(line);
			for (int i = 0; i < N; i++) {
				scv[i] = Integer.parseInt(st.nextToken());
			}
			int[][][] minValue = new int[scv[0] + 1][scv[1] + 1][scv[2] + 1];
			for (int a = 0; a < scv[0] + 1; a++) {
				for (int b = 0; b < scv[1] + 1; b++) {
					Arrays.fill(minValue[a][b], Integer.MAX_VALUE);
				}
			}
			minValue[scv[0]][scv[1]][scv[2]] = 0;
			return minValue;
		}

		private static int bfs(int[][][] count, Scv start){
			LinkedList<Scv> queue = new LinkedList<>();
			queue.addLast(start);
			while (!queue.isEmpty()) {
				Scv curr = queue.removeFirst();
				for (int i = 0; i < attack.length; i++) {
					int[] next = new int[3];
					int[] currValue = curr.value;
					for (int j = 0; j < 3; j++) {
						next[j] = (currValue[j] > attack[i][j]) ? currValue[j] - attack[i][j] : 0;
					}
					if (isClear(next)) {
						return curr.count + 1;
					} else if(count[next[0]][next[1]][next[2]] > curr.count + 1){
						count[next[0]][next[1]][next[2]] = curr.count + 1;
						queue.addLast(new Scv(next, curr.count + 1));
					}
				}
			}
			return 0;
		}

	private static boolean isClear(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0) {
				return false;
			}
		}
		return true;
	}
}
