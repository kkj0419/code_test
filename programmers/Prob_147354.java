import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int x = data.length, y = data[0].length;
        Arrays.sort(data, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[col-1] == o2[col-1])  return o2[0] - o1[0];
                return o1[col-1] - o2[col-1];
            }
        });
        int answer = makeSum(row_begin, data[row_begin-1]);
        for(int i=row_begin + 1; i<=row_end; i++){
            answer ^= makeSum(i, data[i-1]);
        }
        return answer;
    }
    
    private int makeSum(int idx, int[] arr){
        int result = 0;
        for(int i=0; i<arr.length; i++){
            result += (arr[i] % idx);
        }
        return result;
    }
}
