import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {

	static int L, C;
	static List<String> codeList = new ArrayList<>();

	static final List<Character> vowel = List.of('a', 'e', 'i', 'o', 'u');
	static final int MIN_VOWEL_COUNT = 1;
	static final int MIN_CONSONANT_COUNT = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		L = Integer.parseInt(line.split(" ")[0]);
		C = Integer.parseInt(line.split(" ")[1]);

		String[] charArr = br.readLine().split(" ");
		Arrays.sort(charArr);    //사전순 sort

		LinkedList<String> currList = new LinkedList<>();
		for (int i = 0; i <= charArr.length - L; i++) {
			currList.addLast(charArr[i]);
			selectCode(charArr, i, currList);
			currList.removeLast();
		}

		Collections.sort(codeList);
		print();
	}

	private static void selectCode(String[] charArr, int currIdx, LinkedList<String> currCharList) {
		if (currCharList.size() == L) {        //leaf
			String currString = toString(currCharList);
			if (isApplicable(currString)) {
				codeList.add(currString);
			}
			return;
		}

		for (int i = currIdx + 1; i < charArr.length; i++) {
			currCharList.addLast(charArr[i]);
			selectCode(charArr, i, currCharList);
			currCharList.removeLast();
		}

	}

	private static String toString(List<String> stringList) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stringList.size(); i++) {
			sb.append(stringList.get(i));
		}
		return sb.toString();
	}

	private static boolean isApplicable(String s) {
		int vowelCount = 0, consonantCount = 0;
		for (int i = 0; i < s.length(); i++) {
			if (vowel.contains(s.charAt(i))) {
				vowelCount++;
			} else {
				consonantCount++;
			}
		}
		return vowelCount >= MIN_VOWEL_COUNT && consonantCount >= MIN_CONSONANT_COUNT;
	}

	private static void print() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < codeList.size(); i++) {
			bw.write(codeList.get(i) + "\n");
		}
		bw.flush();
		bw.close();
	}
}
