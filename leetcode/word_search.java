class Solution {
    //좌표
    static class Coord{
        int x;
        int y;
        Coord(int x, int y){
            this.x= x;
            this.y=y;
        }
    }
    static int[] dX = {0, 1, 0, -1};
    static int[] dY = {1, 0, -1, 0};
    
    boolean[][] isVisited;
    int height, width;
    boolean ans = false;
    public boolean exist(char[][] board, String word) {
        height = board.length;
        width = board[0].length;
        isVisited = new boolean[height][width];
        
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(board[i][j] == word.charAt(0)){
                    isVisited[i][j] = true;
                    dfs(new Coord(i,j), board, word, 1);
                    isVisited[i][j] = false;
                }
            }
        }
        return ans;
        
    }
    private void dfs(Coord curr, char[][] board, String word, int depth){
        if(depth == word.length() || ans){
            ans = true;
            return;
        }
        
        for(int i=0; i<dX.length; i++){
            int nextX = curr.x + dX[i], nextY = curr.y + dY[i];
            if(!isOutOfBound(nextX, nextY) && !isVisited[nextX][nextY] 
               && board[nextX][nextY] == word.charAt(depth)){
                isVisited[nextX][nextY] = true;
                dfs(new Coord(nextX, nextY), board, word, depth+1);
                isVisited[nextX][nextY] = false;
            }
        }
    }
    
    private boolean isOutOfBound(int x, int y){
        return (x>= height || x<0 || y>=width || y<0);
    }
}
