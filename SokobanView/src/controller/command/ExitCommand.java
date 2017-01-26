package controller;


public class ExitCommand extends Command {
	private Controller contorller;
	public ExitCommand(Controller controller) {
		this.contorller=controller;

		//this.contorller=new Controller();//this is trick need to connect the controller
	}
	public void execute() {

		contorller.stop();


	}

}
