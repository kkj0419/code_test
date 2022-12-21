import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static long[][] map;
	static int[][] mineral;
	static PriorityQueue<Tree> trees = new PriorityQueue<>();
	static PriorityQueue<Tree> treesBuffer;
	static int[] moveX = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] moveY = {-1, -1, 0, 1, 1, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//init
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = input[0], M = input[1], K = input[2];
		//map
		map = new long[N][N];
		mineral = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 5);
			mineral[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		//tree
		for (int i = 0; i < M; i++) {
			input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			trees.add(new Tree(input[0], input[1], input[2]));
		}

		for (int i = 0; i < K; i++) {
			if (!trees.isEmpty()) {
				treesBuffer = new PriorityQueue<>();
				survive(N);
				trees.clear();
				trees.addAll(treesBuffer);
			} else {
				break;
			}
		}
		System.out.println(trees.size());
	}

	private static void survive(int N) {
		Queue<Tree> diedTrees = new LinkedList<>();
		while (!trees.isEmpty()) {
			Tree tree = trees.poll();
			int x = tree.r - 1, y = tree.c - 1;

			if (map[x][y] >= tree.age) {    //survive
				map[x][y] -= tree.age;
				tree.age++;
				treesBuffer.add(tree);
				if (tree.age % 5 == 0) {    //breed
					breed(x, y);
				}
			} else if (tree.age != 1) {     //dead
				diedTrees.add(tree);
			}
		}

		//add mineral
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += mineral[i][j];
			}
		}

		while (!diedTrees.isEmpty()) {
			Tree currTree = diedTrees.poll();
			int add = currTree.age / 2;
			map[currTree.r - 1][currTree.c - 1] += add;
		}
	}

	private static void breed(int x, int y) {
		int currX, currY;
		for (int i = 0; i < moveX.length; i++) {
			currX = x + moveX[i];
			currY = y + moveY[i];
			if (!isOutOfRange(currX, currY)) {
				treesBuffer.add(new Tree(currX + 1, currY + 1, 1));
			}
		}
	}

	private static boolean isOutOfRange(int r, int c) {
		int bound = map[0].length;
		return r >= bound || r < 0 || c >= bound || c < 0;
	}
}

class Tree implements Comparable<Tree> {
	int r;
	int c;
	int age;

	public Tree(int r, int c, int age) {
		this.r = r;
		this.c = c;
		this.age = age;
	}

	@Override
	public int compareTo(Tree o) {
		//age asc
		return this.age - o.age;
	}
}
