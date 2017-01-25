package model.data;


import java.util.HashMap;

public class AddArrayListGenarator {
	private HashMap<Character,IAddArrayList> hm;
	public AddArrayListGenarator() {
		hm=new HashMap<Character,IAddArrayList>();
		hm.put('A', new AddActorArrayList());
		hm.put('@', new AddBoxArrayList());
		hm.put('#', new AddWallArrayList());
		hm.put(' ',new AddSpacesArrayList());
		hm.put('o',new AddDestinationArrayList());
	}
	public HashMap<Character, IAddArrayList> getHm() {
		return hm;
	}
	public void setHm(HashMap<Character, IAddArrayList> hm) {
		this.hm = hm;
	}

}
