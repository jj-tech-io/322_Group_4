package ubc.cosc322;

import java.util.ArrayList;

public class Node {
    public Node parentNode;
    public ArrayList<Node> children = new ArrayList<>();
    public GameBoard current;
    public GameBoard copy;
    private int us;
    private int them;
    private boolean y;

    public ArrayList<ArrayList<ArrayList<Integer>>> moves;
    public ArrayList<ArrayList<ArrayList<Integer>>> arrows;
    public Node(GameBoard current, int us, int them) {
        this.current = current;
        this.copy = current;
        this.us = us;
        this.them = them;
    }
//    public ArrayList<Integer> getMove() {
//        moves =  current.getMoves(cu);
//
//    }
    public int getUtilityScore() {
        int x = 0;
        return x;
    }





}
