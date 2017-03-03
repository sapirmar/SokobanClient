package model.items;

import model.data.Level2D;
import model.data.Position;
import model.data.Level;


public class Moveable_Item extends Items{



	public Moveable_Item(Position p) {
		super(p);

	}
	public Moveable_Item() {
		super();

	}
	public Moveable_Item(Moveable_Item itm){
		super(itm);
	}
	//get/set

	//function move , update the position , the level and the array-lists
	public void move(Position old_p,Position new_p,Level2D level)
	{
		Items item=level.warehouse[old_p.getI()][old_p.getJ()];
		Position old=new Position(old_p);
		//if this on target
		if(item.p.getFlagDestination()==true)
		{
			Destination_Box db=new Destination_Box(old_p);// new target with the old position( because we were on target)
			level.warehouse[old_p.getI()][old_p.getJ()]=db;// update warehouse
			level.dest_boxes.add(db); // update arraylist
			//if this on target an the next on target too.
			if(level.warehouse[new_p.getI()][new_p.getJ()].p.getFlagDestination()==true)
			{
				for(int i=0;i<level.dest_boxes.size();i++){
				if(level.dest_boxes.get(i).p==new_p)
				{
					level.dest_boxes.remove(i);
				}
				
				this.p.setFlagDestination(true);// update the flag because on target
				level.warehouse[new_p.getI()][new_p.getJ()]=this;
				this.p.setPosition(new_p.getI(),new_p.getJ());
				//if the move-item is  actor
				if(this.getClass().equals(Actor.class))
				for(int j=0;j<level.actors.size();j++)// update the array list
				{
					if(level.actors.get(j).p==old_p)
					{
						level.actors.get(j).p.setPosition(new_p.getI(), new_p.getJ());
					}
				}
				//if the move item is box
				if(this.getClass().equals(Box.class))
					for(int j=0;j<level.boxes.size();j++)// update the array list
					{
						if(level.boxes.get(j).p==old_p)
						{
							level.boxes.get(j).p.setPosition(new_p.getI(), new_p.getJ());
						}
					}

			}
			}
			else// if this on target and the next is space
			{
				this.p.setFlagDestination(false);// update the flag because the next not on target
				for(int j=0;j<level.spaces.size();j++){
					if(level.spaces.get(j).p==new_p)
					{
						level.spaces.remove(j);
					}
				}
				level.warehouse[new_p.getI()][new_p.getJ()]=this;
				this.p.setPosition(new_p.getI(),new_p.getJ());
				if(this.getClass().equals(Actor.class))
				for(int i=0;i<level.actors.size();i++)
				{
					if(level.actors.get(i).p==old_p)
					{
						level.actors.get(i).p.setPosition(new_p.getI(), new_p.getJ());
					}
				}
				if(this.getClass().equals(Box.class))
				{
					for(int i=0;i<level.boxes.size();i++)
					{
						if(level.boxes.get(i).p==old_p)
						{
							level.boxes.get(i).p.setPosition(new_p.getI(), new_p.getJ());
						}
					}

				}


			}
		}
		else // if this not on target
		{
			Space space=new Space(old_p);
			level.warehouse[old.getI()][old.getJ()]=space;
			level.spaces.add(space);


			//if this is not on target and the next on target
			if(level.warehouse[new_p.getI()][new_p.getJ()].getP().getFlagDestination())
			{
				p.setFlagDestination(true);
				for(int i=0;i<level.dest_boxes.size();i++)
				{
					if(level.dest_boxes.get(i).p==new_p)
					{
						level.dest_boxes.remove(i);
					}
				}
				level.warehouse[new_p.getI()][new_p.getJ()]=this;
				this.p.setPosition(new_p.getI(), new_p.getJ());
				if(this.getClass().equals(Actor.class))
				for(int j=0;j<level.actors.size();j++)
				{
					if(level.actors.get(j).p==old_p)
					{
						level.actors.get(j).p.setPosition(new_p.getI(),new_p.getJ());
					}

				}
			}
			else //if this not on target and the next not on target
			{
				///remove the space from the array list
				for(int i=0;i<level.spaces.size();i++)
				{
					if(level.spaces.get(i).p==new_p)
					{
						level.spaces.remove(i);
					}
				}
				if(this.getClass().equals(Actor.class))
				{
					level.warehouse[new_p.getI()][new_p.getJ()]=this;
					this.p.setPosition(new_p.getI(), new_p.getJ());
					for(int j=0;j<level.actors.size();j++)
					{
						if(level.actors.get(j).p.equals(old_p))
						{
							level.actors.get(j).p.setPosition(new_p.getI(), new_p.getJ());
						}
					}
				}
				if(this.getClass().equals(Box.class))
				{
					level.warehouse[new_p.getI()][new_p.getJ()]=this;
					this.p.setPosition(new_p.getI(), new_p.getJ());
					for(int j=0;j<level.boxes.size();j++)
					{
						if(level.boxes.get(j).p.equals(old_p))
						{
							level.boxes.get(j).p.setPosition(new_p.getI(), new_p.getJ());
						}
					}

				}
			}
		}

	}
	public void move_up(Level2D level)
	{
		if(!(level.warehouse[this.p.getI()-1][this.p.getJ()].getClass().equals(Box.class)))
		{
			Position a=new Position(this.p);
			move(a, level.warehouse[p.getI()-1][p.getJ()].p, level);
		}
		else
		{
			Position a=new Position(this.p);
			Position b=new Position(level.warehouse[p.getI()-1][p.getJ()].p);
			((Box)level.warehouse[p.getI()-1][p.getJ()]).move(b, level.warehouse[p.getI()-2][p.getJ()].p, level);
			move(a, level.warehouse[p.getI()-1][p.getJ()].p, level);

		}
	}
	public void move_down(Level2D level)
	{
		if(!(level.warehouse[this.p.getI()+1][this.p.getJ()].getClass().equals(Box.class)))
		{
			Position a=new Position(this.p);
			move(a, level.warehouse[p.getI()+1][p.getJ()].p, level);
		}
		else
		{
			Position a=new Position(this.p);
			Position b=new Position(level.warehouse[p.getI()+1][p.getJ()].p);
			((Box)level.warehouse[p.getI()+1][p.getJ()]).move(b, level.warehouse[p.getI()+2][p.getJ()].p, level);
			move(a, level.warehouse[p.getI()+1][p.getJ()].p, level);

		}
	}
	public void move_right(Level2D level)
	{
		if(!(level.warehouse[this.p.getI()][this.p.getJ()+1].getClass().equals(Box.class)))
		{
			Position a=new Position(this.p);
			move(a, level.warehouse[p.getI()][p.getJ()+1].p, level);
		}
		else
		{
			Position a=new Position(this.p);
			Position b=new Position(level.warehouse[p.getI()][p.getJ()+1].p);
			((Box)level.warehouse[p.getI()][p.getJ()+1]).move(b, level.warehouse[p.getI()][p.getJ()+2].p, level);
			move(a, level.warehouse[p.getI()][p.getJ()+1].p, level);

		}
	}
	public void move_left(Level2D level)
	{
		if(!(level.warehouse[this.p.getI()][this.p.getJ()-1].getClass().equals(Box.class)))
		{
			Position a=new Position(this.p);
			move(a, level.warehouse[p.getI()][p.getJ()-1].p, level);
		}
		else
		{
			Position a=new Position(this.p);
			Position b=new Position(level.warehouse[p.getI()][p.getJ()-1].p);
			((Box)level.warehouse[p.getI()][p.getJ()-1]).move(b, level.warehouse[p.getI()][p.getJ()-2].p, level);
			move(a, level.warehouse[p.getI()][p.getJ()-1].p, level);

		}

	}


}
