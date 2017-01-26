package model.data;

import model.items.Destination_Box;
import model.items.Items;

public class AddDestinationArrayList implements IAddArrayList {


	public void addToArrayList(Level level, Items item) {
		Destination_Box db=(Destination_Box)item;
		level.getDest_boxes().add(db);

	}

}
