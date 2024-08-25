import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //init
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int[] numbers = new int[n];
        for(int i=0; i<n; i++){
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int[] ans = new int[k+1];
        ans[0] = 1;
        for(int nIdx=0; nIdx<n; nIdx++){
            int number = numbers[nIdx];
            for(int i=number; i<=k; i++){
                ans[i]+=ans[i-number];
            }
        }
        System.out.println(ans[k]);
    }
}
