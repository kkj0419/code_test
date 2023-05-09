import java.util.ArrayList;
import java.util.Collections;

class Solution {
	static class Score implements Comparable<Score> {
		int idx;
		int work;
		int evaluation;

		public Score(int idx, int work, int evaluation) {
			this.idx = idx;
			this.work = work;
			this.evaluation = evaluation;
		}

		@Override
		public int compareTo(Score o) {
			int value = o.work + o.evaluation;
			if (value == this.work + this.evaluation) {
				return this.idx - o.idx;
			}
			return (value) - (this.work + this.evaluation);
		}
	}

	public int solution(int[][] scores) {

		ArrayList<Score> list = new ArrayList<>();
		for (int i = 0; i < scores.length; i++) {
			list.add(new Score(i + 1, scores[i][0], scores[i][1]));
		}

		//work desc, eval asc
		Collections.sort(list, (o1, o2) -> {
			if (o1.work == o2.work) {
				return o1.evaluation - o2.evaluation;
			}
			return o2.work - o1.work;
		});

		//sum desc
		list = makeList(list);

		Collections.sort(list);

		int preValue = 0;
		int answer = 1;
		for (int i = 0; i < list.size(); i++) {
			int currValue = list.get(i).evaluation + list.get(i).work;
			if (preValue != currValue) {
				answer = i + 1;
				preValue = currValue;
			}

			if (list.get(i).idx == 1) {
				return answer;
			}
		}
		return -1;
	}

	private ArrayList<Score> makeList(ArrayList<Score> scores) {
		ArrayList<Score> list = new ArrayList<>();

		int maxEval = scores.get(0).evaluation;
		for (int i = 0; i < scores.size(); i++) {
			Score curr = scores.get(i);
			if (curr.evaluation < maxEval) {
				if (curr.idx == 1) {
					list.clear();
					return list;
				}
				continue;
			}
			maxEval = Math.max(maxEval, curr.evaluation);
			list.add(curr);
		}
		return list;
	}
}
