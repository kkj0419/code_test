import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int sum = 0, currK = 0;
        for(int i=0; i<enemy.length; i++){
            sum += enemy[i];
            queue.add(enemy[i]);
            if(sum > n){
                if(k == currK || sum - queue.peek() > n){
                    return i;
                }
                sum -= queue.poll();
                currK++;
            }
        }
        return enemy.length;
    }
    
}
