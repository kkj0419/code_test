import java.util.*;
class Solution {
    ArrayList<Integer>[] linkedlist;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        //init
        int[] answer = new int[sources.length];
        linkedlist = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            linkedlist[i] = new ArrayList<>();
        }
        for(int i=0; i<roads.length; i++){
            int sour = roads[i][0], dest = roads[i][1];
            linkedlist[sour].add(dest);
            linkedlist[dest].add(sour);
        }
        int[] isCnt = bfs(destination);
        for(int i=0; i<sources.length; i++){
            answer[i] = isCnt[sources[i]];
        }
        
        return answer;
    }
    
    private int[] bfs(int source){
        int[] isVisited = new int[linkedlist.length];
        Arrays.fill(isVisited, -1);
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(source);
        isVisited[source]=0;
        while(!que.isEmpty()){
            int curr = que.removeFirst();
            
            ArrayList<Integer> linked = linkedlist[curr];
            for(int i=0; i<linked.size(); i++){
                int next = linked.get(i);
                if(isVisited[next]==-1){
                    que.addLast(next);
                    isVisited[next]=isVisited[curr]+1;
                }
            }
        }
        
        return isVisited;
    }
}
