package ubc.cosc322;

import java.security.Key;
import java.util.*;

import static java.lang.Math.floor;


class GameBoardTest {
    public static void main(String [] args) {
        ArrayList<Integer> from = new ArrayList<>();
        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
        GameBoard gb = new GameBoard();
        gb.setMyBOARD(1,6,0,7,1);
        gb.setMyBOARD(1,7,1,9,0);
        Node current = new Node(gb,1);
        gb.printBoard();
        ArrayList<Integer> qC = new ArrayList<>();
        ArrayList<Integer> qN = new ArrayList<>();
        ArrayList<Integer> aR = new ArrayList<>();
        List<Object> gameInfo1 = gb.getScore(1);
        int score = (int) gb.getScore(1).get(0);
        System.out.println(gameInfo1.get(1));
        List<Object> queens_score_moves = (List<Object>) gameInfo1.get(1);
        int index = 0;
        int max = -1;
        int minQS = 100;
        int minQS_index = 0;
        for (int i = 0; i < 4; i++) {
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

        switch (index) {

                case 0: //up
                    System.out.println("up"+index+" "+max);
                    qN.add(qC.get(0)+ max);
                    qN.add(qC.get(1));
                    break;
                case 1: //down
                    System.out.println("down: "+index+" "+max);
                    qN.add(qC.get(0)-max);
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

        gb.updateQueen('w',qC,qN);
        System.out.println("qc: "+qC+" qN " +qN);
        gb.printBoard();




        List<Object> gameInfo1b = gb.getScore(1);
        int scoreArrow = (int) gb.getScore(1).get(0);
        System.out.println(gameInfo1.get(1));
        List<Object> queens_score_moves_arrow = (List<Object>) gameInfo1b.get(1);
        index = 0;
        max = -1;
        minQS = 100;
        minQS_index = 0;
        for (int i = 0; i < 4; i++) {
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

        switch (index) {

            case 0: //up
                System.out.println("up"+index+" "+max);
                aR.add(qC.get(0)+ max);
                aR.add(qC.get(1));
                break;
            case 1: //down
                System.out.println("down: "+index+" "+max);
                aR.add(qC.get(0)-max);
                aR.add(qC.get(1));
                break;
            case 2: //left
                System.out.println("left"+index+" "+max);
                aR.add(qC.get(0));
                aR.add(qC.get(1)-max);
                System.out.println(qN+ ""+ index+" "+max);
                break;
            case 3: //right
                System.out.println("right"+index+" "+max);
                aR.add(qC.get(0));
                aR.add(qC.get(1)+max);
                break;
            case 4: // rightUp
                System.out.println("rightUp"+index+" "+max);
                aR.add(qC.get(0)-(int)floor(max/2));
                aR.add(qC.get(1)+(int)floor(max/2));
                break;
            case 5: // leftUp
                System.out.println("leftUp"+index+" "+max);
                aR.add(qC.get(0)-(int)floor(max/2));
                aR.add(qC.get(1)-(int)floor(max/2));
                break;
            case 6:// rightDown
                System.out.println("rightDown"+index+" "+max);
                aR.add(qC.get(0)+1);
                aR.add(qC.get(1)+1);
                break;
            case 7:// leftDown
                System.out.println("leftDown");
                aR.add(qC.get(0)+1);
                aR.add(qC.get(1)-1);
                break;

        }

        gb.updateArrow('w',aR);
        System.out.println("qc: "+qC+" qN " +qN+" "+aR);
        gb.printBoard();

        //MinMax.getOptimal(1,2,true,current);



    }
}