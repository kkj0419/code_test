import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //init
        int T = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
            int[] arr1= new int[N], arr2 = new int[M];
            arr1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            arr2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            bw.write(getCoupleCount(arr1, arr2) + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int getCoupleCount(int[] arr1, int[] arr2){
        int count = 0;
        Arrays.sort(arr1); Arrays.sort(arr2);
        for(int i=0; i<arr1.length; i++){
            count += binarySearch(arr2, arr1[i]);
        }
        return count;
    }

    private static int binarySearch(int[] arr, int value){
        int left = 0, right = arr.length-1;
        while(left <= right){
            int mid = (left + right)/2;
            if(arr[mid] >= value){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
}
