import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Main {
	static final long MAX_COST = Long.MAX_VALUE;
	static int dest;
	static long[] totalCosts;
	static ArrayList<Integer>[] linkedList;

	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		dest = (int)input[0];
		long countOfEdge = input[1], countOfZombie = input[2], range = input[3];
		input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		long defaultCost = input[0], zombieCost = input[1];

		//init
		TownInfo[] towns = new TownInfo[dest + 1];
		linkedList = new ArrayList[dest + 1];
		for (int i = 1; i <= dest; i++) {
			linkedList[i] = new ArrayList<>();
			towns[i] = new TownInfo(defaultCost, false);
		}
		for (int i = 0; i < countOfZombie; i++) {
			int townIdx = Integer.parseInt(br.readLine());
			towns[townIdx].cost = MAX_COST;
			towns[townIdx].isDanger = true;
		}
		for (int i = 0; i < countOfEdge; i++) {
			int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			linkedList[array[0]].add(array[1]);
			linkedList[array[1]].add(array[0]);
		}
		makeDangerousTown(towns, zombieCost, range);

		totalCosts = new long[dest + 1];
		Arrays.fill(totalCosts, MAX_COST);
		dijkstra(towns);
		System.out.println(totalCosts[dest] - towns[dest].cost);
	}

	private static void dijkstra(TownInfo[] towns) {
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		queue.add(new Vertex(1, 0));
		totalCosts[1] = 0;
		while (!queue.isEmpty()) {
			Vertex curr = queue.poll();
			int currTown = curr.idx;
			long currTotalCosts = curr.totalCosts;
			for (int i = 0; i < linkedList[currTown].size(); i++) {
				int nextTown = linkedList[currTown].get(i);
				long cost = currTotalCosts + towns[nextTown].cost;
				if (totalCosts[nextTown] > cost && !towns[nextTown].isDanger) {
					totalCosts[nextTown] = cost;
					queue.add(new Vertex(nextTown, cost));
				}
			}
		}
	}

	private static void makeDangerousTown(TownInfo[] towns, long cost, long range) {
		Set<Integer> townIdxSet = new HashSet<>();    //idx
		Queue<int[]> queue = new LinkedList<>(); //[idx, depth]
		for (int i = 1; i <= dest; i++) {
			if (towns[i].isDanger) {
				queue.add(new int[] {i, 0});
				townIdxSet.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int[] currTown = queue.poll();
			if (currTown[1] < range) {
				ArrayList<Integer> list = linkedList[currTown[0]];
				for (int j = 0; j < list.size(); j++) {
					int nextTown = list.get(j);
					if (!townIdxSet.contains(nextTown)) {
						townIdxSet.add(nextTown);
						queue.add(new int[] {nextTown, currTown[1] + 1});
						towns[nextTown].cost = cost;
					}
				}
			}
		}
	}
}

class Vertex implements Comparable<Vertex> {
	long totalCosts;
	int idx;

	public Vertex(int idx, long totalCosts) {
		this.totalCosts = totalCosts;
		this.idx = idx;
	}

	@Override
	public int compareTo(Vertex o) {
		//cost DESC
		return Long.compare(this.totalCosts, o.totalCosts);
	}
}

class TownInfo {
	long cost;
	boolean isDanger;

	public TownInfo(long cost, boolean isDanger) {
		this.cost = cost;
		this.isDanger = isDanger;
	}
}
