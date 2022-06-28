package ubc.cosc322;

import java.util.*;

public class Node {
    public List<List<Object>> children = new ArrayList<>();
    public List<Node> childNodes = new ArrayList<>();
    public int nodeScore;
    public List<ArrayList<Integer>> moveFromParentNode;
    public static int numberOfNodes;
    public GameBoard current;
    public int score;
    public GameBoard copy;
//    private int us;
//    private int them;
//    private boolean y;
//    public TreeMap<Integer,int [][]> map= new TreeMap<>();
//    public ArrayList<ArrayList<ArrayList<Integer>>> moves;
//    public ArrayList<ArrayList<ArrayList<Integer>>> arrows;
    public Node(int player,GameBoard current, List<ArrayList<Integer>> moveFromParentNode) {
        numberOfNodes++;
        this.moveFromParentNode = moveFromParentNode;

        int other = 1;
        if(player==1) {
            other = 2;
        }
        int us =  current.nodeScore(player);
        int them = current.nodeScore(other);
        this.nodeScore = us - them;
        this.current = current;
        System.out.println("num nodes: "+ numberOfNodes+ " nodeScore: " + nodeScore);
    }
    public Node(int player,GameBoard current) {
        numberOfNodes++;
        int other = 1;
        if(player==1) {
            other = 2;

        }
        int us =  current.nodeScore(player);
        int them = current.nodeScore(other);

        this.nodeScore = us - them;
        this.current = current;
    }
    public boolean addChild(Node c ) {
        boolean addedChild = false;
        int size = this.childNodes.size();
        this.childNodes.add(c);
        if(this.childNodes.size() >size) {
            addedChild = true;
        }
        return addedChild;

    }
    public List<List<Object>>  getChildren() {
        return this.children;
    }
    @Override
    public String toString() {
        String nToStr ="node to string--------\n" +
                this.nodeScore + "\n"+
                this.moveFromParentNode +  "\n" +
                this.current.boardString() + "\n" +
                "------------";
        return nToStr;
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



//    public Map.Entry<Integer, List<Object>> getMaxKey() {
//        // using iterators
//        Iterator<Map.Entry<Integer, List<Object>>> itr = scoreMovesState.entrySet().iterator();
//        int max = -10000;
//        Map.Entry<Integer, List<Object>> maxScoreNode = null;
//        while(itr.hasNext())
//        {
//            Map.Entry<Integer, List<Object>> entry = itr.next();
//            if(entry.getKey()>max) {
//                max = entry.getKey();
//                maxScoreNode = entry;
//            }
////            System.out.println("Key = " + entry.getKey() +
////                    ", Value = " + entry.getValue());
//        }
//        return maxScoreNode;
//    }



}
