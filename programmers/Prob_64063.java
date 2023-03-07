import java.util.HashMap;
import java.util.LinkedList;

class Solution {
	public long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];

		LinkedList<Long> numberList = new LinkedList<>();
		HashMap<Long, Long> bookedRoom = new HashMap<>();
		int idx = 0;
		for (int i = 0; i < room_number.length; i++) {
			long currNum = room_number[i];
			if (bookedRoom.containsKey(currNum)) {
				numberList.add(currNum);
				long nextNum = bookedRoom.get(currNum);
				while (bookedRoom.containsKey(nextNum)) {
					numberList.add(nextNum);
					nextNum = bookedRoom.get(nextNum);
				}

				for (Long number : numberList) {
					bookedRoom.put(number, nextNum);
				}
				currNum = nextNum;
				numberList.clear();
			}
			answer[idx++] = currNum;
			bookedRoom.put(currNum, currNum + 1);
		}

		return answer;
	}
}
