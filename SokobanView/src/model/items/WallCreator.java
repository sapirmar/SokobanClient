package model.items;

import model.data.ICreator;

public class WallCreator implements ICreator {

	public Items create() {

		return new Wall();
	}

}
