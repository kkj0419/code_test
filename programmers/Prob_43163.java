import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Solution {
	public int solution(String begin, String target, String[] words) {

		int wordSize = words.length + 1;
		//linkedlist
		Map<String, Integer> wordIdxMap = new HashMap<>();
		ArrayList<String>[] linkedList = new ArrayList[wordSize];
		ArrayList<String> stringList = new ArrayList<>(Arrays.asList(words));
		stringList.add(begin);
		for (int i = 0; i < wordSize; i++) {
			linkedList[i] = new ArrayList<>();
			wordIdxMap.put(stringList.get(i), i);
			for (int j = 0; j < wordSize; j++) {
				if (i != j && isAdjacent(stringList.get(i), stringList.get(j))) {
					linkedList[i].add(stringList.get(j));
				}
			}
		}

		//bfs
		int answer = 0;
		Queue<Trace> queue = new LinkedList<>();
		boolean[] isVisited = new boolean[wordSize];
		if (!wordIdxMap.containsKey(target)) {
			return answer;
		}

		queue.add(new Trace(begin, 0));
		isVisited[wordIdxMap.get(begin)] = true;
		while (!queue.isEmpty()) {
			Trace trace = queue.poll();
			if (trace.word.equals(target)) {
				answer = trace.count;
				break;
			}
			int idx = wordIdxMap.get(trace.word);
			for (int i = 0; i < linkedList[idx].size(); i++) {
				String nextWord = linkedList[idx].get(i);
				if (!isVisited[wordIdxMap.get(nextWord)]) {
					isVisited[wordIdxMap.get(nextWord)] = true;
					queue.add(new Trace(nextWord, trace.count + 1));
				}

			}
		}
		return answer;
	}

	private boolean isAdjacent(String s1, String s2) {
		int length = s1.length();
		int cnt = 0;
		for (int i = 0; i < length; i++) {
			if (s1.charAt(i) != s2.charAt(i))
				cnt++;
		}
		return cnt == 1;
	}
}

class Trace {
	String word;
	int count;

	public Trace(String word, int count) {
		this.word = word;
		this.count = count;
	}
}
