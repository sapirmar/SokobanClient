package model.data;

import model.items.Box;
import model.items.Items;

public class AddBoxArrayList implements IAddArrayList {


	public void addToArrayList(Level level, Items item) {
		level.getBoxes().add((Box)item);
		

	}

}
