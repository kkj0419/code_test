class Solution {
	public int solution(int storey) {
		int answer = 0;
		int currNumber = storey;
		while(currNumber != 0){
			int curr = currNumber % 10;
			if(curr <5){
				answer += curr;
			}else if(curr == 5){
				int next = (currNumber % 100) / 10;
				if(next >=5) {
					answer += (10-curr);
					currNumber += (10 - curr);
				}else{
					answer += curr;
				}
			}else{
				answer += (10 - curr);
				currNumber += (10 - curr);
			}
			currNumber /= 10;
		}
		return answer;
	}
}
