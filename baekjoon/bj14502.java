import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int N;
	static int M;

	static final int[] dX = {0, 1, 0, -1};
	static final int[] dY = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ArrayList<Integer> forbidList = new ArrayList<>();
		map = new int[N][M];
		for(int i=0; i<N; i++){
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int j=0; j<M; j++){
				if(map[i][j] == 2)	forbidList.add(i*M + j);
			}
		}

		int minValue = 100;
		for(int a=0; a <N * M; a++){
			if(!isAvailable(a))	continue;
			for(int b=a+1; b <N * M; b++){
				if(!isAvailable(b)) continue;
				for(int c = b+1; c < N * M; c++){
					if(!isAvailable(c))	continue;
					minValue = Math.min(getCntForbidden(forbidList, a, b, c), minValue);
				}

			}
		}
		int privacy = 0;
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(map[i][j] == 1)	privacy++;
			}
		}

		System.out.println(N*M - privacy-minValue-3);
	}

	private static boolean isAvailable(int idx){
		int n = idx / M;
		int m = idx % M;
		return map[n][m] == 0;
	}
	private static int getCntForbidden(ArrayList<Integer> list, int a, int b, int c){
		LinkedList<int[]> queue = new LinkedList<>();
		boolean[][] isForbidden = new boolean[N][M];
		int cnt = 0;
		for(int i=0; i<list.size(); i++){
			int start = list.get(i);
			queue.addLast(new int[]{start/M, start%M});
			isForbidden[start/M][start%M] = true;
			cnt++;
			while(!queue.isEmpty()){
				int[] curr = queue.removeFirst();
				for(int j=0; j<4; j++){
					int nextX = curr[0] + dX[j], nextY = curr[1] + dY[j];
					if(isAvailable(nextX, nextY, a, b, c) && !isForbidden[nextX][nextY])	{
						isForbidden[nextX][nextY] = true;
						cnt++;
						queue.addLast(new int[]{nextX, nextY});
					}
				}
			}
		}

		return cnt;
	}

	private static boolean isAvailable(int x, int y, int a, int b, int c){
		int curr = x*M + y;
		return x>=0 && x<N && y>=0 && y<M &&
			map[x][y] == 0 && curr != a && curr != b && curr != c;
	}
}
