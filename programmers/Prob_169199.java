import java.util.*;

class Solution {
    static class Spot{
        int x;
        int y;
        int count;
        int direction;
        
        Spot(int x, int y, int count, int direction){
            this.x = x;
            this.y = y;
            this.count = count;
            this.direction = direction;
        }
    }
    
    char[][] map;
    int n, m;
    int[][] dMove = {{0,1}, {1,0}, {0, -1}, {-1,0}};
    public int solution(String[] board) {
        //init
        n = board.length; m =board[0].length();
        map = new char[n][m];
        Spot start = null;
        for(int i=0; i<n; i++){
            map[i] = board[i].toCharArray();
            int startY = board[i].indexOf("R");
            if(startY != -1){
                start = new Spot(i, startY, 0, -1);
            }
        }
        
        return bfs(start);
    }
    
    private int bfs(Spot start){
        boolean[][][] isVisited = new boolean[4][n][m];
        LinkedList<Spot> queue = new LinkedList<>();
        queue.addLast(start);
        while(!queue.isEmpty()){
            Spot curr = queue.removeFirst();
            for(int i=0; i<dMove.length; i++){
                if(i != curr.direction && !isVisited[i][curr.x][curr.y]){
                    isVisited[i][curr.x][curr.y] = true;
                    Spot next = move(isVisited, curr.x, curr.y, i, curr.count);
                    if(map[next.x][next.y] == 'G')    return next.count;
                    isVisited[i][next.x][next.y] = true;
                    queue.addLast(next);
                }
            }
        }
        return -1;
    }
    
    private Spot move(boolean[][][] isVisited, int x, int y, int direction, int count){
        
        int currX = x, currY = y;
        while(true){
            int nextX =currX + dMove[direction][0], nextY = currY + dMove[direction][1];
            if(isAvailable(nextX, nextY) && map[nextX][nextY] != 'D'){
                currX = nextX; currY = nextY;
            }else{
                break;
            }
        }
        return new Spot(currX, currY, count + 1, direction);
    }
    
    private boolean isAvailable(int x, int y){
        return x>=0 && x<n && y>=0 && y<m;
    }
}
