package ubc.cosc322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.floor;

public class MinMax {
    public List<Object> searchTree = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> getOptimal(int player, int opponent, GameBoard gb) {
        ArrayList<ArrayList<Integer>> optimal = new ArrayList<>();
        //current node
        //get two lowest queen scores
        //get two bigest dof
        //4 child states
//        List<Object> player_label_moves;
//        player_label_moves = node.current.getMoves(player,node);

        List<Object> playerScoreMoves = gb.getScore(player);
        List<Object> opponentScoreMoves = gb.getScore(opponent);
        GameBoard copy = gb;
//        ArrayList<ArrayList<Integer>> movesPlayer = (ArrayList<ArrayList<Integer>>) playerScoreMoves.get(5);
//        ArrayList<ArrayList<Integer>> movesOpponent = (ArrayList<ArrayList<Integer>>) opponentScoreMoves.get(5);
        ArrayList<Integer> qC = new ArrayList<>();
        ArrayList<Integer> qN = new ArrayList<>();
        ArrayList<Integer> aR = new ArrayList<>();
        List<Object> gameInfo1 = gb.getScore(player);
        int score = (int) gb.getScore(player).get(0);
        System.out.println(gameInfo1.get(1));
        List<Object> queens_score_moves = (List<Object>) gameInfo1.get(1);
        int index = 0;
        int max = -1;
        int minQS = 100;
        int minQS_index = 0;
        for (int i = 0; i < 4; i++) {
            System.out.println(queens_score_moves.get(i));
            List<Object> row_data = (List<Object>) queens_score_moves.get(i);
            List<Object> moves = (List<Object>) row_data.get(1);
            List<Object> pos_qs = (List<Object>) row_data.get(0);
            List<Object> pos = pos_qs.subList(0,2);
            int qs = (int) pos_qs.get(2);
            if(qs<minQS) {
                minQS_index = i;
                minQS = qs;
                qC.clear();
                qC.add((Integer) pos.get(0));
                qC.add((Integer) pos.get(1));
                max = 0;
                for(int j = 0; j<moves.size(); j++) {
                    if((int) moves.get(j) > max) {
                        System.out.println(moves);
                        max = (int) moves.get(j);
                        index = j;
                        System.out.println(" max "+max+" index"+index);
                    }
                }
            }
            System.out.println("pos: "+pos+" qs: "+qs);
            System.out.println(queens_score_moves.get(i));
            System.out.println("index: "+index);

        }
        System.out.println(index);
//up,down,left,right,rightUp,leftUp,rightDown,leftDown

        if(max>3) {
            max = 2;
        }
        switch (index) {

            case 0: //up
                System.out.println("up"+index+" "+max);
                qN.add(qC.get(0)- max);
                qN.add(qC.get(1));
                break;
            case 1: //down
                System.out.println("down: "+index+" "+max);
                qN.add(qC.get(0)+max);
                qN.add(qC.get(1));
                break;
            case 2: //left
                System.out.println("left"+index+" "+max);
                qN.add(qC.get(0));
                qN.add(qC.get(1));
                System.out.println(qN+ ""+ index+" "+max);
                break;
            case 3: //right
                System.out.println("right"+index+" "+max);
                qN.add(qC.get(0));
                qN.add(qC.get(1)+max);
                break;
            case 4: // rightUp
                System.out.println("rightUp"+index+" "+max);
                qN.add(qC.get(0)-1);
                qN.add(qC.get(1)+1);
                break;
            case 5: // leftUp
                System.out.println("leftUp"+index+" "+max);
                qN.add(qC.get(0)-1);
                qN.add(qC.get(1)-1);
                break;
            case 6:// rightDown
                System.out.println("rightDown"+index+" "+max);
                qN.add(qC.get(0)+1);
                qN.add(qC.get(1)+1);
                break;
            case 7:// leftDown
                System.out.println("leftDown");
                qN.add(qC.get(0)+1);
                qN.add(qC.get(1)-1);
                break;

        }
        copy.updateQueen(player,qC,qN);
        optimal.add(qC);
        optimal.add(qN);
        //gb.updateQueen(player,qC,qN);
        System.out.println("qc: "+qC+" qN " +qN);
        //gb.printBoard();



        System.out.println("current: "+gb.getScore(player));



        //gb.updateQueen(player,qC,qN);

        //Node child = new Node(gb,player);

        aR = copy.getArrow(gb,qN);
        optimal.add(aR);
        copy.updateArrow(player,aR);
        copy.printBoard();
        //GameBoard arrowMove = next.updateBoard('w',qC,qN,qN);
        System.out.println("current: "+gb.getScore(1));



        System.out.println(qC+" "+qN+" "+aR);







        System.out.println("qc: "+qC+" qN " +qN+" "+aR);
        gb.printBoard();
        return optimal;
    }


}
