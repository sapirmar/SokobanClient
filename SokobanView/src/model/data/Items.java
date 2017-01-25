package model.data;
import java.io.Serializable;

public abstract class Items implements Serializable{
	public Position p;
	
	public Items() {
		p=null;
	}
	public Items(Position p) {
		this.p=p;
		
	}
	//NEW!!!!!!!!!!!!!!!!!!!!!!!1
	public Items(Items itm){
		this.p=new Position(itm.getP());
	}
	
	public Position getP() {
		return p;
	}
	public void setP(Position p) {
		this.p = p;
	}
	public Boolean ifOnDestination()
	{
		if(p.getFlagDestination()==true)
			return true;
		else 
			return false;
	}
	public char getChar()
	{
		return '*';
	}

}
