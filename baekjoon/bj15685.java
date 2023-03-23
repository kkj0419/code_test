import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static class Line {
		int x;
		int y;
		int direction;

		public Line(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}

	static boolean[][] isMarked = new boolean[100 + 1][100 + 1];
	static final int[] dX = {1, 0, -1, 0};
	static final int[] dY = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken()), d = Integer.parseInt(
				st.nextToken()), g = Integer.parseInt(st.nextToken());
			ArrayList<Line> list = new ArrayList<>();
			isMarked[x][y] = true;
			list.add(new Line(x, y, d));
			makeCurve(list, g);
		}

		int count = 0;
		for (int a = 0; a < 100; a++) {
			for (int b = 0; b < 100; b++) {
				if (isMarked[a][b] && isMarked[a + 1][b] && isMarked[a][b + 1] && isMarked[a + 1][b + 1]) {
					count++;
				}
			}
		}
		System.out.println(count);
	}

	private static void makeCurve(ArrayList<Line> curveList, int generation) {

		Line lastCurve = curveList.get(curveList.size() - 1);
		int currX = lastCurve.x + dX[lastCurve.direction], currY = lastCurve.y + dY[lastCurve.direction];
		isMarked[currX][currY] = true;
		for (int g = 0; g < generation; g++) {
			ArrayList<Line> addList = new ArrayList<>();
			for (int i = curveList.size() - 1; i >= 0; i--) {
				Line currLine = curveList.get(i);
				int preD = currLine.direction;
				int nextD = nextDirection(preD);
				addList.add(new Line(currX, currY, nextD));
				currX = currX + dX[nextD];
				currY = currY + dY[nextD];
				isMarked[currX][currY] = true;
			}
			curveList.addAll(addList);
		}
	}

	private static int nextDirection(int direction) {
		if (direction + 1 == 4) {
			return 0;
		}
		return direction + 1;
	}
}
