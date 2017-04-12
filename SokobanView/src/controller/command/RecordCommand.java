package controller.command;

import java.util.LinkedList;

import db.DbManager;
import db.LevelDb;
import db.Records;
import db.User;
import model.Model;

public class RecordCommand extends Command {
private Model model;



public RecordCommand(Model model , LinkedList<String> params ) {
	this.model=model;
	this.params=params;



}

	@Override
	public void execute() {
		String nickName = params.remove(0);
		String levelid=params.remove(0);
		String timer_string=params.remove(0);
		String steps_string=params.remove(0);
		int timer=Integer.parseInt(timer_string);
		int steps= Integer.parseInt(steps_string);
		model.add_Record(nickName, levelid, timer, steps);


	}

}
