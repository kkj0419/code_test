import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static Point[] towns;

	public static void main(String[] args) throws IOException {

		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		towns = new Point[N];
		long totalPopulation = 0;
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			towns[i] = new Point(Long.valueOf(line[0]), Long.valueOf(line[1]));
			totalPopulation += towns[i].population;
		}

		//sort
		Arrays.sort(towns, (o1, o2) -> Long.compare(o1.location, o2.location));

		//cal
		long currPopulation = 0;
		for (int i = 0; i < N; i++) {
			currPopulation += towns[i].population;
			if (currPopulation >= Math.round(totalPopulation / 2.0)) {
				System.out.println(towns[i].location);
				break;
			}
		}

	}
}

class Point {
	long location;
	long population;

	public Point(long location, long population) {
		this.location = location;
		this.population = population;
	}
}
