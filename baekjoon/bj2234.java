import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Area implements Comparable<Area> {

        int idx;
        int size;
        int startX;
        int startY;

        Area(int idx, int size, int startX, int startY) {
            this.idx = idx;
            this.size = size;
            this.startX = startX;
            this.startY = startY;
        }

        @Override
        public int compareTo(Area o) {
            return o.size - this.size;
        }
    }

    static int[] dX = {0, -1, 0, 1};
    static int[] dY = {-1, 0, 1, 0};
    static int[][] map;
    static int[][] numberMap;
    static ArrayList<Area> boundList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //init
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        numberMap = new int[M][N];
        for (int i = 0; i < M; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        boolean[][] isVisited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!isVisited[i][j]) {
                    bfs(i, j, isVisited);
                }
            }
        }
        Collections.sort(boundList);
        System.out.print(boundList.size() + "\n" + boundList.get(0).size + "\n");
        System.out.println(findMaxAreas(boundList));

    }

    private static void bfs(int x, int y, boolean[][] isVisited) {
        int size = 1, currIdx = boundList.size();
        numberMap[x][y] = currIdx;
        isVisited[x][y] = true;
        LinkedList<int[]> queue = new LinkedList<>();
        queue.addLast(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] curr = queue.removeFirst();
            for (int i = 0; i < dX.length; i++) {
                int nextX = curr[0] + dX[i], nextY = curr[1] + dY[i];
                if (isAvailable(curr[0], curr[1], nextX, nextY, i) && !isVisited[nextX][nextY]) {
                    size++;
                    isVisited[nextX][nextY] = true;
                    numberMap[nextX][nextY] = currIdx;
                    queue.addLast(new int[]{nextX, nextY});
                }
            }
        }
        boundList.add(new Area(currIdx, size, x, y));
    }

    private static boolean isAvailable(int x, int y, int nextX, int nextY, int direction) {
        int M = map.length, N = map[0].length;
        int directionVal = (int) Math.pow(2.0, direction);
        return nextX >= 0 && nextX < M && nextY >= 0 && nextY < N && ((map[x][y] & directionVal) != directionVal);
    }

    private static int findMaxAreas(ArrayList<Area> list) {
        int maxArea = 0;
        Collections.sort(list, Comparator.comparingInt(o -> o.idx));
        int M = map.length, N = map[0].length;
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                for(int d=0; d<dX.length; d++){
                    int nextX = i + dX[d], nextY = j + dY[d];
                    if(nextX>=0 && nextX < M && nextY >= 0 && nextY < N && numberMap[i][j] != numberMap[nextX][nextY]){
                        maxArea = Math.max(maxArea, list.get(numberMap[i][j]).size + list.get(numberMap[nextX][nextY]).size);
                    }
                }
            }
        }
        return maxArea;
    }
}
