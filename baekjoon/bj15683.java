import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	static int height, width;
	static int[][] map;
	static int blindSpots = 100;

	static final ArrayList<int[]>[] direction = new ArrayList[6];
	static final int[] dX = {0, 1, 0, -1};
	static final int[] dY = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//init
		init();
		String line = br.readLine();
		height = Integer.parseInt(line.split(" ")[0]);
		width = Integer.parseInt(line.split(" ")[1]);
		map = new int[height][];
		List<Dot> dotList = new ArrayList<>();

		boolean[][] isMarked = new boolean[height][width];
		for (int i = 0; i < height; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < width; j++) {
				if (map[i][j] == 6) {
					isMarked[i][j] = true;
				} else if (map[i][j] != 0) {
					isMarked[i][j] = true;
					dotList.add(new Dot(i, j, map[i][j]));
				}
			}
		}

		if (dotList.isEmpty()) {
			searchBlindSpots(isMarked);
		} else {
			dfs(isMarked, dotList, 0);
		}
		System.out.println(blindSpots);
	}

	private static void init() {
		for (int i = 1; i < 6; i++) {
			direction[i] = new ArrayList<>();
			if (i == 1) {
				direction[i].add(new int[] {0});
				direction[i].add(new int[] {1});
				direction[i].add(new int[] {2});
				direction[i].add(new int[] {3});
			}
			if (i == 2) {
				direction[i].add(new int[] {0, 2});
				direction[i].add(new int[] {1, 3});
			}
			if (i == 3) {
				direction[i].add(new int[] {0, 1});
				direction[i].add(new int[] {1, 2});
				direction[i].add(new int[] {2, 3});
				direction[i].add(new int[] {3, 0});
			}
			if (i == 4) {
				direction[i].add(new int[] {0, 1, 2});
				direction[i].add(new int[] {1, 2, 3});
				direction[i].add(new int[] {2, 3, 0});
				direction[i].add(new int[] {3, 0, 1});
			}
			if (i == 5) {
				direction[i].add(new int[] {0, 1, 2, 3});
			}
		}
	}

	private static void dfs(boolean[][] isMarked, List<Dot> equips, int idx) {

		boolean[][] currMarked = new boolean[height][width];
		copy(currMarked, isMarked);

		Dot equip = equips.get(idx);

		currMarked[equip.x][equip.y] = true;
		ArrayList<int[]> directs = direction[equip.value];        //가능한 방향 리스트
		for (int i = 0; i < directs.size(); i++) {
			for (int diIdx = 0; diIdx < directs.get(i).length; diIdx++) {
				searchMarkable(currMarked, equip, directs.get(i)[diIdx]);
			}
			if (idx == equips.size() - 1) {		//deeper일때 카운트 갱신
				searchBlindSpots(currMarked);
			} else {
				dfs(currMarked, equips, idx + 1);
			}
			copy(currMarked, isMarked);
		}
	}

	private static void copy(boolean[][] dest, boolean[][] start) {
		for (int i = 0; i < dest.length; i++) {
			dest[i] = start[i].clone();
		}
	}

	private static int searchMarkable(boolean[][] currMarked, Dot dot, int direct) {

		int place = 0;
		int currX = dot.x + dX[direct], currY = dot.y + dY[direct];

		while (isReachable(currX, currY)) {
			if (!currMarked[currX][currY]) {
				currMarked[currX][currY] = true;
				place++;
			}
			currX += dX[direct];
			currY += dY[direct];

		}
		return place;
	}

	private static boolean isReachable(int x, int y) {
		return 0 <= x && x < height && y >= 0 && y < width && map[x][y] != 6;
	}

	private static void searchBlindSpots(boolean[][] currMarked) {
		int count = 0;
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				if (!currMarked[x][y]) {
					count++;
				}
			}
		}
		blindSpots = Math.min(count, blindSpots);
	}
}

class Dot {
	int x;
	int y;
	int value;

	public Dot(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
}
