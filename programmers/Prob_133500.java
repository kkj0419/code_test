import java.util.*;

class Solution {
	static class Edge{
		int start;
		int end;

		Edge(int start, int end){
			this.start = start;
			this.end = end;
		}
	}

	ArrayList<Edge>[] linked;
	int[][] dp;
	public int solution(int n, int[][] lighthouse) {
		//init
		dp = new int[n+1][2];	//{idx, 켜짐 여부}
		linked = new ArrayList[n+1];
		boolean[] onLights = new boolean[n+1];
		for(int i=0; i<=n; i++){
			linked[i] = new ArrayList<>();
		}

		for(int i=0; i<lighthouse.length; i++){
			int a = lighthouse[i][0], b = lighthouse[i][1];
			linked[a].add(new Edge(a, b));   linked[b].add(new Edge(b, a));
		}

		int start = lighthouse[0][0];
		dfs(start, onLights);

		return Math.min(dp[start][0], dp[start][1]);
	}

	//서브 트리는 불빛이 들어와 잇어야 됨
	//start를 root로 가지는 서브 트리에서의 최소 등대개수 계산
	private void dfs(int start, boolean[] isVisited){
		dp[start][0] = 0;
		dp[start][1] = 1;
		
		ArrayList<Edge> currEdge = linked[start];
		isVisited[start] = true;
		for(int i=0; i<currEdge.size(); i++){
			int next = currEdge.get(i).end;
			if(isVisited[next])	continue;
			dfs(next, isVisited);
			
			dp[start][0] += dp[next][1];	//child light on
			dp[start][1] += Math.min(dp[next][1], dp[next][0]);
		}
		isVisited[start] = false;
	}
}
