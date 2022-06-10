package ubc.cosc322;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;



class GameBoardTest {
    public static void main(String [] args) {
        ArrayList<Integer> from = new ArrayList<>();
        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
        GameBoard gb = new GameBoard();
        Node newNode = new Node(gb, 1,2);
//        ArrayList<ArrayList<Integer>> moves = gb.getMoves(1,newNode);
//
//        System.out.println(moves.size());
//        gb.printBoard();
//        newNode = new Node(gb, 2,1);
//        moves= gb.getMoves(1,newNode);
//        System.out.println(moves);
//        gb.printBoard();
//        ArrayList<Integer> qN = new ArrayList<>();
//        ArrayList<Integer> aR = new ArrayList<>();
//        ArrayList<Integer> qC = new ArrayList<>();
//        qC.add(10);
//        qC.add(4);
//        qN.add(9);
//        qN.add(4);
//        aR.add(1);
//        aR.add(9);
//        System.out.println("====");
//        System.out.println(qC);
//        ArrayList<Integer> trans = gb.getXY(qC);
//        System.out.println(trans);
//        ArrayList<Integer> xy = gb.undoXY(trans);
//        System.out.println(xy);
//        System.out.println("====");
//
//        System.out.println("====");
//        System.out.println(aR);
//        trans = gb.getXY(aR);
//        System.out.println(trans);
//        xy = gb.undoXY(trans);
//        System.out.println(xy);
//        System.out.println("====");
//        gb.updateBoard('b',qC,qN,aR);
//        qC.clear();
//        qN.clear();
//        aR.clear();
//        qC.add(1);
//        qC.add(4);
//        qN.add(2);
//        qN.add(4);
//        aR.add(qC.get(0));
//        aR.add(qC.get(1));
//        gb.updateBoard('w',qC,qN,aR);
    }
}