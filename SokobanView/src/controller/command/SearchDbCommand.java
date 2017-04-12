package controller.command;

import java.util.List;

import db.Records;
import model.Model;
import view.View;

public class SearchDbCommand extends Command {
	Model model;
	View view;

	public SearchDbCommand(Model model, View view) {
		this.model=model;
		this.view= view;
	}
	@Override
	public void execute() {
		// the first param is the command the second is table 3-column 4-parameter
		String table=params.remove(0);
		String column=params.remove(0);
		String parameter=params.remove(0);
		List<Records> list=model.searchOnDb(table, column, parameter);
		view.show_Table_OtherWindow(list);




	}

}
