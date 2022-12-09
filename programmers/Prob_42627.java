import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
	public int solution(int[][] jobs) {

		//요청시점 asc, 소요시간 asc sort
		Arrays.sort(jobs, (o1, o2) -> {
			if (o1[0] == o2[0]) {
				return o1[1] - o2[1];
			}
			return o1[0] - o2[0];
		});

		int currIdx = 0;
		int currTime = jobs[0][0], totalDelayTime = 0;
		PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));    //소요시간 asc
		queue.add(jobs[currIdx++]);
		while (!queue.isEmpty()) {
			int[] currJob = queue.poll();
			totalDelayTime += (currTime - currJob[0]);
			currTime += currJob[1];

			if (currIdx < jobs.length) {
				if (jobs[currIdx][0] <= currTime) {    //대기중인 jobs add
					while (currIdx < jobs.length && jobs[currIdx][0] <= currTime) {
						queue.add(jobs[currIdx++]);
					}
				} else if (queue.isEmpty()) {    //요청이 먼저 들어온 job부터 처리
					currTime = jobs[currIdx][0];
					queue.add(jobs[currIdx++]);
				}
			}
		}
		int totalTime = Arrays.stream(jobs).mapToInt(arr -> arr[1]).sum();
		return (totalTime + totalDelayTime) / jobs.length;
	}
}
