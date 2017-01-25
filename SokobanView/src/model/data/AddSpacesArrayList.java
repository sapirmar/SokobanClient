package model.data;


public class AddSpacesArrayList implements IAddArrayList {

	public void addToArrayList(Level level, Items item) {
		Space space=(Space)item;
		level.getSpaces().add(space);

	}

}
