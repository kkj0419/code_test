import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static final int MAX_SENTENCE = (1 << 26) - 1;
	public static int countOfsentences = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int countOfWords = Integer.valueOf(br.readLine());
		ArrayList<Word> words = new ArrayList<>();
		for (int i = 0; i < countOfWords; i++) {
			String input = br.readLine();
			words.add(new Word(input, convertToBits(input)));
		}
		countViaDfs(words, 0, 0);
		System.out.println(countOfsentences);
	}

	private static void countViaDfs(ArrayList<Word> words, int currSentence, int idx) {
		if (idx == words.size())
			return;
		//포함x
		countViaDfs(words, currSentence, idx + 1);

		//포함o
		int newSentence = currSentence | words.get(idx).converted;
		if (newSentence == MAX_SENTENCE) {
			countOfsentences += 1 << (words.size() - (idx + 1));
		} else {
			countViaDfs(words, newSentence, idx + 1);
		}
	}

	private static int convertToBits(String s) {
		int bitsSentence = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			bitsSentence |= 1 << (c - 'a');
		}
		return bitsSentence;
	}
}

class Word {
	String origin;
	int converted;

	public Word(String origin, int converted) {
		this.origin = origin;
		this.converted = converted;
	}
}
