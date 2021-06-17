import java.util.Deque;
import java.util.LinkedList;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private final SearchNode solutionNode;

    private class SearchNode implements Comparable<SearchNode> {

        public final Board board;
        public final SearchNode previousNode;
        public final int moves;
        public final int priority;

        public SearchNode(Board board, SearchNode previousNode) {
            this.board = board;
            this.previousNode = previousNode;
            this.moves = (previousNode == null) ? 0 : previousNode.moves + 1;
            this.priority = board.manhattan() + moves;
        }

        public void insertNeighbors(MinPQ<SearchNode> pq) {
            for (Board neighbor : board.neighbors()) {
                if (previousNode != null && neighbor.equals(previousNode.board)) continue;
                SearchNode node = new SearchNode(neighbor, this);
                pq.insert(node);
            }
        }

        public int compareTo(SearchNode other) {
            return this.priority - other.priority;
        }

    }

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        MinPQ<SearchNode> solutionPQ = new MinPQ<>();
        MinPQ<SearchNode> twinPQ = new MinPQ<>();

        SearchNode initialNode = new SearchNode(initial, null);
        solutionPQ.insert(initialNode);

        SearchNode initialTwinNode = new SearchNode(initial.twin(), null);
        twinPQ.insert(initialTwinNode);

        while (true) {
            SearchNode solNode = solutionPQ.delMin();
            SearchNode twinNode = twinPQ.delMin();

            if (solNode.board.isGoal()) {
                this.solutionNode = solNode;
                break;
            } else if (twinNode.board.isGoal()) {
                this.solutionNode = null;
                break;
            }

            solNode.insertNeighbors(solutionPQ);
            twinNode.insertNeighbors(twinPQ);
        }
    }

    public boolean isSolvable() {
        return solutionNode != null;
    }

    public int moves() {

        if (isSolvable()) {
            return solutionNode.moves;
        } else {
            return -1;
        }

    }

    public Iterable<Board> solution() {
        if (isSolvable()) {
            Deque<Board> solution = new LinkedList<>();
            SearchNode nextNode = solutionNode;
            while (nextNode != null) {
                solution.addFirst(nextNode.board);
                nextNode = nextNode.previousNode;
            }

            return solution;
        } else {
            return null;
        }
    }
}