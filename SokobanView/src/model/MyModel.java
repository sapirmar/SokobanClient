package model;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import model.data.ILevelCreator;
import model.data.IlevelSaverCreator;
import model.data.Level;
import model.data.Level2D;
import model.data.Moveable_Item;
import model.data.MyObjectLevelLoder;
import model.data.MyObjectSaver;
import model.data.MyTextLevelLoader;
import model.data.MyTextLevelSaver;
import model.data.MyXMLLevelLoader;
import model.data.MyXmlSaver;
import model.data.ObjectLevelCreator;
import model.data.ObjectLevelSaverCreator;
import model.data.TextLevelCreator;
import model.data.TextLevelSaverCreator;
import model.data.XmlLevelCreator;
import model.data.XmlSaverCreator;
import model.policy.MySokobanPolicy;

public class MyModel extends Observable implements Model {
private Level2D level;

//private Level2D level=new Level2D();
HashMap<String,ILevelCreator> hm_loader;
HashMap<String,IlevelSaverCreator> hm_saver;
public MyModel() {
	hm_loader=new HashMap<String,ILevelCreator>();
	hm_saver=new HashMap<String,IlevelSaverCreator>();
	hm_loader.put("txt",new TextLevelCreator());
	hm_loader.put("xml",new XmlLevelCreator());
	hm_loader.put("obj",new ObjectLevelCreator());
	hm_saver.put("txt",new TextLevelSaverCreator());
	hm_saver.put("xml", new XmlSaverCreator());
	hm_saver.put("obj", new ObjectLevelSaverCreator());
	
}

	public Level getCurrentLevel() {
		return this.level;
	}


	public void Save_txt_Level(OutputStream out, Level2D level2d)
			throws IOException {
		new MyTextLevelSaver().SaveLevel(out, level2d);
		
	}

	public void Save_xml_Level(OutputStream out, Level2D level2d)
			throws IOException {
		new MyXmlSaver().SaveLevel(out, level2d);
		
	}

	public void Save_obj_Level(OutputStream out, Level2D level2d)
			throws IOException {
		new MyObjectSaver().SaveLevel(out, level2d);
		
	}

	public void move(String direction) {
		Moveable_Item mi=level.getActors().get(0);
		String direct;
		direct="move "+direction;
		MySokobanPolicy policy=new MySokobanPolicy(mi,direct, level);
		//System.out.println(level.getActors().get(0).getChar());
		//System.out.println(this.level.getColumn());
		//System.out.println(direction);
		if(policy.moveByPolicy(this.level))
		{
		switch(direction){
		case "up":
			mi.move_up(level);
			break;
		case "down":
			mi.move_down(level);
			break;
		case "left":
			mi.move_left(level);
			break;
		case "right":
			mi.move_right(level);
			break;
		default:
			throw new IllegalArgumentException("invalid direction");
			
		}
		this.setChanged();
		LinkedList<String> params=new LinkedList<String>();///why new??????????????????
		params.add("display");
		//System.out.println(params.get(0));
		//System.out.println(params.get(1));
		this.notifyObservers(params);
		}
		else
			System.out.println("can not do the action");
	}


	@Override
	public void load_level(String filename) {
		String type;
		//give us the 3 last letters indicate the type of file
		type=filename.substring(filename.length()-3);
		//load according to the type
		this.level=(Level2D)hm_loader.get(type).create(filename);
		//notify 
		this.setChanged();
		List<String> params=new LinkedList<String>();////why new?????????????
		params.add("display");
		//params.add("move");
		//params.add("up");
	
		this.notifyObservers(params);
		
		
	}

	@Override
	public void save_level(Level level,String filename) {
		String type;
		//give us the 3 last letters indicate the type of file
		type=filename.substring(filename.length()-3);
		hm_saver.get(type).create((Level2D)level, filename);
		this.setChanged();
		List<String> params=new LinkedList<String>();
		this.notifyObservers(params);
	}
	


}
