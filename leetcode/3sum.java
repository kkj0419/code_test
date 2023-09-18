import java.util.*;

class Solution {
    List<List<Integer>> list;
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        list = new ArrayList<>();
        //cal
        int prev = nums[0]-1;
        for(int i=0; i<nums.length-2; i++){
            if(prev == nums[i]) continue;
            int left=i+1, right=nums.length-1;
            while(left<right){
                int sum = nums[left] + nums[right];
                if(sum + nums[i] == 0){
                    list.add(List.of(nums[i], nums[left], nums[right]));
                    right = moveLeft(nums, right);
                    left = moveRight(nums, left);
                }
                else if(sum + nums[i] < 0)  left=moveRight(nums, left);
                else    right = moveLeft(nums, right);
            }
            prev = nums[i];
        }
        return list;
    }
    
    private int moveLeft(int[] nums, int currIdx){
        int curr = nums[currIdx];
        while(currIdx >= 0){
            if(nums[currIdx] != curr)   break;
            currIdx--;

        }
        return currIdx;
    }
    
    private int moveRight(int[] nums, int currIdx){
        int curr = nums[currIdx];
        while(currIdx < nums.length){
            if(nums[currIdx] != curr)   break;
            currIdx++;
        }
        return currIdx;
    }
}
