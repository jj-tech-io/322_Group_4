
package ubc.cosc322;

import java.util.*;

import sfs2x.client.entities.Room;
import ygraph.ai.smartfox.games.*;
import ygraph.ai.smartfox.games.amazons.AmazonsGameMessage;


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


    private GameClient gameClient = null;
    private BaseGameGUI gamegui = null;

    private String userName = null;
    private String passwd = null;
    public static ArrayList<Integer> gameBoardCurrent1D = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> gameBoardCurrent2D = new ArrayList<>();

    public static GameBoard gB = new GameBoard();
    public String gameMsg;
    public Map<String,Object> gameStateBoard = new HashMap<>();

    private boolean isWhite;

    private ArrayList<Integer> nextMove = null;
    private int score = 0;

    char ourAmazon;
    int ours;
    char otherAmazon;
    int theirs;
    int gameStage = 0;


    private ArrayList<Integer>  QUEEN_CURRENT=  new ArrayList<>();
    private ArrayList<Integer>  ARROW_CURRENT=  new ArrayList<>();
    private ArrayList<Integer>  QUEEN_NEXT=  new ArrayList<>();
    private ArrayList<Integer>  ARROW_NEXT =  new ArrayList<>();
    public static COSC322Test player;
    private int[][] twodim = new int[][] {
            {0,0,0,2,0,0,2,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {2,0,0,0,0,0,0,0,0,2},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0,0,0,1},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,1,0,0,1,0,0,0}
    };
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

        switch (messageType) {
            case(GameMessage.GAME_STATE_BOARD):
                System.out.println("updated gamestae");
                gameBoardCurrent1D = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.GAME_STATE);
//                gB.printBoard(gameBoardCurrent1D);
                this.gamegui.setGameState(gameBoardCurrent1D);
                System.out.println(gameBoardCurrent1D);
                break;
            case (GameMessage.GAME_ACTION_MOVE):
                this.gamegui.updateGameState(msgDetails);

                //this.gameBoardCurrent1D = (ArrayList<Integer>)msgDetails.get(AmazonsGameMessage.GAME_STATE);
               // System.out.println((ArrayList<Integer>)msgDetails.get(AmazonsGameMessage.GAME_STATE));
                //System.out.println("msgDetails: game state board" +msgDetails.get(AmazonsGameMessage.GAME_STATE_BOARD));
                this.QUEEN_CURRENT = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.QUEEN_POS_CURR);
                this.QUEEN_NEXT = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.QUEEN_POS_NEXT);
                this.ARROW_NEXT = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.ARROW_POS);
                //System.out.println(this.QUEEN_CURRENT +" "+this.QUEEN_NEXT+" "+this.ARROW_NEXT);
                //
                if(this.ARROW_NEXT == null) {
                    //gB.updateBoard(otherAmazon,this.QUEEN_CURRENT,this.QUEEN_NEXT,this.ARROW_CURRENT);

                }
                else {

                    gB.updateBoard(otherAmazon,this.QUEEN_CURRENT,this.QUEEN_NEXT,this.ARROW_NEXT);
                    makeMove();
                    gameStage++;
                }

                System.out.println("done move");


                break;
            case (GameMessage.GAME_ACTION_START):

                if(player.isWhite) {
                    ourAmazon = 'w';
                    otherAmazon = 'b';
                    ours = 1;
                    theirs = 2;
                    makeMove();
                }
                else {
                    ourAmazon = 'b';
                    otherAmazon = 'w';
                    ours = 2;
                    theirs = 1;
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
//    public void updateBoards(char player, ArrayList<Integer> qC,ArrayList<Integer> qN,ArrayList<Integer> aR) {
////        gB = new GameBoard(gameBoardCurrent1D);
//       // System.out.println(gameBoardCurrent1D);
//        gB.updateBoard(player,qC,qN,aR);
//        gB.printBoard();
//        //gameBoardCurrent1D =mb.updateBoard(gameBoardCurrent1D,player,qC,qN,aR);
//        //gameBoardCurrent1D = gB.makeMove(player,qC,qN,aR, gameBoardCurrent1D);
//
//
//    }
//ArrayList<Integer> gameBoard,int queen, int x,int y
    public int qCY = 1;

    public void opponentMove() {

    }
    public void makeMove() {
        System.out.println("number of steps: "+ gameStage);
        ArrayList<Integer> from = new ArrayList<>();
        HashMap<ArrayList<Integer>, int[][]> moves = new HashMap<>();
        ArrayList<ArrayList<Integer>> arrows = new ArrayList<>();
        ArrayList<Integer> queen = new ArrayList<>();
        ArrayList<Integer> arrow = new ArrayList<>();
        ArrayList<Integer> qC = new ArrayList<>();
        ArrayList<Integer> qN = new ArrayList<>();
        ArrayList<Integer> aR = new ArrayList<>();
        Node curNode = new Node(gB,ours);
        ArrayList<ArrayList<Integer>> qc_qn_ar = MinMax.getOptimal(ours,theirs,true,curNode);
        System.out.println(qc_qn_ar);
        qC= qc_qn_ar.get(0);
        qN = qc_qn_ar.get(1);
        aR = qc_qn_ar.get(2);

        //moves = gB.getMoves(ours,curNode);
//        System.out.println(moves);
//        qN = moves.get(0);
//        qC = moves.get(1);
//        aR = gB.getArrow(curNode,qN);
//

        qN = gB.undoXY(qN);
        qC = gB.undoXY(qC);
        aR = gB.undoXY(aR);
        gB.updateBoard(ourAmazon, qC, qN, aR);
        System.out.println(gB.getScore(1));
        System.out.println(gB.getScore(2));
        this.gamegui.updateGameState(qC, qN, aR);
        this.gameClient.sendMoveMessage(qC, qN, aR);



        System.out.println("qC: " + qC);
        System.out.println("qN: " + qN);
        System.out.println("aR: " + aR);
//        }




    }







}
