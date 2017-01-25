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
   	
	
}
