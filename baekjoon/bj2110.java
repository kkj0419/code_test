import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	static List<Integer> houses;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int countOfHouse = Integer.parseInt(line.split(" ")[0]);
		int countOfRouter = Integer.parseInt(line.split(" ")[1]);

		houses = new ArrayList<>();
		for (int i = 0; i < countOfHouse; i++) {
			houses.add(Integer.valueOf(br.readLine()));
		}
		Collections.sort(houses);

		System.out.println(findMaxDistance(countOfHouse, countOfRouter));
	}

	private static int findMaxDistance(int cntOfHouse, int cntOfRouter) {
		int maxLen = houses.get(cntOfHouse - 1) - houses.get(0);
		int maxDistance = 0;
		int start = 1, end = maxLen + 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			int currValue = houses.get(0);

			//check
			for (int i = 1; i < cntOfRouter; i++) {
				int nextIdx = Collections.binarySearch(houses, currValue + mid);
				if(nextIdx < 0)	//insertion point return
					nextIdx = -nextIdx - 1;

				if (nextIdx == cntOfHouse) {
					end = mid - 1;
					break;
				}

				currValue = houses.get(nextIdx);
				if (i == cntOfRouter-1) {
					start = mid + 1;
					maxDistance = mid;
				}
			}
		}

		return maxDistance;
	}

}

