package ubc.cosc322;
import java.util.*;


public class GameBoard {
    private int[][] gameBoard;
    private boolean movesListisCurrent = false;
    private ArrayList<int[]> movesList;

    public GameBoard() {
    }

    /**
     * @param gamestate gameBoard in 1D
     */
    public GameBoard(ArrayList<Integer> gamestate) {
        this.gameBoard = convertTo2DArray(gamestate);
    }

    /**
     * @param gameBoard gamestate gameBoard in 2D
     */
    public GameBoard(int[][] gameBoard) {
        this.gameBoard = Arrays.copyOf(gameBoard, gameBoard.length);
    }

    /**
     * @param othergameBoard copy gameBoard
     * @return copy of gameBoard.
     */
    public static GameBoard copyOf(GameBoard othergameBoard) {
        int[][] temp = othergameBoard.getInnerGameBoardArray();
        int[][] newInnergameBoard = new int[11][11];
        for (int i = 0; i < temp.length; i++)
            System.arraycopy(temp[i], 0, newInnergameBoard[i], 0, temp[0].length);

        return new GameBoard(newInnergameBoard);
    }

    /** method for inner gameBoard variable
     * @param gameBoard 1D format
     */
    public void setGameBoard(ArrayList<Integer> gameBoard) {
        this.gameBoard = convertTo2DArray(gameBoard);
        movesListisCurrent = false;
    }

    /** method for inner gameBoard variable
     * @return gameBoard by value as 2D int array
     */
    public int[][] getInnerGameBoardArray(){
        return Arrays.copyOf(gameBoard, gameBoard.length);
    }

    /**
     * @param x x coordinate
     * @param y y coordinate
     */
    public void set(int x, int y, int val) {
        movesListisCurrent = false;
        if (x > 0 && y > 0 && x < 11 && y < 11) {
            gameBoard[x][y] = val;
        }
    }

    /**
     * @param x  x coordinate
     * @param y  y coordinate
     * @return value of gameBoard at x, y. -1 if out of bounds.
     */
    public int get(int x, int y) {
        if (x > 0 && y > 0 && x < 11 && y < 11)
            return gameBoard[x][y];
        else
            return -1;
    }

    /**
     * @param Coordinates Coordinates of tile to be returned
     * @return game gameBoard value at x, y. -1 if out of bounds.
     */
    public int get(int[] Coordinates) {
        return this.get(Coordinates[0], Coordinates[1]);
    }

    /**
     * @param x  x coordinate
     * @param y  y coordinate
     * @return list of coordinate for tiles
     */
    public ArrayList<int[]> nearTiles(int x, int y) {
        ArrayList<int[]> proximateTiles = new ArrayList<>();
        for (int yi = -1; yi <= 1; yi++)
            for (int xi = -1; xi <= 1; xi++)
                if (this.get(x + xi, y + yi) != -1 && !(xi == 0 && yi == 0))
                    proximateTiles.add(new int[] { x + xi, y + yi});
        return proximateTiles;
    }
    /**
     * @param Coordinates Coordinates of object to be returned
     * @return list of Coordinates for tiles
     */
    public ArrayList<int[]> nearTiles(int[] Coordinates) {
        return nearTiles(Coordinates[0], Coordinates[1]);
    }

    /**
     * @param team Team number
     * @return ArrayList of coordinates x, y for queens for team number above
     */
    public ArrayList<int[]> getQueenCoordinates(int team){
        ArrayList<int[]> queenLocations = new ArrayList<>(4);

        for (int y = 0; y < gameBoard.length; y++)
            for (int x = 0; x < gameBoard[y].length; x++)
                if (gameBoard[x][y] == team)
                    queenLocations.add(new int[] {x, y});

        return queenLocations;
    }

    /**
     * @param gameBoard 1D ArrayList<Integer>
     * @return gameBoard configuration as 2D int array
     */
    public static int[][] convertTo2DArray(ArrayList<Integer> gameBoard){
        int[][] newGameBoard = new int[11][11];

        for (int y = 1; y < 11; y++)
            for (int x = 0; x < 11; x++)
                newGameBoard[x][11 - y] = gameBoard.get(y*11 + x);

        for (int x = 0; x < 11; x++)
            newGameBoard[x][0] = gameBoard.get(x);

        return newGameBoard;
    }

    /** Updates gameBoard with moves
     * @param qx1 starting queen x position
     * @param qy1 starting queen y position
     * @param qx2 New queen x position
     * @param qy2 New queen y position
     * @param ax Arrow position
     */
    public void movePiece(int[] move) {
        movesListisCurrent = false;
        move = Arrays.copyOf(move, move.length);
        if (move[0] <= 10 && move[0] > 0 && move[1] <= 10 && move[1] > 0) {
            int queen = gameBoard[move[0]][move[1]];
            gameBoard[move[0]][move[1]] = 0;
            gameBoard[move[2]][move[3]] = queen;
            gameBoard[move[4]][move[5]] = 3;

			System.out.println("Move made");
        }

		System.err.println("Move not made");
    }

    /** Updates gameBoard with moves
     * @param qx1 starting queen x position
     * @param qy1 starting queen y position
     * @param qx2 New queen x position
     * @param qy2 New queen y position
     * @param ax Arrow position
     */
    public void movePieceFast(int[] action) {
        movesListisCurrent = false;
        int queen = gameBoard[action[0]][action[1]];
        gameBoard[action[0]][action[1]] = 0;
        gameBoard[action[2]][action[3]] = queen;
        gameBoard[action[4]][action[5]] = 3;
    }

    /**
     * @param none
     * @return string that represents gameBoard. 1 = black, 2 = white, 3 = arrow
     */
    public String toString() {
        StringBuilder gameBoardString = new StringBuilder();
        for(int y = 1; y < 11; y++) {
            for(int x = 1; x < 11; x++)
                gameBoardString.append(gameBoard[x][y]);
            gameBoardString.append("\n");
        }
        return gameBoardString.toString();
    }

    /**
     * * @param team the team to make the next move
     * @return list of int arrays of length 6, { qx1, qy1, qx2, qy2, ax, ay }
     */
    public ArrayList<int[]> getAllPossibleMoves(int team){
        if (movesListisCurrent)
            return movesList;

        movesList = new ArrayList<>();
        ArrayList<int[]> allQueenPositions = this.getQueenCoordinates(team);
        for (int[] curQueenPosition : allQueenPositions) {
            ArrayList<int[]> allMovesForCurrentQueen = getAllPossibleMovesHelper(curQueenPosition[0], curQueenPosition[1]);
            for (int[] potentialMoveForCurQueen : allMovesForCurrentQueen) {
                int temp = this.get(curQueenPosition[0], curQueenPosition[1]);
                this.set(curQueenPosition[0], curQueenPosition[1], 0);
                ArrayList<int[]> allArrowsForCurrentMove = getAllPossibleMovesHelper(potentialMoveForCurQueen[0], potentialMoveForCurQueen[1]);
                this.set(curQueenPosition[0], curQueenPosition[1], temp);
                for (int[] arrow : allArrowsForCurrentMove)
                    movesList.add(new int[] { curQueenPosition[0], curQueenPosition[1], potentialMoveForCurQueen[0], potentialMoveForCurQueen[1], arrow[0], arrow[1] });
            }
        }
        return movesList;
    }

    /**
     * @param x tiles on x-axis
     * @param y tiles on y-axis
     * @return list of tiles that can be attained in a straight line
     */
    public ArrayList<int[]> getAllPossibleMovesHelper(int x, int y){
        ArrayList<int[]> list = new ArrayList<>();
        list.addAll(getAllPossibleMovesHelperHelper(x, y, 0, -1, new ArrayList<>()));	// Up
        list.addAll(getAllPossibleMovesHelperHelper(x, y, 1, -1, new ArrayList<>()));	// Up and right
        list.addAll(getAllPossibleMovesHelperHelper(x, y, 1, 0, new ArrayList<>()));	// Right
        list.addAll(getAllPossibleMovesHelperHelper(x, y, 1, 1, new ArrayList<>()));	// Down and right
        list.addAll(getAllPossibleMovesHelperHelper(x, y, 0, 1, new ArrayList<>()));	// Down
        list.addAll(getAllPossibleMovesHelperHelper(x, y, -1, 1, new ArrayList<>()));	// Down and left
        list.addAll(getAllPossibleMovesHelperHelper(x, y, -1, 0, new ArrayList<>()));	// Left
        list.addAll(getAllPossibleMovesHelperHelper(x, y, -1, -1, new ArrayList<>()));	// Up and left
        return list;
    }


    /** ArrayList of coordinates reachable from starting position horizontally, vertically, and diagonally
     * @param x		Starting x coordinate
     * @param xInc	increment x coordinate for each step
     * @param y		Starting y coordinate
     * @param yInc	increment y coordinate for each step
     * @param list	empty list
     * @return coordinates
     */
    public ArrayList<int[]> getAllPossibleMovesHelperHelper(int x, int y, int xInc, int yInc, ArrayList<int[]> list){
        if (this.get(x + xInc, y + yInc) == 0) {									// check if empty
            list.add(new int[]{x + xInc, y + yInc});
            return getAllPossibleMovesHelperHelper(x + xInc, y + yInc, xInc, yInc, list);
        }
        else
            return list;
    }

    /**
     * @param qx1 starting queen x position
     * @param qy1 starting queen y position
     * @param qx2 New queen x position
     * @param qy2 New queen y position
     * @param ax Arrow position
     * @return array of valid moves
     * {{isValid, null, null, null}
     * {isQueenMoveValid, didntMove, notDiagonalHorizontalOrVertical, isObstructed, isOutOfBounds, queenDoesNotExist, queenIsAnArrow, queenIsNotYours}
     * {isArrowShotLegit, didntMove, notDiagonalHorizontalOrVertical, isObstructed, isOutOfBounds, null, null, null}}
     */
    public boolean[][] isValidMove(int qx1, int qy1, int qx2, int qy2, int ax, int ay, int team) {
        int otherTeam = (team == 1 ? 2 : 1);
        boolean[][] validMoveArray = new boolean[3][8];
        boolean[] validMoveArrayForQueen = isValidMoveHelper(qx1, qy1, qx2, qy2);
        int temp = this.get(qx1, qy1); //Temporarily save qx1, qy1 blank so that arrow considers it empty
        this.set(qx1, qy1, 0); // Clear qx1, qy1
        boolean[] validMoveArrayForArrow = isValidMoveHelper(qx2, qy2, ax, ay);
        this.set(qx1, qy1, temp); // Add back qx1, qy1

        if (this.get(qx1, qy1) == 0) {
            validMoveArray[1][5] = true;					//queenDoesNotExist
            validMoveArrayForQueen[0] = false;			//queen move not legit
        }
        if (this.get(qx1, qy1) == 3) {
            validMoveArray[1][6] = true;					//queenIsAnArrow
            validMoveArrayForQueen[0] = false;			//queen move not legit
        }
        if (this.get(qx1, qy1) == otherTeam) {
            validMoveArray[1][7] = true;					//queenIsNotYours
            validMoveArrayForQueen[0] = false;			//queen move not legit
        }
        validMoveArray[0][0] = validMoveArrayForQueen[0] && validMoveArrayForArrow[0];	//isValid
        validMoveArray[1][0] = validMoveArrayForQueen[0];	//isQueenMoveValid
        validMoveArray[1][1] = validMoveArrayForQueen[1];	//didntMove
        validMoveArray[1][2] = validMoveArrayForQueen[2];	//notDiagonalHorizontalOrVertical
        validMoveArray[1][3] = validMoveArrayForQueen[3];	//isObstructed
        validMoveArray[1][4] = validMoveArrayForQueen[4];	//isOutOfBounds
        validMoveArray[2][0] = validMoveArrayForArrow[0];	//isArrowShotLegit
        validMoveArray[2][1] = validMoveArrayForArrow[1];	//didntMove
        validMoveArray[2][2] = validMoveArrayForArrow[2];	//notDiagonalHorizontalOrVertical
        // queen's move has to be valid for arrow's path to be scanned for blockages
        //if (validMoveArray[1][0])
        validMoveArray[2][3] = validMoveArrayForArrow[3];	//isObstructed
        validMoveArray[2][4] = validMoveArrayForArrow[4];	//isOutOfBounds

        return validMoveArray;
    }

    /**
     * @param x1    x-axis tile
     * @param y1    y-axis tile
     * @param x2    new x-axis tile
     * @param y2    new y-axis tile
     * @return The array of truth, with elements as follows: {{isValid, didntMove, notDiagonalHorizontalOrVertical, isObstructed, isOutOfBounds}}
     */
    public boolean[] isValidMoveHelper(int x1, int y1, int x2, int y2) {
        boolean[] validMoveArray = new boolean[5];
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        int deltaXAbs = Math.abs(deltaX);
        int deltaYAbs = Math.abs(deltaY);

        if (	1 > x1 || x1 > 10 &&
                1 > y1 || y1 > 10 &&
                1 > x2 || x2 > 10 &&
                1 > y2 || y2 > 10) {
            validMoveArray[0] = false; //sets isValid false
            validMoveArray[4] = true; //sets isOutOfBounds true
            return validMoveArray;
        }

        //if deltaX = deltaY then the move must be diagonal
        //if only one equals 0 then horizontal/vertical move
        //if BOTH equal zero then it's illegal
        boolean didntMove = (deltaXAbs == 0 && deltaYAbs == 0);
        if (didntMove) {
            validMoveArray[0] = false;	//set isValid
            validMoveArray[1] = true;		//set didntMove
            return validMoveArray;
        }
        boolean isDiagonal = deltaXAbs == deltaYAbs;
        boolean isHorizontalOrVertical = (deltaXAbs == 0 ^ deltaYAbs == 0);
        if (!isDiagonal && !isHorizontalOrVertical) {
            validMoveArray[0] = false; //sets isValid false
            validMoveArray[2] = true; //sets notDiagonalHorizontalOrVertical true
            return validMoveArray;
        }

        int xStep, yStep;
        if (isDiagonal) {
            xStep = deltaX/deltaXAbs;
            yStep = deltaY/deltaYAbs;
        }
        else {
            if (deltaX == 0) {
                xStep = deltaX;
                yStep = deltaY/Math.abs(deltaY);
            }
            else {
                yStep = deltaY;
                xStep = deltaX/Math.abs(deltaX);
            }
        }

        if (Math.abs(xStep) > 1 || Math.abs(yStep) > 1) {
            System.err.println("Cannot check if move is Valid!");
            validMoveArray[0] = true;		//set isValid
            validMoveArray[1] = false;	//set didntMove
            validMoveArray[2] = false;	//set notDiagonalHorizontalOrVertical
            validMoveArray[3] = false;	//set isObstructed
            validMoveArray[4] = false;	//set isOutOfBounds
            return validMoveArray;
        }

        int iteration = 0;
        while (x1 + xStep*iteration != x2 || y1 + yStep*iteration != y2) {
            iteration++;
            if (this.get(x1 + xStep*iteration, y1 + yStep*iteration) != 0) {
                // obstruction encountered between (x1, y1) and (x2,y2)
                validMoveArray[0] = false;
                validMoveArray[3] = true; //set isObstructed true
                return validMoveArray;
            }
        }


        validMoveArray[0] = true;		//set isValid
        validMoveArray[1] = false;	//set didntMove
        validMoveArray[2] = false;	//set notDiagonalHorizontalOrVertical
        validMoveArray[3] = false;	//set isObstructed
        validMoveArray[4] = false;	//set isOutOfBounds
        return validMoveArray;
    }
}
