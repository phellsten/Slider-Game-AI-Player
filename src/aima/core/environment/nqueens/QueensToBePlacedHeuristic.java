package aima.core.environment.nqueens;

/**
 * @author Ravi Mohan
 * 
 */
public class QueensToBePlacedHeuristic implements HeuristicFunction {

	public double h(Object state) {
		NQueensBoard board = (NQueensBoard) state;
		return board.size - board.getNumberOfQueensOnBoard();
	}
}