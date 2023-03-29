import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
	static class Edge implements Comparable<Edge> {
		int dest;
		int cost;

		public Edge(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			//cost ASC, dest ASC
			if (this.cost == o.cost) {
				return this.dest - o.dest;
			}
			return this.cost - o.cost;
		}
	}

	ArrayList<Edge>[] linkedlist;
	Set<Integer> tops;
	int[] starts;

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		int[] answer = new int[2];
		//init
		starts = gates;
		tops = new HashSet<>();
		for (int i = 0; i < summits.length; i++) {
			tops.add(summits[i]);
		}
		linkedlist = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			linkedlist[i] = new ArrayList<>();
		}
		for (int i = 0; i < paths.length; i++) {
			int start = paths[i][0], end = paths[i][1], cost = paths[i][2];
			linkedlist[start].add(new Edge(end, cost));
			linkedlist[end].add(new Edge(start, cost));
		}

		int[] route = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
		for (int i = 0; i < gates.length; i++) {
			route = findRoute(gates[i], route[0], route[1]);
			if (route[0] != Integer.MAX_VALUE) {
				answer = route;
			}
		}
		return answer;
	}

	private int[] findRoute(int start, int dest, int minIntensity) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] isVisited = new int[linkedlist.length];
		initRoute(pq, isVisited, start);

		int intensity = minIntensity, currIntensity = 0;
		int top = dest;
		boolean flag = false;
		while (!pq.isEmpty()) {
			Edge currEdge = pq.poll();
			int next = currEdge.dest;
			int currCost = currEdge.cost;
			if (currCost > intensity) {    //out of 최대 intensity
				break;
			}

			currIntensity = Math.max(currIntensity, currCost);
			if (isVisited[next] != -1 && isVisited[next] <= currIntensity) {    //이미 방문한 노드인지, 또 방문할 의미가 있는지
				continue;
			}
			isVisited[next] = currIntensity;
			if (tops.contains(next)) {
				if (intensity > currIntensity) {
					top = next;
					intensity = currIntensity;
				} else if (intensity == currIntensity) {
					top = Math.min(top, next);
				}
				flag = true;
			} else {
				for (Edge edge : linkedlist[next]) {
					if (isVisited[edge.dest] == -1 || isVisited[edge.dest] > currIntensity) {
						pq.add(edge);
					}
				}
			}
		}

		if (flag) {
			return new int[] {top, intensity};
		}
		return new int[] {Integer.MAX_VALUE, minIntensity};
	}

	private void initRoute(PriorityQueue<Edge> pq, int[] isVisited, int start) {
		Arrays.fill(isVisited, -1);
		for (int i = 0; i < starts.length; i++) {
			isVisited[starts[i]] = 0;
		}
		for (Edge edge : linkedlist[start]) {
			if (isVisited[edge.dest] == -1) {
				pq.add(edge);
			}
		}
	}

}
