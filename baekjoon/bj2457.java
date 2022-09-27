import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Arrays;

public class Main {
	static Scope[] flowers;

	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		flowers = new Scope[N];
		for (int i = 0; i < N; i++) {
			int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			LocalDate start = LocalDate.of(2022, line[0], line[1]);
			LocalDate end = LocalDate.of(2022, line[2], line[3]);
			flowers[i] = new Scope(start, end);
		}

		//sort
		Arrays.sort(flowers, (o1, o2) -> {
			if (o1.startDate.compareTo(o2.startDate) == 0) {
				return -o1.endDate.compareTo(o2.endDate);
			}
			return o1.startDate.compareTo(o2.startDate);
		});
		int answer = calCount(N);
		System.out.println(answer);
	}

	private static int calCount(int N) {
		LocalDate start = LocalDate.of(2022, 3, 1);
		LocalDate end = LocalDate.of(2022, 3, 1);
		int count = 1;
		for (int i = 0; i < N; i++) {
			Scope scope = flowers[i];
			if (scope.startDate.isAfter(end)) {
				return 0;
			} else {
				//merge
				if (isBeforeAndEquals(scope.endDate, end) || isBeforeAndEquals(scope.startDate, start)) {
					end = (end.isAfter(scope.endDate)) ? end : scope.endDate;
				}
				//add
				else if (isBeforeAndEquals(scope.startDate, end) && scope.endDate.isAfter(end)) {
					count++;
					start = end;
					end = scope.endDate;
				}
			}

			if (end.isAfter(LocalDate.of(2022, 11, 30))) {
				return count;
			}
		}
		return 0;
	}

	private static boolean isBeforeAndEquals(LocalDate date, LocalDate other) {
		return date.isBefore(other) || date.compareTo(other) == 0;
	}

	private static boolean isAfterAndEquals(LocalDate date, LocalDate other) {
		return date.isAfter(other) || date.compareTo(other) == 0;
	}
}

class Scope {
	LocalDate startDate;
	LocalDate endDate;

	public Scope(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
