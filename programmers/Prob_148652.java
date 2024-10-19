class Solution {
	public int solution(int n, long l, long r) {
		int answer = 0;
		String s = dfs(n, l, r);
		for(int i=0; i<s.length(); i++){
			if(s.charAt(i) == '1')  answer++;
		}
		return answer;
	}

	private String dfs(int depth, long l, long r){
		if(depth == 0){
			return "1";
		}
		long nextL = (long)Math.ceil((l/5.0)), nextR = (long)Math.ceil((r/5.0));
		String currBits = dfs(depth-1, nextL, nextR);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<currBits.length(); i++){
			StringBuilder addSb = new StringBuilder();
			addSb.append(currBits.charAt(i) == '1' ? "11011" : "00000");
			if(i==0 || i==currBits.length() - 1){
				int currStart = (int)(l%5) == 0 ? 5 : (int)(l%5);
				int currEnd = (int)(r%5) == 0 ? 5 : (int)(r%5);
				if(i==0 && i==currBits.length() - 1){
					sb.append(addSb.substring(currStart-1, currEnd));
				} else if(i == 0){
					sb.append(addSb.substring(currStart-1));
				}else{
					sb.append(addSb.substring(0, currEnd));
				}
			} else{
				sb.append(addSb);
			}
		}
		return sb.toString();
	}
}
