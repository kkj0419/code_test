
class Solution {

	public int solution(int sticker[]) {
		int size = sticker.length;
    //OutOfIndex 처리
		if (size == 1) {
			return sticker[size-1];
		}

		int answer = Math.max(
			sticker[0] + findValue(sticker, size, 1, size - 2, 0),
			findValue(sticker, size, 1, size - 1, 1));

		return answer;
	}

	private int findValue(int[] value, int size, int start, int end, int flag) {

		int[][] maxValue = new int[size][2];
		maxValue[end][1] = value[end];
		for (int i = end - 1; i >= start; i--) {
			maxValue[i][1] = maxValue[i + 1][0] + value[i];
			maxValue[i][0] = Math.max(maxValue[i + 1][1], maxValue[i + 1][0]);
		}

		if (flag == 0) {
			return maxValue[start][flag];
		} else {
			return Math.max(maxValue[start][0], maxValue[start][1]);
		}
	}
}
