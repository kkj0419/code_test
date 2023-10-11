class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        
        LinkedList<Integer> list = new LinkedList<>();
        ans.add(List.copyOf(list));
        for(int i=0; i<nums.length; i++){
            dfs(i, list, nums);
        }
        return ans;
    }
    
    private void dfs(int currNode, LinkedList<Integer> list, int[] nums){
        list.addLast(nums[currNode]);
        ans.add(List.copyOf(list));
        for(int i=currNode+1; i<nums.length; i++){
            dfs(i, list, nums);
        }
        list.removeLast();
    }
}
