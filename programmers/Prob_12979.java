class Solution {
	public int solution(int n, int[] stations, int w) {
		int answer = 0;

		int range = w * 2 + 1;
		int currStation = 0;
		for (int i = 0; i < stations.length; i++) {
			int station = stations[i];
			int left = station - w;
			int right = station + w;
			if (left - 1 > currStation) {
				answer += Math.ceil((left - 1 - currStation) / (float)range);
			}
			currStation = right;
		}

		//remains
		if (currStation <= n) {
			answer += Math.ceil((n - currStation) / (float)range);
		}
		return answer;
	}
}
