import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static ArrayList<Edge>[] linkedlist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int N = Integer.parseInt(input.split(" ")[0]);	//vertex
		int M = Integer.parseInt(input.split(" ")[1]);

		//init
		linkedlist = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			linkedlist[i] = new ArrayList<>();
		}

		//edge
		for (int i = 0; i < N - 1; i++) {
			int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			linkedlist[edge[0]].add(new Edge(edge[1], edge[2]));
			linkedlist[edge[1]].add(new Edge(edge[0], edge[2]));
		}

		//dfs
		for (int i = 0; i < M; i++) {
			int[] m = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			System.out.println(bfs(N, m[0], m[1]));
		}

	}

	private static int bfs(int n, int start, int end) {
		int cost = 0;

		boolean[] isVisited = new boolean[n + 1];
		Queue<int[]> queue = new LinkedList<>();
		isVisited[start] = true;
		queue.add(new int[] {start, 0});	//current vertex,  cost
		while(!queue.isEmpty()){
			int[] currNode = queue.poll();
			int currVertex = currNode[0];
			int currCost = currNode[1];
			if (currVertex == end) {
				cost = currCost;
				break;
			}

			for (int i = 0; i < linkedlist[currVertex].size(); i++) {
				Edge nextEdge = linkedlist[currVertex].get(i);
				if (!isVisited[nextEdge.dest]) {
					queue.add(new int[] {nextEdge.dest, currCost + nextEdge.cost});
					isVisited[nextEdge.dest] = true;
				}
			}
		}
		return cost;
	}
}

class Edge{
	int dest;
	int cost;

	public Edge(int dest, int cost) {
		this.dest = dest;
		this.cost = cost;
	}
}
