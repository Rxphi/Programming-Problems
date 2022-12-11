import java.util.*;

public class DNAPrinter {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		sc.nextLine();
		String dna = sc.nextLine();
		
		int out = 0;
		
		for (int i = 0; i < n; i++) {
			char c = dna.charAt(i);
			switch(c) {
			case 'A':
				out += 45;
				break;
			case 'C':
				out += 29;
				break;
			case 'G':
				out += 33;
				break;
			case 'T':
				out += 26;
				break;
			}
		}
		System.out.println(out);
	}

}