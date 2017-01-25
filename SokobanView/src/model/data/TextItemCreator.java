package model.data;

import java.util.HashMap;

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





