import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int[][] line = new int[2][100000+1];		//{count, pre} (세로)
	static final int[] move = {-1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Arrays.fill(line[0], Integer.MAX_VALUE);
		Arrays.fill(line[1], -1);
		line[0][N] = 0;
		line[1][N] = -1 * Integer.MAX_VALUE;

		findBfs(N, K);

		int next = K;
		String[] numbers = new String[line[0][K] + 1];
		for (int i = 0; i < numbers.length; i++) {
			numbers[numbers.length - (1 + i)] = String.valueOf(next);
			next = line[1][next];
		}
		System.out.println(line[0][K]);
		System.out.println(String.join(" ", numbers));
	}

	private static void findBfs(int start, int end){
		LinkedList<int[]> queue = new LinkedList<>();
		queue.addLast(new int[]{start, 0});
		while (!queue.isEmpty()) {
			int[] curr = queue.removeFirst();
			for (int i = 0; i < move.length + 1; i++) {
				int[] next;
				if (i == move.length) {
					next = new int[]{curr[0] * 2, curr[1] + 1};
				}else {
					next = new int[]{curr[0] + move[i], curr[1] + 1};
				}

				if(next[0] >= 0 && next[0] <= 100000 && line[0][next[0]] > next[1]){
					line[0][next[0]] = next[1];
					line[1][next[0]] = curr[0];
					queue.addLast(next);
				}

				if(next[0] == end)	return;
			}
		}
	}
}
