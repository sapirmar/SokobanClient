package controller;

import model.Model;

public abstract class CommandModel implements ICommand {

	protected Model model;
	
	@Override
	public abstract void execute();
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
}
