package model.data;

public class AddWallArrayList implements IAddArrayList{

	
	public void addToArrayList(Level level, Items item) {
		Wall wall=(Wall)item;
		level.getWalls().add(wall);
		
	}
	

}
