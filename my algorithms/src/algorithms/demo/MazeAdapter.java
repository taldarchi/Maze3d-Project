package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;

/**
 * The Class MazeAdapter.
 * object adapter who adapts a maze to be 'searchable'
 */
public class MazeAdapter implements Searchable<Position> {

	/** The maze. */
	private Maze3d maze;

	/**
	 * Instantiates a new maze adapter.
	 *
	 * @param maze the maze
	 */
	public MazeAdapter(Maze3d maze) {
		super();
		this.maze = maze;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getStartState()
	 */
	@Override
	public State<Position> getStartState(){
		State<Position> s=new State <Position>(maze.getStartPosition());
		s.setCost(0);
		s.setCameFrom(null);
		return s;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getGoalState()
	 */
	@Override
	public State<Position> getGoalState() {
		State<Position> s=new State<Position>(maze.getGoalPosition());
		return s;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getAllPossibleStates(algorithms.search.State)
	 */
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s){
		String[]moves=maze.getPossibleMoves(s.getState()); // getting the moves
		ArrayList<State<Position>> possibleStates =new ArrayList<State<Position>>();
		State<Position> t=new State<Position>();
		State<Position> temp=new State<Position>(); //for the clone
		Position pos=new Position();
		for(int i=0;i<moves.length;i++)
		{
			switch(moves[i]){
			case "Up":
				t.setCameFrom(s);
				t.setCost(1);
				try { // cloning the state so it wouldn't change
					pos= (s.getState()).clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				pos.setZ(s.getState().getZ()+2);
				pos.setX(s.getState().getX());
				pos.setY(s.getState().getY());
				t.setCameFromDirection("Up");
				t.setState(pos);
				try { // cloning the state so it wouldn't change
					temp=t.clone();
				} catch (CloneNotSupportedException e1) { 
					e1.printStackTrace();
				}
				possibleStates.add(temp);
				break;
			case "Down":
				t.setCameFrom(s);
				t.setCost(1);
				try {
					pos= (s.getState()).clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				pos.setZ(s.getState().getZ()-2);
				pos.setX(s.getState().getX());
				pos.setY(s.getState().getY());
				t.setCameFromDirection("Down");
				t.setState(pos);
				try {
					temp=t.clone();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				possibleStates.add(temp);
				break;
			case "Left":
				t.setCameFrom(s);
				t.setCost(1);
				try {
					pos= (s.getState()).clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				pos.setZ(s.getState().getZ());
				pos.setX(s.getState().getX());
				pos.setY(s.getState().getY()-2);
				t.setCameFromDirection("Left");
				t.setState(pos);
				try {
					temp=t.clone();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				possibleStates.add(temp);
				break;
			case "Right":
				t.setCameFrom(s);
				t.setCost(1);
				try {
					pos= (s.getState()).clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				pos.setZ(s.getState().getZ());
				pos.setX(s.getState().getX());
				pos.setY(s.getState().getY()+2);
				t.setCameFromDirection("Right");
				t.setState(pos);
				try {
					temp=t.clone();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				possibleStates.add(temp);
				break;
			case "Forward":
				t.setCameFrom(s);
				t.setCost(1);
				try {
					pos= (s.getState()).clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				pos.setZ(s.getState().getZ());
				pos.setX(s.getState().getX()+2);
				pos.setY(s.getState().getY());
				t.setCameFromDirection("Forward");
				t.setState(pos);
				try {
					temp=t.clone();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				possibleStates.add(temp);
				break;
			case "Backwards":
				t.setCameFrom(s);
				t.setCost(1);
				try {
					pos= (s.getState()).clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				pos.setZ(s.getState().getZ());
				pos.setX(s.getState().getX()-2);
				pos.setY(s.getState().getY());
				t.setCameFromDirection("Backwards");
				t.setState(pos);
				try {
					temp=t.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				possibleStates.add(temp);
				break;
			}
		}
		return possibleStates;
	}

}
