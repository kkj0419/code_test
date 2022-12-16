import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N, S;
	static int[] maxCost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		N = Integer.parseInt(input.split(" ")[0]);
		S = Integer.parseInt(input.split(" ")[1]);

		int[][] arts = new int[N][2];
		for (int i = 0; i < N; i++) {
			arts[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		//높이 asc, 비용 desc
		Arrays.sort(arts, (o1, o2) -> {
			if (o1[0] == o2[0])
				return o2[1] - o1[1];
			return o1[0] - o2[0];
		});
		maxCost = new int[N];
		int[] sortedHeights = Arrays.stream(arts).mapToInt(i -> i[0]).toArray();
		//dp
		maxCost[0] = arts[0][1];
		for (int i = 1; i < N; i++) {
			int currHeight = arts[i][0];
			int currCost = arts[i][1];
			int findIdx = Arrays.binarySearch(sortedHeights, currHeight - S);
			if (findIdx != -1) {
				if (findIdx < 0) {
					findIdx = -(findIdx + 1) - 1;
				}
				maxCost[i] = maxCost[findIdx] + currCost;
			} else {
				maxCost[i] = currCost;
			}
			maxCost[i] = Math.max(maxCost[i - 1], maxCost[i]);
		}

		System.out.println(maxCost[maxCost.length - 1]);
	}
}

