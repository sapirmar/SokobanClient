package controller;

import view.View;
import model.Model;



public interface Command_Creator {
	public Command create();
	public void SetModel(Model model);
	public void SetView(View view);

}

