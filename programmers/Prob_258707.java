import java.util.*;
class Solution {
    public int solution(int coin, int[] cards) {
        //init

        int currPair = 0, currCoin = coin;
        int goal = cards.length + 1;
        HashSet<Integer> currCards = new HashSet<>();
        HashSet<Integer> remain = new HashSet<>();
        for(int i=0; i<cards.length/3; i++){
            int curr = cards[i];
            if(!currCards.contains(goal - curr)){
                currCards.add(cards[i]);
            }else {
                currCards.remove(goal - curr);
                currPair ++;
            }
        }

        int round = 0;
        for(int i=cards.length/3; round <= currPair && currCoin > 0 && i<cards.length; i+=2){
            for(int j=0; j<2; j++){
                if(currCoin >=1 && currCards.contains(goal - cards[i+j])) { //new - old matching
                    currCoin--;
                    currPair++;
                    currCards.remove(goal - cards[i + j]);
                }else{
                    remain.add(cards[i+j]);
                }
            }

            if(round >= currPair && currCoin >= 2){ //new - new matching
                for(int card : remain){
                    if(remain.contains(goal - card)){
                        remain.remove(card);
                        remain.remove(goal-card);
                        currPair++;
                        currCoin-=2;
                        break;
                    }
                }
            }
            round ++;
        }
        return currPair + 1;
    }
}
