package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import controller.command.Command;
import controller.command.DisplayCommand;
import controller.command.ExitCommand;
import controller.command.LoadCommand;
import controller.command.Move_command;
import controller.command.RecordCommand;
import controller.command.SaveCommand;
import controller.command.SearchDbCommand;
import controller.command.ShowDbCommand;
import controller.command.ShowTableDifferentWindowCommand;
import controller.command.SortDbCommand;
import controller.server.MyServer;
import view.View;
import model.Model;

public class SokobanController implements Observer {

	private Model model;
	private View view;
	private Controller controller;

	private HashMap<String, Command> commands;
	LinkedList<String>params;
	MyServer myserver;

	public SokobanController(Model model , View view) {
	this.model=model;
	this.view=view;
	this.controller=new Controller();
	initCommands();
	this.controller.start();


	}
	public SokobanController(Model model ,View view,MyServer myserver) {
	//this.controller=new Controller();
	this.model=model;
	this.view=view;
	this.myserver=myserver;
	initCommands();


	//this.controller=controller;
	myserver.start();
	this.controller.start();

	}


	protected void initCommands(){
		this.controller=new Controller();
		commands=new HashMap<String,Command>();
		commands.put("move",new Move_command(model,view));
		commands.put("display", new DisplayCommand(model,view));
		commands.put("load" ,new LoadCommand(model,params,view));
		commands.put("save", new SaveCommand(model,params));
		commands.put("exit",new ExitCommand(controller,myserver));
		commands.put("exitgame",new ExitCommand(controller,myserver));
		commands.put("record", new RecordCommand(model, params));
		commands.put("sortdb", new SortDbCommand(model,view));
		commands.put("showdb", new ShowDbCommand(view));
		commands.put("tablewindow",new ShowTableDifferentWindowCommand(view, model));
		commands.put("searchdb", new SearchDbCommand(model,view));
	}


	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}
	public void update(Observable o, Object arg) {
		params=(LinkedList<String>)arg;

		if(params.size()>0){
		String commandKey = params.remove(0);// remove the first word and put in commankey
		Command c = commands.get(commandKey);//יצירת הקומנד באמצעות המחרוזת

		if(c==null)
		{
			System.out.println("no command");
			return;
		}
		c.setParams(params);

		controller.insertCommand(c);
		}
	}

}
