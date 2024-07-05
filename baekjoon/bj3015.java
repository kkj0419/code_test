import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    static class Pair {
        int idx;
        long count;

        Pair(int idx, long count) {
            this.idx = idx;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        LinkedList<Pair> stack = new LinkedList<>();      //{idx, count}
        long[] count = new long[N];
        stack.addLast(new Pair(0, 1));
        for (int i = 1; i < N; i++) {
            Pair nextPair = new Pair(i, 1);
            while (!stack.isEmpty()) {
                Pair top = stack.getLast();
                if (arr[top.idx] > arr[i]) {
                    count[i] += 1;
                    break;
                } else {
                    stack.removeLast();
                    count[i] += top.count;
                    if (arr[top.idx] == arr[i]) {
                        nextPair.count += top.count;
                    }
                }
            }
            stack.addLast(nextPair);
        }

        long ans = 0;
        for (int i = 0; i < N; i++) {
            ans += count[i];
        }
        System.out.println(ans);
    }
}
