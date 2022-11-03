import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Pattern;

class Solution {
	boolean[] isVisited;
	HashSet<HashSet<String>> set;
	LinkedList<String> input = new LinkedList<>();

	public int solution(String[] user_id, String[] banned_id) {
		int length = banned_id.length;
		isVisited = new boolean[user_id.length];
		set = new HashSet<>();
		for (int i = 0; i < length; i++) {
			banned_id[i] = banned_id[i].replaceAll("[*]", ".");
		}

		dfs(0, user_id, banned_id);
		return set.size();
	}

	private void dfs(int depth, String[] user_id, String[] banned_id) {
		if (depth == banned_id.length) {
			set.add(new HashSet<>(input));
			return;
		}

		for (int i = 0; i < user_id.length; i++) {
			if (!isVisited[i] && Pattern.matches(banned_id[depth], user_id[i])) {
				isVisited[i] = true;
				input.addLast(user_id[i]);
				dfs(depth + 1, user_id, banned_id);
				input.removeLast();
				isVisited[i] = false;
			}
		}
	}
}
