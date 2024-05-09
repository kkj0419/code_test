import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Material{
		int p;
		int f;
		int s;
		int v;
		int cost;

		Material(int p, int f, int s, int v, int cost) {
			this.p = p;
			this.f=f;
			this.s=s;
			this.v=v;
			this.cost=cost;
		}

		Material sum(Material m) {
			return new Material(this.p + m.p, this.f + m.f, this.s + m.s, this.v + m.v, this.cost + m.cost);
		}
	}

	static int[] standard = new int[4];
	static Material[] materials;
	static boolean[] isVisited;
	static int minCost = Integer.MAX_VALUE;
	static List<Integer> minList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		standard = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		materials = new Material[N]; //{p, f, s, v, cost}
		isVisited = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			materials[i] = new Material(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < N; i++) {
			isVisited[i] = true;
			ArrayList<Integer> list = new ArrayList<>();
			list.add(i);
			dfs(i, new Material(materials[i].p, materials[i].f, materials[i].s, materials[i].v, materials[i].cost), materials[i].cost, list);
			isVisited[i] = false;
		}

		if(minList.isEmpty()){
			System.out.print(-1);
		}else{
			System.out.println(minCost);
			for (int i=0; i<minList.size(); i++) {
				System.out.print(minList.get(i)+1 + " ");
			}
		}
	}

	private static void dfs(int curr, Material currMaterial, int currCost, ArrayList<Integer> currList) {
		if (currCost >= minCost){
		 	return;
		}
		else if(checkFull(currMaterial)) {
			minCost = Math.min(minCost, currCost);
			minList = new ArrayList<>();
			minList.addAll(currList);
			return;
		}

		for (int i = curr + 1; i < materials.length; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				ArrayList<Integer> nextList = new ArrayList<>(currList);
				nextList.add(i);
				dfs(i, currMaterial.sum(materials[i]),currCost + materials[i].cost, nextList);
				isVisited[i] = false;
			}
		}

	}

	private static boolean checkFull(Material material) {
		return material.p >= standard[0] && material.f >= standard[1]
			&& material.s >= standard[2] && material.v >= standard[3];
	}
}
