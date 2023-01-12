import java.util.Arrays;

class Solution {
	public long solution(int n, int[] times) {
		long answer = 0;

		Arrays.sort(times);
		long start = times[0];
		long end = (long)times[0] * n;
		while (start <= end) {
			long mid = (start + end) / 2;
			long person = 0;
			for (int i = 0; i < times.length; i++) {
				person += mid / times[i];
				if (person >= n) {
					answer = mid;
					end = mid - 1;
					break;
				}
			}
			if (person < n) {
				start = mid + 1;
			}
		}
		return answer;
	}
}
