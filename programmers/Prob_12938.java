class Solution {
	public int[] solution(int n, int s) {
		int[] answer;
		int defaultValue = s / n;

		if (defaultValue < 1) {
			return new int[] {-1};
		} else {
			int remain = s % n;
			answer = new int[n];
			Arrays.fill(answer, 0, n - remain, defaultValue);
			Arrays.fill(answer, n - remain, n, defaultValue + 1);
		}
		return answer;
	}
}
