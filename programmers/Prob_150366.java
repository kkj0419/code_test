import java.util.*;

class Solution {

    int[] parent = new int[2501];
    String[] value = new String[2501];
    public String[] solution(String[] commands) {

        //init
        for(int i=0; i<parent.length; i++){
            parent[i] = i;
        }
        ArrayList<String> answer = new ArrayList<>();
        for(int i=0; i<commands.length; i++){
            execute(commands[i], answer);
        }
        return answer.toArray(new String[answer.size()]);
    }

    private void execute(String command, ArrayList<String> print){
        String[] args = command.split(" ");
        switch (args[0]) {
            case "UPDATE":
                update(args);
                break;
            case "MERGE":
                merge(args);
                break;
            case "UNMERGE":
                unmerge(args);
                break;
            case "PRINT":
                int r = Integer.parseInt(args[1]), c = Integer.parseInt(args[2]);
                int parent = find(convert(r, c));
                print.add(value[parent] != null ? value[parent] : "EMPTY");
        }
    }

    private int find(int idx){
        if(parent[idx] == idx)  return idx;
        return find(parent[idx]);       //check
    }

    private void update(String[] args){
        if(args.length == 4){
            //find parent
            int r = Integer.parseInt(args[1]), c = Integer.parseInt(args[2]);
            int parent = find(convert(r, c));
            //update
            value[parent] = args[3];
        }else{
            if(!args[1].equals(args[2])) {
                //UPDATE korean hansik
                for(int i=0; i<value.length; i++){
                    if(value[i] != null && value[i].equals(args[1]))
                        value[i] = args[2];
                }
            }
        }
    }

    private void merge(String[] args){
        int r1 = Integer.parseInt(args[1]), c1 = Integer.parseInt(args[2]);
        int r2 = Integer.parseInt(args[3]), c2 = Integer.parseInt(args[4]);

        int dest = find(convert(r1, c1)), depart = find(convert(r2, c2));
        if(dest == depart)  return;     //ignore

        //merge?
        parent[depart] = dest;

        if(value[dest] != null && value[depart] != null){
            value[depart] = null;
        }else{
            String result = (value[dest] != null) ? value[dest] : value[depart];
            value[dest] = result;
            value[depart] = null;
        }
    }

    private void unmerge(String[] args){
        int r = Integer.parseInt(args[1]), c = Integer.parseInt(args[2]);
        //find parent
        int parent = find(convert(r,c));
        String resultVal = value[parent];
        value[parent] = null;

        //unmerge
        //update parent[]
        reset(parent);
        value[convert(r,c)] = resultVal;
    }

    private void reset(int parentVal){
        List<Integer> deleteList = new ArrayList<>();
        for(int i=1; i<=2500; i++){
            if(find(i) == parentVal){
                deleteList.add(i);
            }
        }
        for(Integer i : deleteList){
            parent[i] = i;
        }
    }

    private int convert(int r, int c){
        return (r-1) * 50 + c;
    }
}
