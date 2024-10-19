import java.util.*;
class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        double number1 = Math.pow(r1, 2.0);
        double number2 = Math.pow(r2, 2.0);
        for(int i=1; i<=r2; i++){
            double length2= Math.sqrt(number2-Math.pow(i, 2.0));
            double length1= Math.sqrt(number1-Math.pow(i, 2.0));
            if(r1 <= i){
                answer += (Math.floor(length2) + 1);
            }else{
                answer += (Math.floor(length2) - Math.ceil(length1) + 1);
            }
        }
        
        return answer*4;
    }
}
