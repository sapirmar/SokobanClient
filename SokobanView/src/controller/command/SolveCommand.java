package controller.command;

import java.io.IOException;

import model.Model;

public class SolveCommand extends Command {
	private Model model;
	public SolveCommand(Model model) {
		this.model=model;
	}
	@Override
	public void execute() {
		try {
			model.solveFromServer("solution", 500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
