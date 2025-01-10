import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

class Solution {

    int n;
    boolean[] maxSelected;
    int maxVictories = 0;
    int[][] diceArr;
    public int[] solution(int[][] dice) {

        n = dice.length;
        boolean[] selected = new boolean[n];
        diceArr = dice;
        for(int i=0; i<n; i++){
            selected[i] = true;
            select(selected, i, 1);
            selected[i] = false;
        }

        int[] answer = IntStream.range(0, maxSelected.length).filter(i -> maxSelected[i]).map(i-> i+1).toArray();
        return answer;
    }

    private void select(boolean[] selectedA, int currSelected, int depth){
        if(depth == n/2) {    //경우의 수 cal
            int currVictories = calVictories(selectedA);
            if(currVictories > maxVictories){
               maxVictories = currVictories;
               maxSelected = selectedA.clone();
            }
            return;
        }

        //위치
        for(int i=currSelected+1; i<n; i++){
            selectedA[i] = true;
            select(selectedA, i, depth + 1);
            selectedA[i] = false;
        }
    }

    //승리 확률 계산
    private int calVictories(boolean[] selected){

        int[] aDice = IntStream.range(0, selected.length).filter(i -> selected[i]).toArray();
        int[] bDice = IntStream.range(0, selected.length).filter(i -> !selected[i]).toArray();

        ArrayList<Integer> aResult = new ArrayList<>(), bResult = new ArrayList<>();

        int victories = 0;
        cal(aResult, aDice, 0, 0);
        cal(bResult, bDice, 0, 0);
        Collections.sort(aResult);  Collections.sort(bResult);
        for(int i=0; i<aResult.size(); i++){
            victories += lowerbound(bResult, aResult.get(i));
        }
        return victories;
    }

    //가능한 모든 sum -> list 저장
    private void cal(ArrayList<Integer> result, int[] dice, int currDiceIdx, int currSum){
        if(currDiceIdx == n/2){
            result.add(currSum);
            return;
        }

        int[] currDice = diceArr[dice[currDiceIdx]];
        for(Integer num : currDice){
            cal(result, dice, currDiceIdx+1, currSum + num);
        }
    }

    private int lowerbound(ArrayList<Integer> list, int value){
        int result = Collections.binarySearch(list, value);       //value-1의 위치 or value보다 크거나 같은 최초의 위치 검색
        if(result < 0) {
            return -(result + 1);
        }else{
            while(result > 0 && list.get(result-1).equals(value)){
                result--;
            }
            return result;
        }
    }
}
