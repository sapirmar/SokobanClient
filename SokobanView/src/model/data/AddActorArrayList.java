package model.data;

import model.items.Actor;
import model.items.Items;

public class AddActorArrayList implements IAddArrayList {


	public void addToArrayList(Level level, Items item) {
		Actor actor;
		actor=(Actor)item;
		level.getActors().add(actor);

	}

}
