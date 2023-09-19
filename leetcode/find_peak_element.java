import java.util.*;

class Solution {
    static class Number implements Comparable<Number>{
        int value;
        int idx;
        
        Number(int value, int idx){
            this.value = value;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Number n){
            return n.value - this.value;
        }
    }
    public int findPeakElement(int[] nums) {
        List<Number> list = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            list.add(new Number(nums[i], i));
        }
        
        Collections.sort(list);
        return list.get(0).idx;        
    }
}
