package aiproj.slider;

import java.util.ArrayList;
import java.util.LinkedList;

public class DecisionNode {
	// Value calculated by MiniMax
	private int value;

	// Arraylist of Moves which have got us to the current position
	private LinkedList<Move> moves;

	boolean childGenerated;

	// The player who makes the decision for this node
	private String player;
	
	private boolean pruned = false;
	
	public void prune()
	{
		this.pruned = true;
	}

	public String getPlayer() {
		return player;
	}

	/** Constructor for the root node */
	DecisionNode() {
		this.childNodes = new ArrayList<DecisionNode>();
		moves = new LinkedList<Move>();
	}

	/**
	 * Constructor for a child node, with the parent node taken as an arguement
	 */
	DecisionNode(DecisionNode parentNode) {
		this.childNodes = new ArrayList<DecisionNode>();
		parentNode.addChildNode(this);
		moves = new LinkedList<Move>();
		moves.addAll(parentNode.moves);

	}

	// The children of the node
	ArrayList<DecisionNode> childNodes;

	/** Get Child nodes */
	public ArrayList<DecisionNode> getChildNodes() {
		return childNodes;
	}

	public void setChildGenerated() {
		this.childGenerated = true;
	}

	public boolean hasChildBeenGenerated() {
		return childGenerated;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public LinkedList<Move> getMoves() {
		return moves;
	}

	public void addMove(Move move) {
		this.moves.add(move);
	}

	/** Adds in a new child node */
	public void addChildNode(DecisionNode nde) {
		childNodes.add(nde);
	}
}
