import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static int MAX_VALUE = 1000000 + 1;
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		//cal
		int[] ans = new int[arr.length];
		Arrays.fill(ans, MAX_VALUE);

		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			int idx = Arrays.binarySearch(ans, arr[i]);

			if (idx < 0) {
				idx = -idx - 1;
			}

			ans[idx] = arr[i];
			max = Math.max(max, idx + 1);
		}

		System.out.println(max);
	}
}
