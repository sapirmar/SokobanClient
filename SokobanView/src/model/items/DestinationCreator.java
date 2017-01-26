package model.items;

import model.data.ICreator;

public class DestinationCreator implements ICreator {


	public Items create() {
		
		return new Destination_Box()  ;
	}

}
