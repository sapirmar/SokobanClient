package model.data;
import java.io.Serializable;

public class Position implements Serializable{
	private int i;
	private int j;
	private boolean flagDestination;

	// default constructor
	public Position() {
		i=-1;
		j=-1;
		flagDestination=false;
	}
	//constructor
	public Position(int i,int j) {
	this.i=i;
	this.j=j;
	flagDestination=false;

}
	// copy-constructor
	public Position(Position p)
	{
		this.i=p.getI();
		this.j=p.getJ();
		flagDestination=false;
	}

	//get/set
	public void setPosition(int i,int j)
	{
		this.i=i;
		this.j=j;
	}

	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public boolean getFlagDestination() {
		return flagDestination;
	}
	public void setFlagDestination(boolean flagDestination) {
		this.flagDestination = flagDestination;
	}

	@Override
	public String toString() {
		String s=""+getI()+getJ();
		return s;

	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	/*
	@Override
	public boolean equals(Object obj) {
	Position p=(Position)obj;
	if(p.getI()==getI()&&p.getJ()==getJ())
		return true;
	else return false;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
		return result;
	}
	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		return true;
	}


}
