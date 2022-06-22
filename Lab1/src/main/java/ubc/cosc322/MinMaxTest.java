//package ubc.cosc322;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static ubc.cosc322.MinMax.getOptimal;
//
//public class MinMaxTest {
//
//    public static void main(String [] args) {
//        GameBoard g = new GameBoard();
//        Node n = new Node(2,g);
//        System.out.println("makeMove(): ");
//        System.out.println(g.boardString());
//
//        ArrayList<Integer> qC = new ArrayList<>();
//        ArrayList<Integer> qN = new ArrayList<>();
//        ArrayList<Integer> aR = new ArrayList<>();
//        List<Boolean> valid = new ArrayList<>();
//        qC.add(0);
//        qC.add(3);
//        qN.add(3);
//        qN.add(4);
//        aR.add(2);
//        aR.add(3);
//        g.updateBoard(false,2,qC,qN,aR);
//
//        qC.add(0);
//        qC.add(6);
//        qN.add(5);
//        qN.add(6);
//        aR.add(1);
//        aR.add(2);
//        g.updateBoard(false,1,qC,qN,aR);
//        Node optimal = MinMax.getNextLevel(1,n, g, false, true);
//        System.out.println(optimal);
////        for(Node i : states) {
////            System.out.println("Score /// Moves");
////            System.out.println(i.score + " " +i.moveFromParentNode);
////        }
//
//
////
////        qC.add(0);
////        qC.add(3);
////        qN.add(1);
////        qN.add(4);
////        aR.add(2);
////        aR.add(5);
////        g.getQueenMoves(2);
////        List<Boolean> vm = g.validateMove(qC, qN, aR);
////        System.out.println(vm);
////        g.updateBoard(false, 2, qC, qN, aR);
////        g.getScore(2);
////        System.out.println(g.boardString());
////        g.getQueenMoves(2);
////
////
////        System.out.println("-----queen list---");
////        System.out.println(g.getQueenMoves(2));
////
//
//    }
//}