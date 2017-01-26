package controller.command;



import view.View;
import model.Model;
import model.data.Level;
import model.data.Level2D;

public class DisplayCommand extends Command {

	private Model model;
	private View view;
	public DisplayCommand(Model model,View view) {
		this.model=model;
		this.view=view;
	}
	@Override
	public void execute() {
		Level level=model.getCurrentLevel();
		view.setCurrentLevel((Level2D)model.getCurrentLevel());
		//view.display((Level2D)model.getCurrentLevel());

	}

}
