class Solution {
	int n;
	int[][] clocks;
	int answer = Integer.MAX_VALUE;
	final int[][] dMove = { {-1,0}, {0,-1}, {0,0}, {0,1}, {1,0}};
	public int solution(int[][] clockHands) {
		n = clockHands.length;
		clocks = clockHands;

		int[] firstArr = new int[n];
		permutation(1, firstArr);
		return answer;
	}

	private void permutation(int depth, int[] firstArr){
		if(depth-1 == n){
			getCount(firstArr);
			return;
		}

		for(int i=0; i<4; i++){
			firstArr[depth-1] = i;
			permutation(depth+1, firstArr);
		}
	}

	private void getCount(int[] firstArr){
		int[][] current = new int[n][n];
		for(int i=0; i<n; i++){
			current[i] = clocks[i].clone();
		}

		int count = 0;
		for(int i=0; i<n; i++){
			count += firstArr[i];
			rotate(current, 0, i, firstArr[i]);
		}

		for(int i=1; i<n; i++){
			for(int j=0; j<n; j++){
				int cnt = (4-current[i-1][j])%4;
				count += cnt;
				if(count >= answer)	return;
				rotate(current, i, j, cnt);
			}
		}

		for(int i=0; i<n; i++){
			if(current[n-1][i] != 0)	return;
		}
		answer = Math.min(count, answer);
	}

	private void rotate(int[][] arr, int x, int y, int count){
		if(count == 0)	return;

		for(int i=0;i<dMove.length; i++){
			int nextX = x + dMove[i][0], nextY = y + dMove[i][1];
			if(nextX < 0 || nextX >=n || nextY <0 || nextY >=n )    continue;
			arr[nextX][nextY] = (arr[nextX][nextY] + count) % 4;
		}
	}
}
