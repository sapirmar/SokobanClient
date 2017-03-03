package model.items;

public class ActorCreator implements ICreator {

	public Items create() {
		return new Actor();
	}

}
