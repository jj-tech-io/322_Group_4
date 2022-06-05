package ubc.cosc322;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;



class GameBoardTest {
    public static void main(String [] args) {
        GameBoard gb = new GameBoard();
        gb.printBoard();
        System.out.println(gb.ONE_DIM);
        System.out.println(gb.white_xy.get(0));
        System.out.println(gb.black_xy.get(1));

        System.out.println("Move" +gb.makeMove('b', 1, 1, 1));
        gb.printBoard();
        System.out.println(gb.black_xy.get(1));
        System.out.println(gb.black_xy.get(2));
        System.out.println("3" +gb.black_xy.get(4));
        System.out.println("2d" +gb.TWO_DIM);
        System.out.println(Arrays.toString(gb.oneDim));

    }

    public void exampleGetBoard_Move() {
        /*
        GameBoard gb = new GameBoard((ArrayList<Integer>) msgDetails.get("game-state"));
        gb.printBoard();
        gb.makeMove('b', 1, 1, 1); // move black 1 up one right
         */
        //
        GameBoard gb = new GameBoard();


    }

}