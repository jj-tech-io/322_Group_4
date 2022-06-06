package ubc.cosc322;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class MiniMaxMoveGenerator {
    private final static int END_REWARD = Integer.MAX_VALUE;
    private static int leavesReached = 0;
    private static int totalNodesVisited = 0;
    private final static int timeAllowed = 10;
    private static long startTime;
    private static int depthReached = 0;

    /** Returns best move
     */
    protected static int[] getMove(GameState gameState, int myTeam) {
        System.out.println("MiniMax Begun");
        startTime = System.currentTimeMillis();
        GameBoard GameBoard = gameState.getGameBoard();
        ArrayList<int[]> allOurMoves = GameBoard.getAllPossibleMoves(myTeam);
        int enemyTeam = (myTeam == 1 ? 2 : 1);
        GameState bestNode = new GameState(allOurMoves.get(0), gameState);
        int depthLimit = 3;
        for (int[] move : allOurMoves) {
            GameState node = new GameState(move, gameState);
            node.setMiniMaxValue(minValue(1, depthLimit, node.getGameBoard(), myTeam, enemyTeam, Integer.MIN_VALUE, Integer.MAX_VALUE));
            if (node.getMiniMaxValue() > bestNode.getMiniMaxValue())
                bestNode = node;
        }

        System.out.println("Terminal nodes reached: " + leavesReached);
        System.out.println("Nodes visited: " + totalNodesVisited);
        System.out.println("Best action difference: " + (bestNode.getMiniMaxValue() - getUtility(gameState.getGameBoard(), myTeam, enemyTeam)));
        System.out.println("Time used: " + (System.currentTimeMillis()/1000. - startTime/1000.));
        System.out.println("Depth reached: " + depthReached);
        return bestNode.getAction();
    }

    private static int maxValue(int depth, int depthLimit, GameBoard GameBoard, int myTeam, int enemyTeam, int a, int b) {
        totalNodesVisited++;
        depth++;
        if (depth > depthReached)
            depthReached = depth;

        ArrayList<int[]> allOurMoves = GameBoard.getAllPossibleMoves(myTeam);

        if (depth == depthLimit || stateIsTerminal(GameBoard)) {
            System.out.println("\nTerminal node reached at depth " + depth);
            System.out.println("\nUtility value: " + getUtility(GameBoard, myTeam, enemyTeam));
            System.out.println("GameBoard: \n" + GameBoard);
            try {
                Thread.sleep(500);
            }
            catch(Exception e) { System.out.println("Exception!!!!");}
            leavesReached++;
            return getUtility(GameBoard, myTeam, enemyTeam);
        }

        int v = Integer.MIN_VALUE;
        for (int[] move : allOurMoves) {
            if (System.currentTimeMillis()/1000. - startTime/1000. > timeAllowed)
                break;
            GameBoard tempGameBoard = GameBoard.copyOf(GameBoard);
            tempGameBoard.movePieceFast(move);
            v = Math.max(v, minValue(depth, depthLimit, tempGameBoard, myTeam, enemyTeam, a , b));
            if (v >= b)
                return v;
            a = Math.max(a, v);
        }
        return v;
    }

    private static int minValue(int depth, int depthLimit, GameBoard GameBoard, int myTeam, int enemyTeam, int a, int b) {
        totalNodesVisited++;
        depth++;
        if (depth < depthReached)
            depthReached = depth;
        ArrayList<int[]> allEnemyMoves = GameBoard.getAllPossibleMoves(enemyTeam);

        if (depth == depthLimit || stateIsTerminal(GameBoard)) {
            System.out.println("\nTerminal node reached at depth " + depth);
            System.out.println("GameBoard: \n" + GameBoard);
            try {
                Thread.sleep(500);
            }
            catch(Exception e) { System.out.println("Exception!!!!");}
            leavesReached++;
            return getUtility(GameBoard, myTeam, enemyTeam);
        }

        int v = Integer.MAX_VALUE;
        for (int[] move : allEnemyMoves) {
            GameBoard tempGameBoard = GameBoard.copyOf(GameBoard);
            tempGameBoard.movePieceFast(move);
            v = Math.min(v, maxValue(depth, depthLimit, tempGameBoard, myTeam, enemyTeam, a, b));
            if (v <= a)
                return v;
            b = Math.min(b, v);
            if (System.currentTimeMillis()/1000. - startTime/1000. > timeAllowed)
                break;
        }
        return v;
    }

    private static boolean stateIsTerminal(GameBoard GameBoard) {
        int blackMoves = GameBoard.getAllPossibleMoves(1).size();
        int whiteMoves = GameBoard.getAllPossibleMoves(2).size();
        return blackMoves == 0 || whiteMoves == 0;
    }

    private static int getUtility(GameBoard GameBoard, int myTeam, int enemyTeam) {
        double c1 = 0;
        double c2 = 1;
        double c3 = 0;
        if (GameBoard.getAllPossibleMoves(myTeam).size() == 0)
            return -END_REWARD;
        if (GameBoard.getAllPossibleMoves(enemyTeam).size() == 0)
            return END_REWARD;
        return (int)(c1*heuristic1(GameBoard, myTeam, enemyTeam)
                + c2*heuristic2(GameBoard, myTeam, enemyTeam)
                + c3*heuristic3(GameBoard, myTeam, enemyTeam));
    }

    /**
     * @param myTeam    my team
     * @param enemyTeam enemy team
     * @return sum of blocked spaces 1 tile from queens on enemy team - queens on my team.
     */
    private static int heuristic1(GameBoard GameBoard, int myTeam, int enemyTeam) {
        int obstaclesEnemyQueen = 0;
        int obstaclesMyQueens = 0;

        // blockages for enemy
        for (int[] queen : GameBoard.getQueenCoordinates(enemyTeam))
            for (int x = -1; x <= 1; x++)
                for (int y = -1; y <= 1; y++)
                    if (!(x == 0 && y == 0))
                        if (GameBoard.get(queen[0] + x, queen[1] + y) != 0)
                            obstaclesEnemyQueen++;

        // blockages for my team
        for (int[] queen : GameBoard.getQueenCoordinates(myTeam))
            for (int x = -1; x <= 1; x++)
                for (int y = -1; y <= 1; y++)
                    if (!(x == 0 && y == 0))
                        if (GameBoard.get(queen[0] + x, queen[1] + y) != 0)
                            obstaclesMyQueens++;

        return obstaclesEnemyQueen - obstaclesMyQueens;
    }

    /**
     * @param myTeam    my team
     * @return moves our team could make - enemy moves
     */
    private static int heuristic2(GameBoard GameBoard, int myTeam, int enemyTeam) {
        return GameBoard.getAllPossibleMoves(myTeam).size() - GameBoard.getAllPossibleMoves(enemyTeam).size();
    }

    /**
     * @param myTeam    my team
     * @param enemyTeam enemy team
     * @return number of tiles that my queens could reach - enemy's queens
     */
    public static int heuristic3(GameBoard GameBoard, int myTeam, int enemyTeam) {
        return heuristic3Helper(GameBoard, myTeam) - heuristic3Helper(GameBoard, enemyTeam	);
    }

    private static int heuristic3Helper(GameBoard GameBoard, int myTeam) {
        ArrayList<int[]> allQueenCoordinates = GameBoard.getQueenCoordinates(myTeam);
        int spaceAvailable = 0;

        for (int[] queenCoordinates : allQueenCoordinates) {
            new Coordinates(queenCoordinates);

            LinkedList<int[]> queuedTiles = new LinkedList<>();
            HashSet<Coordinates> visited = new HashSet<>();
            queuedTiles.add(queenCoordinates);

            // BFS
            while (!queuedTiles.isEmpty()) {

                // Remove current tile then add children to list of tiles to expand
                ArrayList<int[]> tilesToAdd = new ArrayList<>(GameBoard.nearTiles(queuedTiles.poll()));
                for (int[] tile : tilesToAdd)
                    // if empty = not visited
                    if (!visited.contains(new Coordinates(tile)) && GameBoard.get(tile) == 0) {
                        queuedTiles.add(tile);
                        // make tile visited
                        visited.add(new Coordinates(tile));
                        spaceAvailable++;
                    }
            }
        }
        return spaceAvailable;
    }
}
