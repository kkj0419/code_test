import java.io.*;

public class Main {
    static int[] count = new int[50];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //init
        int M = Integer.parseInt(br.readLine());
        int idx = 0;
        count[1] = 5; count[2] = 13;
        for(int i=3; i<count.length; i++){
            int next = count[i-1] + 1 + count[i-2];
            count[i] = next;
            idx = i;
            if(next >= M) break;
        }

        char found = findChar(idx, M);
        if(found != ' '){
            System.out.println(found);
        }else{
            System.out.println("Messi Messi Gimossi");
        }
    }

    private static char findChar(int idx, int M){
        if(idx == 1){
            return "Messi".charAt(M-1);
        }else if(idx == 2){
            return "Messi Gimossi".charAt(M-1);
        }

        int left = count[idx-1], right = count[idx-2];
        if(M <= left){
            return findChar(idx-1, M);
        }else if(M > left + 1){
            return findChar(idx-2, M - (left+1));
        }else{
            return ' ';
        }
    }
}
