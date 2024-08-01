import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int maxCount = 0;
	static int[] numbers;
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		for (int i = 0; i < N; i++) {
			String number = br.readLine().replace("anta", "");
			number = number.replace("tica", "");
			numbers[i] = convertStringToBinary(number);
		}

		if (K < 5) {
			System.out.println(0);
			return;
		} else if (K == 26) {
			System.out.println(N);
			return;
		}
		boolean[] alpha = new boolean[26];
		alpha[0] = true; alpha[2] = true; alpha[8] = true; alpha[13] = true; alpha[19] = true;
		dfs(alpha, 0, K, 5);
		System.out.println(maxCount);
	}

	private static void dfs(boolean[] isVisited, int idx, int maxWordCount, int currWordCount){
		if(idx == 26 || currWordCount == maxWordCount){
			int count = 0, res = convertBooleanToBinary(isVisited);
			for (int i = 0; i < numbers.length; i++) {
				if ((numbers[i] & res) == numbers[i]) count++;
			}
			maxCount = Math.max(count, maxCount);
			return;
		}
		//continue..
		if(isVisited[idx]){
			dfs(isVisited, idx+1, maxWordCount, currWordCount);
			return;
		}

		isVisited[idx] = true;
		dfs(isVisited, idx+1, maxWordCount, currWordCount+1);
		isVisited[idx] = false;
		dfs(isVisited, idx+1, maxWordCount, currWordCount);
	}

	private static int convertBooleanToBinary(boolean[] isVisited){
		int ans = 0;
		for (int i = 0; i < isVisited.length; i++) {
			if(isVisited[i]){
				ans |= (2 << i);
			}
		}
		return ans;
	}

	private static int convertStringToBinary(String s) {
		int ans = 0;
		for (int i = 0; i < s.length(); i++) {
			ans |= (2 << (s.charAt(i) - 'a'));
		}
		return ans;
	}
}
