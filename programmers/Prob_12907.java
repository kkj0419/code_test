import java.util.*;

class Solution {
    int[] dp;
    public int solution(int n, int[] money) {
        int answer = 0;
        dp = new int[n+1];
        
        Arrays.sort(money);
        dp[0]=1;
        for(int parser=0; parser<money.length; parser++){
            for(int i=1; i<=n; i++){
                if(i-money[parser]>=0){
                    dp[i] += dp[i-money[parser]];
                }
            }
        }
        
        return dp[n];
    }
}
