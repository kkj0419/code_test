import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
	static class Node {
		int x;
		int y;
		String route;

		public Node(int x, int y, String route) {
			this.x = x;
			this.y = y;
			this.route = route;
		}
	}

	Node start, dest, maxNode;
	boolean[][][] isVisited;
	final int[] dX = {1, 0, 0, -1};
	final int[] dY = {0, -1, 1, 0};
	final char[] suffix = {'d', 'l', 'r', 'u'};

	public String solution(int n, int m, int x, int y, int r, int c, int k) {
		start = new Node(x, y, "");
		dest = new Node(r, c, null);
		maxNode = new Node(n, m, null);
		isVisited = new boolean[n + 1][m + 1][k + 1];
		isVisited[x][y][0] = true;

		return bfs(k);
	}

	private String bfs(int len) {
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(o -> o.route));
		pq.add(start);
		while (!pq.isEmpty()) {
			sb.setLength(0);    //초기화
			Node currNode = pq.poll();
			String currRoute = currNode.route;
			int currX = currNode.x, currY = currNode.y;
			sb.append(currRoute);

			if (currRoute.length() >= len) {
				if (currRoute.length() == len && isDestination(currNode)) {
					return currRoute;
				}
				continue;
			}
			for (int i = 0; i < dX.length; i++) {
				int nextX = currX + dX[i], nextY = currY + dY[i];
				if (isAvailable(nextX, nextY, currRoute.length() + 1)) {
					isVisited[nextX][nextY][currRoute.length() + 1] = true;
					sb.append(suffix[i]);
					pq.add(new Node(nextX, nextY, sb.toString()));
					sb.setLength(sb.length() - 1);
				}
			}
		}
		return "impossible";
	}

	private boolean isAvailable(int x, int y, int len) {
		return x >= 1 && x <= maxNode.x && y >= 1 && y <= maxNode.y && !isVisited[x][y][len];
	}

	private boolean isDestination(Node curr) {
		return curr.x == dest.x && curr.y == dest.y;
	}
}
