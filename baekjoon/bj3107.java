import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		int cnt = 0;
		List<String> address = new ArrayList<>();
		String[] tokens = input.split(":", -1);
		boolean isChecked = false;
		for (int i = 0; i < tokens.length; i++) {
			String curr = tokens[i];
			if (curr.isEmpty()) {
				if (!isChecked) {
					address.add(curr);
					isChecked = true;
				}
				continue;
			}
			address.add(curr);
			cnt++;
		}

		int remain = 8 - cnt;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < address.size(); i++) {
			if (address.get(i).isEmpty()) {
				for (int j = 0; j < remain; j++) {
					sb.append("0000:");
				}
			} else {
				sb.append(paddingString(address.get(i)));
				sb.append(":");
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}

	private static String paddingString(String s) {
		if (s.length() == 4) {
			return s;
		}
		return String.format("%4s", s).replace(' ', '0');
	}
}
