import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int[] jewels = new int[M];
		for(int i=0; i<M; i++){
			jewels[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(jewels);
		long left = 1, right = jewels[M-1];

		long ans = 0;
		while(left <= right){
			long mid = (left + right)/2;
			if(isAvailable(jewels, N, mid)){
				ans = mid;
				right = mid - 1;
			}else{
				left = mid + 1;
			}
		}
		System.out.println(ans);
	}

	private static boolean isAvailable(int[] arr, int N, long value){
		int minCnt = 0;
		for(int i=0; i<arr.length; i++){
			if (arr[i] % value == 0) {
				minCnt += arr[i]/value;
			}else{
				minCnt += arr[i] / value + 1;
			}
		}
		return N >= minCnt;
	}
}
