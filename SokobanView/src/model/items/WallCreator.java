package model.data;



public class WallCreator implements ICreator {

	public Items create() {

		return new Wall();
	}

}
