import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //init
        String input;
        while(!(input = br.readLine()).equals("0 0.00")){
            StringTokenizer st = new StringTokenizer(input);
            int maxCal = 0;
            int n = Integer.parseInt(st.nextToken()), m = (int) (Float.parseFloat(st.nextToken()) * 100.0 + 0.5);
            int[] ans = new int[m + 1];
            for (int candy = 0; candy < n; candy++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken()), p = (int) (Float.parseFloat(st.nextToken()) * 100.0 + 0.5);
                for(int i=p; i<ans.length; i++){
                    ans[i] = Math.max(c + ans[i - p], ans[i]);
                    maxCal = Math.max(maxCal, ans[i]);
                }
            }
            System.out.println(maxCal);
        }
    }
}
