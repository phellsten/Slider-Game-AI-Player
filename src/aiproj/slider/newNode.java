package aiproj.slider;

import java.util.ArrayList;

public class newNode {
	public int value;
	public ArrayList <Move> moves;
	public ArrayList <newNode> childNodes;
	newNode()
	{
		moves = new ArrayList<Move>();
		childNodes = new ArrayList<newNode>();
	}
}
