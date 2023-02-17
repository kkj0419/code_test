import java.util.Arrays;

class Solution {

	public int solution(String s) {
		char[] chars = s.toCharArray();
		int minLength = s.length();
		for (int i = 1; i <= s.length() / 2; i++) {
			minLength = Math.min(minLength, findZippedLength(chars, i));
		}

		return minLength;
	}

	private int findZippedLength(char[] arr, int sliceSize) {

		int sum = 0;
		char[] currSlice = Arrays.copyOfRange(arr, 0, sliceSize);
		int currCount = 1;
		for (int i = 1; i < arr.length / sliceSize; i++) {
			int sliceIdx = 0;
			while (sliceIdx != sliceSize) {
				int currIdx = i * sliceSize + sliceIdx;
				if (currSlice[sliceIdx] != arr[currIdx]) {
					if (currCount != 1) {
						sum += Integer.toString(currCount).length();
					}
					sum += sliceSize;
					currSlice = Arrays.copyOfRange(arr, i * sliceSize, i * sliceSize + sliceSize);
					currCount = 1;
					break;
				}
				sliceIdx++;
			}
			if (sliceIdx == sliceSize) {
				currCount++;
			}
		}

		if (currCount != 1) {
			sum += Integer.toString(currCount).length();
		}
		sum += sliceSize;
		if (arr.length % sliceSize != 0) {    //remain
			sum += arr.length % sliceSize;
		}

		return sum;
	}
}
