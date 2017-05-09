package aima.core.search.adversarial;

/**
 * @author Ravi Mohan
 * 
 */
public class GameAgent extends AbstractAgent {
	private Game game;

	public GameAgent(Game g) {
		this.game = g;
	}

	public void makeMiniMaxMove() {
		game.makeMiniMaxMove();
	}

	public void makeAlphaBetaMove() {
		game.makeAlphaBetaMove();
	}

}