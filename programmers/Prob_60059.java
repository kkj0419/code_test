
class Solution {
	public boolean solution(int[][] key, int[][] lock) {
		int[][] expandedLock = new int[lock.length + key.length * 2 - 2][lock.length + key.length * 2 - 2];
		for (int i = 0; i < lock.length; i++) {
			for (int j = 0; j < lock.length; j++) {
				if (lock[i][j] == 0)
					expandedLock[key.length - 1 + i][key.length - 1 + j] = 1;
				else
					expandedLock[key.length - 1 + i][key.length - 1 + j] = 0;
			}
		}

		return compareTo(expandedLock, key, lock.length);
	}

	private boolean compareTo(int[][] lock, int[][] key, int lockSize) {
		int[][] temp = new int[lock.length][lock.length];
		if (compare(lock, key.length, lockSize)) {
			return true;
		}

		for (int i = 0; i <= lock.length - key.length; i++) {
			for (int j = 0; j <= lock.length - key.length; j++) {

				for (int r = 0; r < 4; r++) {
					initMap(temp, lock);
					key = rotate(key);
					calKey(key, temp, i, j);
					if (compare(temp, key.length, lockSize))
						return true;
				}

			}
		}
		return false;
	}

	private void initMap(int[][] map, int[][] depart) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = depart[i][j];
			}
		}
	}

	private int[][] rotate(int[][] key) {
		int[][] temp = new int[key.length][key.length];
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key.length; j++) {
				temp[i][j] = key[key.length - 1 - j][i];
			}
		}
		return temp;
	}

	private void calKey(int[][] key, int[][] map, int x, int y) {
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key.length; j++) {
				map[x + i][y + j] += key[i][j];
			}
		}
	}

	private boolean compare(int[][] map, int key, int lock) {
		for (int i = 0; i < lock; i++) {
			for (int j = 0; j < lock; j++) {
				if (map[i + key - 1][j + key - 1] == 1)
					return false;
			}
		}
		return true;
	}
}
