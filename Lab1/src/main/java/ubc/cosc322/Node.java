package ubc.cosc322;

import java.util.ArrayList;

public class Node {
    public Node parentNode;
    public ArrayList<Node> children = new ArrayList<>();
    public GameBoard current;
    public int score;
    public GameBoard copy;
    private int us;
    private int them;
    private boolean y;
    public TreeMap<Integer,int [][]> map= new TreeMap<>();
    public ArrayList<ArrayList<ArrayList<Integer>>> moves;
    public ArrayList<ArrayList<ArrayList<Integer>>> arrows;
    public Node(GameBoard current, int player) {
        this.current = current;
        this.copy = current;
        this.score = (int) current.getScore(player).get(0);
    }


}
