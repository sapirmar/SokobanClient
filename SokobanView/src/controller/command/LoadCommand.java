package controller.command;

import java.util.LinkedList;

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
		//give us the 3 last letters indicate the type of file
		//this.type=filename.substring(filename.length()-3);18.1

		//load according to the type
		//hm.put("txt",new TextLevelCreator());18.1
		//hm.put("xml",new XmlLevelCreator());18.1
		//hm.put("obj",new ObjectLevelCreator());18.1


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

//	public String getType() {
	//	return type;
	//}
	//public void setType(String type) {
		//this.type = type;
	//}
	//public HashMap<String, ILevelCreator> getHm() {
	//	return hm;
	//}
	//public void setHm(HashMap<String, ILevelCreator> hm) {
	//	this.hm = hm;
	//}

}
