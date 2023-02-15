import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static Point[] selected;
	static int minSum;
	static ArrayList<Point> chickenList, houseList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		//init
		chickenList = new ArrayList<>();
		houseList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int curr = Integer.parseInt(st.nextToken());
				if (curr == 2) {
					chickenList.add(new Point(i, j));
				} else if (curr == 1) {
					houseList.add(new Point(i, j));

				}
			}
		}

		//cal
		selected = new Point[M];
		minSum = Integer.MAX_VALUE;
		combination(0, 0, M);
		System.out.println(minSum);
	}

	private static void combination(int currIdx, int selectedCount, int chickenCount) {
		if (selectedCount == chickenCount) {
			int sumOfDist = calDistance();
			minSum = Math.min(minSum, sumOfDist);
			return;
		}

		for (int i = currIdx; i < chickenList.size(); i++) {
			selected[selectedCount] = chickenList.get(i);
			combination(i + 1, selectedCount + 1, chickenCount);
		}
	}

	private static int calDistance() {

		int sum = 0;
		for (int hIdx = 0; hIdx < houseList.size(); hIdx++) {
			int minDist = Integer.MAX_VALUE;
			Point house = houseList.get(hIdx);
			for (int cIdx = 0; cIdx < selected.length; cIdx++) {
				Point chicken = selected[cIdx];
				int dist = Math.abs(chicken.x - house.x) + Math.abs(chicken.y - house.y);
				minDist = Math.min(dist, minDist);
			}
			sum += minDist;
		}
		return sum;
	}
}
