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
        int X = Integer.parseInt(br.readLine());

        int count = 0;
        Arrays.sort(numbers);
        for(int i=0; i<N && numbers[i] <= X/2; i++){
            int match = X - numbers[i];
            int idx = Arrays.binarySearch(numbers, match);
            if(idx >= 0 && idx != i)    count++;
        }
        System.out.println(count);
    }
}
