import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

class Solution {
	static final int MAX_AIRPORT = 10000;
	HashMap<String, Integer> airports = new HashMap<>();
	ArrayList<Edge>[] linkedlist = new ArrayList[MAX_AIRPORT + 1];
	LinkedList<String> bufferList = new LinkedList<>();
	LinkedList<String> answerList = new LinkedList<>();

	public String[] solution(String[][] tickets) {
		//init
		int count = 0;
		for (int i = 0; i < MAX_AIRPORT + 1; i++) {
			linkedlist[i] = new ArrayList<>();
		}
		for (int i = 0; i < tickets.length; i++) {
			String departString = tickets[i][0], destString = tickets[i][1];
			int currDepart = (airports.containsKey(departString)) ? airports.get(departString) : count++;
			int currDest = (airports.containsKey(destString)) ? airports.get(destString) : count++;

			airports.put(departString, currDepart);
			airports.put(destString, currDest);

			linkedlist[currDepart].add(new Edge(destString));
		}
		for (int i = 0; i < count; i++) {
			linkedlist[i].sort(Comparator.comparing(e -> e.dest));
		}

		int startIdx = airports.get("ICN");
		bufferList.add("ICN");
		dfs(startIdx, 0, tickets.length);

		return answerList.toArray(new String[answerList.size()]);
	}

	private void dfs(int currIdx, int currEdgeCount, int totalEdgeCount) {
		if (currEdgeCount == totalEdgeCount) {
			answerList.addAll(bufferList);
			return;
		}
		if (!answerList.isEmpty()) {
			return;
		}

		ArrayList<Edge> nextAirports = linkedlist[currIdx];
		for (int i = 0; i < nextAirports.size(); i++) {
			Edge currEdge = nextAirports.get(i);
			if (!currEdge.isVisited) {
				int nextIdx = airports.get(currEdge.dest);
				currEdge.isVisited = true;
				bufferList.addLast(currEdge.dest);
				dfs(nextIdx, currEdgeCount + 1, totalEdgeCount);
				//out
				bufferList.removeLast();
				currEdge.isVisited = false;
			}
		}
	}
}

class Edge {
	String dest;
	boolean isVisited = false;

	public Edge(String dest) {
		this.dest = dest;
	}
}
