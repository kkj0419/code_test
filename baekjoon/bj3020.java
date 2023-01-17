import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line.split(" ")[0]);
		int H = Integer.parseInt(line.split(" ")[1]);

		//init
		PriorityQueue<Line> outList = new PriorityQueue<>(
			Comparator.comparingInt(o -> o.end));    	//end asc
		PriorityQueue<Line> inList = new PriorityQueue<>(
			Comparator.comparingInt(o -> o.start));    //start asc
		for (int i = 0; i < N; i++) {
			int size = Integer.parseInt(br.readLine());
			if (i % 2 == 0) {    //out
				outList.add(new Line(0, size));
			} else {            //in
				inList.add(new Line(H - size, H));
			}
		}

		int minCount = outList.size(), currCount = outList.size();
		int sectionCount = 0;
		for (int i = 0; i < H; i++) {

			//out
			while (!outList.isEmpty() && outList.peek().end == i) {
				outList.poll();
				currCount--;
			}

			//in
			while (!inList.isEmpty() && inList.peek().start == i) {
				inList.poll();
				currCount++;
			}

			if (minCount > currCount) {
				minCount = currCount;
				sectionCount = 1;
			} else if (minCount == currCount) {
				sectionCount++;
			}
		}

		System.out.printf("%d %d", minCount, sectionCount);
	}
}

class Line {
	int start;
	int end;

	public Line(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

