package model.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ObjectLevelCreator implements ILevelCreator {

	public Level create(String in) {
		try{
			InputStream input=new FileInputStream(new File(in));
			return new MyObjectLevelLoder().load_level(input);
			}catch(IOException e)
			{
				e.printStackTrace();
				System.out.println("cant load the level");
				return null;
			}
				
	
	}

}
