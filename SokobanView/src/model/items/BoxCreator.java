package model.items;

public class BoxCreator implements ICreator {

	public Items create() {
		return new Box();
	}

}
