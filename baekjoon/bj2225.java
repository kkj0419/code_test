import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static final int MAX_COUNT = 1000000000;
	static long[][] countDp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line.split(" ")[0]);
		int K = Integer.parseInt(line.split(" ")[1]);

		//init
		countDp = new long[K + 1][N + 1];	//K개를 뽑아 더한 합이 N개가 되는 경우의 수 = countDp[K][N]
		Arrays.fill(countDp[1], 1);

		for (int i = 2; i <= K; i++) {
			countDp[i][0] = 1;
			for (int j = 1; j <= N; j++) {
				countDp[i][j] = (countDp[i - 1][j] + countDp[i][j - 1]) % MAX_COUNT;
			}
		}
		System.out.println(countDp[K][N]);
	}
}
