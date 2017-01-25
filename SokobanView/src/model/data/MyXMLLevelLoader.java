package model.data;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.InputStream;



public class MyXMLLevelLoader implements LevelLoader {
	
	public Level load_level(InputStream in){
		Level2D level2d=new Level2D();
		XMLDecoder xml=new XMLDecoder(new BufferedInputStream(in));
		level2d=(Level2D)xml.readObject();
		xml.close();
		return level2d;
		
	
	
}


}
