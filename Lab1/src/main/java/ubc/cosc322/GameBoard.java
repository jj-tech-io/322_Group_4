package ubc.cosc322;

import java.util.*;
import java.util.function.IntFunction;

import static java.lang.String.format;

public class GameBoard {

    public   int[] oneDim = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0};


    public int[][] getMyBOARD() {
        return MyBOARD;
    }


    public int [][] setMyBOARD(int xF,int yF,int xT,int yT) {
        MyBOARD[xF][yF] = 0;
        MyBOARD[xT][yT] = 0;
        return MyBOARD;
    }
    private String board =
            "10,0,0,0,2,0,0,2,0,0,0\n"+
            "9,0,0,0,2,0,0,2,0,0,0\n"+
            "8,0,0,0,2,0,0,2,0,0,0\n"+
            "7,0,0,0,2,0,0,2,0,0,0\n"+
            "6,0,0,0,2,0,0,2,0,0,0\n"+
            "5,0,0,0,2,0,0,2,0,0,0\n"+
            "4,0,0,0,2,0,0,2,0,0,0\n"+
            "3,0,0,0,2,0,0,2,0,0,0\n"+
            "2,0,0,0,2,0,0,2,0,0,0\n"+
            "1,0,0,0,0,0,0,0,0,0,0\n"+
            " ,0,0,0,1,0,0,1,0,0,0\n";

    private int[][] MyBOARD = new int[][] {
            {0,0,0,2,0,0,2,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {2,0,0,0,0,0,0,0,0,2},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0,0,0,1},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,1,0,0,1,0,0,0},

    };
    private static char[] letters = new char[]{'\t', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};


    public  Map<Integer,ArrayList<Integer>> white_xy= new HashMap<>();
    public  Map<Integer,ArrayList<Integer>> black_xy = new HashMap<>();
    public  ArrayList<Integer> QUEEN_CURR = new ArrayList<>();


//    public GameBoard(ArrayList<Integer> b) {
//
//        int index = 0;
//        int wI = 0;
//        int bI = 0;
//
//        ArrayList<Integer> temp2 = new ArrayList<>();
//        for (int i = 0; i < 11; i++) {
//            temp2.clear();
//            for (int j = 0; j < 11; j++) {
//                boardCurrent[i][j] = b.get(index);
//                ONE_DIM.add(b.get(index));
//                temp2.add(b.get(index));
//                if(b.get(index) == 1) {
//                    ArrayList<Integer> t = new ArrayList<>();
//                    t.add(i);
//                    t.add(j);
//                    white_xy.put(wI,t);
//                    wI++;
//                }
//                else if(b.get(index) ==2) {
//                    ArrayList<Integer> t = new ArrayList<>();
//                    t.add(i);
//                    t.add(j);
//                    black_xy.put(bI,t);
//                    bI++;
//                }
//                index++;
//
//            }
//            TWO_DIM.add(temp2);
//
//        }
//
//    }

    public GameBoard() {

    }




    public void printBoard() {
        System.out.println("---------------------------------");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                    if(j==0) {
                        System.out.println(" ");
                    }
                    System.out.print(" " + MyBOARD[i][j] + " ");
                }

            }

}








    public void checkSurround() {


    }



    public void updateBoard(char c, ArrayList<Integer> qC,ArrayList<Integer> qN,ArrayList<Integer> aR) {
        ArrayList<Integer> qCT = new ArrayList<>();
        ArrayList<Integer> qNT = new ArrayList<>();
        ArrayList<Integer> aRT = new ArrayList<>();

        qCT = getXY(qC);
        System.out.println(qC+" qC /tr" +qCT);
        qNT = getXY(qN);
        System.out.println(qN+ " qN /tr" +qNT);
        aRT = getXY(aR);
        System.out.println( aR+" aR/ tr" +aRT);
        printBoard();

        int xFrom = qCT.get(0);
        int yFrom = qCT.get(1);
        int xTo = qNT.get(0);
        int yTo = qNT.get(1);
        int arrowX = aRT.get(0);
        int arrowY = aRT.get(1);

        if(c=='b'){
            MyBOARD[xFrom][yFrom] = 0;
            MyBOARD[xTo][yTo] = 2;
            MyBOARD[arrowX][arrowY] = -1;
        }

        else {
            MyBOARD[xFrom][yFrom] = 0;
            MyBOARD[xTo][yTo] = 1;
            MyBOARD[arrowX][arrowY] = -1;

        }

        System.out.println("player"+c);
        printBoard();

    }

    public int getIndex(ArrayList<Integer> XY) {
        int row = XY.get(0);
        int col = XY.get(1);
        int index = (10 - row)*10 +col;
        System.out.println("index "+index+", row " +row+", col " +col);
        return index;
    }
    //transform AMAZONGAME BOARD POSITION X,Y to GameBoard x,y
    public ArrayList<Integer> getXY(ArrayList<Integer> xy) {
        ArrayList<Integer> trans = new ArrayList<>();
        if(!xy.isEmpty())
            trans.add(10 - xy.get(0));
            trans.add(xy.get(1) - 1);

        return trans;
        }
    //transform GameBoard x,y  to  AMAZONGAME BOARD POSITION X,Y
    public ArrayList<Integer> undoXY(ArrayList<Integer> trans) {
        ArrayList<Integer> XY = new ArrayList<>();
        XY.add(10-trans.get(0));
        XY.add(trans.get(1)+1);
        return XY;
    }
    public static int[][] convertTo2DArray(ArrayList<Integer> board){
        int[][] newBoard = new int[11][11];

        //Don't touch this hackjob (It converts the server's one-dimensional gamestate array into one that isn't upside down. Also moves the obsolete row back to the top of the 2D array after flipping.)
        for (int y = 1; y < 11; y++)
            for (int x = 0; x < 11; x++)
                newBoard[x][11 - y] = Integer.valueOf(board.get(y*11 + x));
        //moving obsolete row back to the top:
        for (int x = 0; x < 11; x++)
            newBoard[x][0] = Integer.valueOf(board.get(x));

        return newBoard;
    }

    public void searchForMove(int player) {
        for(int row = 0; row<MyBOARD[0].length;row++) {
            for(int col = 0; col < MyBOARD.length; col++) {
                if(MyBOARD[row][col]==player) {
                    boolean [] upDownRightLeft =checkNeighbour(row,col,MyBOARD);

                }
            }
        }
    }
    public boolean[] checkNeighbour(int x, int y,int[][] board) {
        boolean [] UDRL = new boolean[4];
        try {
            int i = board[x + 1][y + 1];
        }
        catch (IndexOutOfBoundsException e){
            throw e;
        }

        return UDRL;
    }


    public ArrayList<ArrayList<Integer>> getMoves(int ours,Node node) {
        int score = 0;
        int[][] b = node.current.getMyBOARD();
        int [][] copy = node.copy.getMyBOARD();
        ArrayList<ArrayList<ArrayList<Integer>>> moves = new ArrayList<>();
        for(int row = 0; row< 10; row++) {

            for (int col = 0; col < 10; col++) {
                if (b[row][col] == ours) {
                    System.out.println("1 of ours: "+row+" "+col);
                    boolean up = checkInBounds(row+1);
                    boolean down = checkInBounds(row-1);
                    boolean right = checkInBounds(col+1);
                    boolean left = checkInBounds(col-1);
                    boolean upup = checkInBounds(row+2);
                    boolean downdown = checkInBounds(row-2);
                    boolean rightright = checkInBounds(col+2);
                    boolean leftleft = checkInBounds(col-2);
                    for(int i = 0; i < 8; i++) {


                        switch (i) {
                            case 0: //up
                                if (up) {
                                    if (b[row + 1][col] == 0) {
                                        ArrayList<Integer> move = new ArrayList<>();
                                        ArrayList<Integer> from = new ArrayList<>();
                                        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                                        move.add(row + 1);
                                        move.add(col);
                                        from.add(row);
                                        from.add(col);
                                        toFrom.add(move);
                                        toFrom.add(from);
                                        moves.add(toFrom);

                                    }
                                }
                            case 1: //up/right
                                if (up && right) {
                                    if (b[row + 1][col + 1] == 0) {
                                        ArrayList<Integer> move = new ArrayList<>();
                                        ArrayList<Integer> from = new ArrayList<>();
                                        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                                        move.add(row + 1);
                                        move.add(col+1);
                                        from.add(row);
                                        from.add(col);
                                        toFrom.add(move);
                                        toFrom.add(from);
                                        moves.add(toFrom);
                                    }
                                }
                            case 2: //right
                                if (right) {
                                    if (b[row][col + 1] == 0) {
                                        ArrayList<Integer> move = new ArrayList<>();
                                        ArrayList<Integer> from = new ArrayList<>();
                                        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                                        move.add(row);
                                        move.add(col+1);
                                        from.add(row);
                                        from.add(col);
                                        toFrom.add(move);
                                        toFrom.add(from);
                                        moves.add(toFrom);
                                    }
                                }
                            case 3: //down/right
                                if (right && down) {
                                    if (b[row - 1][col + 1] == 0) {
                                        ArrayList<Integer> move = new ArrayList<>();
                                        ArrayList<Integer> from = new ArrayList<>();
                                        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                                        move.add(row - 1);
                                        move.add(col + 1);
                                        from.add(row);
                                        from.add(col);
                                        toFrom.add(move);
                                        toFrom.add(from);
                                        moves.add(toFrom);

                                    }
                                }
                            case 4: //down
                                if (down) {
                                    if (b[row - 1][col] == 0) {
                                        ArrayList<Integer> move = new ArrayList<>();
                                        ArrayList<Integer> from = new ArrayList<>();
                                        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                                        move.add(row - 1);
                                        move.add(col);
                                        from.add(row);
                                        from.add(col);
                                        toFrom.add(move);
                                        toFrom.add(from);
                                        moves.add(toFrom);
                                    }
                                }
                            case 5: //down/left
                                if (down && left) {
                                    if (b[row - 1][col - 1] == 0) {
                                        ArrayList<Integer> move = new ArrayList<>();
                                        ArrayList<Integer> from = new ArrayList<>();
                                        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                                        move.add(row - 1);
                                        move.add(col - 1);
                                        from.add(row);
                                        from.add(col);
                                        toFrom.add(move);
                                        toFrom.add(from);
                                        moves.add(toFrom);
                                    }
                                }
                            case 6: //left
                                if (left) {
                                    if (b[row][col - 1] == 0) {
                                        ArrayList<Integer> move = new ArrayList<>();
                                        ArrayList<Integer> from = new ArrayList<>();
                                        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                                        move.add(row);
                                        move.add(col - 1);
                                        from.add(row);
                                        from.add(col);
                                        toFrom.add(move);
                                        toFrom.add(from);
                                        moves.add(toFrom);
                                    }
                                }
                            case 7: //left/up
                                if (up && left) {
                                    if (b[row + 1][col - 1] == 0) {
                                        ArrayList<Integer> move = new ArrayList<>();
                                        ArrayList<Integer> from = new ArrayList<>();
                                        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                                        move.add(row + 1);
                                        move.add(col - 1);
                                        from.add(row);
                                        from.add(col);
                                        toFrom.add(move);
                                        toFrom.add(from);
                                        moves.add(toFrom);
                                    }
                                }

                        }
                    }
                }

            }
        }
        System.out.println(moves);
        return moves.get(0);
    }
    public boolean checkInBounds(int index) {
        boolean inBounds = (index >= 0) && (index < 10);
        return inBounds;
    }

    public ArrayList<Integer> getArrow(Node node,ArrayList<Integer> pos) {

        int[][] b = node.current.getMyBOARD();
        int [][] copy = node.copy.getMyBOARD();
        ArrayList<ArrayList<Integer>> arrows = new ArrayList<>();
        int row = pos.get(0);
        int col = pos.get(1);
        boolean up = checkInBounds(row+1);
        boolean down = checkInBounds(row-1);
        boolean right = checkInBounds(col+1);
        boolean left = checkInBounds(col-1);
        boolean upup = checkInBounds(row+2);
        boolean downdown = checkInBounds(row-2);
        boolean rightright = checkInBounds(col+2);
        boolean leftleft = checkInBounds(col-2);
        for(int i = 0; i < 8; i++) {


            switch (i) {
                case 0: //up
                    if (up) {
                        if (b[row + 1][col] == 0) {
                            ArrayList<Integer> arrow = new ArrayList<>();
                            ArrayList<Integer> from = new ArrayList<>();
                            ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                            arrow.add(row - 1);
                            arrow.add(col);
                            arrows.add(arrow);

                        }
                    }
                case 1: //up/right
                    if (up && right) {
                        if (b[row + 1][col + 1] == 0) {
                            ArrayList<Integer> arrow = new ArrayList<>();
                            ArrayList<Integer> from = new ArrayList<>();
                            ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                            arrow.add(row + 1);
                            arrow.add(col+1);
                            arrows.add(arrow);

                        }
                    }
                case 2: //right
                    if (right) {
                        if (b[row][col + 1] == 0) {
                            ArrayList<Integer> arrow = new ArrayList<>();
                            ArrayList<Integer> from = new ArrayList<>();
                            ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                            arrow.add(row);
                            arrow.add(col+1);
                            arrows.add(arrow);

                        }
                    }
                case 3: //down/right
                    if (right && down) {
                        if (b[row - 1][col + 1] == 0) {
                            ArrayList<Integer> arrow = new ArrayList<>();
                            ArrayList<Integer> from = new ArrayList<>();
                            ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                            arrow.add(row - 1);
                            arrow.add(col+1);
                            arrows.add(arrow);

                        }
                    }
                case 4: //down
                    if (down) {
                        if (b[row - 1][col] == 0) {
                            ArrayList<Integer> arrow = new ArrayList<>();
                            ArrayList<Integer> from = new ArrayList<>();
                            ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                            arrow.add(row - 1);
                            arrow.add(col);
                            arrows.add(arrow);
                        }
                    }
                case 5: //down/left
                    if (down && left) {
                        if (b[row - 1][col - 1] == 0) {
                            ArrayList<Integer> arrow = new ArrayList<>();
                            ArrayList<Integer> from = new ArrayList<>();
                            ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                            arrow.add(row - 1);
                            arrow.add(col - 1);
                            arrows.add(arrow);

                        }
                    }
                case 6: //left
                    if (left) {
                        if (b[row][col - 1] == 0) {
                            ArrayList<Integer> arrow = new ArrayList<>();
                            ArrayList<Integer> from = new ArrayList<>();
                            ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                            arrow.add(row);
                            arrow.add(col - 1);
                            arrows.add(arrow);
                        }
                    }
                case 7: //left/up
                    if (up && left) {
                        if (b[row + 1][col - 1] == 0) {
                            ArrayList<Integer> arrow = new ArrayList<>();
                            ArrayList<Integer> from = new ArrayList<>();
                            ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                            arrow.add(row + 1);
                            arrow.add(col - 1);
                            arrows.add(arrow);
                        }
                    }

            }
        }
    return arrows.get(0);
    }





}