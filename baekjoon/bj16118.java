import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static class Vertex implements Comparable<Vertex> {
		int idx;
		int cost;
		int isDoubleCost = 0;

		public Vertex(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		public Vertex(int idx, int cost, int isDoubleCost) {
			this.idx = idx;
			this.cost = cost;
			this.isDoubleCost = isDoubleCost;
		}

		//cost asc
		@Override
		public int compareTo(Vertex o) {
			return this.cost - o.cost;
		}
	}

	static ArrayList<Vertex>[] linkedlist;
	static int[] foxCost;
	static int[][] wolfCost;

	static final int MAX_COST = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//init
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		linkedlist = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			linkedlist[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			linkedlist[start].add(new Vertex(end, cost));
			linkedlist[end].add(new Vertex(start, cost));
		}

		foxCost = new int[N + 1];
		wolfCost = new int[2][N + 1];

		Arrays.fill(foxCost, MAX_COST);
		Arrays.fill(wolfCost[0], MAX_COST);
		Arrays.fill(wolfCost[1], MAX_COST);

		makeFoxCost();
		makeWolfCost();

		//compare
		int win = 0;
		for (int i = 2; i <= N; i++) {
			if (foxCost[i] < Math.min(wolfCost[0][i], wolfCost[1][i])) {
				win++;
			}
		}
		System.out.println(win);
	}

	private static void makeFoxCost() {

		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		queue.add(new Vertex(1, 0));
		while (!queue.isEmpty()) {
			Vertex curr = queue.poll();
			int currIdx = curr.idx;
			int currCost = curr.cost;

			if (foxCost[currIdx] < currCost) {
				continue;
			}

			ArrayList<Vertex> list = linkedlist[currIdx];
			for (int i = 0; i < list.size(); i++) {

				Vertex next = list.get(i);
				int nextIdx = next.idx;
				int dCost = next.cost;

				int nextCost = dCost * 2 + currCost;
				if (foxCost[nextIdx] > nextCost) {
					foxCost[nextIdx] = nextCost;
					queue.add(new Vertex(nextIdx, nextCost));
				}
			}
		}
	}

	private static void makeWolfCost() {

		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		queue.add(new Vertex(1, 0, 0));    //출발 /2
		while (!queue.isEmpty()) {
			Vertex curr = queue.poll();
			int currDoubleCost = curr.isDoubleCost;
			int currIdx = curr.idx;
			int currCost = curr.cost;

			if (wolfCost[currDoubleCost][currIdx] < currCost) {
				continue;
			}

			ArrayList<Vertex> list = linkedlist[currIdx];
			for (int i = 0; i < list.size(); i++) {
				Vertex next = list.get(i);

				int nextIdx = next.idx;
				int nextDouble = 1 - currDoubleCost;

				int nextCost;
				int dCost = next.cost;
				if (currDoubleCost == 1) {
					nextCost = currCost + dCost * 4;
				} else {
					nextCost = currCost + dCost;
				}

				if (nextCost < wolfCost[nextDouble][nextIdx]) {
					wolfCost[nextDouble][nextIdx] = nextCost;
					queue.add(new Vertex(nextIdx, nextCost, nextDouble));
				}
			}
		}
	}
}
