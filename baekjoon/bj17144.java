import java.util.*;
import java.io.*;

public class Main{
    static class Dust{
        int r;
        int c;
        int value;
        
        public Dust(int r, int c, int value){
            this.r =r;
            this.c=c;
            this.value=value;
        }
    }
    static int[][] map;
    
    final static int[] dR = {0, 1, 0, -1};
    final static int[] dC = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r=Integer.parseInt(st.nextToken()), c= Integer.parseInt(st.nextToken()), t= Integer.parseInt(st.nextToken());
        
        map = new int[r][c];
        for(int i=0; i<r; i++){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int machine = 0;
        for(int i=0; i<r; i++){
            if(map[i][0] == -1){
                machine = i;
                break;
            }
        }
        
        //process
        for(int i=0; i<t; i++){
            diffuse();
            circulate(machine);
        }
        
        System.out.println(findTotals());
    }
    
    private static void diffuse(){
        ArrayList<Dust> dusts = new ArrayList<>();
        findDusts(dusts);
        
        for(int i=0; i<dusts.size(); i++){
            Dust curr = dusts.get(i);
            int nextVal = curr.value/5;
            
            int cnt=0;
            for(int f=0; f<dR.length; f++){
                int nextR = curr.r + dR[f], nextC = curr.c + dC[f];
                if(isAvailable(nextR, nextC)){
                    map[nextR][nextC] += nextVal;
                    cnt++;
                }
            }
            map[curr.r][curr.c] -= (cnt*nextVal);
        }
    }
    
    private static void findDusts(ArrayList<Dust> list){
        int r=map.length, c= map[0].length;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                //확산 발생하는 dust
                if(map[i][j]>=5){
                    list.add(new Dust(i, j, map[i][j]));
                }
            }
        }
    }
    
    private static boolean isAvailable(int r, int c){
        int maxR = map.length, maxC = map[0].length;
        return (r>=0 && r<maxR && c>=0 && c<maxC) && map[r][c] != -1;
    }
    
    private static void circulate(int machineIdx){
        int[] curr = {machineIdx, 0};
        
        int preVal = 0;
        for(int i=4; i>0; i--){
            int currDirection = (i==4) ? 0 :i;
            preVal = move(currDirection, preVal, curr);
        }
        
        //
        curr[0] = machineIdx +1;
        curr[1] = 0;
        
        preVal =0;
        for(int i=0; i<4; i++){
            preVal = move(i, preVal, curr);
        }
    }
    
    private static int move(int direct, int preVal, int[] curr){
        do{
            curr[0] += dR[direct];
            curr[1] += dC[direct];
            int buff = map[curr[0]][curr[1]];
            map[curr[0]][curr[1]] = preVal;
            preVal =buff;
        }while(isAvailable(curr[0] + dR[direct], curr[1]+dC[direct]));
        return preVal;
    }
    
    private static int findTotals(){
        int r=map.length, c=map[0].length;
        int sum=0;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] > 0)    sum+=map[i][j];
            }
        }
        return sum;
    }
}
