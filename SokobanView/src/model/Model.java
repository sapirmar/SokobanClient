package model;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import model.data.Level;
import model.data.Level2D;


public interface Model {
	public Level getCurrentLevel();
	public void move(String direction);
	public void load_level(String filename);
	public void save_level(Level level,String filename);
	public void Save_txt_Level(OutputStream out,Level2D level2d)throws IOException;
	public void Save_xml_Level(OutputStream out,Level2D level2d)throws IOException;
	public void Save_obj_Level(OutputStream out,Level2D level2d)throws IOException;
}
