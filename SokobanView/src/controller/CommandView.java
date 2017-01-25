package controller;

import view.View;

public abstract class CommandView implements ICommand {
	 private View view;
	@Override
	public abstract void execute() ;
}
