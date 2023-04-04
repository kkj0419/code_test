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
	Set<Integer> bottoms;
	int[] dests;

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		//init
		bottoms = new HashSet<>();
		dests = summits;
		for (int i = 0; i < gates.length; i++) {
			bottoms.add(gates[i]);
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

		Arrays.sort(summits);
		int top = 0, intensity = Integer.MAX_VALUE;
		for (int i = 0; i < summits.length; i++) {
			int currCost = findRoute(summits[i]);
			if (currCost < intensity) {
				intensity= currCost;
				top = summits[i];
			}
		}

		return new int[]{top, intensity};
	}

	private int findRoute(int top){
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] isVisited = new int[linkedlist.length];
		initRoute(pq, isVisited, top);

		int intensity = 0;
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			int next = curr.dest;
			int currCost = curr.cost;

			if (isVisited[next] != -1) {
				continue;
			}
			intensity = Math.max(currCost, intensity);
			isVisited[next] = intensity;
			if (bottoms.contains(next)) {
				return intensity;
			} else {
				for (int i = 0; i < linkedlist[next].size(); i++) {
					int nextDest = linkedlist[next].get(i).dest;
					if (isVisited[nextDest] == -1) {
						pq.add(linkedlist[next].get(i));
					}
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	private void initRoute(PriorityQueue<Edge> pq, int[] isVisited, int start) {
		Arrays.fill(isVisited, -1);
		for (int i = 0; i < dests.length; i++) {
			isVisited[dests[i]] = 0;
		}
		for (Edge edge : linkedlist[start]) {
			if (isVisited[edge.dest] == -1) {
				pq.add(edge);
			}
		}
	}

}
