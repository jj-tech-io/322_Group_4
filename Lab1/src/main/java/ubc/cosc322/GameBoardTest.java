package ubc.cosc322;

import java.security.Key;
import java.util.*;


class GameBoardTest {
    public static void main(String[] args) {
    }
    }
//        GameBoard g = new GameBoard();
//        ArrayList<Integer> qC = new ArrayList<>();
//        ArrayList<Integer> qN = new ArrayList<>();
//        ArrayList<Integer> aR = new ArrayList<>();
//        System.out.println(g.boardString());
//
//        qC.add(0);
//        qC.add(3);
//        qN.add(1);
//        qN.add(4);
//        aR.add(2);
//        aR.add(5);
//        g.getQueenMoves(2);
//        List<Boolean> vm = g.validateMove(qC, qN, aR);
//        System.out.println(vm);
//        g.updateBoard(false, 2, qC, qN, aR);
//        g.getScore(2);
//        System.out.println(g.boardString());
//        g.getQueenMoves(2);
//
//
//        System.out.println("-----queen list---");
//        System.out.println(g.getQueenMoves(2));
//
//
//
//        List<Object> myMoves = g.getQueenMoves(2);
//        // using for-each loop for iteration over Map.entrySet()
//        int index = 0;
//        List<List<ArrayList<Integer>>> qCqN = new ArrayList<>();
//
//        for(int q = 0; q<myMoves.size(); q++) {
//            Map<String, Object> qMap = (Map<String, Object>) myMoves.get(q);//get queen 0;
//            int last = 0;
//            int max = 0;
//            ArrayList<Integer> qc = new ArrayList<>();
//            for (Map.Entry<String, Object> entry : qMap.entrySet()) {
//                List<ArrayList<Integer>> qCqNPair = new ArrayList<>();
//                ArrayList<Integer> qn = new ArrayList<>();
//                ArrayList<Integer> ar = new ArrayList<>();
//
//                String key = entry.getKey();
//
//                if (key == "qC") {
//                    qc = (ArrayList<Integer>) entry.getValue();
//                    System.out.println("qc ----" + qc);
//                } else {
//                    Integer o = (Integer) entry.getValue();
//                    ArrayList<Integer> move = new ArrayList<>();
//
//                    if (last == 0 && o == max) {
//                        switch (entry.getKey()) {
//                            case "up":
//                                qn.add(qC.get(0) - o);
//                                qn.add(qC.get(1));
//                                ar = g.getArrowN(qn);
//                                if(qn != qc && ar != qn) {
//                                    qCqNPair.add(qc);
//                                    qCqNPair.add(qn);
//                                    qCqNPair.add(ar);
//                                    qCqN.add(qCqNPair);
//                                    System.out.println("pair--------\n" + qCqNPair);
//                                }
//                                break;
//                            case "down":
//                                qn.add(qC.get(0) + o);
//                                qn.add(qC.get(1));
//                                ar = g.getArrowN(qn);
//                                if(qn != qc && ar != qn) {
//                                    qCqNPair.add(qc);
//                                    qCqNPair.add(qn);
//                                    qCqNPair.add(ar);
//                                    qCqN.add(qCqNPair);
//                                    System.out.println("pair--------\n" + qCqNPair);
//                                }
//                                break;
//                            case "left":
//                                qn.add(qC.get(0));
//                                qn.add(qC.get(1) - o);
//                                ar = (ArrayList<Integer>) g.getArrowMoves(2,qn).get(0);
//                                if(qn != qc && ar != qn) {
//                                    qCqNPair.add(qc);
//                                    qCqNPair.add(qn);
//                                    qCqNPair.add(ar);
//                                    qCqN.add(qCqNPair);
//                                    System.out.println("pair--------\n" + qCqNPair);
//                                }
//                                break;
//                            case "right":
//                                qn.add(qC.get(0));
//                                qn.add(qC.get(1) + o);
//                                ar = (ArrayList<Integer>) g.getArrowMoves(2,qn).get(0);
//                                if(qn != qc && ar != qn) {
//                                    qCqNPair.add(qc);
//                                    qCqNPair.add(qn);
//                                    qCqNPair.add(ar);
//                                    qCqN.add(qCqNPair);
//                                    System.out.println("pair--------\n" + qCqNPair);
//                                }
//                                break;
//                            case "up/right": //right
//                                qn.add(qC.get(0) - o);
//                                qn.add(qC.get(1) + o);
//                                ar = g.getArrowN(qn);
//                                if(qn != qc && ar != qn) {
//                                    qCqNPair.add(qc);
//                                    qCqNPair.add(qn);
//                                    qCqNPair.add(ar);
//                                    qCqN.add(qCqNPair);
//                                    System.out.println("pair--------\n" + qCqNPair);
//                                }
//                                break;
//                            case "down/right": // rightUp
//                                qn.add(qC.get(0) + o);
//                                qn.add(qC.get(1) + o);
//                                ar = (ArrayList<Integer>) g.getArrowMoves(2,qn).get(0);
//                                if(qn != qc && ar != qn) {
//                                    qCqNPair.add(qc);
//                                    qCqNPair.add(qn);
//                                    qCqNPair.add(ar);
//                                    qCqN.add(qCqNPair);
//                                    System.out.println("pair--------\n" + qCqNPair);
//                                }
//                                break;
//                            case "up/left": //
//                                qn.add(qC.get(0) - 1);
//                                qn.add(qC.get(1) - 1);
//                                ar = (ArrayList<Integer>) g.getArrowMoves(2,qn).get(0);
//                                if(qn != qc && ar != qn) {
//                                    qCqNPair.add(qc);
//                                    qCqNPair.add(qn);
//                                    qCqNPair.add(ar);
//                                    qCqN.add(qCqNPair);
//                                    System.out.println("pair--------\n" + qCqNPair);
//                                }
//                                break;
//                            case "down/left":
//                                qn.add(qC.get(0) + o);
//                                qn.add(qC.get(1) - o);
//                                ar = g.getArrowN(qn);
//                                if(qn != qc && ar != qn) {
//                                    qCqNPair.add(qc);
//                                    qCqNPair.add(qn);
//                                    qCqNPair.add(ar);
//                                    qCqN.add(qCqNPair);
//                                    System.out.println("pair--------\n" + qCqNPair);
//                                }
//                                break;
//                        }
//                    }
//                    index++;
//                }
//            }
//        }
//        System.out.println(qCqN);
//        qCqN.get(1);
//
//    }
//}

//        ArrayList<Integer> from = new ArrayList<>();
//        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
//        GameBoard gb = new GameBoard();
//        gb.setMyBOARD(1,6,0,7,1);
//        Node current = new Node(gb,1);
//
//        MinMax.getOptimal(1,2,true,current);
//
//        gb.setMyBOARD(1,7,1,7,1);

//        gb.printBoard();
//        System.out.println(gb.getMoves(1,current));
//        System.out.println(gb.getScore(2));
//        System.out.println(gb.getScore(1));
//        gb.setMyBOARD(1,6,0,7,1);
//        gb.printBoard();
//        List<Object> score1 = gb.getScore(1);
//        List<Object> score2 = gb.getScore(2);
//        ArrayList<Integer> moves1 = (ArrayList<Integer>) score1.get(5);
//        System.out.println(moves1);
//
//        System.out.println(gb.getScore(2));
//        System.out.println(gb.getScore(1));
//
////        current.getChildren().get(0);
//        //current.

