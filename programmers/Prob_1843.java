import java.util.Arrays;

class Solution {
	int[][] maxSum;
	int[][] minSum;
	static final int MIN_ANSWER = -(100000 + 1000);
	static final int MAX_ANSWER = 100000 + 1000;
	public int solution(String arr[]) {
		//init
		int countOfNum = arr.length / 2 + 1;
		maxSum = new int[countOfNum + 1][countOfNum + 1];
		minSum = new int[countOfNum + 1][countOfNum + 1];

		for (int i = 1; i <= countOfNum; i++) {
			Arrays.fill(maxSum[i], MIN_ANSWER);
			Arrays.fill(minSum[i], MAX_ANSWER);
			maxSum[i][i] = Integer.parseInt(arr[(i - 1) * 2]);
			minSum[i][i] = Integer.parseInt(arr[(i - 1) * 2]);
		}

		//dp
		for (int len = 1; len <= countOfNum; len++) {
			for (int i = 1; i + len <= countOfNum; i++) {    //row
				calMaxMinValue(arr, i, i + len);
			}
		}
		return maxSum[1][countOfNum];
	}

	private void calMaxMinValue(String arr[], int row, int col) {
		int maxValue = maxSum[row][col];
		int minValue = minSum[row][col];
		for (int i = 0; i + row < col; i++) {
			int mid = i + row;
			int currMaxValue, currMinValue;
			if (arr[2 * mid - 1].equals("+")) {    //add
				currMaxValue = maxSum[row][i + row] + maxSum[i + row + 1][col];
				currMinValue = minSum[row][i + row] + minSum[i + row + 1][col];

			} else {    //sub
				currMaxValue = maxSum[row][i + row] - minSum[i + row + 1][col];
				currMinValue = minSum[row][i + row] - maxSum[i + row + 1][col];
			}
			maxValue = Math.max(currMaxValue, maxValue);
			minValue = Math.min(currMinValue, minValue);
		}
		maxSum[row][col] = maxValue;
		minSum[row][col] = minValue;
	}

}
