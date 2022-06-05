package ubc.cosc322;

import java.util.*;
import java.util.function.IntFunction;

import static java.lang.String.format;

public class GameBoard {
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
    public  ArrayList<Integer> QUEEN_CURR = new ArrayList<>();


    public GameBoard(ArrayList<Integer> b) {

        int index = 0;
        int wI = 0;
        int bI = 0;

        ArrayList<Integer> temp2 = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            temp2.clear();
            for (int j = 0; j < 11; j++) {
                boardCurrent[i][j] = b.get(index);
                ONE_DIM.add(b.get(index));
                temp2.add(b.get(index));
                if(b.get(index) == 1) {
                    ArrayList<Integer> t = new ArrayList<>();
                    t.add(i);
                    t.add(j);
                    white_xy.put(wI,t);
                    wI++;
                }
                else if(b.get(index) ==2) {
                    ArrayList<Integer> t = new ArrayList<>();
                    t.add(i);
                    t.add(j);
                    black_xy.put(bI,t);
                    bI++;
                }
                index++;

            }
            TWO_DIM.add(temp2);

        }

    }

    public GameBoard() {
        int index = 0;
        int wI = 0;
        int bI = 0;

        ArrayList<ArrayList<Integer>> board = new ArrayList<>();
        ArrayList<Integer> temp2 = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            temp2.clear();
            for (int j = 0; j < 11; j++) {
                boardCurrent[i][j] = oneDim[index];

                temp2.add(oneDim[index]);
                if(oneDim[index] == 1) {
                    ArrayList<Integer> t = new ArrayList<>();
                    t.add(i);
                    t.add(j);
                    white_xy.put(wI,t);
                    wI++;
                }
                else if(oneDim[index] ==2) {
                    ArrayList<Integer> t = new ArrayList<>();
                    t.add(i);
                    t.add(j);
                    black_xy.put(bI,t);
                    bI++;
                }
                index++;
            }
            TWO_DIM.add(temp2);
        }



        System.out.println("   -----------------------------------------------------------");
        for (int i = 0; i<11; i++) {

            for (int j = 0; j < 11; j++) {
                if(i ==0)
                    System.out.print(letters[j] +" | ");
                else if(j==0) {
                    System.out.print(format("%s:\t |",11-i).toString());
                }
                else {
                    System.out.print(" " +boardCurrent[i][j] +" |");
                }

            }

            System.out.println(" " );
        }
        System.out.println(board.toString());
    }
    public ArrayList<Integer> getAL(ArrayList<Integer> b) {

        int index = 0;
        int wI = 0;
        int bI = 0;

        ArrayList<Integer> temp2 = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            temp2.clear();
            for (int j = 0; j < 11; j++) {
                boardCurrent[i][j] = b.get(index);
                ONE_DIM.add(b.get(index));
                temp2.add(b.get(index));
                if(b.get(index) == 1) {
                    ArrayList<Integer> t = new ArrayList<>();
                    t.add(i);
                    t.add(j);
                    white_xy.put(wI,t);
                    wI++;
                }
                else if(b.get(index) ==2) {
                    ArrayList<Integer> t = new ArrayList<>();
                    t.add(i);
                    t.add(j);
                    black_xy.put(bI,t);
                    bI++;
                }
                index++;

            }

            TWO_DIM.add(temp2);


        }
        return ONE_DIM;
    }

    public ArrayList<Integer> getQueenCurr(char c) {
        int i = 1;
        this.QUEEN_CURR.clear();
        if(c == 'w') {
            i = 2;
        }

        for (int j:ONE_DIM) {
            if (i == j) {
                this.QUEEN_CURR.add(ONE_DIM.indexOf(i));
            }
        }
        return this.QUEEN_CURR;
    }

    public void printBoard() {
        StringJoiner boardString = new StringJoiner("\n ");

        System.out.println("   ------------------2 black 2---------------------------");


        for (int i = 0; i < 11; i++) {

            for (int j = 0; j < 11; j++) {
                if (i == 0)
                    System.out.print(letters[j] + " | ");
                else if (j == 0) {
                    System.out.print(format("%s:\t |", 11 - i).toString());
                } else {
                    System.out.print(" " + boardCurrent[i][j] + " |");
                }

            }

            System.out.println(" ");
        }
        System.out.println( "   ----------------1 white 1---------------------------");
        System.out.println("\t | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |");


    }
    public void printBoard(ArrayList<Integer> cur) {
        StringJoiner boardString = new StringJoiner("\n ");

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

    /*
       c -> color 'w' or 'b'
       index -> 0,1,2,3 (4 queens each)
       x -> col
       y ->  row
     */
    public ArrayList<Integer> makeMove(char c, int index, int x, int y) {

        ArrayList<Integer> move;
        if(c=='b') {
             move = black_xy.get(index);
        }
        else {
            move = white_xy.get(index);
        }


        move.set(0,move.get(0)+x);
        move.set(1,move.get(1)+y);
//        ?GameBoard(this.T.set());
        return move;
    }
    public ArrayList<Integer> makeMove(char c, ArrayList<Integer> qC,ArrayList<Integer> qN,ArrayList<Integer> aR,ArrayList<Integer> G) {
        ONE_DIM = G;

        ArrayList<Integer> move = new ArrayList<>();
        if(c=='b') {
//            move = black_xy.get(qC);
//            singleDimIndex = array[0].length * i + j
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