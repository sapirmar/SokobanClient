package controller.command;

import java.util.List;

import db.Records;
import model.Model;
import view.View;

public class SortDbCommand extends Command {
	Model model;
	View view;
	public SortDbCommand(Model model,View view) {
		this.model=model;
		this.view=view;
	}
	@Override
	public void execute() {
		String sorter=params.remove(0);
		String table=params.remove(0);
		String Snum=params.remove(0);
		int num=Integer.parseInt(Snum);
		List<Records> list= model.sort_topDb(sorter, table, num);
		view.onUpadteTableButton(list);


	}

}
