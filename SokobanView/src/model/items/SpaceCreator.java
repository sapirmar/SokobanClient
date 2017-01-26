package model.items;

import model.data.ICreator;

public class SpaceCreator implements ICreator {

	public Items create() {

		return new Space();
	}

}
