package controller;

import view.View;
import model.Model;
import model.MyModel;
import model.data.Level2D;

public class MoveCommandCreator implements Command_Creator {

	Model model;
public MoveCommandCreator() {
	
}
	@Override
	public Command create() {
		return new Move_command(model);
	}
	@Override
	public void SetModel(Model model) {
		this.model=model;
		
	}
	@Override
	public void SetView(View view) {
		// TODO Auto-generated method stub
		
	}



}
