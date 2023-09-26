class Solution {
    boolean[] isVisited;
    public List<List<Integer>> permute(int[] nums) {
        isVisited = new boolean[nums.length];
        ArrayList<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> addList = new LinkedList();
        permutation(nums, 0, list, addList);
        return list;
    }
    
    private void permutation(int[] numbers, int cnt,  ArrayList<List<Integer>> list,
                            LinkedList<Integer> addList){
        if(cnt == numbers.length){
            list.add(List.copyOf(addList));
            return;
        }
        
        for(int i=0; i<numbers.length; i++){
            if(isVisited[i])    continue;
            isVisited[i] = true;
            addList.addLast(numbers[i]);
            permutation(numbers, cnt+1, list, addList);
            addList.removeLast();
            isVisited[i] = false;
        }
    }
}
