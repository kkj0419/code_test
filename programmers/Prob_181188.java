import java.util.Arrays;

class Solution {
	public int solution(int[][] targets) {
		int answer = 0;
		Arrays.sort(targets, (o1, o2) -> {
			if(o1[0] != o2[0])	return o1[0] - o2[0];
			return o1[1] - o2[1];
		});

		int left = targets[0][0], right = targets[0][1];
		for(int i=1; i<targets.length; i++){
			if(right <= targets[i][0]){
				answer++;
				left = targets[i][0]; right = targets[i][1];
			}
			left = Math.max(left, targets[i][0]);
			right = Math.min(right, targets[i][1]);
		}
		return ++answer;
	}
}
