package model.data;

import java.io.FileOutputStream;
import java.io.IOException;


public class ObjectLevelSaverCreator implements IlevelSaverCreator {
	private String filename;
	private Level2D level;
	public ObjectLevelSaverCreator() {
		// TODO Auto-generated constructor stub
	}

	public void create(Level2D level,String filename) {
		try{
			this.level=level;
			this.filename=filename;
			MyObjectSaver object=new MyObjectSaver();
			object.SaveLevel(new FileOutputStream(this.filename), this.level);
			
		}catch(IOException e)
		{
			System.out.println("can not save the level");
			e.printStackTrace();
		}
		
	}

	
	public void SetLevel(Level2D level) {
		this.level=new Level2D(level);
	}


	public void SetStr(String str) {
		this.filename=new String(str);
		
	}

}
