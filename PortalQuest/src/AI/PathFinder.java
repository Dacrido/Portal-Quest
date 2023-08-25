package AI;

import java.util.ArrayList;

import Main.GamePanel;

public class PathFinder { // A* search algorithm

	GamePanel gp;
	Node[][] node;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode;
	public Node goalNode;
	Node currentNode;
	boolean goalReached = false;
	int step = 0;
	public boolean onlyXLeft = false;
	public boolean onlyYLeft = false;
	
	
	public PathFinder(GamePanel gp) {
		
		this.gp = gp;	
		instantiateNode();
		
	}
	
	public void instantiateNode() {
		
		node = new Node[gp.maxWorldCol][gp.maxWorldRow];
		
		int col = 0;
		int row = 0;
		
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			node[col][row] = new Node(col, row); // Node for every tile on the map
			
			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}			
			
		}		
	}
	
	public void resetNodes() {
		
		int col = 0;
		int row = 0;
		
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
	
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
						
			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}		
		
		}
		// Reset other settings
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
		onlyXLeft = false;
		onlyYLeft = false;
		
	}
	
	
	public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
		
		resetNodes();
		
		// Set Start and Goal Node
		startNode = node[startCol][startRow];
		currentNode = startNode;
		goalNode = node[goalCol][goalRow];
		openList.add(currentNode);
		 
		int col = 0;
		int row = 0;
		
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			// Set Solid Nodes
			int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
			if (gp.tileM.tile[tileNum].collision == true || gp.tileM.tile[tileNum].partialCollision == true) { // partial collision as I don't want to deal with cactus tiles rn
				node[col][row].solid = true;				
			}
			
			// Set Cost
			getCost(node[col][row]);
			
			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
			
		}
		
		
	}
	
	public void getCost(Node node) {
		
		// G cost
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;
		
		
		// H cost
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;
		
		if (Math.abs(node.col - startNode.col) == 0 && Math.abs(node.col - goalNode.col) == 0) {
			onlyYLeft = true;
		}
		if (Math.abs(node.row - startNode.row) == 0 && Math.abs(node.row - goalNode.row) == 0) {
			onlyXLeft = true;
		}
		
		
		// F cost
		
		node.fCost = node.gCost + node.hCost;
		
		
		
	}
	
	public boolean search() {
		
		while(goalReached == false && step < 500) {
			
			int col = currentNode.col;
			int row = currentNode.row;
			
			// Check current node
			currentNode.checked = true;
			openList.remove(currentNode);
			
			// Open the Up Node
			if (row - 1 >= 0) {
				openNode(node[col][row-1]);
			}
			// Open the Left Node
			if (col - 1 >= 0) {
				openNode(node[col-1][row]);
			}
			//Open the Down Node
			if (row + 1 < gp.maxWorldRow) {
				openNode(node[col][row+1]);
			}			
			// Open the Right Node
			if (col + 1 < gp.maxWorldCol) {
				openNode(node[col+1][row]);
			}
			
			
			// Find best Node
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			
			for (int i = 0; i < openList.size(); i++) {
				
				// Compare F Costs of nodes
				if (openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;	
				}
				// If F cost is equal, check G cost
				else if (openList.get(i).fCost == bestNodefCost) {
					
					if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
					
				}
				
			}
			
			// If no node in open list, end loop
			if (openList.size() == 0) {
				break;
			}
			
			// After loop, openList[bestNodeIndex] is the next currentNode
			currentNode = openList.get(bestNodeIndex);
			
			if (currentNode == goalNode) {
				goalReached = true;
				trackThePath();
			}
			
			step++;
			
			
		}
		
		return goalReached;
		
		
	}
	
	public void openNode(Node node) {
		
		
		if (node.open == false && node.checked == false && node.solid == false) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);			
		}
		
	}
	
	public void trackThePath() {
		
		Node current = goalNode; 
		
		while(current != startNode) { // backtracking from goal to start
			
			pathList.add(0, current); // newest added node is in first slot
			current = current.parent;
			
		}
		
		
	}
	
	
	
	
	
}
