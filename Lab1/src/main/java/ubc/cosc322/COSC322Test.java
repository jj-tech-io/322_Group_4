
package ubc.cosc322;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import sfs2x.client.entities.Room;
import ygraph.ai.smartfox.games.*;
import ygraph.ai.smartfox.games.amazons.AmazonsBoard;
import ygraph.ai.smartfox.games.amazons.AmazonsGameMessage;
import ygraph.ai.smartfox.games.amazons.HumanPlayer;

import javax.swing.*;


/**
 *
 *
 *
 *
 * An example illustrating how to implement a GamePlayer
 * @author Yong Gao (yong.gao@ubc.ca)
 * Jan 5, 2021
 *
 */
public class COSC322Test<Rooms> extends GamePlayer {
    public static  int[][] boardCurrent = new int[11][11];
    public static int[][] boardNext = new int[11][11];
    int count = 0;
    int count2=0;
    private GameClient gameClient = null;
    private BaseGameGUI gamegui = null;

    private String userName = null;
    private String passwd = null;
    public static ArrayList<Integer> gameBoardCurrent1D = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> gameBoardCurrent2D = new ArrayList<>();
//    public Integer[][] gB = new Integer[10][10];
    public static GameBoard gB = new GameBoard();
    public String gameMsg;
    //    private GameRules game = null;
    private AmazonsBoard board = null;
    private boolean isWhite;
    //    private List<Queen> queens = null;
//
//    private Queen currentQueen = null;
    private ArrayList<Integer> nextMove = null;
    private int score = 0;

    char ourAmazon;
    char otherAmazon;
    boolean MYTURN = false;


    private ArrayList<Integer>  QUEEN_CURRENT=  new ArrayList<>();
    private ArrayList<Integer>  ARROW_CURRENT=  new ArrayList<>();
    private ArrayList<Integer>  QUEEN_NEXT=  new ArrayList<>();
    private ArrayList<Integer>  ARROW_NEXT =  new ArrayList<>();
    public static COSC322Test player;

    /**
     * The main method
     *
     * @param args for name and passwd (current, any string would work)
     */
    public static void main(String[] args) {
        System.out.println("args: " + args.toString());
        player = new COSC322Test(args[0], args[1]);

        if (player.getGameGUI() == null) {
            player.Go();

        } else {
            BaseGameGUI.sys_setup();
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    player.Go();
                }
            });
        }

    }

    /**
     * Any name and passwd
     *
     * @param userName
     * @param passwd
     */
    public COSC322Test(String userName, String passwd) {
        this.userName = userName;
        this.passwd = passwd;

        //To make a GUI-based player, create an instance of BaseGameGUI
        //and implement the method getGameGUI() accordingly
        this.gamegui = new BaseGameGUI(this);

    }


    @Override
    public void onLogin() {

        System.out.println("Congratualations!!! "
                + "I am called because the server indicated that the login is successfully");
        System.out.println("The next step is to find a room and join it: "
                + "the gameClient instance created in my constructor knows how!");
        this.userName = gameClient.getUserName();
        if (gamegui != null) {
            gamegui.setRoomInformation(gameClient.getRoomList());
        }
        List<Room> rooms = this.gameClient.getRoomList();
        for (Room room : rooms) {
            System.out.println(room);
        }
        this.gameClient.joinRoom(rooms.get(0).getName());
        gameClient.sendTextMessage("hello");

    }

    @Override
    public boolean handleGameMessage(String messageType, Map<String, Object> msgDetails) {
        //This method will be called by the GameClient when it receives a game-related message
        //from the server.
        System.out.println("handleGameMsg_____");
        System.out.println(messageType);

        System.out.println(msgDetails.get(AmazonsGameMessage.GAME_STATE));;
        switch (messageType) {
            case(GameMessage.GAME_STATE_BOARD):
                gameBoardCurrent1D = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.GAME_STATE);
                this.gamegui.setGameState(gameBoardCurrent1D);
                System.out.println(gameBoardCurrent1D);

                break;
            case (GameMessage.GAME_ACTION_MOVE):
                //this.gameBoardCurrent1D = (ArrayList<Integer>)msgDetails.get(AmazonsGameMessage.GAME_STATE);
               // System.out.println((ArrayList<Integer>)msgDetails.get(AmazonsGameMessage.GAME_STATE));
                System.out.println(msgDetails.get(AmazonsGameMessage.GAME_STATE_BOARD));
                this.gamegui.updateGameState(msgDetails);
                MYTURN = true;
                if(gameBoardCurrent1D != null) {

                    makeMove(gameBoardCurrent1D);
                    System.out.println("done move");
                }
                System.out.println("null ");

                break;
            case (GameMessage.GAME_ACTION_START):

                if(player.isWhite) {
                    ourAmazon = 'w';
                    otherAmazon = 'b';
                }
                else {
                    ourAmazon = 'b';
                    otherAmazon = 'w';
                }
                System.out.println("Our Amazon is" + ourAmazon);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public String userName() {
        return userName;
    }

    @Override
    public GameClient getGameClient() {
        // TODO Auto-generated method stub
        return this.gameClient;
    }

    @Override
    public BaseGameGUI getGameGUI() {
        // TODO Auto-generated method stub
        return this.gamegui;
    }

    @Override
    public void connect() {
        // TODO Auto-generated method stub
        gameClient = new GameClient(userName, passwd, this);
    }
    public void updateBoards(char player, ArrayList<Integer> qC,ArrayList<Integer> qN,ArrayList<Integer> aR) {
//        gB = new GameBoard(gameBoardCurrent1D);
        System.out.println(gameBoardCurrent1D);
        MyBoard mb = new MyBoard();
        gameBoardCurrent1D =mb.updateBoard(gameBoardCurrent1D,player,qC,qN,aR);
        //gameBoardCurrent1D = gB.makeMove(player,qC,qN,aR, gameBoardCurrent1D);
        System.out.println(gameBoardCurrent1D);
    }
//ArrayList<Integer> gameBoard,int queen, int x,int y
    public void makeMove(ArrayList<Integer> gameBoard) {

        ArrayList<Integer> qC = new ArrayList<>();
        qC.add(10);
        qC.add(4+count);
        ArrayList<Integer> qN = new ArrayList<>();
        qN.add(10);
        qN.add(5+count);

//        ArrayList<Integer> qN = gB.makeMove(ourAmazon,1,1,1);
        ArrayList<Integer> aR = new ArrayList<>(Arrays.asList(1, 2));
        System.out.println("qC: "+qC);
        System.out.println("qN: "+qN);
        System.out.println("aR: "+aR);

        player.QUEEN_CURRENT = qC;
        player.QUEEN_NEXT = qN;
        player.ARROW_CURRENT = aR;
        this.gamegui.updateGameState(qC,qN,aR);
        this.gameClient.sendMoveMessage(qC, qN, aR);
        System.out.println(gameBoardCurrent1D);
        gB.printBoard(gameBoardCurrent1D);


        if (MYTURN) {
            updateBoards(ourAmazon,qC,qN,aR);
        }
        else {
            updateBoards(otherAmazon,qC,qN,aR);
        }
        count++;



    }

    public ArrayList<Integer> moveQueen(ArrayList<Integer> cur, String direction, int moves) {
        this.QUEEN_NEXT.clear();
        int x = 0;
        int y = 0;
        if (direction.contains("up")) {
            y = 1;
        }
        this.QUEEN_NEXT.add(1);
        this.QUEEN_NEXT.add(1);
        return this.QUEEN_NEXT;
    }

    private ArrayList<Integer> moveArrow(ArrayList<Integer> arrow_current, String direction, int i) {
        this.ARROW_NEXT.clear();
        int x = 0;
        int y = 0;
        if (direction.contains("up")) {
            y = 1;

        }
        this.ARROW_NEXT.add(this.QUEEN_CURRENT.get(0));
        this.ARROW_NEXT.add(this.QUEEN_CURRENT.get(0) + 2);
        return this.ARROW_NEXT;
    }

    public ArrayList<Integer> getNewState(ArrayList<Integer> gameState) {
        ArrayList<Integer> newState = new ArrayList<>();
        int index = 0;
        boolean update = true;
        for (int i : gameState) {

            if (i == 1) {
                newState.set(index, 0);
                newState.set(index + 2, 1);
                update = false;
            } else {
                newState.add(gameState.get(i));
            }
            index++;
        }
        return newState;
    }
}
