import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i + 1] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
			int len = end - start + 1;
			boolean isPal = true;
			for (int j = 0; j < len / 2; j++) {
				if (arr[start + j] != arr[end - j]) {
					isPal = false;
					break;
				}
			}
			bw.write(isPal ? "1\n" : "0\n");
		}

		bw.flush();
		bw.close();
	}
}
