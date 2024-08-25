import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
	static class Info{
		int number;
		int cnt;

		Info(int number, int cnt){
			this.number = number;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] isVisited = new int[N+1];
		Arrays.fill(isVisited, -1);
		isVisited[N] = 0;

		LinkedList<Info> queue = new LinkedList<>();
		int cnt = 0;
		queue.addLast(new Info(N, 0));
		while(!queue.isEmpty()){
			Info curr = queue.removeFirst();
			if(curr.number == 1){
				cnt = curr.cnt;
				break;
			}
			for(int i=3; i>0; i--){
				int number = curr.number;
				if (i == 1) {
					if (number - 1 > 0 && isVisited[number - 1] == -1) {
						isVisited[number-1] = number;
						queue.addLast(new Info(number-1, curr.cnt + 1));
					}
				}else{
					if(number%i == 0 && isVisited[number/i] == -1){
						isVisited[number/i] = number;
						queue.addLast(new Info(number/i, curr.cnt + 1));
					}
				}
			}
		}
		System.out.println(cnt);
		int[] ans = new int[cnt+1];
		ans[cnt] = 1;
		for(int i=cnt; i>0 ;i--){
			ans[i-1] = isVisited[ans[i]];
		}

		for(int i=0; i<=cnt; i++){
			System.out.print(ans[i]);
			if(i != cnt)	System.out.print(" ");
		}
	}

}
