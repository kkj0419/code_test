import java.util.*;

class Solution {
    
    int[][] minValue;
    public int solution(int[] a) {
        int answer = 0;
        
        int length = a.length;
        minValue = new int[2][length];  //{left, right}
        Arrays.fill(minValue[0], Integer.MAX_VALUE);
        Arrays.fill(minValue[1], Integer.MAX_VALUE);
        if(length > 1)  answer+=2;
        else    answer+=1;
        
        //left
        for(int i=1; i<length-1; i++){
            minValue[0][i] = Math.min(minValue[0][i-1], a[i-1]);
        }
        
        //right
        for(int i=length-2; i>0; i--){
            minValue[1][i] = Math.min(minValue[1][i+1], a[i+1]);
        }
        
        for(int i=1; i<length-1; i++){
            if(!(minValue[0][i] < a[i] && minValue[1][i] < a[i])){
                answer++;
            }
        }
        return answer;
    }
}
