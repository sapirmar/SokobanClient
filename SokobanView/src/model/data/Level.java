package model.data;
import java.io.Serializable;
import java.util.ArrayList;


public class Level implements Serializable{
	ArrayList<Box> boxes;
	ArrayList<Wall> walls;
	ArrayList<Actor> actors;
	ArrayList<Destination_Box> dest_boxes;
	ArrayList<Space> spaces;
	ArrayList<Items> items;



// default constructor
	public Level() {
		boxes=new ArrayList<Box>();
		walls=new ArrayList<Wall>();
		actors=new ArrayList<Actor>();
		dest_boxes=new ArrayList<Destination_Box>();
		spaces=new ArrayList<Space>();
		items=new ArrayList<Items>();



	}
	//constructor
	public Level(Level level)
	{
		this.boxes=new ArrayList<Box>();
		this.walls=new ArrayList<Wall>();
		this.actors=new ArrayList<Actor>();
		this.dest_boxes=new ArrayList<Destination_Box>();
		this.spaces=new ArrayList<Space>();
		this.items=new ArrayList<Items>();

		for(Box box: level.boxes)
			this.boxes.add(new Box(new Position(box.p)));
		for(Wall wall: level.walls)
			this.walls.add(wall);
		for(Actor actor:level.actors)
			this.actors.add(actor);
		for(Destination_Box dest:level.dest_boxes)
			this.dest_boxes.add(dest);
		for(Space space:level.spaces)
			this.spaces.add(space);
		for(Items item:level.items)
			this.items.add(item);

	}
	//get/set

	public ArrayList<Box> getBoxes() {
		return boxes;
	}

	public void setBoxes(ArrayList<Box> boxes) {
		this.boxes = boxes;
	}

	public ArrayList<Wall> getWalls() {
		return walls;
	}

	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}

	public ArrayList<Actor> getActors() {
		return actors;
	}

	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}

	public ArrayList<Destination_Box> getDest_boxes() {
		return dest_boxes;
	}

	public void setDest_boxes(ArrayList<Destination_Box> dest_boxes) {
		this.dest_boxes = dest_boxes;
	}

	public ArrayList<Space> getSpaces() {
		return spaces;
	}

	public void setSpaces(ArrayList<Space> spaces) {
		this.spaces = spaces;
	}

	public ArrayList<Items> getItems() {
		return items;
	}

	public void setItems(ArrayList<Items> items) {
		this.items = items;
	}
	public boolean checkIfWin()
	{
		for (Box box : boxes) {
			if(box.ifOnDestination()==false)
				return false;
		}
		return true;
	}








}
