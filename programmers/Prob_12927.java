import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
	public long solution(int n, int[] works) {
		long answer = 0;

		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
		queue.addAll(Arrays.stream(works).boxed().collect(Collectors.toList()));
		for (int i = 0; i < n; i++) {
			int value = queue.remove();
			if (value == 0) {
				break;
			}
			queue.add(value - 1);
		}

		int[] ans = queue.stream().map(String::valueOf).mapToInt(Integer::parseInt).toArray();
		for (int i = 0; i < ans.length; i++) {
			answer += ((long)ans[i] * ans[i]);
		}
		return answer;
	}
}
