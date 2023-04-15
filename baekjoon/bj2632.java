import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int[] countA = new int[2000000 + 1];
	static int[] countB = new int[2000000 + 1];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int total = Integer.parseInt(br.readLine());
		int[] cnt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		//init
		int[] sizeA = new int[cnt[0]];
		int[] sizeB = new int[cnt[1]];

		for (int i = 0; i < sizeA.length; i++) {
			sizeA[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < sizeB.length; i++) {
			sizeB[i] = Integer.parseInt(br.readLine());
		}

		makeSum(sizeA, sizeB);

		int count = 0;
		for (int i = 0; i <= total; i++) {
			int a = i, b = total - i;
			count += (countA[a] * countB[b]);
		}
		System.out.println(count);
	}

	private static void makeSum(int[] sizeA, int[] sizeB) {

		int aLen = sizeA.length;
		for (int i = 0; i < aLen; i++) {
			int sum = sizeA[i];
			countA[sum]++;

			for (int j = i + 1; j < aLen; j++) {
				sum += sizeA[j];
				countA[sum]++;
			}
			for (int j = 0; j < i-1; j++) {
				sum += sizeA[j];
				countA[sum]++;
			}
		}
		countA[0] = 1;

		int bLen = sizeB.length;
		for (int i = 0; i < bLen; i++) {
			int sum = sizeB[i];
			countB[sum]++;
			for (int j = i + 1; j < bLen; j++) {
				sum += sizeB[j];
				countB[sum]++;
			}
			for (int j = 0; j < i - 1; j++) {
				sum += sizeB[j];
				countB[sum]++;
			}
		}
		countB[0] = 1;
	}
}
