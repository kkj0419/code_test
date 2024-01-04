import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        HashMap<Integer, Integer> cntMap = new HashMap<>();

        //setting
        for(int i=0; i<nums.length; i++){
            cntMap.put(nums[i], cntMap.getOrDefault(nums[i], 0)+1);
        }

        int result = 0;
        for(Integer cnt : cntMap.values()){
            if(cnt==1)  return -1;
            result += Math.ceil(cnt/3.0);
        }

        return result;
    }
}
