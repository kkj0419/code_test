import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		for(int i=0; i<N; i++){
			char[] words = br.readLine().toCharArray();
			LinkedList<Character> stack = new LinkedList<>();
			for(int j=0; j< words.length; j++){
				if(!stack.isEmpty() && stack.getFirst() == words[j]){
					stack.removeFirst();
				}else {
					stack.addFirst(words[j]);
				}
			}
			if(stack.isEmpty())	cnt++;
		}
		System.out.println(cnt);
	}
}
