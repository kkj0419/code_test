import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution {
	boolean[] isDel;
	ArrayList<Node> nodes;

	static class Node {
		int preIdx;
		int nextIdx;
		int value;

		public Node(int preIdx, int nextIdx, int value) {
			this.preIdx = preIdx;
			this.nextIdx = nextIdx;
			this.value = value;
		}
	}

	public String solution(int n, int k, String[] cmd) {
		//init
		isDel = new boolean[n];
		nodes = new ArrayList<>();
		Stack<Integer> delNodes = new Stack<>();
		for (int i = 0; i < n; i++) {
			nodes.add(new Node(i - 1, i + 1, i));
		}

		int currIdx = k;
		StringTokenizer st;
		for (int i = 0; i < cmd.length; i++) {
			st = new StringTokenizer(cmd[i]);
			String command = st.nextToken();
			if (command.equals("U") || command.equals("D")) {
				int move = Integer.parseInt(st.nextToken());
				currIdx = findIdx(command, move, currIdx);
			} else {
				if (command.equals("C")) {
					delNodes.push(nodes.get(currIdx).value);
					isDel[currIdx] = true;
					currIdx = delNode(currIdx);
				} else {
					int zNode = delNodes.pop();
					rollback(zNode);
					isDel[zNode] = false;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < isDel.length; i++) {
			if (isDel[i]) {
				sb.append('X');
			} else {
				sb.append('O');
			}
		}

		return sb.toString();
	}

	private int findIdx(String command, int dMove, int curr) {
		int d = 0;
		int next = curr;
		if (command.equals("U")) {
			while (d != dMove) {
				next = nodes.get(next).preIdx;
				d++;
			}
		} else {
			while (d != dMove) {
				next = nodes.get(next).nextIdx;
				d++;
			}
		}
		return next;
	}

	private int delNode(int nodeIdx) {

		Node curr = nodes.get(nodeIdx);
		int preIdx = curr.preIdx, nextIdx = curr.nextIdx;
		int nextCurrIdx = nextIdx;

		if (preIdx == -1) {
			nodes.get(nextIdx).preIdx = preIdx;
		} else if (nextIdx == nodes.size()) {
			nodes.get(preIdx).nextIdx = nextIdx;
			nextCurrIdx = preIdx;
		} else {
			nodes.get(nextIdx).preIdx = preIdx;
			nodes.get(preIdx).nextIdx = nextIdx;
		}

		return nextCurrIdx;
	}

	private void rollback(int node) {
		int cursor = node - 1;
		while (cursor > 0) {
			if (!isDel[cursor]) {
				break;
			}
			cursor--;
		}
		int preIdx = cursor;
		cursor = node + 1;
		while (cursor < nodes.size()) {
			if (!isDel[cursor]) {
				break;
			}
			cursor++;
		}
		int nextIdx = cursor;

		if (preIdx != -1) {
			nodes.get(preIdx).nextIdx = node;
		}
		if (nextIdx != nodes.size()) {
			nodes.get(nextIdx).preIdx = node;
		}
		nodes.get(node).preIdx = preIdx;
		nodes.get(node).nextIdx = nextIdx;
	}
}
