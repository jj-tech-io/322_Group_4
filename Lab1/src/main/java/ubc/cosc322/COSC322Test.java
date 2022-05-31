
package ubc.cosc322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import sfs2x.client.entities.Room;
import ygraph.ai.smartfox.games.BaseGameGUI;
import ygraph.ai.smartfox.games.GameClient;
import ygraph.ai.smartfox.games.GameMessage;
import ygraph.ai.smartfox.games.GamePlayer;
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
public class COSC322Test<Rooms> extends GamePlayer{

    private GameClient gameClient = null;
    private BaseGameGUI gamegui = null;
	
    private String userName = null;
    private String passwd = null;
    public ArrayList<Integer> gameBoardCurrent1D = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> gameBoardCurrent2D = new ArrayList<>();
    public Integer [][] gB = new Integer[10][10];
    public  String gameMsg;
    private GameRules game = null;
    private GameBoard board = null;
    private boolean isWhite;
    private List<Queen> queens = null;

    private Queen currentQueen = null;
    private ArrayList<Integer> nextMove = null;
    private int score = 0;

    String ourAmazon;
    String otherAmazon;

    /**
     * The main method
     * @param args for name and passwd (current, any string would work)
     */
    public static void main(String[] args) {
        System.out.println("args: "+args.toString());
    	COSC322Test player = new COSC322Test(args[0], args[1]);
    	
    	if(player.getGameGUI() == null) {
    		player.Go();

    	}
    	else {
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
        if(gamegui!= null) {
            gamegui.setRoomInformation(gameClient.getRoomList());
        }
		List<Room> rooms = this.gameClient.getRoomList();
		for(Room room: rooms) {
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
        System.out.println(msgDetails.get(AmazonsGameMessage.GAME_STATE));
//        msgDetails.put(AmazonsGameMessage.GAME_STATE, gameBoardCurrent1D);
        gameBoardCurrent1D = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.GAME_STATE);

//        int [] ggg = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0};
//        int size = 0;
//
//        for(int x : gameBoardCurrent1D) {
//            size++;
//        for(int row = 0; row<10; row++) {
//            for (int col = 0; col <10; col++){
//                gB[row][col]=ggg[row*10+col];
//                size++;
//            }
//
//        }
//
//        }
//        System.out.println("size" +size);

    	//For a detailed description of the message types and format, 
    	//see the method GamePlayer.handleGameMessage() in the game-client-api document.

        switch(messageType) {
            case(GameMessage.GAME_STATE_BOARD):
                this.gamegui.setGameState((ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.GAME_STATE));

                break;
            case (GameMessage.GAME_ACTION_MOVE):
                this.gamegui.updateGameState(msgDetails);
                break;
            case(GameMessage.GAME_ACTION_START):
                System.out.println(this.userName());
                System.out.println(AmazonsGameMessage.PLAYER_WHITE);
                if((msgDetails.get(AmazonsGameMessage.PLAYER_WHITE)).equals(this.userName())){
                    System.out.println("The AI is white");
                    this.isWhite = true;
                    board.setAIColor(true);
                    queens = setTeam(board);
                } else {
                    System.out.println("The AI is black");
                    this.isWhite = false;
                    board.setAIColor(false);
                    queens = setTeam(board);
                    generateMove();
                }

                break;
            default:
                break;
        }
    	    	
    	return true;   	
    }

    private void generateMove() {
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
		return  this.gamegui;
	}

	@Override
	public void connect() {
		// TODO Auto-generated method stub
    	gameClient = new GameClient(userName, passwd, this);			
	}
    public void makeMove(int queen, int move) {
//        msgDetails.get(AmazonsGameMessage.GAME_STATE);
//        updateGameState​(java.util.ArrayList<java.lang.Integer> queenCurrent,
//                java.util.ArrayList<java.lang.Integer> queenNew,
//                java.util.ArrayList<java.lang.Integer> arrow)

        ArrayList<Integer> qC = new ArrayList<>(Arrays.asList(3,7));
        ArrayList<Integer> qN = new ArrayList<>(Arrays.asList(1,5));
        ArrayList<Integer> aR = new ArrayList<>(Arrays.asList(1,2));
        this.gamegui.updateGameState(qC,qN,aR);

    }
    public static int turn = 0;
    public static boolean running = false;
    public void play()  {
        running = true;

        while(running) {

            String player = AmazonsGameMessage.PLAYER_WHITE;
            ArrayList<Integer> qC = new ArrayList<>(Arrays.asList(3,7));
            //makeMove();
        }
    }
 
}//end of class
