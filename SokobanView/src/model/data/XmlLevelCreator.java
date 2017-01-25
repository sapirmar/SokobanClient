package model.data;


import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class XmlLevelCreator implements ILevelCreator {

	Level newlevel;
	
	public Level create(String in) {
		
		try{
			newlevel=new MyXMLLevelLoader().load_level(new FileInputStream(in));
			return newlevel;
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
