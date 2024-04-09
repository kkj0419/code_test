class Solution {
    static class Dot{
        long x;
        long y;
        
        Dot(long x, long y){
            this.x = x;
            this.y = y;
        }
        
        public void move(Dot obj){
            this.x += obj.x;
            this.y += obj.y;
        }
        
        public void back(Dot obj){
            this.x -= obj.x;
            this.y -= obj.y;
        }
    }
    Dot a, b;
    static int[][] move = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = -1;
        
        a = new Dot(x, y);
        b = new Dot(x, y);
        int cnt = queries.length;
        for(int i=cnt-1; i>=0; i--){
            int number = queries[i][0];
            long size = queries[i][1];
            if(number == 0 || number == 1){
                //y move
                long dMove = move[number][1] * -size;
                long[] next = move(a.y, b.y, dMove, m);
                if(next == null)    return 0;
                a.y = next[0];
                b.y = next[1];
            }else{
                //x move
                long dMove = move[number][0] * -size;
                long[] next = move(a.x, b.x, dMove, n);
                if(next == null)    return 0;
                a.x = next[0];
                b.x = next[1];
            }
        }
        
        return getCount();
    }
    private long[] move(long dotA, long dotB, long dMove, int maxIdx){
        //고정되는 경우 체크
        long newA = (dotA == 0 && dMove > 0) ? dotA : dotA + dMove;
        long newB = (dotB == maxIdx - 1 && dMove < 0) ? dotB : dotB + dMove;
        
        if(newA < maxIdx && newB >= 0){
            //범위 벗어나는 경우 조정
            newA = (newA < 0) ? 0 : newA;
            newB = (newB >= maxIdx) ? maxIdx-1 : newB;
        }else{
            return null;
        }
        return new long[]{newA, newB};
    }
    
    private boolean moveRange(int n, int m, Dot dMove, int direction){
        // System.out.println("dMove x : " + dMove.x + " y : " + dMove.y);
        if(direction == 0 && b.y == 0){
            a.back(dMove);
        }else if(direction == 1 && b.y == m - 1){
            a.back(dMove);
        }else if(direction == 2 && a.x == 0){
            a.back(dMove);   
        }else{
            if(b.x == n - 1){
                b.back(dMove);
            }
        }
        a.move(dMove);
        b.move(dMove);
        
        return checkAndCutRange(n, m);
    }
    
    private boolean checkAndCutRange(int n, int m){
        if(a.x >= n || a.y >= m || b.x < 0 || b.y < 0){
            a.x = -1;
            a.y = -1;
            b.x = -1;
            b.y = -1;
            return false;
        }
        
        a.x = (a.x < 0) ? 0 : a.x;
        a.y = (a.y < 0) ? 0 : a.y;
        b.x = (b.x >= n) ? n-1 : b.x;
        b.y = (b.y >= m) ? m-1 : b.y;
        return true;
    }
    
    private long getCount(){
        // System.out.println("final = a.x : " + a.x + " a.y : " + a.y + " b.x : " + b.x + " b.y : " + b.y);
        if(a.x == -1 && a.y == -1 && b.x == -1 && b.y == -1){
            return 0;
        }
        
        return (long)(b.x - a.x + 1)*(long)(b.y - a.y + 1);
    }
}
