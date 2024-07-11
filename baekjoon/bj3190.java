import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static class Vertex{
		int x;
		int y;
		int direct;
		int time;
		Vertex(int x, int y, int direct, int time){
			this.x=x;
			this.y=y;
			this.direct = direct;
			this.time = time;
		}

		Vertex move(){
			return new Vertex(x + dMove[this.direct][0], y + dMove[this.direct][1], this.direct, this.time + 1);
		}

	}

	static int[][] map; //1: snake, 2:apple
	static int[][] dMove = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), K = Integer.parseInt(br.readLine());
		map = new int[n][n];
		//apple
		for(int i=0; i<K; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken()) -1] = 2;
		}
		int L = Integer.parseInt(br.readLine());
		char[] infoArr = new char[10001];
		for(int i=0; i<L; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			infoArr[Integer.parseInt(st.nextToken())] =  st.nextToken().charAt(0);
		}

		System.out.println(move(infoArr));
	}

	private static int move(char[] infos){
		int time = 0;
		LinkedList<Vertex> queue = new LinkedList<>();
		queue.addLast(new Vertex(0, 0, 0, 0));
		map[0][0] = 1;
		while(!queue.isEmpty()){
			time++;
			boolean eating = false;
			int size = queue.size();
			for(int i=0; i<size; i++){
				Vertex curr = queue.removeFirst();
				//move
				Vertex next = curr.move();
				if (isAvailable(next)) {
					queue.addLast(next);
					if (map[next.x][next.y] == 2) {
						//lengthen
						eating = true;
					}
					map[curr.x][curr.y] = 0;
					map[next.x][next.y] = 1;
				} else {
					return time;
				}

				//turn
				if (infos[next.time] != '\u0000') {
					int currDirection = next.direct;
					if (infos[next.time] == 'D') {
						next.direct = (currDirection + 1) % 4;
					} else {
						next.direct = (currDirection == 0) ? 3 : currDirection - 1;
					}
				}

				if (eating && i == size - 1) {
					queue.addLast(curr);
					map[curr.x][curr.y] = 1;
				}
			}
		}
		return time;
	}

	private static boolean isAvailable(Vertex vertex){
		int N = map.length, x = vertex.x, y = vertex.y;
		return x >= 0 && x < N && y >= 0 && y < N && map[x][y] != 1;
	}
}
