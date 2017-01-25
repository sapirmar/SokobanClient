package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
	initCommands();
	this.controller=new Controller();
	this.controller.start();
	}
	public SokobanController(Model model ,View view,MyServer myserver) {
	this.model=model;
	this.view=view;
	this.myserver=myserver;
	initCommands();
	this.controller=new Controller();
	//this.controller=controller;
	myserver.start();
	this.controller.start();

	}


	protected void initCommands(){
		commands=new HashMap<String,Command>();
		commands.put("move",new Move_command(model,view));
		commands.put("display", new DisplayCommand(model,view));
		commands.put("load" ,new LoadCommand(model,params,view));
		commands.put("save", new SaveCommand(model,params));
		commands.put("exit",new ExitCommand(controller));

	}


	public void update(Observable o, Object arg) {
		params=(LinkedList<String>)arg;

		if(params.size()>0){
		String commandKey = params.remove(0);// remove the first word and put in commankey
		Command c = commands.get(commandKey);
		if(c==null)
		{
			//view.displayError("command not found \n ");
			return;
		}
		c.setParams(params);

		controller.insertCommand(c);
		}
	}

}
