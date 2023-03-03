import java.util.ArrayList;
import java.util.LinkedList;

class Solution {
	boolean[][][] isVisited;
	ArrayList<Integer>[] childList;

	int maxSheep = 0;
	public int solution(int[] info, int[][] edges) {
		//init
		int cnt = info.length;
		isVisited = new boolean[cnt + 1][cnt + 1][cnt];        //{현재 sheep 수, wolf 수, idx}
		childList = new ArrayList[cnt];
		for (int i = 0; i < cnt; i++) {
			childList[i] = new ArrayList<>();
		}
		for (int i = 0; i < edges.length; i++) {
			childList[edges[i][0]].add(edges[i][1]);
		}
		LinkedList<Integer> vertexList = new LinkedList<>();
		vertexList.add(0);
		dfs(info, vertexList, 0, 1, 0);
		return maxSheep;
	}

	private void dfs(int[] info, LinkedList<Integer> vertexList, int curr, int currSheep, int currWolf) {
		if (isVisited[currSheep][currWolf][curr]) {
			return;
		}

		maxSheep = Math.max(currSheep, maxSheep);
		isVisited[currSheep][currWolf][curr] = true;

		vertexList.addAll(childList[curr]);
		vertexList.remove(Integer.valueOf(curr));
		for (Integer next : vertexList) {

			if (info[next] == 0) {    //sheep
				dfs(info, new LinkedList<>(vertexList), next, currSheep + 1, currWolf);
			} else {                //wolf
				if (currSheep > currWolf + 1) {
					LinkedList<Integer> nextList = new LinkedList<>(vertexList);
					dfs(info, nextList, next, currSheep, currWolf + 1);
				}
			}
		}
	}
}
