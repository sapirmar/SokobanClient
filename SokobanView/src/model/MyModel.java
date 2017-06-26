package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.regex.Pattern;

import db.DbManager;
import db.LevelDb;
import db.Records;
import db.User;
import javafx.application.Platform;
import model.data.ILevelCreator;
import model.data.IlevelSaverCreator;
import model.data.Level;
import model.data.Level2D;
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
import model.items.Moveable_Item;
import model.policy.MySokobanPolicy;

public class MyModel extends Observable implements Model {
	private Level2D level;
	

	// private Level2D level=new Level2D();
	HashMap<String, ILevelCreator> hm_loader;
	HashMap<String, IlevelSaverCreator> hm_saver;

	//data members for the server
	private static final int port = 8787;
	private static final String ip = "127.0.0.1";
	
	
	public MyModel() {
		hm_loader = new HashMap<String, ILevelCreator>();
		hm_saver = new HashMap<String, IlevelSaverCreator>();
		hm_loader.put("txt", new TextLevelCreator());
		hm_loader.put("xml", new XmlLevelCreator());
		hm_loader.put("obj", new ObjectLevelCreator());
		hm_saver.put("txt", new TextLevelSaverCreator());
		hm_saver.put("xml", new XmlSaverCreator());
		hm_saver.put("obj", new ObjectLevelSaverCreator());

	}

	public Level getCurrentLevel() {
		return this.level;
	}
/**
 * save the level to the output stream 
 * @param out
 * @param level
 */
	public void Save_txt_Level(OutputStream out, Level2D level2d) throws IOException {
		new MyTextLevelSaver().SaveLevel(out, level2d);

	}

	public void Save_xml_Level(OutputStream out, Level2D level2d) throws IOException {
		new MyXmlSaver().SaveLevel(out, level2d);

	}

	public void Save_obj_Level(OutputStream out, Level2D level2d) throws IOException {
		new MyObjectSaver().SaveLevel(out, level2d);

	}
/**
 * move to the direction 
 * @param String direction
 */
	public void move(String direction) {
		Moveable_Item mi = level.getActors().get(0);
		String direct;
		direct = "move " + direction;
		MySokobanPolicy policy = new MySokobanPolicy(mi, direct, level);
		// System.out.println(level.getActors().get(0).getChar());
		// System.out.println(this.level.getColumn());
		// System.out.println(direction);
		if (policy.moveByPolicy(this.level)) {
			switch (direction) {
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
			LinkedList<String> params = new LinkedList<String>();/// why
																	/// new??????????????????
			params.add("display");
			// System.out.println(params.get(0));
			// System.out.println(params.get(1));
			this.notifyObservers(params);
		} else
			System.out.println("can not do the action");
	}
/**
 * load the level from the path(filename)
 * @param string filename the path
 */
	@Override
	public void load_level(String filename) {
		String type;
		// give us the 3 last letters indicate the type of file
		type = filename.substring(filename.length() - 3);
		// load according to the type
		this.level = (Level2D) hm_loader.get(type).create(filename);
		// split the filename according \
		String splitRegex = Pattern.quote(System.getProperty("file.separator"));
		String[] splittedFilename = filename.split(splitRegex);
		String simpleFilename = splittedFilename[splittedFilename.length - 1];
		// split according . and update levelId
		String[] array_levelId = simpleFilename.split(Pattern.quote("."));

		getCurrentLevel().setLevelId(array_levelId[0]);
		// notify
		this.setChanged();
		List<String> params = new LinkedList<String>();//// why new?????????????
		params.add("display");

		this.notifyObservers(params);

	}
/**
 * save the level to the filename(the filename with full path)
 * @param level 
 * @param String filename
 */
	@Override
	public void save_level(Level level, String filename) {
		String type;
		// give us the 3 last letters indicate the type of file
		type = filename.substring(filename.length() - 3);
		hm_saver.get(type).create((Level2D) level, filename);
		// this.setChanged();
		// List<String> params=new LinkedList<String>();
		// this.notifyObservers(params);
	}

	public void add_Record(String nickName, String levelId, int timer, int steps) {

		DbManager<Records> records_manager = new DbManager<Records>();
		DbManager<User> user_manager = new DbManager<User>();

		DbManager<LevelDb> level_manager = new DbManager<LevelDb>();


		System.out.println(levelId);
		if((records_manager.if_exist("Users", "NickName",nickName)).size()==0)
		user_manager.add(new User(nickName));
		if((records_manager.if_exist("Levels", "LevelID",levelId)).size()==0)
		level_manager.add(new LevelDb(levelId));
		records_manager.add(new Records(nickName, levelId, timer, steps));
		List<String> params = new LinkedList<String>();
	/*	params.add("showdb");
		setChanged();
		notifyObservers(params);
*/
	}
	public List<Records> sort_topDb(String sorter , String table, int num)/// sort and show the top of the database
	{
		DbManager<Records> records=new DbManager<Records>();
		List<Records> list=records.sort_top(sorter, table, num);
		System.out.println(sorter+table);
		return list;


	}
	public List<Records> searchOnDb(String table ,String column, String parameter)
	{
		DbManager<Records> records=new DbManager<Records>();
		return records.if_exist(table, column, parameter);
	}
//changes that we do to mileston5
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 	send to the server the level we want to solve and get the solution actions ,
	the string we get tell us if the client want full soultion or just a hint
	 * @param solution or hint
	 * @param the delay from command to next command
	 */
	public boolean solveFromServer(String solutionOrHint,int delay) throws UnknownHostException, IOException{
		Socket connectionSocket = new Socket(ip, port);
		ObjectOutputStream objWriter = new ObjectOutputStream(connectionSocket.getOutputStream());
		objWriter.writeObject(this.level);//send the level
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		//String actions = reader.readLine();//get the solution as a string
		String action="";
		LinkedList<String> allActionsFromServer=new LinkedList<>();
		
		while((action=reader.readLine())!=null){
			System.out.println(action);////////////////////////////////////////////////////just for test
			allActionsFromServer.add(action);
		} 
		if(allActionsFromServer.size()==0)
		{
			System.out.println("there is no solution from server !!!!!!!!!!!");
			connectionSocket.close();
			return false;
		}
		sendActionsFromSolution(allActionsFromServer, delay,solutionOrHint);
		connectionSocket.close();
		return true;
		
	}
	/**
	 * notify the move command to the controller
	 * @param action
	 */
	public void notifyMoveCommandFromServer(String action){
		if(action.contains("move up")){
			LinkedList<String > params=new LinkedList<>();
			params.add("move");
			params.add("up");
			setChanged();
			notifyObservers(params);
		}
		else if(action.contains("move down")){
			LinkedList<String > params=new LinkedList<>();
			params.add("move");
			params.add("down");
			setChanged();
			notifyObservers(params);
		}
		else if(action.contains("move right")){
			LinkedList<String > params=new LinkedList<>();
			params.add("move");
			params.add("right");
			setChanged();
			notifyObservers(params);
		}
		else if(action.contains("move left")){
			LinkedList<String > params=new LinkedList<>();
			params.add("move");
			params.add("left");
			setChanged();
			notifyObservers(params);
		}
		
	}
		
		
	
/**
 * pass the action with delay to the controller 
 * possible to full solution or just a hint(one step)
 * @param actions
 * @param delay
 * @param solutionOrHint
 */
	private void sendActionsFromSolution(LinkedList<String> actions, int delay,String solutionOrHint) {
		//if he wants hint-give just the first action
		if(solutionOrHint.equals("hint")){
		notifyMoveCommandFromServer(actions.get(0));
		}
		else{
		//action example "move up" ,"move down","move up 0"...
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (String action : actions) {
					notifyMoveCommandFromServer(action);
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}
	
	}

	

	public void setLevel(Level2D level) {
		this.level = level;
	}
	

}
