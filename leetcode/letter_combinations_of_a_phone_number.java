class Solution {
    String[] arr = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    ArrayList<String> ans = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if(digits.length() != 0){
            dfs(0, "", digits);
        }
        
        return ans;
    }
    
    private void dfs(int stringIdx, String currString, String origin){
        if(currString.length() == origin.length()){
            ans.add(currString);
            return;
        }
        
        int toNumber = origin.charAt(stringIdx) - '0';
        for(int i=0; i<arr[toNumber].length(); i++){
            dfs(stringIdx+1, currString + arr[toNumber].charAt(i), origin);
        }
    }
}
