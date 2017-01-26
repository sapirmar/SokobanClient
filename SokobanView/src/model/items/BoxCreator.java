package model.items;

import model.data.ICreator;

public class BoxCreator implements ICreator {

	public Items create() {
		return new Box();
	}

}
