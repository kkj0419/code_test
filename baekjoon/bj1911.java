import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), L = Integer.parseInt(st.nextToken());
		int[][] pond = new int[N][2];
		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine());
			pond[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}

		Arrays.sort(pond, Comparator.comparingInt(o -> o[0]));
		int count = 0, curr = pond[0][0];
		for (int i = 0; i < N; i++) {
			curr = (Math.max(curr, pond[i][0]));
			float currLine = pond[i][1] - curr;
			int currCount = (int)Math.ceil(currLine / L);
			curr += (currCount * L);
			count+=currCount;
		}
		System.out.println(count);
	}
}
