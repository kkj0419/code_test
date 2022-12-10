class Solution {
	public int solution(int[] money) {
		int cnt = money.length;
		int[] totalFirst = new int[cnt];		//첫 집 포함
		int[] totalNotFirst = new int[cnt];		//첫 집 미포함
		totalFirst[0] = money[0];
		totalFirst[1] = money[0];
		totalNotFirst[1] = money[1];

		for (int i = 2; i < cnt; i++) {
			totalNotFirst[i] = Math.max(totalNotFirst[i - 1], totalNotFirst[i - 2] + money[i]);
			if (i == cnt - 1) {
				totalFirst[i] = totalFirst[i - 1];
				break;
			}
			totalFirst[i] = Math.max(totalFirst[i - 1], totalFirst[i - 2] + money[i]);
		}

		int answer = Math.max(totalFirst[cnt - 1], totalNotFirst[cnt - 1]);
		return answer;
	}
}
