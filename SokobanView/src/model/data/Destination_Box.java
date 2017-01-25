package model.data;
public class Destination_Box extends Items {
	
	
public Destination_Box() {
	super();
	
}
	public Destination_Box(Position p) {
		super(p);
		p.setFlagDestination(true);
	}
	public char getChar()
	{
		return 'o';
	}


}
