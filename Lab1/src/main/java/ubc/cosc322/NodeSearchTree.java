package ubc.cosc322;

public class NodeSearchTree {
    static int MAX = 1000;
    static int MIN = -1000;
    public static int minimax(int depth, int nodeIndex,
                       Boolean maximizingPlayer,
                       int values[], int alpha,
                       int beta)
    {
        // Terminating condition. i.e
        // leaf node is reached
        if (depth == 3)
            return values[nodeIndex];

        if (maximizingPlayer)
        {
            int best = MIN;

            // Recur for left and
            // right children
            for (int i = 0; i < 2; i++)
            {
                int val = minimax(depth + 1, nodeIndex * 2 + i,
                        false, values, alpha, beta);
                best = Math.max(best, val);
                alpha = Math.max(alpha, best);

                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return best;
        }
        else
        {
            int best = MAX;

            // Recur for left and
            // right children
            for (int i = 0; i < 2; i++)
            {

                int val = minimax(depth + 1, nodeIndex * 2 + i,
                        true, values, alpha, beta);
                best = Math.min(best, val);
                beta = Math.min(beta, best);

                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return nodeIndex;
        }
    }
}
