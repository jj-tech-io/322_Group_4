package ubc.cosc322;

import java.util.ArrayList;

public class Node {
    public Node parentNode;
    public ArrayList<Node> children = new ArrayList<>();
    public GameBoard current;
    public int [] scoreUsVThem;
    public GameBoard copy;
    private int us;
    private int them;
    private boolean y;
    public TreeMap<Integer,int [][]> map= new TreeMap<>();
    public ArrayList<ArrayList<ArrayList<Integer>>> moves;
    public ArrayList<ArrayList<ArrayList<Integer>>> arrows;
    public Node(GameBoard current, int us, int them) {
        this.current = current;
        this.copy = current;
        this.us = us;
        this.them = them;
    }
    public ArrayList<ArrayList<Integer>> getChildren() {
        ArrayList<ArrayList<Integer>> m = this.copy.getMoves(us,this);
        //get moves
        //for each move -> get arrows
        //for each arrow get board state and score
        //return best move
        ArrayList<Integer> arrow = new ArrayList<>();
        arrow = copy.getArrow(this,m.get(0));

        System.out.println(m);
        //ArrayList<int [][]> states
//        GameBoard child = copy.updateBoard('b',m.get(0).get(1),m.get(0).get(0),arrow);
        return m;
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
