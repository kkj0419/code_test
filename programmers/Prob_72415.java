import java.util.Arrays;
import java.util.LinkedList;

class Solution {
    static class Dot{
        int x;
        int y;
        int cnt;

        Dot(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    int result = Integer.MAX_VALUE;
    int numberIdx = 0;
    int[][] dMove = {
            {0, 1}, {1, 0}, {0, -1} ,{-1, 0},
            {0, 1}, {1, 0}, {0, -1} ,{-1, 0}          // + ctrl
    };
    public int solution(int[][] board, int r, int c) {


        for(int i=0; i<board.length; i++){
            numberIdx = Math.max(Arrays.stream(board[i]).max().getAsInt(), numberIdx);
        }

        boolean[] numbers = new boolean[numberIdx + 1];         //numbers to remove
        //dfs
        for(int i=1; i<=numberIdx; i++){
            numbers[i] = true;
            int[][] map = board.clone();
            removeNumber(numbers, map, new Dot(r, c, 0), i, 0, 1);
            numbers[i] = false;
        }
        return result;
    }

    private void removeNumber(boolean[] numbers, int[][] map, Dot start, int currNum, int currCnt, int depth){

        //bfs
        boolean[][] isVisited = new boolean[4][4];
        int[][] currMap = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);
        Dot dest = bfs(currMap, isVisited, start, currNum);
        int minCnt = dest.cnt + 1;
        isVisited = new boolean[4][4];
        isVisited[dest.x][dest.y] = true;
        dest = bfs(currMap, isVisited, dest, currNum);
        minCnt += (dest.cnt + 1);

        if(depth != numberIdx){
            for(int i=1; i<numbers.length; i++){
                if(!numbers[i]){
                    numbers[i] = true;
                    removeNumber(numbers, currMap, dest, i, currCnt + minCnt, depth+1);
                    numbers[i] = false;
                }
            }
        }else {
            result = Math.min(result, currCnt + minCnt);
        }
    }

    private Dot bfs(int[][] map, boolean[][] isVisited, Dot start, int currNum){
        LinkedList<Dot> queue = new LinkedList<>();
        queue.addLast(new Dot(start.x, start.y, 0));

        while(!queue.isEmpty()){
            Dot curr = queue.removeFirst();
            if(map[curr.x][curr.y] == currNum){   //find
                map[curr.x][curr.y] = 0;
                return curr;
            }

            for(int i=0; i<dMove.length; i++){
                Dot next = move(map, isVisited, curr, i, currNum);
                if(next != null){
                    queue.addLast(next);
                    isVisited[next.x][next.y] = true;
                }
            }
        }
        return start;   //?;;
    }

    //handle can't move
    //out of bound, isNumbered(map), isVisited
    private Dot move(int[][] map, boolean[][] isVisited, Dot curr, int idx, int currNumber){

        int currX = curr.x, currY  = curr.y;
        if(idx >=4){
            int nextX = currX + dMove[idx][0], nextY = currY + dMove[idx][1];
            while(!isOutOfBound(nextX, nextY))        //currNumber 추가..
            {
                if(map[nextX][nextY] != 0){ //stop
                    currX = nextX;
                    currY = nextY;
                    break;
                }
                currX = nextX;
                currY = nextY;
                nextX += dMove[idx][0]; nextY += dMove[idx][1];
            }
        }else{
            currY = curr.y + dMove[idx][1];
            currX = curr.x + dMove[idx][0];
        }

        if(isOutOfBound(currX, currY) || isVisited[currX][currY])    return null;

        //if ) + ctrl : stop
        return new Dot(currX, currY, curr.cnt + 1);
    }

    private boolean isOutOfBound(int x, int y){
        return x < 0 || x >=4 || y < 0 || y >=4;
    }
}
