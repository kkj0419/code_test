import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
	public int solution(int[] stones, int k) {
		int answer = 0;
		int length = stones.length;
		PriorityQueue<StoneInfo> queue = new PriorityQueue<>();
		for (int i = 0; i < length; i++) {
			queue.add(new StoneInfo(i, stones[i]));
		}

		int[][] range = new int[2][length];    //{depart, dest}
		for (int i = 0; i < 2; i++) {
			Arrays.fill(range[i], -2);
		}
		while (!queue.isEmpty()) {
			StoneInfo info = queue.poll();
			int currIdx = info.idx;
			int currDepart = range[0][currIdx], currDest = range[1][currIdx];
			int newDepart, newDest;
			if (currDepart == -2) {
				newDepart = currIdx - 1;
			} else {
				newDepart = currDepart;
			}
			if (currDest == -2) {
				newDest = currIdx + 1;
			} else {
				newDest = currDest;
			}

			if (!checkDistance(newDepart, newDest, k)) {
				answer = info.value;
				break;
			}
			//renew [departure]
			if (newDepart != -1) {
				range[1][newDepart] = newDest;
			}
			//renew [dest]
			if (newDest != length) {
				range[0][newDest] = newDepart;
			}
		}

		return answer;
	}

	private boolean checkDistance(int depart, int dest, int k) {
		return (dest - depart) <= k;
	}
}

class StoneInfo implements Comparable<StoneInfo> {
	int idx;
	int value;

	public StoneInfo(int idx, int value) {
		this.idx = idx;
		this.value = value;
	}

	@Override
	public int compareTo(StoneInfo o) {
		//value ASC, idx ASC
		if (o.value == this.value) {
			return this.idx - o.idx;
		}
		return this.value - o.value;
	}
}
