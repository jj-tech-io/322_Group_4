package ubc.cosc322;
import java.util.ArrayList;
import java.util.Arrays;

public class GameState {
    private final int[] action;
    private GameBoard gameBoard = new GameBoard();
    private int value = 0;
    private int miniMaxValue = 0;
    private final GameState parent;
    private final ArrayList<GameState> children = new ArrayList<>();
    private boolean isExpanded = false;
    private int visits = 0;
    private int depth = 0;


    public GameState(int[] action, GameState parent, int depth) {
        this.action = Arrays.copyOf(action, action.length);
        this.depth = depth;
        this.parent = parent;
        this.gameBoard = GameBoard.copyOf(parent.getGameBoard());
        this.gameBoard.movePiece(action);
    }

    public GameState(int[] action, GameState parent) {
        this.action = Arrays.copyOf(action, action.length);
        this.parent = parent;
        this.gameBoard = GameBoard.copyOf(parent.getGameBoard());
        this.gameBoard.movePiece(action);
    }

    public GameState(GameBoard gameBoard) {
        this.action = null;
        this.gameBoard = GameBoard.copyOf(gameBoard);
        this.parent = null;
    }

    /** @return action to get this GameState from parent GameState. */
    public int[] getAction() { return Arrays.copyOf(action, action.length); }

    /**@return gameBoard configuration that GameState is, by value. */
    public GameBoard getGameBoard() { return GameBoard.copyOf(gameBoard); }

    /** @return Set depth of node in GameState tree */
    public int getDepth() { return depth; }

    /** @return parent of current GameState */
    public GameState getParent() {
        return parent;
    }

    /** @return value of current GameState */
    public int getValue() { return value; }

    /** @return value of current GameState */
    public int getMiniMaxValue() { return miniMaxValue; }

    /** @return number of times this GameState has been visited by parent */
    public int getVisits() { return visits; }

    /** Calculates and returns the UCB value of this node.
     * @return UCB value of this node. */
    public double getUCB(double C) {
        if (this.parent == null)
            return 0;

        double ucb;
        if (this.getVisits() == 0)
            ucb = Integer.MAX_VALUE;
        else
            ucb = Math.abs(this.getValue())/(double)this.getVisits() + (C * Math.sqrt((Math.log(this.parent.getVisits())) / this.getVisits()));

        if (this.getDepth() % 2 == 0)
            ucb = -ucb;
        return ucb;
    }

    /**
     * @return True if GameState has been expanded
     */
    public boolean isExpanded() {
        return this.isExpanded;
    }

    public void setMiniMaxValue(int val) { this.miniMaxValue = val; }

    /** @param addedValue  value to add to current value of GameState  */
    public void incrValue(int addedValue) {
        this.value = this.value + addedValue;
    }

    /** @param addedVisits How many visits to add to amount of visits */
    public void incrVisits(int addedVisits) { this.visits = this.visits + addedVisits; }


    /** Returns list of this GameState's child states
     * @param team number of team for which you want all moves(child nodes)
     * @return  ArrayList of all GameStates that can be created from this GameState in one move
     */
    public ArrayList<GameState> getChildren(int team) {
        if (!isExpanded) {
            System.out.println("Generating child nodes");
            for (int[] action : gameBoard.getAllPossibleMoves(team))
                this.children.add(new GameState(action, this, this.getDepth() + 1));
            isExpanded = true;
            System.out.println("Child nodes generated.");
        }
        System.out.println("Children generated. Array size: " + children.size());
        return this.children;
    }
}
