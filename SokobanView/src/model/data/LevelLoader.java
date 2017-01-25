package model.data;

import java.io.InputStream;


public interface LevelLoader {
	
	public Level load_level(InputStream in);//get a file and load the level(txt,xml,obj)
	
}
