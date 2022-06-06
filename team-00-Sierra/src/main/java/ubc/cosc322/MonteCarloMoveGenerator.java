package ubc.cosc322;

import java.lang.Math;
import java.util.ArrayList;

public class MonteCarloMoveGenerator {

    // constant value for UCB
    private final double C = 0.25;
    private final int myTeam;
    private final int otherTeam;
    private int simulationsRan = 0;
    private int maxDepthReached = 0;
    private int numOfMovesRan = 0;
    private int estimatedNumberOfMovesLeft = 0;
    private int maxBranchingFactor = 0;
    private long totalSimTime = 0;

    public MonteCarloMoveGenerator(int myTeam) {
        this.myTeam = myTeam;
        if (this.myTeam == 1)
            otherTeam = 2;
        else
            otherTeam = 1;
    }


    /**
     * @param root  node to begin search
     * @return best move from GameState
     */
    public int[] monteCarloTreeSearch(GameState root) {
        simulationsRan = 0;
        maxDepthReached = 0;
        System.out.println("Monte Carlo Tree Search Begun");

        double startTime = (System.currentTimeMillis() / 1000.);
        double currentTime = (System.currentTimeMillis() / 1000.);

        long timeAllotted = 29;
        while ((currentTime - startTime) < timeAllotted) {
            GameState leaf = traverse(root);
            int simulation_result = simulate(leaf);
            backPropagate(leaf, simulation_result);
            currentTime = (System.currentTimeMillis() / 1000.);
        }

        System.out.println("Simulations ran:\t\t" + simulationsRan);
        System.out.println("Max branching factor:\t" + maxBranchingFactor);
        System.out.println("Max depth reached:\t\t" + maxDepthReached);
        System.out.printf("UCB of root's best child:\t%.4f\n", bestChild(root).getUCB(C));
        System.out.printf("n value of root's best child:\t%d\n", bestChild(root).getVisits());
        System.out.printf("Win rate of root's best child:\t%.4f\n", bestChild(root).getValue() / (double) bestChild(root).getVisits());
        System.out.printf("Avg number of moves per simulation:\t%.1f\n", numOfMovesRan / (double) simulationsRan);
        System.out.printf("Avg time taken per simulation:\t%f ms\n", (totalSimTime / (double) simulationsRan) / 1_000_000);
        return bestChild(root).getAction();
    }

    /**
     * Traverses through root to find unvisited child nodes. If all visited selects the child of best UCB value, becomes root.
     * Until unvisited found
     *
     * @param node The root GameState
     * @return The leaf to simulate
     */
    private GameState traverse(GameState node) {
        int nextTurn = node.getDepth() % 2 == 0 ? myTeam : otherTeam;
        if (node.isExpanded() && node.getChildren(nextTurn).size() > 0) {
            return traverse(bestChild(node));
        }


        if (node.getVisits() == 0)
            return node;
        else {
            // If visited show all moves and add to tree
            // return first child node to be simulated
            ArrayList<GameState> newChildren = node.getChildren(nextTurn);
            if (newChildren.size() > maxBranchingFactor)
                maxBranchingFactor = newChildren.size();
            if (newChildren.size() > 0)
                return newChildren.get(0);
            else
                return node;
        }
    }

    /**
     * Searches roots children
     * UCB to choose best child
     * Depth used to decide if it is the best
     *
     * @param root parent node.
     * @return child of parent node of largest UCB value, null for no kids
     */
    private GameState bestChild(GameState root) {
        GameState bestChild = null;

        // Our turn (maximize value)
        if (root.getDepth() % 2 == 0) {
            double highestValue = Double.MIN_VALUE;

            for (GameState child : root.getChildren(myTeam)) {
                double UCBvalue = child.getUCB(C);
                if (UCBvalue > highestValue) {
                    highestValue = UCBvalue;
                    bestChild = child;
                }
                if (child.getVisits() == 0)
                    return child;
            }

            return bestChild;
        }
        // enemy's turn
        else {
            double lowestValue = Double.MAX_VALUE;

            for (GameState child : root.getChildren(otherTeam)) {
                double UCBvalue = child.getUCB(C);
                if (UCBvalue < lowestValue) {
                    lowestValue = UCBvalue;
                    bestChild = child;
                }
                if (child.getVisits() == 0)
                    return child;
            }

            return bestChild;
        }
    }

    /**
     * Simulation of Monte-Carlo TS
     * Randomly makes moves for opposite teams my team if depth is even, odd for enemy
     *
     * @param node node to start simulation
     * @return 1 = winning is my team; -1 = winning is enemy
     */
    private int simulate(GameState node) {
        int result;
        simulationsRan++;
        if (node.getDepth() > maxDepthReached)
            maxDepthReached = node.getDepth();
        GameBoard GameBoardToSimulate = GameBoard.copyOf(node.getGameBoard());

        long startTime = System.nanoTime();
        if (node.getDepth() % 2 == 0)
            result = simulateHelper(GameBoardToSimulate, myTeam, otherTeam) == myTeam ? 1 : 0;
        else
            result = simulateHelper(GameBoardToSimulate, otherTeam, myTeam) == myTeam ? 1 : 0;

        totalSimTime += (System.nanoTime() - startTime);
        return result;
    }

    /**
     * @param simBoard         Starting board
     * @param currentTeam      Beginning team
     * @param currentOtherTeam Other team
     * @return Number for winning team
     */
    private int simulateHelper(GameBoard simBoard, int currentTeam, int currentOtherTeam) {
        numOfMovesRan++;
        // Find possible moves
        ArrayList<int[]> allPossibleMoves = simBoard.getAllPossibleMoves(currentTeam);

        // No possible moves, other team wins
        if (allPossibleMoves.size() == 0)
            return currentOtherTeam;

        // Moves left = random one
        int[] randomMove = allPossibleMoves.get((int) (Math.random() * allPossibleMoves.size()));
        simBoard.movePiece(randomMove);
        return simulateHelper(simBoard, currentOtherTeam, currentTeam);
    }

    /**
     * Loops node to root
     * Increases each node by terminal node, increases visits of each node visited
     *
     * @param node              Node for traversal, simulation begins here
     * @param simulation_result Simulation result after traversal is complete
     */
    private void backPropagate(GameState node, int simulation_result) {
        GameState currentNode = node;
        while (currentNode != null) {
            currentNode.incrValue(simulation_result);
            currentNode.incrVisits(1);
            currentNode = currentNode.getParent();
        }
    }

    /**
     * Returns estimate of moves left in game
     *
     * @param node node to estimate moves from
     * @return  returns moves left
     */
    protected double estimateMovesLeft(GameState node) {
        estimatedNumberOfMovesLeft = 0;
        GameBoard GameBoardToSimulate = GameBoard.copyOf(node.getGameBoard());
        double movesLeft = 0;
        for (int i = 0; i < 10; i++)
            movesLeft += estimateMovesLeftHelper(GameBoardToSimulate, myTeam, otherTeam);
        return movesLeft / 10;
    }

    /**
     * @param simBoard         Starting board
     * @param currentTeam      Starting team
     * @param currentOtherTeam Other team
     * @return Winning team
     */
    private int estimateMovesLeftHelper(GameBoard simBoard, int currentTeam, int currentOtherTeam) {
        // Find possible moves
        ArrayList<int[]> allPossibleMoves = simBoard.getAllPossibleMoves(currentTeam);

        // No possible moves other team wins
        if (allPossibleMoves.size() == 0)
            return estimatedNumberOfMovesLeft;// (base case)

        // Moves left = random one
        int[] randomMove = allPossibleMoves.get((int) (Math.random() * allPossibleMoves.size()));
        simBoard.movePiece(randomMove);

        estimatedNumberOfMovesLeft++;
        return estimateMovesLeftHelper(simBoard, currentOtherTeam, currentTeam);
    }
}
