import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		//init
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		Node[] company = new Node[n + 1];
		for (int i = 1; i <= n; i++) {
			company[i] = new Node();
		}

		//childIdx
		stk = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int parent = Integer.parseInt(stk.nextToken());
			if (parent != -1) {
				company[parent].addChild(i);
			}
		}

		//value
		for (int i = 0; i < m; i++) {
			stk = new StringTokenizer(br.readLine());
			int nodeIdx = Integer.parseInt(stk.nextToken());
			int addValue = Integer.parseInt(stk.nextToken());
			company[nodeIdx].value += addValue;
		}

		//cal
		int[] value = new int[n];
		Queue<Integer> searchQue = new LinkedList<>();
		searchQue.add(1);
		while (!searchQue.isEmpty()) {
			int currIdx = searchQue.poll();
			int addValue = company[currIdx].value;
			List<Integer> childList = company[currIdx].childs;
			for (int i = 0; i < childList.size(); i++) {
				int childIdx = childList.get(i);
				searchQue.add(childIdx);
				company[childIdx].value += addValue;
				value[childIdx - 1] = company[childIdx].value;
			}
		}

		//print
		for (int i = 0; i < value.length; i++) {
			System.out.print(value[i] + " ");
		}
	}
}

class Node {
	List<Integer> childs = new ArrayList<>();
	int value = 0;

	public Node() {
	}

	public void addChild(int childIdx) {
		childs.add(childIdx);
	}
}
