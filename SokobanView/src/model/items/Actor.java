package model.items;

import model.data.Position;

public class Actor extends Moveable_Item {
public Actor() {
	super();
}
	public Actor(Position p) {
		super(p);
		
	}
	//מייצג בקובץ טקסט
	public char getChar()
	{
		return 'A';
	}
	@Override
	public char getFirstChar()
	{
		return 'A';
	}




}
