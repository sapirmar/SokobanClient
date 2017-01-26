package model.data;
public class Box extends Moveable_Item {
public Box() {
	super();
}
	public Box(Box b) {
		this.p=new Position(b.p);;
	}
	public Box(Position p) {
		super(p);
		
	}
	
	public void move_up(Level2D level2d)
	{
		p.setI(p.getI()-1);
		
		
	}
	public void move_down(Level2D level2d)
	{
		p.setI(p.getI()+1);
	}
	public void move_right(Level2D level2d)
	{
		p.setJ(p.getJ()+1);
	}
	public void move_left(Level2D level2d)
	{
		p.setJ(p.getJ()-1);
	}
	public char getChar()
	{
		return '@';
	}



}
