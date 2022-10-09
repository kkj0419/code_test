import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static boolean[] isNo;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		long min = Long.parseLong(line.split(" ")[0]);
		long max = Long.parseLong(line.split(" ")[1]);

		int length = (int)(max - min) + 1;
		isNo = new boolean[length];
		Arrays.fill(isNo, true);
		for (long i = 2; i <= Math.sqrt(max); i++) {
			double squareNum = i * i;
			long start = (long)Math.ceil(min / squareNum);
			long bound = (long)Math.floor(max / squareNum);
			for (long j = start; j <= bound; j++) {
				int index = (int)(j * squareNum - min);
				if (index >= length && !isNo[index]) {
					break;
				}
				isNo[index] = false;
			}
		}
		System.out.print(counts());
	}

	private static int counts() {
		int count = 0;
		for (int i = 0; i < isNo.length; i++) {
			if(isNo[i])
				count++;
		}
		return count;
	}
}
