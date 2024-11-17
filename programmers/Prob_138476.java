import java.util.*;
class Solution {
    static class Fruit implements Comparable<Fruit>{
        int value;
        int cnt;
        
        Fruit(int value, int cnt){
            this.value = value;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(Fruit o){
            return this.cnt - o.cnt;
        }
    }
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        PriorityQueue<Fruit> queue = new PriorityQueue<>();
        Arrays.sort(tangerine);
        int currCnt = 1, currValue = tangerine[0];
        for(int i=1; i<tangerine.length; i++){
            if(currValue != tangerine[i]){
                queue.add(new Fruit(currValue, currCnt));
                currCnt = 1; currValue = tangerine[i];
            }else{
                currCnt++;
            }
        }
        queue.add(new Fruit(currValue, currCnt));
        
        int sum = tangerine.length;
        while(!queue.isEmpty() && sum > k){
            Fruit curr = queue.poll();
            sum -= curr.cnt;
        }
        return sum < k ? queue.size() + 1 : queue.size();
    }
}
