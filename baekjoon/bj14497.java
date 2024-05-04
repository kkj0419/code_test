import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static class Dot{
		int x;
		int y;

		Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M;
	static char[][] map;
	static boolean[][] isVisited;
	static final int[] dX = {0, 1, 0, -1};
	static final int[] dY = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken()) - 1, startY = Integer.parseInt(st.nextToken()) - 1;
		int destX = Integer.parseInt(st.nextToken()), destY = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		isVisited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		System.out.println(getJumpCount(new Dot(startX, startY)));
	}

	private static int getJumpCount(Dot start) {
		checkVisited(start);
		LinkedList<Dot> nextQueue = new LinkedList<>();
		int count = 0;
		nextQueue.addLast(start);
		while (!nextQueue.isEmpty()) {
			count++;
			LinkedList<Dot> currQueue = nextQueue;
			nextQueue = new LinkedList<>();
			while (!currQueue.isEmpty()) {
				Dot curr = currQueue.removeFirst();
				for (int i = 0; i < dX.length; i++) {
					Dot next = new Dot(curr.x + dX[i], curr.y + dY[i]);
					if (isAvailable(next)) {
						checkVisited(next);
						switch (map[next.x][next.y]){
							case '0':
								currQueue.addLast(next);
								break;
							case '1':
								nextQueue.addLast(next);
								break;
							case '#':
								return count;
							default:
						}
					}
				}
			}
		}
		return count;
	}

	private static void checkVisited(Dot dot) {
		isVisited[dot.x][dot.y] = true;
	}

	private static boolean isAvailable(Dot dot) {
		int x = dot.x, y = dot.y;
		return x >= 0 && x < N && y >= 0 && y < M && !isVisited[x][y];
	}
}
