import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //init
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[100000 + 1];
        long count = 0, currCount = 0;
        int left = 0, right = 0;
        while(right < N && left <= right){
            if(cnt[numbers[right]] == 0){
                cnt[numbers[right++]]++;
                currCount++;
            }else{
                //left
                cnt[numbers[left++]] = 0;
                count += currCount;
                currCount -= 1;
            }
        }
        count += currCount;
        for(left+=1; left < N; left++){
            count += (N-left);
        }
        System.out.println(count);
    }
}
