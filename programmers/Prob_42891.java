import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
	static class Food implements Comparable<Food> {
		int idx;
		long time;

		public Food(int idx, long time) {
			this.idx = idx;
			this.time = time;
		}

		//time asc, idx asc
		@Override
		public int compareTo(Food o) {
			if (this.time == o.time) {
				return this.idx - o.idx;
			}
			return (int)(this.time - o.time);
		}
	}

	public int solution(int[] food_times, long k) {
		//init
		PriorityQueue<Food> foods = new PriorityQueue<>();
		long totalTime = 0;
		for (int i = 0; i < food_times.length; i++) {
			Food food = new Food(i + 1, food_times[i]);
			foods.add(food);

			totalTime += food_times[i];
		}

		long currTime = 0;
		long height = 0;
		if (k < totalTime) {
			while ((foods.peek().time - height) * foods.size() <= (k - currTime)) {

				int length = foods.size();
				Food currFood = foods.poll();
				currTime += (currFood.time - height) * length;
				height = currFood.time;
			}

			int idx = (int)((k - currTime) % foods.size());
			ArrayList<Food> foodList = new ArrayList<>(foods);
			Collections.sort(foodList, Comparator.comparingInt(o -> o.idx));
			return foodList.get(idx).idx;
		}
		return -1;
	}
}
