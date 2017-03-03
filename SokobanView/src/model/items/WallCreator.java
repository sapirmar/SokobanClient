package model.items;

public class WallCreator implements ICreator {

	public Items create() {

		return new Wall();
	}

}
