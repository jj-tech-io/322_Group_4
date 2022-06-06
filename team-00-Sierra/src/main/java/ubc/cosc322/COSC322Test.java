
package ubc.cosc322;

import java.util.ArrayList;

import java.util.Map;

import ygraph.ai.smartfox.games.BaseGameGUI;
import ygraph.ai.smartfox.games.GameClient;
import ygraph.ai.smartfox.games.GameMessage;
import ygraph.ai.smartfox.games.GamePlayer;
import ygraph.ai.smartfox.games.amazons.AmazonsGameMessage;

/**
 * An example illustrating how to implement a GamePlayer
 * @author Yong Gao (yong.gao@ubc.ca)
 * Jan 5, 2021
 *
 */
public class COSC322Test extends GamePlayer{

    private GameClient gameClient = null;
    private final BaseGameGUI gamegui;
    private GameBoard GameBoard = null;

    private int myTeam;
    private int enemyTeam;
	private final boolean MONTE_CARLO;

	{
		MONTE_CARLO = false;
	}

	private String userName;
    private final String passwd;
 
	
    /**
     * The main method
     * @param args for name and passwd (current, any string would work)
     */
    public static void main(String[] args) {				 
    	COSC322Test player = new COSC322Test(args[0], args[1]);
    	
    	if(player.getGameGUI() == null) {
    		player.Go();
    	} else {
    		BaseGameGUI.sys_setup();
            java.awt.EventQueue.invokeLater(player::Go);
    	}
    }
	
    /**
     * Any name and passwd 
     * @param userName chosen username
      * @param passwd chosen password
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
    	System.out.println("Congratulations!!! "
    			+ "I am called because the server indicated that the login is successfully");
    	System.out.println("The next step is to find a room and join it: "
    			+ "the gameClient instance created in my constructor knows how!");
    	GameBoard = new GameBoard();
    	userName = gameClient.getUserName();
    
//    List<Room> rooms = this.gameClient.getRoomList();
//    for (Room room : rooms) {
//    	System.out.println(room);	
//    }
//    this.gameClient.joinRoom(rooms.get(0).getName());
//    gameClient.sendTextMessage("hello");
//    }
    	if(gamegui != null) {
    		gamegui.setRoomInformation(gameClient.getRoomList());
    	}
    }
  
    @Override
    public boolean handleGameMessage(String messageType, Map<String, Object> msgDetails) {
    	//This method will be called by the GameClient when it receives a game-related message
    	//from the server.
  
	
    	//For a detailed description of the message types and format, 
    	//see the method GamePlayer.handleGameMessage() in the game-client-api document. 
    	//System.out.println(messageType);
    	//System.out.println(msgDetails);
		if (messageType.equalsIgnoreCase(GameMessage.GAME_ACTION_MOVE)) {
	    	System.out.println("\nGame action move message:\n");

			// Find enemy's move
			ArrayList<Integer> queenPos = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.QUEEN_POS_CURR);
			ArrayList<Integer> queenPosNew = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.QUEEN_POS_NEXT);
			ArrayList<Integer> arrowPos = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.ARROW_POS);

			// Enemy move
			System.out.println("Enemy Queen position: [y:" + queenPos.get(0) + ", x:" + queenPos.get(1) + "]");
			System.out.println("Enemy Queen new position: [y:" + queenPosNew.get(0) + ", x:" + queenPosNew.get(1) + "]");
			System.out.println("Enemy Arrow position: [y:" + arrowPos.get(0) + ", x:" + arrowPos.get(1) + "]");

			// Checking for valid moves
			boolean[][] isValidMove = GameBoard.isValidMove(queenPos.get(1), 11 - queenPos.get(0), queenPosNew.get(1), 11 - queenPosNew.get(0), arrowPos.get(1), 11 - arrowPos.get(0), enemyTeam);
			if (!isValidMove[0][0]) {
				for (int i = 0; i < 10; i ++) {
					System.out.println("Legal Move");
				}
				System.out.println("\nIllegal move");
				System.out.println("Enemy Queen position: [y:" + queenPos.get(0) + ", x:" + queenPos.get(1) + "]");
				System.out.println("Enemy Queen new position: [y:" + queenPosNew.get(0) + ", x:" + queenPosNew.get(1) + "]");
				System.out.println("Enemy Arrow position: [y:" + arrowPos.get(0) + ", x:" + arrowPos.get(1) + "]");
				System.out.println("Before last move: ");
				System.out.println(GameBoard.toString());
				System.out.println("Invalid due to ");
				int brokenRules = 0;
				if (isValidMove[1][1])
					System.out.println(++brokenRules + " Queen didn't move.");
				if (isValidMove[1][2])
					System.out.println(++brokenRules + " Queen did not go horizontal, diagonal, or vertical.");
				if (isValidMove[1][3])
					System.out.println(++brokenRules + " Queen was blocked.");
				if (isValidMove[1][4])
					System.out.println(++brokenRules + " Queen position is not inside the GameBoard.");
				if (isValidMove[1][5])
					System.out.println(++brokenRules + " The queen to be moved is not there.");
				if (isValidMove[1][6])
					System.out.println(++brokenRules + " The queen to be moved is an arrow.");
				if (isValidMove[1][7])
					System.out.println(++brokenRules + " The queen to be moved does not belong to the player who tried to move it.");
				if (isValidMove[2][1])
					System.out.println(++brokenRules + " Arrow was placed at queen's position.");
				if (isValidMove[2][2])
					System.out.println(++brokenRules + " Arrow's move was not diagonal, horizontal, or vertical.");
				if (isValidMove[2][3])
					System.out.println(++brokenRules + " Arrow's path was blocked.");
				if (isValidMove[2][4])
					System.out.println(++brokenRules + " Arrow is out of the GameBoard.");
			}
// Update the GameBoard and GUI with the enemy's move
			int[] fixedMoveInformation = new int[] { queenPos.get(1), 11 - queenPos.get(0), queenPosNew.get(1), 11 - queenPosNew.get(0), arrowPos.get(1), 11 - arrowPos.get(0)};
			GameBoard.movePiece(fixedMoveInformation);
			gamegui.updateGameState(queenPos, queenPosNew, arrowPos);

			//Updates our GUI with enemy invalid move
			if (!isValidMove[0][0])
				return false;

			// Print out enemy move
			System.out.println("\n\nEnemy player made a move\n");
			System.out.println(GameBoard.toString());

			// After enemy makes a move, it is our turn
			makeMove();
		}

		else if (messageType.equalsIgnoreCase(GameMessage.GAME_STATE_BOARD)) {
			System.out.println("\nGame state GameBoard message: \n");

			// Current game state
			ArrayList<Integer> gamestate = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.GAME_STATE);

			// Set the GameBoard and GUI to beginning game state
			GameBoard.setGameBoard(gamestate);
			gamegui.setGameState(gamestate);

			// Print out beginning GameBoard
			System.out.println("\nInitial GameBoard: \n");
			System.out.println(GameBoard.toString());
		}

		else if (messageType.equalsIgnoreCase(GameMessage.GAME_ACTION_START)) {
			System.out.println("\nGame action start message:\n");

			// Get usernames of the black or white players
			String blackUsername = (String)msgDetails.get(AmazonsGameMessage.PLAYER_BLACK);
			String whiteUsername = (String)msgDetails.get(AmazonsGameMessage.PLAYER_WHITE);

			// Which teams are which
			System.out.println("Black team: " + blackUsername);
			System.out.println("White team: " + whiteUsername);


			// Which team are we on
			if (blackUsername.equalsIgnoreCase(userName)) {
				myTeam = 1;
				enemyTeam = 2;
			}
			else if (whiteUsername.equalsIgnoreCase(userName)) {
				enemyTeam = 1;
				myTeam = 2;
			}
			else {
				System.err.println("Error!! Our username matched neither black or white player.");
				return false;
			}

			// Confirm the team
			System.out.println("We are on team " + (myTeam == 1? "Black" : "White"));
			System.out.println("Team number: " + myTeam);

			// If Black team, we start, if not enemy starts
			if(myTeam == 1)
				makeMove();
		}

		return true;
	}
//			switch(messageType) {
//    	case GameMessage.GAME_STATE_GameBoard:
//    		this.gamegui.setGameState((ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.GAME_STATE));
//    		break;
//    	case GameMessage.GAME_ACTION_MOVE:
//    		this.gamegui.updateGameState(msgDetails);
//    		break;
//    	default:
//    		break;
//    	}
//    	return true;
//    }

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
		return  gamegui;
	}

	@Override
	public void connect() {
		// TODO Auto-generated method stub
    	gameClient = new GameClient(userName, passwd, this);			
	}

	public void makeMove() {
		// MonteCarloMoveGenerator AI
		MonteCarloMoveGenerator moveGen = new MonteCarloMoveGenerator(myTeam);
		int[] move = moveGen.monteCarloTreeSearch(new GameState(GameBoard));

		//Randomized game estimates how many moves left
		double movesLeft = moveGen.estimateMovesLeft(new GameState(GameBoard));
		System.out.println("Estimated moves left: " + movesLeft);
		System.out.println("Tiles available to us: " + MiniMaxMoveGenerator.heuristic3(GameBoard, myTeam, enemyTeam));
		System.out.println("Tiles available to them: " + MiniMaxMoveGenerator.heuristic3(GameBoard, enemyTeam, myTeam));

		// Minimax or Monte Carlo
		// MiniMax, then Monte Carlo when there are less than minimax moves left
		int MINIMAX = 60;
		if (movesLeft > MINIMAX && !MONTE_CARLO)
			move = MiniMaxMoveGenerator.getMove(new GameState(GameBoard), myTeam);
		else
			move = moveGen.monteCarloTreeSearch(new GameState(GameBoard));

		// Send play to server, update GameBoard with move
		sendPlay(move);
		GameBoard.movePiece(move);

		// Print move and what move it was
		System.out.println("\nMove made by my Team:\n");
		System.out.println("Team " + (myTeam == 1 ? "Black" : "White"));
		System.out.println(GameBoard.toString());
		System.out.println("Queen position: [y:" + (11 - move[1]) + ", x:" + move[0] + "]");
		System.out.println("New queen position: [y:" + (11 - move[3]) + ", x:" + move[2] + "]");
		System.out.println("Arrow position: [y:" + (11 - move[5]) + ", x:" + move[4] + "]");
	}

	public void sendPlay(int[] move) {
		ArrayList<Integer> queenStart = new ArrayList<>();
		queenStart.add(11 - move[1]);
		queenStart.add(move[0]);

		ArrayList<Integer> queenEnd = new ArrayList<>();
		queenEnd.add(11 - move[3]);
		queenEnd.add(move[2]);

		ArrayList<Integer> arrow = new ArrayList<>();
		arrow.add(11 - move[5]);
		arrow.add(move[4]);

		this.gameClient.sendMoveMessage(queenStart, queenEnd, arrow);
		this.gamegui.updateGameState(queenStart, queenEnd, arrow);
	}
 
}//end of class
