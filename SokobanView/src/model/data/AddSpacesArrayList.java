package model.data;

import model.items.Items;
import model.items.Space;

public class AddSpacesArrayList implements IAddArrayList {

	public void addToArrayList(Level level, Items item) {
		Space space=(Space)item;
		level.getSpaces().add(space);

	}

}
