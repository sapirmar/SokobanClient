package model.data;




public class AddBoxArrayList implements IAddArrayList {


	public void addToArrayList(Level level, Items item) {
		level.getBoxes().add((Box)item);
		

	}

}
