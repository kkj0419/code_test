import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		int remain = N - (a + b) + 1;
		int maxHeight = Math.max(a, b);

		if (remain < 0) {
			System.out.println(-1);
		} else {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			for (int i = 1; i < a; i++) {
				bw.write(i + " ");
				if (i == 1) {
					for (int j = 0; j < remain; j++) {
						bw.write(1 + " ");
					}
				}
			}
			bw.write(maxHeight + " ");        //top
			if (a == 1) {
				for (int i = 0; i < remain; i++) {
					bw.write(1 + " ");
				}
			}
			for (int i = b - 1; i > 0; i--) {
				bw.write(i + " ");
			}

			bw.flush();
			bw.close();
		}
	}
}
