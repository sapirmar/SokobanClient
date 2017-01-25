package model.data;


public class AddDestinationArrayList implements IAddArrayList {


	public void addToArrayList(Level level, Items item) {
		Destination_Box db=(Destination_Box)item;
		level.getDest_boxes().add(db);

	}

}
