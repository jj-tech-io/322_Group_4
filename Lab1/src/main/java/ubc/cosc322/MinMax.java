package ubc.cosc322;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ubc.cosc322.GFG.minimax;

public class MinMax {
    public List<Object> searchTree = new ArrayList<>();

    public static List<ArrayList<Integer>> getOptimal(int p, Node current, GameBoard g, boolean MIN, boolean MAX) {
        System.out.println(g.boardString());
        ArrayList<ArrayList<Integer>> optimal_qC_qN = new ArrayList<>();
//        GameBoard childBoard = g;
        List<Object> myMoves = g.getQueenMoves(p);
        System.out.println(myMoves);
        // using for-each loop for iteration over Map.entrySet()
        int index = 0;
        List<List<ArrayList<Integer>>> qCqN = new ArrayList<>();
        List<ArrayList<Integer>> qCqNPair = new ArrayList<>();
        ArrayList<ArrayList<Integer>> arrows = new ArrayList<>();
        List<Node> nextLevel = new ArrayList<>();

        for (int q = 0; q < myMoves.size(); q++) {
            Map<String, Object> qMap = (Map<String, Object>) myMoves.get(q);//get queen 0;
            int last = 0;
            int max = 0;
            ArrayList<Integer> qc = new ArrayList<>();
            for (Map.Entry<String, Object> entry : qMap.entrySet()) {

                ArrayList<Integer> qn = new ArrayList<>();
                ArrayList<Integer> ar = new ArrayList<>();

                String key = entry.getKey();

                if (key == "qC") {
                    qc = (ArrayList<Integer>) entry.getValue();
                    System.out.println("qc ----" + qc + "------------------------------------------");
                    System.out.println(entry);
                } else  {

                    Integer o = (Integer) entry.getValue();
                    ArrayList<Integer> move = new ArrayList<>();
                    qCqNPair.clear();
                    if(o == 0) {
                        continue;
                    }
                    else {
                    switch (entry.getKey()) {
                        case "up":
                            qn.add(qc.get(0) - o);
                            qn.add(qc.get(1));
                            arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
                            for (ArrayList<Integer> a : arrows) {
                                if (qn != qc && ar != qn && ar.size() == 2) {
                                    qCqNPair.add(qc);
                                    qCqNPair.add(qn);
                                    qCqNPair.add(ar);
                                    qCqN.add(qCqNPair);
                                    Node c = new Node(p, g, qCqNPair);
                                    current.addChild(c);
                                    System.out.println(key + "pair--------\n" + qc + " " + qn + " " + ar);
                                    return qCqNPair;

                                }
                            }
                            break;
                        case "down":
                            qn.add(qc.get(0) + o);
                            qn.add(qc.get(1));
                            arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
//                                ar = (ArrayList<Integer>) g.getArrowMoves(p,qn).get(0);
                            for (ArrayList<Integer> a : arrows) {
                                if (qn != qc && ar != qn && ar.size() == 2) {
                                    qCqNPair.add(qc);
                                    qCqNPair.add(qn);
                                    qCqNPair.add(ar);
                                    qCqN.add(qCqNPair);
                                    Node c = new Node(p, g, qCqNPair);
                                    current.addChild(c);
                                    System.out.println(key + "pair--------\n" + qc + " " + qn + " " + ar);
                                    return qCqNPair;
                                }
                            }
                            break;
                        case "left":
                            qn.add(qc.get(0));
                            qn.add(qc.get(1) - o);
                            arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
//                                ar = (ArrayList<Integer>) g.getArrowMoves(p,qn).get(0);
                            for (ArrayList<Integer> a : arrows) {
                                ar = a;
                                if (qn != qc && ar != qn && ar.size() == 2) {
                                    qCqNPair.add(qc);
                                    qCqNPair.add(qn);
                                    qCqNPair.add(ar);
                                    qCqN.add(qCqNPair);
                                    Node c = new Node(p, g, qCqNPair);
                                    current.addChild(c);
                                    System.out.println(key + "pair----\n" + qc + " " + qn + " " + ar);
                                    return qCqNPair;
                                }
                            }
                            break;
                        case "right":
                            qn.add(qc.get(0));
                            qn.add(qc.get(1) + o);
                            arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
//                                ar = (ArrayList<Integer>) g.getArrowMoves(p,qn).get(0);
                            for (ArrayList<Integer> a : arrows) {
                                ar = a;
                                if (qn != qc && ar != qn && ar.size() == 2) {
                                    qCqNPair.add(qc);
                                    qCqNPair.add(qn);
                                    qCqNPair.add(ar);
                                    qCqN.add(qCqNPair);
                                    Node c = new Node(p, g, qCqNPair);
                                    current.addChild(c);
                                    System.out.println(key + "pair--------\n" + qc + " " + qn + " " + ar);
                                    return qCqNPair;
                                }
                            }
                            break;
                        case "up/right": //right
                            qn.add(qc.get(0) - o);
                            qn.add(qc.get(1) + o);

                            arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
                            for (ArrayList<Integer> a : arrows) {
                                ar = a;
                                if (qn != qc && ar != qn && !ar.isEmpty()) {
                                    qCqNPair.add(qc);
                                    qCqNPair.add(qn);
                                    qCqNPair.add(ar);
                                    qCqN.add(qCqNPair);
                                    Node c = new Node(p, g, qCqNPair);
                                    current.addChild(c);
                                    System.out.println(key + "pair--------\n" + qc + " " + qn + " " + ar);
                                    return qCqNPair;
                                }
                            }
                            break;
                        case "down/right": // rightUp
                            qn.add(qc.get(0) + o);
                            qn.add(qc.get(1) + o);
                            arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
                            for (ArrayList<Integer> a : arrows) {
                                ar = a;
                                if (qn != qc && ar != qn && !ar.isEmpty()) {
                                    qCqNPair.add(qc);
                                    qCqNPair.add(qn);
                                    qCqNPair.add(ar);
                                    qCqN.add(qCqNPair);
                                    Node c = new Node(p, g, qCqNPair);
                                    current.addChild(c);
                                    System.out.println(key + "pair--------\n" + qc + " " + qn + " " + ar);
                                    return qCqNPair;
                                }
                            }
                            break;
                        case "up/left": //
                            qn.add(qc.get(0) - 1);
                            qn.add(qc.get(1) - 1);
                            //ar = g.getArrowN(qn);
                            arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
                            for (ArrayList<Integer> a : arrows) {
                                ar = a;
                                if (qn != qc && ar != qn && !ar.isEmpty()) {
                                    qCqNPair.add(qc);
                                    qCqNPair.add(qn);
                                    qCqNPair.add(ar);
                                    qCqN.add(qCqNPair);
                                    Node c = new Node(p, g, qCqNPair);
                                    current.addChild(c);
                                    System.out.println(key + "pair--------\n" + qc + " " + qn + " " + ar);
                                    return qCqNPair;

                                } else {
                                    continue;
                                }
                            }
                            break;
                        case "down/left":
                            qn.add(qc.get(0) + o);
                            qn.add(qc.get(1) - o);
                            //ar = g.getArrowN(qn);
                            arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
                            for (ArrayList<Integer> a : arrows) {
                                ar = a;
                                if (qn != qc && ar != qn && ar.size() == 2) {
                                    qCqNPair.add(qc);
                                    qCqNPair.add(qn);
                                    qCqNPair.add(ar);

                                    qCqN.add(qCqNPair);
                                    System.out.println(index);
                                    Node c = new Node(p, g, qCqNPair);
                                    current.addChild(c);
                                    System.out.println(key + "pair--------\n" + qc + " " + qn + " " + ar);
                                    return qCqNPair;
                                }
                            }
                    }}
                }


            }
        }
        System.out.println(current.childNodes);
            List<ArrayList<Integer>> r = new ArrayList<>();
            List<Boolean> v;
            boolean val = false;
            index = qCqN.size();
            while (!val) {
                r = qCqN.get(index);
                System.out.println(r.get(0) + " " + r.get(1) + " " + r.get(2));
                v = g.validateMove(r.get(0), r.get(1), r.get(2));

                if (v.get(0) && v.get(1) && v.get(2) && index <10 ) {
                    val = true;
                }
                index--;
            }
            System.out.println(r + "----r");
            return r;


        }

    public static Node getNextLevel(int p, Node current, GameBoard g, boolean MIN, boolean MAX) throws CloneNotSupportedException {
        System.out.println(g.boardString());
        ArrayList<ArrayList<Integer>> optimal_qC_qN = new ArrayList<>();
        List<Object> myMoves = g.getQueenMoves(p);
        System.out.println(myMoves);
        // using for-each loop for iteration over Map.entrySet()
        int index = 0;

        List<List<ArrayList<Integer>>> qCqN = new ArrayList<>();
        List<ArrayList<Integer>> qCqNPair = new ArrayList<>();
        ArrayList<ArrayList<Integer>> arrows = new ArrayList<>();
        List<Node> nextLevel = new ArrayList<>();

        for (int q = 0; q < myMoves.size(); q++) {
            Map<String, Object> qMap = (Map<String, Object>) myMoves.get(q);//get queen 0;
            int last = 0;
            int max = 0;
            ArrayList<Integer> qc = new ArrayList<>();
            for (Map.Entry<String, Object> entry : qMap.entrySet()) {
                ArrayList<Integer> qn = new ArrayList<>();
                ArrayList<Integer> ar = new ArrayList<>();
                String key = entry.getKey();
                if (key == "qC") {
                    try {
                        qc = (ArrayList<Integer>) entry.getValue();
                    }
                    catch (TypeNotPresentException e) {
                        System.out.println("type qC err");
                    }
                } else  {
                    Integer o = (Integer) entry.getValue();
                    ArrayList<Integer> move = new ArrayList<>();
                    if(o == 0) {
                        continue;
                    }
                    else {
                        switch (entry.getKey()) {
                            case "up":
                                qn.add(qc.get(0) - o);
                                qn.add(qc.get(1));
                                arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
                                for (ArrayList<Integer> a : arrows) {
                                    if (qn != qc && a != qn && !a.isEmpty()) {
                                        //System.out.println(key + "--before\n" + qCqNPair);
                                        qCqNPair.clear();
                                        qCqNPair.add(qc);
                                        qCqNPair.add(qn);
                                        qCqNPair.add(a);
                                        qCqN.add(qCqNPair);
                                        GameBoard newGB = (GameBoard) g.clone();
                                        Node c = new Node(p, newGB, qCqNPair);
                                        current.addChild(c);
                                        nextLevel.add(c);
                                        //System.out.println(key + "after--\n" + qCqNPair);

                                    }
                                }
                                continue;
                            case "down":
                                qn.add(qc.get(0) + o);
                                qn.add(qc.get(1));
                                arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
                                qCqNPair.clear();
                                for (ArrayList<Integer> a : arrows) {
                                    if (qn != qc && a != qn && !a.isEmpty()) {
                                        //System.out.println(key + "--before\n" + qCqNPair);
                                        qCqNPair.clear();
                                        qCqNPair.add(qc);
                                        qCqNPair.add(qn);
                                        qCqNPair.add(a);
                                        qCqN.add(qCqNPair);
                                        GameBoard newGB = (GameBoard) g.clone();
                                        Node c = new Node(p, newGB, qCqNPair);
                                        current.addChild(c);
                                        nextLevel.add(c);
                                        //System.out.println(key + "after--\n" + qCqNPair);

                                    }
                                }
                                continue;
                            case "left":
                                qn.add(qc.get(0));
                                qn.add(qc.get(1) - o);
                                arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
//                                ar = (ArrayList<Integer>) g.getArrowMoves(p,qn).get(0);
                                qCqNPair.clear();
                                for (ArrayList<Integer> a : arrows) {
                                    if (qn != qc && a != qn && a.size() == 2) {
                                        //System.out.println(key + "--before\n" + qCqNPair);
                                        qCqNPair.clear();
                                        qCqNPair.add(qc);
                                        qCqNPair.add(qn);
                                        qCqNPair.add(a);
                                        qCqN.add(qCqNPair);
                                        GameBoard newGB = (GameBoard) g.clone();
                                        Node c = new Node(p, newGB, qCqNPair);
                                        current.addChild(c);
                                        nextLevel.add(c);
                                        //System.out.println(key + "after--\n" + qCqNPair);

                                    }
                                }
                                continue;
                            case "right":
                                qn.add(qc.get(0));
                                qn.add(qc.get(1) + o);
                                arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
//                                ar = (ArrayList<Integer>) g.getArrowMoves(p,qn).get(0);

                                for (ArrayList<Integer> a : arrows) {
                                    if (qn != qc && a != qn && a.size() == 2) {
                                        //System.out.println(key + "--before\n" + qCqNPair);
                                        qCqNPair.clear();
                                        qCqNPair.add(qc);
                                        qCqNPair.add(qn);
                                        qCqNPair.add(a);
                                        qCqN.add(qCqNPair);
                                        GameBoard newGB = (GameBoard) g.clone();
                                        Node c = new Node(p, newGB, qCqNPair);
                                        current.addChild(c);
                                        nextLevel.add(c);
                                        //System.out.println(key + "after--\n" + qCqNPair);

                                    }
                                }
                                continue;
                            case "up/right": //right
                                qn.add(qc.get(0) - o);
                                qn.add(qc.get(1) + o);

                                arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
                                for (ArrayList<Integer> a : arrows) {
                                    if (qn != qc && a != qn && a.size() == 2) {
                                        //System.out.println(key + "--before\n" + qCqNPair);
                                        qCqNPair.clear();
                                        qCqNPair.add(qc);
                                        qCqNPair.add(qn);
                                        qCqNPair.add(a);
                                        qCqN.add(qCqNPair);
                                        GameBoard newGB = (GameBoard) g.clone();
                                        Node c = new Node(p, newGB, qCqNPair);
                                        current.addChild(c);
                                        nextLevel.add(c);
                                        //System.out.println(key + "after--\n" + qCqNPair);

                                    }
                                }
                                continue;
                            case "down/right": // rightUp
                                qn.add(qc.get(0) + o);
                                qn.add(qc.get(1) + o);
                                arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
                                for (ArrayList<Integer> a : arrows) {
                                    if (qn != qc && a != qn && a.size() == 2) {
                                        //System.out.println(key + "--before\n" + qCqNPair);
                                        qCqNPair.clear();
                                        qCqNPair.add(qc);
                                        qCqNPair.add(qn);
                                        qCqNPair.add(a);
                                        qCqN.add(qCqNPair);
                                        GameBoard newGB = (GameBoard) g.clone();
                                        Node c = new Node(p, newGB, qCqNPair);
                                        current.addChild(c);
                                        nextLevel.add(c);
                                        //System.out.println(key + "after--\n" + qCqNPair);

                                    }
                                }
                                continue;
                            case "up/left": //
                                qn.add(qc.get(0) - 1);
                                qn.add(qc.get(1) - 1);
                                //ar = g.getArrowN(qn);
                                arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
                                for (ArrayList<Integer> a : arrows) {
                                    if (qn != qc && a != qn && a.size() == 2) {
                                        //System.out.println(key + "--before\n" + qCqNPair);
                                        qCqNPair.clear();
                                        qCqNPair.add(qc);
                                        qCqNPair.add(qn);
                                        qCqNPair.add(a);
                                        qCqN.add(qCqNPair);
                                        GameBoard newGB = (GameBoard) g.clone();
                                        Node c = new Node(p, newGB, qCqNPair);
                                        current.addChild(c);
                                        nextLevel.add(c);
                                        //System.out.println(key + "after--\n" + qCqNPair);

                                    }
                                }
                                continue;
                            case "down/left":
                                qn.add(qc.get(0) + o);
                                qn.add(qc.get(1) - o);
                                //ar = g.getArrowN(qn);
                                arrows = (ArrayList<ArrayList<Integer>>) g.getArrowN(qn);
                                for (ArrayList<Integer> a : arrows) {
                                    ar = a;
                                    if (qn != qc && ar != qn && ar.size() == 2) {
                                        qCqNPair.clear();
                                        qCqNPair.add(qc);
                                        qCqNPair.add(qn);
                                        qCqNPair.add(a);
                                        qCqN.add(qCqNPair);
                                        GameBoard newGB = (GameBoard) g.clone();
                                        Node c = new Node(p, newGB, qCqNPair);
                                        current.addChild(c);
                                        //System.out.println(c.toString());

                                    }
                                }
                        }}
                }


            }
        }
//        int nodeMax = -100;
//        int nodeMin = 100;
//        Node candidate = null;
//        if(MAX) {
//            for(Node newN : nextLevel) {
//                if(newN.nodeScore >nodeMax) {
//                    nodeMax = newN.nodeScore;
//                    candidate = newN;
//                    System.out.println(nodeMax);
//                }
//
//            }
//        }
//        else {
//            for(Node newN : nextLevel) {
//                if(newN.nodeScore < nodeMin) {
//                    nodeMin = newN.nodeScore;
//                    candidate = newN;
//                    System.out.println(nodeMin);
//                }
//
//            }
//
//        }

        //System.out.println(current.childNodes);
//        List<ArrayList<Integer>> r = new ArrayList<>();
//        List<Boolean> v;
//        boolean val = false;
//        index = qCqN.size();
//        while (!val) {
//            r = qCqN.get(index);
//            System.out.println(r.get(0) + " " + r.get(1) + " " + r.get(2));
//            v = g.validateMove(r.get(0), r.get(1), r.get(2));
//
//            if (v.get(0) && v.get(1) && v.get(2) && index <10 ) {
//                val = true;
//            }
//            index--;
//        }
//        System.out.println(r + "----r");
        int values[] = new int[current.childNodes.size()];
        int idx = 0;
        for(Node i : current.childNodes) {
            values[idx] = i.nodeScore;
            index++;

        }

        System.out.println("The optimal value is : " +
                NodeSearchTree.minimax(2, 0, false, values, 100, -100));
        return current.childNodes.get(minimax(2, 0, false, values, 100, -100));
    }

    public static List<List<Node>> getSearchTree(int depth, Node current, int p) throws CloneNotSupportedException {
        int values[] = new int[current.childNodes.size()];
        int index = 0;
        List<List<Node>> searchTree = new ArrayList<>();
        List<Node> curDepth = new ArrayList<>();
        boolean min = true;
        boolean max = false;
        for(Node i : current.childNodes) {
            curDepth.clear();
            for(int d = 0; d < depth; d++) {
                curDepth.add(getNextLevel(p,i, (GameBoard) current.current.clone(),!min, !max));
            }
            values[index] = i.nodeScore;
            index++;
            searchTree.add(curDepth);
        }
        return searchTree;

    }


    }




//        List<Object> playerScoreMoves = g.getScore(p);
//
//        ArrayList<ArrayList<Integer>> movesPlayer = (ArrayList<ArrayList<Integer>>) playerScoreMoves.get(2);
//        ArrayList<Integer> qL = new ArrayList<>();
//        ArrayList<Integer> qC = new ArrayList<>();
//        ArrayList<Integer> qN = new ArrayList<>();
//        ArrayList<Integer> aR = new ArrayList<>();
//        List<List<Object>>  children = new ArrayList<>();
//
//        ArrayList<ArrayList<Integer>> queenList = g.queenList(p);
//        System.out.println("queens list:\n "+queenList);
//        qL.add(-2);
//        qL.add(-2);
//        List<Boolean> oneTwoThree = new ArrayList<>();
//
//        for (ArrayList<Integer> queen : queenList) {
//            System.out.println("qC "+queen);
//            try {
//
//                int x = queen.get(0);
//                int y = queen.get(1);
//                if(g.getMyBOARD()[x][y] != p ) {
//                    System.out.println("qC out of bounds");
//                    continue;
//                }
//                qC = queen;
//
//            }
//            catch (IndexOutOfBoundsException e) {
//                System.out.println("qN out of bounds");
//                System.out.println(e );
//            }
//
//            try {
////                qN = g.getQueenN(qC);
////                System.out.println("qN "+qN);
////                int xn = qN.get(0);
////                int yn = qN.get(1);
////
////                if(g.getMyBOARD()[xn][yn] != 0 ) {
////                    System.out.println("qN out of bounds");
////                    continue;
////                }
////                else {
////
////                }
////
//
//            }
//            catch (IndexOutOfBoundsException e) {
//                System.out.println("qN out of bounds");
//                System.out.println(e);
//                continue;
//            }
//
//            try {
//                aR = g.getArrowN(qN);
//                int arx = aR.get(0);
//                int ary = aR.get(1);
//                if(g.getMyBOARD()[arx][ary] != 0 || g.getMyBOARD()[arx][ary] == -1 || g.getMyBOARD()[arx][ary] == 2 || g.getMyBOARD()[arx][ary] == 1 ||aR == qN) {
//                    System.out.println("aR out of bounds");
//                    continue;
//                }
//                List<Boolean> v = g.validateMove(qC,qN,aR);
//                System.out.println(v);
//                if(v.get(0) & v.get(1) &v.get(2)) {
//                    System.out.println("valid node generated");
//                    optimal_qC_qN.add(qC);
//                    optimal_qC_qN.add(qN);
//                    optimal_qC_qN.add(aR);
//
//                    //System.out.println("child board:" );
//                    //System.out.println(childBoard.boardString());
//                    childBoard.updateBoard(false, p, queen, qN, aR);
//                    Node child = new Node(p, g);
//                    current.addChild(child, qC, qN, aR);
//                    System.out.println(optimal_qC_qN);
//                    System.out.println(oneTwoThree);
//                    System.out.println(qC+" "+qN+" "+aR);
//                    System.out.println("breakkkkkk-----");
//                    break;
//
//                }
//
//            }
//            catch (IndexOutOfBoundsException e) {
//                System.out.println(aR);
//                System.out.println("aR out of bounds "+aR);
//                System.out.println(e);
//                continue;
//            }
//
//
//
//            oneTwoThree = g.validateMove(qC, qN, aR);
//            System.out.println(oneTwoThree);
//            if (oneTwoThree.get(0) && oneTwoThree.get(1) && oneTwoThree.get(2)) {
//                System.out.println("valid node generated");
//                //System.out.println("child board:" );
//                //System.out.println(childBoard.boardString());
//                childBoard.updateBoard(false, p, queen, qN, aR);
//                Node child = new Node(p, g);
//                current.addChild(child, qC, qN, aR);
//                System.out.println(oneTwoThree);
//
//            } else {
//                //System.out.println(":(node failed");
//                //System.out.println(oneTwoThree);
//            }
//                qL.add(qN.get(0));
//                qL.add(qN.get(1));
//        Map.Entry<Integer, List<Object>>  entry = current.getMaxKey();
//        if(entry != null) {
//            System.out.println("entry valid");
//            List<Object> move = entry.getValue();
//            System.out.println(move.get(0));
//
//
//            optimal_qC_qN.add(qC);
//            optimal_qC_qN.add(qN);
//            optimal_qC_qN.add(aR);
//            System.out.println(optimal_qC_qN.get(0));
//            System.out.println(optimal_qC_qN.get(1));
//            System.out.println(optimal_qC_qN.get(2));
//
//
//        }
//        for (List<Object> childData: current.getChildren()) {
//            int s = childData.get(0).getScore();
//            System.out.println("score = " + s + " "+childData.toString());
//        }
//        ArrayList<ArrayList<Integer>> movesOpponent = (ArrayList<ArrayList<Integer>>) opScoreMoves.get(2);
//        int MIN = 30;
//        int MAX = 0;
//        int queenDir = 2;
//
//        int MIN2 = 30;
//        int MAX2 = 0;
//        int queenDir2 = 2;
//
//
//        for(int i = 0; i < movesPlayer.size(); i++) {
//            ArrayList<Integer> thisQueen = movesPlayer.get(i);
//            System.out.println("min max this queen");
//            System.out.println(thisQueen);
//            System.out.println(thisQueen.get(thisQueen.size()-1)+ " this q s");
//            if(thisQueen.get(thisQueen.size()-1) < MIN) {
//                MIN = thisQueen.get(thisQueen.size()-1);
//                MAX = 0;
//                for (int j = 2; j < thisQueen.size()-1; j++) {
//
//
//                    if (j>1 && thisQueen.get(j) > MAX ) {
//                        qC.clear();
//                        qC.add(thisQueen.get(0));
//                        qC.add(thisQueen.get(1));
//                        MAX = thisQueen.get(j);
//                        queenDir = j;
//                        System.out.println(" j = "+ j + " MAX = " + MAX);
//                        if(MAX > 4) {
//                            if(MAX % 2 == 1) {
//                                MAX = (MAX +1) /2;
//
//                            }
//                            else {
//                                MAX = MAX / 2;
//                            }
//
//                        }
//                    }
//                }
//            }
//
//        }
//        System.out.println("MIN MAX"+ MIN + MAX);
//
//
//
//
//        System.out.println("queen current:"+qC);
//        switch (queenDir) {
//            case 2: //up
//                //System.out.println("up"+queenDir+" "+MAX);
//                qN.add(qC.get(0)-1);
//                qN.add(qC.get(1));
//                break;
//            case 3: //down
//                //System.out.println("down: "+queenDir+" "+MAX+" "+qC);
//                qN.add(qC.get(0)+1);
//                qN.add(qC.get(1));
//                break;
//            case 4: //left
//                //System.out.println("left"+queenDir+" "+MAX);
//                qN.add(qC.get(0));
//                qN.add(qC.get(1)-1);
//                break;
//            case 5: //right
//                //System.out.println("right"+queenDir+" "+MAX);
//                qN.add(qC.get(0));
//                qN.add(qC.get(1)+1);
//                break;
//            case 6: // rightUp
//                //System.out.println("rightUp"+queenDir+" "+MAX);
//                qN.add(qC.get(0)-1);
//                qN.add(qC.get(1)+1);
//                break;
//            case 7: // leftUp
//                System.out.println("leftUp"+queenDir+" "+MAX);
//                qN.add(qC.get(0)-1);
//                qN.add(qC.get(1)-1);
//                break;
//            case 8:// rightDown
//                //System.out.println("rightDown"+queenDir+" "+MAX);
//                qN.add(qC.get(0)+1);
//                qN.add(qC.get(1)+1);
//                break;
//            case 9:// leftDown
//                System.out.println("leftDown");
//                qN.add(qC.get(0)+1);
//                qN.add(qC.get(1)-1);
//                break;
//
//        }
//        ArrayList<ArrayList<Integer>> optimal_qC_qN = new ArrayList<>();
//        ArrayList<Integer> temp = new ArrayList<>();
//        temp.add(qC.get(0));
//        temp.add(qC.get(1));
//        optimal_qC_qN.add(temp);
//
//        aR = g.getArrowN(g,qN);
//        if(aR.size() <2) {
//            System.out.println();
//        }
//
//
//        optimal_qC_qN.add(qN);
//        optimal_qC_qN.add(aR);
//        System.out.println("optimal q,q,a " +optimal_qC_qN);
