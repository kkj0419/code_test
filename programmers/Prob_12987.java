import java.util.Arrays;

class Solution {
	public int solution(int[] A, int[] B) {
		int answer = 0;
		Arrays.sort(A);
		Arrays.sort(B);
		int b = 0;
		for (int a = 0; a < A.length; a++) {
			for (; b < B.length; ) {
				if (A[a] < B[b++]) {
					answer++;
					break;
				}
			}
		}
		return answer;
	}
}
