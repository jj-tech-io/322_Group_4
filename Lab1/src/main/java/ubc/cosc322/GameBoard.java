package ubc.cosc322;

import java.util.*;

import static java.lang.String.format;

public class GameBoard {

    public int[] oneDim = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0};


    public int[][] getMyBOARD() {
        return MyBOARD;
    }


    public int[][] setMyBOARD(int player, int xF, int yF, int xT, int yT) {
        MyBOARD[xF][yF] = 0;
        MyBOARD[xT][yT] = player;
        return MyBOARD;
    }

    public int[][] MyBOARD = new int[][]{
            {0, 0, 0, 2, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},

    };



    public Map<Integer, ArrayList<Integer>> white_xy = new HashMap<>();
    public Map<Integer, ArrayList<Integer>> black_xy = new HashMap<>();
    public ArrayList<Integer> QUEEN_CURR = new ArrayList<>();

    public GameBoard() {

    }
    public GameBoard(int[][] cur) {
        this.MyBOARD = cur;
    }


    public void printBoard() {
        System.out.println("---------------------------------");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    System.out.println(" ");
                }
                System.out.print(" " + MyBOARD[i][j] + " ");
            }

        }

    }



    public void updateBoard(boolean opponent, int player, ArrayList<Integer> qC, ArrayList<Integer> qN, ArrayList<Integer> aR) {
        int xFrom;
        int yFrom;
        int xTo;
        int yTo;
        int arrowX;
        int arrowY;
        if(opponent) {
            ArrayList<Integer> qCT = new ArrayList<>();
            ArrayList<Integer> qNT = new ArrayList<>();
            ArrayList<Integer> aRT = new ArrayList<>();

            qCT = getXY(qC);
            System.out.println(qC + " qC /tr" + qCT);
            qNT = getXY(qN);
            System.out.println(qN + " qN /tr" + qNT);
            aRT = getXY(aR);
            System.out.println(aR + " aR/ tr" + aRT);
            printBoard();
            xFrom = qCT.get(0);
            yFrom = qCT.get(1);
            xTo = qNT.get(0);
            yTo = qNT.get(1);
            arrowX = aRT.get(0);
            arrowY = aRT.get(1);
        }
        else {


            xFrom = qC.get(0);
            yFrom = qC.get(1);
            xTo = qN.get(0);
            yTo = qN.get(1);
            arrowX = aR.get(0);
            arrowY = aR.get(1);
        }
        if(checkInBounds(xTo)&&checkInBounds(yTo)&&checkInBounds(xFrom)&&checkInBounds(yFrom)) {
            if (MyBOARD[xTo][yTo] == 0) {

                    MyBOARD[xFrom][yFrom] = 0;
                    MyBOARD[xTo][yTo] = player;
                    MyBOARD[arrowX][arrowY] = -1;





            }
        }

        //System.out.println("player" + c);
        printBoard();

    }
    public void updateQueen(int player, ArrayList<Integer> qC, ArrayList<Integer> qN) {



        ArrayList<Integer> qCT = new ArrayList<>();
        ArrayList<Integer> qNT = new ArrayList<>();
        ArrayList<Integer> aRT = new ArrayList<>();



        printBoard();

        int xFrom = qC.get(0);
        int yFrom = qC.get(1);
        int xTo = qN.get(0);
        int yTo = qN.get(1);
        System.out.println(xTo+" "+yTo);
        if(checkInBounds(xTo)&&checkInBounds(yTo)) {
            if(MyBOARD[xTo][yTo]==0) {

                    MyBOARD[xFrom][yFrom] = 0;
                    MyBOARD[xTo][yTo] = player;


            }
        }


        //System.out.println("player" + c);
        printBoard();

    }
    public void updateArrow(int player, ArrayList<Integer> aR) {


        printBoard();
        int arrowX = aR.get(0);
        int arrowY = aR.get(1);

        if(checkInBounds(arrowX)&&checkInBounds(arrowY)) {
            if (MyBOARD[arrowX][arrowY] == 0) {
                MyBOARD[arrowX][arrowY] = -1;
            }
        }


        printBoard();


    }
    public int getIndex(ArrayList<Integer> XY) {
        int row = XY.get(0);
        int col = XY.get(1);
        int index = (10 - row) * 10 + col;
        System.out.println("index " + index + ", row " + row + ", col " + col);
        return index;
    }

    //transform AMAZONGAME BOARD POSITION X,Y to GameBoard x,y
    public ArrayList<Integer> getXY(ArrayList<Integer> xy) {
        ArrayList<Integer> trans = new ArrayList<>();
        if (!xy.isEmpty())
            trans.add(10 - xy.get(0));
        trans.add(xy.get(1) - 1);

        return trans;
    }

    //transform GameBoard x,y  to  AMAZONGAME BOARD POSITION X,Y
    public ArrayList<Integer> undoXY(ArrayList<Integer> trans) {
        ArrayList<Integer> XY = new ArrayList<>();
        XY.add(10 - trans.get(0));
        XY.add(trans.get(1) + 1);
        return XY;
    }





    public boolean[] checkNeighbour(int x, int y, int[][] board) {
        boolean[] UDRL = new boolean[4];
        try {
            int i = board[x + 1][y + 1];
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }

        return UDRL;
    }


    public List<Object> getMoves(int ours, Node node) {
        int score = 0;
        int theirs;
        int[][] b = node.current.getMyBOARD();
        int[][] copy = node.copy.getMyBOARD();
        List<Object> moves_states = new ArrayList<>();

        ArrayList<ArrayList<ArrayList<Integer>>> moves = new ArrayList<>();
        if(ours==1) {
            theirs = 2;
        }
        else{
            theirs = 1;
        }
        //int utility =(int)getScore(ours).get(1) - (int)getScore(theirs).get(1) ;

        for (int row = 0; row < 10; row++) {

            for (int col = 0; col < 10; col++) {
                if (b[row][col] == ours) {
                    moves_states.add(Arrays.asList(row,col));
                    boolean up = checkInBounds(row + 1);
                    boolean down = checkInBounds(row - 1);
                    boolean right = checkInBounds(col + 1);
                    boolean left = checkInBounds(col - 1);
                    boolean up2 = checkInBounds(row + 2);
                    boolean down2 = checkInBounds(row - 2);
                    boolean right2 = checkInBounds(col + 2);
                    boolean left2 = checkInBounds(col - 2);
                    boolean up3 = checkInBounds(row + 3);
                    boolean down3 = checkInBounds(row - 3);
                    boolean right3 = checkInBounds(col + 3);
                    boolean left3 = checkInBounds(col - 3);
                    for (int i = 0; i < 8; i++) {
                        switch (i) {
                            case 0: // right3down3
                                if (right3 && down3) {
                                    if (b[row - 3][col + 3] == 0) {
                                        ArrayList<Integer> move = new ArrayList<>();
                                        ArrayList<Integer> from = new ArrayList<>();
                                        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                                        move.add(row - 3);
                                        move.add(col + 3);
                                        from.add(row);
                                        from.add(col);
                                        toFrom.add(move);
                                        toFrom.add(from);
                                        moves.add(toFrom);
                                    }
                                }
                            case 1: //left3down3
                                if (left3 && down3) {
                                    if (b[row - 3][col - 3] == 0) {
                                        ArrayList<Integer> move = new ArrayList<>();
                                        ArrayList<Integer> from = new ArrayList<>();
                                        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                                        move.add(row - 3);
                                        move.add(col - 3);
                                        from.add(row);
                                        from.add(col);
                                        toFrom.add(move);
                                        toFrom.add(from);
                                        moves.add(toFrom);
                                    }
                                }
                            case 2: //right3 up3
                                if (right3 && up3) {
                                    if (b[row + 3][col + 3] == 0) {
                                        ArrayList<Integer> move = new ArrayList<>();
                                        ArrayList<Integer> from = new ArrayList<>();
                                        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                                        move.add(row + 3);
                                        move.add(col + 3);
                                        from.add(row);
                                        from.add(col);
                                        toFrom.add(move);
                                        toFrom.add(from);
                                        moves.add(toFrom);
                                    }
                                }
                            case 3: //left3 up3
                                if (left3 && up3) {
                                    if (b[row + 3][col - 3] == 0) {
                                        ArrayList<Integer> move = new ArrayList<>();
                                        ArrayList<Integer> from = new ArrayList<>();
                                        ArrayList<ArrayList<Integer>> toFrom = new ArrayList<>();
                                        move.add(row + 3);
                                        move.add(col - 3);
                                        from.add(row);
                                        from.add(col);
                                        toFrom.add(move);
                                        toFrom.add(from);
                                        moves.add(toFrom);
                                    }
                                }
                        }

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
                                        move.add(col + 1);
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
                                        move.add(col + 1);
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
                            case (8): //downdownrightright

                        }

                    }
                }

            }
        }
        //System.out.println(moves);
        moves_states.add(moves);
        return moves_states;
    }

    public boolean checkInBounds(int index) {
        boolean inBounds = (index >= 0) && (index < 10);
        return inBounds;
    }

    public ArrayList<Integer> getArrow(GameBoard gameBoard, ArrayList<Integer> pos) {

        ArrayList<ArrayList<Integer>> arrows = new ArrayList<>();
        int row = pos.get(0);
        int col = pos.get(1);
        boolean up = checkInBounds(row + 1);
        boolean down = checkInBounds(row - 1);
        boolean right = checkInBounds(col + 1);
        boolean left = checkInBounds(col - 1);
        boolean up2 = checkInBounds(row + 2);
        boolean down2 = checkInBounds(row - 2);
        boolean right2 = checkInBounds(col + 2);
        boolean left2 = checkInBounds(col - 2);
        boolean up3 = checkInBounds(row + 3);
        boolean down3 = checkInBounds(row - 3);
        boolean right3 = checkInBounds(col + 3);
        boolean left3 = checkInBounds(col - 3);
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0: // right2
                    if (right2) {
                        if (gameBoard.MyBOARD[row][col + 2] == 0) {
                            ArrayList<Integer> arrow = new ArrayList<>();
                            arrow.add(row);
                            arrow.add(col + 2);
                            arrows.add(arrow);
                            break;
                        }
                    }

                case 1: //left2
                    if (left2) {
                        if (MyBOARD[row][col - 2] == 0) {
                            ArrayList<Integer> arrow = new ArrayList<>();
                            arrow.add(row);
                            arrow.add(col - 2);
                            arrows.add(arrow);
                            break;
                        }
                    }


                    switch (i) {
                        case 0: // right3down3
                            if (right3 && down3) {
                                if (MyBOARD[row - 3][col + 3] == 0) {
                                    ArrayList<Integer> arrow = new ArrayList<>();
                                    arrow.add(row - 3);
                                    arrow.add(col + 3);
                                    arrows.add(arrow);

                                }
                            }
                        case 1: //left3down3
                            if (left3 && down3) {
                                if (gameBoard.MyBOARD[row - 3][col - 3] == 0) {
                                    ArrayList<Integer> arrow = new ArrayList<>();
                                    arrow.add(row - 3);
                                    arrow.add(col - 3);
                                    arrows.add(arrow);

                                }
                            }
                        case 2: //right3 up3
                            if (right3 && up3) {
                                if (gameBoard.MyBOARD[row + 3][col + 3] == 0) {
                                    ArrayList<Integer> arrow = new ArrayList<>();
                                    arrow.add(row + 3);
                                    arrow.add(col + 3);
                                    arrows.add(arrow);

                                }
                            }
                        case 3: //left3 up3
                            if (left3 && up3) {
                                if (gameBoard.MyBOARD[row + 3][col - 3] == 0) {
                                    ArrayList<Integer> arrow = new ArrayList<>();
                                    arrow.add(row + 3);
                                    arrow.add(col - 3);
                                    arrows.add(arrow);

                                }
                            }
                    }

                    switch (i) {
                        case 0: //up
                            if (up) {
                                if (gameBoard.MyBOARD[row + 1][col] == 0) {
                                    ArrayList<Integer> arrow = new ArrayList<>();
                                    arrow.add(row - 1);
                                    arrow.add(col);
                                    arrows.add(arrow);


                                }
                            }
                        case 1: //up/right
                            if (up && right) {
                                if (gameBoard.MyBOARD[row + 1][col + 1] == 0) {
                                    ArrayList<Integer> arrow = new ArrayList<>();
                                    arrow.add(row + 1);
                                    arrow.add(col + 1);
                                    arrows.add(arrow);


                                }
                            }
                        case 2: //right
                            if (right) {
                                if (gameBoard.MyBOARD[row][col + 1] == 0) {
                                    ArrayList<Integer> arrow = new ArrayList<>();
                                    arrow.add(row);
                                    arrow.add(col + 1);
                                    arrows.add(arrow);

                                }
                            }
                        case 3: //down/right
                            if (right && down) {
                                if (MyBOARD[row - 1][col + 1] == 0) {
                                    ArrayList<Integer> arrow = new ArrayList<>();
                                    arrow.add(row - 1);
                                    arrow.add(col + 1);
                                    arrows.add(arrow);

                                }
                            }
                        case 4: //down
                            if (down) {
                                if (MyBOARD[row - 1][col] == 0) {
                                    ArrayList<Integer> arrow = new ArrayList<>();
                                    arrow.add(row - 1);
                                    arrow.add(col);
                                    arrows.add(arrow);
                                }
                            }
                        case 5: //down/left
                            if (down && left) {
                                if (gameBoard.MyBOARD[row - 1][col - 1] == 0) {
                                    ArrayList<Integer> arrow = new ArrayList<>();
                                    arrow.add(row - 1);
                                    arrow.add(col - 1);
                                    arrows.add(arrow);

                                }
                            }
                        case 6: //left
                            if (left) {
                                if (gameBoard.MyBOARD[row][col - 1] == 0) {
                                    ArrayList<Integer> arrow = new ArrayList<>();
                                    arrow.add(row);
                                    arrow.add(col - 1);
                                    arrows.add(arrow);
                                }
                            }
                        case 7: //left/up
                            if (up && left) {
                                if (gameBoard.MyBOARD[row + 1][col - 1] == 0) {
                                    ArrayList<Integer> arrow = new ArrayList<>();
                                    arrow.add(row + 1);
                                    arrow.add(col - 1);
                                    arrows.add(arrow);
                                }
                            }

                    }
            }


        }
        return arrows.get(0);
    }

    public List<Object> getScore(int player) {
        int score = 0;
        ArrayList<List<Object>> positions = new ArrayList<>();

        int queen = 0;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (MyBOARD[row][col] == player) {
                    List<Object> pos = new ArrayList<>();
                    List<Object> moves = new ArrayList<>();
                    pos.add(row);
                    pos.add(col);
                    int up = 0;
                    int down = 0;
                    int left = 0;
                    int right = 0;
                    int rightup = 0;
                    int rightdown = 0;
                    int leftup = 0;
                    int leftdown = 0;
                    boolean upS = true;
                    boolean downS = true;
                    boolean rightS = true;
                    boolean leftS = true;
                    boolean rightUpS = true;
                    boolean leftUpS = true;
                    boolean rightDownS = true;
                    boolean leftDownS = true;
                    int queenScore = 0;
                    int index = 1;
                    while (index < 9 && upS||downS||rightS||leftS) {
                        //up
                        if (upS && checkInBounds(row - index) && MyBOARD[row - index][col] == 0) {
                            //continue
                            up++;
                            queenScore++;
                            score++;
                        } else {
                            upS = false;

                        }
                        if (downS && checkInBounds(row + index) && MyBOARD[row + index][col] == 0 ) {
                            //continue
                            queenScore++;
                            score++;
                            down++;
                        }
                        else {
                            downS = false;
                        }
                        //right
                        if (rightS && checkInBounds(row) && checkInBounds(col + index) && MyBOARD[row][col+ index] == 0 ) {
                            //continue
                            queenScore++;
                            score++;
                            right++;
                        }
                        else {
                            rightS = false;
                        }
                        //left
                        if (leftS && checkInBounds(row) && checkInBounds(col - index) && MyBOARD[row][col - index] == 0 ) {
                            //continue
                            queenScore++;
                            score++;
                            left++;
                        }
                        else {
                            leftS = false;
                        }
                        //up/right
                        if (rightUpS && checkInBounds(row - index) && checkInBounds(col + index) && MyBOARD[row - index][col+ index] == 0 ) {
                            //continue
                            queenScore++;
                            score++;
                            rightup++;
                        }
                        else {
                            rightUpS = false;
                        }
                        //up/left
                        if (leftUpS && checkInBounds(row - index) && checkInBounds(col - index) && MyBOARD[row - index][col - index] == 0 ) {
                            //continue
                            queenScore++;
                            score++;
                            leftup++;
                        }
                        else {
//                            System.out.println(rightup + " rightup");
                            leftUpS = false;
                        }
                        //down/right
                        if (rightDownS && checkInBounds(row + index) && checkInBounds(col + index) && MyBOARD[row + index][col+ index] == 0 ) {
                            //continue
                            queenScore++;
                            score++;
                            rightdown++;
                        }
                        else {
//                            System.out.println(rightup + " rightup");
                            rightDownS = false;
                        }
                        //down/left
                        if (leftDownS && checkInBounds(row + index) && checkInBounds(col - index) && MyBOARD[row + index][col - index] == 0 ) {
                            //continue
                            queenScore++;
                            score++;
                            leftdown++;
                        }
                        else {
//                            System.out.println(rightup + " rightup");
                            leftDownS = false;
                        }
                        index++;
                    }

                    moves.add(up);
                    moves.add(down);
                    moves.add(left);
                    moves.add(right);
                    moves.add(rightup);
                    moves.add(leftup);
                    moves.add(rightdown);
                    moves.add(leftdown);
                    List<Object> data_row = new ArrayList<>();
                    pos.add(queenScore);
                    data_row.add(pos);
                    data_row.add(moves);


                    positions.add(data_row);
                    }
                }
            }
        System.out.println("\n{score,{{row,col,queenScore},{up,down,left,right,rightUp,leftUp,rightDown,leftDown}},{},{}}");
        return Arrays.asList(score,positions);
        }
}