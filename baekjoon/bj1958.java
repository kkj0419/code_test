import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		String s3 = br.readLine();
		//[i][j][k]
		int[][][] length = new int[s1.length() + 1][s2.length() + 1][s3.length() + 1];
		for (int i = 1; i <= s1.length(); i++) {
			char c1 = s1.charAt(i - 1);
			for (int j = 1; j <= s2.length(); j++) {
				char c2 = s2.charAt(j - 1);
				for (int k = 1; k <= s3.length(); k++) {
					char c3 = s3.charAt(k - 1);
					if (c1 == c2 && c2 == c3) {
						length[i][j][k] = length[i - 1][j - 1][k - 1] + 1;
					} else {
						length[i][j][k] = Math.max(length[i - 1][j][k],
							Math.max(length[i][j - 1][k], length[i][j][k - 1]));
					}
				}
			}
		}

		System.out.println(length[s1.length()][s2.length()][s3.length()]);
	}
}
