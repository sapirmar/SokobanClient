package controller.command;

import controller.Controller;
import controller.server.MyServer;

public class ExitCommand extends Command {
	private Controller controller;
	private MyServer myserver;
	public ExitCommand(Controller controller,MyServer myserver) {
		this.controller=controller;
		this.myserver=myserver;
		//this.contorller=new Controller();//this is trick need to connect the controller
	}
	public void execute() {

		//if(myserver!=null)
		//	myserver.stop();
		controller.stop();
		if(myserver==null)
		System.exit(0);






	}

}
