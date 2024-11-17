import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);
        //index
        for(int i=answer.length-2; i>=0 ;i--){
            int currValue = numbers[i];
            for(int j=i+1; j<answer.length; ){
                int searchIdx = answer[j];
                if(numbers[j] > currValue){
                    answer[i] = j;
                    break;
                }else if(searchIdx == -1){
                    break;
                }else{
                    j = answer[j];
                }
            }
        }
        
        //value
        for(int i=0; i<answer.length; i++){
            if(answer[i] != -1) answer[i] = numbers[answer[i]];
        }
        return answer;
    }
}
