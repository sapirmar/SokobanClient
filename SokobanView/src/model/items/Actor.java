package model.items;

import model.data.Position;

public class Actor extends Moveable_Item {
public Actor() {
	super();
}
	public Actor(Position p) {
		super(p);
		
	}
	public char getChar()
	{
		return 'A';
	}



}
