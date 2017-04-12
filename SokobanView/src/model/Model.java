package model;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import db.Records;
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
	public void add_Record(String nickName , String levelId , int timer, int steps);
	public List<Records> sort_topDb(String sorter , String table, int num);
	public List<Records> searchOnDb(String table ,String column, String parameter);

}
