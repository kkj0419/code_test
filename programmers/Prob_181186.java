
class Solution {
    public int solution(int n) {
        long fix = 1000000007;
        long[] dp = new long[100000+1];
        long[] uniqueDp = new long[10];
        
        long[] sum = {2, 4, 12};  //4, 5, 6
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        uniqueDp[1] = 1;
        uniqueDp[2] = 2;
        uniqueDp[3] = 5;
        uniqueDp[4] = 2;
        uniqueDp[5] = 2;
        uniqueDp[6] = 4;
        for(int i=4; i<=n; i++){
            long curr = 0;
            //2, 2, 4 반복부분
            if(i>6){
                for(int j=4; j<=6; j++){
                    sum[(i-4)%3] += ((uniqueDp[j] * dp[i-j])) % fix;
                }
            }
            
            //dp[n-1]*unique[1] ~ dp[n-3](unique[3])
            for(int cnt = 1; cnt<=3; cnt++){
                curr += (dp[i-cnt] * uniqueDp[cnt]) % fix;
            }
            curr += (sum[(i-4)%3] % fix);
            dp[i] = (curr % fix);
            // System.out.println(i + " : " + dp[i]);
        }
        
        return (int)dp[n];
    }
}
