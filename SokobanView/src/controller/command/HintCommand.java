package controller.command;

import java.io.IOException;

import model.Model;

public class HintCommand extends Command {

	private Model model;
	public HintCommand(Model model) {
		this.model=model;
	}
	@Override
	public void execute() {
		try {
			model.solveFromServer("hint", 500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
