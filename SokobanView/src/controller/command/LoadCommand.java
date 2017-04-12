package controller.command;

import java.util.LinkedList;
import java.util.regex.Pattern;

import model.Model;
import model.data.Level;
import view.View;





public class LoadCommand extends Command {
	private Model model;
	private String filename;
	private View view;////////
	//private String type;

	//public HashMap<String,ILevelCreator> hm= new HashMap<String,ILevelCreator>();

	public LoadCommand(Model model,LinkedList<String> params,View view) {
		this.model=model;
		this.view=view;
		this.params=params;
		if(this.params!=null)
		this.filename=params.get(0);

	}
	public void execute() {
	//System.out.println(params.get(0));
	view.stopTimer();
	filename=params.remove(0);
	model.load_level(filename);

	this.view.startTimer();
	}
	public Level getLevel() {
		return model.getCurrentLevel();
	}
	public void setLevel(Level level) {
	  ////////////////////maybe we dont need this func
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}



}
