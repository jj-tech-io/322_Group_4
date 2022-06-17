package ubc.cosc322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MinMax {
    public List<Object> searchTree = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> getOptimal(int p, Node current, GameBoard g) {
        System.out.println(g.boardString());
        ArrayList<ArrayList<Integer>> optimal_qC_qN = new ArrayList<>();

        GameBoard childBoard = g;

//        List<Object> playerScoreMoves = g.getScore(p);
//
//        ArrayList<ArrayList<Integer>> movesPlayer = (ArrayList<ArrayList<Integer>>) playerScoreMoves.get(2);

        ArrayList<Integer> qL = new ArrayList<>();
        ArrayList<Integer> qC = new ArrayList<>();
        ArrayList<Integer> qN = new ArrayList<>();
        ArrayList<Integer> aR = new ArrayList<>();
        List<List<Object>>  children = new ArrayList<>();

        ArrayList<ArrayList<Integer>> queenList = g.queenList(p);
        System.out.println("queens list:\n "+queenList);
        qL.add(-2);
        qL.add(-2);
        List<Boolean> oneTwoThree = new ArrayList<>();

        for (ArrayList<Integer> queen : queenList) {
            System.out.println("qC "+queen);
            try {

                int x = queen.get(0);
                int y = queen.get(1);
                if(g.getMyBOARD()[x][y] != p ) {
                    continue;
                }
                qC = queen;

            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("qC out of bounds");
                System.out.println(e );
            }

            try {
                qN = g.getQueenN(qC);
                System.out.println("qN "+qN);
                int xn = qN.get(0);
                int yn = qN.get(1);

                if(g.getMyBOARD()[xn][yn] != 0 ) {
                    continue;
                }

            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("qN out of bounds");
                System.out.println(e);
                continue;
            }

            try {
                aR = g.getArrowN(qN);
                int arx = aR.get(0);
                int ary = aR.get(1);
                if(g.getMyBOARD()[arx][ary] != 0 || g.getMyBOARD()[arx][ary] == -1 || g.getMyBOARD()[arx][ary] == 2 || g.getMyBOARD()[arx][ary] == 1 ||aR == qN) {
                    System.out.println("bad arrow");
                    continue;
                }
                List<Boolean> v = g.validateMove(qC,qN,aR);
                if(v.get(0) & v.get(1) &v.get(2)) {
                    optimal_qC_qN.add(qC);
                    optimal_qC_qN.add(qN);
                    optimal_qC_qN.add(aR);
                    System.out.println("valid node generated");
                    //System.out.println("child board:" );
                    //System.out.println(childBoard.boardString());
                    childBoard.updateBoard(false, p, queen, qN, aR);
                    Node child = new Node(p, g);
                    current.addChild(child, qC, qN, aR);
                    System.out.println(optimal_qC_qN);
                    System.out.println(oneTwoThree);
                    System.out.println(qC+" "+qN+" "+aR);


                }

            }
            catch (IndexOutOfBoundsException e) {
                System.out.println(aR);
                System.out.println("aR out of bounds");
                System.out.println(e);
                continue;
            }



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
        }



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
        System.out.println(optimal_qC_qN.size());
        return optimal_qC_qN;
    }


}