package model.data;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import model.items.Actor;
import model.items.Box;
import model.items.Destination_Box;
import model.items.Items;
import model.items.Space;
import model.items.Wall;

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


	public Level2D copyLevel(Level2D level) {
		HashMap<Character, Items> hm;
		hm = new HashMap<Character, Items>();
		hm.put('A', new Actor());
		hm.put('@', new Box());
		hm.put('#', new Wall());
		hm.put(' ', new Space());
		hm.put('o', new Destination_Box());
		Level2D copy = new Level2D(level);
		Items[][] items = new Items[level.getColumn()][level.getRow()];

		char[][] ch = new char[level.getColumn()][level.getRow()];

		for (int i = 0; i < level.getColumn(); i++) {
			for (int j = 0; j < level.getRow(); j++) {
				ch[i][j] = level.getWarehouse()[i][j].getChar();

				items[i][j] = hm.get(ch[i][j]);
				items[i][j].setP(new Position(i, j));

			}

		}
		copy.setRow(level.getRow());
		copy.setColumn(level.getColumn());
		copy.setWarehouse(items);
		copy.actors=new ArrayList<Actor>();
		copy.getActors().add(new Actor(new Position(level.getActors().get(0).getP())));


		/////maybe we need to update the arraylists
		return copy;
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
