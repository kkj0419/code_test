import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] fix = new int[M + 1];
		for (int i = 0; i < M; i++) {
			fix[i] = Integer.parseInt(br.readLine());
		}
		fix[M] = N + 1;

		int[][] dp = new int[2][N];
		dp[0][0] = 0;
		dp[1][0] = 1;
		for (int i = 1; i < N; i++) {
			dp[0][i] = dp[1][i-1];
			dp[1][i] = dp[0][i-1] + dp[1][i-1];
		}

		int ans = 1;
		int preIdx = 0;
		for (int i = 0; i < fix.length; i++) {
			int currIdx = fix[i] - 1;
			if (currIdx == preIdx) {
				preIdx = fix[i];
				continue;
			}
			ans *= (dp[1][currIdx - preIdx - 1] + dp[0][currIdx - preIdx - 1]);
			preIdx = fix[i];
		}


		System.out.println(ans);
	}
}
