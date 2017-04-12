package controller.command;

import java.util.List;

import db.DbManager;
import db.Records;
import view.View;

public class ShowDbCommand extends Command {
	View view;
	public ShowDbCommand(View view) {
		this.view=view;
	}
	@Override
	public void execute() {
		DbManager<Records> manager = new DbManager<Records>();
		List <Records> list= manager.show_all_table("Records");
		
		

		view.onUpadteTableButton(list);



	}

}
