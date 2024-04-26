import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		Arrays.sort(input);
		Map<Character, Integer> chars = new HashMap<>();
		Set<Character> characterSet = new HashSet<>();
		for (int i = 0; i < input.length; i++) {
			chars.put(input[i], chars.getOrDefault(input[i], 1));
			if (characterSet.contains(input[i])) {
				characterSet.remove(input[i]);
			} else {
				characterSet.add(input[i]);
			}
		}

		if (characterSet.size() > 1) {
			System.out.println("I'm Sorry Hansoo");
		} else {
			System.out.println(makeWords(input));
		}
	}

	private static String makeWords(char[] input) {
		StringBuilder sb = new StringBuilder();
		LinkedList<Character> list = new LinkedList<>();
		list.addLast(input[0]);
		for (int i = 1; i < input.length; i++) {
			if (!list.isEmpty() && list.getLast() == input[i]){
				sb.append(list.removeLast());
			} else {
				list.addLast(input[i]);
			}
		}
		String middle = "";
		for(Character c : list){
			middle += c;
		}
		return  sb.toString() + middle + sb.reverse();
	}
}
