import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int size = Integer.parseInt(br.readLine());
			int[] numArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] nge = new int[size];
			LinkedList<Integer> queue = new LinkedList<>();
			for (int i = numArr.length-1; i >= 0; i--) {
				while(!queue.isEmpty() && queue.getLast() <= numArr[i]){
					queue.removeLast();
				}
				if(queue.isEmpty()){
					queue.addLast(numArr[i]);
					nge[i] = -1;
				}else {
					if(queue.getLast() > numArr[i]){
						nge[i] = queue.getLast();
						queue.addLast(numArr[i]);
					}
				}
			}

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			for (int i = 0; i < nge.length; i++) {
				bw.write(nge[i] + " ");
			}
			bw.flush();
			bw.close();
		}
}
