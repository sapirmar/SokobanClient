package controller;

import model.Model;
import view.View;

public abstract class CommandModelAndView implements ICommand {
	private Model model;
	private View view;
	@Override
	public abstract void execute();
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
}
