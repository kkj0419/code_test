import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int enrollCount = enroll.length;
		int[] answer = new int[enrollCount];
		Node[] nodes = new Node[enrollCount];
		//init
		for (int i = 0; i < enrollCount; i++) {
			int referIdx = -1;
			String refer = referral[i];
			if (!refer.equals("-")) {
				referIdx = IntStream.range(0, i)
					.filter(idx -> refer.equals(enroll[idx])).findFirst().orElse(-1);
				nodes[referIdx].inboundCount++;
			}
			nodes[i] = new Node(i, enroll[i], referIdx, 0);
		}

		for (int i = 0; i < seller.length; i++) {
			String sellerName = seller[i];
			int sellerIdx = IntStream.range(0, enroll.length)
				.filter(idx -> sellerName.equals(enroll[idx]))
				.findFirst()
				.orElse(-1);
			nodes[sellerIdx].addProfit(100 * amount[i]);
		}

		//cal
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].inboundCount == 0) {
				queue.add(i);
			}
		}

		//trologySort
		while (!queue.isEmpty()) {
			int currIdx = queue.poll();
			Node currNode = nodes[currIdx];
			int addValue = 0;
			int total = 0;
			int referIdx = currNode.referralIdx;
			for (int i = 0; i < currNode.profit.size(); i++) {
				int profit = currNode.profit.get(i);
				addValue = (profit / 10);
				total += (profit - addValue);
				if (referIdx != -1 && addValue != 0) {
					nodes[referIdx].addProfit(addValue);
				}
			}

			answer[currIdx] = total;
			if (referIdx != -1 && --nodes[referIdx].inboundCount == 0) {
				queue.add(referIdx);
			}
		}
		return answer;
	}
}

class Node {
	int idx;
	String name;
	List<Integer> profit = new ArrayList<>();
	// int profit = 0;
	int referralIdx;
	int inboundCount = 0;

	public Node(int idx, String name, int referralIdx, int inboundCount) {
		this.idx = idx;
		this.name = name;
		this.referralIdx = referralIdx;
		this.inboundCount = inboundCount;
	}

	public void addProfit(int cost) {
		profit.add(cost);
	}
}
