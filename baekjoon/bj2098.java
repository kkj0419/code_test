import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] costs;
	static int[][] minCosts;    //{현재위치, 현재방문상태}

	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		costs = new int[N + 1][N + 1];
		minCosts = new int[N + 1][(int)Math.pow(2.0, N)];
		for (int i = 0; i < N; i++) {
			Arrays.fill(minCosts[i + 1], -1);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				costs[i + 1][j + 1] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[] isVisited = new boolean[N + 1];
		isVisited[1] = true;
		System.out.println(dfs(1, 1, isVisited));
	}

	//현재 위치에서 순회까지의 최소 비용 return
	private static int dfs(int start, int currIdx, boolean[] isVisited) {
		int currVisited = convertVisited(isVisited);
		if (currVisited == Math.pow(2.0, N) - 1) {
			if (costs[currIdx][start] != 0) {
				minCosts[currIdx][currVisited] = costs[currIdx][start];
				return costs[currIdx][start];
			} else {
				return Integer.MAX_VALUE;
			}
		}

		//방문하지 않은 경우에만 탐색
		if (minCosts[currIdx][currVisited] == -1) {
			minCosts[currIdx][currVisited] = Integer.MAX_VALUE;    //방문 처리
			for (int i = 1; i <= N; i++) {
				if (costs[currIdx][i] == 0 || isVisited[i])
					continue;
				isVisited[i] = true;
				int result = dfs(start, i, isVisited);
				if (result != Integer.MAX_VALUE) {
					minCosts[currIdx][currVisited] = Math.min(minCosts[currIdx][currVisited],
						costs[currIdx][i] + result);
				}
				isVisited[i] = false;
			}
		}

		return minCosts[currIdx][currVisited];
	}

	private static int convertVisited(boolean[] isVisited) {
		int result = 0;
		for (int i = 1; i <= N; i++) {
			if (isVisited[i]) {
				result += Math.pow(2.0, i - 1);
			}
		}
		return result;
	}
}
