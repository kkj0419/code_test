
class Solution {
	static int[] binaryLength = {1, 3, 7, 15, 31, 63};

	public int[] solution(long[] numbers) {
		int[] answer = new int[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			answer[i] = (isBinaryTree(numbers[i])) ? 1 : 0;
		}
		return answer;
	}

	private boolean isBinaryTree(long number) {
		String binary = paddingBinary(Long.toBinaryString(number));

		return checkEnableBinaryTree(binary.toCharArray(), 0, binary.length() - 1);
	}

	private String paddingBinary(String s) {
		int paddingLength = 0;
		for (int i = 0; i < binaryLength.length; i++) {
			if (s.length() == binaryLength[i]) {
				return s;
			} else if (s.length() < binaryLength[i]) {
				paddingLength = binaryLength[i] - s.length();
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < paddingLength; i++) {
			sb.append('0');
		}
		return sb.append(s).toString();
	}

	private boolean checkEnableBinaryTree(char[] binaryString, int left, int right) {
		if (left == right) {
			return true;
		}

		int mid = (left + right) / 2;
		if (binaryString[mid] != '0') {
			return checkEnableBinaryTree(binaryString, left, mid - 1) &&
				checkEnableBinaryTree(binaryString, mid + 1, right);

		} else {    //dummy tree
			return checkDummyTree(binaryString, left, mid - 1) &&
				checkDummyTree(binaryString, mid + 1, right);
		}
	}

	private boolean checkDummyTree(char[] binaryString, int left, int right) {
		if (left == right) {
			return binaryString[left] == '0';
		}

		int mid = (left + right) / 2;
		if (binaryString[mid] == '0') {
			return checkDummyTree(binaryString, left, mid - 1) &&
				checkDummyTree(binaryString, mid + 1, right);
		}
		return false;
	}
}
