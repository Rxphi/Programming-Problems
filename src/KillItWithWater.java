import java.util.*;

public class KillItWithWater {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int i = 0; i < t; i++) {
			System.out.println(testCase(sc));
		}
	}
	
	public static int testCase(Scanner sc) {
		int out = 0;
		
		int n = sc.nextInt();
		sc.nextLine();
		
		ArrayList<TreeNode> allNodes = new ArrayList<TreeNode>();
		
		for (int i = 0; i < n; i++) {
			allNodes.add(new TreeNode(i+1));
		}
		
		// create the tree
		TreeNode root = allNodes.get(0);
		
		String[] parents = sc.nextLine().split(" "); // nextLine
		for (int i = 2; i <= n; i++) {
			int pi = Integer.parseInt(parents[i-2]);
			allNodes.get(i-1).parent = allNodes.get(pi-1);
			allNodes.get(pi-1).children.add(allNodes.get(i-1));
		}
		String[] cost = sc.nextLine().split(" "); // nextLine
		for (int i = 0; i < n; i++) {
			allNodes.get(i).cost = Integer.parseInt(cost[i]);
		}
		//
		
		// calculates savecostpotential and subtreecost for all nodes
		root.calc();
		
		
		root.burning = true;
		
		// frontier of nodes that are not yet burning but will in the next step
		Set<TreeNode> frontier = new HashSet<TreeNode>();
		frontier.addAll(root.children);
		
		// first minimize the radius in which the fire spreads
		while (frontier.size() != 0) {
			// get the node with the heighest savecostpotential and save it
			float maxpotential = -1;
			TreeNode save = null;
			for (TreeNode node : frontier) {
				if (node.savecostpotential > maxpotential && node.children.size() != 0) {
					maxpotential = node.savecostpotential;
					save = node;
				}
			}
			
			if (save != null) {
				//System.out.println("Saved node " + save.i);
				out += save.cost;
				save.safe = true;
				frontier.remove(save);
			}
			
			// let the unsaved nodes catch fire and add their children to the frontier
			Set<TreeNode> newFrontier = new HashSet<TreeNode>();
			for (TreeNode node : frontier) {
				node.burning = true;
				for (TreeNode c : node.children) {
					if (!c.burning && !c.safe) {
						newFrontier.add(c);
					}
				}
			}
			frontier = newFrontier;
		}
		
		// then extinguish all nodes that are still burning
		for (TreeNode node : allNodes) {
			if (node.burning) {
				out += node.cost;
				node.burning = false;
				node.safe = true;
			}
		}
		return out;
	}
	
}

class TreeNode {
	int i;
	TreeNode parent;
	ArrayList<TreeNode> children;
	int cost;
	int subtreecost;
	float savecostpotential;
	boolean burning;
	boolean safe;
	
	TreeNode(int i) {
		this.i = i;
		children = new ArrayList<TreeNode>();
	}
	
	// calculates subtreecost(inclusive) and savecostpotential
	void calc() {
		subtreecost = cost;
		int max = 0;
		for (TreeNode c : children) {
			c.calc();
			int ccost = c.subtreecost;
			if (ccost > max) {
				max = ccost;
			}
			subtreecost += ccost;
		}
		
		savecostpotential = ((float) cost) / ((float) subtreecost);
	}
	
}