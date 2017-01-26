package model.data;



public class BoxCreator implements ICreator {

	public Items create() {
		return new Box();
	}

}
