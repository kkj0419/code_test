import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] times = new int[M];
		st = new StringTokenizer(br.readLine());
		int maxTime = 0;
		for (int i = 0; i < M; i++) {
			times[i] = Integer.parseInt(st.nextToken());
			maxTime = Math.max(maxTime, times[i]);
		}
		long left = 1, right = maxTime*N;
		long time = getTime(left, right, N, times);
		long count = times.length;
		for (int i = 0; i < times.length; i++) {
			count += (time / times[i]);
		}
		int number = -1;
		if(time != 0){
			time+=1;
			for (int i = 0; i < times.length; i++) {
				if (time % times[i] == 0) {
					count++;
				}

				if (count == N) {
					number = i+1;
					break;
				}
			}
		}else {
			number = (int)N;
		}
		System.out.println(number);
	}

	private static long getTime(long left, long right, long N, int[] times) {
		while (left <= right) {
			long mid = (left + right)/2;
			long count = times.length;
			for (int i = 0; i < times.length; i++) {
				count += (mid / times[i]);
			}

			if (count >= N) {
				right = mid-1;
			}else{
				left = mid+1;
			}
		}
		return right;
	}
}
