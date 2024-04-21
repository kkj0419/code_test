import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		while((input = br.readLine()) != null ) {
			int n = Integer.parseInt(input);
			long cnt = 1, curr = 1;
			while (true) {
				if (curr % n == 0) {
					System.out.println(cnt);
					break;
				}
				curr = (curr * 10 + 1) % n;
				cnt++;
			}

		}
	}
}
