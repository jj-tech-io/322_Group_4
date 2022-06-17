package ubc.cosc322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinMax {
    public List<Object> searchTree = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> getOptimal(int player, int theirs, GameBoard g) {



        List<Object> playerScoreMoves = g.getScore(player);
        List<Object> opScoreMoves = g.getScore(theirs);
        ArrayList<ArrayList<Integer>> movesPlayer = (ArrayList<ArrayList<Integer>>) playerScoreMoves.get(5);

        ArrayList<ArrayList<Integer>> movesOpponent = (ArrayList<ArrayList<Integer>>) opScoreMoves.get(5);
        ArrayList<Integer> qC = new ArrayList<>();
        ArrayList<Integer> qN = new ArrayList<>();
        ArrayList<Integer> aR = new ArrayList<>();

        int MIN = 25;
        int MAX = 1;
        int queenMinIndex = 0;
        int queenDir = 2;
        int queenDof = 0;




        for(int i = 0; i < movesPlayer.size()-1; i++) {
            if (movesPlayer.get(i).get(10) < MIN) {
                queenMinIndex = i;
                MIN = movesPlayer.get(i).get(10);
                for (int j = 2; j < 10; j++) {
                    if (movesPlayer.get(i).get(j) > MAX) {
                        queenDir = j;
                        MAX = movesPlayer.get(i).get(j);

                    }
                }
            }
        }
        qC.add(movesPlayer.get(queenMinIndex).get(0));
        qC.add(movesPlayer.get(queenMinIndex).get(1));

        System.out.println("queen current:"+qC);
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
        ArrayList<ArrayList<Integer>> optimal_qC_qN = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(qC.get(0));
        temp.add(qC.get(1));
        optimal_qC_qN.add(temp);

        qN = COSC322Test.gB.getQueenN(g,qC);
        aR = g.getArrowN(g,qN);

        optimal_qC_qN.add(qN);
        optimal_qC_qN.add(aR);
//        if(player==1) {
//            //next.updateQueen('w',qC,qN);
//            aR.clear();
//            aR = next.getArrowN(next,qN);
//            if(aR.size()==2) {
//
//            }
//            else {
//                aR = COSC322Test.gB.getArrowN(next,qN);
//            }
//            optimal_qC_qN.add(aR);
//            next.updateArrow('w',aR);
//            //GameBoard arrowMove = next.updateBoard('w',qC,qN,qN);
//
//        }
//        else{
//            //next.updateQueen('b',qC,qN);
//            aR.clear();
//            aR = next.getArrowN(next,qN);
//            if(aR.size()==2) {
//
//            }
//            else {
//                aR = COSC322Test.gB.getArrowN(next,qN);
//            }
//            optimal_qC_qN.add(aR);
////            next.updateArrow('b',aR);
//            //GameBoard arrowMove = next.updateBoard('w',qC,qN,qN);
//        }
        System.out.println("optimal q,q,a " +optimal_qC_qN);
        return optimal_qC_qN;
    }


}