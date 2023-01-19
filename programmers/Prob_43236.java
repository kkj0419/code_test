import java.util.Arrays;

class Solution {
	public int solution(int distance, int[] rocks, int n) {
		//init
		Arrays.sort(rocks);
		int[] dist = new int[rocks.length];    //i번째 rock 제거 시 추가되는 distance
		dist[rocks.length - 1] = distance - rocks[rocks.length - 1];
		for (int i = 0; i < rocks.length - 1; i++) {
			dist[i] = rocks[i + 1] - rocks[i];
		}

		int answer = 0;
		int start = 1, end = distance;
		while (end >= start) {
			int mid = (start + end) / 2;

			int rockIdx = 0;
			int ridCount = 0;
			int currDist = rocks[0];
			for (; rockIdx < rocks.length; rockIdx++) {

				if (currDist >= mid) {			//pass
					currDist = dist[rockIdx];
				} else {                		//제거
					if (ridCount == n) {
						end = mid - 1;
						break;
					}
					ridCount++;
					currDist += dist[rockIdx];
				}

				if (rockIdx == rocks.length - 1) {
					answer = mid;
					start = mid + 1;
				}
			}
		}

		return answer;
	}
}
