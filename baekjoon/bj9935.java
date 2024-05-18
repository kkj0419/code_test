import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	static char[] bomb;
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		bomb = br.readLine().toCharArray();

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			stack.push(s.charAt(i));

			int count = 0;
			if (stack.size() >= bomb.length) {
				for (int j = 0; j < bomb.length; j++) {
					if (stack.get(stack.size() - bomb.length + j) != bomb[j]) {
						break;
					}
					count++;
				}

				if (count == bomb.length) {
					for (int j = 0; j < bomb.length; j++) {
						stack.pop();
					}
				}
			}
		}

		if (stack.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < stack.size(); i++) {
				sb.append(stack.get(i));
			}
			System.out.println(sb);
		}
	}
}
