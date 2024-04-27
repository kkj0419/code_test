import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		ArrayList[] linkedlist = new ArrayList[count];
		for(int i=0;i<count; i++){
			linkedlist[i] = new ArrayList<>();
		}

		int start = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<count; i++){
			int vertex = Integer.parseInt(st.nextToken());
			if(vertex == -1){
				start = i;
			}else{
				linkedlist[vertex].add(i);
			}
		}

		int remove = Integer.parseInt(br.readLine());
		System.out.println(getLeafCount(linkedlist, start, remove));
	}

	private static int getLeafCount(ArrayList<Integer>[] list, int currVertex, int removeVertex){
		if(currVertex == removeVertex)	return 0;
		int answer = 0, count = 0;
		ArrayList<Integer> currList = list[currVertex];
		for(int i=0; i<currList.size(); i++){
			if(currList.get(i) != removeVertex){
				count++;
				answer += getLeafCount(list, currList.get(i), removeVertex);
			}
		}

		if(count == 0){
			return answer + 1;
		}
		return answer;
	}
}
