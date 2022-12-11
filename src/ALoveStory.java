import java.util.*;

// funktioniert aber sehr ineffizient :)

public class ALoveStory {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		
		for (int i = 0; i < n; i++) {
			String word = sc.next();
			System.out.println(sumOfLove(word));
		}
		
	}

	public static int love(String word) {
		int n = word.length();
		int out = 0;
		for (int i = 1; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				for (int k = j+1; k < n; k++) {
					String a1 = word.substring(0, i);
					String b = word.substring(i, j);
					String a2 = word.substring(j, k);
					String c = word.substring(k, n);
					
					
					if (a1.equals(a2) && !a1.equals(b) && !a1.equals(c) && !b.equals(c)) {
						//System.out.println(a1 + " " + b + " " + a2 + " " + c);
						out++;
					}
				}
			}
		}
		//System.out.print(word + " ");
		return out;
	}
	
	public static int sumOfLove(String word) {
		int n = word.length();
		int out = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				out += love(word.substring(i, j));
			}
		}
		return out;
	}
}