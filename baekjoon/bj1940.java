import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int cnt = 0;
		Set<Integer> numbers = new HashSet<>();
		for(int i=0; i<N; i++){
			int number = Integer.parseInt(st.nextToken());
			if (numbers.contains(M - number)) cnt++;
			numbers.add(number);
		}
		System.out.println(cnt);
	}
}
