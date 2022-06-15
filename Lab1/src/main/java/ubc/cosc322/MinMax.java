package ubc.cosc322;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MinMax {
    public List<Object> searchTree = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> getOptimal(int player,int opponent, boolean max, Node node) {


        GameBoard next = node.copy;
        GameBoard current = node.current;
        List<Object> playerScoreMoves = node.current.getScore(player);
        List<Object> opponentScoreMoves = node.current.getScore(opponent);
        ArrayList<ArrayList<Integer>> movesPlayer = (ArrayList<ArrayList<Integer>>) playerScoreMoves.get(5);
        ArrayList<ArrayList<Integer>> movesOpponent = (ArrayList<ArrayList<Integer>>) opponentScoreMoves.get(5);
        ArrayList<Integer> qC = new ArrayList<>();
        ArrayList<Integer> qN = new ArrayList<>();
        ArrayList<Integer> aR = new ArrayList<>();

        int MIN = 25;
        int MAX = 1;
        int queenMinIndex = 0;
        int queenDir = 2;
        int queenDof = 0;




        for(int i = 0; i < movesPlayer.size(); i++) {
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
        switch (queenDir) {
            case 2: //up
                System.out.println("up"+queenDir+" "+MAX);
                qN.add(qC.get(0)-1);
                qN.add(qC.get(1));
                break;
            case 3: //down
                System.out.println("down: "+queenDir+" "+MAX+" "+qC);
                qN.add(qC.get(0)+1);
                qN.add(qC.get(1));
                break;
            case 4: //left
                System.out.println("left"+queenDir+" "+MAX);
                qN.add(qC.get(0));
                qN.add(qC.get(1)-1);
                break;
            case 5: //right
                System.out.println("right"+queenDir+" "+MAX);
                qN.add(qC.get(0));
                qN.add(qC.get(1)+1);
                break;
            case 6: // rightUp
                System.out.println("rightUp"+queenDir+" "+MAX);
                qN.add(qC.get(0)-1);
                qN.add(qC.get(1)+1);
                break;
            case 7: // leftUp
                System.out.println("leftUp"+queenDir+" "+MAX);
                qN.add(qC.get(0)-1);
                qN.add(qC.get(1)-1);
                break;
            case 8:// rightDown
                System.out.println("rightDown"+queenDir+" "+MAX);
                qN.add(qC.get(0)+1);
                qN.add(qC.get(1)+1);
                break;
            case 9:// leftDown
                System.out.println("leftDown");
                qN.add(qC.get(0)+1);
                qN.add(qC.get(1)-1);
                break;

        }
        ArrayList<ArrayList<Integer>> optimal_qC_qN = new ArrayList<>();

        optimal_qC_qN.add(qC);
        optimal_qC_qN.add(qN);

        if(player==1) {
            //next.updateQueen('w',qC,qN);

            aR = next.getArrowN(next,qN);
            optimal_qC_qN.add(aR);
            next.updateArrow('w',aR);
            //GameBoard arrowMove = next.updateBoard('w',qC,qN,qN);

        }
        else{
            //next.updateQueen('b',qC,qN);

            aR = next.getArrowN(next,qN);
            optimal_qC_qN.add(aR);
            next.updateArrow('b',aR);
            //GameBoard arrowMove = next.updateBoard('w',qC,qN,qN);
            System.out.println("current: "+current.getScore(2));
        }

        return optimal_qC_qN;
    }


}