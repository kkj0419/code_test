
class Solution {

	int N, M;
	long[][] sum;

	public int solution(int[][] board, int[][] skill) {
		N = board.length;
		M = board[0].length;
		sum = new long[N + 1][M + 1];
		init(skill);

		return calBoard(board);
	}

	private void init(int[][] skill) {
		for (int i = 0; i < skill.length; i++) {
			int r1 = skill[i][1], c1 = skill[i][2];
			int r2 = skill[i][3], c2 = skill[i][4];
			int degree = (skill[i][0] == 1) ? -skill[i][5] : skill[i][5];

			sum[r1][c1] += degree;
			sum[r2 + 1][c2 + 1] += degree;

			sum[r1][c2 + 1] -= degree;
			sum[r2 + 1][c1] -= degree;
		}
	}

	private int calBoard(int[][] board) {

		for (int r = 0; r < N; r++) {
			long total = 0;
			for (int c = 0; c < M; c++) {
				total += sum[r][c];
				sum[r][c] = total;
			}
		}

		int cnt = 0;
		for (int c = 0; c < M; c++) {
			long total = 0;
			for (int r = 0; r < N; r++) {
				total += sum[r][c];
				sum[r][c] = total + board[r][c];
				if (sum[r][c] > 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
