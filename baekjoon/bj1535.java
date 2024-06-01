import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] loss;
	static int[] joy;
	static int maxJoy = 0;
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		loss = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		joy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		dfs(-1, 100, 0, N);
		System.out.println(maxJoy);
	}

	private static void dfs(int currIdx, int currHp, int currJoy, int N) {
		if (currHp <= 0) {
			return;
		}
		maxJoy = Math.max(maxJoy, currJoy);
		if (currIdx < N - 1) {
			dfs(currIdx + 1, currHp - loss[currIdx+1], currJoy + joy[currIdx+1], N);
			dfs(currIdx + 1, currHp, currJoy, N);
		}
	}
}
