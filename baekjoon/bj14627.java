import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		long left = 1, right = 0;
		long[] length = new long[s];
		for (int i = 0; i < s; i++) {
			length[i] = Long.parseLong(br.readLine());
			right = Math.max(right, length[i]);
		}

		long pha = calLength(left, right, c, length);
		long sum = 0;
		for (int i = 0; i < s; i++) {
			sum += length[i];
		}
		System.out.println(sum - pha*(long)c);


	}

	private static long calLength(long left, long right, int c,  long[] length){
		//cal
		while(left < right){
			long mid = (left + right)/2;
			long count = 0;
			for (int i = 0; i < length.length; i++) {
				count += (length[i] / mid);
			}

			if (count < c) {
				right = mid-1;
			}else{
				left = mid+1;
			}
		}
		return right;
	}
}
