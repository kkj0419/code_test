import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] dMove = {{1, 0}, {0, 1}};
    static int[][] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //init
        int N = Integer.parseInt(st.nextToken()), L = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for(int i=0; i<N; i++){
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int count = 0;
        //세로
        for(int i=0; i<N; i++){
            count += searchLine(0, i, 0, L);
        }
        //세로
        for(int i=0; i<N; i++){
            count += searchLine(i, 0, 1, L);
        }
        System.out.println(count);
    }


    private static int searchLine(int x, int y, int direction, int L){
        boolean[][] isOccupied = new boolean[arr.length][arr.length];
        int currHeight = arr[x][y], currFloorLength = 1;
        for(int i=1; i<arr.length; i++){
            int nextX = x + i * dMove[direction][0], nextY = y + i * dMove[direction][1];
            int buff = Math.abs(currHeight - arr[nextX][nextY]);
            if(buff == 0){          //동일 floor
                if(isOccupied[nextX][nextY]){
                    currFloorLength = 0;
                }else{
                    currFloorLength++;
                }
                continue;
            }else if(buff > 1){
                return 0;
            }

            if(currHeight < arr[nextX][nextY]){
                //up
                if(currFloorLength < L) return 0;
            }else{
                //down
                for(int j=0; j<L; j++){
                    if(i+j >= arr.length)    return 0;
                    int searchX = x + (i + j) * dMove[direction][0], searchY = y + (i + j) * dMove[direction][1];
                    isOccupied[searchX][searchY] = true;
                    if(arr[nextX][nextY] != arr[searchX][searchY])  return 0;
                }
            }
            currFloorLength = (isOccupied[nextX][nextY] ? 0 : 1);
            currHeight = arr[nextX][nextY];
        }
        return 1;
    }
}
