import java.util.*;

class Solution {
	public int solution(int n, int[][] edge) {

		ArrayList<Integer>[] linkedlist = new ArrayList[edge.length + 1];
		for (int i = 1; i <= edge.length; i++) {
			linkedlist[i] = new ArrayList<>();
		}
		for (int i = 0; i < edge.length; i++) {
			int a = edge[i][0], b = edge[i][1];
			linkedlist[a].add(b);
			linkedlist[b].add(a);
		}

		int answer = 0;
		int maxCount = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		int[] vertex = new int[edge.length + 1];
		Arrays.fill(vertex, -1);
		vertex[1] = 0;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			int count = vertex[curr];
			for (int i = 0; i < linkedlist[curr].size(); i++) {
				int next = linkedlist[curr].get(i);
				if (vertex[next] == -1) {
					queue.add(next);
					vertex[next] = count + 1;
					if (maxCount < vertex[next]) {
						maxCount = vertex[next];
						answer = 1;
					} else {
						answer++;
					}
				}
			}
		}
		return answer;
	}
}
