package controller.command;

import model.Model;
import model.data.Level2D;
import view.View;


public class Move_command extends Command {
	private Level2D level2d;
	private Model model;
	private View view;//////////
	public Move_command(Model model,View view) {
		this.view=view;
		this.model=model;
		this.level2d=(Level2D)model.getCurrentLevel();

	}
	@Override
	public void execute() {
		String direction=params.remove(0);
		model.move(direction);
		this.view.addStep();
		this.view.winLevel();

	}

}
