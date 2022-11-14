import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
	public int solution(int n, int[][] costs) {
		int answer = 0;

		Set<Edge> insertedEdges = new HashSet<>();
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		boolean[] isConnected = new boolean[n];
		ArrayList<Edge>[] edgeList = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			edgeList[i] = new ArrayList<>();
		}
		for (int[] cost : costs) {
			int start = cost[0];
			int end = cost[1];
			edgeList[start].add(new Edge(start, end, cost[2]));
			edgeList[end].add(new Edge(start, end, cost[2]));
		}

		int count = 1;
		int currVer = 0;
		while (count != n) {
			isConnected[currVer] = true;
			//edgelist insert
			for (int i = 0; i < edgeList[currVer].size(); i++) {
				Edge edge = edgeList[currVer].get(i);
				if (!insertedEdges.contains(edge)) {
					insertedEdges.add(edge);
					edges.add(edge);
				}
			}

			Edge connecting = edges.poll();
			while (isConnected[connecting.end] && isConnected[connecting.start]) {
				connecting = edges.poll();
			}
			int next = (isConnected[connecting.start]) ? connecting.end : connecting.start;
			isConnected[next] = true;
			count++;
			answer += connecting.cost;
			currVer = next;
		}

		return answer;
	}

}

class Edge implements Comparable<Edge> {
	int start;
	int end;
	int cost;

	public Edge(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Edge))
			return false;
		Edge edge = (Edge)o;
		return start == edge.start && end == edge.end && cost == edge.cost;
	}

	@Override
	public int hashCode() {
		return Objects.hash(start, end, cost);
	}

	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}
