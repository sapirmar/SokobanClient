package controller.command;

import java.util.List;

import db.DbManager;
import db.Records;
import model.Model;
import view.View;

public class ShowTableDifferentWindowCommand extends Command {
	View view;
	Model model;
	public ShowTableDifferentWindowCommand(View view,Model model) {
		this.view=view;
		this.model=model;
	}
	@Override
	public void execute() {
		String sorter=params.remove(0);
		String table=params.remove(0);
		String nums=params.remove(0);
		int num=Integer.parseInt(nums);
		System.out.println(num);
		//List<Records> list= model.sort_topDb(sorter, table, num);
		view.show_Table_OtherWindow(model.sort_topDb(sorter, table, num));


	}

}
