package ubc.cosc322;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;



class GameBoardTest {
    public static void main(String [] args) {
        ArrayList<Integer> from = new ArrayList<>();
        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
        GameBoard gb = new GameBoard();
        gb.setMyBOARD(1,6,0,7,1);
        Node current = new Node(gb,1);

        MinMax.getOptimal(1,2,true,current);

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

    }
}