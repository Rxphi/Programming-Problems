import java.util.*;

public class CryptoEmpire {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		
		Map<Integer, Integer> receipts = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < n; i++) {
			int id = sc.nextInt();
			int cash = sc.nextInt();
			receipts.put(id, cash);
		}
		
		int sum = 0;
		for (int c : receipts.values()) {
			sum += c;
		}
		System.out.println(sum);
	}
}