package model.items;

import model.data.ICreator;

public class ActorCreator implements ICreator {

	public Items create() {
		return new Actor();
	}

}
