package ubc.cosc322;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;



class GameBoardTest {
    public static void main(String [] args) {
        GameBoard gb = new GameBoard();
        gb.printBoard();
        ArrayList<Integer> qN = new ArrayList<>();
        ArrayList<Integer> aR = new ArrayList<>();
        ArrayList<Integer> qC = new ArrayList<>();
        qC.add(10);
        qC.add(4);
        qN.add(9);
        qN.add(4);
        aR.add(8);
        aR.add(6);
        gb.getXY(qC);
        System.out.println(gb.getXY(qC));

        gb.updateBoard('b',qC,qN,aR);
        qC.clear();
        qN.clear();
        aR.clear();
        qC.add(1);
        qC.add(4);
        qN.add(2);
        qN.add(4);
        aR.add(qC.get(0));
        aR.add(qC.get(1));
        gb.updateBoard('w',qC,qN,aR);
    }
}