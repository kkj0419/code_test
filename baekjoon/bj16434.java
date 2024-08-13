import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Info{
        int type;
        long atk;
        int health;

        Info(int type, long atk, int health){
            this.type = type;
            this.atk = atk;
            this.health = health;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //init
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), atk = Integer.parseInt(st.nextToken());
        Info[] infos = new Info[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            infos[i] = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(binarySearch(atk, infos));
    }

    private static long binarySearch(int atk, Info[] arr){
        long left = 1, right = Long.MAX_VALUE - 1;
        while(left <= right){
            long mid = (left + right)/2;
            if(findMinHealth(mid, atk, arr)){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean findMinHealth(long mid, int atk, Info[] arr){
        long roboAtk = atk;
        long currHealth = mid;
        for(int i=0; i<arr.length; i++){
            Info curr = arr[i];
            if(curr.type == 1){
                long time = (long) Math.ceil(curr.health/(float)roboAtk);
                currHealth -= (time-1)*curr.atk;
            }else{
                currHealth = Math.min(mid, currHealth + curr.health);
                roboAtk += curr.atk;
            }
            if(currHealth <= 0) return false;
        }
        return currHealth > 0;
    }
}
