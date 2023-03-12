import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 1;

        for(int i=0; i<s.length()-2; i++){
            int currLen = 1;
            if(i+2<s.length() && s.charAt(i) == s.charAt(i+2)){
                int left = i, right = i+2;
                while(left>=0 && right<s.length()){
                    if(s.charAt(left) != s.charAt(right))
                        break;
                    currLen+=2;
                    left--;
                    right++;
                }
            }
            answer = Math.max(currLen, answer);
            
            currLen = 0;
            if(i+3 <s.length() && s.charAt(i) == s.charAt(i+3)){
                int left = i+1, right= i+2;
                while(left>=0 && right<s.length()){
                    if(s.charAt(left) != s.charAt(right))
                        break;
                    currLen+=2;
                    left--;
                    right++;
                }
            }
            answer = Math.max(currLen, answer);
        }

        return answer;
    }
}
