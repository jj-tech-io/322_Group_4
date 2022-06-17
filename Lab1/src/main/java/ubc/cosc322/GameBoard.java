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

    private int[][] MyBOARD = new int[][]{
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


    public GameBoard() {

    }
    public GameBoard(int[][] cur) {
        this.MyBOARD = cur;
    }


    public String boardString() {
        String b = "---------------------------------";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    b+="\n ";
                }
                b+=" " + MyBOARD[i][j] + " ";
            }

        }
        return b;

    }



    public void updateBoard(boolean op, int player, ArrayList<Integer> qC, ArrayList<Integer> qN, ArrayList<Integer> aR) {
        System.out.println("update"+ player+" opponent = "+ op +" move = "+qC+qN+aR );
        ArrayList<Integer> qCT = new ArrayList<>();
        ArrayList<Integer> qNT = new ArrayList<>();
        ArrayList<Integer> aRT = new ArrayList<>();
        int xFrom;
        int yFrom;
        int xTo;
        int yTo;
        int arrowX;
        int arrowY;
        List<Boolean> valid = new ArrayList<>();

        if(op) {
            qCT = getXY(qC);
            System.out.println(qC + " qC /tr" + qCT);
            qNT = getXY(qN);
            System.out.println(qN + " qN /tr" + qNT);
            aRT = getXY(aR);
            System.out.println(aR + " aR/ tr" + aRT);

            xFrom = qCT.get(0);
            yFrom = qCT.get(1);
            xTo = qNT.get(0);
            yTo = qNT.get(1);
            arrowX = aRT.get(0);
            arrowY = aRT.get(1);
            valid = this.validateMove(qCT,qNT,aRT);
        }
        else {
            xFrom = qC.get(0);
            yFrom = qC.get(1);
            xTo = qN.get(0);
            yTo = qN.get(1);
            arrowX = aR.get(0);
            arrowY = aR.get(1);
            valid = this.validateMove(qC,qN,aR);
        }

        if(!valid.get(0) || !valid.get(1) || !valid.get(2)) {
            System.out.println("player: "+player+ " made and invalid move: \n" +valid);
            return;
        }

        MyBOARD[xFrom][yFrom] = 0;
            MyBOARD[xTo][yTo] = player;
            MyBOARD[arrowX][arrowY] = -1;

    }
    public void updateQueen(char c, ArrayList<Integer> qC, ArrayList<Integer> qN) {
        System.out.println("update queen"+c);
        ArrayList<Integer> qCT = new ArrayList<>();
        ArrayList<Integer> qNT = new ArrayList<>();
        ArrayList<Integer> aRT = new ArrayList<>();

        int xFrom = qC.get(0);
        int yFrom = qC.get(1);
        int xTo = qN.get(0);
        int yTo = qN.get(1);



        if (c == 'b' && checkInBounds(xFrom) && checkInBounds(yFrom) && MyBOARD[xTo][yTo]==0) {
            MyBOARD[xFrom][yFrom] = 0;
            MyBOARD[xTo][yTo] = 2;

        } else if(checkInBounds(xFrom) && checkInBounds(yFrom) ) {
            MyBOARD[xFrom][yFrom] = 0;
            MyBOARD[xTo][yTo] = 1;
        }

        //System.out.println("player" + c);

    }
    public List<Boolean> validateMove(ArrayList<Integer> qC, ArrayList<Integer> qN, ArrayList<Integer> aR) {

        boolean validQC = true;
        boolean validQN = true;
        boolean validAR = true;
        if(qC.size() >1) {
            if(checkInBounds(qC.get(0))&& checkInBounds(qC.get(1))) {
                validQC = true;
            }
            else {
                validQC = false;
            }

        }
        else {
            validQC = false;
        }
        if(qN.size()>1) {
            if(checkInBounds(qN.get(0))&& checkInBounds(qN.get(1))) {

                    validQN = true;

            }
            else {
                validQN = false;
            }
        }
        else {
            validQN = false;
        }
        if(aR.size() > 1) {
            if(checkInBounds(aR.get(0))&& checkInBounds(aR.get(1))) {
                validAR = true;
            }
            else {
                validAR = false;
            }
        }
        else {
            validAR =  false;
        }


        return Arrays.asList(validQC,validQN,validAR);
    }
    public void updateArrow(char c, ArrayList<Integer> aR) {
        System.out.println("update arrow"+c);

        int arrowX = aR.get(0);
        int arrowY = aR.get(1);
        if (c == 'b') {
            MyBOARD[arrowX][arrowY] = -1;
        } else {
            MyBOARD[arrowX][arrowY] = -1;
        }

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


    public boolean checkInBounds(int index) {
        boolean inBounds = (index >= 0) && (index < 10);
        return inBounds;
    }

    public ArrayList<Integer> getArrowN(ArrayList<Integer> qn) {

        ArrayList<Integer> arrow = new ArrayList<>();
        int index = 0;
        int row = qn.get(0);
        int col = qn.get(1);
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
        int max = 0;
        int possible = 8;
        do {

            //up
            if (upS && checkInBounds(row - index) && MyBOARD[row - index][col] == 0) {
                //continue
                up++;
                arrow.clear();
                arrow.add(row-index);
                arrow.add(col);

            } else {
                upS = false;
                possible--;
            }
            //down
            if (downS && checkInBounds(row + index) && MyBOARD[row + index][col] == 0 ) {
                //continue
                down++;
                arrow.clear();
                arrow.add(row+index);
                arrow.add(col);
            }
            else {
                downS = false;
                possible--;

            }
            //right
            if (rightS && checkInBounds(row) && checkInBounds(col + index) && MyBOARD[row][col+ index] == 0 ) {
                //continue
                right++;
                arrow.clear();
                arrow.add(row);
                arrow.add(col+index);
            }
            else {
                rightS = false;
                possible--;

            }
            //left
            if (leftS && checkInBounds(row) && checkInBounds(col - index) && MyBOARD[row][col - index] == 0 ) {
                //continue
                left++;
                arrow.clear();
                arrow.add(row);
                arrow.add(col-index);

            }
            else {
                leftS = false;
                possible--;

            }
            //up/right
            if (rightUpS && checkInBounds(row - index) && checkInBounds(col + index) && MyBOARD[row - index][col+ index] == 0 ) {
                //continue
                rightup++;
                arrow.clear();
                arrow.add(row-index);
                arrow.add(col+index);
            }
            else {
                rightUpS = false;
                possible--;
            }
            //up/left
            if (leftUpS && checkInBounds(row - index) && checkInBounds(col - index) && MyBOARD[row - index][col - index] == 0 ) {
                //continue
                leftup++;
                arrow.clear();
                arrow.add(row-index);
                arrow.add(col-index);
            }
            else {
                leftUpS = false;
                possible--;
            }
            //down/right
            if (rightDownS && checkInBounds(row + index) && checkInBounds(col + index) && MyBOARD[row + index][col+ index] == 0 ) {
                //continue
                rightdown++;
                arrow.clear();
                arrow.add(row+index);
                arrow.add(col+index);
            }
            else {
                rightDownS = false;
                possible--;
            }
            //down/left
            if (leftDownS && checkInBounds(row + index) && checkInBounds(col - index) && MyBOARD[row + index][col - index] == 0 ) {
                //continue
                leftdown++;
                arrow.clear();
                arrow.add(row+index);
                arrow.add(col-index);
            }
            else {
                leftDownS = false;
                possible--;
            }


            index++;
            if(possible < 2 && arrow.size() > 1)  {
                break;
            }
        }while(index <8);
        System.out.println("arrow: "+arrow);
        return  arrow;
    }

    public ArrayList<Integer> getQueenN(ArrayList<Integer> qC) {

        ArrayList<Integer> qN = new ArrayList<>();
        int index = 0;
        int row = qC.get(0);
        int col = qC.get(1);
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
        int max = 0;
        int qCX = qC.get(0);
        int qCY = qC.get(1);
        int qNX = qCX;
        int qnY = qCY;
        do {
            System.out.println("do while qn method");
            System.out.println(row + " " + col + " " + index );
            upS = checkInBounds(row - index);

            //up
            if (upS && checkInBounds(row - index)) {
                if(MyBOARD[row - index][col] == 0) {
                    System.out.println("up index: \n" + index);
                    //continue
                    up++;
                    //                qN.clear();
                    qN.add(row - index);
                    qN.add(col);
                }
                else {
                    upS = false;
                }

            }
            if (downS && checkInBounds(row + index) && MyBOARD[row + index][col] == 0 ) {
                System.out.println("down index: \n" +index);
                //continue
                down++;
//                qN.clear();
                qN.add(row + index);
                qN.add(col);

            }
            else {
                downS = false;

            }
            //right
            if (rightS && checkInBounds(row) && checkInBounds(col + index) && MyBOARD[row][col+ index] == 0 ) {
                System.out.println("right index: \n" +index);
                //continue
                right++;
                qN.clear();
                qN.add(row);
                qN.add(col + 1);

            }
            else {
                rightS = false;

            }
            //left
            if (leftS && checkInBounds(row) && checkInBounds(col - index) && MyBOARD[row][col - index] == 0 ) {
                System.out.println("right index: \n" +index);
                //continue
                left++;
//                qN.clear();
                qN.add(row);
                qN.add(col - index);

            }
            else {
                leftS = false;

            }
            //up/right
            if (rightUpS && checkInBounds(row - index) && checkInBounds(col + index) && MyBOARD[row - index][col+ index] == 0 ) {
                System.out.println("right index: \n" +index);
                //continue
                rightup++;
//                qN.clear();
                qN.add(row - index);
                qN.add(col + index);

            }
            else {
                rightUpS = false;
            }
            //up/left
            if (leftUpS && checkInBounds(row - index) && checkInBounds(col - index) && MyBOARD[row - index][col - index] == 0 ) {
                System.out.println("right index: \n" +index);
                //continue
                leftup++;
//                qN.clear();
                qN.add(row + index);
                qN.add(col - index);

            }
            else {
                leftUpS = false;
            }
            //down/right
            if (rightDownS && checkInBounds(row + index) && checkInBounds(col + index) && MyBOARD[row + index][col+ index] == 0 ) {
                System.out.println("right index: \n" +index);

                //continue
                rightdown++;
//                qN.clear();
                qN.add(row + index);
                qN.add(col + index);

            }
            else {
                rightDownS = false;
            }
            //down/left
            if (leftDownS && checkInBounds(row + index) && checkInBounds(col - index) && MyBOARD[row + index][col - index] == 0 ) {
                System.out.println("right index: \n" +index);

                //continue
                leftdown++;
//                qN.clear();
                qN.add(row + index);
                qN.add(col - index);

            }
            else {
                leftDownS = false;
            }


            index++;

        }while(index <9 );
        System.out.println("queenN: "+ qN +" "+ up+" "+ down+" "+ left+" "+ right+" ");
        return  qN;
    }

    public ArrayList<ArrayList<Integer>> queenList(int p) {
        ArrayList<ArrayList<Integer>> queenList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.MyBOARD[i][j] == p) {
                    ArrayList<Integer> q = new ArrayList<>();
                    q.add(i);
                    q.add(j);
                    queenList.add(q);
                    System.out.println(q);
                }

            }

        }
        System.out.println(queenList);
        return queenList;
    }

    public ArrayList<ArrayList<Integer>> getQueen(int p, int  xBad, int yBad) {
        ArrayList<ArrayList<Integer>> qq = new ArrayList<>();
        List<Object> playerScoreMoves = this.getScore(p);

        ArrayList<ArrayList<Integer>> movesPlayer = (ArrayList<ArrayList<Integer>>) playerScoreMoves.get(2);

        ArrayList<Integer> c = new ArrayList<>();
        ArrayList<Integer> c2 = new ArrayList<>();
        ArrayList<Integer> n = new ArrayList<>();
        ArrayList<Integer> n2 = new ArrayList<>();

        int MIN = 30;
        int MAX = 0;
        int queenDir = 2;

        int MIN2 = 28;
        int MAX2 = 0;
        int queenDir2 = 2;

        for(int i = 0; i < movesPlayer.size()-1; i++) {
            ArrayList<Integer> thisQueen = movesPlayer.get(i);
//            if(thisQueen.get(0) == xBad && thisQueen.get(1)==yBad) {
//                continue;
//            }
//            System.out.println("min max this queen");
//            System.out.println(thisQueen);
//            System.out.println(thisQueen.get(thisQueen.size()-1)+ " this q s");

//            if(thisQueen.get(thisQueen.size()-1) < MIN) {
              if(thisQueen.get(thisQueen.size()-1) < MIN) {
                //System.out.println("size" +thisQueen.get(thisQueen.size()-1));
                MIN = thisQueen.get(thisQueen.size()-1);
                MAX = 0;
                for (int j = 2; j < thisQueen.size()-1; j++) {

                    if (j>1 && thisQueen.get(j) > MAX ) {
                        MAX2 = MAX;
                        queenDir2 = queenDir;
                        c2 = movesPlayer.get(j-1);
                        c.add(thisQueen.get(0));
                        c.add(thisQueen.get(1));
                        MAX = thisQueen.get(j);
                        queenDir = j;
                        System.out.println("---- c c2 dir");
                        System.out.println(c);
                        System.out.println(c2);
                        System.out.println(queenDir);
                        System.out.println(queenDir2);

//                        System.out.println(" j = "+ j + " MAX = " + MAX);
//                        if(MAX > 4) {
//                            if(MAX % 2 == 1) {
//                                MAX = (MAX +1) /2;
//
//                            }
//                            else {
//                                MAX = MAX / 2;
//                            }
//
//                        }
                    }
                }
            }

        }
//        System.out.println("MIN MAX"+ MIN + MAX);
//
//
//
//
//        System.out.println("queen current:"+qC);
        qq.add(c);
        switch (queenDir) {

            case 2: //up
                //System.out.println("up"+queenDir+" "+MAX);
                n.add(c.get(0)-MAX);
                n.add(c.get(1));

                if (n.size() <1) {
                    n2.add(c2.get(0)-MAX2);
                    n2.add(c2.get(1));
                    qq.add(c2);
                    qq.add(n2);
                }
                else {
                    qq.add(c);
                    qq.add(n);
                }
            case 3: //down
                //System.out.println("down: "+queenDir+" "+MAX+" "+qC);
                n.add(c.get(0)+MAX);
                n.add(c.get(1));
                if (n.size() <1) {
                    n.add(c2.get(0)+MAX2);
                    n.add(c2.get(1));
                    qq.add(c2);
                    qq.add(n2);
                }
                else {
                    qq.add(c);
                    qq.add(n);
                }
            case 4: //left
                //System.out.println("left"+queenDir+" "+MAX);
                n.add(c.get(0));
                n.add(c.get(1)-MAX);
                if (n.size() <1) {
                    n.add(c2.get(0));
                    n.add(c2.get(1)-MAX2);
                    qq.add(c2);
                    qq.add(n2);
                }
                else {
                    qq.add(c);
                    qq.add(n);
                }
            case 5: //right
                //System.out.println("right"+queenDir+" "+MAX);
                n.add(c.get(0));
                n.add(c.get(1)+MAX);
                if (n.size() <1) {
                    n.add(c2.get(0));
                    n.add(c2.get(1)+MAX2);
                    qq.add(c2);
                    qq.add(n2);
                }
                else {
                    qq.add(c);
                    qq.add(n);
                }
            case 6: // rightUp
                //System.out.println("rightUp"+queenDir+" "+MAX);
                n.add(c.get(0)-MAX);
                n.add(c.get(1)+MAX);
                if (n.size() <1) {
                    n.add(c2.get(0)-MAX2);
                    n.add(c2.get(1)+MAX2);
                    qq.add(c2);
                    qq.add(n2);
                }
                else {
                    qq.add(c);
                    qq.add(n);
                }
            case 7: // leftUp
//                System.out.println("leftUp"+queenDir+" "+MAX);
                n.add(c.get(0)-MAX);
                n.add(c.get(1)-MAX);
                if (n.size() <1) {
                    n.add(c2.get(0)-MAX2);
                    n.add(c2.get(1)-MAX2);
                    qq.add(c2);
                    qq.add(n2);
                }
                else {
                    qq.add(c);
                    qq.add(n);
                }

            case 8:// rightDown
                //System.out.println("rightDown"+queenDir+" "+MAX);
                n.add(c.get(0)+MAX);
                n.add(c.get(1)+MAX);
                if (n.size() <1) {
                    n.add(c2.get(0)+MAX2);
                    n.add(c2.get(1)+MAX2);
                    qq.add(c2);
                    qq.add(n2);
                }
                else {
                    qq.add(c);
                    qq.add(n);
                }

            case 9:// leftDown
//                System.out.println("leftDown");
                n.add(c.get(0)+MAX);
                n.add(c.get(1)-MAX);
                if (n.size() <1) {
                    n.add(c2.get(0)+MAX2);
                    n.add(c2.get(1)-MAX2);
                    qq.add(c2);
                    qq.add(n2);
                }
                else {
                    qq.add(c);
                    qq.add(n);
                }
            default:
                System.out.println("----------------\n"+queenDir);




        }
        System.out.println(qq.size());
        System.out.println(qq);
        return qq;
    }

    public List<Object> getScore(int player) {

        int score = 0;
        ArrayList<ArrayList<Integer>> positions = new ArrayList<>();
        HashMap<String, ArrayList<Integer>> scoreTable = new HashMap<>();
        int queen = 0;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (this.MyBOARD[row][col] == player) {
                    ArrayList<Integer> pos = new ArrayList<>();
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
                        if (upS && checkInBounds(row - index) && this.MyBOARD[row - index][col] == 0) {
                            //continue
                            up++;
                            queenScore++;
                            score++;
                        } else {
                            upS = false;

                        }
                        if (downS && checkInBounds(row + index) && this.MyBOARD[row + index][col] == 0 ) {
                            //continue
                            queenScore++;
                            score++;
                            down++;
                        }
                        else {
                            downS = false;
                        }
                        //right
                        if (rightS && checkInBounds(row) && checkInBounds(col + index) && this.MyBOARD[row][col+ index] == 0 ) {
                            //continue
                            queenScore++;
                            score++;
                            right++;
                        }
                        else {
                            rightS = false;
                        }
                        //left
                        if (leftS && checkInBounds(row) && checkInBounds(col - index) && this.MyBOARD[row][col - index] == 0 ) {
                            //continue
                            queenScore++;
                            score++;
                            left++;
                        }
                        else {
                            leftS = false;
                        }
                        //up/right
                        if (rightUpS && checkInBounds(row - index) && checkInBounds(col + index) && this.MyBOARD[row - index][col+ index] == 0 ) {
                            //continue
                            queenScore++;
                            score++;
                            rightup++;
                        }
                        else {
                            rightUpS = false;
                        }
                        //up/left
                        if (leftUpS && checkInBounds(row - index) && checkInBounds(col - index) && this.MyBOARD[row - index][col - index] == 0 ) {
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
                        if (rightDownS && checkInBounds(row + index) && checkInBounds(col + index) && this.MyBOARD[row + index][col+ index] == 0 ) {
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
                        if (leftDownS && checkInBounds(row + index) && checkInBounds(col - index) && this.MyBOARD[row + index][col - index] == 0 ) {
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
                    pos.add(up);
                    pos.add(down);
                    pos.add(left);
                    pos.add(right);
                    pos.add(rightup);
                    pos.add(leftup);
                    pos.add(rightdown);
                    pos.add(leftdown);
                    pos.add(queenScore);
                    positions.add(pos);
                }
            }
        }
        //System.out.println("[player:score:[[row,col,up,down,left,right,rightUp,leftUp,rightDown,leftDown],[],[],[]]");
        List<Object> temp = Arrays.asList(player,score, positions);
        //System.out.println("MinMax.Optimal: "+ temp );
        return temp;
    }
}