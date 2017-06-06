package model.data;
import java.io.BufferedWriter;
import java.io.IOException;

import model.items.Items;

public class Level2D extends Level {
	public Items[][] warehouse;
	private int row;
	private int column;
	//constructor
	public Level2D(Level2D newlevel)
	{
		super(newlevel);
		row=newlevel.row;
		column=newlevel.column;
		this.warehouse=newlevel.warehouse;

	}
	public Level2D() {
		super();
	}
	public Items[][] getWarehouse() {
		return warehouse;
	}



	public void setWarehouse(Items[][] warehouse) {
		this.warehouse = warehouse;
	}
	public Level2D(int i,int j) {
		column=i;
		row=j;
		warehouse=new Items[i][j];

	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}




	//function that we use to save the text
	public void textSaver(BufferedWriter writer)throws IOException{

		for(int i=0;i<getColumn();i++)
		{
			for(int j=0;j<getRow();j++)
				writer.write(warehouse[i][j].getChar());
			writer.newLine();
		}

	}



}
