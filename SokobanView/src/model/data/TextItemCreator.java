package model.data;

import java.util.HashMap;

import model.items.ActorCreator;
import model.items.BoxCreator;
import model.items.DestinationCreator;
import model.items.ICreator;
import model.items.SpaceCreator;
import model.items.WallCreator;

public class TextItemCreator {
	
	public HashMap<Character, ICreator> hm;
	public TextItemCreator() {
		hm=new HashMap<Character,ICreator>();
		hm.put('A',new ActorCreator());
		hm.put('@',new BoxCreator());
		hm.put('#', new WallCreator());
		hm.put('o', new DestinationCreator());
		hm.put(' ', new SpaceCreator());
		
	}
}





