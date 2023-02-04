import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
	public String solution(int n, int t, int m, String[] timetable) {
		String answer;
		Arrays.sort(timetable);
		List<String> busTime = calBusTime(t, n);
		int currTimeTableIdx = 0;
		int currCrewCount = 0;
		for (int i = 0; i < busTime.size(); i++) {
			currCrewCount = 0;
			//탑승
			while (currTimeTableIdx < timetable.length && isAvailable(busTime.get(i), timetable[currTimeTableIdx])) {
				if (currCrewCount >= m) {    //인원 초과
					break;
				}
				currCrewCount++;
				currTimeTableIdx++;
			}
		}

		if (currCrewCount == m) {
			answer = calTime(timetable[currTimeTableIdx - 1], -1);
		} else {
			answer = busTime.get(busTime.size() - 1);
		}

		return answer;
	}

	private List<String> calBusTime(int timeSlice, int count) {
		List<String> times = new ArrayList<>();
		String currTime = "09:00";
		times.add(currTime);
		for (int i = 0; i < count - 1; i++) {
			String nextTime = calTime(currTime, timeSlice);
			times.add(nextTime);
			currTime = nextTime;
		}
		return times;
	}

	private boolean isAvailable(String busTime, String crewTime) {
		return busTime.compareTo(crewTime) >= 0;
	}

	private String calTime(String standard, int time) {
		int[] timeArr = Arrays.stream(standard.split(":")).mapToInt(Integer::parseInt).toArray();    //{HH, MM}
		timeArr[1] += time;
		if (timeArr[1] >= 60) {
			timeArr[0] += 1;
			timeArr[1] -= 60;
		} else if (timeArr[1] < 0) {
			timeArr[0] -= 1;
			timeArr[1] += 60;
		}
		return toTimeString(timeArr);
	}

	private String toTimeString(int[] time) {
		return String.format("%02d:%02d", time[0], time[1]);
	}

}
