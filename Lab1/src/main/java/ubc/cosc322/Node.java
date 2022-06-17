package ubc.cosc322;

import java.util.*;

public class Node {
    public Map<Integer, List<Object>> scoreMovesState = new HashMap<>();
    public List<List<Object>> children = new ArrayList<>();
    public List<GameBoard> childStates;
    public List<Node> childNodes = new ArrayList<>();

    @Override
    public String toString() {
        return "Node[{]" +
                "score: " +score + "\n"+
                ", board state: \n" +this.current.boardString();
    }
    public Map.Entry<Integer, List<Object>> getMaxKey() {
        // using iterators
        Iterator<Map.Entry<Integer, List<Object>>> itr = scoreMovesState.entrySet().iterator();
        int max = -10000;
        Map.Entry<Integer, List<Object>> maxScoreNode = null;
        while(itr.hasNext())
        {
            Map.Entry<Integer, List<Object>> entry = itr.next();
            if(entry.getKey()>max) {
                max = entry.getKey();
                maxScoreNode = entry;
            }
//            System.out.println("Key = " + entry.getKey() +
//                    ", Value = " + entry.getValue());
        }
        return maxScoreNode;
    }
    protected GameBoard current;
    public int score;
    public GameBoard copy;
//    private int us;
//    private int them;
//    private boolean y;
//    public TreeMap<Integer,int [][]> map= new TreeMap<>();
//    public ArrayList<ArrayList<ArrayList<Integer>>> moves;
//    public ArrayList<ArrayList<ArrayList<Integer>>> arrows;
    public Node(int player,GameBoard current) {
        int other = 1;
        if(player==1) {
            other = 2;
        }
        int us = (int) current.getScore(player).get(1);
        int them =(int) current.getScore(other).get(1);

        this.score = us - them;
        this.current = current;
        this.copy = current;

    }




    public void addChild(Node c, ArrayList<Integer> qc, ArrayList<Integer> qn, ArrayList<Integer> ar ) {
        boolean addedChild = false;
        if(c != null) {
            this.childNodes.add(c);
            List<Object> scoremovestate = new ArrayList<>();

            scoremovestate.add(qc); //0
            scoremovestate.add(qn); //1
            scoremovestate.add(ar); //2
            scoremovestate.add(c);
            this.scoreMovesState.put(c.score,scoremovestate);


        }

    }
    public List<List<Object>>  getChildren() {
        return this.children;

    }
//    public String printChildren() {
//        String out = "-----print children -------\n";
//        int index = 0;
//        for (Node c: this.childNodes) {
//            out += c.toString();
//            index++;
//        }
//        out+=index;
//        return out;
//    }

    public GameBoard getCurrent() {
        return current;
    }

    public int getScore() {
        return score;
    }

    public GameBoard getCopy() {
        return copy;
    }



}
