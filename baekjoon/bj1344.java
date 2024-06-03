import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static float[][] nextPer = new float[2][2];
	static int[] goal = {0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18};

	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		float num1 = Float.parseFloat(br.readLine());
		float num2 = Float.parseFloat(br.readLine());
		nextPer[0][0] = (100f - num1) / 100f * (100f - num2) / 100f;
		nextPer[0][1] = (100f - num1) / 100f * (num2 / 100f);
		nextPer[1][0] = (num1 / 100f) * (100f - num2) / 100f;
		nextPer[1][1] = (num1 / 100f) * (num2 / 100f);

		float[][][] ans = new float[19][19][19];
		//
		ans[0][0][0] = 1;
		for (int n = 1; n <= 18; n++) {
			for (int a = 0; a <= n; a++) {
				for (int b = 0; b <= n; b++) {
					cal(n, a, b, ans);
				}
			}
		}

		float answer = 0;
		for (int i = 0; i < goal.length; i++) {
			for (int j = 0; j < goal.length; j++) {
				answer += ans[18][goal[i]][goal[j]];
			}
		}
		System.out.println(1f - answer);
	}

	private static void cal(int currCount, int a, int b, float[][][] ans) {

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				int preA = a - i, preB = b - j;
				if (isAvailable(preA, preB)) {
					ans[currCount][a][b] += ans[currCount - 1][preA][preB] * nextPer[i][j];
				}
			}
		}
	}

	//이전 a, b
	private static boolean isAvailable(int a, int b) {
		//upperbound 추가?
		return a >= 0 && b >= 0;
	}
}
