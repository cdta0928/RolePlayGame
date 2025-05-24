package ai;

public class PathFinder {
    main.GamePanel gp;

    Node[][] node;
    java.util.ArrayList<Node> openList = new java.util.ArrayList<>();
    public java.util.ArrayList<Node> pathList = new java.util.ArrayList<>();

    Node startNode, goalNode, currNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(main.GamePanel gp) {
        this.gp = gp;
        instantiateNodes(); // init node
    }

    public void instantiateNodes() {
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldCol) {
            node[col][row] = new Node(col, row);

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
            // Reset open, checked and solid state
            node[col][row].open = false;
            node[col][row].solid = false;
            node[col][row].checked = false;

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
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow, entity.Entity entity) {
        resetNodes();

        // Set start & goal node
        startNode = node[startCol][startRow];
        currNode = startNode;
        goalNode = node[goalCol][goalRow];

        openList.add(currNode);

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            // SET SOLID NODE
            // CHECK TILE
            int tileNum = gp.tileManager.mapTileNum[gp.currentMap][col][row];
            if (gp.tileManager.tile[tileNum].collision == true) {
                node[col][row].solid = true;
            }
            // CHECK INTERACTIVE TILE
            for (int i = 0; i < gp.iTile[gp.currentMap].length; i++) {
                if (gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].destructible == true) {
                    int itCol = gp.iTile[gp.currentMap][i].worldX/gp.tileSize;
                    int itRow = gp.iTile[gp.currentMap][i].worldY/gp.tileSize;
                    node[itCol][itRow].solid = true;
                }
            }
            // SET COST
            getCost(node[col][row]);

            col++;
            if (col == gp.maxWorldCol) {
                row++;
                col = 0;
            }
        }
    }

    public void getCost(Node node) {
        // G Cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // H Cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        // F Cost
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (goalReached == false && step < 500) {
            int col = currNode.col;
            int row = currNode.row;

            // Check currNode
            currNode.checked = true;
            openList.remove(currNode);

            if (row - 1 >= 0) { openNode(node[col][row - 1]); }
            if (col - 1 >= 0) { openNode(node[col - 1][row]); }
            if (row + 1 >= 0) { openNode(node[col][row + 1]); }
            if (col + 1 >= 0) { openNode(node[col + 1][row]); }

            // Find best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;
            for (int i = 0; i < openList.size(); i++) {
                // Check this node fCost better?
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }

                // If fCost equal -> gCost
                else if (openList.get(i).fCost == bestNodefCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }

            // If openList.size = 0  -> end loop
            if (openList.size() == 0) break;

            // After loop, openList[bestNodeIndex] is the next step (= currNode)
            currNode = openList.get(bestNodeIndex);
            if (currNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }

    public void openNode(Node node) {
        if (node.open == false && node.checked == false) {
            node.open = true;
            node.parent = currNode;
            openList.add(node);
        }
    }

    public void trackThePath() {
        Node curr = goalNode;
        while (curr != startNode) {
            pathList.add(0, curr);
            curr= curr.parent;
        }
    }
}
