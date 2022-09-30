import java.util.ArrayList;
import java.util.List;

class Solution {
	boolean[][][] isChecked;
	int row, col;
	int[] direction = {0, 1, 2, 3}; //UP, RIGHT, DOWN, LEFT

	public int[] solution(String[] grid) {

		row = grid.length;
		col = grid[0].length();
		char[][] routes = new char[grid.length][];
		for (int i = 0; i < grid.length; i++) {
			routes[i] = grid[i].toCharArray();
		}

		List<Integer> counts = new ArrayList<>();
		isChecked = new boolean[4][row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				for (int i = 0; i < direction.length; i++) {
					if (!isChecked[i][r][c]) {
						Coord start = new Coord(r, c);
						counts.add(goCycle(routes, start, i));
					}
				}
			}
		}
		return counts.stream().mapToInt(Integer::intValue).sorted().toArray();
	}

	private int goCycle(char[][] grid, Coord start, int direction) {
		int count = 0;
		Coord curr = new Coord(start.x, start.y);
		int moveDirection = direction;
		while (!isChecked[moveDirection][curr.x][curr.y]) {
			//move
			isChecked[moveDirection][curr.x][curr.y] = true;
			curr = move(curr, moveDirection);
			count++;
			//turn
			moveDirection = turn(grid[curr.x][curr.y], moveDirection);
		}
		return count;
	}

	private Coord move(Coord coord, int direction) {
		switch (direction) {
			case 0:    //UP
				coord.x = (--coord.x < 0) ? row - 1 : coord.x;
				break;
			case 1:    //RIGHT
				coord.y = (++coord.y >= col) ? 0 : coord.y;
				break;
			case 2:    //DOWN
				coord.x = (++coord.x >= row) ? 0 : coord.x;
				break;
			case 3:    //LEFT
				coord.y = (--coord.y < 0) ? col - 1 : coord.y;
				break;
			default:
		}
		return coord;
	}

	private int turn(char value, int direction) {
		switch (value) {
			case 'S':
				return direction;
			case 'L':
				return (--direction < 0) ? (4 + direction) : direction;
			case 'R':
				return (++direction > 3) ? (direction - 4) : direction;
			default:
				return -1;
		}
	}
}

class Coord {
	int x;
	int y;

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
