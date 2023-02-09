import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
	int[] dX = {0, 1, 0, -1};
	int[] dY = {1, 0, -1, 0};

	int[][][] isVisited;
	int gridSize;

	public int solution(int[][] board) {
		//init
		gridSize = board.length;
		isVisited = new int[4][gridSize][gridSize];    //	방향마다의 cost
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < gridSize; j++) {
				Arrays.fill(isVisited[i][j], Integer.MAX_VALUE);
			}
		}
		isVisited[0][0][0] = 0;
		isVisited[1][0][0] = 0;
		isVisited[2][0][0] = 0;
		isVisited[3][0][0] = 0;

		return findMinRoutes(board);
	}

	private int findMinRoutes(int[][] board) {
		PriorityQueue<Grid> queue = new PriorityQueue<>();
		queue.add(new Grid(0, 0, -1, 0));
		while (!queue.isEmpty()) {
			Grid currGrd = queue.poll();
			if (currGrd.x == gridSize - 1 && currGrd.y == gridSize - 1) {
				return currGrd.minCost;
			}

			for (int i = 0; i < dX.length; i++) {
				int nextX = currGrd.x + dX[i], nextY = currGrd.y + dY[i];
				if (isReachable(board, nextX, nextY)) {
					int nextCost = currGrd.minCost + calCost(currGrd, i);
					Grid newGrid;
					if (isVisited[i][nextX][nextY] < nextCost) {
						continue;
					} else {
						isVisited[i][nextX][nextY] = nextCost;
						newGrid = new Grid(nextX, nextY, i, nextCost);
					}
					queue.add(newGrid);
				}
			}
		}
		return 0;
	}

	private boolean isReachable(int[][] map, int x, int y) {
		return x >= 0 && x < gridSize && y >= 0 && y < gridSize && map[x][y] == 0;
	}

	private int calCost(Grid grid, int nextDirection) {
		if (grid.routeDirection == -1 || ((grid.routeDirection + nextDirection) % 2 == 0)) {
			return 100;
		}
		return 500 + 100;
	}

}

class Grid implements Comparable<Grid> {
	int x;
	int y;

	int routeDirection;
	int minCost;

	public Grid(int x, int y, int routeDirection, int minCost) {
		this.x = x;
		this.y = y;
		this.routeDirection = routeDirection;
		this.minCost = minCost;
	}

	@Override
	public int compareTo(Grid o) {
		return this.minCost - o.minCost;
	}
}
