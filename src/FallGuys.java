import java.util.*;

public class FallGuys {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		
		Game game = new Game(n);
		
		for (int i = 0; i < n; i++) {
			String line = sc.nextLine();
			for (int j = 0; j < n; j++) {
				if (line.charAt(j) == 'Y') {
					game.grid[i][j] = true;
				}
			}
		}
		game.solve();
		System.out.println(game.moves);
		//game.printMe();
	}

}

class Game {
	boolean[][] grid;
	Pos pos;
	int n;
	String moves;
	
	Game (int n) {
		grid = new boolean[n][n];
		pos = new Pos();
		this.n = n;
		moves = "";
	}
	
	void printMe() {
		for (boolean[] row : grid) {
			for (boolean b : row) {
				if (b) {
					System.out.print("Y");
				} else {					
					System.out.print("B");
				}
			}
			System.out.println();
		}
	}
	
	void move (int di, int dj) {
		if (di == -1 && dj == 0) {
			moves += "U";
		} else if (di == 1 && dj == 0) {
			moves += "D";
		} else if (di == 0 && dj == -1) {
			moves += "L";
		} else {
			moves += "R";
		}
		pos.i += di;
		pos.j += dj;
		
		if (0 <= pos.i && 0 <= pos.j && n > pos.i && n > pos.j) {
			grid[pos.i][pos.j] = !grid[pos.i][pos.j];
		}
	}
	
	void solve() {
		move(-1, 0);
		for (int i = n-1; i >= 0; i--) {
			for (int j = n-1; j >= 0; j--) {
				if (!grid[i][j]) {
					// move to target location
					if (pos.i > i) {		
						while(pos.i != i) {
							move(-1, 0);
						}
					} else {
						while(pos.i != i) {
							move(1, 0);
						}
					}
					while(pos.j != j) {
						move(0, 1);
					}
					
					while (pos.i != 0 && !grid[i-1][0]) {
						move(-1, 0);
					}
					
					// move back out
					while (pos.j != -1) {
						move(0, -1);
					}

				}
			}
		}
	}
}

class Pos {
	int i;
	int j;
}