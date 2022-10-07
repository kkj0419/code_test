import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
	public int[] solution(String[] gems) {
		
		//init
		Set<String> gemType = new HashSet<>();
		Collections.addAll(gemType, gems);

		int typeSize = gemType.size();

		int left = 0, right = 0;
		Map<String, GemInfo> map = new HashMap<>();
		map.put(gems[left], new GemInfo(gems[left], left));
		List<int[]> ranges = new ArrayList<>();
		if (map.size() == typeSize) {
			ranges.add(new int[] {left + 1, right + 1});
		}
		while (++right < gems.length) {
			String gem = gems[right];
			if (map.containsKey(gem) && gems[left].equals(gem)) {
				//move left
				left = moveLeft(gems, left, right, map);
			}
			map.put(gem, new GemInfo(gem, right));

			if (map.size() == typeSize) {
				ranges.add(new int[] {left + 1, right + 1});
			}
		}

		Collections.sort(ranges, Comparator.comparingInt(o -> (o[1] - o[0])));
		return ranges.get(0);
	}

	private int moveLeft(String[] gems, int left, int right, Map<String, GemInfo> map) {
		while (++left != right) {
			GemInfo info = map.get(gems[left]);
			if (info.idx == left)
				break;
		}
		return left;
	}
}

class GemInfo {
	String name;
	int idx;

	public GemInfo(String name, int idx) {
		this.name = name;
		this.idx = idx;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + idx;
		return result;
	}
}
