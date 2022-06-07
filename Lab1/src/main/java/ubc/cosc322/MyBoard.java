package ubc.cosc322;

//same as game board just trying to simplify this class
import java.util.*;

import static java.lang.String.format;

public  class MyBoard {
    public static  int[][] boardCurrent = new int[11][11];
    public  int[][] boardNext = new int[11][11];
    public static ArrayList<Integer>  ONE_DIM = new ArrayList<>();
    public static   ArrayList<ArrayList<Integer>>  TWO_DIM = new ArrayList<>();
    public static   int[] oneDim = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0};
    ;
    private static char[] letters = new char[]{'\t', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};


    public  Map<Integer,ArrayList<Integer>> white_xy= new HashMap<>();
    public  Map<Integer,ArrayList<Integer>> black_xy = new HashMap<>();



    public MyBoard() {


    }
    public void printBoard(ArrayList<Integer> cur) {


        System.out.println("   ------------------2 black 2---------------------------");
        int index = 0;

        for (int i = 0; i < 11; i++) {

            for (int j = 0; j < 11; j++) {
                if (i == 0)
                    System.out.print(cur.get(j) + " | ");
                else if (j == 0) {
                    System.out.print(format("%s:\t |", 11 - i).toString());
                } else {
                    System.out.print(" " + cur.get(index) + " |");
                }
                index++;
            }

            System.out.println(" ");
        }
        System.out.println( "   ----------------1 white 1---------------------------");
        System.out.println("\t | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |");
    }
    public ArrayList<Integer> updateBoard(ArrayList<Integer> current,char c, ArrayList<Integer> qC,ArrayList<Integer> qN,ArrayList<Integer> aR) {
        ArrayList<Integer> next = current;
        printBoard(current);
        int indexFrom =getIndex(qC);
        int indexTo =getIndex(qN);
        next.set(indexFrom,0);
        if(c=='b')
        next.set(indexTo,2);
        else
        next.set(indexTo,1);
        System.out.println(indexFrom+" "+indexTo);
        printBoard(next);
        return current;
    }
    public ArrayList<Integer> makeMove(char c, ArrayList<Integer> qC,ArrayList<Integer> qN,ArrayList<Integer> aR,ArrayList<Integer> G) {
        ONE_DIM = G;

        ArrayList<Integer> move = new ArrayList<>();
        if(c=='b') {

            int indexFrom =getIndex(qC);
            int indexTo =getIndex(qN);
            System.out.println(indexFrom+" "+indexTo);
            boardCurrent[qC.get(0)-1][qC.get(1)-1] = 0;
            boardCurrent[qN.get(0)-1][qN.get(1)-1] = 2;
            oneDim[indexFrom] = 0;
            oneDim[indexTo] = 2;
            ONE_DIM.set(indexFrom,0);
            ONE_DIM.set(indexTo,2);



        }
        else {
            int indexFrom =getIndex(qC);
            int indexTo =getIndex(qN);
            System.out.println(indexFrom+" "+indexTo);
            boardCurrent[qC.get(0)-1][qC.get(1)-1] = 0;
            boardCurrent[qN.get(0)-1][qN.get(1)-1] = 1;
            oneDim[indexFrom] = 0;
            oneDim[indexTo] = 1;
            ONE_DIM.set(indexFrom,0);
            ONE_DIM.set(indexTo,1);

        }


        return ONE_DIM;
    }
    public int getIndex(ArrayList<Integer> XY) {
        int row = XY.get(0);
        int col = XY.get(1);
        int index = (10 - row)*10 +col;
        System.out.println("index "+index+", row " +row+", col " +col);
        return index;
    }



}